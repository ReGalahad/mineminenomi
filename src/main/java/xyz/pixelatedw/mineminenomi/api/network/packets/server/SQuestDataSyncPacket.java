package xyz.pixelatedw.mineminenomi.api.network.packets.server;

import java.util.function.Supplier;
import java.util.stream.Collectors;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.api.data.questdata.IQuestData;
import xyz.pixelatedw.mineminenomi.api.data.questdata.QuestDataCapability;
import xyz.pixelatedw.mineminenomi.api.debug.WyDebug;

public class SQuestDataSyncPacket
{
	private int entityId;
	private INBT data;

	public SQuestDataSyncPacket() {}
	
	public SQuestDataSyncPacket(int entityId, IQuestData questData)
	{
		this.data = new CompoundNBT();
		this.data = QuestDataCapability.INSTANCE.getStorage().writeNBT(QuestDataCapability.INSTANCE, questData, null);
		this.entityId = entityId;
	}

	public void encode(PacketBuffer buffer)
	{
		buffer.writeInt(this.entityId);
		buffer.writeCompoundTag((CompoundNBT) this.data);
	}
	
	public static SQuestDataSyncPacket decode(PacketBuffer buffer)
	{
		SQuestDataSyncPacket msg = new SQuestDataSyncPacket();
		msg.entityId = buffer.readInt();
		msg.data = buffer.readCompoundTag();
		return msg;
	}

	public static void handle(SQuestDataSyncPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = Minecraft.getInstance().player;

				Entity target = player.world.getEntityByID(message.entityId);			
				if(target == null || !(target instanceof LivingEntity))
					return;

				IQuestData questData = QuestDataCapability.get((LivingEntity) target);
				QuestDataCapability.INSTANCE.getStorage().readNBT(QuestDataCapability.INSTANCE, questData, null, message.data);
							
				WyDebug.debug
				(
					"\nServer -> Client Sync for Quest Data"
					+ "\n Available Quests: \n" + questData.getInProgressQuests().parallelStream().map(x -> x.getQuestName()).collect(Collectors.toList()) 
					+ "\n Extra Data: \n" + questData.getInProgressQuests().parallelStream().map(x -> x.getExtraData()).collect(Collectors.toList())
					+ "\n"
				);				
			});	
		}
		ctx.get().setPacketHandled(true);
	}

}
