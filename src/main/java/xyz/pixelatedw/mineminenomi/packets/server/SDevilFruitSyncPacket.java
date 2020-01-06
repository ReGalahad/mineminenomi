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
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;

public class SDevilFruitSyncPacket
{
	private int entityId;
	private INBT data;

	public SDevilFruitSyncPacket() {}
	
	public SDevilFruitSyncPacket(int entityId, IDevilFruit props)
	{
		this.data = new CompoundNBT();
		this.data = DevilFruitCapability.INSTANCE.getStorage().writeNBT(DevilFruitCapability.INSTANCE, props, null);
		this.entityId = entityId;
	}

	public void encode(PacketBuffer buffer)
	{
		buffer.writeInt(this.entityId);
		buffer.writeCompoundTag((CompoundNBT) this.data);
	}
	
	public static SDevilFruitSyncPacket decode(PacketBuffer buffer)
	{
		SDevilFruitSyncPacket msg = new SDevilFruitSyncPacket();
		msg.entityId = buffer.readInt();
		msg.data = buffer.readCompoundTag();
		return msg;
	}

	public static void handle(SDevilFruitSyncPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = Minecraft.getInstance().player;

				Entity target = player.world.getEntityByID(message.entityId);			
				if(target == null || !(target instanceof LivingEntity))
					return;

				IDevilFruit props = DevilFruitCapability.get((LivingEntity) target);
				DevilFruitCapability.INSTANCE.getStorage().readNBT(DevilFruitCapability.INSTANCE, props, null, message.data);
			});	
		}
		ctx.get().setPacketHandled(true);
	}

}
