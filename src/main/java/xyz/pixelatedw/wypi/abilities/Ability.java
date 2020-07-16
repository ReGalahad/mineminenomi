package xyz.pixelatedw.wypi.abilities;

import java.io.Serializable;

import javax.annotation.Nullable;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.registries.ForgeRegistryEntry;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.events.AbilityEvent;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.wypi.network.WyNetwork;
import xyz.pixelatedw.wypi.network.packets.server.SSyncAbilityDataPacket;

public abstract class Ability extends ForgeRegistryEntry<Ability>
{
	private String name = "";
	private String desc = "";
	protected double cooldown;
	protected double maxCooldown;
	private AbilityCategory category = AbilityCategory.ALL;
	private State state = State.STANDBY;
	private State previousState = State.STANDBY;
	
	// Setting the defaults so that no crash occurs and so they will be null safe.
	protected IOnUse onUseEvent = (player) -> { return true; };
	protected IDuringCooldown duringCooldownEvent = (player, cooldown) -> {};
	protected IOnEndCooldown onEndCooldownEvent = (player) -> {};
	
	public Ability(String name, AbilityCategory category)
	{
		this.name = name;
		this.category = category;
	}
	
	/*
	 * 	Event Starters
	 */
	public void use(PlayerEntity player)
	{
		if(player.world.isRemote)
			return;

		if(this.isOnCooldown() && this.getCooldown() <= 10)
			this.stopCooldown(player);
		
		if(!this.isOnStandby())
			return;
		
		AbilityEvent.Use event = new AbilityEvent.Use(player, this);
		if (MinecraftForge.EVENT_BUS.post(event))
			return;
		
		if(this.onUseEvent.onUse(player))
		{
			this.startCooldown(player);
			IAbilityData props = AbilityDataCapability.get(player);
			props.setPreviouslyUsedAbility(this);
			WyNetwork.sendTo(new SSyncAbilityDataPacket(player.getEntityId(), props), (ServerPlayerEntity)player);
		}
	}
	
	
	/*
	 * 	Setters/Getters
	 */
	public boolean isOnStandby()
	{
		return this.state == State.STANDBY;
	}
	
	public boolean isOnCooldown()
	{
		return this.state == State.COOLDOWN;
	}

	public boolean isPassiveEnabled()
	{
		return this.state == State.PASSIVE;
	}
	
	public boolean isContinuous()
	{
		return this.state == State.CONTINUOUS;
	}
	
	public boolean isCharging()
	{
		return this.state == State.CHARGING;
	}

	public boolean isDisabled()
	{
		return this.state == State.DISABLED;
	}
	
	public void startStandby()
	{
		this.previousState = this.state;
		this.state = State.STANDBY;
	}
	
	public void startDisable()
	{
		this.previousState = this.state;
		this.state = State.DISABLED;
	}
	
	public void startCooldown(PlayerEntity player)
	{
		this.previousState = this.state;
		this.state = State.COOLDOWN;
	}	
	
	public void stopCooldown(PlayerEntity player)
	{
		if(player.world.isRemote)
			return;
		this.cooldown = this.maxCooldown;				
		this.previousState = this.state;
		this.state = State.STANDBY;
		this.onEndCooldownEvent.onEndCooldown(player);
		IAbilityData props = AbilityDataCapability.get(player);
		WyNetwork.sendTo(new SSyncAbilityDataPacket(player.getEntityId(), props), player);
	}
	
	public void setState(State state)
	{
		this.previousState = this.state;
		this.state = state;
	}
	
	public State getState()
	{
		return this.state;
	}
	
	public State getPreviousState()
	{
		return this.previousState;
	}
	
	/**
	 * 
	 * @param cooldown - seconds before the abiltiy can be used again
	 */
	public void setMaxCooldown(double cooldown)
	{
		this.maxCooldown = cooldown * 20;
		this.cooldown = this.maxCooldown;
	}
	
	public double getMaxCooldown()
	{
		return this.maxCooldown;
	}
	
	public void setCooldown(int cooldown)
	{
		this.cooldown = cooldown * 20;
	}
	
	public double getCooldown()
	{
		return this.cooldown;
	}
	
	public void setDescription(String desc)
	{
		this.desc = desc;
	}
	
	public String getDescription()
	{
		return this.desc;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return this.name;
	}

	public AbilityCategory getCategory()
	{
		return this.category;
	}
	
	
	/*
	 * 	Methods
	 */
	public void cooldown(PlayerEntity player)
	{
		//if(player.world.isRemote)
		//	return;
				
		if(this.isOnCooldown() && this.cooldown > 0)
		{
			this.cooldown--;
			if(!player.world.isRemote && this.getPreviousState() != State.DISABLED)
				this.duringCooldownEvent.duringCooldown(player, (int) this.cooldown);
		}
		else if(this.isOnCooldown() && this.cooldown <= 0)
		{
			this.stopCooldown(player);
		}
	}
	
	private Ability getOriginalAbility(PlayerEntity player)
	{
		IAbilityData props = AbilityDataCapability.get(player);
		return props.getUnlockedAbilities(AbilityCategory.ALL).stream().filter(ability -> ability.getName().equalsIgnoreCase(this.getName())).findFirst().orElse(null);
	}
	
	protected Ability getSavedAbility(PlayerEntity player)
	{
		IAbilityData props = AbilityDataCapability.get(player);
		Ability abl = props.getUnlockedAbility(this.getOriginalAbility(player));
		return abl;
	}
	
	@Override
	public boolean equals(Object abl)
	{
		if(!(abl instanceof Ability))
			return false;
		
		return this.getName().equalsIgnoreCase(((Ability) abl).getName());
	}

	@Nullable
	public Ability create()
	{
		try
		{
			return this.getClass().getConstructor().newInstance();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	}

	/*
	 *	Enums
	 */
	public enum State
	{
		STANDBY,
		DISABLED,
		
		COOLDOWN,
		PASSIVE,
		CONTINUOUS,
		CHARGING,
	}
	
	
	/*
	 *	Interfaces
	 */
	public interface IOnUse extends Serializable
	{
		boolean onUse(PlayerEntity player);
	}

	public interface IDuringCooldown extends Serializable
	{
		void duringCooldown(PlayerEntity player, int cooldown);
	}
	
	public interface IOnEndCooldown extends Serializable
	{
		void onEndCooldown(PlayerEntity player);
	}
}
