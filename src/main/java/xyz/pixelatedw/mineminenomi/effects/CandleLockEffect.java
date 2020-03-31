package xyz.pixelatedw.mineminenomi.effects;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import xyz.pixelatedw.wypi.WyHelper;

public class CandleLockEffect extends Effect
{

	public CandleLockEffect()
	{
		super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
	}

}
