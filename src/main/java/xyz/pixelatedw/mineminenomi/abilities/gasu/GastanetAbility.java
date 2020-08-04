package xyz.pixelatedw.mineminenomi.abilities.gasu;

import java.util.List;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.gasu.GastanetParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;

public class GastanetAbility extends Ability
{
	public static final GastanetAbility INSTANCE = new GastanetAbility();

	private static final ParticleEffect PARTICLES = new GastanetParticleEffect();
	
	public GastanetAbility()
	{
		super("Gastanet", AbilityCategory.DEVIL_FRUIT);
		this.setDescription("The user fills castanets with unstable gas, which causes an explosion when slammed together.");
		this.setMaxCooldown(6);

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		ExplosionAbility explosion = AbilityHelper.newExplosion(player, player.posX, player.posY, player.posZ, 5);
		explosion.setExplosionSound(true);
		explosion.setDamageOwner(false);
		explosion.setDestroyBlocks(false);
		explosion.setFireAfterExplosion(false);
		explosion.setSmokeParticles(null);
		explosion.setDamageEntities(true);
		explosion.doExplosion();		
		
		List<LivingEntity> targets = WyHelper.<LivingEntity>getEntitiesNear(player.getPosition(), player.world, 6, FactionHelper.getOutsideGroupPredicate(player), LivingEntity.class);
		targets.remove(player);
		
		for (LivingEntity target : targets)
		{
			target.addPotionEffect(new EffectInstance(Effects.POISON, 200, 1));
		}
		
		PARTICLES.spawn(player.world, player.posX, player.posY, player.posZ, 0, 0, 0);
		
		return true;
	}
}
