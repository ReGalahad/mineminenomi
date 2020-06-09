package xyz.pixelatedw.mineminenomi.packets.server;

import java.util.function.Supplier;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;

public class SSyncEntityStatsPacket
{
	private int entityId;
	private INBT data;

	public SSyncEntityStatsPacket()
	{
	}

	public SSyncEntityStatsPacket(int entityId, IEntityStats props)
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

	public static SSyncEntityStatsPacket decode(PacketBuffer buffer)
	{
		SSyncEntityStatsPacket msg = new SSyncEntityStatsPacket();
		msg.entityId = buffer.readInt();
		msg.data = buffer.readCompoundTag();
		return msg;
	}

	public static void handle(SSyncEntityStatsPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
		{
			ctx.get().enqueueWork(() ->
			{
				ClientHandler.handle(message);
			});
		}
		ctx.get().setPacketHandled(true);
	}
	
	public static class ClientHandler
	{
		@OnlyIn(Dist.CLIENT)
		public static void handle(SSyncEntityStatsPacket message)
		{
			Entity target = Minecraft.getInstance().world.getEntityByID(message.entityId);
			if (target == null || !(target instanceof LivingEntity))
				return;

			IEntityStats props = EntityStatsCapability.get((LivingEntity) target);
			EntityStatsCapability.INSTANCE.getStorage().readNBT(EntityStatsCapability.INSTANCE, props, null, message.data);
		}
	}

}
