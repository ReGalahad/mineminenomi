package xyz.pixelatedw.MineMineNoMi3.packets;

import java.util.Random;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

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
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper.Direction;
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
	
	public PacketParticles(String fx, double posX, double posY, double posZ) 
	{
		this.fx = fx;		
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
			else if(message.fx.equals("daienkai2"))
			{
				Timer timer = new Timer(true); 
				timer.schedule(ListParticleEffects.createCharge1FX(player, message.posX, message.posY + 0.5, message.posZ, "custom_" + ID.PARTICLE_NAME_MERA, 1.0, 1, 0.45, 0.2), 0);	
			}
			else if(message.fx.equals("fubuki"))
			{
				for (int i = 0; i < 1024 * 15; i++)
				{
					double offsetX = (new Random().nextInt(50) + 1.0D - 25.0D) / 1.0D;
					double offsetY = (new Random().nextInt(50) + 1.0D - 25.0D) / 1.0D;
					double offsetZ = (new Random().nextInt(50) + 1.0D - 25.0D) / 1.0D;
			      
					MainMod.proxy.spawnCustomParticles(player, ID.PARTICLE_NAME_YUKI, message.posX + offsetX, (message.posY + offsetY), message.posZ + offsetZ, 0.0D, -0.01D, 0.0D);
				}	
			}
			else if(message.fx.equals("whiteLauncher"))
			{
				for (int i = 0; i < 20; i++)
				{
					double offsetX = (new Random().nextInt(20) + 1.0D - 10.0D) / 15.0D;
					double offsetY = (new Random().nextInt(20) + 1.0D - 10.0D) / 15.0D;
					double offsetZ = (new Random().nextInt(20) + 1.0D - 10.0D) / 15.0D;
			      
					MainMod.proxy.spawnCustomParticles(player, ID.PARTICLE_NAME_MOKU, message.posX + offsetX, (message.posY + offsetY), message.posZ + offsetZ, 0.0D, -0.01D, 0.0D);
				}	
			}
			else if(message.fx.equals("samehada"))
			{
				Timer timer = new Timer(true); 
				timer.schedule(ListParticleEffects.createSphereFX(player, EnumParticleTypes.WATER_SPLASH.getParticleName(), 1.5, 10, 1), 0);
			}
			else if(message.fx.equals("sables"))
			{
				Timer timer = new Timer(true); 
				timer.schedule(ListParticleEffects.createTornadoFX(player, message.posX, message.posY, message.posZ, "custom_" + ID.PARTICLE_NAME_SUNA2, 2.0, 1, 0.35, 0.7), 0);
			}
			else if(message.fx.equals("groundDeath"))
			{
				Timer timer = new Timer(true); 
				timer.schedule(ListParticleEffects.createCharge1FX(player, message.posX, message.posY, message.posZ, "custom_" + ID.PARTICLE_NAME_SUNA2, 0.5, 1, 0.80, 0.2), 0);
			}
			else if(message.fx.equals("kokuteiCross"))
			{
				Timer timer = new Timer(true); 
				timer.schedule(ListParticleEffects.createCharge2FX(player, message.posX, message.posY, message.posZ, EnumParticleTypes.EXPLOSION_NORMAL.getParticleName(), 2.0, 1), 0);
			}
			else if(message.fx.equals("gearSecond"))
			{
				for (int i = 0; i < 2; i++)
				{
					double offsetX = (new Random().nextInt(20) + 1.0D - 10.0D) / 15.0D;
					double offsetY = (new Random().nextInt(20) + 1.0D - 10.0D) / 15.0D;
					double offsetZ = (new Random().nextInt(20) + 1.0D - 10.0D) / 15.0D;
			      
					player.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL.getParticleName(), message.posX + offsetX, (message.posY + 0.5) + offsetY, message.posZ + offsetZ, 0.0D, 0.1D, 0.0D);
				}	
			}
			else if(message.fx.equals("tenseiNoSoen"))
			{
				Timer timer = new Timer(true); 
				timer.schedule(ListParticleEffects.createTornadoFX(player, message.posX, message.posY + 4, message.posZ, "custom_" + ID.PARTICLE_NAME_BLUEFLAME, 4.0, 1, 0.15, -1.7), 0);
			}
			else if(message.fx.equals("tenseiNoSoen2"))
			{
				Timer timer = new Timer(true); 
				timer.schedule(ListParticleEffects.createWave1FX(player, message.posX, message.posY, message.posZ, "custom_" + ID.PARTICLE_NAME_BLUEFLAME), 0);
			}
			else if(message.fx.equals("blackworld"))
			{
				for (int i = 0; i < 2048 * 2; i++)
				{
					double offsetX = (new Random().nextInt(40) + 1.0D - 20.0D) / 2.0D;
					double offsetY = (new Random().nextInt(40) + 1.0D - 20.0D) / 35.0D;
					double offsetZ = (new Random().nextInt(40) + 1.0D - 20.0D) / 2.0D;
					
					MainMod.proxy.spawnCustomParticles(player, ID.PARTICLE_NAME_DARKNESS, message.posX + offsetX + new Random().nextInt(5), message.posY + offsetY, message.posZ + offsetZ + new Random().nextInt(5), 0.0D, 0.1D, 0.0D);
					MainMod.proxy.spawnCustomParticles(player, ID.PARTICLE_NAME_DARKNESS, message.posX + offsetX + new Random().nextInt(5), message.posY + 1.5 + offsetY, message.posZ + offsetZ + new Random().nextInt(5), 0.0D, 0.1D, 0.0D);		
					MainMod.proxy.spawnCustomParticles(player, ID.PARTICLE_NAME_DARKNESS, message.posX + offsetX + new Random().nextInt(5), message.posY + 2.5 + offsetY, message.posZ + offsetZ + new Random().nextInt(5), 0.0D, 0.1D, 0.0D);	
				}		
			}
			else if(message.fx.equals("elthor"))
			{
				for (int i = 0; i < 20; i++)
				{
					double offsetX = (new Random().nextInt(40) + 1.0D - 20.0D) / 10.0D;
					double offsetY = (new Random().nextInt(40) + 1.0D - 20.0D) / 10.0D;
					double offsetZ = (new Random().nextInt(40) + 1.0D - 20.0D) / 10.0D;
					
					MainMod.proxy.spawnCustomParticles(player, ID.PARTICLE_NAME_GORO, message.posX + offsetX, message.posY + 0.3 + offsetY, message.posZ + offsetZ, 0.0D, 0.0D, 0.0D);
				}
			}
			else if(message.fx.equals("yatanokagami"))
			{
				for (int i = 0; i < 20; i++)
				{
					double offsetX = (new Random().nextInt(40) + 1.0D - 20.0D) / 20.0D;
					double offsetY = (new Random().nextInt(40) + 1.0D) / 20.0D;
					double offsetZ = (new Random().nextInt(40) + 1.0D - 20.0D) / 20.0D;
					
					MainMod.proxy.spawnCustomParticles(player, ID.PARTICLE_NAME_PIKA, message.posX + offsetX, message.posY + 0.5 + offsetY, message.posZ + offsetZ, 0.0D, 0.0D, 0.0D);
				}
			}
			else if(message.fx.equals("amaterasu"))
			{
				double offsetX = 0;
				double offsetZ = 0;
				
				switch(WyHelper.get4Directions(player))
				{
					case NORTH:
						offsetZ = -4.5; break;
					case SOUTH:
						offsetZ = 4.5; break;
					case EAST:
						offsetX = 4.5; break;
					case WEST:
						offsetX = -4.5; break;
				}
				
				MainMod.proxy.spawnCustomParticles(player, ID.PARTICLE_NAME_PIKABIG, message.posX + offsetX, message.posY + 0.5, message.posZ + offsetZ, 0.0D, 0.0D, 0.0D);
			}
			else if(message.fx.equals("darkmatter"))
			{
				Timer timer = new Timer(true); 
				timer.schedule(ListParticleEffects.createTornadoFX(player, message.posX, message.posY, message.posZ, "custom_" + ID.PARTICLE_NAME_DARKNESS, 8.0, 2, 0.15, 0.0), 0);
			}
			else if(message.fx.equals("blackhole"))
			{
				for (int i = 0; i < 1024; i++)
				{
					double offsetX = (new Random().nextInt(20) - 10.0D) / 3.0D;
					double offsetZ = (new Random().nextInt(20) - 10.0D) / 3.0D;
					
					MainMod.proxy.spawnCustomParticles(player, ID.PARTICLE_NAME_DARKNESS, player.posX - 1 + offsetX + new Random().nextInt(5), player.posY - 2.5 + new Random().nextDouble(), player.posZ - 1 + offsetZ + new Random().nextInt(5), 0.0D, 0.0D, 0.0D);
				}
			}
			else if(message.fx.equals("kumonosugaki"))
			{
				double offsetX = 0;
				double offsetZ = 0;
				
				switch(WyHelper.get4Directions(player))
				{
					case NORTH:
						offsetZ = -1.5; break;
					case SOUTH:
						offsetZ = 1.5; break;
					case EAST:
						offsetX = 1.5; break;
					case WEST:
						offsetX = -1.5; break;
				}
				
				MainMod.proxy.spawnCustomParticles(player, ID.PARTICLE_NAME_ITO, message.posX + offsetX, message.posY + 1.0, message.posZ + offsetZ, 0.0D, 0.0D, 0.0D);
			}
			else if(message.fx.equals("flash"))
			{				
				MainMod.proxy.spawnCustomParticles(player, ID.PARTICLE_NAME_PIKABIG, message.posX, message.posY + 3.5, message.posZ, 0.0D, 0.0D, 0.0D);
			}
			else if(message.fx.equals("gekishin"))
			{				
				double offsetX = 0;
				double offsetZ = 0;
				
				switch(WyHelper.get4Directions(player))
				{
					case NORTH:
						offsetZ = -2.5; break;
					case SOUTH:
						offsetZ = 2.5; break;
					case EAST:
						offsetX = 2.5; break;
					case WEST:
						offsetX = -2.5; break;
				}
				
				MainMod.proxy.spawnCustomParticles(player, ID.PARTICLE_NAME_GURABIG, message.posX + offsetX, message.posY + 1.0, message.posZ + offsetZ, 0.0D, 0.0D, 0.0D);
				
				for (int i = 0; i < 50; i++)
				{
					offsetX = (new Random().nextInt(40) + 1.0D - 20.0D) / 2.0D;
					double offsetY = (new Random().nextInt(40) + 1.0D - 20.0D) / 10.0D;
					offsetZ = (new Random().nextInt(40) + 1.0D - 20.0D) / 2.0D;
					
					player.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE.getParticleName(), message.posX + offsetX + new Random().nextInt(5), message.posY + offsetY, message.posZ + offsetZ + new Random().nextInt(5), 0.0, 0.0, 0.0);
				}		
			}
			else
			{
				Logger.getGlobal().log(Level.INFO, message.fx + " is not an initialized particle task !");
			}
			
			
			return null;	
		}
	}

}
