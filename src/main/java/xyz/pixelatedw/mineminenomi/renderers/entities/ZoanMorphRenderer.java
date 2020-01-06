package xyz.pixelatedw.mineminenomi.renderers.entities;

import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.HandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.Env;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.entities.zoan.ZoanInfo;
import xyz.pixelatedw.mineminenomi.helpers.DevilFruitsHelper;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.ZoanMorphModel;

public class ZoanMorphRenderer extends LivingRenderer
{
	private ResourceLocation texture = new ResourceLocation(Env.PROJECT_ID, "textures/models/null.png");
	private EntityModel model;
	private double scale;
	private float offset[] = new float[3];

	public ZoanMorphRenderer(EntityRendererManager renderManager, EntityModel model, String texture)
	{
		this(renderManager, model, texture, 1);
	}

	public ZoanMorphRenderer(EntityRendererManager renderManager, EntityModel model, String texture, double scale)
	{
		this(renderManager, model, texture, scale, new float[]
			{
					0, 0, 0
			});
	}

	public ZoanMorphRenderer(EntityRendererManager renderManager, EntityModel model, String texture, double scale, float[] offset)
	{
		super(renderManager, model, 0.5F);
		this.shadowSize = 0;
		this.model = model;
		this.scale = scale;
		this.texture = new ResourceLocation(Env.PROJECT_ID, "textures/models/zoanmorph/" + texture + ".png");
		this.offset = offset;
		this.addLayer(new HeldItemLayer<>(this));
	}

	public void renderFirstPersonArm(PlayerEntity player)
	{
		float f = 1.0F;
		GL11.glColor3f(f, f, f);
		if (this.model instanceof ZoanMorphModel && ((ZoanMorphModel) this.model).getHandRenderer() != null)
			((ZoanMorphModel) this.model).getHandRenderer().render(0.0625F);
	}

	@Override
	public void doRender(LivingEntity entity, double x, double y, double z, float u, float v)
	{
		GL11.glPushMatrix();

		float clinetBuffer = 1.2F;

		if (entity != Minecraft.getInstance().player)
			GL11.glTranslatef((float) x + this.offset[0], (float) y + 1.3F + this.offset[1] - 1.2F, (float) z + this.offset[2]);
		else
			GL11.glTranslatef((float) x + this.offset[0], (float) y + 1.3F + this.offset[1], (float) z + this.offset[2]);

		GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
		// GL11.glRotatef(entity.prevRotationYaw + (entity.rotationYaw -
		// entity.prevRotationYaw) * v - 180.0F, 0.0F, 1.0F, 0.0F);

		GL11.glScaled(this.scale, this.scale, this.scale);
		// GL11.glScalef(1.0F, 1.0F, 1.0F);

		float ageInTicks = entity.ticksExisted + v;

		float headYawOffset = this.interpolateRotation(entity.prevRenderYawOffset, entity.renderYawOffset, v);
		float headYaw = this.interpolateRotation(entity.prevRotationYawHead, entity.rotationYawHead, v);

		float headPitch = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * v;

		this.rotateCorpse(entity, ageInTicks, headYawOffset, v);

		float limbSwingAmount = entity.prevLimbSwingAmount + (entity.limbSwingAmount - entity.prevLimbSwingAmount) * v;
		float limbSwing = entity.limbSwing - entity.limbSwingAmount * (1.0F - v);

		Minecraft.getInstance().getTextureManager().bindTexture(this.getEntityTexture(entity));
		this.model.render(entity, limbSwing, limbSwingAmount, ageInTicks, headYaw - headYawOffset, headPitch, 0.0625F);

		// GL11.glScaled(this.scale/2, this.scale/2, this.scale/2);
		// In-hand item rendering
		GL11.glPushMatrix();
		{
			GL11.glDisable(GL11.GL_CULL_FACE);
			RendererModel arm = ((ZoanMorphModel) this.model).getArmRenderer();
			ZoanInfo info = DevilFruitsHelper.getZoanInfo((PlayerEntity) entity);

			if (arm != null)
			{
				/*GL11.glRotated(arm.rotateAngleX * 80, 1, 0, 0);
				GL11.glRotated(arm.rotateAngleY * 80, 0, 1, 0);
				GL11.glRotated(arm.rotateAngleZ * 80, 0, 0, 1);*/
				
				double rotation = info.getHeldItemRotation();
				
				GL11.glRotated(arm.rotateAngleX * rotation, 1, 0, 0);
				GL11.glRotated(arm.rotateAngleY * rotation, 0, 1, 0);
				GL11.glRotated(arm.rotateAngleZ * rotation, 0, 0, 1);

				this.renderEquippedItems(entity, v);
			}
			
			GL11.glEnable(GL11.GL_CULL_FACE);
		}
		GL11.glPopMatrix();

		GL11.glPopMatrix();
	}

