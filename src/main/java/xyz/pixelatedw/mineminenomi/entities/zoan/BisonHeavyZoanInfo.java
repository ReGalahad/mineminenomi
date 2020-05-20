package xyz.pixelatedw.mineminenomi.entities.zoan;

import xyz.pixelatedw.mineminenomi.abilities.ushibison.BisonHeavyPointAbility;
import xyz.pixelatedw.mineminenomi.api.ZoanInfo;
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
		ZoanMorphRenderer.Factory factory = new ZoanMorphRenderer.Factory(new BisonHeavyModel(), "bisonpower");
		factory.setScale(1.4);
		factory.setOffset(0, 0.7f, 0);
		return factory;
	}

	@Override
	public Ability getAbility()
	{
		return BisonHeavyPointAbility.INSTANCE;
	}

	@Override
	public double getWidth()
	{
		return 1.2;
	}

	@Override
	public double getHeight()
	{
		return 2.5;
	}
	
	@Override
	public float getShadowSize()
	{
		return 0.8F;
	}

	@Override
	public double[][] getHeldItemOffset()
	{
		return new double[][] 
			{
				{-0.3F, -0.1F, 0F},
				{0.05F, 0.9F, -0.4F}
			};
	}

	@Override
	public double getHeldItemRotation()
	{
		return 0;
	}

}
