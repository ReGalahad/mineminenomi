package xyz.pixelatedw.MineMineNoMi3.packets;

import java.util.Random;
import java.util.Timer;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.MainMod;
import xyz.pixelatedw.MineMineNoMi3.api.EnumParticleTypes;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;
import xyz.pixelatedw.MineMineNoMi3.lists.ListParticleEffects;

public class PacketParticles implements IMessage
{
	private String fx;
	private double posX, posY, posZ;
	
	public PacketParticles() {}
	
	public PacketParticles(String fx, EntityLivingBase living) 
	{
		this.fx = fx;		
		this.posX = living.posX;
		this.posY = living.posY;
		this.posZ = living.posZ;
	}
	
	public PacketParticles(String cmd, double posX, double posY, double posZ) 
	{
		this.fx = cmd;		
		this.posX = posX;
		this.posY = posY;
		this.posZ = posZ;
	}
	
	public void fromBytes(ByteBuf buf) 
	{
		this.fx = ByteBufUtils.readUTF8String(buf);
		this.posX = buf.readDouble();
		this.posY = buf.readDouble();
		this.posZ = buf.readDouble();
	}

	public void toBytes(ByteBuf buf) 
	{
		ByteBufUtils.writeUTF8String(buf, this.fx);
		buf.writeDouble(this.posX);
		buf.writeDouble(this.posY);
		buf.writeDouble(this.posZ);
	}

	public static class ClientHandler implements IMessageHandler<PacketParticles, IMessage>
	{
		@SideOnly(Side.CLIENT)
		public IMessage onMessage(PacketParticles message, MessageContext ctx) 
		{
			final EntityPlayer player = Minecraft.getMinecraft().thePlayer;
			ExtendedEntityStats props = ExtendedEntityStats.get(player);
			
			if(message.fx.equals("daienkai"))
			{
				for (int i = 0; i < 20; i++)
				{
					double offsetX = (new Random().nextInt(40) + 1.0D - 20.0D) / 20.0D;
					double offsetY = (new Random().nextInt(40) + 1.0D - 20.0D) / 20.0D;
					double offsetZ = (new Random().nextInt(40) + 1.0D - 20.0D) / 20.0D;
					
					MainMod.proxy.spawnCustomParticles(player, ID.PARTICLE_NAME_MERA, message.posX + offsetX, (message.posY + offsetY), message.posZ + offsetZ, 0.0D, -0.01D, 0.0D);
				}		
			}
			if(message.fx.equals("daienkai2"))
			{
				Timer timer = new Timer(true); 
				timer.schedule(ListParticleEffects.createChargeEffect1FX(player, message.posX, message.posY + 0.5, message.posZ, "custom_" + ID.PARTICLE_NAME_MERA, 1.0, 1, 0.45, 0.2), 0);	
			}
			if(message.fx.equals("fubuki"))
			{
				for (int i = 0; i < 1024 * 15; i++)
				{
					double offsetX = (new Random().nextInt(50) + 1.0D - 25.0D) / 1.0D;
					double offsetY = (new Random().nextInt(50) + 1.0D - 25.0D) / 1.0D;
					double offsetZ = (new Random().nextInt(50) + 1.0D - 25.0D) / 1.0D;
			      
					MainMod.proxy.spawnCustomParticles(player, ID.PARTICLE_NAME_YUKI, message.posX + offsetX, (message.posY + offsetY), message.posZ + offsetZ, 0.0D, -0.01D, 0.0D);
				}	
			}
			if(message.fx.equals("whiteLauncher"))
			{
				for (int i = 0; i < 20; i++)
				{
					double offsetX = (new Random().nextInt(20) + 1.0D - 10.0D) / 15.0D;
					double offsetY = (new Random().nextInt(20) + 1.0D - 10.0D) / 15.0D;
					double offsetZ = (new Random().nextInt(20) + 1.0D - 10.0D) / 15.0D;
			      
					MainMod.proxy.spawnCustomParticles(player, ID.PARTICLE_NAME_MOKU, message.posX + offsetX, (message.posY + offsetY), message.posZ + offsetZ, 0.0D, -0.01D, 0.0D);
				}	
			}
			if(message.fx.equals("samehada"))
			{
				Timer timer = new Timer(true); 
				timer.schedule(ListParticleEffects.createSphereFX(player, EnumParticleTypes.WATER_SPLASH.getParticleName(), 1.5, 10, 1), 0);
			}
			if(message.fx.equals("sables"))
			{
				Timer timer = new Timer(true); 
				timer.schedule(ListParticleEffects.createTornadoFX(player, message.posX, message.posY, message.posZ, "custom_" + ID.PARTICLE_NAME_SUNA2, 2.0, 1, 0.35, 0.7), 0);
			}
			if(message.fx.equals("groundDeath"))
			{
				Timer timer = new Timer(true); 
				timer.schedule(ListParticleEffects.createChargeEffect1FX(player, message.posX, message.posY, message.posZ, "custom_" + ID.PARTICLE_NAME_SUNA2, 0.5, 1, 0.80, 0.2), 0);
			}
			if(message.fx.equals("kokuteiCross"))
			{
				Timer timer = new Timer(true); 
				timer.schedule(ListParticleEffects.createGroundEffect1FX(player, message.posX, message.posY, message.posZ, EnumParticleTypes.EXPLOSION_NORMAL.getParticleName(), 2.0, 1), 0);
			}
			if(message.fx.equals("gearSecond"))
			{
				for (int i = 0; i < 2; i++)
				{
					double offsetX = (new Random().nextInt(20) + 1.0D - 10.0D) / 15.0D;
					double offsetY = (new Random().nextInt(20) + 1.0D - 10.0D) / 15.0D;
					double offsetZ = (new Random().nextInt(20) + 1.0D - 10.0D) / 15.0D;
			      
					player.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL.getParticleName(), message.posX + offsetX, (message.posY + 0.5) + offsetY, message.posZ + offsetZ, 0.0D, 0.1D, 0.0D);
				}	
			}
			
			return null;	
		}
	}

}
