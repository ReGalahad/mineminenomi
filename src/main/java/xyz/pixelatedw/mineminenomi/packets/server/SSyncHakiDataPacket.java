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
import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;

public class SSyncHakiDataPacket
{
	private int entityId;
	private INBT data;

	public SSyncHakiDataPacket() {}
	
	public SSyncHakiDataPacket(int entityId, IHakiData props)
	{
		this.data = new CompoundNBT();
		this.data = HakiDataCapability.INSTANCE.getStorage().writeNBT(HakiDataCapability.INSTANCE, props, null);
		this.entityId = entityId;
	}

	public void encode(PacketBuffer buffer)
	{
		buffer.writeInt(this.entityId);
		buffer.writeCompoundTag((CompoundNBT) this.data);
	}
	
	public static SSyncHakiDataPacket decode(PacketBuffer buffer)
	{
		SSyncHakiDataPacket msg = new SSyncHakiDataPacket();
		msg.entityId = buffer.readInt();
		msg.data = buffer.readCompoundTag();
		return msg;
	}

	public static void handle(SSyncHakiDataPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
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
		public static void handle(SSyncHakiDataPacket message)
		{
			Entity target = Minecraft.getInstance().world.getEntityByID(message.entityId);			
			if(target == null || !(target instanceof LivingEntity))
				return;
			
			IHakiData props = HakiDataCapability.get((LivingEntity) target);
			HakiDataCapability.INSTANCE.getStorage().readNBT(HakiDataCapability.INSTANCE, props, null, message.data);
		}
	}
}
