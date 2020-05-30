package xyz.pixelatedw.mineminenomi.renderers.layers;

import java.util.ArrayList;
import java.util.List;

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
import net.minecraft.potion.Effect;
import xyz.pixelatedw.mineminenomi.api.IHasOverlay;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModEffects;

@SuppressWarnings("deprecation")
public class PotionLayer extends LayerRenderer {
    LivingRenderer renderer;
    private List<Effect> effectsWithOverlay = new ArrayList();

    public PotionLayer(IEntityRenderer renderer) {
        super(renderer);
        this.renderer = (LivingRenderer) renderer;
        this.effectsWithOverlay.add(ModEffects.LOVESTRUCK);
        this.effectsWithOverlay.add(ModEffects.BUBBLY_CORAL);
    }

	@Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        if(((LivingEntity) entity).isPotionActive(ModEffects.FROZEN)) {
            if (((LivingEntity) entity).getActivePotionEffect(ModEffects.FROZEN).getDuration() <= 0)
                ((LivingEntity) entity).removePotionEffect(ModEffects.FROZEN);

            GlStateManager.pushMatrix();
            GlStateManager.disableLighting();
            GlStateManager.disableCull();
            GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            float blocksWidth = (float) (Math.ceil(entity.getWidth()) + 1);
            float blocksHeight = (float) (Math.ceil(entity.getHeight()) + 1);
            GlStateManager.translatef(0.45F - blocksWidth / 2F, 1.3F - entity.getHeight() / 2F - blocksHeight / 2F, 0.45F - blocksWidth / 2F);

            for (int x = 0; x < blocksWidth; x++) {
                for (int y = 0; y < blocksHeight; y++) {
                    for (int z = 0; z < blocksWidth; z++) {
                        GlStateManager.pushMatrix();
                        GlStateManager.translatef(x, y, z);
                        Minecraft.getInstance().getItemRenderer().renderItem(new ItemStack(Blocks.PACKED_ICE), ItemCameraTransforms.TransformType.HEAD);
                        GlStateManager.popMatrix();
                    }
                }
            }
            GlStateManager.enableCull();
            GlStateManager.enableLighting();
            GlStateManager.popMatrix();
        }
        
        if(((LivingEntity) entity).isPotionActive(ModEffects.BLACK_BOX)) {
            if (((LivingEntity) entity).getActivePotionEffect(ModEffects.BLACK_BOX).getDuration() <= 0)
                ((LivingEntity) entity).removePotionEffect(ModEffects.BLACK_BOX);

            GlStateManager.pushMatrix();
            GlStateManager.disableLighting();
            GlStateManager.disableCull();
            GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            float blocksWidth = (float) (Math.ceil(entity.getWidth()) + 1);
            float blocksHeight = (float) (Math.ceil(entity.getHeight()) + 1);
            GlStateManager.translatef(0.45F - blocksWidth / 2F, 1.3F - entity.getHeight() / 2F - blocksHeight / 2F, 0.45F - blocksWidth / 2F);

            for (int x = 0; x < blocksWidth; x++) {
                for (int y = 0; y < blocksHeight; y++) {
                    for (int z = 0; z < blocksWidth; z++) {
                        GlStateManager.pushMatrix();
                        GlStateManager.translatef(x, y, z);
                        Minecraft.getInstance().getItemRenderer().renderItem(new ItemStack(ModBlocks.DARKNESS), ItemCameraTransforms.TransformType.HEAD);
                        GlStateManager.popMatrix();
                    }
                }
            }
            GlStateManager.enableCull();
            GlStateManager.enableLighting();
            GlStateManager.popMatrix();
        }

        for (Effect effect : this.effectsWithOverlay) {
            if(((LivingEntity) entity).isPotionActive(effect)) {
                if (((LivingEntity) entity).getActivePotionEffect(effect).getDuration() <= 0)
                    ((LivingEntity) entity).removePotionEffect(effect);

                IHasOverlay overlay = (IHasOverlay) effect;
                GlStateManager.pushMatrix();
                {
                    GlStateManager.disableTexture();
                    GlStateManager.enableBlend();
                    GlStateManager.disableLighting();
                    GlStateManager.disableCull();
                    GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);

                    GlStateManager.color4f(overlay.getOverlayColor()[0], overlay.getOverlayColor()[1], overlay.getOverlayColor()[2], overlay.getOverlayColor()[3]);
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


    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }
}
