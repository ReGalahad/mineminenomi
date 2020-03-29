package xyz.pixelatedw.mineminenomi.events.abilities;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.SamehadaShoteiAbility;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class FishmanKaratePassiveEvents
{
	@SubscribeEvent
	public static void onEntityUpdate(LivingUpdateEvent event)
	{
		if (!(event.getEntityLiving() instanceof PlayerEntity))
			return;	
		
		PlayerEntity player = (PlayerEntity) event.getEntityLiving();
		IEntityStats statsProps = EntityStatsCapability.get(player);
		IAbilityData abilityProps = AbilityDataCapability.get(player);
		
		if (!statsProps.isFishman())
			return;

		Ability samehadaAbility = abilityProps.getEquippedAbility(SamehadaShoteiAbility.INSTANCE);
		boolean isSamehadaActive = samehadaAbility != null && samehadaAbility.isContinuous();
		if(isSamehadaActive)
			player.setMotion(0, -5, 0);
	}
}
