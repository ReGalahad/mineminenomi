package xyz.pixelatedw.mineminenomi.abilities.ito;

import java.util.List;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.api.helpers.CrewHelper;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;

public class ParasiteAbility extends Ability
{
	public static final ParasiteAbility INSTANCE = new ParasiteAbility();

	public ParasiteAbility()
	{
		super("Parasite", AbilityCategory.DEVIL_FRUIT);
		this.setDescription("By attaching your strings to nearby enemies they'll start attacking nearby targets, except you!");
		this.setMaxCooldown(14);
		
		this.onUseEvent = this::onUseEvent;
	}
	
	private boolean onUseEvent(PlayerEntity player)
	{
		List<LivingEntity> targets = WyHelper.<LivingEntity>getEntitiesNear(player.getPosition(), player.world, 20, CrewHelper.NOT_IN_CREW_PREDICATE, LivingEntity.class);
		targets.remove(player);
		for(LivingEntity target : targets)
		{
			target.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 200, 10));
			target.addPotionEffect(new EffectInstance(Effects.NAUSEA, 200, 10));	
			target.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 200, 10));
			target.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 200, 10));
		}
		
		return true;
	}
}
