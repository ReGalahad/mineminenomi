package xyz.pixelatedw.mineminenomi.abilities.pika;

import java.util.List;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.api.helpers.CrewHelper;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.pika.FlashParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;

public class FlashAbility extends Ability
{
	public static final Ability INSTANCE = new FlashAbility();
	
	private static final ParticleEffect PARTICLES = new FlashParticleEffect();

	public FlashAbility()
	{
		super("Flash", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(10);
		this.setDescription("The user creates a bright flash of light, blinding their opponents.");

		this.onUseEvent = this::onUseEvent;
	}
	
	private boolean onUseEvent(PlayerEntity player)
	{
		List<LivingEntity> list = WyHelper.<LivingEntity>getEntitiesNear(player.getPosition(), player.world, 10, CrewHelper.NOT_IN_CREW_PREDICATE, LivingEntity.class);
		list.remove(player);
		
		list.stream().forEach(entity -> 
		{
			entity.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 7 * 20, 3));
			entity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 7 * 20, 1));
			PARTICLES.spawn(player.world, entity.posX, entity.posY + entity.getEyeHeight(), entity.posZ, 0, 0, 0);
		});
		
		return true;
	}
}