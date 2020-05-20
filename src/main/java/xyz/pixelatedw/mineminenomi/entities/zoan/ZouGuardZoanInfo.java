package xyz.pixelatedw.mineminenomi.entities.zoan;

import xyz.pixelatedw.mineminenomi.abilities.zou.ZouGuardPointAbility;
import xyz.pixelatedw.mineminenomi.api.ZoanInfo;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.ZouGuardModel;
import xyz.pixelatedw.mineminenomi.renderers.entities.ZoanMorphRenderer;
import xyz.pixelatedw.mineminenomi.renderers.entities.ZoanMorphRenderer.Factory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class ZouGuardZoanInfo extends ZoanInfo
{
	public static final String FORM = "guard";

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
		ZoanMorphRenderer.Factory factory = new ZoanMorphRenderer.Factory(new ZouGuardModel(), "zoufull");
		factory.setScale(1.3);
		factory.setOffset(0, 0.65f, 0);
		return factory;	
	}

	@Override
	public Ability getAbility()
	{
		return ZouGuardPointAbility.INSTANCE;
	}

	@Override
	public double getWidth()
	{
		return 1.7;
	}

	@Override
	public double getHeight()
	{
		return 2.2;
	}
	
	@Override
	public float getShadowSize()
	{
		return 1;
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
