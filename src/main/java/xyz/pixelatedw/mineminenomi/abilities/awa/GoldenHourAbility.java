package xyz.pixelatedw.mineminenomi.abilities.awa;

import java.util.List;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.awa.GoldenHourParticleEffect;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;

public class GoldenHourAbility extends ContinuousAbility
{
	public static final GoldenHourAbility INSTANCE = new GoldenHourAbility();
	private static final ParticleEffect PARTICLES = new GoldenHourParticleEffect();
	
	public GoldenHourAbility()
	{
		super("Golden Hour", APIConfig.AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(40);
		this.setThreshold(10);
		this.setDescription("Spreads bubbles on your foe leaving them in a weakened");

		this.duringContinuityEvent = this::duringContinuity;
	}

	private void duringContinuity(PlayerEntity player, int timer)
	{
		List<LivingEntity> targets = WyHelper.<LivingEntity>getEntitiesNear(player.getPosition(), player.world, 10, FactionHelper.getOutsideGroupPredicate(player), LivingEntity.class);
		targets.remove(player);

		for(LivingEntity target : targets)
		{
			if(!target.isPotionActive(Effects.MINING_FATIGUE))
				target.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 600, 3, true, false, true));
			if(!target.isPotionActive(Effects.WEAKNESS))
				target.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 600, 3, true, false, true));
			if(!target.isPotionActive(Effects.SLOWNESS))
				target.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 600, 4, true, false, true));
			
			PARTICLES.spawn(player.world, target.posX, target.posY, target.posZ, 0, 0, 0);
		}
		
	}
}
