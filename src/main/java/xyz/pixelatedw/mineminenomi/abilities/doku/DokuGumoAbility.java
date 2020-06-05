package xyz.pixelatedw.mineminenomi.abilities.doku;

import java.util.List;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.doku.DokuGumoParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;

public class DokuGumoAbility extends ContinuousAbility
{
	public static final Ability INSTANCE = new DokuGumoAbility();

	private static final ParticleEffect PARTICLES = new DokuGumoParticleEffect();
	
	public DokuGumoAbility()
	{
		super("Doku Gumo", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(30);
		this.setThreshold(20);
		this.setDescription("Creates a dense cloud of poisonous smoke, which moves along with the user and poisons and blinds everyone inside.");

		this.duringContinuityEvent = this::duringContinuity;
	}
	
	private void duringContinuity(PlayerEntity player, int timer)
	{
		List<LivingEntity> targets = WyHelper.<LivingEntity>getEntitiesNear(player.getPosition(), player.world, 10);
		targets.remove(player);
		player.addPotionEffect(new EffectInstance(Effects.POISON, 200, 1));

		for(LivingEntity enemy : targets)
		{
			if(!enemy.isPotionActive(Effects.BLINDNESS))
				enemy.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 10 * 20, 0));
			if(!enemy.isPotionActive(Effects.POISON))
				enemy.addPotionEffect(new EffectInstance(Effects.POISON, 10 * 20, 1));
			if(!enemy.isPotionActive(Effects.WEAKNESS))
				enemy.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 10 * 20, 0));
		}
		
		PARTICLES.spawn(player.world, player.posX, player.posY, player.posZ, 0, 0, 0);
	}
}
