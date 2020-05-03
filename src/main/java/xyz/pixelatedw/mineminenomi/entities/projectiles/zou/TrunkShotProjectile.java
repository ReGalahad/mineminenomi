package xyz.pixelatedw.mineminenomi.entities.projectiles.zou;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class TrunkShotProjectile extends AbilityProjectileEntity
{
	public TrunkShotProjectile(World world)
	{
		super(ZouProjectiles.TRUNK_SHOT, world);
	}

	public TrunkShotProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public TrunkShotProjectile(World world, double x, double y, double z)
	{
		super(ZouProjectiles.TRUNK_SHOT, world, x, y, z);
	}

	public TrunkShotProjectile(World world, LivingEntity player)
	{
		super(ZouProjectiles.TRUNK_SHOT, world, player);

		this.setDamage(10);
		this.setPhysical();
		
		this.onEntityImpactEvent = this::onEntityImpactEvent;
	}

	private void onEntityImpactEvent(LivingEntity target)
	{
		target.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 100, 1));
	}
}
