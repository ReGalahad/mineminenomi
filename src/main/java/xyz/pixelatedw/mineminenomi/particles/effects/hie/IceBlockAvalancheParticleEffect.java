package xyz.pixelatedw.mineminenomi.particles.effects.hie;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;

public class IceBlockAvalancheParticleEffect extends ParticleEffect {

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY,
			double motionZ) {
		// TODO Auto-generated method stub
	}

	public void spawnWithRadius(World world, double posX, double posY, double posZ, double motionX, double motionY,
			double motionZ, Entity e) {
		// TODO Auto-generated method stub
		double density = 6;
		double phi = 0;

		while (phi < Math.PI) {
			phi += Math.PI / 4;

			for (double theta = 0; theta <= 2 * Math.PI; theta += Math.PI / 6) {
				double radius = e.ticksExisted  / 10;
				if(radius > 8) {
					radius = 8;
				}
				double x = radius * Math.cos(theta) * Math.sin(phi);
				double y = posY - radius - 1;
				double z = radius * Math.sin(theta) * Math.sin(phi); 
				motionX = x / 20;
				motionY = 0.001;
				motionZ = z / 20;

				GenericParticleData data = new GenericParticleData();
				data.setTexture(ModResources.HIE);
				data.setLife(400);
				world.addParticle(data, posX + x, y, posZ + z, motionX, motionY, motionZ);
			}
		}
	}
}
