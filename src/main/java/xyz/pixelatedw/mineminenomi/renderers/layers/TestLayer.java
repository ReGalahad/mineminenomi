package xyz.pixelatedw.mineminenomi.renderers.layers;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class TestLayer extends LayerRenderer {
    LivingRenderer renderer;

    public TestLayer(IEntityRenderer renderer) {
        super(renderer);
        this.renderer = (LivingRenderer) renderer;
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        GlStateManager.pushMatrix();
        GlStateManager.rotatef(180, 0, 0, 1);
        GlStateManager.scalef(0.6f, 0.6f, 0.6f);
        GlStateManager.rotatef((ageInTicks) / 20.0F * (180F / (float)Math.PI), 0.0F, 1.0F, 0.0F);
        GlStateManager.translatef(0, (float) (entity.getHeight() - 0.3), 0);
        Minecraft.getInstance().getItemRenderer().renderItem(new ItemStack(Items.DIAMOND), ItemCameraTransforms.TransformType.FIXED);
        GlStateManager.popMatrix();
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }
}
