package xyz.pixelatedw.mineminenomi.data.entity.haki;

import net.minecraft.util.math.MathHelper;

public class HakiDataBase implements IHakiData
{
	private static final int KENBUNSHOKU_MAX_EXP = 1000;
	private static final int BUSOSHOKU_HARDENING_MAX_EXP = 1000;
	private static final int BUSOSHOKU_IMBUING_MAX_EXP = 1000;

	private int kenbunshokuExp, busoshokuHardeningExp, busoshokuImbuingExp;
	
	@Override
	public int getKenbunshokuHakiExp()
	{
		return this.kenbunshokuExp;
	}

	@Override
	public void alterKenbunshokuHakiExp(int value)
	{
		this.kenbunshokuExp += value;
		this.kenbunshokuExp = MathHelper.clamp(this.kenbunshokuExp, 0, KENBUNSHOKU_MAX_EXP);
	}

	@Override
	public void setKenbunshokuHakiExp(int value)
	{
		this.kenbunshokuExp = value;
	}

	@Override
	public int getBusoshokuHardeningHakiExp()
	{
		return this.busoshokuHardeningExp;
	}

	@Override
	public void alterBusoshokuHardeningHakiExp(int value)
	{
		this.busoshokuHardeningExp += value;
		this.busoshokuHardeningExp = MathHelper.clamp(this.busoshokuHardeningExp, 0, BUSOSHOKU_HARDENING_MAX_EXP);		
	}

	@Override
	public void setBusoshokuHardeningHakiExp(int value)
	{
		this.busoshokuHardeningExp = value;
	}

	@Override
	public int getBusoshokuImbuingHakiExp()
	{
		return this.busoshokuImbuingExp;
	}

	@Override
	public void alterBusoshokuImbuingHakiExp(int value)
	{
		this.busoshokuImbuingExp += value;
		this.busoshokuImbuingExp = MathHelper.clamp(this.busoshokuImbuingExp, 0, BUSOSHOKU_IMBUING_MAX_EXP);		
	}

	@Override
	public void setBusoshokuImbuingHakiExp(int value)
	{
		this.busoshokuImbuingExp = value;
	}

}
