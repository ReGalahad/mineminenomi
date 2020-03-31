package xyz.pixelatedw.mineminenomi.events.passives;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.abilities.sniper.KaenBoshiAbility;
import xyz.pixelatedw.mineminenomi.abilities.sniper.KemuriBoshiAbility;
import xyz.pixelatedw.mineminenomi.abilities.sniper.RenpatsuNamariBoshiAbility;
import xyz.pixelatedw.mineminenomi.abilities.sniper.SakuretsuSabotenBoshiAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.ISniperAbility;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class SniperPassiveEvents
{
	private static final List<Ability> SNIPER_ABILITIES = Lists.newArrayList(KaenBoshiAbility.INSTANCE, KemuriBoshiAbility.INSTANCE, RenpatsuNamariBoshiAbility.INSTANCE, SakuretsuSabotenBoshiAbility.INSTANCE);

	@SubscribeEvent
	public static void onEntityShootProjectile(ArrowLooseEvent event)
	{
		if (event.getPlayer() != null)
		{
			IAbilityData props = AbilityDataCapability.get(event.getPlayer());
			
			SNIPER_ABILITIES.forEach(ability -> 
			{
				if(ability instanceof ISniperAbility && props.hasEquippedAbility(ability) && props.getEquippedAbility(ability).isContinuous())
				{			
					((ISniperAbility)props.getEquippedAbility(ability)).shoot(event.getPlayer());
					props.getEquippedAbility(ability).use(event.getPlayer());
					event.setCanceled(true);
				}
			});
		}
	}
}
