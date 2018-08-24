package xyz.pixelatedw.MineMineNoMi3.entities.particles;

import java.util.TimerTask;

import net.minecraft.entity.EntityLivingBase;
import xyz.pixelatedw.MineMineNoMi3.MainMod;

public class ParticleTaskTornado extends TimerTask
{
	
	private EntityLivingBase player;
	private String fxName;
	private double radius, opening, posX, posY, posZ, height;
	private int density, repeats;

	public ParticleTaskTornado(EntityLivingBase player, double posX, double posY, double posZ, String fxName, double radius, int density, double opening, double height)
	{
		this.player = player;
		this.fxName = fxName;
		this.radius = radius;
		this.density = density;
		this.opening = opening;
		this.height = height;
		this.posX = posX;
		this.posY = posY;
		this.posZ = posZ;
	}
	
	public void run()
	{
		double phi = 0;
		double x, y, z;
		
		while(phi < Math.PI * density)
		{
			phi += Math.PI / 16;
			for(double t = 0; t <= 2 * Math.PI; t += Math.PI / 16)
			{
				for(double i = 0; i <= 1; i += 1)
				{				
					x = opening * (radius * Math.PI + t) * Math.cos(t * phi + i * Math.PI);
					y = height * t;
					z = opening * (radius * Math.PI + t) * Math.sin(t * phi - i * Math.PI);
					
					try
					{
						if(this.fxName.contains("custom_"))
							MainMod.proxy.spawnCustomParticles(this.player, this.fxName.replace("custom_", ""), this.posX + x, this.posY + y, this.posZ + z, 0.0D, 0.0D, 0.0D);
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
}
