package xyz.pixelatedw.mineminenomi.packets.server;

import java.util.function.Supplier;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;

public class SEntityStatsSyncPacket
{
	private int entityId;
	private INBT data;

	public SEntityStatsSyncPacket() {}
	
	public SEntityStatsSyncPacket(int entityId, IEntityStats props)
	{
		this.data = new CompoundNBT();
		this.data = EntityStatsCapability.INSTANCE.getStorage().writeNBT(EntityStatsCapability.INSTANCE, props, null);
		this.entityId = entityId;
	}

	public void encode(PacketBuffer buffer)
	{
		buffer.writeInt(this.entityId);
		buffer.writeCompoundTag((CompoundNBT) this.data);
	}
	
	public static SEntityStatsSyncPacket decode(PacketBuffer buffer)
	{
		SEntityStatsSyncPacket msg = new SEntityStatsSyncPacket();
		msg.entityId = buffer.readInt();
		msg.data = buffer.readCompoundTag();
		return msg;
	}

	public static void handle(SEntityStatsSyncPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = Minecraft.getInstance().player;
				
				Entity target = player.world.getEntityByID(message.entityId);			
				if(target == null || !(target instanceof LivingEntity))
					return;
				
				IEntityStats props = EntityStatsCapability.get((LivingEntity) target);
				EntityStatsCapability.INSTANCE.getStorage().readNBT(EntityStatsCapability.INSTANCE, props, null, message.data);
			});	
		}
		ctx.get().setPacketHandled(true);
	}

}
