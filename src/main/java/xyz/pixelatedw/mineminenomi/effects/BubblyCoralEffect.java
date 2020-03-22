package xyz.pixelatedw.mineminenomi.effects;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import xyz.pixelatedw.wypi.WyHelper;

public class BubblyCoralEffect extends Effect
{

	public BubblyCoralEffect()
	{
		super(EffectType.NEUTRAL, WyHelper.hexToRGB("#000000").getRGB());
	}

}
