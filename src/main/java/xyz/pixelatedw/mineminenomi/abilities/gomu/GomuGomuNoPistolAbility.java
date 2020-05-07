package xyz.pixelatedw.mineminenomi.abilities.gomu;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.GomuHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.gomu.GomuGomuNoElephantGunProjectile;
import xyz.pixelatedw.mineminenomi.entities.projectiles.gomu.GomuGomuNoJetPistolProjectile;
import xyz.pixelatedw.mineminenomi.entities.projectiles.gomu.GomuGomuNoKongGunProjectile;
import xyz.pixelatedw.mineminenomi.entities.projectiles.gomu.GomuGomuNoPistolProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

public class GomuGomuNoPistolAbility extends Ability
{
	public static final GomuGomuNoPistolAbility INSTANCE = new GomuGomuNoPistolAbility();

	public GomuGomuNoPistolAbility()
	{
		super("Gomu Gomu no Pistol", AbilityCategory.DEVIL_FRUIT);
		this.setDescription("The user stretches their arm to hit the opponent.");
		this.setMaxCooldown(8);

		this.onUseEvent = this::onUseEvent;
	}
	
	private boolean onUseEvent(PlayerEntity player)
	{
		IAbilityData props = AbilityDataCapability.get(player);
		AbilityProjectileEntity projectile = null;
		float speed = 2.5f;
		
		if(GomuHelper.hasGearSecondActive(props))
		{
			projectile = new GomuGomuNoJetPistolProjectile(player.world, player);
			this.setMaxCooldown(5);
			speed = 3.5f;
		}
		else if(GomuHelper.hasGearThirdActive(props))
		{
			projectile = new GomuGomuNoElephantGunProjectile(player.world, player);
			this.setMaxCooldown(12);
			speed = 2.0f;
		}
		else if(GomuHelper.hasGearFourthActive(props))
		{
			projectile = new GomuGomuNoKongGunProjectile(player.world, player);
			this.setMaxCooldown(15);
			speed = 2.0f;
		}
		else
		{
			projectile = new GomuGomuNoPistolProjectile(player.world, player);
			this.setMaxCooldown(7);
			speed = 2.5f;
		}
		
		player.world.addEntity(projectile);
		projectile.shoot(player, player.rotationPitch, player.rotationYaw, 0, speed, 1);
		
		return true;
	}
}
