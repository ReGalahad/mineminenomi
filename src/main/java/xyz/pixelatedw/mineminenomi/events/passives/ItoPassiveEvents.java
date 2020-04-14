package xyz.pixelatedw.mineminenomi.events.passives;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.abilities.ito.KumoNoSugakiAbility;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class ItoPassiveEvents
{
	@SubscribeEvent
	public static void onEntityAttackEvent(LivingAttackEvent event)
	{	
		if (!(event.getEntityLiving() instanceof PlayerEntity))
			return;	
				
		PlayerEntity player = (PlayerEntity) event.getEntityLiving();
		IDevilFruit devilProps = DevilFruitCapability.get(player);
		IAbilityData abilityProps = AbilityDataCapability.get(player);
		
		if (!devilProps.getDevilFruit().equalsIgnoreCase("ito_ito"))
			return;
	
		KumoNoSugakiAbility ability = abilityProps.getEquippedAbility(KumoNoSugakiAbility.INSTANCE);
		boolean isActive = ability != null && ability.isContinuous();
		if(isActive)
			event.setCanceled(true);
	}
}
