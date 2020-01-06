package xyz.pixelatedw.mineminenomi.renderers;

import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.platform.GLX;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.HandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.IAbilityData;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.entities.zoan.ZoanInfo;
import xyz.pixelatedw.mineminenomi.helpers.DevilFruitsHelper;
import xyz.pixelatedw.mineminenomi.helpers.MorphsHelper;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.renderers.entities.ZoanMorphRenderer;

@OnlyIn(Dist.CLIENT)
public class ZoanFirstPersonRenderer
{
	@SuppressWarnings("resource")
	public static void renderArmFirstPerson(float equippedProgress, float swingProgress, HandSide side)
	{
		Minecraft mc = Minecraft.getInstance();
		EntityRendererManager renderManager = mc.getRenderManager();
		ItemRenderer itemRenderer = mc.getItemRenderer();
		IAbilityData props = AbilityDataCapability.get(mc.player);
		IDevilFruit dfProps = DevilFruitCapability.get(mc.player);

		GlStateManager.pushMatrix();
		{
			boolean flag = side != HandSide.LEFT;
			float f = flag ? 1.0F : -1.0F;
			float f1 = MathHelper.sqrt(swingProgress);
			float f2 = -0.3F * MathHelper.sin(f1 * (float) Math.PI);
			float f3 = 0.4F * MathHelper.sin(f1 * ((float) Math.PI * 2F));
			float f4 = -0.4F * MathHelper.sin(swingProgress * (float) Math.PI);
			GlStateManager.translatef(f * (f2 + 0.64000005F), f3 + -0.6F + equippedProgress * -0.6F, f4 + -0.71999997F);
			GlStateManager.rotatef(f * 45.0F, 0.0F, 1.0F, 0.0F);
			float f5 = MathHelper.sin(swingProgress * swingProgress * (float) Math.PI);
			float f6 = MathHelper.sin(f1 * (float) Math.PI);
			GlStateManager.rotatef(f * f6 * 70.0F, 0.0F, 1.0F, 0.0F);
			GlStateManager.rotatef(f * f5 * -20.0F, 0.0F, 0.0F, 1.0F);
			AbstractClientPlayerEntity clientPlayer = mc.player;
			// mc.getTextureManager().bindTexture(abstractclientplayerentity.getLocationSkin());
			
			if (props.isPassiveActive(ModAttributes.BUSOSHOKU_HAKI))
				mc.getTextureManager().bindTexture(ModResources.BUSOSHOKU_HAKI_ARM);
			else if (props.isPassiveActive(ModAttributes.HOT_BOILING_SPECIAL))
				mc.getTextureManager().bindTexture(ModResources.HOT_BOILING_SPECIAL_ARM);
			else
				mc.getTextureManager().bindTexture(getTextureFromMorph(mc.player, renderManager));
	
			GlStateManager.translatef(f * -1.0F, 3.6F, 3.5F);
			GlStateManager.rotatef(f * 120.0F, 0.0F, 0.0F, 1.0F);
			GlStateManager.rotatef(200.0F, 1.0F, 0.0F, 0.0F);
			GlStateManager.rotatef(f * -135.0F, 0.0F, 1.0F, 0.0F);
			GlStateManager.translatef(f * 5.6F, 0.0F, 0.0F);
			PlayerRenderer playerrenderer = renderManager.getRenderer(clientPlayer);
			GlStateManager.disableCull();
	
			boolean flag2 = mc.getRenderViewEntity() instanceof LivingEntity && ((LivingEntity)mc.getRenderViewEntity()).isSleeping();
			if (mc.gameSettings.thirdPersonView != 0 || flag2 || mc.gameSettings.hideGUI)
				return;
				
			RenderHelper.enableStandardItemLighting();
			//mc.gameRenderer.enableLightmap();

			if (WyHelper.isNullOrEmpty(dfProps.getZoanPoint()))
			{
				if (flag)
					playerrenderer.renderRightArm(clientPlayer);
				else
					playerrenderer.renderLeftArm(clientPlayer);
			}
			else
			{
				ZoanMorphRenderer render = MorphsHelper.getZoanInfoList().get(0).getFactory().createRenderFor(renderManager);
	
				ZoanInfo info = DevilFruitsHelper.getZoanInfo(clientPlayer);
				if(info != null)
					render = info.getFactory().createRenderFor(renderManager);
	
				if (render != null)
				{
					ZoanMorphRenderer renderZoan = render;
					float i = 1.0F;
					GL11.glColor3f(1, 1, 1);
					GL11.glScalef(i, i, i);
					GL11.glRotatef(60.0F, 0.0F, 1.0F, 0.0F);
					GL11.glTranslatef(0.2f, 0.0f, -0.5f);
					renderZoan.renderFirstPersonArm(mc.player);
				}
			}
			
			RenderHelper.disableStandardItemLighting();
			//mc.gameRenderer.disableLightmap();
	
			GlStateManager.enableCull();
		}
		GlStateManager.popMatrix();
	}

	private static ResourceLocation getTextureFromMorph(ClientPlayerEntity player, EntityRendererManager renderManager)
	{
		IDevilFruit props = DevilFruitCapability.get(player);
		IAbilityData abilityProps = AbilityDataCapability.get(player);
		ZoanMorphRenderer render = null;

		ZoanInfo info = DevilFruitsHelper.getZoanInfo(player);
		if(info != null)
			render = info.getFactory().createRenderFor(renderManager);

		if (render != null)
			return render.getEntityTexture(null);

		return player.getLocationSkin();
	}

	@SuppressWarnings("resource")
	private static void setLightmap()
	{
		Minecraft mc = Minecraft.getInstance();
		AbstractClientPlayerEntity abstractclientplayerentity = mc.player;
		int i = mc.world.getCombinedLight(new BlockPos(abstractclientplayerentity.posX, abstractclientplayerentity.posY + abstractclientplayerentity.getEyeHeight(), abstractclientplayerentity.posZ), 0);
		float f = i & '\uffff';
		float f1 = i >> 16;
		GLX.glMultiTexCoord2f(GLX.GL_TEXTURE1, f, f1);
	}
}
