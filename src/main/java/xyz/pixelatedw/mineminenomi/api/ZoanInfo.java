package xyz.pixelatedw.mineminenomi.api;

import xyz.pixelatedw.mineminenomi.renderers.entities.ZoanMorphRenderer;
import xyz.pixelatedw.wypi.abilities.Ability;

public abstract class ZoanInfo
{	
	public abstract String getDevilFruit();
	public abstract String getForm();
	
	public abstract ZoanMorphRenderer.Factory getFactory();
	
	public abstract Ability getAbility();
	
	public abstract double getWidth();
	public abstract double getHeight();
	
	public abstract double[] getHeldItemOffset();
	public abstract double getHeldItemRotation();
}
