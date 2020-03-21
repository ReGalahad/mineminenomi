package xyz.pixelatedw.mineminenomi.renderers.entities;

import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.GlStateManager.DestFactor;
import com.mojang.blaze3d.platform.GlStateManager.SourceFactor;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.entities.ChargingUrsusShockEntity;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.PawModel;

public class ChargingUrsusShockRenderer extends EntityRenderer<ChargingUrsusShockEntity>
{
	private PawModel model;
	
	protected ChargingUrsusShockRenderer(EntityRendererManager manager)
	{
		super(manager);
		this.model = new PawModel();
	}

	@Override
	public void doRender(ChargingUrsusShockEntity entity, double x, double y, double z, float u, float v)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x, (float) y + 1.5F, (float) z);

		GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * v - 180.0F, 0.0F, 1.0F, 0.0F);

		double size = 1 + (entity.getCharge() / 2);

		GL11.glScaled(size, size, size);

		GlStateManager.disableTexture();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
		GlStateManager.disableDepthTest();

		GlStateManager.pushMatrix();
		{
			double t = entity.ticksExisted * 3 % 100;
			double mirageSize = t >= 50 ? 2 - (t / 100.0D) : 1 + (t / 100.0D);
			double scale = mirageSize;
			GL11.glColor4d(1, 1, 1, 0.2);
			GlStateManager.scaled(scale, scale, scale);
			this.model.render(entity, 0.0F, 0.0F, 0F, 0.0F, 0.0F, 0.0625F);
		}
		GlStateManager.popMatrix();

		GL11.glColor4d(1, 1, 1, 0.6);

		this.model.render(entity, 0.0F, 0.0F, 0F, 0.0F, 0.0F, 0.0625F);

		GL11.glColor4d(1, 1, 1, 1);
		
		GlStateManager.enableDepthTest();
		GlStateManager.disableBlend();
		GlStateManager.enableTexture();
		
		GL11.glPopMatrix();
	}
	
	@Override
	protected ResourceLocation getEntityTexture(ChargingUrsusShockEntity entity)
	{
		return null;
	}
	
	public static class Factory implements IRenderFactory<ChargingUrsusShockEntity>
	{
		public Factory() {}
		
		@Override
		public EntityRenderer<? super ChargingUrsusShockEntity> createRenderFor(EntityRendererManager manager)
		{
			return new ChargingUrsusShockRenderer(manager);
		}
	}
}
