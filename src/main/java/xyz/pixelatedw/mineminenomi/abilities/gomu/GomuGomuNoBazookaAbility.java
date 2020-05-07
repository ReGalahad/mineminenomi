package xyz.pixelatedw.mineminenomi.abilities.gomu;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.GomuHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.gomu.GomuGomuNoBazookaProjectile;
import xyz.pixelatedw.mineminenomi.entities.projectiles.gomu.GomuGomuNoGrizzlyMagnumProjectile;
import xyz.pixelatedw.mineminenomi.entities.projectiles.gomu.GomuGomuNoJetBazookaProjectile;
import xyz.pixelatedw.mineminenomi.entities.projectiles.gomu.GomuGomuNoLeoBazookaProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.ChargeableAbility;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

public class GomuGomuNoBazookaAbility extends ChargeableAbility
{
	public static final GomuGomuNoBazookaAbility INSTANCE = new GomuGomuNoBazookaAbility();

	public GomuGomuNoBazookaAbility()
	{
		super("Gomu Gomu no Bazooka", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(10);
		this.setMaxChargeTime(3);
		
		this.onEndChargingEvent = this::onEndChargingEvent;
	}
	
	private boolean onEndChargingEvent(PlayerEntity player)
	{
		IAbilityData props = AbilityDataCapability.get(player);
		AbilityProjectileEntity projectile = null;
		float speed = 2f;
		
		if(GomuHelper.hasGearSecondActive(props))
		{
			projectile = new GomuGomuNoJetBazookaProjectile(player.world, player);
			this.setMaxCooldown(8);
			speed = 2.8f;
		}
		else if(GomuHelper.hasGearThirdActive(props))
		{
			projectile = new GomuGomuNoGrizzlyMagnumProjectile(player.world, player);
			this.setMaxCooldown(15);
			speed = 1.8f;
		}
		else if(GomuHelper.hasGearFourthActive(props))
		{
			projectile = new GomuGomuNoLeoBazookaProjectile(player.world, player);
			this.setMaxCooldown(25);
			speed = 2.2f;
		}
		else
		{
			projectile = new GomuGomuNoBazookaProjectile(player.world, player);
			this.setMaxCooldown(10);
			speed = 2.0f;
		}
		
		player.world.addEntity(projectile);
		projectile.shoot(player, player.rotationPitch, player.rotationYaw, 0, speed, 0);
		
		return true;
	}
}
