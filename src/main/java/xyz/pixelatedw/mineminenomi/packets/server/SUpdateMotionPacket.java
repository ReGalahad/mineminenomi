package xyz.pixelatedw.mineminenomi.packets.server;

import java.util.function.Supplier;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

public class SUpdateMotionPacket
{

	private String operation;
	private double motionX, motionY, motionZ;
	
	public SUpdateMotionPacket() {}

	public SUpdateMotionPacket(String string, double x, double y, double z)
	{
		this.operation = string;
		this.motionX = x;
		this.motionY = y;
		this.motionZ = z;
	}

	public void encode(PacketBuffer buffer)
	{
		buffer.writeInt(this.operation.length());
		buffer.writeString(this.operation);
		buffer.writeDouble(this.motionX);
		buffer.writeDouble(this.motionY);
		buffer.writeDouble(this.motionZ);
	}
	
	public static SUpdateMotionPacket decode(PacketBuffer buffer)
	{
		SUpdateMotionPacket msg = new SUpdateMotionPacket();
		int len = buffer.readInt();
		msg.operation = buffer.readString(len);
		msg.motionX = buffer.readDouble();
		msg.motionY = buffer.readDouble();
		msg.motionZ = buffer.readDouble();
		return msg;
	}
	
	public static void handle(SUpdateMotionPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
		{
			ctx.get().enqueueWork(() ->
			{	
				PlayerEntity player = Minecraft.getInstance().player;

				Entity target = null;

				target = player;
				
				if(target == null)
					return;
				
				if(message.operation.contains("+"))
					target.setMotion(target.getMotion().add(message.motionX, message.motionY, message.motionZ));
				if(message.operation.contains("-"))
					target.setMotion(target.getMotion().subtract(message.motionX, message.motionY, message.motionZ));	
				if(message.operation.contains("="))
					target.setMotion(message.motionX, message.motionY, message.motionZ);
				if(message.operation.contains("*"))
					target.setMotion(target.getMotion().mul(message.motionX, message.motionY, message.motionZ));	
			});
		}
		
		ctx.get().setPacketHandled(true);
	}
}
