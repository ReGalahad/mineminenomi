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
import xyz.pixelatedw.mineminenomi.api.abilities.CubeModel;
import xyz.pixelatedw.mineminenomi.entities.VivreCardEntity;

@OnlyIn(Dist.CLIENT)
public class VivreCardRenderer extends EntityRenderer<VivreCardEntity>
{
	private CubeModel model = new CubeModel();
	
	protected VivreCardRenderer(EntityRendererManager manager)
	{
		super(manager);
	}

	@Override
	protected ResourceLocation getEntityTexture(VivreCardEntity entity)
	{
		return null;
	}
	
	@Override
	public void doRender(VivreCardEntity entity, double x, double y, double z, float entityYaw, float partialTicks)
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
		
	public static class Factory implements IRenderFactory<VivreCardEntity>
	{
		public Factory() {}
		
		@Override
		public EntityRenderer<? super VivreCardEntity> createRenderFor(EntityRendererManager manager)
		{
			return new VivreCardRenderer(manager);
		}
	}
}