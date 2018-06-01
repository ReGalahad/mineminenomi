package xyz.pixelatedw.MineMineNoMi3.entities.particles;

import net.minecraft.client.particle.EntityAuraFX;
import net.minecraft.world.World;

public class EntityParticleFXSuna extends EntityAuraFX
{
	public EntityParticleFXSuna(World parWorld, double parX, double parY, double parZ, double parMotionX, double parMotionY, double parMotionZ) 
	{
    	super(parWorld, parX, parY, parZ, parMotionX, parMotionY, parMotionZ);
    	
        this.setParticleTextureIndex(5);
        this.particleScale = 2.5F;
        this.setRBGColorF(1.0F, 0.88F, 0.42F);
        this.particleMaxAge = 20 + this.rand.nextInt(10);
	}
}
