package xyz.pixelatedw.mineminenomi.effects;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import xyz.pixelatedw.mineminenomi.api.IHasOverlay;
import xyz.pixelatedw.wypi.WyHelper;

public class UnconsciousEffect extends Effect implements IHasOverlay
{

	public UnconsciousEffect()
	{
		super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
	}

	@Override
	public float[] getOverlayColor()
	{
		return new float[] { 0.0f, 0.0f, 0.0f, 0.9f };
	}
}
