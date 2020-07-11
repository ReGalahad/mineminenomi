package xyz.pixelatedw.mineminenomi.packets.server;

import java.util.function.Supplier;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;

public class SSyncCrewDataPacket
{
	private int entityId;
	private CompoundNBT data;

	public SSyncCrewDataPacket()
	{
	}

	public SSyncCrewDataPacket(int entityId, ExtendedWorldData worldData)
	{
		this.data = new CompoundNBT();
		this.data = worldData.write(this.data);
		this.entityId = entityId;
	}

	public void encode(PacketBuffer buffer)
	{
		buffer.writeInt(this.entityId);
		buffer.writeCompoundTag(this.data);
	}

	public static SSyncCrewDataPacket decode(PacketBuffer buffer)
	{
		SSyncCrewDataPacket msg = new SSyncCrewDataPacket();
		msg.entityId = buffer.readInt();
		msg.data = buffer.readCompoundTag();
		return msg;
	}

	public static void handle(SSyncCrewDataPacket message, final Supplier<NetworkEvent.Context> ctx)
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
		public static void handle(SSyncCrewDataPacket message)
		{
			Entity target = Minecraft.getInstance().world.getEntityByID(message.entityId);
			if (target == null || !(target instanceof LivingEntity))
				return;

			ExtendedWorldData worldData = ExtendedWorldData.get(target.world);
			worldData.read(message.data);
		}
	}

}
