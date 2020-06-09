package xyz.pixelatedw.mineminenomi.packets.client;

import java.util.function.Supplier;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.data.entity.jollyroger.IJollyRoger;
import xyz.pixelatedw.mineminenomi.data.entity.jollyroger.JollyRogerCapability;

public class CSyncJollyRogerPacket
{
	private INBT data;

	public CSyncJollyRogerPacket() {}
	
	public CSyncJollyRogerPacket(IJollyRoger props)
	{
		this.data = new CompoundNBT();
		this.data = JollyRogerCapability.INSTANCE.getStorage().writeNBT(JollyRogerCapability.INSTANCE, props, null);
	}

	public void encode(PacketBuffer buffer)
	{
		buffer.writeCompoundTag((CompoundNBT) data);
	}
	
	public static CSyncJollyRogerPacket decode(PacketBuffer buffer)
	{
		CSyncJollyRogerPacket msg = new CSyncJollyRogerPacket();
		msg.data = buffer.readCompoundTag();
		return msg;
	}

	public static void handle(CSyncJollyRogerPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ctx.get().getSender();
				IJollyRoger props = JollyRogerCapability.get(player);
				
				JollyRogerCapability.INSTANCE.getStorage().readNBT(JollyRogerCapability.INSTANCE, props, null, message.data);
			});	
		}
		ctx.get().setPacketHandled(true);
	}

}
