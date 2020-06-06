package xyz.pixelatedw.mineminenomi.data.entity.haki;

public interface IHakiData
{
	int getKenbunshokuHakiExp();
	void alterKenbunshokuHakiExp(int value);
	void setKenbunshokuHakiExp(int value);
	
	int getBusoshokuHardeningHakiExp();
	void alterBusoshokuHardeningHakiExp(int value);
	void setBusoshokuHardeningHakiExp(int value);
	
	int getBusoshokuImbuingHakiExp();
	void alterBusoshokuImbuingHakiExp(int value);
	void setBusoshokuImbuingHakiExp(int value);
}
