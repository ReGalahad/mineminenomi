package xyz.pixelatedw.mineminenomi.api.abilities;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import xyz.pixelatedw.mineminenomi.api.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.api.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.packets.server.SUpdateHotbarStatePacket;

public class RepeaterAbility extends Ability
{
	private int repeaterCount;
	private int maxRepeaterCount;
	private int repeaterInterval;
	
	public RepeaterAbility(String name, Category category)
	{
		super(name, category);
	}
	
	
	/*
	 * 	Setters/Getters
	 */	
	public void setMaxRepearCount(int count, int interval)
	{
		this.maxRepeaterCount = count;
		this.repeaterCount = this.maxRepeaterCount;
		this.repeaterInterval = interval;
		
		this.maxCooldown += (this.maxRepeaterCount * this.repeaterInterval);
		this.cooldown = this.maxCooldown;	
	}
		
	
	
	/*
	 * 	Methods
	 */
	@Override
	public void cooldown(PlayerEntity player)
	{
		if(player.world.isRemote)
			return;
		
		if(this.isOnCooldown() && this.cooldown > 0)
		{
			this.cooldown--;

			if(this.repeaterCount > 0 && this.cooldown % this.repeaterInterval == 0)
			{
				this.onUseEvent.onUse(player, this);
				this.repeaterCount--;
			}

			this.duringCooldownEvent.duringCooldown(player, this, this.cooldown);
		}
		else if(this.isOnCooldown() && this.cooldown <= 0)
		{
			this.cooldown = this.maxCooldown;				
			this.repeaterCount = this.maxRepeaterCount;
			this.startStandby();
			IAbilityData props = AbilityDataCapability.get(player);
			ModNetwork.sendTo(new SUpdateHotbarStatePacket(props, props.getAbilityPosition(this.getSavedAbility(player))), (ServerPlayerEntity)player);
		}
	}
}
