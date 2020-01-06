package xyz.pixelatedw.mineminenomi.abilities.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effects;

public class DFEffectNoroSlowness extends DFEffect
{

	public DFEffectNoroSlowness(LivingEntity entity, int timer)
	{
		super(entity, timer, null);
	}

	@Override
	public void onEffectStart(LivingEntity entity)
	{

	}

	@Override
	public void onEffectEnd(LivingEntity entity)
	{		
		if(entity.isPotionActive(Effects.SLOWNESS))
		{
			int timer = entity.getActivePotionEffect(Effects.SLOWNESS).getDuration();
			new DFEffectNoroSlowness(entity, timer);
		}
	}
		
}
