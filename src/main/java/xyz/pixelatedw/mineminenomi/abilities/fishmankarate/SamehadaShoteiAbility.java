package xyz.pixelatedw.mineminenomi.abilities.fishmankarate;

import java.util.UUID;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.fishkarate.SamehadaParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;

public class SamehadaShoteiAbility extends ContinuousAbility
{
	public static final Ability INSTANCE = new SamehadaShoteiAbility();
	private static final ParticleEffect PARTICLES = new SamehadaParticleEffect();
	private static final AttributeModifier JUMP_MULTIPLIER = new AttributeModifier(UUID.fromString("efa11cbd-57e5-478f-b15c-6295eb1b375e"), "Jump Multiplier",  -50, AttributeModifier.Operation.ADDITION).setSaved(false);

	public SamehadaShoteiAbility()
	{
		super("Samehada Shotei", AbilityCategory.RACIAL);
		this.setThreshold(10);
		this.setDescription("The user concentrates their power to their palms, protecting themselves from attacks.");

		this.onStartContinuityEvent = this::onStartContinuityEvent;
		this.duringContinuityEvent = this::duringContinuity;
		this.onEndContinuityEvent = this::onEndContinuityEvent;
	}

	private boolean onStartContinuityEvent(PlayerEntity player) {
		player.getAttribute(ModAttributes.JUMP_HEIGHT).applyModifier(JUMP_MULTIPLIER);
		return true;
	}

	private void duringContinuity(PlayerEntity player, int passiveTimer)
	{
		player.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 20, 6, false, false));
		player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 20, 100, false, false));
		player.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 20, 5, false, false));
		
		PARTICLES.spawn(player.world, player.posX, player.posY, player.posZ, 0, 0, 0);
	}

	private boolean onEndContinuityEvent(PlayerEntity player)
	{
		if(player.getAttribute(ModAttributes.JUMP_HEIGHT).hasModifier(JUMP_MULTIPLIER))
			player.getAttribute(ModAttributes.JUMP_HEIGHT).removeModifier(JUMP_MULTIPLIER);
		int cooldown = (int) Math.round(this.continueTime / 15.0);
		this.setMaxCooldown(cooldown);
		player.removePotionEffect(Effects.RESISTANCE);
		player.removePotionEffect(Effects.SLOWNESS);
		player.removePotionEffect(Effects.MINING_FATIGUE);
		return true;
	}
}