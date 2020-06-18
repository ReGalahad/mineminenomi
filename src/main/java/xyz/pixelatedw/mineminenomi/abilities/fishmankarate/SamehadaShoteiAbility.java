package xyz.pixelatedw.mineminenomi.abilities.fishmankarate;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.fishkarate.SamehadaParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;

public class SamehadaShoteiAbility extends ContinuousAbility
{
	public static final Ability INSTANCE = new SamehadaShoteiAbility();
	private static final ParticleEffect PARTICLES = new SamehadaParticleEffect();

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
		return true;
	}

	private void duringContinuity(PlayerEntity player, int passiveTimer)
	{
		player.addPotionEffect(new EffectInstance(ModEffects.GUARDING, 10, 2, false, false));

		
		PARTICLES.spawn(player.world, player.posX, player.posY, player.posZ, 0, 0, 0);
	}

	private boolean onEndContinuityEvent(PlayerEntity player)
	{
		int cooldown = (int) Math.round(this.continueTime / 15.0);
		this.setMaxCooldown(cooldown);
		return true;
	}
}