package xyz.pixelatedw.mineminenomi.abilities.pika;

import java.util.List;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.data.ability.AbilityDataCapability.Category;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.pika.ParticleEffectFlash;

public class FlashAbility extends Ability
{
	public static final Ability INSTANCE = new FlashAbility();
	
	private static final ParticleEffect PARTICLES = new ParticleEffectFlash();

	public FlashAbility()
	{
		super("Flash", Category.DEVIL_FRUIT);
		this.setMaxCooldown(10);
		this.setDescription("The user creates a bright flash of light, blinding their opponents.");

		this.onUseEvent = this::onUseEvent;
	}
	
	private void onUseEvent(PlayerEntity player, Ability ability)
	{
		List<LivingEntity> list = WyHelper.<LivingEntity>getEntitiesNear(player.getPosition(), player.world, 10);
		list.remove(player);
		
		list.parallelStream().forEach(entity -> 
		{
			entity.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 7 * 20, 3));
			entity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 7 * 20, 1));
			PARTICLES.spawn(player.world, entity.posX, entity.posY + entity.getEyeHeight(), entity.posZ, 0, 0, 0);
		});
	}
}