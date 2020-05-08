package xyz.pixelatedw.mineminenomi.particles.effects.artofweather;

import java.awt.Color;

import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;

public class ChargedWeatherBallParticleEffect extends ParticleEffect
{
	private float red, green, blue;
	
	public ChargedWeatherBallParticleEffect(Color color)
	{
		this.red = color.getRed() / 255.0F;
		this.green = color.getGreen() / 255.0F;
		this.blue = color.getBlue() / 255.0F;
	}
	
	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		for (int i = 0; i < 10; i++)
		{
			double offsetX = WyHelper.randomDouble() / 1.5;
			double offsetY = WyHelper.randomDouble() / 1.5;
			double offsetZ = WyHelper.randomDouble() / 1.5;
	      
			GenericParticleData data = new GenericParticleData();
			data.setTexture(ModResources.GASU);
			data.setLife(4);
			data.setSize(2F);
			data.setMotion(0, 0.02, 0);
			data.setColor(this.red, this.green, this.blue);
			WyHelper.spawnParticles(data, (ServerWorld) world, posX + offsetX, posY + 1 + offsetY, posZ + offsetZ);
		}	
	}

}
