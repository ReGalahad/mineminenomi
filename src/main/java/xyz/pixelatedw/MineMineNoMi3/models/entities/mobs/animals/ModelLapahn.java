package xyz.pixelatedw.MineMineNoMi3.models.entities.mobs.animals;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLapahn extends ModelBiped
{
	public ModelRenderer leftLeg1;
	public ModelRenderer leftLeg2;
	public ModelRenderer leftFoot;
	public ModelRenderer head;
	public ModelRenderer body1;
	public ModelRenderer body2;
	public ModelRenderer body3;
	public ModelRenderer body4;
	public ModelRenderer body5;
	public ModelRenderer tail;
	public ModelRenderer rightLeg1;
	public ModelRenderer rightArm1;
	public ModelRenderer leftArm1;
	public ModelRenderer rightEar;
	public ModelRenderer leftEar;
	public ModelRenderer rightLeg2;
	public ModelRenderer rightFoot;
	public ModelRenderer rightArm2;
	public ModelRenderer leftArm2;

	public ModelLapahn()
	{
		this.textureWidth = 140;
		this.textureHeight = 70;
		this.tail = new ModelRenderer(this, 0, 50);
		this.tail.setRotationPoint(0.0F, 13.0F, 5.800000190734863F);
		this.tail.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 2, 0.0F);
		this.setRotateAngle(tail, -0.1047197580337524F, -0.0F, 0.0F);
		this.head = new ModelRenderer(this, 0, 0);
		this.head.setRotationPoint(0.0F, -8.0F, 0.0F);
		this.head.addBox(-3.0F, -5.0F, -3.0F, 6, 6, 6, 0.0F);
		this.body3 = new ModelRenderer(this, 35, 36);
		this.body3.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.body3.addBox(-8.0F, 0.0F, -5.5F, 16, 6, 11, 0.0F);
		this.body4 = new ModelRenderer(this, 35, 54);
		this.body4.setRotationPoint(0.0F, -5.0F, 0.0F);
		this.body4.addBox(-7.0F, 0.0F, -5.0F, 14, 5, 10, 0.0F);
		this.body1 = new ModelRenderer(this, 35, 0);
		this.body1.setRotationPoint(0.0F, 16.0F, 0.0F);
		this.body1.addBox(-8.0F, 0.0F, -5.5F, 16, 1, 11, 0.0F);
		this.rightArm2 = new ModelRenderer(this, 94, 27);
		this.rightArm2.setRotationPoint(-2.5F, 5.5F, 0.0F);
		this.rightArm2.addBox(-2.5F, 0.0F, -2.5F, 5, 9, 5, 0.0F);
		this.setRotateAngle(rightArm2, 0.0F, -0.0F, -0.12217304763960307F);
		this.leftLeg1 = new ModelRenderer(this, 0, 13);
		this.leftLeg1.setRotationPoint(4.8F, 14.9F, -2.2F);
		this.leftLeg1.addBox(-4.0F, -1.0F, -4.0F, 8, 6, 9, 0.0F);
		this.setRotateAngle(leftLeg1, -0.24434609527920614F, -0.0F, 0.0F);
		this.rightFoot = new ModelRenderer(this, 0, 37);
		this.rightFoot.setRotationPoint(0.0F, 3.0F, 0.7F);
		this.rightFoot.addBox(-2.5F, 0.0F, -8.0F, 5, 2, 10, 0.0F);
		this.setRotateAngle(rightFoot, -0.03490658503988659F, 0.0F, 0.0F);
		this.rightLeg2 = new ModelRenderer(this, 0, 29);
		this.rightLeg2.setRotationPoint(0.0F, 3.3F, 3.0F);
		this.rightLeg2.addBox(-2.0F, 0.0F, -2.0F, 4, 3, 4, 0.0F);
		this.setRotateAngle(rightLeg2, 0.33161255787892263F, -0.0F, 0.0F);
		this.body5 = new ModelRenderer(this, 90, 0);
		this.body5.setRotationPoint(0.0F, -7.0F, 0.0F);
		this.body5.addBox(-6.5F, 0.0F, -4.5F, 13, 2, 9, 0.0F);
		this.rightArm1 = new ModelRenderer(this, 94, 13);
		this.rightArm1.setRotationPoint(-6.5F, -3.0F, 0.0F);
		this.rightArm1.addBox(-5.0F, -2.0F, -2.5F, 5, 8, 5, 0.0F);
		this.setRotateAngle(rightArm1, 0.0F, -0.0F, 0.2617993950843811F);
		this.leftEar = new ModelRenderer(this, 25, 0);
		this.leftEar.setRotationPoint(1.7F, -4.5F, 0.0F);
		this.leftEar.addBox(-1.0F, -6.0F, -0.5F, 2, 6, 1, 0.0F);
		this.setRotateAngle(leftEar, 0.07190756518216637F, -0.17383479349863523F, 0.08866272600131193F);
		this.leftFoot = new ModelRenderer(this, 0, 37);
		this.leftFoot.setRotationPoint(0.0F, 3.0F, 0.7F);
		this.leftFoot.addBox(-2.5F, 0.0F, -8.0F, 5, 2, 10, 0.0F);
		this.setRotateAngle(leftFoot, -0.03490658503988659F, 0.0F, 0.0F);
		this.rightLeg1 = new ModelRenderer(this, 0, 13);
		this.rightLeg1.setRotationPoint(-4.800000190734863F, 14.899999618530273F, -2.200000047683716F);
		this.rightLeg1.addBox(-4.0F, -1.0F, -4.0F, 8, 6, 9, 0.0F);
		this.setRotateAngle(rightLeg1, -0.24434609711170194F, -0.0F, 0.0F);
		this.leftLeg2 = new ModelRenderer(this, 0, 29);
		this.leftLeg2.setRotationPoint(0.0F, 3.3F, 3.0F);
		this.leftLeg2.addBox(-2.0F, 0.0F, -2.0F, 4, 3, 4, 0.0F);
		this.setRotateAngle(leftLeg2, 0.33161255787892263F, -0.0F, 0.0F);
		this.leftArm2 = new ModelRenderer(this, 94, 27);
		this.leftArm2.setRotationPoint(2.5F, 5.5F, 0.0F);
		this.leftArm2.addBox(-2.5F, 0.0F, -2.5F, 5, 9, 5, 0.0F);
		this.setRotateAngle(leftArm2, 0.0F, -0.0F, 0.12217304763960307F);
		this.leftArm1 = new ModelRenderer(this, 94, 13);
		this.leftArm1.setRotationPoint(6.5F, -3.0F, 0.0F);
		this.leftArm1.addBox(0.0F, -2.0F, -2.5F, 5, 8, 5, 0.0F);
		this.setRotateAngle(leftArm1, 0.0F, -0.0F, -0.2617993950843811F);
		this.rightEar = new ModelRenderer(this, 25, 0);
		this.rightEar.setRotationPoint(-1.7F, -4.5F, 0.0F);
		this.rightEar.addBox(-1.0F, -6.0F, -0.5F, 2, 6, 1, 0.0F);
		this.setRotateAngle(rightEar, 0.07190756518216637F, 0.17383479349863523F, -0.08866272600131193F);
		this.body2 = new ModelRenderer(this, 35, 13);
		this.body2.setRotationPoint(0.0F, 6.0F, 0.0F);
		this.body2.addBox(-8.5F, 0.0F, -6.0F, 17, 10, 12, 0.0F);
		this.rightArm1.addChild(this.rightArm2);
		this.rightLeg2.addChild(this.rightFoot);
		this.rightLeg1.addChild(this.rightLeg2);
		this.head.addChild(this.leftEar);
		this.leftLeg2.addChild(this.leftFoot);
		this.leftLeg1.addChild(this.leftLeg2);
		this.leftArm1.addChild(this.leftArm2);
		this.head.addChild(this.rightEar);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		this.tail.render(f5);
		this.head.render(f5);
		this.body3.render(f5);
		this.body4.render(f5);
		this.body1.render(f5);
		this.leftLeg1.render(f5);
		this.body5.render(f5);
		this.rightArm1.render(f5);
		this.rightLeg1.render(f5);
		this.leftArm1.render(f5);
		this.body2.render(f5);
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
