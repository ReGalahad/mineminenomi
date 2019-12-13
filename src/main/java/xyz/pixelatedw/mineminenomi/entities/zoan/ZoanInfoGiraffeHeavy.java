package xyz.pixelatedw.mineminenomi.entities.zoan;

import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttribute;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.GiraffeHeavyModel;
import xyz.pixelatedw.mineminenomi.renderers.entities.ZoanMorphRenderer;
import xyz.pixelatedw.mineminenomi.renderers.entities.ZoanMorphRenderer.Factory;

public class ZoanInfoGiraffeHeavy extends ZoanInfo
{
	public static final String FORM = "heavy";

	@Override
	public String getDevilFruit()
	{
		return "ushiushigiraffe";
	}

	@Override
	public String getForm()
	{
		return FORM;
	}

	@Override
	public Factory getFactory()
	{
		return new ZoanMorphRenderer.Factory(new GiraffeHeavyModel(), "giraffehybrid", 1.4, new float[] { 0, 0.7f, 0 });
	}

	@Override
	public AbilityAttribute getAttribute()
	{
		return ModAttributes.GIRAFFE_HEAVY_POINT;
	}

	@Override
	public double getWidth()
	{
		return 1.3;
	}

	@Override
	public double getHeight()
	{
		return 4;
	}

	@Override
	public double[] getHeldItemOffset()
	{
		return new double[] {-0.39, 0.75, -0.4};
	}

	@Override
	public double getHeldItemRotation()
	{
		return 40;
	}

}
