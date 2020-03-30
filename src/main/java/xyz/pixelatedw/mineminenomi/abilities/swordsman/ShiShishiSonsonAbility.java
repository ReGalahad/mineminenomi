package xyz.pixelatedw.mineminenomi.abilities.swordsman;

import java.util.List;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitsHelper;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;

public class ShiShishiSonsonAbility extends Ability
{
	public static final Ability INSTANCE = new ShiShishiSonsonAbility();

	public ShiShishiSonsonAbility()
	{
		super("Shi Shishi Sonson", AbilityCategory.RACIAL);
		this.setMaxCooldown(8);
		this.setDescription("The user dashes forward and rapidly slashes the opponent.");

		this.onUseEvent = this::onUseEvent;
		this.duringCooldownEvent = this::duringCooldown;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		if (!DevilFruitsHelper.canUseSwordsmanAbilities(player))
		{
			WyHelper.sendMsgToPlayer(player, "You need a sword to use this ability !");
			return false;
		}

		double[] speed = WyHelper.propulsion(player, 3, 3);
		player.setMotion(speed[0], 0.2, speed[2]);
		player.velocityChanged = true;
		((ServerWorld) player.world).getChunkProvider().sendToTrackingAndSelf(player, new SAnimateHandPacket(player, 0));
		
		return true;
	}
	
	private void duringCooldown(PlayerEntity player, int cooldownTimer)
	{
		if (cooldownTimer > 6 * 20)
		{
			List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 1.6);
			list.remove(player);

			list.stream().forEach(entity ->
			{
				entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 10);
			});
		}
	}
}