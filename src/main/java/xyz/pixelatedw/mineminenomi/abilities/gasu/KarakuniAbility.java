package xyz.pixelatedw.mineminenomi.abilities.gasu;

import java.util.List;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;

public class KarakuniAbility extends Ability
{
	public static final KarakuniAbility INSTANCE = new KarakuniAbility();
	
	public KarakuniAbility()
	{
		super("Karakuni", AbilityCategory.DEVIL_FRUIT);
		this.setDescription("Removes the oxygen around the user, suffocating and weakening everyone in the vicinity.");
		this.setMaxCooldown(30);

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		List<LivingEntity> targets = WyHelper.<LivingEntity>getEntitiesNear(player.getPosition(), player.world, 25, FactionHelper.getOutsideGroupPredicate(player), LivingEntity.class);
		targets.remove(player);
		
		for (LivingEntity entity : targets)
		{
			entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 20);
			entity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 1000, 2));
			entity.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 400, 1));
		}
		
		return true;
	}
}
