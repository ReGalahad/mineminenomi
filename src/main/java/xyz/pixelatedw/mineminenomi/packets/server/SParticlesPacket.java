package xyz.pixelatedw.mineminenomi.packets.server;

import java.util.function.Supplier;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.ModMain;
import xyz.pixelatedw.mineminenomi.api.debug.WyDebug;

public class SParticlesPacket
{

	private String fx;
	private double posX, posY, posZ;
	
	public SParticlesPacket() {}
	
	public SParticlesPacket(String fx, LivingEntity living)
	{
		this.fx = fx;
		this.posX = living.posX;
		this.posY = living.posY;
		this.posZ = living.posZ;
	}
	
	public SParticlesPacket(String fx, double posX, double posY, double posZ)
	{
		this.fx = fx;		
		this.posX = posX;
		this.posY = posY;
		this.posZ = posZ;
	}
	
	public void encode(PacketBuffer buffer)
	{
		buffer.writeInt(this.fx.length());
		buffer.writeString(this.fx);
		buffer.writeDouble(this.posX);
		buffer.writeDouble(this.posY);
		buffer.writeDouble(this.posZ);
	}
	
	public static SParticlesPacket decode(PacketBuffer buffer)
	{
		SParticlesPacket msg = new SParticlesPacket();
		int len = buffer.readInt();
		msg.fx = buffer.readString(len);
		msg.posX = buffer.readDouble();
		msg.posY = buffer.readDouble();
		msg.posZ = buffer.readDouble();
		return msg;
	}
	
	public static void handle(SParticlesPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ModMain.proxy.getClientPlayer();
					
				String fx = message.fx;
				
				boolean pass = ModMain.proxy.spawnParticleEffects(player, message.posX, message.posY, message.posZ, fx);
						
				if(fx.contains("logiaEffect_"))
				{
					ModMain.proxy.spawnLogiaParticles(player.world, fx, message.posX, message.posY, message.posZ);
					
					pass = true;
				}	
				
				if(!pass)
					WyDebug.warn(message.fx + " is not an initialized particle effect !");
			});
		}
		
		ctx.get().setPacketHandled(true);
	}
}
