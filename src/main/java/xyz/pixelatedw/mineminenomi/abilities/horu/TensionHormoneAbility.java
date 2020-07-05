package xyz.pixelatedw.mineminenomi.abilities.horu;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.PunchAbility;

public class TensionHormoneAbility extends PunchAbility
{
	public static final TensionHormoneAbility INSTANCE = new TensionHormoneAbility();

	public TensionHormoneAbility()
	{
		super("Tension Hormone", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(30);
		this.setDescription("The user injects a target or themselves with special hormones meant to provide them a supply of adrenaline");

		this.onStartContinuityEvent = this::onStartContinuityEvent;
		this.onHitEntityEvent = this::onHitEntity;
	}

	private boolean onStartContinuityEvent(PlayerEntity player)
	{
		if (player.isSneaking())
		{
			player.addPotionEffect(new EffectInstance(ModEffects.TENSION_HORMONE, 200, 0));
			this.stopContinuity(player);
			return false;
		}

		return true;
	}

	private float onHitEntity(PlayerEntity player, LivingEntity target)
	{
		target.addPotionEffect(new EffectInstance(ModEffects.TENSION_HORMONE, 200, 0));

		return 0;
	}
}
