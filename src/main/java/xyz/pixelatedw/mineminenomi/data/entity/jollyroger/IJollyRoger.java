package xyz.pixelatedw.mineminenomi.data.entity.jollyroger;

import xyz.pixelatedw.mineminenomi.api.jollyroger.JollyRogerElement;

public interface IJollyRoger
{

	JollyRogerElement getBase();
	void setBase(JollyRogerElement base);
	
	JollyRogerElement[] getBackgrounds();
	void setBackgrounds(JollyRogerElement[] elements);
	boolean addBackground(JollyRogerElement bg);
	boolean setBackground(int slot, JollyRogerElement bg);
	boolean hasBackground(JollyRogerElement bg);
	
	JollyRogerElement[] getDetails();
	void setDetails(JollyRogerElement[] elements);
	boolean addDetail(JollyRogerElement detail);
	boolean setDetail(int slot, JollyRogerElement detail);
	boolean hasDetail(JollyRogerElement detail);
}
