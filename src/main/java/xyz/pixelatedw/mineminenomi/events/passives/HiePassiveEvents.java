package xyz.pixelatedw.mineminenomi.events.passives;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.GlStateManager.DestFactor;
import com.mojang.blaze3d.platform.GlStateManager.SourceFactor;

import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class HiePassiveEvents
{

	@SubscribeEvent
	public static void onEntityUpdate(LivingUpdateEvent event)
	{
		if (!(event.getEntityLiving() instanceof PlayerEntity))
			return;	

		PlayerEntity player = (PlayerEntity) event.getEntityLiving();
		IDevilFruit devilFruitProps = DevilFruitCapability.get(player);
				
		if (!devilFruitProps.getDevilFruit().equals("hie_hie"))
			return;
				
		if (!AbilityHelper.isNearbyKairoseki(player) && (player.getHealth() > player.getMaxHealth() / 5 || player.abilities.isCreativeMode))
			AbilityHelper.createFilledSphere(player.world, (int) player.posX - 1, (int) player.posY, (int) player.posZ - 1, 2, Blocks.ICE, "liquid");
	}
	
	
	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public static void onEntityRendered(RenderLivingEvent.Post event)
	{
		LivingEntity entity = event.getEntity();
		LivingRenderer renderer = event.getRenderer();

		if (!entity.isPotionActive(ModEffects.FROZEN))
			return;

		if (entity.getActivePotionEffect(ModEffects.FROZEN).getDuration() <= 0)
			entity.removePotionEffect(ModEffects.FROZEN);

		GlStateManager.pushMatrix();
		{
			GlStateManager.translatef((float) event.getX(), (float) event.getY() + 1.5F, (float) event.getZ());

			GlStateManager.disableTexture();
			GlStateManager.enableBlend();
			GlStateManager.disableCull();
			GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);

			GlStateManager.color4f(0.3f,0.92f, 0.87f, 0.9f);

			GlStateManager.rotatef(180.0F, 1.0F, 0.0F, 0.0F);
			GlStateManager.rotatef(180.0F, 0.0F, 1.0F, 0.0F);

			GlStateManager.scaled(1.05, 1.04, 1.05);

			float ageInTicks = entity.ticksExisted + event.getPartialRenderTick();
			float headYawOffset = WyHelper.interpolateRotation(entity.prevRenderYawOffset, entity.renderYawOffset, event.getPartialRenderTick());
			float headYaw = WyHelper.interpolateRotation(entity.prevRotationYawHead, entity.rotationYawHead, event.getPartialRenderTick());
			float headPitch = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * event.getPartialRenderTick();

			WyHelper.rotateCorpse(entity, ageInTicks, headYawOffset, event.getPartialRenderTick());
			float limbSwingAmount = entity.prevLimbSwingAmount + (entity.limbSwingAmount - entity.prevLimbSwingAmount) * event.getPartialRenderTick();
			float limbSwing = entity.limbSwing - entity.limbSwingAmount * (1.0F - event.getPartialRenderTick());

			renderer.getEntityModel().swingProgress = entity.getSwingProgress(event.getPartialRenderTick());
			renderer.getEntityModel().render(entity, limbSwing, limbSwingAmount, ageInTicks, headYaw - headYawOffset, headPitch, 0.06F);

			GlStateManager.enableTexture();
			GlStateManager.enableCull();
			GlStateManager.disableBlend();
		}
		GlStateManager.popMatrix();
	}
}
