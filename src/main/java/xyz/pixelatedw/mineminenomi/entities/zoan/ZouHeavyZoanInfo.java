package xyz.pixelatedw.mineminenomi.entities.zoan;

import xyz.pixelatedw.mineminenomi.abilities.zou.ZouHeavyPointAbility;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.ZouHeavyModel;
import xyz.pixelatedw.mineminenomi.renderers.entities.ZoanMorphRenderer;
import xyz.pixelatedw.mineminenomi.renderers.entities.ZoanMorphRenderer.Factory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class ZouHeavyZoanInfo extends ZoanInfo
{
	public static final String FORM = "heavy";

	@Override
	public String getDevilFruit()
	{
		return "zou_zou";
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
		return ZouHeavyPointAbility.INSTANCE;
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
