package xyz.pixelatedw.mineminenomi.events.passives;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.player.PlayerEntity;
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
				GlStateManager.rotatef(3.0F, 0.0F, 0.0F, 1.0F);
				
				GlStateManager.scaled(size, size, size);
				GlStateManager.color4f(0.5F, 1F, 0.8F, 0.5F);
				
				GlStateManager.disableTexture();
				
				float ageInTicks = player.ticksExisted + event.getPartialRenderTick();
				float headYawOffset = WyHelper.interpolateRotation(player.prevRenderYawOffset, player.renderYawOffset, event.getPartialRenderTick());
	
				WyHelper.rotateCorpse(player, ageInTicks, headYawOffset, event.getPartialRenderTick());
	
				GlStateManager.translatef(-0.9F, -9F, 0F);
				GlStateManager.rotated(Math.toDegrees(handRenderer.rotateAngleX), 1, 0, 0);
				GlStateManager.rotated(Math.toDegrees(handRenderer.rotateAngleY), 0, 1, 0);
				GlStateManager.rotated(Math.toDegrees(handRenderer.rotateAngleZ), 0, 0, 1);
				GlStateManager.translatef(-1.1F, 4.5F, 0F);
				
				BARI_SPHERE.render(player, 0, 0, 0, 0, 0, 0.625F);
				
				GlStateManager.enableTexture();
			}
			GlStateManager.popMatrix();
		}
	}
	
	@SubscribeEvent
	public static void onHandRendering(RenderSpecificHandEvent event)
	{
		
	}
}
