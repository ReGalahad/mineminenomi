package xyz.pixelatedw.mineminenomi.particles.effects.haki;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;

public class HaoshokuHakiParticleEffect extends ParticleEffect
{
	private int level;
	
	public HaoshokuHakiParticleEffect(int level)
	{
		this.level = level;
	}
	
	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		double phi = 0;
		double x, y, z;
		double radius = 1;
		Random rand = world.rand;

		int i = 0;
		while (phi < 1)
		{
			phi += 0.5 * Math.PI;

			for (double theta = 0; theta <= 2 * Math.PI; theta += Math.PI / 64)
			{
				x = phi * Math.cos(theta);
				y = WyHelper.randomDouble() / 2;
				z = phi * Math.sin(theta);

				motionX = x * 1.2;
				motionY = 0.2 + (rand.nextDouble() / 8);
				motionZ = z * 1.2;

				GenericParticleData data = new GenericParticleData();
				if(i % 3 == 0)
					data.setTexture(ModResources.GASU2);
				else
					data.setTexture(ModResources.MOKU);
				data.setLife(20);
				data.setSize(8F);
				data.setMotion(motionX, motionY / 2, motionZ);
				data.setColor(0.7F, 0, 0.7F, 0.5F);
				WyHelper.spawnParticles(data, (ServerWorld) world, posX + x, posY - 0.2 + y, posZ + z);
				
				if(this.level >= 2)
				{
				
					data = new GenericParticleData();
					if(i % 3 == 0)
						data.setTexture(ModResources.GASU2);
					else
						data.setTexture(ModResources.MOKU);
					data.setLife(20);
					data.setSize(7F);
					data.setMotion(motionX, motionY / 1.5, motionZ);
					data.setColor(0.7F, 0, 0.7F, 0.5F);
					WyHelper.spawnParticles(data, (ServerWorld) world, posX + (x * 1.35), posY + 0.2 + y, posZ + (z * 1.35));
					
					data = new GenericParticleData();
					if(i % 3 == 0)
						data.setTexture(ModResources.GASU2);
					else
						data.setTexture(ModResources.MOKU);
					data.setLife(20);
					data.setSize(7F);
					data.setMotion(motionX, motionY, motionZ);
					data.setColor(0.7F, 0, 0.7F, 0.5F);
					WyHelper.spawnParticles(data, (ServerWorld) world, posX + (x * 1.25), posY + 1.2 + y, posZ + (z * 1.25));
							
					data = new GenericParticleData();
					if(i % 3 == 0)
						data.setTexture(ModResources.GASU2);
					else
						data.setTexture(ModResources.MOKU);
					data.setLife(20);
					data.setSize(8F);
					data.setMotion(motionX, motionY, motionZ);
					data.setColor(0.7F, 0, 0.7F, 0.5F);
					WyHelper.spawnParticles(data, (ServerWorld) world, posX + (x * 0.85), posY + 2.2 + y, posZ + (z * 0.85));
				}

				if(this.level >= 3)
				{
					for(int n = 0; n < 12; n++)
					{
						x = (radius * Math.cos(theta) * Math.sin(phi)) + WyHelper.randomDouble();
						y = radius * Math.cos(phi) + (WyHelper.randomDouble() * 2);
						z = (radius* Math.sin(theta) * Math.sin(phi)) + WyHelper.randomDouble();
						
						data = new GenericParticleData();
						if(i % 3 == 0)
							data.setTexture(ModResources.GASU);
						else
							data.setTexture(ModResources.YUKI);
						data.setLife(18);
						data.setSize(5F);
						data.setMotion(motionX / 1.1, 0.3, motionZ / 1.1);
						data.setColor(0.7F, 0, 0.7F);
						WyHelper.spawnParticles(data, (ServerWorld) world, posX + x, posY - 0.5 + y, posZ + z);
						
						motionY = Math.abs(y * 1.01);
						
						data = new GenericParticleData();
						if(i % 3 == 0)
							data.setTexture(ModResources.GASU);
						else
							data.setTexture(ModResources.YUKI);
						data.setLife(18);
						data.setSize(5F);
						data.setMotion(motionX / 1.1, motionY / 1.5, motionZ / 1.1);
						data.setColor(0.7F, 0, 0.7F);
						WyHelper.spawnParticles(data, (ServerWorld) world, posX + x, posY - 0.5 + y, posZ + z);			
					}
				}
				i++;
			}
		}
	}
	
}
