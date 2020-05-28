package xyz.pixelatedw.mineminenomi.effects;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import xyz.pixelatedw.mineminenomi.api.IHasOverlay;
import xyz.pixelatedw.wypi.WyHelper;

public class BubblyCoralEffect extends Effect implements IHasOverlay
{

	public BubblyCoralEffect()
	{
		super(EffectType.NEUTRAL, WyHelper.hexToRGB("#000000").getRGB());
	}

	@Override
	public float[] getOverlayColor()
	{
		return new float[] { 0.0F, 0.41F, 0.58F, 0.5F };
	}

}
