package xyz.pixelatedw.mineminenomi.entities.zoan;

import xyz.pixelatedw.mineminenomi.abilities.ushibison.BisonHeavyPointAbility;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.BisonHeavyModel;
import xyz.pixelatedw.mineminenomi.renderers.entities.ZoanMorphRenderer;
import xyz.pixelatedw.mineminenomi.renderers.entities.ZoanMorphRenderer.Factory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class BisonHeavyZoanInfo extends ZoanInfo
{
	public static final String FORM = "heavy";
	
	@Override
	public String getDevilFruit()
	{
		return "ushi_ushi_bison";
	}

	@Override
	public String getForm()
	{
		return FORM;
	}

	@Override
	public Factory getFactory()
	{
		return new ZoanMorphRenderer.Factory(new BisonHeavyModel(), "bisonpower", 1.4, new float[] { 0, 0.7f, 0 });
	}

	@Override
	public Ability getAbility()
	{
		return BisonHeavyPointAbility.INSTANCE;
	}

	@Override
	public double getWidth()
	{
		return 1.5;
	}

	@Override
	public double getHeight()
	{
		return 2.5;
	}

	@Override
	public double[] getHeldItemOffset()
	{
		return new double[] {-0.37, 0.6, -0.325};
	}

	@Override
	public double getHeldItemRotation()
	{
		return 50;
	}

}
