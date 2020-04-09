package xyz.pixelatedw.mineminenomi.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import xyz.pixelatedw.wypi.WyHelper;

public class ChiyuHormoneEffect extends Effect
{

	public ChiyuHormoneEffect()
	{
		super(EffectType.BENEFICIAL, WyHelper.hexToRGB("#000000").getRGB());
	}

	@Override
	public boolean isReady(int duration, int amplifier)
	{
		return duration % 30 == 0;
	}

	@Override
	public void performEffect(LivingEntity entity, int amplifier)
	{
		if (entity.getHealth() < entity.getMaxHealth())
			entity.heal(2.0F);
	}
}
