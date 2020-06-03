package xyz.pixelatedw.mineminenomi.entities.projectiles.gomu;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.gomu.GearSecondParticleEffect;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class GomuGomuNoJetPistolProjectile extends AbilityProjectileEntity
{
	private static final ParticleEffect PARTICLES = new GearSecondParticleEffect();

	public GomuGomuNoJetPistolProjectile(World world)
	{
		super(GomuProjectiles.GOMU_GOMU_NO_JET_PISTOL, world);
	}

	public GomuGomuNoJetPistolProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public GomuGomuNoJetPistolProjectile(World world, double x, double y, double z)
	{
		super(GomuProjectiles.GOMU_GOMU_NO_JET_PISTOL, world, x, y, z);
	}

	public GomuGomuNoJetPistolProjectile(World world, LivingEntity player)
	{
		super(GomuProjectiles.GOMU_GOMU_NO_JET_PISTOL, world, player);

		this.setDamage(10);
		this.setPhysical();
		this.setChangeHurtTime(true);
		
		this.onTickEvent = this::onTickEvent;
	}
	
	private void onTickEvent()
	{
		if(this.ticksExisted % 2 == 0)
			PARTICLES.spawn(this.world, this.posX, this.posY, this.posZ, 0, 0, 0);
	}	
}
