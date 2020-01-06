package xyz.pixelatedw.mineminenomi.packets.server;

import java.util.function.Supplier;

import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;

public class SParticlesPacket
{

	private ParticleEffect effect;
	private double posX, posY, posZ;
	private double motionX, motionY, motionZ;

	public SParticlesPacket()
	{
	}

	public SParticlesPacket(ParticleEffect effect, double posX, double posY, double posZ)
	{
		this(effect, posX, posY, posZ, 0, 0, 0);
	}
	
	public SParticlesPacket(ParticleEffect effect, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		this.effect = effect;
		
		this.posX = posX;
		this.posY = posY;
		this.posZ = posZ;
		
		this.motionX = 0;
		this.motionY = 0;
		this.motionZ = 0;
	}

	public void encode(PacketBuffer buffer)
	{
		try
		{
			buffer.writeDouble(this.posX);
			buffer.writeDouble(this.posY);
			buffer.writeDouble(this.posZ);
			buffer.writeByteArray(WyHelper.serialize(this.effect));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static SParticlesPacket decode(PacketBuffer buffer)
	{
		SParticlesPacket msg = new SParticlesPacket();
		try
		{			
			msg.posX = buffer.readDouble();
			msg.posY = buffer.readDouble();
			msg.posZ = buffer.readDouble();
			msg.effect = (ParticleEffect) WyHelper.deserialize(buffer.readByteArray());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return msg;
	}

	public static void handle(SParticlesPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
		{
			ctx.get().enqueueWork(() ->
			{
				message.effect.spawn(Minecraft.getInstance().world, message.posX, message.posY, message.posZ, message.motionX, message.motionX, message.motionZ);
			});
		}

		ctx.get().setPacketHandled(true);
	}
}
