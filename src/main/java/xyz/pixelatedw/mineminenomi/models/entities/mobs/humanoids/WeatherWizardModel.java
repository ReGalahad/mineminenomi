package xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WeatherWizardModel extends BipedModel
{
	public RendererModel rightArm;
	public RendererModel rightLeg;
	public RendererModel head;
	public RendererModel body;
	public RendererModel leftArm;
	public RendererModel leftLeg;
	public RendererModel hat1;
	public RendererModel beard1;
	public RendererModel hat2;
	public RendererModel hat3;
	public RendererModel hat4;
	public RendererModel hat5;
	public RendererModel beard2;
	public RendererModel rightFancyBeard1;
	public RendererModel leftFancyBeard1;

	public WeatherWizardModel()
	{
		this.textureWidth = 64;
		this.textureHeight = 64;
		this.hat5 = new RendererModel(this, 0, 39);
		this.hat5.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.hat5.addBox(-2.5F, -9.0F, -2.5F, 5, 2, 5, 0.0F);
		this.leftFancyBeard1 = new RendererModel(this, 35, 0);
		this.leftFancyBeard1.setRotationPoint(0.9F, 1.0F, 0.0F);
		this.leftFancyBeard1.addBox(0.0F, -2.0F, 0.0F, 4, 2, 1, 0.0F);
		this.setRotateAngle(leftFancyBeard1, 0.0F, 0.0F, 1.0471975511965976F);
		this.beard2 = new RendererModel(this, 35, 0);
		this.beard2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.beard2.addBox(-2.0F, 7.0F, 0.0F, 4, 2, 1, 0.0F);
		this.head = new RendererModel(this, 0, 0);
		this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
		this.beard1 = new RendererModel(this, 35, 0);
		this.beard1.setRotationPoint(0.0F, -2.0F, -5.0F);
		this.beard1.addBox(-2.5F, 0.0F, 0.0F, 5, 7, 1, 0.0F);
		this.rightArm = new RendererModel(this, 40, 16);
		this.rightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
		this.rightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
		this.rightFancyBeard1 = new RendererModel(this, 35, 0);
		this.rightFancyBeard1.setRotationPoint(-0.9F, 1.0F, 0.0F);
		this.rightFancyBeard1.addBox(-4.0F, -2.0F, 0.0F, 4, 2, 1, 0.0F);
		this.setRotateAngle(rightFancyBeard1, 0.0F, 0.0F, -1.0471975511965976F);
		this.body = new RendererModel(this, 16, 16);
		this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
		this.hat1 = new RendererModel(this, 0, 35);
		this.hat1.setRotationPoint(0.0F, -8.5F, 0.0F);
		this.hat1.addBox(-4.5F, 0.0F, -4.5F, 9, 2, 9, 0.0F);
		this.hat2 = new RendererModel(this, 0, 36);
		this.hat2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.hat2.addBox(-4.0F, -2.0F, -4.0F, 8, 2, 8, 0.0F);
		this.hat4 = new RendererModel(this, 0, 38);
		this.hat4.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.hat4.addBox(-3.0F, -7.0F, -3.0F, 6, 2, 6, 0.0F);
		this.leftLeg = new RendererModel(this, 16, 48);
		this.leftLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
		this.leftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
		this.rightLeg = new RendererModel(this, 0, 16);
		this.rightLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
		this.rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
		this.leftArm = new RendererModel(this, 32, 48);
		this.leftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
		this.leftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
		this.hat3 = new RendererModel(this, 0, 36);
		this.hat3.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.hat3.addBox(-3.5F, -5.0F, -3.5F, 7, 3, 7, 0.0F);
		this.hat4.addChild(this.hat5);
		this.beard2.addChild(this.leftFancyBeard1);
		this.beard1.addChild(this.beard2);
		this.head.addChild(this.beard1);
		this.beard2.addChild(this.rightFancyBeard1);
		this.head.addChild(this.hat1);
		this.hat1.addChild(this.hat2);
		this.hat3.addChild(this.hat4);
		this.hat2.addChild(this.hat3);
	}

	@Override
	public void render(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		this.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		super.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

		this.bipedBody = this.body;
		this.bipedHead = this.head;
		this.bipedLeftArm = this.leftArm;
		this.bipedRightArm = this.rightArm;
		this.bipedLeftLeg = this.leftLeg;
		this.bipedRightLeg = this.rightLeg;

		this.bipedHeadwear.isHidden = true;
		
        this.head.render(scale);
        this.rightArm.render(scale);
        this.body.render(scale);
        this.leftLeg.render(scale);
        this.rightLeg.render(scale);
        this.leftArm.render(scale);
	}

	public void setRotateAngle(RendererModel model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
