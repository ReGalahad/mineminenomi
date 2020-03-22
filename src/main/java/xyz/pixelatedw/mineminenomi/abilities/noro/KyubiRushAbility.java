package xyz.pixelatedw.mineminenomi.abilities.noro;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.PunchAbility;

public class KyubiRushAbility extends PunchAbility
{
	public static final Ability INSTANCE = new KyubiRushAbility();

	public KyubiRushAbility()
	{
		super("Kyubi Rush", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(5);
		this.setDescription("While the opponent is slowed, the user delivers a series of punches, which hits the opponent all at once");

		this.onHitEntity = this::onHitEntity;
	}
	
	private float onHitEntity(PlayerEntity player, LivingEntity target)
	{
		float damageFromSlowness = 0;
		
		if (target.isPotionActive(Effects.SLOWNESS))
		{
			damageFromSlowness = (float) (Math.sqrt(target.getActivePotionEffect(Effects.SLOWNESS).getDuration()) / 2);
			int newTime = target.getActivePotionEffect(Effects.SLOWNESS).getDuration() / 2;
			int newAmplifier = target.getActivePotionEffect(Effects.SLOWNESS).getAmplifier() - 5;
			target.removePotionEffect(Effects.SLOWNESS);
			target.removePotionEffect(Effects.MINING_FATIGUE);
			target.addPotionEffect(new EffectInstance(Effects.SLOWNESS, newTime, newAmplifier));
			target.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, newTime, newAmplifier));
		}
		else
			damageFromSlowness = 2;
		
		return damageFromSlowness;
	}

}