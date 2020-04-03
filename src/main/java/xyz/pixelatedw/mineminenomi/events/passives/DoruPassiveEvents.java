package xyz.pixelatedw.mineminenomi.events.passives;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.models.effects.CandleLockModel;
import xyz.pixelatedw.wypi.APIConfig;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID, value = Dist.CLIENT)
public class DoruPassiveEvents
{
	private static final CandleLockModel CANDLE_LOCK = new CandleLockModel();

	@SubscribeEvent
	public static void onEntityRendered(RenderLivingEvent.Pre event)
	{
		LivingEntity entity = event.getEntity();
		LivingRenderer renderer = event.getRenderer();

		if (!entity.isPotionActive(ModEffects.CANDLE_LOCK))
			return;

		if (entity.getActivePotionEffect(ModEffects.CANDLE_LOCK).getDuration() <= 0)
			entity.removePotionEffect(ModEffects.CANDLE_LOCK);

		GlStateManager.pushMatrix();
		{
			GlStateManager.translatef((float) event.getX(), (float) event.getY() - 0.8F, (float) event.getZ());

			Minecraft.getInstance().textureManager.bindTexture(new ResourceLocation(APIConfig.PROJECT_ID, "textures/models/zoanmorph/candlelock.png"));

			GlStateManager.rotatef(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * event.getPartialRenderTick() - 180, 0.0F, 1.0F, 0.0F);

			GlStateManager.scaled(0.1, 0.1, 0.15);

			CANDLE_LOCK.render(entity, 0, 0, 0, 0, 0, 0.625F);
		}
		GlStateManager.popMatrix();
	}

}
