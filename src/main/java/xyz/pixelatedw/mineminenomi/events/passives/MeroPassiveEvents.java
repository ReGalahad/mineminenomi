package xyz.pixelatedw.mineminenomi.events.passives;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.api.IHasOverlay;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;

import java.awt.*;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class MeroPassiveEvents
{
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
