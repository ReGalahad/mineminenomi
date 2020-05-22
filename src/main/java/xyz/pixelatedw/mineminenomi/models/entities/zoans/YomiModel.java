package xyz.pixelatedw.mineminenomi.models.entities.zoans;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;
import xyz.pixelatedw.mineminenomi.api.ZoanMorphModel;

public class YomiModel<T extends LivingEntity> extends ZoanMorphModel<T> implements IHasArm
{
	public RendererModel bipedHead;
	public RendererModel bipedBody;
	public RendererModel bipedRightArm;
	public RendererModel bipedLeftArm;
	public RendererModel bipedRightLeg;
	public RendererModel bipedLeftLeg;
	public RendererModel afro;

	public YomiModel()
	{
		this(0.0F);
	}

	public YomiModel(float f)
	{
		super();
		this.textureWidth = 128;
		this.textureHeight = 128;
		this.bipedHead = new RendererModel(this, 0, 0);
		this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0);
		this.bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bipedBody = new RendererModel(this, 16, 16);
		this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0);
		this.bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bipedRightArm = new RendererModel(this, 40, 16);
		this.bipedRightArm.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, 0);
		this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
		this.bipedLeftArm = new RendererModel(this, 40, 16);
		this.bipedLeftArm.mirror = true;
		this.bipedLeftArm.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, 0);
		this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
		this.bipedRightLeg = new RendererModel(this, 0, 16);
		this.bipedRightLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, 0);
		this.bipedRightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
		this.bipedLeftLeg = new RendererModel(this, 0, 16);
		this.bipedLeftLeg.mirror = true;
		this.bipedLeftLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, 0);
		this.bipedLeftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
	}

	@Override
	public void render(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		this.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

		this.bipedRightArm.render(scale);
		this.bipedLeftArm.render(scale);
		this.bipedRightLeg.render(scale);
		this.bipedLeftLeg.render(scale);
		this.bipedHead.render(scale);
		this.bipedBody.render(scale);
	}

	@Override
	public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		super.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

		// Handles the head movement when following the mouse or when swimming
		this.bipedHead.rotateAngleY = netHeadYaw * ((float) Math.PI / 180F);
		this.bipedHead.rotateAngleX = headPitch * ((float) Math.PI / 180F);

		// Hanldes the arm and leg movement
		float f = 1.0F;
		this.bipedRightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.8F * limbSwingAmount * 0.5F / f;
		this.bipedLeftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.8F * limbSwingAmount * 0.5F / f;
		this.bipedRightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.7F * limbSwingAmount / f;
		this.bipedLeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.7F * limbSwingAmount / f;

		// Handles the punch and item use animations of the model
		this.swingProgress = entity.swingProgress;
		if (this.swingProgress > 0)
		{
			this.bipedBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt(this.swingProgress) * ((float) Math.PI * 2F)) * 0.2F;
			this.bipedRightArm.rotationPointZ = MathHelper.sin(this.bipedBody.rotateAngleY) * 4.0F;
			this.bipedRightArm.rotationPointX = -MathHelper.cos(this.bipedBody.rotateAngleY) * 4.0F;
			this.bipedRightArm.rotateAngleY += this.bipedBody.rotateAngleY;
			this.bipedLeftArm.rotateAngleY += this.bipedBody.rotateAngleY;
			this.bipedLeftArm.rotateAngleX += this.bipedBody.rotateAngleY;
			float f1 = 1.0F - this.swingProgress;
			f1 = f1 * f1;
			f1 = f1 * f1;
			f1 = 1.0F - f1;
			float f2 = MathHelper.sin(f1 * (float) Math.PI);
			float f3 = MathHelper.sin(this.swingProgress * (float) Math.PI) * -(this.bipedHead.rotateAngleX - 0.7F) * 0.75F;
			this.bipedRightArm.rotateAngleX = (float) (this.bipedRightArm.rotateAngleX - (f2 * 1.2D + f3));
			this.bipedRightArm.rotateAngleY += this.bipedBody.rotateAngleY * 2.0F;
			this.bipedRightArm.rotateAngleZ += MathHelper.sin(this.swingProgress * (float) Math.PI) * -0.4F;
		}
		
		// Handles the rotations and positions of individual cubes when sneaking or not
		if (entity.isSneaking())
		{
			this.bipedBody.rotateAngleX = 0.5F;
			this.bipedBody.rotationPointZ -= 4F;		
			this.bipedRightArm.rotateAngleX += 0.4F;
			this.bipedRightArm.rotationPointZ -= 2.5F;		
			this.bipedLeftArm.rotateAngleX += 0.4F;
			this.bipedLeftArm.rotationPointZ -= 2.5F;		
			this.bipedRightLeg.rotationPointZ = 1.0F;
			this.bipedRightLeg.rotationPointY = 10.0F;			
			this.bipedLeftLeg.rotationPointZ = 1F;
			this.bipedLeftLeg.rotationPointY = 10.0F;
			this.bipedHead.rotationPointZ = -4.0F;
			this.bipedHead.rotationPointY = 1.0F;
		}
	}

	public void setRotateAngle(RendererModel RendererModel, float x, float y, float z)
	{
		RendererModel.rotateAngleX = x;
		RendererModel.rotateAngleY = y;
		RendererModel.rotateAngleZ = z;
	}

	@Override
	public RendererModel getHandRenderer()
	{
		GL11.glTranslated(-0.1, 0, 0);
		return this.bipedRightArm;
	}

	@Override
	public RendererModel getArmRenderer()
	{
		return this.getHandRenderer();
	}

	@Override
	public void postRenderArm(float scale, HandSide side)
	{

	}
}