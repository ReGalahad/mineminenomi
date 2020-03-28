package xyz.pixelatedw.mineminenomi.events.abilities.common;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.abilities.rokushiki.GeppoAbility;
import xyz.pixelatedw.mineminenomi.abilities.rokushiki.KamieAbility;
import xyz.pixelatedw.mineminenomi.abilities.rokushiki.RankyakuAbility;
import xyz.pixelatedw.mineminenomi.abilities.rokushiki.ShiganAbility;
import xyz.pixelatedw.mineminenomi.abilities.rokushiki.SoruAbility;
import xyz.pixelatedw.mineminenomi.abilities.rokushiki.TekkaiAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitsHelper;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.events.custom.DorikiEvent;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class AbilityProgressionEvents
{

	@SubscribeEvent
	public static void onDorikiGained(DorikiEvent event)
	{
		if (event.props.isHuman())
		{
			gainAbility(event.player, 500, SoruAbility.INSTANCE);
			gainAbility(event.player, 1500, TekkaiAbility.INSTANCE);
			gainAbility(event.player, 3000, ShiganAbility.INSTANCE);
			gainAbility(event.player, 4500, GeppoAbility.INSTANCE);
			gainAbility(event.player, 6000, KamieAbility.INSTANCE);
			gainAbility(event.player, 8500, RankyakuAbility.INSTANCE);
		}
/*		else if (event.props.isFishman())
		{
			gainAbility(event.player, 800, FishKarateAbilities.UCHIMIZU);
			gainAbility(event.player, 2000, FishKarateAbilities.MURASAME);
			gainAbility(event.player, 2500, FishKarateAbilities.KACHIAGE_HAISOKU);
			gainAbility(event.player, 3000, FishKarateAbilities.SAMEHADA_SHOTEI);
			gainAbility(event.player, 4000, HakiAbilities.KENBUNSHOKU_HAKI);
			gainAbility(event.player, 7500, FishKarateAbilities.KARAKUSAGAWARA_SEIKEN);
			gainAbility(event.player, 9000, HakiAbilities.BUSOSHOKU_HAKI);
		}
		else if (event.props.isCyborg())
		{
			gainAbility(event.player, 0, CyborgAbilities.FRESH_FIRE);
			gainAbility(event.player, 0, CyborgAbilities.COLA_OVERDRIVE);
			gainAbility(event.player, 0, CyborgAbilities.STRONG_RIGHT);
			gainAbility(event.player, 0, CyborgAbilities.RADICAL_BEAM);
			gainAbility(event.player, 0, CyborgAbilities.COUP_DE_VENT);
			gainAbility(event.player, 5500, HakiAbilities.KENBUNSHOKU_HAKI);
			gainAbility(event.player, 8500, HakiAbilities.BUSOSHOKU_HAKI);
		}*/

	}
	

	private static void gainAbility(PlayerEntity player, int doriki, Ability ability)
	{
		IEntityStats props = EntityStatsCapability.get(player);
		IAbilityData abilityProps = AbilityDataCapability.get(player);

		if (props.getDoriki() >= doriki && !abilityProps.hasUnlockedAbility(ability) && !DevilFruitsHelper.verifyIfAbilityIsBanned(ability))
			abilityProps.addUnlockedAbility(ability);
		if ((props.getDoriki() < doriki || DevilFruitsHelper.verifyIfAbilityIsBanned(ability)) && abilityProps.hasUnlockedAbility(ability))
			abilityProps.removeUnlockedAbility(ability);
		
/*		if (ability instanceof KenbunshokuHaki || ability instanceof BusoshokuHaki)
		{
			if (props.getDoriki() >= doriki && !abilityProps.hasHakiAbility(ability) && !DevilFruitsHelper.verifyIfAbilityIsBanned(ability))
				abilityProps.addHakiAbility(ability);
			if ((props.getDoriki() < doriki || DevilFruitsHelper.verifyIfAbilityIsBanned(ability)) && abilityProps.hasHakiAbility(ability))
				abilityProps.removeHakiAbility(ability);
		}
		else
		{
			
		}*/
	}
}
