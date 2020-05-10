package xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather;

import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.EntityCloud;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.sniper.KemuriBoshiParticleEffect;

public class MirageTempoCloudEntity extends EntityCloud
{
	private static final ParticleEffect PARTICLES = new KemuriBoshiParticleEffect();

	public MirageTempoCloudEntity(World world)
	{
		super(world);
	}
	
	@Override
	public void tick()
	{
		super.tick();
		if(!this.world.isRemote)
		{
			if(this.ticksExisted % 2 == 0)
				PARTICLES.spawn(this.world, this.posX, this.posY, this.posZ, 0, 0, 0);
		}
	}
}