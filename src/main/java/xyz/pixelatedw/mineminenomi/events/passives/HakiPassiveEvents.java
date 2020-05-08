package xyz.pixelatedw.mineminenomi.events.passives;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.GlStateManager.DestFactor;
import com.mojang.blaze3d.platform.GlStateManager.SourceFactor;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderSpecificHandEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiFullBodyHardeningAbility;
import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiHardeningAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.MorphHelper;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class HakiPassiveEvents
{
	@SubscribeEvent
	public static void onHandRendering(RenderSpecificHandEvent event)
	{
		if (event.getHand() != Hand.MAIN_HAND)
			return;

		if (!event.getItemStack().isEmpty())
			return;

		PlayerEntity player = Minecraft.getInstance().player;

		IAbilityData abilityProps = AbilityDataCapability.get(player);

		BusoshokuHakiHardeningAbility hardening = abilityProps.getEquippedAbility(BusoshokuHakiHardeningAbility.INSTANCE);
		BusoshokuHakiFullBodyHardeningAbility fullBodyHardening = abilityProps.getEquippedAbility(BusoshokuHakiFullBodyHardeningAbility.INSTANCE);

		boolean hasHardeningActive = hardening != null && hardening.isContinuous();
		boolean hasFullBodyActive = fullBodyHardening != null && fullBodyHardening.isContinuous();
		
		if (hasHardeningActive || hasFullBodyActive)
		{
			event.setCanceled(true);
			MorphHelper.renderArmFirstPerson(event.getEquipProgress(), event.getSwingProgress(), HandSide.RIGHT, ModResources.BUSOSHOKU_HAKI_ARM);		
		}
	}

	@SubscribeEvent
	public static void onEntityRendered(RenderLivingEvent.Post event)
	{
		if (!(event.getEntity() instanceof PlayerEntity))
			return;

		PlayerEntity player = (PlayerEntity) event.getEntity();
		LivingRenderer renderer = event.getRenderer();

		IAbilityData abilityProps = AbilityDataCapability.get(player);

		BusoshokuHakiHardeningAbility hardening = abilityProps.getEquippedAbility(BusoshokuHakiHardeningAbility.INSTANCE);

		if (hardening != null && hardening.isContinuous() && renderer.getEntityModel() instanceof IHasArm)
			MorphHelper.renderArmThirdPerson(event, player, ModResources.BUSOSHOKU_HAKI_ARM);
		
		BusoshokuHakiFullBodyHardeningAbility fullBodyHardening = abilityProps.getEquippedAbility(BusoshokuHakiFullBodyHardeningAbility.INSTANCE);
		
		if (fullBodyHardening != null && fullBodyHardening.isContinuous())
		{
			GlStateManager.pushMatrix();
			{
				GlStateManager.translatef((float) event.getX(), (float) event.getY() + 1.42F, (float) event.getZ());
	
				GlStateManager.enableBlend();
				GlStateManager.disableCull();
				GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
	
				GlStateManager.color4f(1.0F, 1.0F, 1.0F, 0.5F);
				
				GlStateManager.rotatef(180.0F, 1.0F, 0.0F, 0.0F);
				GlStateManager.rotatef(180.0F, 0.0F, 1.0F, 0.0F);
	
				GlStateManager.scaled(1.05, 1.04, 1.05);
	
				float ageInTicks = player.ticksExisted + event.getPartialRenderTick();
				float headYawOffset = WyHelper.interpolateRotation(player.prevRenderYawOffset, player.renderYawOffset, event.getPartialRenderTick());
				float headYaw = WyHelper.interpolateRotation(player.prevRotationYawHead, player.rotationYawHead, event.getPartialRenderTick());
				float headPitch = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * event.getPartialRenderTick();
	
				WyHelper.rotateCorpse(player, ageInTicks, headYawOffset, event.getPartialRenderTick());
				float limbSwingAmount = player.prevLimbSwingAmount + (player.limbSwingAmount - player.prevLimbSwingAmount) * event.getPartialRenderTick();
				float limbSwing = player.limbSwing - player.limbSwingAmount * (1.0F - event.getPartialRenderTick());
	
				Minecraft.getInstance().getTextureManager().bindTexture(ModResources.BUSOSHOKU_HAKI_ARM);
				renderer.getEntityModel().swingProgress = player.getSwingProgress(event.getPartialRenderTick());
				renderer.getEntityModel().render(player, limbSwing, limbSwingAmount, ageInTicks, headYaw - headYawOffset, headPitch, 0.055F);
	
				GlStateManager.enableCull();
				GlStateManager.disableBlend();
			}
			GlStateManager.popMatrix();
		}
	}
}
