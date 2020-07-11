package xyz.pixelatedw.mineminenomi.renderers.entities;

import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.api.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.api.helpers.MorphHelper;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.models.CubeModel;
import xyz.pixelatedw.wypi.debug.WyDebug;

@SuppressWarnings("deprecation")
@OnlyIn(Dist.CLIENT)
public class ZoanMorphRenderer extends LivingRenderer
{
	private ResourceLocation texture = new ResourceLocation(APIConfig.PROJECT_ID, "textures/models/null.png");
	private EntityModel model;
	private double scale;
	private float offset[] = new float[3];

	public ZoanMorphRenderer(EntityRendererManager renderManager, EntityModel model, String texture)
	{
		super(renderManager, model, 0.5F);
		this.model = model;
		this.texture = new ResourceLocation(APIConfig.PROJECT_ID, "textures/models/zoanmorph/" + texture + ".png");
		this.addLayer(new HeldItemLayer<>(this));
	}
	
	public void setScale(double size)
	{
		this.scale = size;
	}
	
	public void setOffset(float[] offset)
	{
		this.offset = offset;
	}

	public void renderFirstPersonArm(PlayerEntity player)
	{
		float f = 1.0F;
		GL11.glColor3f(f, f, f);
		if (this.model instanceof ZoanMorphModel && ((ZoanMorphModel) this.model).getHandRenderer() != null)
			((ZoanMorphModel) this.model).getHandRenderer().render(0.0625F);
	}

	@Override
	public void doRender(LivingEntity entity, double x, double y, double z, float u, float partialTicks)
	{
		//super.doRenderShadowAndFire(entity, x, y, z, (float) y, v);
		GL11.glPushMatrix();
		
		GlStateManager.disableCull();
		
		if (entity != Minecraft.getInstance().player)
			GL11.glTranslatef((float) x + this.offset[0], (float) y + 1.3F + this.offset[1] - 1.2F, (float) z + this.offset[2]);
		else
			GL11.glTranslatef((float) x + this.offset[0], (float) y + 1.3F + this.offset[1], (float) z + this.offset[2]);

		GlStateManager.rotatef(180.0F, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotatef(180.0F, 0.0F, 1.0F, 0.0F);

		GL11.glScaled(this.scale, this.scale, this.scale);

		float ageInTicks = entity.ticksExisted + partialTicks;

		float headYawOffset = WyHelper.interpolateRotation(entity.prevRenderYawOffset, entity.renderYawOffset, partialTicks);
		float headYaw = WyHelper.interpolateRotation(entity.prevRotationYawHead, entity.rotationYawHead, partialTicks);

		float headPitch = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks;

		WyHelper.rotateCorpse(entity, ageInTicks, headYawOffset, partialTicks);

		float limbSwingAmount = entity.prevLimbSwingAmount + (entity.limbSwingAmount - entity.prevLimbSwingAmount) * partialTicks;
		float limbSwing = entity.limbSwing - entity.limbSwingAmount * (1.0F - partialTicks);
		
		Minecraft.getInstance().getTextureManager().bindTexture(this.getEntityTexture(entity));
		this.model.render(entity, limbSwing, limbSwingAmount, ageInTicks, headYaw - headYawOffset, headPitch, 0.0625F);

		// In-hand item rendering
		ZoanInfo info = MorphHelper.getZoanInfo((PlayerEntity) entity);
		RendererModel arm = ((ZoanMorphModel) this.model).getArmRenderer();
		if (arm != null)
		{	
			if(entity.isSneaking())
				GlStateManager.translated(0, -0.1, -0.2);
			
			GlStateManager.disableTexture();
			GlStateManager.translated(info.getHeldItemOffset()[0][0], info.getHeldItemOffset()[0][1], info.getHeldItemOffset()[0][2]);
			if(WyDebug.isDebug())
			{
				GlStateManager.disableDepthTest();
				GlStateManager.enableCull();
				new CubeModel().render(entity, 0, 0, 0, 0, 0, 0.0325F);
				GlStateManager.disableCull();
				GlStateManager.enableDepthTest();
			}
			GlStateManager.rotated(Math.toDegrees(arm.rotateAngleX), 1, 0, 0);
			GlStateManager.rotated(Math.toDegrees(arm.rotateAngleY), 0, 1, 0);
			GlStateManager.rotated(Math.toDegrees(arm.rotateAngleZ), 0, 0, 1);
			GlStateManager.translated(info.getHeldItemOffset()[1][0], info.getHeldItemOffset()[1][1], info.getHeldItemOffset()[1][2]);
			GlStateManager.enableTexture();
	
			ItemStack stack = entity.getHeldItem(Hand.MAIN_HAND);
			if(stack != null)
			{
				GlStateManager.rotatef(180.0F, 1.0F, 0.0F, 0.0F);
				GlStateManager.rotatef(180.0F, 0.0F, 1.0F, 0.0F);
				GlStateManager.rotatef(180.0F, 0.0F, 0.0F, 1.0F);
				Minecraft.getInstance().getFirstPersonRenderer().renderItemSide(entity, stack, TransformType.FIRST_PERSON_LEFT_HAND, false);
			}
		}
		
		GlStateManager.enableCull();
		
		GL11.glPopMatrix();
	}

	@Override
	public ResourceLocation getEntityTexture(Entity p_110775_1_)
	{
		return this.texture;
	}

	public static class Factory<T extends LivingEntity> implements IRenderFactory<T>
	{
		private EntityModel model;
		private String texture;
		private double scale;
		private float[] offset = new float[] {0, 0, 0};

		public Factory(EntityModel model, String texture)
		{
			this.model = model;
			this.texture = texture;
		}
		
		public Factory setScale(double size)
		{
			this.scale = size;
			return this;
		}
		
		public Factory setOffset(float x, float y, float z)
		{
			this.offset = new float[] {x, y, z};
			return this;
		}

		@Override
		public ZoanMorphRenderer createRenderFor(EntityRendererManager manager)
		{
			ZoanMorphRenderer renderer = new ZoanMorphRenderer(manager, this.model, this.texture);
			renderer.setScale(this.scale);
			renderer.setOffset(this.offset);
			return renderer;
		}
	}
}
