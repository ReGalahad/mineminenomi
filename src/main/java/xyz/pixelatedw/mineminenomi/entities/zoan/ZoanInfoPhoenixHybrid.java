package xyz.pixelatedw.mineminenomi.entities.zoan;

import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.PhoenixHybridModel;
import xyz.pixelatedw.mineminenomi.renderers.entities.ZoanMorphRenderer;
import xyz.pixelatedw.mineminenomi.renderers.entities.ZoanMorphRenderer.Factory;

public class ZoanInfoPhoenixHybrid extends ZoanInfo
{
	public static final String FORM = "hybrid";

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
		return new ZoanMorphRenderer.Factory(new PhoenixHybridModel(), "phoenixhybrid", 1, new float[] { 0, 0.2f, 0 });
	}

	@Override
	public Ability getAbility()
	{
		return null;//ModAttributes.PHOENIX_HYBRID_POINT;
	}

	@Override
	public double getWidth()
	{
		return 0.6;
	}

	@Override
	public double getHeight()
	{
		return 1.8;
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
