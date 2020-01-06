package xyz.pixelatedw.mineminenomi.api.abilities;

import java.io.Serializable;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.extra.AbilityExplosion;
import xyz.pixelatedw.mineminenomi.api.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.api.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.api.network.packets.server.SAbilityDataSyncPacket;
import xyz.pixelatedw.mineminenomi.api.telemetry.WyTelemetry;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;

public class Ability implements Serializable
{
	protected State state = State.STANDBY;
	protected AbilityProjectile projectile;
	protected String originalDisplayName = "n/a";
	protected AbilityAttribute attr;
	protected boolean isOnCooldown = false, isCharging = false, isRepeating = false, passiveActive = false, isDisabled = false;
	private int cooldownTicks, maxCooldownTicks, ticksForCharge, ticksForRepeater, ticksForRepeaterFreq, currentSpawn = 0;
	protected IOnUse onUseEvent = (player, ability) -> {};
	protected IDuringCooldown duringCooldownEvent = (player, ability, cooldown) -> {};

	public Ability(AbilityAttribute attr)
	{
		this.attr = new AbilityAttribute(attr);
		this.cooldownTicks = this.attr.getAbilityCooldown();
		this.maxCooldownTicks = this.attr.getAbilityCooldown();
		this.ticksForCharge = this.attr.getAbilityCharges();
		this.ticksForRepeater = this.attr.getAbilityCooldown();
		this.ticksForRepeaterFreq = this.attr.getAbilityRepeaterFrequency();
		this.originalDisplayName = this.attr.getAbilityDisplayName();
	}

	public AbilityAttribute getAttribute() { return attr; }
	
	public void use(PlayerEntity player)
	{
		if(player.world.isRemote)
			return;
		
		Ability abl = this.getOriginalAbility(player);
		
		if(abl == null || abl.getState() != Ability.State.STANDBY)
			return;
		
		this.onUseEvent.onUse(player, abl);
		
		if(this.projectile != null)
		{
			if(this.attr.isRepeater())
				this.startRepeater(player);
			else
			{
				player.world.addEntity(this.projectile);
				this.projectile.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);
			}
		}
		
		abl.startCooldown();
		
