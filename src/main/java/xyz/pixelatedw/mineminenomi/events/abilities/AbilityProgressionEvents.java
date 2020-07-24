package xyz.pixelatedw.mineminenomi.events.abilities;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.abilities.cyborg.ColaOverdriveAbility;
import xyz.pixelatedw.mineminenomi.abilities.cyborg.CoupDeVentAbility;
import xyz.pixelatedw.mineminenomi.abilities.cyborg.FreshFireAbility;
import xyz.pixelatedw.mineminenomi.abilities.cyborg.RadicalBeamAbility;
import xyz.pixelatedw.mineminenomi.abilities.cyborg.StrongRightAbility;
import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.KachiageHaisokuAbility;
import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.KarakusagawaraSeikenAbility;
import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.MurasameAbility;
import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.SamehadaShoteiAbility;
import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.UchimizuAbility;
import xyz.pixelatedw.mineminenomi.abilities.rokushiki.GeppoAbility;
import xyz.pixelatedw.mineminenomi.abilities.rokushiki.KamieAbility;
import xyz.pixelatedw.mineminenomi.abilities.rokushiki.RankyakuAbility;
import xyz.pixelatedw.mineminenomi.abilities.rokushiki.ShiganAbility;
import xyz.pixelatedw.mineminenomi.abilities.rokushiki.SoruAbility;
import xyz.pixelatedw.mineminenomi.abilities.rokushiki.TekkaiAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.events.custom.DorikiEvent;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.wypi.network.WyNetwork;
import xyz.pixelatedw.wypi.network.packets.server.SSyncAbilityDataPacket;

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
		else if (event.props.isFishman())
		{
			gainAbility(event.player, 800, UchimizuAbility.INSTANCE);
			gainAbility(event.player, 1500, KachiageHaisokuAbility.INSTANCE);
			gainAbility(event.player, 3000, SamehadaShoteiAbility.INSTANCE);
			gainAbility(event.player, 5000, MurasameAbility.INSTANCE);
			gainAbility(event.player, 7500, KarakusagawaraSeikenAbility.INSTANCE);
		}
		else if (event.props.isCyborg())
		{
			gainAbility(event.player, 0, FreshFireAbility.INSTANCE);
			gainAbility(event.player, 0, ColaOverdriveAbility.INSTANCE);
			gainAbility(event.player, 0, StrongRightAbility.INSTANCE);
			gainAbility(event.player, 0, RadicalBeamAbility.INSTANCE);
			gainAbility(event.player, 0, CoupDeVentAbility.INSTANCE);
		}
	}

	private static void gainAbility(PlayerEntity player, int doriki, Ability ability)
	{
		IEntityStats props = EntityStatsCapability.get(player);
		IAbilityData abilityProps = AbilityDataCapability.get(player);

		if (props.getDoriki() >= doriki && !abilityProps.hasUnlockedAbility(ability) && !AbilityHelper.verifyIfAbilityIsBanned(ability))
			abilityProps.addUnlockedAbility(ability);
		if ((props.getDoriki() < doriki || AbilityHelper.verifyIfAbilityIsBanned(ability)) && abilityProps.hasUnlockedAbility(ability))
			abilityProps.removeUnlockedAbility(ability);
		
		WyNetwork.sendTo(new SSyncAbilityDataPacket(player.getEntityId(), abilityProps), player);
	}
}
