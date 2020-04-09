package xyz.pixelatedw.mineminenomi.abilities.nikyu;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.ChargingUrsusShockEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.nikyu.UrsusShock25Projectile;
import xyz.pixelatedw.mineminenomi.entities.projectiles.nikyu.UrsusShock50Projectile;
import xyz.pixelatedw.mineminenomi.entities.projectiles.nikyu.UrsusShock75Projectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.ChargeableAbility;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class UrsusShockAbility extends ChargeableAbility
{
	public static final Ability INSTANCE = new UrsusShockAbility();

	private int power = 0;
	private ChargingUrsusShockEntity ursusShockEntity;
	
	public UrsusShockAbility()
	{
		super("Ursus Shock", AbilityCategory.DEVIL_FRUIT);
		this.setDescription("The user compresses air and sends it towards the opponent to create a massive explosion.");
		this.setMaxCooldown(30);
		this.setMaxChargeTime(15);
		this.setCancelable();
		
		this.duringChargingEvent = this::duringChargingEvent;
		this.onStartChargingEvent = this::onStartChargingEvent;
		this.onEndChargingEvent = this::onEndChargingEvent;
	}
	
	private void duringChargingEvent(PlayerEntity player, int chargeTimer)
	{
		if (this.ursusShockEntity == null)
		{
			this.stopCharging(player);
			return;
		}
				
		this.power = chargeTimer;
		double truePower = Math.abs(this.power - this.getMaxChargeTime());
		
		float currentCharge = this.ursusShockEntity.getCharge();
		if(truePower < 220)
		{
			currentCharge += 0.005;
			this.ursusShockEntity.setCharge(currentCharge);
		}
		else
		{
			currentCharge -= 0.03;
			this.ursusShockEntity.setCharge(currentCharge);
		}
	}
	
	private boolean onStartChargingEvent(PlayerEntity player)
	{
		ChargingUrsusShockEntity entity = new ChargingUrsusShockEntity(player.world);
		entity.setOwner(player);
		entity.setPositionAndRotation(player.posX, player.posY + 1.5, player.posZ, 0, 0);
		player.world.addEntity(entity);
		this.ursusShockEntity = entity;
		return true;
	}
	
	private boolean onEndChargingEvent(PlayerEntity player)
	{
		double truePower = Math.abs(this.power - this.getMaxChargeTime());
		AbilityProjectileEntity projectile = null;
		
		if (truePower > 0 && truePower <= 70)
		{
			projectile = new UrsusShock25Projectile(player.world, player);
			this.setMaxCooldown(10);
		}
		else if (truePower > 70 && truePower <= 250)
		{
			projectile = new UrsusShock50Projectile(player.world, player);
			this.setMaxCooldown(20);
		}
		else if (truePower > 250 && truePower <= 300)
		{
			projectile = new UrsusShock75Projectile(player.world, player);
			this.setMaxCooldown(30);
		}
		
		player.world.addEntity(projectile);
		projectile.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);
		
		if(this.ursusShockEntity != null)
			this.ursusShockEntity.remove();
		
		return true;
	}
}