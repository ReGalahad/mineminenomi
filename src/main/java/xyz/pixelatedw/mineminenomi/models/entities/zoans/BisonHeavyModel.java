package xyz.pixelatedw.mineminenomi.models.entities.zoans;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;
import xyz.pixelatedw.mineminenomi.api.ZoanMorphModel;

public class BisonHeavyModel<T extends LivingEntity> extends ZoanMorphModel<T> implements IHasArm
{
	public RendererModel rightarm1;
	public RendererModel leftarm1;
	public RendererModel rightleg4;
	public RendererModel leftleg4;
	public RendererModel body2;
	public RendererModel rightarm2;
	public RendererModel righthull1;
	public RendererModel leftarm2;
	public RendererModel lefthull1;
	public RendererModel rightleg3;
	public RendererModel rightleg2;
	public RendererModel rightleg1;
	public RendererModel leftleg3;
	public RendererModel leftleg2;
	public RendererModel leftleg1;
	public RendererModel body1;
	public RendererModel body3;
	public RendererModel body4;
	public RendererModel body5;
	public RendererModel body6;
	public RendererModel head;
	public RendererModel righthorn1;
	public RendererModel righthorn2;
	public RendererModel lefthorn1;
	public RendererModel lefthorn2;

	public float swimAnimation;

