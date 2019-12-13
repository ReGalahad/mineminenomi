package xyz.pixelatedw.mineminenomi.renderers.entities;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.entities.WantedPosterPackageEntity;
import xyz.pixelatedw.mineminenomi.models.blocks.WantedPosterPackageModel;
import xyz.pixelatedw.mineminenomi.values.ModValuesEnv;

public class WantedPosterPackageRenderer extends EntityRenderer<WantedPosterPackageEntity>
{
	private ResourceLocation texture = new ResourceLocation(ModValuesEnv.PROJECT_ID, "textures/models/wantedposterspackage.png");;
	private WantedPosterPackageModel model = new WantedPosterPackageModel();
	
	protected WantedPosterPackageRenderer(EntityRendererManager manager)
	{
		super(manager);
	}

	@Override
	public void doRender(WantedPosterPackageEntity entity, double x, double y, double z, float u, float v)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x, (float)y + 1.5F , (float) z);

		GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
    	GL11.glRotatef(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * v - 180.0F, 0.0F, 1.0F, 0.0F);
		
    	GL11.glScaled(1.5, 1, 1.5);

		Minecraft.getInstance().getTextureManager().bindTexture(this.texture);
		this.model.render(entity, 0.0F, 0.0F, 0F, 0.0F, 0.0F, 0.0625F);
		
		GL11.glColor3d(1, 1, 1);

		GL11.glPopMatrix();
	}
	
	@Override
	protected ResourceLocation getEntityTexture(WantedPosterPackageEntity entity)
	{
		return texture;
	}
	
	public static class Factory implements IRenderFactory<WantedPosterPackageEntity>
	{
		public Factory() {}
		
		@Override
		public EntityRenderer<? super WantedPosterPackageEntity> createRenderFor(EntityRendererManager manager)
		{
			return new WantedPosterPackageRenderer(manager);
		}
	}
}