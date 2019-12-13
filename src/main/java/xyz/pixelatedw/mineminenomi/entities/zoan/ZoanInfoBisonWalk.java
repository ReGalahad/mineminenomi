package xyz.pixelatedw.mineminenomi.entities.zoan;

import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttribute;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.BisonWalkModel;
import xyz.pixelatedw.mineminenomi.renderers.entities.ZoanMorphRenderer;
import xyz.pixelatedw.mineminenomi.renderers.entities.ZoanMorphRenderer.Factory;

public class ZoanInfoBisonWalk extends ZoanInfo
{

	public static final String FORM = "walk";
	
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
		return new ZoanMorphRenderer.Factory(new BisonWalkModel(), "bisonspeed", 1.4, new float[] { 0, 0.8f, 0 });
	}

	@Override
	public AbilityAttribute getAttribute()
	{
		return ModAttributes.BISON_WALK_POINT;
	}

	@Override
	public double getWidth()
	{
		return 1.3;
	}

	@Override
	public double getHeight()
	{
		return 1.5;
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
