package xyz.pixelatedw.mineminenomi.events.items;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.models.SphereModel;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class BubblyCoralEvents
{
	@OnlyIn(Dist.CLIENT)
	private static final SphereModel SPHERE = new SphereModel();

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

		GlStateManager.pushMatrix();
		{
			GlStateManager.translatef((float) event.getX(), (float) event.getY() + 1.45F, (float) event.getZ());

			Minecraft.getInstance().textureManager.bindTexture(ModResources.BUBBLY_CORAL);
			
			GlStateManager.rotatef(180.0F, 1.0F, 0.0F, 0.0F);
			GlStateManager.rotatef(180.0F, 0.0F, 1.0F, 0.0F);

			GlStateManager.scaled(1.0, 1.0, 1.0);

			float ageInTicks = entity.ticksExisted + event.getPartialRenderTick();
			float headYawOffset = WyHelper.interpolateRotation(entity.prevRenderYawOffset, entity.renderYawOffset, event.getPartialRenderTick());
			float headYaw = WyHelper.interpolateRotation(entity.prevRotationYawHead, entity.rotationYawHead, event.getPartialRenderTick());
			float headPitch = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * event.getPartialRenderTick();

			WyHelper.rotateCorpse(entity, ageInTicks, headYawOffset, event.getPartialRenderTick());
			float limbSwingAmount = entity.prevLimbSwingAmount + (entity.limbSwingAmount - entity.prevLimbSwingAmount) * event.getPartialRenderTick();
			float limbSwing = entity.limbSwing - entity.limbSwingAmount * (1.0F - event.getPartialRenderTick());

			renderer.getEntityModel().render(entity, limbSwing, limbSwingAmount, ageInTicks, headYaw - headYawOffset, headPitch, 0.0625F);
		}
		GlStateManager.popMatrix();

	}
}