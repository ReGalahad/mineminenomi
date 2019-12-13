package xyz.pixelatedw.mineminenomi.entities.zoan;

import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttribute;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.BisonHeavyModel;
import xyz.pixelatedw.mineminenomi.renderers.entities.ZoanMorphRenderer;
import xyz.pixelatedw.mineminenomi.renderers.entities.ZoanMorphRenderer.Factory;

public class ZoanInfoBisonHeavy extends ZoanInfo
{
	public static final String FORM = "heavy";
	
	@Override
	public String getDevilFruit()
	{
		return "ushiushibison";
	}

	@Override
	public String getForm()
	{
		return FORM;
	}

	@Override
	public Factory getFactory()
	{
		return new ZoanMorphRenderer.Factory(new BisonHeavyModel(), "bisonpower", 1.4, new float[] { 0, 0.7f, 0 });
	}

	@Override
	public AbilityAttribute getAttribute()
	{
		return ModAttributes.BISON_HEAVY_POINT;
	}

	@Override
	public double getWidth()
	{
		return 1.5;
	}

	@Override
	public double getHeight()
	{
		return 2.5;
	}

	@Override
	public double[] getHeldItemOffset()
	{
		return new double[] {-0.37, 0.6, -0.325};
	}

	@Override
	public double getHeldItemRotation()
	{
		return 50;
	}

}
