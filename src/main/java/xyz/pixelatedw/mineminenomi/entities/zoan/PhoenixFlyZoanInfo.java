package xyz.pixelatedw.mineminenomi.entities.zoan;

import xyz.pixelatedw.mineminenomi.abilities.toriphoenix.PhoenixFlyPointAbility;
import xyz.pixelatedw.mineminenomi.api.ZoanInfo;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.PhoenixFullModel;
import xyz.pixelatedw.mineminenomi.renderers.entities.ZoanMorphRenderer;
import xyz.pixelatedw.mineminenomi.renderers.entities.ZoanMorphRenderer.Factory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class PhoenixFlyZoanInfo extends ZoanInfo
{
	public static final String FORM = "fly";

	@Override
	public String getDevilFruit()
	{
		return "tori_tori_phoenix";
	}

	@Override
	public String getForm()
	{
		return FORM;
	}

	@Override
	public Factory getFactory()
	{
		ZoanMorphRenderer.Factory factory = new ZoanMorphRenderer.Factory(new PhoenixFullModel(), "phoenixfull");
		factory.setScale(1.3);
		factory.setOffset(0, -0.5f, 0);
		return factory;
	}

	@Override
	public Ability getAbility()
	{
		return PhoenixFlyPointAbility.INSTANCE;
	}

	@Override
	public double getWidth()
	{
		return 0.9;
	}

	@Override
	public double getHeight()
	{
		return 0.8;
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
