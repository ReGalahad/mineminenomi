package xyz.pixelatedw.mineminenomi.abilities.bane;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.SEntityVelocityPacket;
import net.minecraft.util.math.Vec3d;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.ChargeableAbility;

public class SpringHopperAbility extends ChargeableAbility
{
	public static final Ability INSTANCE = new SpringHopperAbility();
	
	public SpringHopperAbility()
	{
		super("Spring Hopper", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(0.6);
		this.setMaxChargeTime(1);
		this.setDescription("Turning the user's legs into springs, which launches them into the air.");

		this.onStartChargingEvent = this::onStartChargingEvent;
		this.onEndChargingEvent = this::onEndChargingEvent;
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
}