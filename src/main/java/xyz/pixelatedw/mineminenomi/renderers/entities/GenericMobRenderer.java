package xyz.pixelatedw.mineminenomi.renderers.entities;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.entities.mobs.IDynamicRenderer;
import xyz.pixelatedw.mineminenomi.values.ModValuesEnv;

@OnlyIn(Dist.CLIENT)
public class GenericMobRenderer extends BipedRenderer
{
	private ResourceLocation texture;
	private float scale;

	public GenericMobRenderer(EntityRendererManager EntityRendererManager, BipedModel model, float scale, String tex)
	{
		super(EntityRendererManager, model, 0.0F);
		this.scale = scale;
		this.texture = new ResourceLocation(ModValuesEnv.PROJECT_ID, "textures/models/" + tex + ".png");
		this.addLayer(new BipedArmorLayer<>(this, new BipedModel(0.5F), new BipedModel(1.0F)));
	}

	public GenericMobRenderer(EntityRendererManager EntityRendererManager, BipedModel model, String tex)
	{
		super(EntityRendererManager, model, 0.0F);
		this.scale = 1.0F;
		this.texture = new ResourceLocation(ModValuesEnv.PROJECT_ID, "textures/models/" + tex + ".png");
		this.addLayer(new BipedArmorLayer<>(this, new BipedModel(0.5F), new BipedModel(1.0F)));
	}

	public GenericMobRenderer(EntityRendererManager EntityRendererManager, BipedModel model)
	{
		super(EntityRendererManager, model, 0.0F);
		this.scale = 1.0F;
		this.texture = null;
		this.addLayer(new BipedArmorLayer<>(this, new BipedModel(0.5F), new BipedModel(1.0F)));
	}

	public void doRender(LivingEntity entity, double x, double y, double z, float u, float v)
	{
		super.doRender((MobEntity) entity, x, y, z, u, v);
	}

	@Override
	protected void preRenderCallback(LivingEntity livingBase, float f)
	{
		GL11.glScalef(this.scale, this.scale, this.scale);
	}

	/*protected void renderEquippedItems(LivingEntity entity, float f)
	{
		GL11.glPushMatrix();
		if (entity instanceof IDynamicRenderer)
		{
			GL11.glScaled(((IDynamicRenderer) entity).itemScale()[0], ((IDynamicRenderer) entity).itemScale()[1], ((IDynamicRenderer) entity).itemScale()[2]);
			GL11.glTranslated(((IDynamicRenderer) entity).itemOffset()[0], ((IDynamicRenderer) entity).itemOffset()[1], ((IDynamicRenderer) entity).itemOffset()[2]);
		}

		/*ExtendedEntityData props = ExtendedEntityData.get((EntityLivingBase) entity);
		boolean hasHaki = props.hasBusoHakiActive();

		if(hasHaki)
		{
			this.bindTexture(ID.HANDTEXTURE_ZOANMORPH_BUSO);
			GL11.glColor3d(0.5, 0, 0.5);
			super.renderEquippedItems(entity, f);
		}
		else
		{
			super.renderEquippedItems(entity, f);
		}/
		GL11.glPopMatrix();
	}*/

	protected ResourceLocation getEntityTexture(Entity entity)
	{
		if ((this.texture == null && entity instanceof IDynamicRenderer) || this.texture.equals(new ResourceLocation(ModValuesEnv.PROJECT_ID + ":textures/models/null.png")))
			return new ResourceLocation(ModValuesEnv.PROJECT_ID + ":textures/models/" + ((IDynamicRenderer) entity).getMobTexture() + ".png");
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
