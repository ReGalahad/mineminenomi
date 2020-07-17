package xyz.pixelatedw.mineminenomi.api.helpers;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.api.abilities.IHakiAbility;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
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
		if(ability instanceof IHakiAbility)
			return ((IHakiAbility)ability).getType();

		return HakiType.HARDENING;
	}
	
	public static float getTotalHakiExp(PlayerEntity player)
	{
		IHakiData props = HakiDataCapability.get(player);
		return props.getKenbunshokuHakiExp() + props.getBusoshokuHardeningHakiExp() + props.getBusoshokuImbuingHakiExp();
	}
	
	public static boolean checkForHakiOveruse(PlayerEntity player, int passiveTimer)
	{
		IEntityStats props = EntityStatsCapability.get(player);
		if (passiveTimer > 2400 + (props.getDoriki() / 15))
		{
			player.addPotionEffect(new EffectInstance(Effects.NAUSEA, 100, 0));
			player.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 100, 0));

			if(passiveTimer > 3600 + (props.getDoriki() / 15))
			{
				player.addPotionEffect(new EffectInstance(Effects.NAUSEA, 1000, 5));
				player.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 1000, 5));
				player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 1000, 5));
				player.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 1000, 5));
				return true;
			}

		}
		return false;
	}
	
	public static enum HakiType
	{
		HARDENING,
		IMBUING,
		KENBUNSHOKU
	}
}
