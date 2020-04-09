package xyz.pixelatedw.mineminenomi.effects;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import xyz.pixelatedw.wypi.WyHelper;

public class TensionHormoneEffect extends Effect
{

	public TensionHormoneEffect()
	{
		super(EffectType.BENEFICIAL, WyHelper.hexToRGB("#000000").getRGB());
	}
}
