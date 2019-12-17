package xyz.pixelatedw.mineminenomi.particles.effects;

import java.io.Serializable;

import net.minecraft.world.World;

public abstract class ParticleEffect implements Serializable
{
	public abstract void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ);
}