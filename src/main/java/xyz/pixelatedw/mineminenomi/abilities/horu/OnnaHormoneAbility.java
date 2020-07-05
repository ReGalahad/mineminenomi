package xyz.pixelatedw.mineminenomi.abilities.horu;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.PunchAbility;

public class OnnaHormoneAbility extends PunchAbility
{
	public static final OnnaHormoneAbility INSTANCE = new OnnaHormoneAbility();

	public OnnaHormoneAbility()
	{
		super("Onna Hormone", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(7);
		this.setDescription("By injecting an enemy with special female hormones, the user can inflict moderate debuffs on them.");

		this.onStartContinuityEvent = this::onStartContinuityEvent;
		this.onHitEntityEvent = this::onHitEntity;
	}

	private boolean onStartContinuityEvent(PlayerEntity player)
	{
		if (player.isSneaking())
		{

		}

		return true;
	}

	private float onHitEntity(PlayerEntity player, LivingEntity target)
	{
		target.addPotionEffect(new EffectInstance(Effects.NAUSEA, 600, 2));
		target.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 600, 1));
		target.addPotionEffect(new EffectInstance(Effects.SPEED, 600, 0));

		return 0;
	}
}
