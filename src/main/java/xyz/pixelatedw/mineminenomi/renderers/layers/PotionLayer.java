package xyz.pixelatedw.mineminenomi.renderers.layers;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import xyz.pixelatedw.mineminenomi.api.IHasOverlay;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.models.abilities.CandleLockModel;
import xyz.pixelatedw.wypi.WyHelper;

import java.awt.*;

public class PotionLayer extends LayerRenderer {
    LivingRenderer renderer;

    public PotionLayer(IEntityRenderer renderer) {
        super(renderer);
        this.renderer = (LivingRenderer) renderer;
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {


        if(((LivingEntity) entity).isPotionActive(ModEffects.FROZEN)) {
            GlStateManager.pushMatrix();
            GlStateManager.disableCull();
            GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            int blocksWidth = (int) Math.ceil(entity.getWidth()) + 1;
            int blocksHeight = (int) Math.ceil(entity.getHeight()) + 1;
            GlStateManager.translatef(0.5F - blocksWidth / 3F, 2F - entity.getHeight() / 3F - blocksHeight / 2F, 0.5F - blocksWidth / 3F);

            for (int x = 0; x < blocksWidth; x++) {
                for (int y = 0; y < blocksHeight; y++) {
                    for (int z = 0; z < blocksWidth; z++) {
                        GlStateManager.pushMatrix();
                        GlStateManager.translatef(x, y, z);
                        Minecraft.getInstance().getItemRenderer().renderItem(new ItemStack(Blocks.ICE), ItemCameraTransforms.TransformType.NONE);
                        GlStateManager.popMatrix();
                    }
                }
            }
            GlStateManager.enableCull();
            GlStateManager.popMatrix();
        }

        if(((LivingEntity) entity).isPotionActive(ModEffects.LOVESTRUCK)) {
            IHasOverlay effect = (IHasOverlay) ModEffects.LOVESTRUCK;
            GlStateManager.pushMatrix();
            {
                GlStateManager.disableTexture();
                GlStateManager.enableBlend();
                GlStateManager.disableLighting();
                GlStateManager.disableCull();
                GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);

                GlStateManager.color4f(effect.getOverlayColor()[0], effect.getOverlayColor()[1], effect.getOverlayColor()[2], effect.getOverlayColor()[3]);
                GlStateManager.scaled(1.05, 1.04, 1.05);
                renderer.getEntityModel().render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

                GlStateManager.enableTexture();
                GlStateManager.enableCull();
                GlStateManager.enableLighting();
                GlStateManager.disableBlend();
            }
            GlStateManager.popMatrix();
        }

    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }
}
