package xyz.pixelatedw.mineminenomi.effects;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import xyz.pixelatedw.mineminenomi.api.IHasOverlay;
import xyz.pixelatedw.wypi.WyHelper;

public class LovestruckEffect extends Effect implements IHasOverlay
{

	public LovestruckEffect()
	{
		super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
	}

	@Override
	public float[] getOverlayColor()
	{
		return new float[] { 0.9F, 0.8F, 0.9F, 0.8F };
	}
}
