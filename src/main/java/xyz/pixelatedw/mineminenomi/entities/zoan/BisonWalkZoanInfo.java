package xyz.pixelatedw.mineminenomi.entities.zoan;

import xyz.pixelatedw.mineminenomi.abilities.ushibison.BisonWalkPointAbility;
import xyz.pixelatedw.mineminenomi.api.ZoanInfo;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.BisonWalkModel;
import xyz.pixelatedw.mineminenomi.renderers.entities.ZoanMorphRenderer;
import xyz.pixelatedw.mineminenomi.renderers.entities.ZoanMorphRenderer.Factory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class BisonWalkZoanInfo extends ZoanInfo
{
	public static final String FORM = "walk";
	
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
		ZoanMorphRenderer.Factory factory = new ZoanMorphRenderer.Factory(new BisonWalkModel(), "bisonspeed");
		factory.setScale(1.4);
		factory.setOffset(0F, 0.8f, 0F);
		return factory;
	}

	@Override
	public Ability getAbility()
	{
		return BisonWalkPointAbility.INSTANCE;
	}

	@Override
	public double getWidth()
	{
		return 1.3;
	}

	@Override
	public double getHeight()
	{
		return 1.5;
	}
	
	@Override
	public float getShadowSize()
	{
		return 0.9F;
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
