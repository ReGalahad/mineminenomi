package xyz.pixelatedw.mineminenomi.api.jollyroger;

import xyz.pixelatedw.mineminenomi.api.jollyroger.JollyRogerElement.LayerType;
import xyz.pixelatedw.mineminenomi.init.ModJollyRogers;

public class JollyRoger
{
	private JollyRogerElement base = new JollyRogerElement(LayerType.BASE);
	private JollyRogerElement[] backgrounds = new JollyRogerElement[2];
	private JollyRogerElement[] details = new JollyRogerElement[5];
	
	public JollyRoger()
	{
		this.base.setTexture(ModJollyRogers.BASE_0.getTexture());
		
		for(int i = 0; i < this.backgrounds.length; i++)
		{
			this.backgrounds[i] = new JollyRogerElement(LayerType.BACKGROUND);
		}
		
		for(int i = 0; i < this.details.length; i++)
		{
			this.details[i] = new JollyRogerElement(LayerType.DETAIL);
		}
	}

	public JollyRogerElement getBase()
	{
		return this.base;
	}
	
	public void setBase(JollyRogerElement base)
	{
		this.base = base;
	}
		
	public JollyRogerElement[] getBackgrounds()
	{
		return this.backgrounds;
	}
	
	public void setBackgrounds(JollyRogerElement[] details)
	{
		this.backgrounds = details;
	}
	
	public JollyRogerElement[] getDetails()
	{
		return this.details;
	}
	
	public void setDetails(JollyRogerElement[] details)
	{
		this.details = details;
	}
}
