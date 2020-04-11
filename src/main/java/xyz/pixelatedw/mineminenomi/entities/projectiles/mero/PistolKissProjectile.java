package xyz.pixelatedw.mineminenomi.entities.projectiles.mero;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class PistolKissProjectile extends AbilityProjectileEntity
{
	public PistolKissProjectile(World world)
	{
		super(MeroProjectiles.PISTOL_KISS, world);
	}

	public PistolKissProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public PistolKissProjectile(World world, double x, double y, double z)
	{
		super(MeroProjectiles.PISTOL_KISS, world, x, y, z);
	}

	public PistolKissProjectile(World world, LivingEntity player)
	{
		super(MeroProjectiles.PISTOL_KISS, world, player);

		this.setDamage(4);

		this.withEffects = () ->
		{
			return new EffectInstance[]
				{
						new EffectInstance(ModEffects.LOVESTRUCK, 100, 0)
				};
		};		
	}
}