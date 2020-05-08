package xyz.pixelatedw.mineminenomi.api.helpers;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;

public class HakiHelper
{
	public static float getKenHakiExp()
	{
		return 0;
	}
	
	public static float getBusoHakiExp()
	{
		return 0;
	}
	
	public static float getTotalHakiExp()
	{
		return 0;
	}
	
	public static void checkForHakiOveruse(PlayerEntity player, int passiveTimer)
	{
		IEntityStats props = EntityStatsCapability.get(player);
		if (passiveTimer > 2400 + (props.getDoriki() / 15))
		{
			player.addPotionEffect(new EffectInstance(Effects.NAUSEA, 100, 0));
			player.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 100, 0));
			
			if(passiveTimer > 3600 + (props.getDoriki() / 15))
			{
				player.addPotionEffect(new EffectInstance(Effects.NAUSEA, 100, 5));
				player.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 100, 5));
				player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 100, 5));
				player.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 100, 5));
			}
		}
	}
	
}
