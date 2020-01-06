package xyz.pixelatedw.mineminenomi.api.network.packets.client;

import java.util.function.Supplier;
import java.util.stream.Collectors;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.api.data.quest.IQuestData;
import xyz.pixelatedw.mineminenomi.api.data.quest.QuestDataCapability;
import xyz.pixelatedw.mineminenomi.api.debug.WyDebug;

public class CQuestDataSyncPacket
{
	private INBT data;

	public CQuestDataSyncPacket() {}
	
	public CQuestDataSyncPacket(IQuestData questData)
	{
		this.data = new CompoundNBT();
		this.data = QuestDataCapability.INSTANCE.getStorage().writeNBT(QuestDataCapability.INSTANCE, questData, null);
	}

	public void encode(PacketBuffer buffer)
	{
		buffer.writeCompoundTag((CompoundNBT) this.data);
	}
	
	public static CQuestDataSyncPacket decode(PacketBuffer buffer)
	{
		CQuestDataSyncPacket msg = new CQuestDataSyncPacket();
		msg.data = buffer.readCompoundTag();
		return msg;
	}

	public static void handle(CQuestDataSyncPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ctx.get().getSender();			
				IQuestData questData = QuestDataCapability.get(player);
				
				QuestDataCapability.INSTANCE.getStorage().readNBT(QuestDataCapability.INSTANCE, questData, null, message.data);
				
				WyDebug.debug
				(
					"\nClient -> Server Sync for Quest Data"
					+ "\n Available Quests: \n" + questData.getInProgressQuests().parallelStream().map(x -> x.getQuestName()).collect(Collectors.toList()) 
					+ "\n Extra Data: \n" + questData.getInProgressQuests().parallelStream().map(x -> x.getExtraData()).collect(Collectors.toList()) 
					+ "\n"
				);	
			});	
		}
		ctx.get().setPacketHandled(true);
	}

}
