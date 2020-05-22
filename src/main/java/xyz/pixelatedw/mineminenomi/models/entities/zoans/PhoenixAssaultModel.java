package xyz.pixelatedw.mineminenomi.models.entities.zoans;

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import xyz.pixelatedw.mineminenomi.api.ZoanMorphModel;

public class PhoenixAssaultModel<T extends LivingEntity> extends ZoanMorphModel<T>
{
	public RendererModel head;
	public RendererModel body;
	public RendererModel leftarm;
	public RendererModel rightarm;
	public RendererModel rightleg;
	public RendererModel leftleg;
	public RendererModel Flame;
	public RendererModel Flame2;
	public RendererModel LeftWing1;
	public RendererModel leftWing2;
	public RendererModel RightWing1;
	public RendererModel rightWing2;

	public PhoenixAssaultModel()
	{
		this.textureWidth = 128;
		this.textureHeight = 64;
		this.leftleg = new RendererModel(this, 0, 16);
		this.leftleg.setRotationPoint(2.0F, 12.0F, 0.0F);
		this.leftleg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
		this.LeftWing1 = new RendererModel(this, 71, 39);
		this.LeftWing1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.LeftWing1.addBox(0.0F, 1.0F, 1.0F, 13, 10, 0, 0.0F);
		this.RightWing1 = new RendererModel(this, 71, 54);
		this.RightWing1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.RightWing1.addBox(-13.0F, 1.0F, 1.0F, 13, 10, 0, 0.0F);
		this.rightWing2 = new RendererModel(this, 98, 52);
		this.rightWing2.setRotationPoint(-12.1F, 0.2F, 0.0F);
		this.rightWing2.addBox(-15.0F, 0.0F, 1.0F, 15, 12, 0, 0.0F);
		this.setRotateAngle(rightWing2, 0.0F, -0.0F, 0.10471975511965977F);
		this.body = new RendererModel(this, 16, 16);
		this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
		this.rightarm = new RendererModel(this, 100, 36);
		this.rightarm.setRotationPoint(-3.0F, 0.0F, -0.5F);
		this.rightarm.addBox(-13.0F, 0.0F, 0.0F, 13, 1, 1, 0.0F);
		this.setRotateAngle(rightarm, 1.1344640137963142F, -0.2617993877991494F, -0.9948376736367678F);
		this.leftarm = new RendererModel(this, 71, 36);
		this.leftarm.setRotationPoint(3.0F, 0.0F, -0.5F);
		this.leftarm.addBox(0.0F, 0.0F, 0.0F, 13, 1, 1, 0.0F);
		this.setRotateAngle(leftarm, 1.1344640137963142F, 0.2617993877991494F, 0.9948376736367678F);
		this.Flame = new RendererModel(this, 73, 19);
		this.Flame.setRotationPoint(0.0F, -8.0F, -4.0F);
		this.Flame.addBox(0.0F, -4.0F, 0.0F, 0, 4, 8, 0.0F);
		this.rightleg = new RendererModel(this, 0, 16);
		this.rightleg.setRotationPoint(-2.0F, 12.0F, 0.0F);
		this.rightleg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
		this.Flame2 = new RendererModel(this, 73, 13);
		this.Flame2.setRotationPoint(0.0F, -8.0F, 4.0F);
		this.Flame2.addBox(0.0F, 0.0F, 0.0F, 0, 8, 4, 0.0F);
		this.head = new RendererModel(this, 0, 0);
		this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
		this.leftWing2 = new RendererModel(this, 98, 39);
		this.leftWing2.setRotationPoint(12.1F, 0.0F, 0.0F);
		this.leftWing2.addBox(0.0F, 0.0F, 1.0F, 15, 12, 0, 0.0F);
		this.setRotateAngle(leftWing2, 0.0F, -0.0F, -0.10471975511965977F);
		this.leftarm.addChild(this.LeftWing1);
		this.rightarm.addChild(this.RightWing1);
		this.rightarm.addChild(this.rightWing2);
		this.head.addChild(this.Flame);
		this.head.addChild(this.Flame2);
		this.leftarm.addChild(this.leftWing2);
	}

