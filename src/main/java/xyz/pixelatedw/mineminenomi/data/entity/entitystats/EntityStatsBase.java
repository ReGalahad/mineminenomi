package xyz.pixelatedw.mineminenomi.data.entity.entitystats;

import xyz.pixelatedw.mineminenomi.init.ModValues;
import xyz.pixelatedw.wypi.WyHelper;

public class EntityStatsBase implements IEntityStats
{

	private int doriki, belly, extol, cola = 100, maxCola = 100, ultraCola = 0;
	private long bounty;
	private String faction = "", race = "", fightingStyle = "";
	private boolean hasShadow = true, hasHeart = true, inCombatMode = false;
	
	@Override
	public int getDoriki()
	{
		return this.doriki;
	}

	@Override
	public void alterDoriki(int value)
	{
		if (this.doriki + value < 0)
			this.doriki = 0;
		else if(this.doriki + value > ModValues.MAX_DORIKI)
			this.doriki = ModValues.MAX_DORIKI;
		else
			this.doriki = this.doriki + value;
	}

	@Override
	public void setDoriki(int value)
	{
		if(value <= ModValues.MAX_DORIKI)
			this.doriki = value;
		else
			this.doriki = ModValues.MAX_DORIKI;
	}

	@Override
	public int getBelly()
	{
		return this.belly;
	}

	@Override
	public void alterBelly(int value)
	{
		if (this.belly + value < 0)
			this.belly = 0;
		else
			this.belly = this.belly + value;
	}

	@Override
	public void setBelly(int value)
	{
		this.belly = value;
	}

	@Override
	public int getExtol()
	{
		return this.extol;
	}

	@Override
	public void alterExtol(int value)
	{
		if (this.extol + value < 0)
			this.extol = 0;
		else
			this.extol = this.extol + value;
	}

	@Override
	public void setExtol(int value)
	{
		this.extol = value;
	}

	@Override
	public long getBounty()
	{
		return this.bounty;
	}

	@Override
	public void alterBounty(long value)
	{
		if (this.bounty + value < 0)
			this.bounty = 0;
		else
			this.bounty = this.bounty + value;
	}

	@Override
	public void setBounty(long value)
	{
		this.bounty = value;
	}

	@Override
	public int getCola()
	{
		return this.cola;
	}

	@Override
	public void alterCola(int value)
	{
		if (this.cola + value < 0)
			this.cola = 0;
		else
			this.cola = this.cola + value;
	}

	@Override
	public void setCola(int value)
	{
		this.cola = value;
	}

	@Override
	public int getMaxCola()
	{
		return this.maxCola;
	}

	@Override
	public void alterMaxCola(int value)
	{
		if (this.maxCola + value < 0)
			this.maxCola = 0;
		else
			this.maxCola = this.maxCola + value;
	}

	@Override
	public void setMaxCola(int value)
	{
		this.maxCola = value;
	}

	@Override
	public int getUltraCola()
	{
		return this.ultraCola;
	}

	@Override
	public void setUltraCola(int value)
	{
		if (this.ultraCola + value < 0)
			this.ultraCola = 0;
		else
			this.ultraCola = this.ultraCola + value;
	}

	@Override
	public void addUltraCola(int value)
	{
		this.ultraCola += value;
	}

	@Override
	public boolean isPirate()
	{
		if (WyHelper.isNullOrEmpty(this.faction))
			return false;

		return this.faction.equalsIgnoreCase(ModValues.PIRATE);
	}

	@Override
	public boolean isMarine()
	{
		if (WyHelper.isNullOrEmpty(this.faction))
			return false;

		return this.faction.equalsIgnoreCase(ModValues.MARINE);
	}

	@Override
	public boolean isBountyHunter()
	{
		if (WyHelper.isNullOrEmpty(this.faction))
			return false;

		return this.faction.equalsIgnoreCase(ModValues.BOUNTY_HUNTER);
	}

	@Override
	public boolean isRevolutionary()
	{
		if (WyHelper.isNullOrEmpty(this.faction))
			return false;

		return this.faction.equalsIgnoreCase(ModValues.REVOLUTIONARY);
	}
	
	@Override
	public boolean isBandit()
	{
		if (WyHelper.isNullOrEmpty(this.faction))
			return false;

		return this.faction.equalsIgnoreCase(ModValues.BANDIT);
	}
	
	@Override
	public boolean hasFaction()
	{
		if (WyHelper.isNullOrEmpty(this.faction))
			return false;

		return true;
	}

	@Override
	public void setFaction(String value)
	{
		this.faction = WyHelper.getResourceName(value);
	}

	@Override
	public String getFaction()
	{
		return this.faction;
	}
	
	@Override
	public boolean isHuman()
	{
		if (WyHelper.isNullOrEmpty(this.race))
			return false;

		return this.race.equalsIgnoreCase(ModValues.HUMAN);
	}

	@Override
	public boolean isFishman()
	{
		if (WyHelper.isNullOrEmpty(this.race))
			return false;

		return this.race.equalsIgnoreCase(ModValues.FISHMAN);
	}

	@Override
	public boolean isCyborg()
	{
		if (WyHelper.isNullOrEmpty(this.race))
			return false;

		return this.race.equalsIgnoreCase(ModValues.CYBORG);
	}

	@Override
	public boolean hasRace()
	{
		if (WyHelper.isNullOrEmpty(this.race))
			return false;

		return true;
	}

	@Override
	public void setRace(String value)
	{
		this.race = WyHelper.getResourceName(value);
	}
	
	@Override
	public String getRace()
	{
		return this.race;
	}

	@Override
	public boolean isSwordsman()
	{
		if (WyHelper.isNullOrEmpty(this.fightingStyle))
			return false;

		return this.fightingStyle.equalsIgnoreCase(ModValues.SWORDSMAN);
	}

	@Override
	public boolean isSniper()
	{
		if (WyHelper.isNullOrEmpty(this.fightingStyle))
			return false;

		return this.fightingStyle.equalsIgnoreCase(ModValues.SNIPER);
	}

	@Override
	public boolean isDoctor()
	{
		if (WyHelper.isNullOrEmpty(this.fightingStyle))
			return false;

		return this.fightingStyle.equalsIgnoreCase(ModValues.DOCTOR);
	}
	
	@Override
	public boolean isWeatherWizard()
	{
		if (WyHelper.isNullOrEmpty(this.fightingStyle))
			return false;

		return this.fightingStyle.equalsIgnoreCase(ModValues.ART_OF_WEATHER);
	}
	
	@Override
	public boolean hasFightingStyle()
	{
		if (WyHelper.isNullOrEmpty(this.fightingStyle))
			return false;

		return true;
	}
	
	@Override
	public void setFightingStyle(String value)
	{
		this.fightingStyle = value;
	}
	
	@Override
	public String getFightingStyle()
	{
		return this.fightingStyle;
	}
	
	@Override
	public boolean hasShadow()
	{
		return this.hasShadow;
	}

	@Override
	public void setShadow(boolean value)
	{
		this.hasShadow = value;
	}

	@Override
	public boolean hasHeart()
	{
		return this.hasHeart;
	}

	@Override
	public void setHeart(boolean value)
	{
		this.hasHeart = value;
	}
	
	@Override
	public boolean isInCombatMode()
	{
		return this.inCombatMode;
	}

	@Override
	public void setCombatMode(boolean value)
	{
		this.inCombatMode = value;
	}


}
