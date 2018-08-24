package xyz.pixelatedw.MineMineNoMi3.entities.particles;

import java.util.TimerTask;

import net.minecraft.entity.EntityLivingBase;
import xyz.pixelatedw.MineMineNoMi3.MainMod;


/** TODO Change this shitty effect */
public class ParticleTaskCharge2 extends TimerTask
{
	
	private EntityLivingBase player;
	private String fxName;
	private double radius, posX, posY, posZ;
	private int density;

	public ParticleTaskCharge2(EntityLivingBase player, double posX, double posY, double posZ, String fxName, double radius, int density)
	{
		this.player = player;
		this.fxName = fxName;
		this.radius = radius;
		this.density = density;
		this.posX = posX;
		this.posY = posY;
		this.posZ = posZ;
	}
	
	public void run()
	{
		double phi = 0;
		double x, y, z;
		
		while(phi < density * Math.PI)
		{
			phi += Math.PI / 16;
			for(double t = 0; t <= 2 * Math.PI; t += Math.PI / 16)
			{
				for(double i = 0; i <= 1; i += 1)
				{
					
					x = 0.5 * (radius * phi + t) * Math.cos(t * phi + i);
					y = 0.1 * (t * 3 + radius * Math.PI);
					z = 0.5 * (radius * phi + t) * Math.sin(t * phi + i);
					
					try
					{
						if(this.fxName.contains("custom_"))
							MainMod.proxy.spawnCustomParticles(this.player, this.fxName.replace("custom_", ""), this.posX + x, this.posY - 0.7 + y, this.posZ + z, 0.0D, 0.0D, 0.0D);
						else							
							player.worldObj.spawnParticle(fxName, this.posX + x, this.posY - 0.7 + y, this.posZ + z, 0.0D, 0.0D, 0.0D);
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