		/*if(!this.isOnCooldown)
		{

			
			if(this.attr.getPotionEffectsForUser() != null)
				for(EffectInstance p : this.attr.getPotionEffectsForUser())				
					player.addPotionEffect(new EffectInstance(p));

			if(this.attr.getPotionEffectsForAoE() != null) 
				for(EffectInstance p : this.attr.getPotionEffectsForAoE())
					for(LivingEntity l : WyHelper.getEntitiesNear(player, this.attr.getEffectRadius())) 
						l.addPotionEffect(new EffectInstance(p));

			if(!(this.attr.getAbilityCharges() > 0) && this.attr.getAbilityExplosionPower() > 0)
			{
				AbilityExplosion explosion = WyHelper.newExplosion(player, player.posX, player.posY, player.posZ, this.attr.getAbilityExplosionPower());
				explosion.setDamageOwner(false);
				explosion.setFireAfterExplosion(this.attr.canAbilityExplosionSetFire());
				explosion.setDestroyBlocks(this.attr.canAbilityExplosionDestroyBlocks());
				explosion.setSmokeParticles(new CommonExplosionParticleEffect(this.attr.getAbilityExplosionPower()));
				explosion.doExplosion();
			}
			
	    	if(!player.abilities.isCreativeMode)
	    		WyTelemetry.addAbilityStat(this.getAttribute().getAbilityTexture(), this.getAttribute().getAttributeName(), 1);

	    	IAbilityData abilityProps = AbilityDataCapability.get(player);
	    	abilityProps.setPreviouslyUsedAbility(this);

	    	if(!this.attr.isPassive())
	    		this.sendShounenScream(player);
				
	    	this.duringRepeater(player);
	    	this.startCooldown();
			ModNetwork.sendToAllAround(new SAbilityDataSyncPacket(player.getEntityId(), abilityProps), (ServerPlayerEntity) player);
			//this.cooldownThread = new Update(player, attr);
			//this.cooldownThread.start();
		}*/
	}
	
	public void tick(PlayerEntity player) {}
	
	public void duringRepeater(PlayerEntity player)
	{
		if(this.isRepeating)
		{
			try 
			{
				if(!player.world.isRemote && this.currentSpawn % this.ticksForRepeaterFreq == 0)
				{
					player.world.addEntity(this.projectile.getClass().getDeclaredConstructor(World.class, LivingEntity.class, AbilityAttribute.class).newInstance(player.world, player, attr));
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			this.currentSpawn++;
		}
	}
	
	protected void startRepeater(PlayerEntity player)
	{
		this.isRepeating = true;
	}
	
	public boolean isRepeating()
	{
		return this.isRepeating;
	}
	
	public void passive(PlayerEntity player)
	{
		if(!this.isOnCooldown)
		{
			if(this.passiveActive)
			{
				this.passiveActive = false;
				ModNetwork.sendTo(new SAbilityDataSyncPacket(player.getEntityId(), AbilityDataCapability.get(player)), (ServerPlayerEntity) player);
				if(this.attr.getPotionEffectsForUser() != null)
					for(EffectInstance p : this.attr.getPotionEffectsForUser())	
						player.removePotionEffect(p.getPotion());
				
				this.endPassive(player);
			}
			else
			{
				this.passiveActive = true;
				ModNetwork.sendTo(new SAbilityDataSyncPacket(player.getEntityId(), AbilityDataCapability.get(player)), (ServerPlayerEntity) player);
				if(this.attr.getPotionEffectsForUser() != null)
					for(EffectInstance p : this.attr.getPotionEffectsForUser())				
						player.addPotionEffect(new EffectInstance(p.getPotion(), Integer.MAX_VALUE, p.getAmplifier(), true, false));
				
				this.sendShounenScream(player);
				
				this.startPassive(player);
				//(new Update(player, this.attr)).start();
			}			
		}
	}
	
	public boolean isDisabled()
	{
		return isDisabled;
	}
	
	public void disable(PlayerEntity player, boolean bool) 
	{
		//if(bool)
		//	(new ResetDisable(player, attr)).start();
		isDisabled = bool;
		ModNetwork.sendTo(new SAbilityDataSyncPacket(player.getEntityId(), AbilityDataCapability.get(player)), (ServerPlayerEntity) player);
	}
	
	/** Only use super. if the ability is also using passive potion effects, otherwise there's really no plus */
	public void endPassive(PlayerEntity player)
	{
		if(this.attr.getPotionEffectsForUser() != null)
			for(EffectInstance p : this.attr.getPotionEffectsForUser())	
				player.removePotionEffect(p.getPotion());
	}
	
	public void startPassive(PlayerEntity player) {}
		
	public void duringPassive(PlayerEntity player, int passiveTimer) {}
		
	public boolean isPassiveActive()
	{
		return this.passiveActive;
	}

	public void setPassiveActive(boolean b)
	{
		this.passiveActive = b;
	}
	
	public void setChargeActive(boolean b)
	{
		this.isCharging = b;
	}
	
	public void setCooldownActive(boolean b)
	{
		this.isOnCooldown = b;
	}
	
	
	public void duringCharging(PlayerEntity player, int currentCharge) {}
	
	public void startCharging(PlayerEntity player)
	{
		if(!isOnCooldown)
		{
			this.sendShounenScream(player, 1);
			
			isCharging = true;
			ModNetwork.sendTo(new SAbilityDataSyncPacket(player.getEntityId(), AbilityDataCapability.get(player)), (ServerPlayerEntity) player);
			//(new Update(player, attr)).start();
		}
	}
	
	public void endCharging(PlayerEntity player)
	{
		isCharging = false;
		isOnCooldown = true;
		if(player instanceof ServerPlayerEntity)
			ModNetwork.sendTo(new SAbilityDataSyncPacket(player.getEntityId(), AbilityDataCapability.get(player)), (ServerPlayerEntity) player);
		
		if(projectile != null)
		{
			if(this.attr.isRepeater())
				startRepeater(player);
			else
				player.world.addEntity(projectile);
		}
		
		this.sendShounenScream(player, 2);
		
		if(this.attr.getAbilityExplosionPower() > 0)
		{
			AbilityExplosion explosion = WyHelper.newExplosion(player, player.posX, player.posY, player.posZ, this.attr.getAbilityExplosionPower());
			explosion.setDamageOwner(false);
			explosion.setFireAfterExplosion(this.attr.canAbilityExplosionSetFire());
			explosion.setDestroyBlocks(this.attr.canAbilityExplosionDestroyBlocks());
			explosion.setSmokeParticles(new CommonExplosionParticleEffect(this.attr.getAbilityExplosionPower()));
			explosion.doExplosion();
		}
				
    	if(!player.abilities.isCreativeMode)
    		WyTelemetry.addAbilityStat(this.getAttribute().getAbilityTexture(), this.getAttribute().getAttributeName(), 1);

		//(new Update(player, attr)).start();
	}
	
	public boolean isCharging()
	{
		return isCharging;
	}
	
	public boolean isOnCooldown()
	{
		return isOnCooldown;
	}
	
	public void duringCooldown(PlayerEntity player, int currentCooldown) {}
	
	public void hitEntity(PlayerEntity player, LivingEntity target) 
	{
		if(this.attr.getPotionEffectsForHit() != null)
			for(EffectInstance p : this.attr.getPotionEffectsForHit())				
				target.addPotionEffect(new EffectInstance(p.getPotion(), p.getDuration(), p.getAmplifier(), true, false)); 

		if(this.attr.getAbilityExplosionPower() > 0)
		{
			AbilityExplosion explosion = WyHelper.newExplosion(player, player.posX, player.posY, player.posZ, this.attr.getAbilityExplosionPower());
			explosion.setDamageOwner(false);
			explosion.setFireAfterExplosion(this.attr.canAbilityExplosionSetFire());
			explosion.setDestroyBlocks(this.attr.canAbilityExplosionDestroyBlocks());
			explosion.setSmokeParticles(new CommonExplosionParticleEffect(this.attr.getAbilityExplosionPower()));
			explosion.doExplosion();
		}
		
		this.passiveActive = false;
		startCooldown();
		ModNetwork.sendTo(new SAbilityDataSyncPacket(player.getEntityId(), AbilityDataCapability.get(player)), (ServerPlayerEntity) player);

		target.attackEntityFrom(DamageSource.causePlayerDamage(player), this.attr.getPunchDamage());
		
		//(new Update(player, this.attr)).start();
	}
	
	protected void startCooldown()
	{
		isOnCooldown = true;
	}
	
	protected void startExtUpdate(PlayerEntity player)
	{
		ModNetwork.sendTo(new SAbilityDataSyncPacket(player.getEntityId(), AbilityDataCapability.get(player)), (ServerPlayerEntity) player);
		//(new Update(player, attr)).start();
	}
	
	public void startUpdate(PlayerEntity player)
	{
		this.setCooldownActive(true);
		if(player instanceof ServerPlayerEntity)
			ModNetwork.sendTo(new SAbilityDataSyncPacket(player.getEntityId(), AbilityDataCapability.get(player)), (ServerPlayerEntity) player);
		//(new Update(player, attr)).start();
	}
	
	protected void sendShounenScream(PlayerEntity player)
	{
		this.sendShounenScream(player, 0);
	}
	
	protected void sendShounenScream(PlayerEntity player, int part)
	{
		//if(CommonConfig.instance.getAnimeScreaming())
    	//	ModNetwork.sendToAllAround(new PacketShounenScream(player.getCommandSource().getDisplayName().toString(), this.attr.getAbilityDisplayName(), part), player.dimension, player.posX, player.posY, player.posZ, 15);
	}
	
	
	
	public State getState()
	{
		return this.state;
	}
	
	public void cooldown(PlayerEntity player)
	{
		if(player.world.isRemote)
			return;
		
		if(this.state == State.COOLDOWN && this.cooldownTicks > 0)
		{
			this.cooldownTicks--;
			this.duringCooldownEvent.duringCooldown(player, this.getOriginalAbility(player), this.cooldownTicks);
		}
		else
		{
			this.cooldownTicks = this.maxCooldownTicks;
			this.state = State.STANDBY;
		}
	}
	
	private Ability getOriginalAbility(PlayerEntity player)
	{
		IAbilityData props = AbilityDataCapability.get(player);
		return props.getPlayerAbilities().parallelStream().filter(ability -> ability.getAttribute().getAttributeName().equalsIgnoreCase(this.getAttribute().getAttributeName())).findFirst().orElse(null);
	}
	
	public enum State
	{
		STANDBY,
		COOLDOWN
	}
	
	public interface IOnUse extends Serializable
	{
		void onUse(PlayerEntity player, Ability ability);
	}
	
	public interface IDuringCooldown extends Serializable
	{
		void duringCooldown(PlayerEntity player, Ability ability, int cooldown);
	}
	
/*	
	class ResetDisable extends Thread
	{
		private PlayerEntity player;
		private AbilityAttribute attr;
		
		public ResetDisable(PlayerEntity user, AbilityAttribute attribute)
		{
			this.player = user;
			this.attr = attribute;
			this.setName("ResetThread Thread for " + attr.getAttributeName());
		}
		
		@Override
		public void run()
		{
			while(isDisabled)
			{
				if( !DevilFruitsHelper.isNearbyKairoseki(player)  )
				{
			    	disable(player, false);
					setCooldownActive(false);
					try
					{
						return;
					} 
					catch (Exception e) 
					{
						e.printStackTrace();
					}
				}
				try 
				{
					Thread.sleep(24);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		}
		
		private boolean abilityCounterpart(String ablNameForCheck)
		{
			return WyHelper.getFancyName(this.attr.getAttributeName()).equals(WyHelper.getFancyName(ablNameForCheck));
		}
	}
	
	
	class Update extends Thread
	{
		private PlayerEntity player;
		private AbilityAttribute attr;
		
		public Update(PlayerEntity user, AbilityAttribute attribute)
		{
			this.player = user;
			this.attr = attribute;
			this.setName("Update Thread for " + attr.getAttributeName());
			ticksForCooldown = this.attr.getAbilityCooldown();
			ticksForCharge = this.attr.getAbilityCharges();
		}
		
		@Override
		public void run()
		{
			if(passiveActive)
			{
				int passiveTimer = 0;
				while(passiveActive)
				{					
					try 
					{
						duringPassive(player, passiveTimer++);
						Thread.sleep(20);
					} 
					catch (InterruptedException e) 
					{
						e.printStackTrace();
					}
				}
			}
			
			if(isOnCooldown)
			{
				while(isOnCooldown)
				{
					if(ticksForCooldown > 0)
					{
						ticksForCooldown--;
						if(isRepeating)
						{
							ticksForRepeater--;
							if(ticksForRepeater > this.attr.getAbilityCooldown() - (this.attr.getAbilityCooldown() / this.attr.getAbilityRepeaterTime()) && projectile != null) {}
							else
							{
								isRepeating = false;
								ticksForRepeater = attr.getAbilityCooldown();
							}
						}
						duringCooldown(player, ticksForCooldown);
						try 
						{
							Thread.sleep(20);
						} 
						catch (InterruptedException e) 
						{
							e.printStackTrace();
						}
					}
					else
					{
						ticksForCooldown = this.attr.getAbilityCooldown();
						currentSpawn = 0;
						isOnCooldown = false;
						if(player instanceof ServerPlayerEntity)
							ModNetwork.sendTo(new SAbilityDataSyncPacket(player.getEntityId(), AbilityDataCapability.get(player)), (ServerPlayerEntity) player);
						return;
					}
				}	
			}
			else if(isCharging)
			{
				while(isCharging)
				{
					if(ticksForCharge > 0)
					{
						ticksForCharge--;	
						duringCharging(player, ticksForCharge);
						try 
						{
							Thread.sleep(20);
						} 
						catch (InterruptedException e) 
						{
							e.printStackTrace();
						}
					}
					else
					{
						ticksForCharge = this.attr.getAbilityCharges();
						endCharging(player);
					}
				}
			}
		}
	}*/
}
