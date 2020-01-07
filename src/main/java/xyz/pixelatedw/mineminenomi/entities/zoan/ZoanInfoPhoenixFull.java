package xyz.pixelatedw.mineminenomi.entities.zoan;

import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.PhoenixFullModel;
import xyz.pixelatedw.mineminenomi.renderers.entities.ZoanMorphRenderer;
import xyz.pixelatedw.mineminenomi.renderers.entities.ZoanMorphRenderer.Factory;

public class ZoanInfoPhoenixFull extends ZoanInfo
{
	public static final String FORM = "phoenix";

	@Override
	public String getDevilFruit()
	{
		return "toritoriphoenix";
	}

	@Override
	public String getForm()
	{
		return FORM;
	}

	@Override
	public Factory getFactory()
	{
		return new ZoanMorphRenderer.Factory(new PhoenixFullModel(), "phoenixfull", 1.3, new float[] { 0, -0.5f, 0 });
	}

	@Override
	public Ability getAbility()
	{
		return null;//ModAttributes.PHOENIX_PHOENIX_POINT;
	}

	@Override
	public double getWidth()
	{
		return 0.9;
	}

	@Override
	public double getHeight()
	{
		return 0.8;
	}
	
	@Override
	public double[] getHeldItemOffset()
	{
		return new double[] {0, 0, 0};
	}

	@Override
	public double getHeldItemRotation()
	{
		return 0;
	}

}
