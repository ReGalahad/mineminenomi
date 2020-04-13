package xyz.pixelatedw.mineminenomi.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.wypi.WyHelper;

public class DrunkEffect extends Effect
{
	private double oldHealth = 0;
	private int storedDamage = 0;
	
	public DrunkEffect()
	{
		super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
	}

	@Override
	public boolean isReady(int duration, int amplifier)
	{
		return true;
	}

	@Override
	public void performEffect(LivingEntity entity, int amplifier)
	{
		if(this.oldHealth == 0)
			this.oldHealth = entity.getHealth();
		
		System.out.println(this.oldHealth);
		if(entity.getHealth() < this.oldHealth)
		{
			double damage = this.oldHealth - entity.getHealth();
			this.storedDamage += damage;
			entity.heal((float) damage);
			this.oldHealth = entity.getHealth();
		}
		
		if(amplifier >= 2 && amplifier < 4)
			entity.addPotionEffect(new EffectInstance(Effects.NAUSEA, 100, 0));
		else if(amplifier >= 4)
			entity.addPotionEffect(new EffectInstance(Effects.NAUSEA, 100, 2));
		
		EffectInstance effect = entity.getActivePotionEffect(this);
		
		if(effect.getDuration() < 2)
			entity.attackEntityFrom(DamageSource.MAGIC, this.storedDamage);
	}
}
