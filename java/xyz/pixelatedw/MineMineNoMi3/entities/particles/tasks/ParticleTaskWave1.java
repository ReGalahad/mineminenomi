package xyz.pixelatedw.MineMineNoMi3.entities.particles.tasks;

import java.util.Random;
import java.util.TimerTask;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import xyz.pixelatedw.MineMineNoMi3.MainMod;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.EntityParticleFX;

public class ParticleTaskWave1 extends TimerTask
{
	
	private EntityLivingBase player;
	private Object particle;
	private double posX, posY, posZ;

	public ParticleTaskWave1(EntityLivingBase player, double posX, double posY, double posZ, String particle)
	{
		this.player = player;
		this.particle = particle;
		this.posX = posX;
		this.posY = posY;
		this.posZ = posZ;
	}
	
	public ParticleTaskWave1(EntityLivingBase player, double posX, double posY, double posZ, EntityParticleFX particle)
	{
		this.player = player;
		this.particle = particle;
		this.posX = posX;
		this.posY = posY;
		this.posZ = posZ;
	}
	
	public void run()
	{
		double t = 0;
		double x, y, z;
		Random rand = this.player.getRNG();

		while(t < 20)
		{
			t += 0.5 * Math.PI;
			
			for(double theta = 0; theta <= 4 * Math.PI; theta += Math.PI / 16)
			{
				x = t * Math.cos(theta);
				//y = Math.sin(theta / (0.5 * t));
				//y = Math.exp(t * -0.4) + Math.sin((t*t) * 0.05);
				y = rand.nextInt(1) + rand.nextDouble();
				z = t * Math.sin(theta);
				
				try
				{
					if(this.particle instanceof EntityParticleFX)
					{
						EntityParticleFX clone = ((EntityParticleFX)particle).clone(this.posX + (rand.nextInt(4) - 2) + x, this.posY + 1 + y, this.posZ  + (rand.nextInt(4) - 2) + z);
						Minecraft.getMinecraft().effectRenderer.addEffect(clone);
					}
					else							
						player.worldObj.spawnParticle((String) particle, this.posX + x, this.posY + y, this.posZ + z, 0.0D, 0.0D, 0.0D);
					Thread.sleep(1);
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}	
			}
		}
	}
}