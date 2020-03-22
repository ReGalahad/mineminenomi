package xyz.pixelatedw.mineminenomi.events.items;

import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderLivingEvent;
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
	@OnlyIn(Dist.CLIENT)
	public static void onEntityRendered(RenderLivingEvent.Pre event)
	{
		LivingEntity entity = event.getEntity();
		LivingRenderer renderer = event.getRenderer();

		if (!entity.isPotionActive(ModEffects.BUBBLY_CORAL))
			return;

		if (entity.getActivePotionEffect(ModEffects.BUBBLY_CORAL).getDuration() <= 0)
			entity.removePotionEffect(ModEffects.BUBBLY_CORAL);

		System.out.println("@@");
	}
}