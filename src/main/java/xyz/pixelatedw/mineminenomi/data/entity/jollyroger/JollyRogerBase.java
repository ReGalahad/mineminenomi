package xyz.pixelatedw.mineminenomi.data.entity.jollyroger;

import java.util.Arrays;

import xyz.pixelatedw.mineminenomi.api.jollyroger.JollyRogerElement;
import xyz.pixelatedw.mineminenomi.init.ModJollyRogers;

public class JollyRogerBase implements IJollyRoger
{
	private JollyRogerElement base = ModJollyRogers.BASE_0;
	private JollyRogerElement[] backgrounds = new JollyRogerElement[2];
	private JollyRogerElement[] details = new JollyRogerElement[5];

	@Override
	public JollyRogerElement getBase()
	{
		return this.base;
	}

	@Override
	public void setBase(JollyRogerElement base)
	{
		this.base = base;
	}

	@Override
	public JollyRogerElement[] getBackgrounds()
	{
		return this.backgrounds;
	}

	@Override
	public void setBackgrounds(JollyRogerElement[] elements)
	{
		this.backgrounds = elements;
	}

	@Override
	public boolean addBackground(JollyRogerElement bg)
	{
		for (int i = 0; i < this.backgrounds.length; i++)
		{
			JollyRogerElement background = this.backgrounds[i];
			if (background == null)
			{
				this.backgrounds[i] = bg;
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean setBackground(int slot, JollyRogerElement bg)
	{
		if (!this.hasBackground(bg) && slot <= 2)
		{
			this.backgrounds[slot] = bg;
			return true;
		}
		return false;
	}

	@Override
	public boolean hasBackground(JollyRogerElement bg)
	{
		return Arrays.stream(this.backgrounds).parallel().anyMatch(background -> background.equals(bg));
	}

	@Override
	public JollyRogerElement[] getDetails()
	{
		return this.details;
	}

	@Override
	public void setDetails(JollyRogerElement[] elements)
	{
		this.details = elements;
	}
	
	@Override
	public boolean addDetail(JollyRogerElement det)
	{
		for (int i = 0; i < this.details.length; i++)
		{
			JollyRogerElement detail = this.details[i];
			if (detail == null)
			{
				this.details[i] = det;
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean setDetail(int slot, JollyRogerElement det)
	{
		if (!this.hasDetail(det) && slot <= 5)
		{
			this.details[slot] = det;
			return true;
		}
		return false;
	}

	@Override
	public boolean hasDetail(JollyRogerElement det)
	{
		return Arrays.stream(this.details).parallel().anyMatch(detail -> detail.equals(det));
	}

}
