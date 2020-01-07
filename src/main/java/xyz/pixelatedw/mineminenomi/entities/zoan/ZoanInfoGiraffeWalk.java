package xyz.pixelatedw.mineminenomi.entities.zoan;

import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.GiraffeWalkModel;
import xyz.pixelatedw.mineminenomi.renderers.entities.ZoanMorphRenderer;
import xyz.pixelatedw.mineminenomi.renderers.entities.ZoanMorphRenderer.Factory;

public class ZoanInfoGiraffeWalk extends ZoanInfo
{
	public static final String FORM = "walk";

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
		return new ZoanMorphRenderer.Factory(new GiraffeWalkModel(), "giraffefull", 1.55, new float[] { 0, 0.95f, 0 });
	}

	@Override
	public Ability getAbility()
	{
		return null;//ModAttributes.GIRAFFE_WALK_POINT;
	}

	@Override
	public double getWidth()
	{
		return 1.5;
	}

	@Override
	public double getHeight()
	{
		return 4;
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
