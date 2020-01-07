package xyz.pixelatedw.mineminenomi.entities.zoan;

import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.ZouHeavyModel;
import xyz.pixelatedw.mineminenomi.renderers.entities.ZoanMorphRenderer;
import xyz.pixelatedw.mineminenomi.renderers.entities.ZoanMorphRenderer.Factory;

public class ZoanInfoZouHeavy extends ZoanInfo
{
	public static final String FORM = "heavy";

	@Override
	public String getDevilFruit()
	{
		return "zouzou";
	}

	@Override
	public String getForm()
	{
		return FORM;
	}

	@Override
	public Factory getFactory()
	{
		return new ZoanMorphRenderer.Factory(new ZouHeavyModel(), "zouhybrid", 1.0, new float[] { 0, 0.2f, 0 });
	}

	@Override
	public Ability getAbility()
	{
		return null;//ModAttributes.ZOU_HEAVY_POINT;
	}

	@Override
	public double getWidth()
	{
		return 1.2;
	}

	@Override
	public double getHeight()
	{
		return 2.7;
	}

	@Override
	public double[] getHeldItemOffset()
	{
		return new double[] {-0.77, 0.45, -0.4};
	}

	@Override
	public double getHeldItemRotation()
	{
		return 80;
	}

}