	public BisonHeavyModel()
	{
		this.textureWidth = 128;
		this.textureHeight = 64;
		this.rightleg4 = new RendererModel(this, 9, 30);
		this.rightleg4.setRotationPoint(-3.0F, 11.6F, 0.5F);
		this.rightleg4.addBox(-1.5F, 0.0F, -1.5F, 3, 7, 3, 0.0F);
		this.setRotateAngle(rightleg4, -0.3490658503988659F, -0.0F, 0.0F);
		this.rightleg1 = new RendererModel(this, 0, 39);
		this.rightleg1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.rightleg1.addBox(-1.0F, 10.5F, -0.5F, 2, 2, 2, 0.0F);
		this.setRotateAngle(rightleg1, 0.5235987755982988F, -0.0F, 0.0F);
		this.body1 = new RendererModel(this, 17, 0);
		this.body1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.body1.addBox(-5.0F, 9.9F, -2.0F, 10, 8, 5, 0.0F);
		this.lefthull1 = new RendererModel(this, 0, 25);
		this.lefthull1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.lefthull1.addBox(2.0F, 12.0F, -3.0F, 3, 3, 1, 0.0F);
		this.setRotateAngle(lefthull1, 0.17453292519943295F, -0.0F, 0.0F);
		this.rightleg2 = new RendererModel(this, 0, 30);
		this.rightleg2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.rightleg2.addBox(-1.0F, 3.1F, 4.8F, 2, 6, 2, 0.0F);
		this.setRotateAngle(rightleg2, -2.007128639793479F, -0.0F, 0.0F);
		this.righthull1 = new RendererModel(this, 0, 25);
		this.righthull1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.righthull1.addBox(-4.3F, 12.0F, -3.0F, 3, 3, 1, 0.0F);
		this.setRotateAngle(righthull1, 0.17453292519943295F, 0.0F, 0.017453292519943295F);
		this.leftleg2 = new RendererModel(this, 0, 30);
		this.leftleg2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.leftleg2.addBox(-1.5F, 3.3F, 4.4F, 2, 6, 2, 0.0F);
		this.setRotateAngle(leftleg2, -2.007128639793479F, -0.0F, 0.0F);
		this.leftleg1 = new RendererModel(this, 0, 39);
		this.leftleg1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.leftleg1.addBox(-1.5F, 10.5F, -1.0F, 2, 2, 2, 0.0F);
		this.setRotateAngle(leftleg1, 0.5235987755982988F, -0.0F, 0.0F);
		this.leftleg3 = new RendererModel(this, 9, 41);
		this.leftleg3.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.leftleg3.addBox(-1.5F, -1.5F, -6.8F, 2, 4, 2, 0.0F);
		this.setRotateAngle(leftleg3, 1.8151424220741026F, -0.0F, 0.0F);
		this.lefthorn1 = new RendererModel(this, 115, 0);
		this.lefthorn1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.lefthorn1.addBox(1.0F, 1.0F, -6.0F, 2, 1, 1, 0.0F);
		this.setRotateAngle(lefthorn1, 0.0F, -0.0F, -0.7330382858376184F);
		this.righthorn2 = new RendererModel(this, 122, 0);
		this.righthorn2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.righthorn2.addBox(-1.3F, 2.5F, -6.0F, 2, 1, 1, 0.0F);
		this.setRotateAngle(righthorn2, 0.0F, 0.0F, 1.9198621771937625F);
		this.rightleg3 = new RendererModel(this, 9, 41);
		this.rightleg3.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.rightleg3.addBox(-1.0F, -0.9F, -6.8F, 2, 4, 2, 0.0F);
		this.setRotateAngle(rightleg3, 1.8151424220741026F, -0.0F, 0.0F);
		this.head = new RendererModel(this, 0, 0);
		this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.head.addBox(-2.0F, -1.0F, -7.0F, 4, 5, 4, 0.0F);
		this.leftarm2 = new RendererModel(this, 23, 42);
		this.leftarm2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.leftarm2.addBox(2.0F, 5.8F, -2.0F, 3, 7, 3, 0.0F);
		this.setRotateAngle(leftarm2, 0.0F, -0.0F, 0.2792526803190927F);
		this.lefthorn2 = new RendererModel(this, 122, 0);
		this.lefthorn2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.lefthorn2.addBox(-0.7F, 2.5F, -6.0F, 2, 1, 1, 0.0F);
		this.setRotateAngle(lefthorn2, 0.0F, -0.0F, -1.9198621771937625F);
		this.body6 = new RendererModel(this, 83, 24);
		this.body6.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.body6.addBox(-4.5F, 0.0F, -0.5F, 9, 10, 6, 0.0F);
		this.setRotateAngle(body6, 0.41887902047863906F, -0.0F, 0.0F);
		this.body3 = new RendererModel(this, 83, 0);
		this.body3.setRotationPoint(-3.5F, 4.0F, 0.0F);
		this.body3.addBox(-3.0F, -3.0F, -2.0F, 6, 7, 4, 0.0F);
		this.setRotateAngle(body3, 0.0F, 0.0F, -0.2617993877991494F);
		this.righthorn1 = new RendererModel(this, 115, 0);
		this.righthorn1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.righthorn1.addBox(-3.0F, 1.0F, -6.0F, 2, 1, 1, 0.0F);
		this.setRotateAngle(righthorn1, 0.0F, -0.0F, 0.7330382858376184F);
		this.leftarm1 = new RendererModel(this, 23, 30);
		this.leftarm1.setRotationPoint(5.0F, -2.6F, 0.0F);
		this.leftarm1.addBox(0.0F, 0.0F, -2.0F, 4, 7, 4, 0.0F);
		this.setRotateAngle(leftarm1, 0.0F, -0.0F, -0.20943951023931953F);
		this.leftleg4 = new RendererModel(this, 9, 30);
		this.leftleg4.setRotationPoint(3.5F, 11.600000381469727F, 1.0F);
		this.leftleg4.addBox(-2.0F, 0.0F, -2.0F, 3, 7, 3, 0.0F);
		this.setRotateAngle(leftleg4, -0.34906584024429316F, -0.0F, 0.0F);
		this.rightarm1 = new RendererModel(this, 23, 30);
		this.rightarm1.setRotationPoint(-5.0F, -2.6F, 0.0F);
		this.rightarm1.addBox(-4.0F, 0.0F, -2.0F, 4, 7, 4, 0.0F);
		this.setRotateAngle(rightarm1, 0.0F, -0.0F, 0.20943951023931953F);
		this.body2 = new RendererModel(this, 48, 0);
		this.body2.setRotationPoint(0.0F, -5.0F, 0.0F);
		this.body2.addBox(-5.5F, 0.0F, -2.5F, 11, 10, 6, 0.0F);
		this.body4 = new RendererModel(this, 83, 0);
		this.body4.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.body4.addBox(1.5F, 0.0F, -2.0F, 6, 7, 4, 0.0F);
		this.setRotateAngle(body4, 0.0F, -0.0F, 0.2617993877991494F);
		this.rightarm2 = new RendererModel(this, 23, 42);
		this.rightarm2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.rightarm2.addBox(-4.5F, 5.5F, -2.0F, 3, 7, 3, 0.0F);
		this.setRotateAngle(rightarm2, 0.0F, -0.0F, -0.2792526803190927F);
		this.body5 = new RendererModel(this, 83, 12);
		this.body5.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.body5.addBox(-3.5F, 0.0F, -5.0F, 7, 7, 4, 0.0F);
		this.setRotateAngle(body5, 0.4886921905584123F, -0.0F, 0.0F);
		this.rightleg2.addChild(this.rightleg1);
		this.body2.addChild(this.body1);
		this.leftarm2.addChild(this.lefthull1);
		this.rightleg3.addChild(this.rightleg2);
		this.rightarm2.addChild(this.righthull1);
		this.leftleg3.addChild(this.leftleg2);
		this.leftleg2.addChild(this.leftleg1);
		this.leftleg4.addChild(this.leftleg3);
		this.head.addChild(this.lefthorn1);
		this.head.addChild(this.righthorn2);
		this.rightleg4.addChild(this.rightleg3);
		this.leftarm1.addChild(this.leftarm2);
		this.head.addChild(this.lefthorn2);
		this.body2.addChild(this.body6);
		this.body2.addChild(this.body3);
		this.head.addChild(this.righthorn1);
		this.body2.addChild(this.body4);
		this.rightarm1.addChild(this.rightarm2);
		this.body2.addChild(this.body5);
	}

