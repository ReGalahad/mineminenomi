package xyz.pixelatedw.mineminenomi.models.entities.zoans;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import xyz.pixelatedw.mineminenomi.api.ZoanMorphModel;

public class PhoenixFlyModel<T extends LivingEntity> extends ZoanMorphModel<T>
{
	public RendererModel Fire2;
	public RendererModel head1;
	public RendererModel head2;
	public RendererModel head3;
	public RendererModel neck1;
	public RendererModel beak1;
	public RendererModel beak2;
	public RendererModel beak3;
	public RendererModel beak4;
	public RendererModel body1;
	public RendererModel body2;
	public RendererModel body3;
	public RendererModel body4;
	public RendererModel Fire;
	public RendererModel rightArm1;
	public RendererModel leftArm1;
	public RendererModel RightLeg1;
	public RendererModel RightLeg2;
	public RendererModel RightFoot1;
	public RendererModel LeftLeg1;
	public RendererModel LeftLeg2;
	public RendererModel LeftFoot1;
	public RendererModel tails1;
	public RendererModel tails2;
	public RendererModel RightWing1;
	public RendererModel rightWing2;
	public RendererModel LeftWing1;
	public RendererModel leftWing2;
	public RendererModel tails11;
	public RendererModel tails22;

	public PhoenixFlyModel()
	{
		this.textureWidth = 128;
		this.textureHeight = 64;
		this.RightWing1 = new RendererModel(this, 65, 20);
		this.RightWing1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.RightWing1.addBox(-13.0F, 0.5F, 0.0F, 13, 0, 10, 0.0F);
		this.leftWing2 = new RendererModel(this, 86, 31);
		this.leftWing2.setRotationPoint(12.0F, 0.0F, 0.0F);
		this.leftWing2.addBox(0.0F, 0.5F, 0.0F, 15, 0, 12, 0.0F);
		this.setRotateAngle(leftWing2, 0.0F, 0.05235987755982988F, 0.0F);
		this.body2 = new RendererModel(this, 23, 19);
		this.body2.setRotationPoint(-4.5F, 5.0F, -7.5F);
		this.body2.addBox(0.0F, 0.0F, 0.0F, 9, 15, 4, 0.0F);
		this.setRotateAngle(body2, 1.5707963705062866F, -0.0F, 0.0F);
		this.RightFoot1 = new RendererModel(this, 118, 50);
		this.RightFoot1.setRotationPoint(-3.5F, 9.399999618530273F, 5.800000190734863F);
		this.RightFoot1.addBox(0.0F, 0.0F, 0.0F, 3, 0, 4, 0.0F);
		this.setRotateAngle(RightFoot1, 0.6981316804885863F, -0.0F, 0.0F);
		this.rightWing2 = new RendererModel(this, 55, 31);
		this.rightWing2.setRotationPoint(-12.0F, 0.0F, 0.0F);
		this.rightWing2.addBox(-15.0F, 0.5F, 0.0F, 15, 0, 12, 0.0F);
		this.setRotateAngle(rightWing2, 0.0F, -0.06981317007977318F, 0.0F);
		this.tails2 = new RendererModel(this, 110, 55);
		this.tails2.setRotationPoint(0.0F, 3.0F, 11.0F);
		this.tails2.addBox(-2.5F, 0.0F, 0.0F, 2, 0, 9, 0.0F);
		this.setRotateAngle(tails2, 0.0F, -0.08726646259971647F, 0.0F);
		this.leftArm1 = new RendererModel(this, 100, 17);
		this.leftArm1.setRotationPoint(4.0F, 2.0F, -7.0F);
		this.leftArm1.addBox(0.0F, 0.0F, 0.0F, 13, 1, 1, 0.0F);
		this.setRotateAngle(leftArm1, 0.0F, 0.06981317007977318F, 0.0F);
		this.tails1 = new RendererModel(this, 110, 55);
		this.tails1.setRotationPoint(0.0F, 3.0F, 11.0F);
		this.tails1.addBox(0.5F, 0.0F, 0.0F, 2, 0, 9, 0.0F);
		this.setRotateAngle(tails1, 0.0F, 0.08726646259971647F, 0.0F);
		this.LeftLeg1 = new RendererModel(this, 115, 44);
		this.LeftLeg1.setRotationPoint(3.0F, 5.0F, 4.0F);
		this.LeftLeg1.addBox(-2.0F, 0.0F, 0.0F, 2, 3, 2, 0.0F);
		this.setRotateAngle(LeftLeg1, 0.6981316804885863F, -0.0F, 0.0F);
		this.LeftLeg2 = new RendererModel(this, 124, 46);
		this.LeftLeg2.setRotationPoint(2.5F, 6.800000190734863F, 5.800000190734863F);
		this.LeftLeg2.addBox(-1.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
		this.setRotateAngle(LeftLeg2, 0.6981316804885863F, -0.0F, 0.0F);
		this.rightArm1 = new RendererModel(this, 71, 17);
		this.rightArm1.setRotationPoint(-4.0F, 2.0F, -7.0F);
		this.rightArm1.addBox(-13.0F, 0.0F, 0.0F, 13, 1, 1, 0.0F);
		this.setRotateAngle(rightArm1, 0.0F, -0.06981317007977318F, 0.0F);
		this.head2 = new RendererModel(this, 0, 4);
		this.head2.setRotationPoint(-1.5F, 1.2000000476837158F, -16.0F);
		this.head2.addBox(0.0F, 0.0F, 0.0F, 3, 3, 2, 0.0F);
		this.setRotateAngle(head2, 0.05235987901687623F, -0.0F, 0.0F);
		this.beak3 = new RendererModel(this, 33, 9);
		this.beak3.setRotationPoint(-0.5F, 3.0F, -17.0F);
		this.beak3.addBox(-1.0F, 0.0F, -3.0F, 1, 1, 3, 0.0F);
		this.setRotateAngle(beak3, 0.0F, -0.2617993950843811F, 0.0F);
		this.LeftWing1 = new RendererModel(this, 92, 20);
		this.LeftWing1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.LeftWing1.addBox(0.0F, 0.5F, 0.0F, 13, 0, 10, 0.0F);
		this.head1 = new RendererModel(this, 0, 0);
		this.head1.setRotationPoint(-1.5F, 2.299999952316284F, -17.700000762939453F);
		this.head1.addBox(0.0F, 0.0F, 0.0F, 3, 1, 2, 0.0F);
		this.setRotateAngle(head1, 0.5585053563117981F, -0.0F, 0.0F);
		this.Fire2 = new RendererModel(this, 78, -12);
		this.Fire2.setRotationPoint(0.0F, -3.0F, -9.0F);
		this.Fire2.addBox(0.0F, 0.0F, 0.0F, 0, 3, 18, 0.0F);
		this.body1 = new RendererModel(this, 0, 18);
		this.body1.setRotationPoint(-2.5F, 6.0F, -9.0F);
		this.body1.addBox(0.0F, 0.0F, 0.0F, 5, 18, 6, 0.0F);
		this.setRotateAngle(body1, 1.5707963705062866F, -0.0F, 0.0F);
		this.beak1 = new RendererModel(this, 33, 0);
		this.beak1.setRotationPoint(-1.0F, 3.0F, -19.0F);
		this.beak1.addBox(0.0F, 0.0F, 0.0F, 2, 1, 2, 0.0F);
		this.beak2 = new RendererModel(this, 33, 4);
		this.beak2.setRotationPoint(0.5F, 3.0F, -17.0F);
		this.beak2.addBox(0.0F, 0.0F, -3.0F, 1, 1, 3, 0.0F);
		this.setRotateAngle(beak2, 0.0F, 0.2617993950843811F, 0.0F);
		this.body3 = new RendererModel(this, 0, 43);
		this.body3.setRotationPoint(-3.5F, 5.5F, -8.0F);
		this.body3.addBox(0.0F, 0.0F, 0.0F, 7, 16, 5, 0.0F);
		this.setRotateAngle(body3, 1.5707963705062866F, -0.0F, 0.0F);
		this.neck1 = new RendererModel(this, 11, 0);
		this.neck1.setRotationPoint(-1.0F, 2.0F, -14.0F);
		this.neck1.addBox(0.0F, 0.0F, 0.0F, 2, 2, 5, 0.0F);
		this.setRotateAngle(neck1, 0.01745329238474369F, -0.0F, 0.0F);
		this.head3 = new RendererModel(this, 0, 10);
		this.head3.setRotationPoint(-1.5F, 2.200000047683716F, -17.700000762939453F);
		this.head3.addBox(0.0F, 0.0F, 0.0F, 3, 2, 2, 0.0F);
		this.setRotateAngle(head3, 0.03490658476948738F, -0.0F, 0.0F);
		this.beak4 = new RendererModel(this, 33, 14);
		this.beak4.setRotationPoint(0.0F, 3.0F, -20.799999237060547F);
		this.beak4.addBox(-1.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
		this.setRotateAngle(beak4, 0.0F, 0.7853981852531433F, 0.0F);
		this.RightLeg2 = new RendererModel(this, 124, 46);
		this.RightLeg2.setRotationPoint(-2.5F, 6.800000190734863F, 5.800000190734863F);
		this.RightLeg2.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
		this.setRotateAngle(RightLeg2, 0.6981316804885863F, -0.0F, 0.0F);
		this.RightLeg1 = new RendererModel(this, 115, 44);
		this.RightLeg1.setRotationPoint(-3.0F, 5.0F, 4.0F);
		this.RightLeg1.addBox(0.0F, 0.0F, 0.0F, 2, 3, 2, 0.0F);
		this.setRotateAngle(RightLeg1, 0.6981316804885863F, -0.0F, 0.0F);
		this.LeftFoot1 = new RendererModel(this, 118, 50);
		this.LeftFoot1.setRotationPoint(3.5F, 9.399999618530273F, 5.800000190734863F);
		this.LeftFoot1.addBox(-3.0F, 0.0F, 0.0F, 3, 0, 4, 0.0F);
		this.setRotateAngle(LeftFoot1, 0.6981316804885863F, -0.0F, 0.0F);
		this.body4 = new RendererModel(this, 25, 39);
		this.body4.setRotationPoint(-2.0F, 5.0F, -10.0F);
		this.body4.addBox(0.0F, 0.0F, 0.0F, 4, 21, 4, 0.0F);
		this.setRotateAngle(body4, 1.5707963705062866F, -0.0F, 0.0F);
		this.tails11 = new RendererModel(this, 117, 57);
		this.tails11.setRotationPoint(0.6F, 0.0F, 8.5F);
		this.tails11.addBox(0.0F, 0.0F, 0.0F, 2, 0, 7, 0.0F);
		this.setRotateAngle(tails11, 0.0F, -0.08726646259971647F, 0.0F);
		this.tails22 = new RendererModel(this, 117, 57);
		this.tails22.setRotationPoint(0.0F, 0.0F, 8.5F);
		this.tails22.addBox(-2.5F, 0.0F, 0.0F, 2, 0, 7, 0.0F);
		this.setRotateAngle(tails22, 0.0F, 0.08726646259971647F, 0.0F);
		this.Fire = new RendererModel(this, 78, 3);
		this.Fire.setRotationPoint(0.0F, -1.0F, -16.0F);
		this.Fire.addBox(0.0F, 0.0F, 0.0F, 0, 3, 7, 0.0F);
		this.rightArm1.addChild(this.RightWing1);
		this.leftArm1.addChild(this.leftWing2);
		this.rightArm1.addChild(this.rightWing2);
		this.leftArm1.addChild(this.LeftWing1);
		this.tails1.addChild(this.tails11);
		this.tails2.addChild(this.tails22);
	}

	@Override
	public void render(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		this.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

		GlStateManager.enableCull();
		
		this.body1.render(scale);
		this.leftArm1.render(scale);
		this.RightFoot1.render(scale);
		this.body4.render(scale);
		this.Fire2.render(scale);
		this.RightLeg2.render(scale);
		this.LeftLeg2.render(scale);
		this.body3.render(scale);
		this.head1.render(scale);
		this.rightArm1.render(scale);
		this.head2.render(scale);
		this.RightLeg1.render(scale);
		this.neck1.render(scale);
		this.body2.render(scale);
		this.beak3.render(scale);
		this.Fire.render(scale);
		this.LeftLeg1.render(scale);
		this.beak1.render(scale);
		this.tails1.render(scale);
		this.tails2.render(scale);
		this.beak2.render(scale);
		this.beak4.render(scale);
		this.LeftFoot1.render(scale);
		this.head3.render(scale);
		
		GlStateManager.disableCull();
	}

	@Override
	public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		super.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		
		// Handles the wing and tail ornaments animations
		this.leftArm1.rotateAngleZ = MathHelper.cos(ageInTicks * 0.4F) * 0.7F;
		this.rightArm1.rotateAngleZ = MathHelper.cos(ageInTicks * 0.4F + (float) Math.PI) * 0.7F;

		this.leftWing2.rotateAngleZ = MathHelper.cos(ageInTicks * 0.4F) * 0.4F;
		this.rightWing2.rotateAngleZ = MathHelper.cos(ageInTicks * 0.4F + (float) Math.PI) * 0.4F;

		this.tails1.rotateAngleX = 0.5F * MathHelper.cos(ageInTicks * 0.4F) * 0.3F;
		this.tails2.rotateAngleX = 0.5F * MathHelper.cos(ageInTicks * 0.4F + (float) Math.PI) * 0.3F;

		this.tails11.rotateAngleX = 0.2F * MathHelper.cos(ageInTicks * 0.5F) * 0.8F;
		this.tails22.rotateAngleX = 0.2F * MathHelper.cos(ageInTicks * 0.5F + (float) Math.PI) * 0.8F;
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
