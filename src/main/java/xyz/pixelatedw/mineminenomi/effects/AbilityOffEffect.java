package xyz.pixelatedw.mineminenomi.effects;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import xyz.pixelatedw.wypi.WyHelper;

public class AbilityOffEffect extends Effect
{

	public AbilityOffEffect()
	{
		super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
	}

	@Override
	public boolean shouldRender(EffectInstance effect)
	{
		return false;
	}

	@Override
	public boolean shouldRenderHUD(EffectInstance effect)
	{
		return false;
	}
}
