package xyz.pixelatedw.mineminenomi.abilities.suna;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.suna.DesertEncierroParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.PunchAbility;

public class DesertEncierroAbility extends PunchAbility
{
	public static final Ability INSTANCE = new DesertEncierroAbility();
	
	private static final ParticleEffect PARTICLES = new DesertEncierroParticleEffect();

	public DesertEncierroAbility()
	{
		super("Desert Encierro", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(8);
		this.setDescription("Quickly drains the enemy of their moisture leaving them weak for a few seconds.");

		this.onHitEntityEvent = this::onHitEntity;
	}

	private float onHitEntity(PlayerEntity player, LivingEntity target)
	{
		target.addPotionEffect(new EffectInstance(Effects.HUNGER, 100, 1, false, false));
		target.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 100, 1, false, false));
		target.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 100, 1, false, false));
		target.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 100, 1, false, false));
		
		PARTICLES.spawn(player.world, target.posX, target.posY, target.posZ, 0, 0, 0);
		
		return 8;
	}
}
