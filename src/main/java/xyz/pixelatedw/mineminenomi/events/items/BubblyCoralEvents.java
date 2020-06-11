package xyz.pixelatedw.mineminenomi.events.items;

import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.wypi.APIConfig;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class BubblyCoralEvents
{
	@SubscribeEvent
	public static void onBubblyCoralCheck(LivingUpdateEvent event)
	{
		if (event.getEntityLiving().isPotionActive(ModEffects.BUBBLY_CORAL))
		{
			event.getEntityLiving().setAir(event.getEntityLiving().getMaxAir());
		}
	}

	@SubscribeEvent
	public static void livingDamage(LivingDamageEvent event) {
		if (event.getEntityLiving().isPotionActive(ModEffects.BUBBLY_CORAL))
		{
			event.getEntityLiving().removePotionEffect(ModEffects.BUBBLY_CORAL);
		}
	}

}