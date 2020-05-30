package xyz.pixelatedw.mineminenomi.effects;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import xyz.pixelatedw.mineminenomi.api.IHasOverlay;
import xyz.pixelatedw.wypi.WyHelper;

public class BlackBoxEffect extends Effect implements IHasOverlay
{
	public BlackBoxEffect()
	{
		super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
	}

	@Override
	public float[] getOverlayColor()
	{
		return new float[] { 0.0f,0.0f, 0.0f, 0.0f };
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
