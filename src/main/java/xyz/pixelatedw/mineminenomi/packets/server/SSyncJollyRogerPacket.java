package xyz.pixelatedw.mineminenomi.packets.server;

import java.util.function.Supplier;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.data.entity.jollyroger.IJollyRoger;
import xyz.pixelatedw.mineminenomi.data.entity.jollyroger.JollyRogerCapability;

public class SSyncJollyRogerPacket
{
	private int entityId;
	private INBT data;

	public SSyncJollyRogerPacket()
	{
	}

	public SSyncJollyRogerPacket(int entityId, IJollyRoger props)
	{
		this.data = new CompoundNBT();
		this.data = JollyRogerCapability.INSTANCE.getStorage().writeNBT(JollyRogerCapability.INSTANCE, props, null);
		this.entityId = entityId;
	}

	public void encode(PacketBuffer buffer)
	{
		buffer.writeInt(this.entityId);
		buffer.writeCompoundTag((CompoundNBT) this.data);
	}

	public static SSyncJollyRogerPacket decode(PacketBuffer buffer)
	{
		SSyncJollyRogerPacket msg = new SSyncJollyRogerPacket();
		msg.entityId = buffer.readInt();
		msg.data = buffer.readCompoundTag();
		return msg;
	}

	public static void handle(SSyncJollyRogerPacket message, final Supplier<NetworkEvent.Context> ctx)
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
		public static void handle(SSyncJollyRogerPacket message)
		{
			Entity target = Minecraft.getInstance().world.getEntityByID(message.entityId);
			if (target == null || !(target instanceof PlayerEntity))
				return;

			IJollyRoger props = JollyRogerCapability.get((PlayerEntity) target);
			JollyRogerCapability.INSTANCE.getStorage().readNBT(JollyRogerCapability.INSTANCE, props, null, message.data);
		}
	}

}
