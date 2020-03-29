package xyz.pixelatedw.mineminenomi.abilities.fishmankarate;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.PunchAbility;

public class KachiageHaisokuAbility extends PunchAbility
{
	public static final Ability INSTANCE = new KachiageHaisokuAbility();
	
	public KachiageHaisokuAbility()
	{
		super("Kachiage Haisoku", AbilityCategory.RACIAL);
		this.setMaxCooldown(8);
		this.setDescription("The user delivers a powerful kick to the opponent's chin.");

		this.onHitEntity = this::onHitEntity;
	}
	
	private float onHitEntity(PlayerEntity player, LivingEntity target)
	{
		float damage = 20;
		
		if(player.isInWater())
			damage = 40;
		
		return damage;
	}
}
