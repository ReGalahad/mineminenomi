package xyz.pixelatedw.MineMineNoMi3.entities.particles;

import java.util.Random;
import java.util.TimerTask;

import net.minecraft.entity.EntityLivingBase;
import xyz.pixelatedw.MineMineNoMi3.MainMod;

public class ParticleTaskWave1 extends TimerTask
{
	
	private EntityLivingBase player;
	private String fxName;
	private double posX, posY, posZ;

	public ParticleTaskWave1(EntityLivingBase player, double posX, double posY, double posZ, String fxName)
	{
		this.player = player;
		this.fxName = fxName;
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
			
			for(double theta = 0; theta <= 8 * Math.PI; theta += Math.PI / 8)
			{
				x = t * Math.cos(theta);
				y = Math.sin(theta / (0.8 * t));
				//y = Math.exp(t * -0.4) + Math.sin((t*t) * 0.05);
				z = t * Math.sin(theta);
				
				try
				{
					if(this.fxName.contains("custom_"))
						MainMod.proxy.spawnCustomParticles(this.player, this.fxName.replace("custom_", ""), this.posX + (rand.nextInt(4) - 2) + x, this.posY + 1 + y, this.posZ  + (rand.nextInt(4) - 2) + z, 0.0D, 0.0D, 0.0D);
					else							
						player.worldObj.spawnParticle(fxName, this.posX + x, this.posY + y, this.posZ + z, 0.0D, 0.0D, 0.0D);
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