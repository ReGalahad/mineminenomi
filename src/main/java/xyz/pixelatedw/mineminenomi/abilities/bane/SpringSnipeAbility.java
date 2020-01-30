package xyz.pixelatedw.mineminenomi.abilities.bane;

import java.util.List;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.SEntityVelocityPacket;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.abilities.ChargeableAbility;
import xyz.pixelatedw.mineminenomi.api.data.ability.AbilityDataCapability.Category;

public class SpringSnipeAbility  extends ChargeableAbility
{
	public static final Ability INSTANCE = new SpringSnipeAbility();
	
	public SpringSnipeAbility()
	{
		super("Spring Snipe", Category.DEVIL_FRUIT);
		this.setMaxCooldown(5);
		this.setMaxChargeTime(1);
		this.setDescription("Turning the user's forelegs into springs, they can launch themselves directly at the opponent.");

		this.onEndChargingEvent = this::onEndChargingEvent;
		this.duringCooldownEvent = this::duringCooldown;
	}
	
	private boolean onEndChargingEvent(PlayerEntity player)
	{
		double[] speed = WyHelper.propulsion(player, 12.5, 12.5);
		player.setMotion(speed[0], 0.2, speed[1]);
		((ServerPlayerEntity)player).connection.sendPacket(new SEntityVelocityPacket(player));
		return true;
	}
	
	private void duringCooldown(PlayerEntity player, Ability ability, int cooldownTimer)
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