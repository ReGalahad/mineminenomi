package xyz.pixelatedw.mineminenomi.packets.client;

import java.util.function.Supplier;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;

public class CSyncDevilFruitPacket
{
	private INBT data;

	public CSyncDevilFruitPacket() {}
	
	public CSyncDevilFruitPacket(IDevilFruit props)
	{
		this.data = new CompoundNBT();
		this.data = DevilFruitCapability.INSTANCE.getStorage().writeNBT(DevilFruitCapability.INSTANCE, props, null);
	}

	public void encode(PacketBuffer buffer)
	{
		buffer.writeCompoundTag((CompoundNBT) data);
	}
	
	public static CSyncDevilFruitPacket decode(PacketBuffer buffer)
	{
		CSyncDevilFruitPacket msg = new CSyncDevilFruitPacket();
		msg.data = buffer.readCompoundTag();
		return msg;
	}

	public static void handle(CSyncDevilFruitPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ctx.get().getSender();
				IDevilFruit props = DevilFruitCapability.get(player);
				
				DevilFruitCapability.INSTANCE.getStorage().readNBT(DevilFruitCapability.INSTANCE, props, null, message.data);
			});	
		}
		ctx.get().setPacketHandled(true);
	}

}
