package xyz.pixelatedw.mineminenomi.renderers.entities;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.GlStateManager.DestFactor;
import com.mojang.blaze3d.platform.GlStateManager.SourceFactor;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.entities.PhysicalBodyEntity;
import xyz.pixelatedw.wypi.abilities.models.CubeModel;

@OnlyIn(Dist.CLIENT)
public class PhysicalBodyRenderer extends EntityRenderer<PhysicalBodyEntity>
{
	private CubeModel model = new CubeModel();
	
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
			GlStateManager.translated(x, y + 0.15, z);
			GlStateManager.rotated(-entity.rotationYaw, 0, 1, 0);
			
			GlStateManager.disableTexture();
			GlStateManager.enableBlend();
			GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
			
			GlStateManager.color4f(1F, 1F, 1F, 1.0F);
			GlStateManager.scaled(0.4, 0.1, 0.5);
						
			this.model.render(entity, (float) x, (float) y, (float) z, 0.0F, 0.0F, 0.0625F);
			
			GlStateManager.enableTexture();

		}
		GlStateManager.popMatrix();
	}	

	@Override
	protected ResourceLocation getEntityTexture(PhysicalBodyEntity entity)
	{
		return null;
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
