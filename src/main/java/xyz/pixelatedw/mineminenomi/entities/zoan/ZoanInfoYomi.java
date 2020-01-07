package xyz.pixelatedw.mineminenomi.entities.zoan;

import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.YomiModel;
import xyz.pixelatedw.mineminenomi.renderers.entities.ZoanMorphRenderer;
import xyz.pixelatedw.mineminenomi.renderers.entities.ZoanMorphRenderer.Factory;

public class ZoanInfoYomi  extends ZoanInfo
{
	public static final String FORM = "yomi";

	@Override
	public String getDevilFruit()
	{
		return "yomiyomi";
	}

	@Override
	public String getForm()
	{
		return FORM;
	}

	@Override
	public Factory getFactory()
	{
		return new ZoanMorphRenderer.Factory(new YomiModel(), "skeleton", 1.1, new float[] { 0, 0.35f, 0 });
	}

	@Override
	public Ability getAbility()
	{
		return null;
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
		return new double[] {-0.28, 0.45, -0.45};
	}

	@Override
	public double getHeldItemRotation()
	{
		return 40;
	}
}
