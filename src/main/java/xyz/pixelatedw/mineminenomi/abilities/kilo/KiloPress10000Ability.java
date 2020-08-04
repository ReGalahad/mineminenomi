package xyz.pixelatedw.mineminenomi.abilities.kilo;

import java.util.List;
import java.util.UUID;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.SEntityVelocityPacket;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;

public class KiloPress10000Ability extends ContinuousAbility
{
	public static final KiloPress10000Ability INSTANCE = new KiloPress10000Ability();

	private static final AttributeModifier KILO_PRESS = new AttributeModifier(UUID.fromString("692759d2-5d8d-4809-912d-86ad362f8f95"), "Kilo Press", -10.0, Operation.ADDITION);
	private static final AttributeModifier KILO_PRESS_KNOCKBACK = new AttributeModifier(UUID.fromString("f3597992-9268-4a40-9363-555cf06c7771"), "Kilo Press Knockback", 1.0, Operation.ADDITION);

	private double initialPosY = 0;

	public KiloPress10000Ability()
	{
		super("10,000 Kilo Press", AbilityCategory.DEVIL_FRUIT);
		this.setThreshold(60);

		this.onStartContinuityEvent = this::onStartContinuityEvent;
		this.duringContinuityEvent = this::duringContinuityEvent;
		this.onEndContinuityEvent = this::onEndContinuityEvent;
	}

	private boolean onStartContinuityEvent(PlayerEntity player)
	{
		player.getAttribute(ModAttributes.JUMP_HEIGHT).applyModifier(KILO_PRESS);
		player.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).applyModifier(KILO_PRESS_KNOCKBACK);

		player.setMotion(player.getMotion().x, -5, player.getMotion().z);
		((ServerPlayerEntity)player).connection.sendPacket(new SEntityVelocityPacket(player));
		
		if (!player.onGround)
			this.initialPosY = player.posY;
		else
			this.initialPosY = 0;

		return true;
	}

	private void duringContinuityEvent(PlayerEntity player, int time)
	{
		player.fallDistance = 0;

		if (player.onGround && this.initialPosY > 0 && player.posY < this.initialPosY)
		{
			double damage = this.initialPosY - player.posY;
			if (damage > 0)
			{
				List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 5, FactionHelper.getOutsideGroupPredicate(player), LivingEntity.class);
				targets.remove(player);
				for (LivingEntity entity : targets)
				{
					entity.attackEntityFrom(DamageSource.causePlayerDamage(player), (float) (damage * 0.75));
				}
				this.initialPosY = 0;
			}
		}
	}

	private boolean onEndContinuityEvent(PlayerEntity player)
	{
		player.getAttribute(ModAttributes.JUMP_HEIGHT).removeModifier(KILO_PRESS);
		player.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).removeModifier(KILO_PRESS_KNOCKBACK);

		int cooldown = (int) Math.round(this.continueTime / 20.0);
		this.setMaxCooldown(cooldown);

		return true;
	}
}
