package xyz.pixelatedw.mineminenomi.entities.zoan;

import xyz.pixelatedw.mineminenomi.abilities.mogu.MoguHeavyPointAbility;
import xyz.pixelatedw.mineminenomi.api.ZoanInfo;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.MoguMoleModel;
import xyz.pixelatedw.mineminenomi.renderers.entities.ZoanMorphRenderer;
import xyz.pixelatedw.mineminenomi.renderers.entities.ZoanMorphRenderer.Factory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class MoguHeavyZoanInfo extends ZoanInfo
{
	public static final String FORM = "heavy";

	@Override
	public String getDevilFruit()
	{
		return "mogu_mogu";
	}

	@Override
	public String getForm()
	{
		return FORM;
	}

	@Override
	public Factory getFactory()
	{
		ZoanMorphRenderer.Factory factory = new ZoanMorphRenderer.Factory(new MoguMoleModel(), "mogu");
		factory.setScale(0.9);
		factory.setOffset(0, 0.05f, 0);
		return factory;
	}

	@Override
	public Ability getAbility()
	{
		return MoguHeavyPointAbility.INSTANCE;
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
	public float getShadowSize()
	{
		return 1;
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
