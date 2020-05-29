package xyz.pixelatedw.mineminenomi.models.entities.mobs.animals;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DenDenMushiModel extends BipedModel
{
	private RendererModel shape1;
	private RendererModel shape2;
	private RendererModel shape3;
	private RendererModel shape4;
	private RendererModel shape5;
	private RendererModel shape6;
	private RendererModel shape7;

	public DenDenMushiModel()
	{
		this.textureWidth = 64;
		this.textureHeight = 64;
	
		this.bipedBody.isHidden = true;
		this.bipedHead.isHidden = true;
		this.bipedHeadwear.isHidden = true;
		this.bipedLeftArm.isHidden = true;
		this.bipedLeftLeg.isHidden = true;
		this.bipedRightArm.isHidden = true;
		this.bipedRightLeg.isHidden = true;
		
		this.shape1 = new RendererModel(this, 0, 0);
		this.shape1.addBox(-2.0F, 0.0F, -3.0F, 5, 1, 9);
		this.shape1.setRotationPoint(0.0F, 23.0F, 0.0F);
		this.shape1.setTextureSize(64, 64);
		this.shape1.mirror = true;
		this.setRotation(this.shape1, 0.0F, 0.0F, 0.0F);
		this.shape2 = new RendererModel(this, 21, 11);
		this.shape2.addBox(-1.0F, -3.0F, -3.0F, 3, 3, 3);
		this.shape2.setRotationPoint(0.0F, 23.0F, 0.0F);
		this.shape2.setTextureSize(64, 64);
		this.shape2.mirror = true;
		this.setRotation(this.shape2, 0.0F, 0.0F, 0.0F);
		this.shape3 = new RendererModel(this, 0, 11);
		this.shape3.addBox(-2.0F, -5.0F, 0.0F, 5, 5, 5);
		this.shape3.setRotationPoint(0.0F, 23.0F, 0.0F);
		this.shape3.setTextureSize(64, 64);
		this.shape3.mirror = true;
		this.setRotation(this.shape3, 0.0F, 0.0F, 0.0F);
		this.shape4 = new RendererModel(this, 29, 0);
		this.shape4.addBox(-1.0F, -5.0F, -2.0F, 1, 2, 1);
		this.shape4.setRotationPoint(0.0F, 23.0F, 0.0F);
		this.shape4.setTextureSize(64, 64);
		this.shape4.mirror = true;
		this.setRotation(this.shape4, 0.0F, 0.0F, 0.0F);
		this.shape5 = new RendererModel(this, 34, 3);
		this.shape5.addBox(-1.666667F, -7.0F, -2.5F, 2, 2, 2);
		this.shape5.setRotationPoint(0.0F, 23.0F, 0.0F);
		this.shape5.setTextureSize(64, 64);
		this.shape5.mirror = true;
		this.setRotation(this.shape5, 0.0F, 0.0F, 0.0F);
		this.shape6 = new RendererModel(this, 29, 0);
		this.shape6.addBox(1.0F, -5.0F, -2.0F, 1, 2, 1);
		this.shape6.setRotationPoint(0.0F, 23.0F, 0.0F);
		this.shape6.setTextureSize(64, 64);
		this.shape6.mirror = true;
		this.setRotation(this.shape6, 0.0F, 0.0F, 0.0F);
		this.shape7 = new RendererModel(this, 34, 3);
		this.shape7.addBox(0.7F, -7.0F, -2.5F, 2, 2, 2);
		this.shape7.setRotationPoint(0.0F, 23.0F, 0.0F);
		this.shape7.setTextureSize(64, 64);
		this.shape7.mirror = true;
		this.setRotation(this.shape7, 0.0F, 0.0F, 0.0F);
	}

	@Override
	public void render(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		this.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		super.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

		this.shape1.render(scale);
		this.shape2.render(scale);
		this.shape3.render(scale);
		this.shape4.render(scale);
		this.shape5.render(scale);
		this.shape6.render(scale);
		this.shape7.render(scale);
	}

	private void setRotation(RendererModel model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor)
	{
		super.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
	}
}
