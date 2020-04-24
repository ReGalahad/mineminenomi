package xyz.pixelatedw.mineminenomi.abilities.bane;

import java.util.List;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.SEntityVelocityPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.ChargeableAbility;

public class SpringSnipeAbility  extends ChargeableAbility
{
	public static final Ability INSTANCE = new SpringSnipeAbility();
	
	public SpringSnipeAbility()
	{
		super("Spring Snipe", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(5);
		this.setMaxChargeTime(1);
		this.setDescription("Turning the user's forelegs into springs, they can launch themselves directly at the opponent.");

		this.onEndChargingEvent = this::onEndChargingEvent;
		this.duringCooldownEvent = this::duringCooldown;
	}
	
	private boolean onEndChargingEvent(PlayerEntity player)
	{
		Vec3d speed = WyHelper.propulsion(player, 6, 2, 6);
		player.setMotion(speed.x, 0.5 + speed.y, speed.z);
		((ServerPlayerEntity)player).connection.sendPacket(new SEntityVelocityPacket(player));
		return true;
	}
	
	private void duringCooldown(PlayerEntity player, int cooldownTimer)
	{
		if ((cooldownTimer / 20) > (this.maxCooldown / 20) - 3)
		{
			List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 1.6);
			list.remove(player);
			for (LivingEntity target : list)
				target.attackEntityFrom(DamageSource.causePlayerDamage(player), 8);
		}
	}
}