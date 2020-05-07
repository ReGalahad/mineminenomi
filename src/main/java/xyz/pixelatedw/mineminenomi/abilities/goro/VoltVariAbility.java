package xyz.pixelatedw.mineminenomi.abilities.goro;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.VoltVari200MillionProjectile;
import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.VoltVari20MillionProjectile;
import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.VoltVari5MillionProjectile;
import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.VoltVari60MillionProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.ChargeableAbility;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class VoltVariAbility extends ChargeableAbility
{
	public static final Ability INSTANCE = new VoltVariAbility();

	private int power = 0;
	
	public VoltVariAbility()
	{
		super("Volt Vari", AbilityCategory.DEVIL_FRUIT);
		this.setDescription("Creates a concentrated ball of lightning, which varies in power");
		this.setMaxCooldown(3);
		this.setMaxChargeTime(10);
		this.setCancelable();
		
		this.duringChargingEvent = this::duringChargingEvent;
		this.onEndChargingEvent = this::onEndChargingEvent;
	}
	
	private void duringChargingEvent(PlayerEntity player, int chargeTimer)
	{
		this.power = chargeTimer;
		double truePower = Math.abs(power - this.getMaxChargeTime());
		if (truePower % 25 == 0 && CommonConfig.instance.isAnimeScreamingEnabled())
		{
			int voltVariType = (int) Math.floor(truePower / 25);
			switch (voltVariType)
			{
				case 1:
					this.setName("1 Million Volt Vari");
					break;
				case 2:
					this.setName("5 Million Volt Vari");
					break;
				case 3:
					this.setName("10 Million Volt Vari");
					break;
				case 4:
					this.setName("20 Million Volt Vari");
					break;
				case 5:
					this.setName("50 Million Volt Vari");
					break;
				case 6:
					this.setName("60 Million Volt Vari");
					break;
				case 7:
					this.setName("100 Million Volt Vari");
					break;
			}
//			if (voltVariType < 8)
//				this.sendShounenScream(player);
		}
	}
	
	private boolean onEndChargingEvent(PlayerEntity player)
	{
		double truePower = Math.abs(this.power - this.getMaxChargeTime());
		AbilityProjectileEntity projectile = null;
		
		if (truePower > 0 && truePower <= 50)
		{
			/*
			if (CommonConfig.instance.isAnimeScreamingEnabled())
			{
				if (truePower > 0 && truePower <= 25)
					this.setName("1 Million Volt Vari");
				else
					this.setName("5 Million Volt Vari");
			}
			*/
			projectile = new VoltVari5MillionProjectile(player.world, player);
			this.setMaxCooldown(3);
		}
		else if (truePower > 50 && truePower <= 100)
		{
			/*
			if (CommonConfig.instance.isAnimeScreamingEnabled())
			{
				if (truePower > 50 && truePower <= 75)
					this.setName("10 Million Volt Vari");
				else
					this.setName("20 Million Volt Vari");
			}
			*/
			projectile = new VoltVari20MillionProjectile(player.world, player);
			this.setMaxCooldown(5);
		}
		else if (truePower > 100 && truePower <= 150)
		{
			/*
			if (CommonConfig.instance.isAnimeScreamingEnabled())
			{
				if (truePower > 100 && truePower <= 125)
					this.setName("50 Million Volt Vari");
				else
					this.setName("60 Million Volt Vari");
			}
			*/
			projectile = new VoltVari60MillionProjectile(player.world, player);
			this.setMaxCooldown(7);
		}
		else if (truePower > 150 && truePower <= 200)
		{
			/*
			if (CommonConfig.instance.isAnimeScreamingEnabled())
			{
				if (truePower > 150 && truePower <= 175)
					this.setName("100 Million Volt Vari");
				else
					this.setName("Max 200 Million Volt Vari");
			}
			*/
			projectile = new VoltVari200MillionProjectile(player.world, player);
			this.setMaxCooldown(10);
		}
		
		player.world.addEntity(projectile);
		projectile.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);
		
		return true;
	}
}