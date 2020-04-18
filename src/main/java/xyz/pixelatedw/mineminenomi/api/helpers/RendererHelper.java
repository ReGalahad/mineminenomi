package xyz.pixelatedw.mineminenomi.api.helpers;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.GlStateManager.DestFactor;
import com.mojang.blaze3d.platform.GlStateManager.SourceFactor;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import xyz.pixelatedw.mineminenomi.api.IHasOverlay;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;

public class RendererHelper
{
	public static void drawAbilityIcon(String iconName, int x, int y, int u, int v)
	{
		drawAbilityIcon(iconName, x, y, 1, u, v);
	}
	
	public static void drawAbilityIcon(String iconName, int x, int y, int z, int u, int v)
	{
        Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation(APIConfig.PROJECT_ID, "textures/abilities/" + WyHelper.getResourceName(iconName) + ".png"));        
		BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.pos(x			, y + v			, z).tex(0.0, 1.0).endVertex();
		bufferbuilder.pos(x + u		, y + v			, z).tex(1.0, 1.0).endVertex();
		bufferbuilder.pos(x + u		, y        		, z).tex(1.0, 0.0).endVertex();
		bufferbuilder.pos(x			, y				, z).tex(0.0, 0.0).endVertex();
		Tessellator.getInstance().draw();   
	}
	
	public static void drawDevilFruitIcon(String iconName, int x, int y, int u, int v)
	{
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation(APIConfig.PROJECT_ID, "textures/items/" + WyHelper.getResourceName(iconName) + ".png"));        
		BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.pos(x			, y + v			, 1).tex(0.0, 1.0).endVertex();
		bufferbuilder.pos(x + u		, y + v			, 1).tex(1.0, 1.0).endVertex();
		bufferbuilder.pos(x + u		, y        		, 1).tex(1.0, 0.0).endVertex();
		bufferbuilder.pos(x			, y				, 1).tex(0.0, 0.0).endVertex();
		Tessellator.getInstance().draw();
	}
	
	public static void renderEffectOverlay(float posX, float posY, float posZ, float partialTicks, LivingEntity entity, LivingRenderer renderer, IHasOverlay effect)
	{
		GlStateManager.pushMatrix();
		{
			GlStateManager.translatef(posX, posY + 1.5F, posZ);

			GlStateManager.disableTexture();
			GlStateManager.enableBlend();
			GlStateManager.disableCull();
			GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);

			GlStateManager.color4f(effect.getOverlayColor()[0], effect.getOverlayColor()[1], effect.getOverlayColor()[2], effect.getOverlayColor()[3]);

			GlStateManager.rotatef(180.0F, 1.0F, 0.0F, 0.0F);
			GlStateManager.rotatef(180.0F, 0.0F, 1.0F, 0.0F);

			GlStateManager.scaled(1.05, 1.04, 1.05);

			float ageInTicks = entity.ticksExisted + partialTicks;
			float headYawOffset = WyHelper.interpolateRotation(entity.prevRenderYawOffset, entity.renderYawOffset, partialTicks);
			float headYaw = WyHelper.interpolateRotation(entity.prevRotationYawHead, entity.rotationYawHead, partialTicks);
			float headPitch = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks;

			WyHelper.rotateCorpse(entity, ageInTicks, headYawOffset, partialTicks);
			float limbSwingAmount = entity.prevLimbSwingAmount + (entity.limbSwingAmount - entity.prevLimbSwingAmount) * partialTicks;
			float limbSwing = entity.limbSwing - entity.limbSwingAmount * (1.0F - partialTicks);

			renderer.getEntityModel().swingProgress = entity.getSwingProgress(partialTicks);
			renderer.getEntityModel().render(entity, limbSwing, limbSwingAmount, ageInTicks, headYaw - headYawOffset, headPitch, 0.06F);

			GlStateManager.enableTexture();
			GlStateManager.enableCull();
			GlStateManager.disableBlend();
		}
		GlStateManager.popMatrix();
	}
}
