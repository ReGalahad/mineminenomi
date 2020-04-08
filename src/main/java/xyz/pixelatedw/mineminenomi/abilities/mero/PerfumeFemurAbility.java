package xyz.pixelatedw.mineminenomi.abilities.mero;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.mero.PerfumeFemurParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.PunchAbility;

public class PerfumeFemurAbility extends PunchAbility
{
	public static final PerfumeFemurAbility INSTANCE = new PerfumeFemurAbility();

	private static final ParticleEffect PARTICLES = new PerfumeFemurParticleEffect();

	public PerfumeFemurAbility()
	{
		super("Perfume Femur", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(8);
		this.setDescription("Truns enemies hit by this into stone");

		this.onHitEntityEvent = this::onHitEntityEvent;
	}

	private float onHitEntityEvent(PlayerEntity player, LivingEntity target)
	{
		target.addPotionEffect(new EffectInstance(ModEffects.LOVESTRUCK, 300, 1));

		PARTICLES.spawn(player.world, target.posX, target.posY, target.posZ, 0, 0, 0);

		return 10;
	}
}