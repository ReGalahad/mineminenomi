package xyz.pixelatedw.mineminenomi.events.passives;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class HoruPassiveEvents
{
	@SubscribeEvent
	public static void onEntityUpdate(LivingUpdateEvent event)
	{
		LivingEntity entity = event.getEntityLiving();

		if (!entity.isAlive())
			return;

		if (entity.isPotionActive(ModEffects.CHIYU_HORMONE))
		{
			EffectInstance effect = entity.getActivePotionEffect(ModEffects.CHIYU_HORMONE);

			if (effect.getDuration() <= 2)
				entity.addPotionEffect(new EffectInstance(Effects.HUNGER, 200, 1));
		}

		if (entity.isPotionActive(ModEffects.TENSION_HORMONE))
		{
			EffectInstance effect = entity.getActivePotionEffect(ModEffects.TENSION_HORMONE);

			if (effect.getDuration() <= 2)
				entity.addPotionEffect(new EffectInstance(Effects.NAUSEA, 400, 1));
		}
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public static void onEntityRendered(RenderLivingEvent.Post event)
	{
		if (!(event.getEntity() instanceof PlayerEntity))
			return;
		
		PlayerEntity entity = (PlayerEntity) event.getEntity();
		PlayerRenderer renderer = (PlayerRenderer) event.getRenderer();

		AbstractClientPlayerEntity abstractOwner = (AbstractClientPlayerEntity) entity;
		BipedModel model = renderer.getEntityModel();
		
		if (!entity.isPotionActive(ModEffects.GANMEN_SEICHO_HORMONE))
			return;

		if (entity.getActivePotionEffect(ModEffects.GANMEN_SEICHO_HORMONE).getDuration() <= 0)
			entity.removePotionEffect(ModEffects.GANMEN_SEICHO_HORMONE);
		
		GlStateManager.pushMatrix();
		{	
			GlStateManager.translatef((float) event.getX(), (float) event.getY() + 1.5F, (float) event.getZ());
			GlStateManager.rotatef(180.0F, 1.0F, 0.0F, 0.0F);
			GlStateManager.rotatef(180.0F, 0.0F, 1.0F, 0.0F);
			GlStateManager.scaled(1, 1, 1);
			
			float ageInTicks = entity.ticksExisted + event.getPartialRenderTick();
			float headYawOffset = WyHelper.interpolateRotation(entity.prevRenderYawOffset, entity.renderYawOffset, event.getPartialRenderTick());
			float headYaw = WyHelper.interpolateRotation(entity.prevRotationYawHead, entity.rotationYawHead, event.getPartialRenderTick());
			float headPitch = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * event.getPartialRenderTick();
			
			WyHelper.rotateCorpse(entity, ageInTicks, headYawOffset, event.getPartialRenderTick());
			float limbSwingAmount = entity.prevLimbSwingAmount + (entity.limbSwingAmount - entity.prevLimbSwingAmount) * event.getPartialRenderTick();
			float limbSwing = entity.limbSwing - entity.limbSwingAmount * (1.0F - event.getPartialRenderTick());
			
			renderer.bindTexture(renderer.getEntityTexture(abstractOwner));			
			model.swingProgress = entity.getSwingProgress(event.getPartialRenderTick());
			
			GlStateManager.scaled(3.5, 3.5, 3.5);

			model.setVisible(false);
			model.bipedHead.showModel = true;
			if (model.isSneak)
				GlStateManager.translated(0, -0.17, 0);
			else
				GlStateManager.translated(0, 0.02, 0);
			model.render(entity, limbSwing, limbSwingAmount, ageInTicks, headYaw - headYawOffset, headPitch, 0.06F);
		}
		GlStateManager.popMatrix();

	}
}
