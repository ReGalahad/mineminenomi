package xyz.pixelatedw.mineminenomi.abilities.moku;

import java.util.List;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.SEntityVelocityPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.moku.WhiteLauncherParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.ChargeableAbility;

public class WhiteLauncherAbility extends ChargeableAbility
{
	public static final Ability INSTANCE = new WhiteLauncherAbility();
	
	private static final ParticleEffect PARTICLES = new WhiteLauncherParticleEffect();
	
	public WhiteLauncherAbility()
	{
		super("White Launcher", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(5);
		this.setMaxChargeTime(1);
		this.setDescription("Transforms the user into smoke and launches them forward.");

		this.onStartChargingEvent = this::onStartChargingEvent;
		this.onEndChargingEvent = this::onEndChargingEvent;
		this.duringCooldownEvent = this::duringCooldown;
	}
	
	private boolean onStartChargingEvent(PlayerEntity player)
	{
		return player.onGround;
	}
	
	private boolean onEndChargingEvent(PlayerEntity player)
	{
		Vec3d speed = WyHelper.propulsion(player, 5.5, 5.5);
		player.setMotion(speed.x, 2.0, speed.z);

		((ServerPlayerEntity)player).connection.sendPacket(new SEntityVelocityPacket(player));
		return true;
	}
	
	private void duringCooldown(PlayerEntity player, int cooldownTimer)
	{
		if ((cooldownTimer / 20) > (this.maxCooldown / 20) - 3)
		{
			if(cooldownTimer % 2 == 0)
				PARTICLES.spawn(player.world, player.posX, player.posY, player.posZ, 0, 0, 0);
			List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 1.6, FactionHelper.getOutsideGroupPredicate(player), LivingEntity.class);
			list.remove(player);
			for (LivingEntity target : list)
				target.attackEntityFrom(DamageSource.causePlayerDamage(player), 4);
		}
	}
}