	protected void renderEquippedItems(LivingEntity entity, float age)
	{
		GL11.glColor3f(1.0F, 1.0F, 1.0F);

		ItemStack itemstack = entity.getHeldItemMainhand();

		if (!itemstack.isEmpty())
		{
			//GlStateManager.pushMatrix();
			//{
				IDevilFruit props = DevilFruitCapability.get(entity);

				if (props == null || WyHelper.isNullOrEmpty(props.getZoanPoint()))
					return;

				ZoanInfo info = DevilFruitsHelper.getZoanInfo((PlayerEntity) entity);

				//this.translateToHand(HandSide.LEFT);
				GlStateManager.rotatef(-180.0F, 1.0F, 0.0F, 0.0F);
				GlStateManager.rotatef(180.0F, 0.0F, 1.0F, 0.0F);
				GlStateManager.rotatef(180.0F, 0.0F, 0.0F, 1.0F);
				GlStateManager.translated(info.getHeldItemOffset()[0], info.getHeldItemOffset()[1], info.getHeldItemOffset()[2]);

				Minecraft.getInstance().getFirstPersonRenderer().renderItemSide(entity, itemstack, TransformType.FIRST_PERSON_LEFT_HAND, false);
			//}
			//GlStateManager.popMatrix();
		}
	}

	private void translateToHand(HandSide side)
	{
		((IHasArm) this.getEntityModel()).postRenderArm(0.0625F, side);
	}

	private float interpolateRotation(float lowerLimit, float upperLimit, float range)
	{
		float f3;

		for (f3 = upperLimit - lowerLimit; f3 < -180.0F; f3 += 360.0F)
		{
			;
		}

		while (f3 >= 180.0F)
		{
			f3 -= 360.0F;
		}

		return lowerLimit + range * f3;
	}

	protected void rotateCorpse(LivingEntity entityLiving, float ageInTicks, float headYawOffset, float v)
	{
		GL11.glRotatef(180.0F + headYawOffset, 0.0F, 1.0F, 0.0F);

		if (entityLiving.deathTime > 0)
		{
			float f3 = (entityLiving.deathTime + v - 1.0F) / 20.0F * 1.6F;
			f3 = MathHelper.sqrt(f3);

			if (f3 > 1.0F)
			{
				f3 = 1.0F;
			}

			// GL11.glRotatef(f3 * this.getDeathMaxRotation(p_77043_1_), 0.0F,
			// 0.0F, 1.0F);
		}
	}

	@Override
	public ResourceLocation getEntityTexture(Entity p_110775_1_)
	{
		return texture;
	}

	public static class Factory implements IRenderFactory<PlayerEntity>
	{
		private EntityModel model;
		private String texture;
		private double scale;
		private float[] offset;

		public Factory(EntityModel model, String texture, double scale, float[] offset)
		{
			this.model = model;
			this.texture = texture;
			this.scale = scale;
			this.offset = offset;
		}

		@Override
		public ZoanMorphRenderer createRenderFor(EntityRendererManager manager)
		{
			return new ZoanMorphRenderer(manager, this.model, this.texture, this.scale, this.offset);
		}
	}

}
