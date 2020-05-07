package xyz.pixelatedw.mineminenomi.abilities.chiyu;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.chiyu.HealingTouchParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.PunchAbility;

public class HealingTouchAbility extends PunchAbility
{
	public static final HealingTouchAbility INSTANCE = new HealingTouchAbility();

	private static final ParticleEffect PARTICLES = new HealingTouchParticleEffect();

	public HealingTouchAbility()
	{
		super("Healing Touch", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(10);
		this.setDescription("Targets hit with his ability will get a healing over time buff.");

		this.onHitEntityEvent = this::onHitEntityEvent;
	}

	private float onHitEntityEvent(PlayerEntity player, LivingEntity target)
	{
		target.heal(20);
		target.addPotionEffect(new EffectInstance(Effects.REGENERATION, 400, 1));

		PARTICLES.spawn(player.world, target.posX, target.posY, target.posZ, 0, 0, 0);

		return 0;
	}
}
