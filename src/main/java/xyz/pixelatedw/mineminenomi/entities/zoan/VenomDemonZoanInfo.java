package xyz.pixelatedw.mineminenomi.entities.zoan;

import xyz.pixelatedw.mineminenomi.abilities.doku.VenomDemonAbility;
import xyz.pixelatedw.mineminenomi.api.ZoanInfo;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.VenomDemonModel;
import xyz.pixelatedw.mineminenomi.renderers.entities.ZoanMorphRenderer;
import xyz.pixelatedw.mineminenomi.renderers.entities.ZoanMorphRenderer.Factory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class VenomDemonZoanInfo extends ZoanInfo
{
	public static final String FORM = "venom_demon";

	@Override
	public String getDevilFruit()
	{
		return "doku_doku";
	}

	@Override
	public String getForm()
	{
		return FORM;
	}

	@Override
	public Factory getFactory()
	{
		ZoanMorphRenderer.Factory factory = new ZoanMorphRenderer.Factory(new VenomDemonModel(), "venomdemon");
		factory.setScale(1.1);
		factory.setOffset(0, 0.3f, 0);
		return factory;
	}

	@Override
	public Ability getAbility()
	{
		return VenomDemonAbility.INSTANCE;
	}

	@Override
	public double getWidth()
	{
		return 1.5;
	}

	@Override
	public double getHeight()
	{
		return 3.0;
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
