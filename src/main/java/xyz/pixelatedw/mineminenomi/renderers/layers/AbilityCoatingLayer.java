package xyz.pixelatedw.mineminenomi.renderers.layers;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.HandSide;

public class AbilityCoatingLayer<T extends LivingEntity, M extends EntityModel<T> & IHasArm> extends LayerRenderer<T, M>
{
	public AbilityCoatingLayer(IEntityRenderer<T, M> renderer)
	{
		super(renderer);
	}

	@Override
	public void render(T entity, float p_212842_2_, float p_212842_3_, float p_212842_4_, float p_212842_5_, float p_212842_6_, float p_212842_7_, float p_212842_8_)
	{
		GlStateManager.pushMatrix();
		if (this.getEntityModel().isChild)
		{
			GlStateManager.translatef(0.0F, 0.75F, 0.0F);
			GlStateManager.scalef(0.5F, 0.5F, 0.5F);
		}

		if (entity.shouldRenderSneaking())
		{
			GlStateManager.translatef(0.0F, 0.2F, 0.0F);
		}

		// Forge: moved this call down, fixes incorrect offset while sneaking.
		 ((IHasArm)this.getEntityModel()).postRenderArm(0.0625F, HandSide.RIGHT);
		 GlStateManager.rotatef(-90.0F, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotatef(180.0F, 0.0F, 1.0F, 0.0F);
		GlStateManager.translatef(1 / 16.0F, 0.125F, -0.625F);
		Minecraft.getInstance().getFirstPersonRenderer().renderItemSide(entity, new ItemStack(Items.STONE_SWORD), ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, false);

		GlStateManager.popMatrix();
	}

	@Override
	public boolean shouldCombineTextures()
	{
		return false;
	}

}
