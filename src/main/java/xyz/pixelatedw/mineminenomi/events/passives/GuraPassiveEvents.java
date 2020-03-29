package xyz.pixelatedw.mineminenomi.events.passives;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.abilities.gura.ShimaYurashiAbility;
import xyz.pixelatedw.mineminenomi.abilities.gura.TenchiMeidoAbility;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class GuraPassiveEvents
{
	@SubscribeEvent
	public static void onEntityUpdate(LivingUpdateEvent event)
	{
		if (!(event.getEntityLiving() instanceof PlayerEntity))
			return;	
		
		PlayerEntity player = (PlayerEntity) event.getEntityLiving();
		IDevilFruit devilFruitProps = DevilFruitCapability.get(player);
		IAbilityData abilityProps = AbilityDataCapability.get(player);
				
		if (!devilFruitProps.getDevilFruit().equals("gura_gura"))
			return;
	
		Ability shimaYurashiAbility = abilityProps.getEquippedAbility(ShimaYurashiAbility.INSTANCE);
		boolean isShimaYurashiActive = shimaYurashiAbility != null && shimaYurashiAbility.isCharging();
		Ability tenchiMeidoAbility = abilityProps.getEquippedAbility(TenchiMeidoAbility.INSTANCE);
		boolean isTenchiMeidoActive = tenchiMeidoAbility != null && tenchiMeidoAbility.isCharging();
		if(isShimaYurashiActive || isTenchiMeidoActive)
			player.setMotion(0, -5, 0);
	}
}
