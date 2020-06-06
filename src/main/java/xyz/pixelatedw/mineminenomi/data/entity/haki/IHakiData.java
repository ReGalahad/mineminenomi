package xyz.pixelatedw.mineminenomi.data.entity.haki;

public interface IHakiData
{
	float getKenbunshokuHakiExp();
	void alterKenbunshokuHakiExp(float value);
	void setKenbunshokuHakiExp(float value);
	
	float getBusoshokuHardeningHakiExp();
	void alterBusoshokuHardeningHakiExp(float value);
	void setBusoshokuHardeningHakiExp(float value);
	
	float getBusoshokuImbuingHakiExp();
	void alterBusoshokuImbuingHakiExp(float value);
	void setBusoshokuImbuingHakiExp(float value);
}
