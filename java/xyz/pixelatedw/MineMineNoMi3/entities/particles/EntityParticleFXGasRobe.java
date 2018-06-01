package xyz.pixelatedw.MineMineNoMi3.entities.particles;

import net.minecraft.client.particle.EntityAuraFX;
import net.minecraft.world.World;

public class EntityParticleFXGasRobe extends EntityAuraFX
{
	public EntityParticleFXGasRobe(World parWorld, double parX, double parY, double parZ, double parMotionX, double parMotionY, double parMotionZ) 
	{
    	super(parWorld, parX, parY, parZ, parMotionX, parMotionY, parMotionZ);
    	
        this.setParticleTextureIndex(5);
        this.particleScale = 2.0F;
        this.setRBGColorF(0.87F, 0.5F, 0.87F);
        this.particleMaxAge = 20 + this.rand.nextInt(10);
	}
}
