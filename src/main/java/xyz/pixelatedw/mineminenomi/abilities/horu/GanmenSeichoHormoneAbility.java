package xyz.pixelatedw.mineminenomi.abilities.horu;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.PunchAbility;

public class GanmenSeichoHormoneAbility extends PunchAbility
{
	public static final GanmenSeichoHormoneAbility INSTANCE = new GanmenSeichoHormoneAbility();

	public GanmenSeichoHormoneAbility()
	{
		super("Ganmen Seicho Hormone", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(1);
		this.setDescription("By injecting special growth hormones into a target or themselves the user can make a person's head expand to enormous proportions that surpass even giants.");

		this.onStartContinuityEvent = this::onStartContinuityEvent;
		this.onHitEntityEvent = this::onHitEntity;
	}

	private boolean onStartContinuityEvent(PlayerEntity player)
	{
		if (player.isSneaking())
		{
			if (player.isPotionActive(ModEffects.GANMEN_SEICHO_HORMONE))
				player.removePotionEffect(ModEffects.GANMEN_SEICHO_HORMONE);
			else
				player.addPotionEffect(new EffectInstance(ModEffects.GANMEN_SEICHO_HORMONE, 2000, 0));
			this.stopContinuity(player);
			return false;
		}

		return true;
	}

	private float onHitEntity(PlayerEntity player, LivingEntity target)
	{
		if (target.isPotionActive(ModEffects.GANMEN_SEICHO_HORMONE))
			target.removePotionEffect(ModEffects.GANMEN_SEICHO_HORMONE);
		else
			target.addPotionEffect(new EffectInstance(ModEffects.GANMEN_SEICHO_HORMONE, 2000, 0));

		return 0;
	}
}
