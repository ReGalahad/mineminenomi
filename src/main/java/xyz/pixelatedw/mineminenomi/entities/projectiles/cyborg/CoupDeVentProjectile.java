package xyz.pixelatedw.mineminenomi.entities.projectiles.cyborg;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class CoupDeVentProjectile extends AbilityProjectileEntity
{
	public CoupDeVentProjectile(World world)
	{
		super(CyborgProjectiles.COUP_DE_VENT, world);
	}

	public CoupDeVentProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public CoupDeVentProjectile(World world, double x, double y, double z)
	{
		super(CyborgProjectiles.COUP_DE_VENT, world, x, y, z);
	}

	public CoupDeVentProjectile(World world, LivingEntity player)
	{
		super(CyborgProjectiles.COUP_DE_VENT, world, player);
		
		this.setDamage(5);
		this.setPassThroughEntities();
		this.setMaxLife(15);
		
		this.onEntityImpactEvent = this::onEntityImpactEvent;
		this.onTickEvent = this::onTickEvent;
	}
	
	private void onEntityImpactEvent(LivingEntity target)
	{
		double xPower = WyHelper.randomWithRange(-10, 10);
		if(xPower >= 0) xPower += 10;
		else xPower -= 10;
		
		double zPower = WyHelper.randomWithRange(-10, 10);
		if(zPower >= 0) zPower += 10;
		else zPower -= 10;
		
		target.setPosition(target.posX, target.posY + 10, target.posZ);
		target.setMotion(xPower, 2.5, zPower);
		target.velocityChanged = true;
		target.fallDistance = 0;
		
		target.attackEntityFrom(DamageSource.causeMobDamage(this.getThrower()), 15);
	}
	
	private void onTickEvent()
	{
		for (int i = 0; i < 25; i++)
		{
			double offsetX = WyHelper.randomDouble() / 5;
			double offsetY = WyHelper.randomDouble() / 5;
			double offsetZ = WyHelper.randomDouble() / 5;

			((ServerWorld)this.world).spawnParticle(ParticleTypes.CRIT, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ, 1, 0, 0, 0, -0.1);
		}
	}
}