	@Override
	public void render(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		this.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

		this.rightleg4.render(scale);
		this.leftarm1.render(scale);
		this.leftleg4.render(scale);
		this.rightarm1.render(scale);
		this.body2.render(scale);
		this.head.render(scale);
	}
	
	@Override
	public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		super.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

		// Handles the head movement when following the mouse or when swimming
		boolean flag = entity.getTicksElytraFlying() > 4;
		boolean flag1 = entity.isActualySwimming();
		this.head.rotateAngleY = netHeadYaw * ((float) Math.PI / 180F) / 2;
		if (flag)
		{
			this.head.rotateAngleX = (-(float) Math.PI / 4F);
		}
		else if (this.swimAnimation > 0.0F)
		{
			if (flag1)
			{
				this.head.rotateAngleX = this.func_205060_a(this.head.rotateAngleX, (-(float) Math.PI / 4F), this.swimAnimation);
			}
			else
			{
				this.head.rotateAngleX = this.func_205060_a(this.head.rotateAngleX, headPitch * ((float) Math.PI / 180F), this.swimAnimation);
			}
		}
		else
		{
			this.head.rotateAngleX = headPitch * ((float) Math.PI / 180F);
			if (Math.toDegrees(this.head.rotateAngleX) > 15)
				this.head.rotateAngleX = (float) Math.toRadians(15);
			if (Math.toDegrees(this.head.rotateAngleX) < -45)
				this.head.rotateAngleX = (float) Math.toRadians(-45);
		}

		// Hanldes the arm and leg movement
		float f = 1.0F;
		if (flag)
		{
			f = (float) entity.getMotion().lengthSquared();
			f = f / 0.2F;
			f = f * f * f;
		}

		if (f < 1.0F)
		{
			f = 1.0F;
		}
		this.rightarm1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.8F * limbSwingAmount * 0.5F / f;
		this.leftarm1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.8F * limbSwingAmount * 0.5F / f;
		this.rightleg4.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.7F * limbSwingAmount / f;
		this.leftleg4.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.7F * limbSwingAmount / f;

