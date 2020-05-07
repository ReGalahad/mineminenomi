package xyz.pixelatedw.mineminenomi.abilities.gomu;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.GomuHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.gomu.GomuGomuNoElephantGunProjectile;
import xyz.pixelatedw.mineminenomi.entities.projectiles.gomu.GomuGomuNoJetPistolProjectile;
import xyz.pixelatedw.mineminenomi.entities.projectiles.gomu.GomuGomuNoKongGunProjectile;
import xyz.pixelatedw.mineminenomi.entities.projectiles.gomu.GomuGomuNoPistolProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.RepeaterAbility;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

public class GomuGomuNoGatlingAbility extends RepeaterAbility
{
	public static final GomuGomuNoGatlingAbility INSTANCE = new GomuGomuNoGatlingAbility();

	private boolean hasDataSet = false;
	
	public GomuGomuNoGatlingAbility()
	{
		super("Gomu Gomu no Gatling", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(12);
		this.setMaxRepearCount(5, 2);

		this.onUseEvent = this::onUseEvent;
		this.duringCooldownEvent = this::duringCooldownEvent;
	}
	
	private boolean onUseEvent(PlayerEntity player)
	{
		IAbilityData props = AbilityDataCapability.get(player);
		AbilityProjectileEntity projectile = null;
		float speed = 2.5f;
		int projectileSpace = 3;

		if (GomuHelper.hasGearSecondActive(props))
		{
			projectile = new GomuGomuNoJetPistolProjectile(player.world, player);
			if(!this.hasDataSet)
			{
				this.setMaxCooldown(10);
				this.setMaxRepearCount(10, 3);
				speed = 3.8f;
				this.hasDataSet = true;
			}
		}
		else if (GomuHelper.hasGearThirdActive(props))
		{
			projectile = new GomuGomuNoElephantGunProjectile(player.world, player);
			if(!this.hasDataSet)
			{
				this.setMaxCooldown(25);
				this.setMaxRepearCount(3, 5);
				speed = 2.3f;
				projectileSpace = 7;
				this.hasDataSet = true;
			}
		}
		else if (GomuHelper.hasGearFourthActive(props))
		{
			projectile = new GomuGomuNoKongGunProjectile(player.world, player);
			if(!this.hasDataSet)
			{
				this.setMaxCooldown(30);
				this.setMaxRepearCount(2, 3);
				speed = 2.3f;
				projectileSpace = 7;
				this.hasDataSet = true;
			}
		}
		else
		{
			projectile = new GomuGomuNoPistolProjectile(player.world, player);
			if(!this.hasDataSet)
			{
				this.setMaxCooldown(12);
				this.setMaxRepearCount(5, 2);
				speed = 2.8f;
				this.hasDataSet = true;
			}
		}

		for(int j = 0; j < 5; j++)
		{
			projectile.setLocationAndAngles(
				player.posX + WyHelper.randomWithRange(-projectileSpace, projectileSpace) + WyHelper.randomDouble(), 
				(player.posY + 0.3) + WyHelper.randomWithRange(0, projectileSpace) + WyHelper.randomDouble(), 
				player.posZ + WyHelper.randomWithRange(-projectileSpace, projectileSpace) + WyHelper.randomDouble(),
				0, 0);
			player.world.addEntity(projectile);
			projectile.shoot(player, player.rotationPitch, player.rotationYaw, 0, speed, 3);
		}

		return true;
	}
	
	private void duringCooldownEvent(PlayerEntity player, int cooldown)
	{
		if(cooldown <= 1)
		{
			this.hasDataSet = false;
		}
	}
}
