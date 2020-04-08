package xyz.pixelatedw.mineminenomi.renderers.abilities;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.GlStateManager.DestFactor;
import com.mojang.blaze3d.platform.GlStateManager.SourceFactor;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.entities.PhysicalBodyEntity;
import xyz.pixelatedw.mineminenomi.models.abilities.PhysicalBodyModel;

@OnlyIn(Dist.CLIENT)
public class PhysicalBodyRenderer extends EntityRenderer<PhysicalBodyEntity>
{
	private PhysicalBodyModel model = new PhysicalBodyModel();
	
	public PhysicalBodyRenderer(EntityRendererManager renderManager)
	{
		super(renderManager);
	}
	
	@Override
	public void doRender(PhysicalBodyEntity entity, double x, double y, double z, float entityYaw, float partialTicks)
	{		
		GlStateManager.pushMatrix();
		{
			GlStateManager.color4f(1, 1, 1, 1);
			GlStateManager.translated(x, y + 1.5, z);
			GlStateManager.rotated(180, 0, 0, 1);
			
			GlStateManager.enableBlend();
			GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
			
			GlStateManager.scaled(1, 1, 1);

			Minecraft.getInstance().getTextureManager().bindTexture(this.getEntityTexture(entity));
			this.model.render(entity, (float) x, (float) y, (float) z, 0.0F, 0.0F, 0.0625F);
		}
		GlStateManager.popMatrix();
	}	

	@Override
	protected ResourceLocation getEntityTexture(PhysicalBodyEntity entity)
	{
		AbstractClientPlayerEntity abstractOwner = (AbstractClientPlayerEntity) entity.getOwner();
		return abstractOwner.getLocationSkin();
	}
	
	public static class Factory implements IRenderFactory<PhysicalBodyEntity>
	{
		public Factory() {}
		
		@Override
		public EntityRenderer<? super PhysicalBodyEntity> createRenderFor(EntityRendererManager manager)
		{
			return new PhysicalBodyRenderer(manager);
		}
	}
}
