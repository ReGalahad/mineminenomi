package xyz.pixelatedw.mineminenomi.entities.zoan;

import xyz.pixelatedw.mineminenomi.abilities.toriphoenix.PhoenixAssaultPointAbility;
import xyz.pixelatedw.mineminenomi.api.ZoanInfo;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.PhoenixHybridModel;
import xyz.pixelatedw.mineminenomi.renderers.entities.ZoanMorphRenderer;
import xyz.pixelatedw.mineminenomi.renderers.entities.ZoanMorphRenderer.Factory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class PhoenixAssaultZoanInfo extends ZoanInfo
{
	public static final String FORM = "assault";

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
		ZoanMorphRenderer.Factory factory = new ZoanMorphRenderer.Factory(new PhoenixHybridModel(), "phoenixhybrid");
		factory.setScale(1);
		factory.setOffset(0, 0.2f, 0);
		return factory;
	}

	@Override
	public Ability getAbility()
	{
		return PhoenixAssaultPointAbility.INSTANCE;
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
	public float getShadowSize()
	{
		return 1;
	}
	
	@Override
	public double[][] getHeldItemOffset()
	{
		return null;
	}

	@Override
	public double getHeldItemRotation()
	{
		return 0;
	}

}
