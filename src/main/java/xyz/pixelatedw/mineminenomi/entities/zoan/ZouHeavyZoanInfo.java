package xyz.pixelatedw.mineminenomi.entities.zoan;

import xyz.pixelatedw.mineminenomi.abilities.zou.ZouHeavyPointAbility;
import xyz.pixelatedw.mineminenomi.api.ZoanInfo;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.ZouHeavyModel;
import xyz.pixelatedw.mineminenomi.renderers.entities.ZoanMorphRenderer;
import xyz.pixelatedw.mineminenomi.renderers.entities.ZoanMorphRenderer.Factory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class ZouHeavyZoanInfo extends ZoanInfo
{
	public static final String FORM = "heavy";

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
		ZoanMorphRenderer.Factory factory = new ZoanMorphRenderer.Factory(new ZouHeavyModel(), "zouhybrid");
		factory.setScale(1);
		factory.setOffset(0, 0.2f, 0);
		return factory;	
	}

	@Override
	public Ability getAbility()
	{
		return ZouHeavyPointAbility.INSTANCE;
	}

	@Override
	public double getWidth()
	{
		return 1.2;
	}

	@Override
	public double getHeight()
	{
		return 2.7;
	}
	
	@Override
	public float getShadowSize()
	{
		return 1;
	}

	@Override
	public double[][] getHeldItemOffset()
	{
		return new double[][] 
			{
				{-0.6, -0.5, 0.0}, 
				{-0.05, 1.1, -0.4}
			};
	}

	@Override
	public double getHeldItemRotation()
	{
		return 0;
	}

}
