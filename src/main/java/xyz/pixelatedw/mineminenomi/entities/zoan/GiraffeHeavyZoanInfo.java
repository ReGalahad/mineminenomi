package xyz.pixelatedw.mineminenomi.entities.zoan;

import xyz.pixelatedw.mineminenomi.abilities.ushigiraffe.GiraffeHeavyPointAbility;
import xyz.pixelatedw.mineminenomi.api.ZoanInfo;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.GiraffeHeavyModel;
import xyz.pixelatedw.mineminenomi.renderers.entities.ZoanMorphRenderer;
import xyz.pixelatedw.mineminenomi.renderers.entities.ZoanMorphRenderer.Factory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class GiraffeHeavyZoanInfo extends ZoanInfo
{
	public static final String FORM = "heavy";

	@Override
	public String getDevilFruit()
	{
		return "ushi_ushi_giraffe";
	}

	@Override
	public String getForm()
	{
		return FORM;
	}

	@Override
	public Factory getFactory()
	{
		ZoanMorphRenderer.Factory factory = new ZoanMorphRenderer.Factory(new GiraffeHeavyModel(), "giraffehybrid");
		factory.setScale(1.4);
		factory.setOffset(0, 0.7f, 0);
		return factory;
	}

	@Override
	public Ability getAbility()
	{
		return GiraffeHeavyPointAbility.INSTANCE;
	}

	@Override
	public double getWidth()
	{
		return 1.3;
	}

	@Override
	public double getHeight()
	{
		return 4;
	}
	
	@Override
	public float getShadowSize()
	{
		return 0.9F;
	}

	@Override
	public double[][] getHeldItemOffset()
	{
		return new double[][] 
			{
				{-0.4, -0.1, 0.0},
				{0.1, 0.65, -0.5}
			};
	}

	@Override
	public double getHeldItemRotation()
	{
		return 40;
	}

}