	@Override
	public void render(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		this.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

		this.leftleg.render(scale);
		this.body.render(scale);
		this.rightarm.render(scale);
		this.leftarm.render(scale);
		this.rightleg.render(scale);
		this.head.render(scale);
	}

	@Override
	public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		super.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

		// Handles the head movement when following the mouse or when swimming
		this.head.rotateAngleY = netHeadYaw * ((float) Math.PI / 180F);
		this.head.rotateAngleX = headPitch * ((float) Math.PI / 180F);

		// Hanldes the wing and leg movement
		this.rightarm.rotationPointZ -= 3;
		this.rightarm.rotationPointX -= 1;
		this.leftarm.rotationPointZ -= 3;
		this.leftarm.rotationPointX += 1;
		float f = 1.0F;
		this.rightleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.9F * limbSwingAmount / f;
		this.leftleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.9F * limbSwingAmount / f;
		
		if(entity.onGround)
		{
			this.rightarm.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount / f;
			this.leftarm.rotateAngleY = -MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount / f;			
		}
		else
		{
			this.rightarm.rotationPointX += 2;
			this.leftarm.rotationPointX -= 2;
			
			this.rightarm.rotateAngleY = this.rightarm.rotateAngleZ = 3 * MathHelper.cos(ageInTicks * 0.4F) * 0.23F;
			this.leftarm.rotateAngleY = this.leftarm.rotateAngleZ = 3 * MathHelper.cos(ageInTicks * 0.4F + (float) Math.PI) * 0.23F;

			this.rightWing2.rotateAngleY = MathHelper.cos(ageInTicks * 0.4F) * 0.35F;
			this.leftWing2.rotateAngleY = MathHelper.cos(ageInTicks * 0.4F + (float) Math.PI) * 0.35F;
		}

		// Handles the punch and item use animations of the model
		this.swingProgress = entity.swingProgress;
		if (this.swingProgress > 0)
		{
			this.body.rotateAngleY = MathHelper.sin(MathHelper.sqrt(this.swingProgress) * ((float) Math.PI * 2F)) * 0.2F;
			this.rightarm.rotationPointZ = MathHelper.sin(this.body.rotateAngleY) * 3.0F;
			this.rightarm.rotationPointX = -MathHelper.cos(this.body.rotateAngleY) * 3.0F;
			this.leftarm.rotationPointZ = -MathHelper.sin(this.body.rotateAngleY) * 5.0F;
			this.leftarm.rotationPointX = MathHelper.cos(this.body.rotateAngleY) * 5.0F;
			this.rightarm.rotateAngleY += this.body.rotateAngleY;
			this.leftarm.rotateAngleY += this.body.rotateAngleY;
			this.leftarm.rotateAngleX += this.body.rotateAngleY;
			float f1 = 1.0F - this.swingProgress;
			f1 = f1 * f1;
			f1 = f1 * f1;
			f1 = 1.0F - f1;
			this.rightarm.rotateAngleY += this.body.rotateAngleY * 7.0F;
			this.rightarm.rotateAngleZ += MathHelper.sin(this.swingProgress * (float) Math.PI) * -0.4F;
		}
		
		// Handles the rotations and positions of individual cubes when sneaking or not
		if (entity.isSneaking())
		{
			this.body.rotateAngleX = 0.5F;
			this.body.rotationPointZ -= 4F;
			
			this.rightarm.rotateAngleX += 0.4F;
			this.rightarm.rotateAngleZ -= 0.15F;
			this.rightarm.rotationPointX += 1.5F;
			this.rightarm.rotationPointZ -= 2.5F;
			
			this.leftarm.rotateAngleX += 0.4F;
			this.leftarm.rotateAngleZ += 0.15F;
			this.leftarm.rotationPointX -= 2.5F;
			this.leftarm.rotationPointZ -= 2.5F;
			
			this.rightleg.rotationPointZ = 1.2F;
			this.rightleg.rotationPointY = 9.0F;
			
			this.leftleg.rotationPointZ = 1.2F;
			this.leftleg.rotationPointY = 9.0F;
			
			this.head.rotationPointY = 2F;
			this.head.rotationPointZ = -3F;
		}
	}

	public void setRotateAngle(RendererModel model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public RendererModel getHandRenderer()
	{
		return null;
	}

	@Override
	public RendererModel getArmRenderer()
	{
		return null;
	}
}
