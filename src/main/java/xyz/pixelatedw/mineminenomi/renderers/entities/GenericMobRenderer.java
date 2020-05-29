package xyz.pixelatedw.mineminenomi.renderers.entities;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.entities.mobs.IDynamicRenderer;
import xyz.pixelatedw.mineminenomi.renderers.layers.PotionLayer;
import xyz.pixelatedw.wypi.APIConfig;

@OnlyIn(Dist.CLIENT)
public class GenericMobRenderer<T extends MobEntity, M extends BipedModel<T>> extends BipedRenderer<T, M>
{
	private ResourceLocation texture;
	private float scale;

	public GenericMobRenderer(EntityRendererManager manager, M model, float scale, String tex)
	{
		super(manager, model, 0.0F);
		this.scale = scale;
		this.texture = new ResourceLocation(APIConfig.PROJECT_ID, "textures/models/" + tex + ".png");
		this.addLayer(new BipedArmorLayer<>(this, new BipedModel(0.5F), new BipedModel(1.0F)));
		this.addLayer(new PotionLayer(this));
	}

	public GenericMobRenderer(EntityRendererManager manager, M model, String tex)
	{
		super(manager, model, 0.0F);
		this.scale = 1.0F;
		this.texture = new ResourceLocation(APIConfig.PROJECT_ID, "textures/models/" + tex + ".png");
		this.addLayer(new BipedArmorLayer<>(this, new BipedModel(0.5F), new BipedModel(1.0F)));
		this.addLayer(new PotionLayer(this));
	}

	public GenericMobRenderer(EntityRendererManager manager, M model)
	{
		super(manager, model, 0.0F);
		this.scale = 1.0F;
		this.texture = null;
		this.addLayer(new BipedArmorLayer<>(this, new BipedModel(0.5F), new BipedModel(1.0F)));
		this.addLayer(new PotionLayer(this));
	}

	@Override
	public void doRender(T entity, double x, double y, double z, float u, float v)
	{
		super.doRender(entity, x, y, z, u, v);
	}

	@Override
	protected void preRenderCallback(T livingBase, float f)
	{
		GL11.glScalef(this.scale, this.scale, this.scale);
	}

	@Override
	protected ResourceLocation getEntityTexture(T entity)
	{
		if ((this.texture == null && entity instanceof IDynamicRenderer) || this.texture.equals(new ResourceLocation(APIConfig.PROJECT_ID + ":textures/models/null.png")))
			return new ResourceLocation(APIConfig.PROJECT_ID + ":textures/models/" + ((IDynamicRenderer) entity).getMobTexture() + ".png");
		else
			return this.texture;
	}

	public static class Factory implements IRenderFactory
	{
		private BipedModel model;
		private float scale;
		private String texture;
			
		public Factory(BipedModel model, float scale, String texture)
		{
			this.model = model;
			this.scale = scale;
			this.texture = texture;
			
			if(this.scale == 0)
				this.scale = 1;
			
		}

		@Override
		public EntityRenderer createRenderFor(EntityRendererManager manager)
		{
			return new GenericMobRenderer(manager, this.model, this.scale, this.texture);
		}

	}
}
