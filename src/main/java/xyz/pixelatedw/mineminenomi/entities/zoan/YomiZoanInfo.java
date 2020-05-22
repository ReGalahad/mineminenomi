package xyz.pixelatedw.mineminenomi.entities.zoan;

import xyz.pixelatedw.mineminenomi.api.ZoanInfo;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.YomiModel;
import xyz.pixelatedw.mineminenomi.renderers.entities.ZoanMorphRenderer;
import xyz.pixelatedw.mineminenomi.renderers.entities.ZoanMorphRenderer.Factory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class YomiZoanInfo extends ZoanInfo
{
	public static final String FORM = "yomi";

	@Override
	public String getDevilFruit()
	{
		return "yomi_yomi";
	}

	@Override
	public String getForm()
	{
		return FORM;
	}

	@Override
	public Factory getFactory()
	{
		ZoanMorphRenderer.Factory factory = new ZoanMorphRenderer.Factory(new YomiModel(), "skeleton");
		factory.setScale(1.1);
		factory.setOffset(0, 0.35f, 0);
		return factory;	
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
	public float getShadowSize()
	{
		return 0.55F;
	}

	@Override
	public double[][] getHeldItemOffset()
	{
		return new double[][] 
			{
				{-0.3F, -0.1F, 0F},
				{0.15F, 0.9F, -0.4F}
			};
	}

	@Override
	public double getHeldItemRotation()
	{
		return 40;
	}
}
