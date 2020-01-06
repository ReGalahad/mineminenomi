package xyz.pixelatedw.mineminenomi.packets.server;

import java.util.function.Supplier;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

public class SSpecialFlyingPacket
{

	private boolean specialFlying;
	
	public SSpecialFlyingPacket() {}
	
	public SSpecialFlyingPacket(boolean canSpecialFly)
	{
		this.specialFlying = canSpecialFly;
	}
	
	public void encode(PacketBuffer buffer)
	{
		buffer.writeBoolean(this.specialFlying);
	}
	
	public static SSpecialFlyingPacket decode(PacketBuffer buffer)
	{
		SSpecialFlyingPacket msg = new SSpecialFlyingPacket();
		msg.specialFlying = buffer.readBoolean();
		return msg;
	}
	
	public static void handle(SSpecialFlyingPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = Minecraft.getInstance().player;

				player.abilities.allowFlying = message.specialFlying;
				if(!message.specialFlying)
				{
					player.abilities.isFlying = false;
				}
			});
		}
		
		ctx.get().setPacketHandled(true);
	}
}
