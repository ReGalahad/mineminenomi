package xyz.pixelatedw.mineminenomi.api.helpers;

import java.util.Arrays;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiFullBodyHardeningAbility;
import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiHardeningAbility;
import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiImbuingAbility;
import xyz.pixelatedw.mineminenomi.abilities.haki.KenbunshokuHakiAuraAbility;
import xyz.pixelatedw.mineminenomi.abilities.haki.KenbunshokuHakiFutureSightAbility;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

public class HakiHelper
{
	public static boolean canUseHaki(PlayerEntity player, Ability ability)
	{
		IAbilityData props = AbilityDataCapability.get(player);

		System.out.println(Arrays.toString(props.getEquippedAbilities(AbilityCategory.HAKI)));
		
		for(Ability abl : props.getEquippedAbilities(AbilityCategory.HAKI))
		{
			if(abl != null && abl instanceof ContinuousAbility)
			{
				ContinuousAbility hakiAbility = (ContinuousAbility) abl;
				
				if(!hakiAbility.isContinuous())
					continue;
				
				if(getHakiType(hakiAbility) == HakiType.HARDENING && getHakiType(ability) == HakiType.HARDENING)
					return false;
				
				if(getHakiType(hakiAbility) == HakiType.IMBUING && getHakiType(ability) == HakiType.IMBUING)
					return false;
				
				if(getHakiType(hakiAbility) == HakiType.KENBUNSHOKU && getHakiType(ability) == HakiType.KENBUNSHOKU)
					return false;
			}
		}

		return true;
	}
	
	public static HakiType getHakiType(Ability ability)
	{
		if(ability instanceof BusoshokuHakiHardeningAbility || ability instanceof BusoshokuHakiFullBodyHardeningAbility)
			return HakiType.HARDENING;
		
		if(ability instanceof BusoshokuHakiImbuingAbility)
			return HakiType.IMBUING;
		
		if(ability instanceof KenbunshokuHakiAuraAbility || ability instanceof KenbunshokuHakiFutureSightAbility)
			return HakiType.KENBUNSHOKU;
		
		return HakiType.HARDENING;
	}
	
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
	
	enum HakiType
	{
		HARDENING,
		IMBUING,
		KENBUNSHOKU
	}
}
