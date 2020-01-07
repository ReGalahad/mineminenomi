package xyz.pixelatedw.mineminenomi.api.abilities;

import java.io.Serializable;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.api.data.ability.IAbilityData;

public abstract class Ability implements Serializable
{
	private int cooldown;
	private int maxCooldown;
	
	protected State state = State.STANDBY;
	protected Category category = Category.DEVIL_FRUIT;
	
	// Setting the defaults so that no crash occurs and so they will be null safe.
	protected IOnUse onUseEvent = (player, ability) -> {};
	protected IDuringCooldown duringCooldownEvent = (player, ability, cooldown) -> {};
		
	public abstract String getName();
	public abstract String getDescription();
	
	public void use(PlayerEntity player)
	{
		if(player.world.isRemote)
			return;
		
		Ability abl = this.getOriginalAbility(player);
		
		if(abl == null || abl.getState() != Ability.State.STANDBY)
			return;
		
		this.onUseEvent.onUse(player, abl);
		
		abl.startCooldown();
	}
	
	public void setMaxCooldown(int cooldown)
	{
		this.maxCooldown = cooldown;
	}
	
	public void setCooldown(int cooldown)
	{
		this.cooldown = cooldown;
	}
	
	public State getState()
	{
		return this.state;
	}
	
	public Category getCategory()
	{
		return this.category;
	}
	
	public void startCooldown()
	{
		this.state = State.COOLDOWN;
	}
	
	public void cooldown(PlayerEntity player)
	{
		if(player.world.isRemote)
			return;
		
		if(this.state == State.COOLDOWN && this.cooldown > 0)
		{
			this.cooldown--;
			this.duringCooldownEvent.duringCooldown(player, this.getOriginalAbility(player), this.cooldown);
		}
		else
		{
			this.cooldown = this.maxCooldown;
			this.state = State.STANDBY;
		}
	}
	
	private Ability getOriginalAbility(PlayerEntity player)
	{
		IAbilityData props = AbilityDataCapability.get(player);
		return props.getAbilities(Category.ALL).parallelStream().filter(ability -> ability.getName().equalsIgnoreCase(this.getName())).findFirst().orElse(null);
	}
	
	@Override
	public boolean equals(Object abl)
	{
		if(!(abl instanceof Ability))
			return false;
		
		return this.getName().equalsIgnoreCase(((Ability) abl).getName());
	}
	
	public enum State
	{
		STANDBY,
		COOLDOWN
	}
	
	public enum Category
	{
		ALL,
		
		DEVIL_FRUIT,
		RACIAL,
		HAKI,
	}
	
	public interface IOnUse extends Serializable
	{
		void onUse(PlayerEntity player, Ability ability);
	}
	
	public interface IDuringCooldown extends Serializable
	{
		void duringCooldown(PlayerEntity player, Ability ability, int cooldown);
	}
}
