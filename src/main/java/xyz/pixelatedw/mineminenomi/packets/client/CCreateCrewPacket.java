package xyz.pixelatedw.mineminenomi.packets.client;

import java.util.function.Supplier;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.api.crew.Crew;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.wypi.WyHelper;

public class CCreateCrewPacket
{
	private String name;

	public CCreateCrewPacket()
	{
	}

	public CCreateCrewPacket(String name)
	{
		this.name = name;
	}

	public void encode(PacketBuffer buffer)
	{
		buffer.writeInt(this.name.length());
		buffer.writeString(this.name);
	}

	public static CCreateCrewPacket decode(PacketBuffer buffer)
	{
		CCreateCrewPacket msg = new CCreateCrewPacket();
		int len = buffer.readInt();
		msg.name = buffer.readString(len);
		return msg;
	}

	public static void handle(CCreateCrewPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ctx.get().getSender();
				ExtendedWorldData worldProps = ExtendedWorldData.get(player.world);

				Crew crew = new Crew(message.name, player.getUniqueID());
				worldProps.addCrew(crew);
				crew.create(player.world);

				if (CommonConfig.instance.isCrewWorldMessageEnabled())
				{
					TranslationTextComponent newCrewMsg = new TranslationTextComponent(ModI18n.CREW_MESSAGE_NEW_CREW, message.name);
					for (PlayerEntity target : player.world.getPlayers())
					{
						WyHelper.sendMsgToPlayer(target, TextFormatting.GOLD + newCrewMsg.getFormattedText());
					}
				}
			});
		}
		ctx.get().setPacketHandled(true);
	}
}
