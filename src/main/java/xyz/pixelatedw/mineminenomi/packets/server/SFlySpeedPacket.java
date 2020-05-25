package xyz.pixelatedw.mineminenomi.packets.server;

import java.util.function.Supplier;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

public class SFlySpeedPacket
{
	private float speed;
	
	public SFlySpeedPacket() {}
	
	public SFlySpeedPacket(float speed)
	{
		this.speed = speed;
	}
	
	public void encode(PacketBuffer buffer)
	{
		buffer.writeFloat(this.speed);
	}
	
	public static SFlySpeedPacket decode(PacketBuffer buffer)
	{
		SFlySpeedPacket msg = new SFlySpeedPacket();
		msg.speed = buffer.readFloat();
		return msg;
	}
	
	public static void handle(SFlySpeedPacket message, final Supplier<NetworkEvent.Context> ctx)
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
		public static void handle(SFlySpeedPacket message)
		{
			PlayerEntity player = Minecraft.getInstance().player;

			player.abilities.setFlySpeed(message.speed);
		}
	}
}
