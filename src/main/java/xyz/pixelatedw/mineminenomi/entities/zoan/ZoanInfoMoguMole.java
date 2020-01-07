package xyz.pixelatedw.mineminenomi.entities.zoan;

import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.MoguMoleModel;
import xyz.pixelatedw.mineminenomi.renderers.entities.ZoanMorphRenderer;
import xyz.pixelatedw.mineminenomi.renderers.entities.ZoanMorphRenderer.Factory;

public class ZoanInfoMoguMole extends ZoanInfo
{
	public static final String FORM = "mole";

	@Override
	public String getDevilFruit()
	{
		return "mogumogu";
	}

	@Override
	public String getForm()
	{
		return FORM;
	}

	@Override
	public Factory getFactory()
	{
		return new ZoanMorphRenderer.Factory(new MoguMoleModel(), "mogu", 0.9, new float[] { 0, 0.05f, 0 });
	}

	@Override
	public Ability getAbility()
	{
		return null;//ModAttributes.MOGU_MOLE_POINT;
	}

	@Override
	public double getWidth()
	{
		return 0.8;
	}

	@Override
	public double getHeight()
	{
		return 1.5;
	}

	@Override
	public double[] getHeldItemOffset()
	{
		return new double[] {-0.45, 1.1, -0.4};
	}

	@Override
	public double getHeldItemRotation()
	{
		return 50;
	}
}
