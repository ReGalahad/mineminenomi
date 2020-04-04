package xyz.pixelatedw.mineminenomi.events.passives;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.GlStateManager.DestFactor;
import com.mojang.blaze3d.platform.GlStateManager.SourceFactor;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderSpecificHandEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.abilities.bari.BariBariNoPistolAbility;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.models.SphereModel;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID, value = Dist.CLIENT)
public class BariPassiveEvents
{
	private static final SphereModel BARI_SPHERE = new SphereModel();

	@SubscribeEvent
	public static void onEntityRendered(RenderLivingEvent.Post event)
	{
		if (!(event.getEntity() instanceof PlayerEntity))
			return;	
		
		PlayerEntity player = (PlayerEntity) event.getEntity();
		LivingRenderer renderer = event.getRenderer();
		
		IDevilFruit devilFruitProps = DevilFruitCapability.get(player);
		IAbilityData abilityProps = AbilityDataCapability.get(player);
		
		if (!devilFruitProps.getDevilFruit().equals("bari_bari"))
			return;

		BariBariNoPistolAbility ability = abilityProps.getEquippedAbility(BariBariNoPistolAbility.INSTANCE);

		if (ability != null && ability.isContinuous())
		{
			RendererModel handRenderer = renderer.getEntityModel().boxList.get(3);
			
			GlStateManager.pushMatrix();
			{
				GlStateManager.translatef((float) event.getX(), (float) event.getY(), (float) event.getZ());
	
				double size = 0.16;
	
				GlStateManager.rotatef(180.0F, 1.0F, 0.0F, 0.0F);
				GlStateManager.rotatef(180.0F, 0.0F, 1.0F, 0.0F);
				
				GlStateManager.scaled(size, size, size);
				GlStateManager.color4f(0.5F, 1F, 0.8F, 0.5F);
				
				GlStateManager.disableTexture();
				
				float ageInTicks = player.ticksExisted + event.getPartialRenderTick();
				float headYawOffset = WyHelper.interpolateRotation(player.prevRenderYawOffset, player.renderYawOffset, event.getPartialRenderTick());
	
				WyHelper.rotateCorpse(player, ageInTicks, headYawOffset, event.getPartialRenderTick());
	
				GlStateManager.translatef(-1.8F, -8.5F, 0F);
				GlStateManager.rotated(Math.toDegrees(handRenderer.rotateAngleX), 1, 0, 0);
				GlStateManager.rotated(Math.toDegrees(handRenderer.rotateAngleY), 0, 1, 1);
				GlStateManager.rotated(Math.toDegrees(handRenderer.rotateAngleZ), 0, -1, 1);
				GlStateManager.translatef(-0.4F, 4F, 0F);
				
				BARI_SPHERE.render(player, 0, 0, 0, 0, 0, 0.625F);
				
				GlStateManager.enableTexture();
			}
			GlStateManager.popMatrix();
		}
	}
	
	@SubscribeEvent
	public static void onHandRendering(RenderSpecificHandEvent event)
	{
		if(event.getHand() != Hand.MAIN_HAND)
			return;
		
		if(!event.getItemStack().isEmpty())
			return;	
		
		PlayerEntity player = Minecraft.getInstance().player;
		
		IDevilFruit devilFruitProps = DevilFruitCapability.get(player);
		IAbilityData abilityProps = AbilityDataCapability.get(player);
		
		if (!devilFruitProps.getDevilFruit().equals("bari_bari"))
			return;
		
		BariBariNoPistolAbility ability = abilityProps.getEquippedAbility(BariBariNoPistolAbility.INSTANCE);

		if (ability != null && ability.isContinuous())
		{
			event.setCanceled(true);		
			
			AbstractClientPlayerEntity abstractPlayer = (AbstractClientPlayerEntity) player;	
			Minecraft.getInstance().getFirstPersonRenderer().renderItemInFirstPerson(abstractPlayer, event.getPartialTicks(), event.getInterpolatedPitch(), event.getHand(), event.getSwingProgress(), event.getItemStack(), event.getEquipProgress());		

			GlStateManager.enableBlend();
			GlStateManager.disableCull();
			GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);

			GlStateManager.pushMatrix();
			{
				GlStateManager.translatef(-0.22F, -1.05F, -0.6F);
	
				double size = 0.2;
								
				GlStateManager.disableTexture();

				GlStateManager.scaled(size, size, size);
				GlStateManager.color4f(0.5F, 1F, 0.8F, 0.6F);

				GlStateManager.translatef(3F, 0F, 3F);
				
				float f = 1;
				float f1 = MathHelper.sqrt(event.getSwingProgress());
				float f2 = -0.3F * MathHelper.sin(f1 * (float) Math.PI);
				float f3 = 0.4F * MathHelper.sin(f1 * ((float) Math.PI * 2F));
				float f4 = -0.4F * MathHelper.sin(event.getSwingProgress() * (float) Math.PI);
				GlStateManager.translatef(f * (f2 + 0.34F), f3 + 0.5F + event.getEquipProgress() * -0.6F, f4 + -0.20F);
				GlStateManager.rotatef(f * 40.0F, 0.0F, 1.0F, 0.0F);
				
				float f5 = MathHelper.sin(event.getSwingProgress() * event.getSwingProgress() * (float) Math.PI);
				float f6 = MathHelper.sin(f1 * (float) Math.PI);
				GlStateManager.rotatef(f6 * 40.0F, 0.0F, 1.0F, 0.0F);
				GlStateManager.rotatef(f5 * -20.0F, 0.0F, 0.0F, 1.0F);

				GlStateManager.rotatef(120.0F, 0.0F, 0.0F, 1.0F);
				GlStateManager.rotatef(200.0F, 1.0F, 0.0F, 0.0F);
				GlStateManager.rotatef(-135.0F, 0.0F, 1.0F, 0.0F);
				GlStateManager.translatef(0.8F, 6.5F, -0.7F);
				
				BARI_SPHERE.render(player, 0, 0, 0, 0, 0, 0.625F);

				GlStateManager.enableTexture();
			}
			GlStateManager.popMatrix();

			GlStateManager.enableCull();
			GlStateManager.disableBlend();
		}
	}
}
