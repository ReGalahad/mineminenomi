package xyz.pixelatedw.mineminenomi.abilities.chiyu;

import java.util.List;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.chiyu.ChiyupopoParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;

public class ChiyupopoAbility extends Ability
{
	public static final Ability INSTANCE = new ChiyupopoAbility();

	private static final ParticleEffect PARTICLES = new ChiyupopoParticleEffect();
	
	public ChiyupopoAbility()
	{
		super("Chiyupopo", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(15);
		this.setDescription("Releases dandelions made of tears that temporarily increase the healing rate of those whom they make contact with.");
		
		this.onUseEvent = this::onUseEvent;
	}
	
	private boolean onUseEvent(PlayerEntity player)
	{
		player.heal(player.getMaxHealth());

		List<LivingEntity> targets = WyHelper.<LivingEntity>getEntitiesNear(player.getPosition(), player.world, 15);
		targets.remove(player);

		for(LivingEntity entity : targets)
		{
			entity.addPotionEffect(new EffectInstance(Effects.REGENERATION, 300, 1));
		}
		
		PARTICLES.spawn(player.world, player.posX, player.posY, player.posZ, 0, 0, 0);

		return true;
	}
}
