package xyz.pixelatedw.mineminenomi.entities.projectiles.horo;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class NegativeHollowProjectile extends AbilityProjectileEntity
{
	public NegativeHollowProjectile(World world)
	{
		super(HoroProjectiles.NEGATIVE_HOLLOW, world);
	}

	public NegativeHollowProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public NegativeHollowProjectile(World world, double x, double y, double z)
	{
		super(HoroProjectiles.NEGATIVE_HOLLOW, world, x, y, z);
	}

	public NegativeHollowProjectile(World world, LivingEntity player)
	{
		super(HoroProjectiles.NEGATIVE_HOLLOW, world, player);

		this.setDamage(10);
		
		this.withEffects = () ->
		{
			return new EffectInstance[]
			{
				new EffectInstance(Effects.NAUSEA, 200, 1),
				new EffectInstance(Effects.SLOWNESS, 200, 1), 
				new EffectInstance(ModEffects.NEGATIVE, 300, 1)
			};
		};
	}
}
