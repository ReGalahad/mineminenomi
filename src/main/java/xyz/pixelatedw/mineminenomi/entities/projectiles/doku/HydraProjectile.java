package xyz.pixelatedw.mineminenomi.entities.projectiles.doku;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class HydraProjectile extends AbilityProjectileEntity
{
	public HydraProjectile(World world)
	{
		super(DokuProjectiles.HYDRA, world);
	}

	public HydraProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public HydraProjectile(World world, double x, double y, double z)
	{
		super(DokuProjectiles.HYDRA, world, x, y, z);
	}

	public HydraProjectile(World world, LivingEntity player)
	{
		super(DokuProjectiles.HYDRA, world, player);

		this.setDamage(30);
		this.setMaxLife(15);
		this.setPassThroughEntities();
		
		this.onEntityImpactEvent = this::onEntityImpactEvent;
	}
	
	private void onEntityImpactEvent(LivingEntity hitEntity)
	{
		hitEntity.addPotionEffect(new EffectInstance(Effects.POISON, 500, 1));
	}
}
