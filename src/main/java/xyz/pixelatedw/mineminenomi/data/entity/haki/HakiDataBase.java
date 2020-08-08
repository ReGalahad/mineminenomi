package xyz.pixelatedw.mineminenomi.data.entity.haki;

import net.minecraft.util.math.MathHelper;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.init.ModValues;

public class HakiDataBase implements IHakiData
{
	private float kenbunshokuExp, busoshokuHardeningExp, busoshokuImbuingExp;
	
	@Override
	public float getKenbunshokuHakiExp()
	{
		return this.kenbunshokuExp;
	}

	@Override
	public void alterKenbunshokuHakiExp(float value)
	{
		this.kenbunshokuExp += value;
		if(!CommonConfig.instance.getRemoveSoftLimitForExp())
			this.kenbunshokuExp = MathHelper.clamp(this.kenbunshokuExp, 0, ModValues.KENBUNSHOKU_MAX_EXP);
	}

	@Override
	public void setKenbunshokuHakiExp(float value)
	{
		this.kenbunshokuExp = value;
	}

	@Override
	public float getBusoshokuHardeningHakiExp()
	{
		return this.busoshokuHardeningExp;
	}

	@Override
	public void alterBusoshokuHardeningHakiExp(float value)
	{
		this.busoshokuHardeningExp += value;
		if(!CommonConfig.instance.getRemoveSoftLimitForExp())
			this.busoshokuHardeningExp = MathHelper.clamp(this.busoshokuHardeningExp, 0, ModValues.BUSOSHOKU_HARDENING_MAX_EXP);		
	}

	@Override
	public void setBusoshokuHardeningHakiExp(float value)
	{
		this.busoshokuHardeningExp = value;
	}

	@Override
	public float getBusoshokuImbuingHakiExp()
	{
		return this.busoshokuImbuingExp;
	}

	@Override
	public void alterBusoshokuImbuingHakiExp(float value)
	{
		this.busoshokuImbuingExp += value;
		if(!CommonConfig.instance.getRemoveSoftLimitForExp())
			this.busoshokuImbuingExp = MathHelper.clamp(this.busoshokuImbuingExp, 0, ModValues.BUSOSHOKU_IMBUING_MAX_EXP);		
	}

	@Override
	public void setBusoshokuImbuingHakiExp(float value)
	{
		this.busoshokuImbuingExp = value;
	}

}
