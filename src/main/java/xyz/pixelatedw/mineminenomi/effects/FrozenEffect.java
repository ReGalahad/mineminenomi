package xyz.pixelatedw.mineminenomi.effects;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import xyz.pixelatedw.mineminenomi.api.IHasOverlay;
import xyz.pixelatedw.wypi.WyHelper;

public class FrozenEffect extends Effect implements IHasOverlay
{
	public FrozenEffect()
	{
		super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
	}

	@Override
	public float[] getOverlayColor()
	{
		return new float[] { 0.3f,0.92f, 0.87f, 0.9f };
	}
}
