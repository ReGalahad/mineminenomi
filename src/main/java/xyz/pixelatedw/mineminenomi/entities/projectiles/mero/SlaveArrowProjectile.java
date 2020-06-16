package xyz.pixelatedw.mineminenomi.entities.projectiles.mero;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class SlaveArrowProjectile extends AbilityProjectileEntity
{
	public SlaveArrowProjectile(World world)
	{
		super(MeroProjectiles.SLAVE_ARROW, world);
	}

	public SlaveArrowProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public SlaveArrowProjectile(World world, double x, double y, double z)
	{
		super(MeroProjectiles.SLAVE_ARROW, world, x, y, z);
	}

	public SlaveArrowProjectile(World world, LivingEntity player)
	{
		super(MeroProjectiles.SLAVE_ARROW, world, player);

		this.setDamage(0.8F);
		this.setChangeHurtTime(true);

		this.withEffects = () ->
		{
			return new EffectInstance[]
				{
						new EffectInstance(ModEffects.LOVESTRUCK, 100, 0)
				};
		};		
	}
}