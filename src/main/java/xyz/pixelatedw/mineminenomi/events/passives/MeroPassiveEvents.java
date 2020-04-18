package xyz.pixelatedw.mineminenomi.events.passives;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.api.IHasOverlay;
import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class MeroPassiveEvents
{

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public static void onEntityRendered(RenderLivingEvent.Post event)
	{
		LivingEntity entity = event.getEntity();
		LivingRenderer renderer = event.getRenderer();

		if (!entity.isPotionActive(ModEffects.LOVESTRUCK))
			return;

		if (entity.getActivePotionEffect(ModEffects.LOVESTRUCK).getDuration() <= 0)
			entity.removePotionEffect(ModEffects.LOVESTRUCK);

		RendererHelper.renderEffectOverlay((float) event.getX(), (float) event.getY(), (float) event.getZ(), event.getPartialRenderTick(), entity, renderer, (IHasOverlay) ModEffects.LOVESTRUCK);
	}
	
	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public static void onFirstPersonViewRendered(TickEvent.RenderTickEvent event)
	{
		Minecraft mc = Minecraft.getInstance();
		PlayerEntity player = mc.player;

		if (player == null)
			return;
		
		if (!player.isPotionActive(ModEffects.LOVESTRUCK))
			return;

		if (player.getActivePotionEffect(ModEffects.LOVESTRUCK).getDuration() <= 0)
			player.removePotionEffect(ModEffects.LOVESTRUCK);
		
		float[] colors = ((IHasOverlay) ModEffects.LOVESTRUCK).getOverlayColor();
		Color color = new Color(colors[0], colors[1], colors[2]);
		WyHelper.drawColourOnScreen(color.getRGB(), 200, 0, 0, mc.mainWindow.getScaledWidth(), mc.mainWindow.getScaledHeight(), 500);
	}
}
