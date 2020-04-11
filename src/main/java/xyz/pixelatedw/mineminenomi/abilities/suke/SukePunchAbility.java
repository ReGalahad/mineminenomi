package xyz.pixelatedw.mineminenomi.abilities.suke;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.PunchAbility;

public class SukePunchAbility extends PunchAbility
{
	public static final Ability INSTANCE = new SukePunchAbility();

	public SukePunchAbility()
	{
		super("Suke Punch", AbilityCategory.DEVIL_FRUIT);
		this.setDescription("Turns an entity's entire body invisible after hitting them.");

		this.onHitEntityEvent = this::onHitEntity;
	}
	
	private float onHitEntity(PlayerEntity player, LivingEntity target)
	{
		if(target.isPotionActive(Effects.INVISIBILITY))
			target.removePotionEffect(Effects.INVISIBILITY);
		else
			target.addPotionEffect(new EffectInstance(Effects.INVISIBILITY, Integer.MAX_VALUE, 1, false, false));
		
		return 1;
	}
}
