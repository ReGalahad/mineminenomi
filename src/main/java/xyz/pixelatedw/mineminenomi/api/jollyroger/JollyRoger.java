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
		for(int i = 0; i < this.backgrounds.length - 1; i++)
		{
			this.backgrounds[i] = new JollyRogerElement(LayerType.BACKGROUND);
		}
		
		for(int i = 0; i < this.details.length - 1; i++)
		{
			this.details[i] = new JollyRogerElement(LayerType.DETAIL);
		}
		
		this.base.setTexture(ModJollyRogers.BASE_0.getTexture());
	}

	public JollyRogerElement getBase()
	{
		return this.base;
	}
		
	public JollyRogerElement[] getBackgrounds()
	{
		return this.backgrounds;
	}
	
	public JollyRogerElement[] getDetails()
	{
		return this.details;
	}
}
