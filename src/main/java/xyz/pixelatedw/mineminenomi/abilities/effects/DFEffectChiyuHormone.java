package xyz.pixelatedw.mineminenomi.abilities.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class DFEffectChiyuHormone extends DFEffect
{

	public DFEffectChiyuHormone(LivingEntity entity, int timer)
	{
		super(entity, timer, null);
	}

	@Override
	public void onEffectStart(LivingEntity entity)
	{
		entity.addPotionEffect(new EffectInstance(Effects.REGENERATION, 100, 2));
	}

	@Override
	public void onEffectEnd(LivingEntity entity)
	{
		entity.addPotionEffect(new EffectInstance(Effects.HUNGER, 300, 1));
	}
		
}
