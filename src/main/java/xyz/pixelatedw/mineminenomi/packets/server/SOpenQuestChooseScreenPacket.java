package xyz.pixelatedw.mineminenomi.packets.server;

import java.util.function.Supplier;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.entities.mobs.quest.givers.IQuestGiver;
import xyz.pixelatedw.mineminenomi.screens.QuestChooseScreen;

public class SOpenQuestChooseScreenPacket
{
	private int questGiverEntity;

	public SOpenQuestChooseScreenPacket()
	{
	}

	public SOpenQuestChooseScreenPacket(int questGiverEntity)
	{
		this.questGiverEntity = questGiverEntity;
	}

	public void encode(PacketBuffer buffer)
	{
		buffer.writeInt(this.questGiverEntity);
	}

	public static SOpenQuestChooseScreenPacket decode(PacketBuffer buffer)
	{
		SOpenQuestChooseScreenPacket msg = new SOpenQuestChooseScreenPacket();
		msg.questGiverEntity = buffer.readInt();
		return msg;
	}

	public static void handle(SOpenQuestChooseScreenPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		System.out.println("@");
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
			ctx.get().enqueueWork(() -> ClientHandler.handle(message));
		ctx.get().setPacketHandled(true);
	}

	public static class ClientHandler
	{
		@OnlyIn(Dist.CLIENT)
		public static void handle(SOpenQuestChooseScreenPacket message)
		{
			PlayerEntity player = Minecraft.getInstance().player;
			Entity questGiver = Minecraft.getInstance().world.getEntityByID(message.questGiverEntity);
			System.out.println(message.questGiverEntity);

			if (!(questGiver instanceof IQuestGiver))
				return;

			Minecraft.getInstance().displayGuiScreen(new QuestChooseScreen(player, questGiver, ((IQuestGiver) questGiver).getAvailableQuests(player)));
		}
	}
}
