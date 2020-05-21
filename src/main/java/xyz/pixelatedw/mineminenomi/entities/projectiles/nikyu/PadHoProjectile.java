package xyz.pixelatedw.mineminenomi.entities.projectiles.nikyu;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class PadHoProjectile extends AbilityProjectileEntity
{
	public PadHoProjectile(World world)
	{
		super(NikyuProjectiles.PAD_HO, world);
	}

	public PadHoProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public PadHoProjectile(World world, double x, double y, double z)
	{
		super(NikyuProjectiles.PAD_HO, world, x, y, z);
	}

	public PadHoProjectile(World world, LivingEntity player)
	{
		super(NikyuProjectiles.PAD_HO, world, player);

		this.setDamage(8);

		this.onBlockImpactEvent = this::onBlockImpactEvent;
		this.onEntityImpactEvent = this::onEntityImpactEvent;
	}
	
	private void onEntityImpactEvent(LivingEntity target)
	{
		this.onBlockImpactEvent.onImpact(target.getPosition());
		
		double xPower = WyHelper.randomWithRange(-5, 5);
		if(xPower >= 0) xPower += 2;
		else xPower -= 2;
		
		double zPower = WyHelper.randomWithRange(-5, 5);
		if(zPower >= 0) zPower += 2;
		else zPower -= 2;
		
		target.setMotion(xPower, 0.1, zPower);
		target.velocityChanged = true;
		target.fallDistance = 0;
	}
	
	private void onBlockImpactEvent(BlockPos hit)
	{
		ExplosionAbility explosion = AbilityHelper.newExplosion(this.getThrower(), hit.getX(), hit.getY(), hit.getZ(), 1);
		explosion.setExplosionSound(true);
		explosion.setDamageOwner(false);
		explosion.setDestroyBlocks(true);
		explosion.setFireAfterExplosion(false);
		explosion.setSmokeParticles(new CommonExplosionParticleEffect(2));
		explosion.setDamageEntities(true);
		explosion.doExplosion();
	}
}