package xyz.pixelatedw.mineminenomi.abilities.ope;

import java.util.List;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.SEntityVelocityPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.data.ability.AbilityDataCapability.Category;
import xyz.pixelatedw.mineminenomi.helpers.DevilFruitsHelper;
import xyz.pixelatedw.mineminenomi.helpers.ItemsHelper;

public class InjectionShotAbility extends Ability
{
	public static final Ability INSTANCE = new InjectionShotAbility();

	public InjectionShotAbility()
	{
		super("Injection Shot", Category.DEVIL_FRUIT);
		this.setMaxCooldown(15);
		this.setDescription("While holding a weapon, the user charges at the enemy, leaving them poisoned and confused");

		this.onUseEvent = this::onUseEvent;
		this.duringCooldownEvent = this::duringCooldown;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		if (!DevilFruitsHelper.isEntityInRoom(player))
		{
			WyHelper.sendMsgToPlayer(player, "" + this.getName() + " can only be used inside ROOM !");
			return false;
		}
		
		if (!ItemsHelper.isSword(player.getHeldItemMainhand()))
		{
			WyHelper.sendMsgToPlayer(player, "You need a sword to use this ability !");
			return false;
		}

		double[] speed = WyHelper.propulsion(player, 3, 3);
		player.setMotion(speed[0], 0.2, speed[1]);
		((ServerPlayerEntity)player).connection.sendPacket(new SEntityVelocityPacket(player));
		
		return true;
	}
	
	private void duringCooldown(PlayerEntity player, int cooldownTimer)
	{
		if (cooldownTimer > 13)
		{
			List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 1.6);
			list.remove(player);

			list.parallelStream().forEach(entity ->
			{
				entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 20);
				entity.addPotionEffect(new EffectInstance(Effects.POISON, 10 * 20, 0));
				entity.addPotionEffect(new EffectInstance(Effects.NAUSEA, 10 * 20, 0));
			});
		}
	}
}
