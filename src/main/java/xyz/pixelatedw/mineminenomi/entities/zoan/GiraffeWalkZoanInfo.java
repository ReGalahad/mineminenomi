package xyz.pixelatedw.mineminenomi.entities.zoan;

import xyz.pixelatedw.mineminenomi.abilities.ushigiraffe.GiraffeWalkPointAbility;
import xyz.pixelatedw.mineminenomi.api.ZoanInfo;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.GiraffeWalkModel;
import xyz.pixelatedw.mineminenomi.renderers.entities.ZoanMorphRenderer;
import xyz.pixelatedw.mineminenomi.renderers.entities.ZoanMorphRenderer.Factory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class GiraffeWalkZoanInfo extends ZoanInfo
{
	public static final String FORM = "walk";

	@Override
	public String getDevilFruit()
	{
		return "ushi_ushi_giraffe";
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
		return GiraffeWalkPointAbility.INSTANCE;
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
