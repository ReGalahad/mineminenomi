package xyz.pixelatedw.mineminenomi.events.passives;

import java.awt.Color;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.config.GuiUtils;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class CyborgPassiveEvents
{
	@SuppressWarnings("resource")
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onRenderOverlay(RenderGameOverlayEvent event)
	{
		Minecraft mc = Minecraft.getInstance();
		PlayerEntity player = mc.player;
		IEntityStats entityStatsProps = EntityStatsCapability.get(player);

		if (!entityStatsProps.isCyborg())
			return;
				
		int posX = mc.mainWindow.getScaledWidth();
		int posY = mc.mainWindow.getScaledHeight();

		if (event.getType() == ElementType.HOTBAR)
		{
			mc.getTextureManager().bindTexture(ModResources.WIDGETS);
			
			GlStateManager.enableBlend();
			
			GuiUtils.drawTexturedModalRect((posX - 260) / 2, posY - 42, 0, 52, 23, 40, 0);
			int barHeight = (int) (((float) entityStatsProps.getCola() / entityStatsProps.getMaxCola()) * 30) + 23;
	
			if (barHeight > 0 && barHeight < 24)
				barHeight = 24;
			else if (barHeight > 52)
				barHeight = 52;
	
			GuiUtils.drawTexturedModalRect((posX - 252) / 2, posY - 42, 32, barHeight, 16, 32, 0);
			WyHelper.centerString(Minecraft.getInstance().fontRenderer, entityStatsProps.getCola() + "", (posX - 237) / 2, posY - 12);
			WyHelper.drawStringWithBorder(Minecraft.getInstance().fontRenderer, entityStatsProps.getCola() + "", (posX - 237) / 2, posY - 12, Color.WHITE.getRGB());
			
			GlStateManager.disableBlend();
		}
	}
	
}
