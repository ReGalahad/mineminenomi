package xyz.pixelatedw.wypi.abilities;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.wypi.network.WyNetwork;
import xyz.pixelatedw.wypi.network.packets.server.SSyncAbilityDataPacket;

public abstract class RepeaterAbility extends Ability
{
	private int repeaterCount;
	private int maxRepeaterCount;
	private int repeaterInterval;
	
	public RepeaterAbility(String name, AbilityCategory category)
	{
		super(name, category);
	}
	
	
	/*
	 * 	Setters/Getters
	 */	
	public void setMaxRepearCount(int count, int interval)
	{
		this.maxRepeaterCount = count - 1;
		this.repeaterCount = this.maxRepeaterCount;
		this.repeaterInterval = interval;
		
		this.maxCooldown += (this.maxRepeaterCount * this.repeaterInterval);
		this.cooldown = this.maxCooldown;	
	}
	
	public void setRepeaterCount(int count)
	{
		this.repeaterCount = count;
	}
	
	
	/*
	 * 	Methods
	 */
	
	@Override
	public void stopCooldown(PlayerEntity player)
	{
		if(player.world.isRemote)
			return;
		this.cooldown = this.maxCooldown;		
		this.repeaterCount = this.maxRepeaterCount;
		this.setState(State.STANDBY);
		this.onEndCooldownEvent.onEndCooldown(player);
		IAbilityData props = AbilityDataCapability.get(player);
		WyNetwork.sendTo(new SSyncAbilityDataPacket(player.getEntityId(), props), player);
	}
	
	@Override
	public void cooldown(PlayerEntity player)
	{
		//if(player.world.isRemote)
		//	return;
		
		if(this.isOnCooldown() && this.cooldown > 0)
		{
			this.cooldown--;
			
			if(!player.world.isRemote)
			{
				if(this.repeaterCount > 0 && (this.cooldown - 10) % this.repeaterInterval == 0)
				{
					this.onUseEvent.onUse(player);
					this.repeaterCount--;
				}
				
				this.duringCooldownEvent.duringCooldown(player, (int) this.cooldown);			
			}
		}
		else if(this.isOnCooldown() && this.cooldown <= 0)
		{
			if(player.world.isRemote)
				return;
			this.cooldown = this.maxCooldown;
			this.repeaterCount = this.maxRepeaterCount;
			this.startStandby();
			IAbilityData props = AbilityDataCapability.get(player);
			WyNetwork.sendTo(new SSyncAbilityDataPacket(player.getEntityId(), props), (ServerPlayerEntity)player);
		}
	}
}