		// Handles the punch and item use animations of the model
		this.swingProgress = entity.swingProgress;
		if (this.swingProgress > 0)
		{
			this.body2.rotateAngleY = MathHelper.sin(MathHelper.sqrt(this.swingProgress) * ((float) Math.PI * 2F)) * 0.2F;
			this.rightarm1.rotationPointZ = MathHelper.sin(this.body2.rotateAngleY) * 5.0F;
			this.rightarm1.rotationPointX = -MathHelper.cos(this.body2.rotateAngleY) * 5.0F;
			this.leftarm1.rotationPointZ = -MathHelper.sin(this.body2.rotateAngleY) * 5.0F;
			this.leftarm1.rotationPointX = MathHelper.cos(this.body2.rotateAngleY) * 5.0F;
			this.rightarm1.rotateAngleY += this.body2.rotateAngleY;
			this.leftarm1.rotateAngleY += this.body2.rotateAngleY;
			this.leftarm1.rotateAngleX += this.body2.rotateAngleY;
			float f1 = 1.0F - this.swingProgress;
			f1 = f1 * f1;
			f1 = f1 * f1;
			f1 = 1.0F - f1;
			float f2 = MathHelper.sin(f1 * (float) Math.PI);
			float f3 = MathHelper.sin(this.swingProgress * (float) Math.PI) * -(this.head.rotateAngleX - 0.7F) * 0.75F;
			this.rightarm1.rotateAngleX = (float) (this.rightarm1.rotateAngleX - (f2 * 1.2D + f3));
			this.rightarm1.rotateAngleY += this.body1.rotateAngleY * 2.0F;
			this.rightarm1.rotateAngleZ += MathHelper.sin(this.swingProgress * (float) Math.PI) * -0.4F;
		}

		// Handles the rotations and positions of individual cubes when sneaking or not
		if (entity.isSneaking())
		{
			this.body2.rotateAngleX = 0.5F;
			this.body2.rotationPointZ -= 4F;
			this.rightarm1.rotateAngleX += 0.4F;
			this.rightarm1.rotationPointZ -= 2.5F;
			this.leftarm1.rotateAngleX += 0.4F;
			this.leftarm1.rotationPointZ -= 2.5F;
			this.rightleg4.rotationPointZ = 4.0F;
			this.leftleg4.rotationPointZ = 4.5F;
			this.rightleg4.rotationPointY = 9.0F;
			this.leftleg4.rotationPointY = 9.0F;
			this.head.rotationPointY = -4F;
			this.head.rotationPointZ = -2F;
		}
		else
		{
			this.body2.rotateAngleX = 0.0F;
			this.rightleg4.rotationPointZ = 0.1F;
			this.leftleg4.rotationPointZ = 0.6F;
			this.rightleg4.rotationPointY = 12.0F;
			this.leftleg4.rotationPointY = 12.0F;
			this.head.rotationPointY = -3F;
			this.head.rotationPointZ = 1F;
		}
	}

	@Override
	public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick)
	{
		this.swimAnimation = entityIn.getSwimAnimation(partialTick);
		super.setLivingAnimations(entityIn, limbSwing, limbSwingAmount, partialTick);
	}

	public void setRotateAngle(RendererModel model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	protected float func_205060_a(float p_205060_1_, float p_205060_2_, float p_205060_3_)
	{
		float f = (p_205060_2_ - p_205060_1_) % ((float) Math.PI * 2F);
		if (f < -(float) Math.PI)
		{
			f += ((float) Math.PI * 2F);
		}

		if (f >= (float) Math.PI)
		{
			f -= ((float) Math.PI * 2F);
		}

		return p_205060_1_ + p_205060_3_ * f;
	}

	@Override
	public RendererModel getHandRenderer()
	{
		GL11.glScaled(1.2, 1.2, 1);
		GL11.glTranslated(-0.1, -0.1, 0.05);
		GL11.glRotated(-7, 1, 0, 0);
		GL11.glRotated(7, 0, 0, 1);
		return this.rightarm2;
	}

	@Override
	public RendererModel getArmRenderer()
	{
		return this.rightarm1;
	}

	@Override
	public void postRenderArm(float scale, HandSide side)
	{

	}
}
