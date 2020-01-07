package xyz.pixelatedw.mineminenomi.entities.zoan;

import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.VenomDemonModel;
import xyz.pixelatedw.mineminenomi.renderers.entities.ZoanMorphRenderer;
import xyz.pixelatedw.mineminenomi.renderers.entities.ZoanMorphRenderer.Factory;

public class ZoanInfoVenomDemon  extends ZoanInfo
{
	public static final String FORM = "venom_demon";

	@Override
	public String getDevilFruit()
	{
		return "dokudoku";
	}

	@Override
	public String getForm()
	{
		return FORM;
	}

	@Override
	public Factory getFactory()
	{
		return new ZoanMorphRenderer.Factory(new VenomDemonModel(), "venomdemon", 1.1, new float[] { 0, 0.3f, 0 });
	}

	@Override
	public Ability getAbility()
	{
		return null;//ModAttributes.VENOM_DEMON;
	}

	@Override
	public double getWidth()
	{
		return 1.5;
	}

	@Override
	public double getHeight()
	{
		return 3.0;
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
