package xyz.pixelatedw.mineminenomi.particles.effects.kilo;

import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;

public class HeavyPunchParticleEffect extends ParticleEffect
{

    @Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
    {
    	/*
        for (int i = 0; i < 20; i++)
        {
            double offsetX = (new Random().nextInt(10) + 1.0D - 10.0D) / 10.0D;
            double offsetY = (new Random().nextInt(30) + 1.0D - 10.0D) / 10.0D;
            double offsetZ = (new Random().nextInt(10) + 1.0D - 10.0D) / 10.0D;

            CustomParticle cp = new CustomParticle(player.world, ModParticleTextures.KILO,
                            posX + offsetX,
                            posY + offsetY,
                            posZ + offsetZ,
                            0, 0, 0)
                            .setParticleScale(3F).setParticleGravity(0).setParticleAge(1);
            Minecraft.getInstance().particles.addEffect(cp);
        }
        */
    }

}
