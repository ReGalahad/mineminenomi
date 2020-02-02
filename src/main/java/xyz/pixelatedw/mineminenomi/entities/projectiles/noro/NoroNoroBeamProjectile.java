package xyz.pixelatedw.mineminenomi.entities.projectiles.noro;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.abilities.effects.DFEffectNoroSlowness;
import xyz.pixelatedw.mineminenomi.api.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.data.entity.extraeffects.ExtraEffectCapability;
import xyz.pixelatedw.mineminenomi.data.entity.extraeffects.IExtraEffect;

public class NoroNoroBeamProjectile extends AbilityProjectileEntity
{
	public NoroNoroBeamProjectile(World world)
	{
		super(NoroProjectiles.NORO_NORO_BEAM, world);
	}

	public NoroNoroBeamProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public NoroNoroBeamProjectile(World world, double x, double y, double z)
	{
		super(NoroProjectiles.NORO_NORO_BEAM, world, x, y, z);
	}

	public NoroNoroBeamProjectile(World world, LivingEntity player)
	{
		super(NoroProjectiles.NORO_NORO_BEAM, world, player);

		this.setMaxLife(10);
		
		this.onEntityImpactEvent = this::onEntityImpactEvent;
	}
	
	private void onEntityImpactEvent(LivingEntity hitEntity)
	{
		if( hitEntity.isPotionActive(Effects.SLOWNESS) && hitEntity.isPotionActive(Effects.MINING_FATIGUE) )
		{
			int newTimer = 0;
			int newAmplifier = 0;
			
			newTimer = hitEntity.getActivePotionEffect(Effects.SLOWNESS).getDuration() + 240;
			if(hitEntity.getActivePotionEffect(Effects.SLOWNESS).getAmplifier() + 10 < 200)
				newAmplifier = hitEntity.getActivePotionEffect(Effects.SLOWNESS).getAmplifier() + 10;
			else
				newAmplifier = 200;
			hitEntity.removePotionEffect(Effects.SLOWNESS);
			hitEntity.removePotionEffect(Effects.MINING_FATIGUE);
			hitEntity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, newTimer, newAmplifier));
			hitEntity.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, newTimer, newAmplifier));
			
			IExtraEffect props = ExtraEffectCapability.get(hitEntity);
			//if(!props.hasExtraEffect(ID.EXTRAEFFECT_NORO))
			//	new DFEffectNoroSlowness(target, newTimer);
			
		}
		else
		{
			hitEntity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 240, 10));
			hitEntity.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 240, 10));
			new DFEffectNoroSlowness(hitEntity, 240);					
		}	
	}
	
}