package xyz.pixelatedw.mineminenomi.api.abilities;

import java.io.Serializable;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import xyz.pixelatedw.mineminenomi.api.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.api.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.packets.server.SUpdateHotbarStatePacket;

public class PassiveAbility extends Ability
{
	private int threshold = 0;
	protected int passiveTime = 0;
	
	// Setting the defaults so that no crash occurs and so they will be null safe.
	protected IOnStartPassive onStartPassiveEvent = (player) -> { return true; };
	protected IOnEndPassive onEndPassiveEvent = (player) -> { return true; };
	protected IDuringActivePassive duringPassiveEvent = (player, passiveTime) -> {};
	
	public PassiveAbility(String name, Category category)
	{
		super(name, category);
	}

	
	/*
	 *  Event Starters
	 */
	@Override
	public void use(PlayerEntity player)
	{
		if(player.world.isRemote)
			return;	
		
		if(!this.isPassiveActive())
		{
			if(this.onStartPassiveEvent.onStartPassive(player))
			{
				this.startPassive();
				IAbilityData props = AbilityDataCapability.get(player);
				ModNetwork.sendTo(new SUpdateHotbarStatePacket(props, props.getAbilityPosition(this)), (ServerPlayerEntity)player);
			}
		}
		else
		{
			if(this.onEndPassiveEvent.onEndPassive(player))
			{
				this.passiveTime = 0;
				this.startCooldown();
				IAbilityData props = AbilityDataCapability.get(player);
				ModNetwork.sendTo(new SUpdateHotbarStatePacket(props, props.getAbilityPosition(this)), (ServerPlayerEntity)player);				
			}
		}
	}
	
	
	
	/*
	 * 	Setters/Getters
	 */	
	public void setThreshold(int threshold)
	{
		this.threshold = threshold;
	}
	
	public void startPassive()
	{
		this.setState(State.PASSIVE);
	}

	
	
	/*
	 * 	Methods
	 */
	public void passive(PlayerEntity player)
	{
		if(player.world.isRemote)
			return;
		
		if(this.isPassiveActive())
		{
			this.passiveTime++;		
			
			this.duringPassiveEvent.duringActivePassive(player, this.passiveTime);
			
			if(this.threshold > 0 && this.passiveTime >= this.threshold)
			{
				this.passiveTime = 0;
				this.startCooldown();
				this.onEndPassiveEvent.onEndPassive(player);
				IAbilityData props = AbilityDataCapability.get(player);
				ModNetwork.sendTo(new SUpdateHotbarStatePacket(props, props.getAbilityPosition(this)), (ServerPlayerEntity)player);
			}
		}
	}
	
	
	/*
	 *	Interfaces
	 */
	public interface IDuringActivePassive extends Serializable
	{
		void duringActivePassive(PlayerEntity player, int passiveTime);
	}
	
	public interface IOnStartPassive extends Serializable
	{
		boolean onStartPassive(PlayerEntity player);
	}
	
	public interface IOnEndPassive extends Serializable
	{
		boolean onEndPassive(PlayerEntity player);
	}
}
