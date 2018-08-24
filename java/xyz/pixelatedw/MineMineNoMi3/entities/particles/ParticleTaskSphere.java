package xyz.pixelatedw.MineMineNoMi3.entities.particles;

import java.util.TimerTask;

import net.minecraft.entity.EntityLivingBase;
import xyz.pixelatedw.MineMineNoMi3.MainMod;

public class ParticleTaskSphere extends TimerTask
{
	
	private EntityLivingBase player;
	private String fxName;
	private int density, repeats;
	private double radius, posX, posY, posZ;

	public ParticleTaskSphere(EntityLivingBase player, double posX, double posY, double posZ, String fxName, double radius, int density, int repeats)
	{
		this.player = player;
		this.posX = posX;
		this.posY = posY;
		this.posZ = posZ;
		this.fxName = fxName;
		this.radius = radius;
		this.density = density;
		this.repeats = repeats;
	}
	
	public void run()
	{
		double phi = 0;
		while(phi < repeats * Math.PI)
		{
			phi += Math.PI / density;
			
			for(double theta = 0; theta <= 2 * Math.PI; theta += Math.PI / density)
			{
				double r = radius;
				double x = r * Math.cos(theta) * Math.sin(phi);
				double y = r * Math.cos(phi) + 1.5;
				double z = r * Math.sin(theta) * Math.sin(phi);
				
				try 
				{
					if(this.fxName.contains("custom_"))
						MainMod.proxy.spawnCustomParticles(this.player, this.fxName.replace("custom_", ""), this.posX + x, this.posY + y, this.posZ + z, 0.0D, 0.0D, 0.0D);
					else							
						player.worldObj.spawnParticle(fxName, this.posX + x, this.posY + y, this.posZ + z, 0.0D, 0.0D, 0.0D);
					Thread.sleep(2);
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}	
			}
		}
	}
}