package xyz.pixelatedw.mineminenomi.models.blocks;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;

public class DenDenMushiBlockModel extends EntityModel
{
	// fields
	RendererModel shape1;
	RendererModel shape2;
	RendererModel shape3;
	RendererModel shape4;
	RendererModel shape5;
	RendererModel shape6;
	RendererModel shape7;
	RendererModel shape8;
	RendererModel shape9;
	RendererModel shape10;

	public DenDenMushiBlockModel()
	{
		this.textureWidth = 64;
		this.textureHeight = 64;

		this.shape1 = new RendererModel(this, 0, 0);
		this.shape1.addBox(-2F, 0F, -3F, 5, 1, 9);
		this.shape1.setRotationPoint(0F, 23F, 0F);
		this.shape1.setTextureSize(64, 64);
		this.shape1.mirror = true;
		this.setRotation(this.shape1, 0F, 0F, 0F);
		this.shape2 = new RendererModel(this, 21, 11);
		this.shape2.addBox(-1F, -3F, -3F, 3, 3, 3);
		this.shape2.setRotationPoint(0F, 23F, 0F);
		this.shape2.setTextureSize(64, 64);
		this.shape2.mirror = true;
		this.setRotation(this.shape2, 0F, 0F, 0F);
		this.shape3 = new RendererModel(this, 0, 11);
		this.shape3.addBox(-2F, -5F, 0F, 5, 5, 5);
		this.shape3.setRotationPoint(0F, 23F, 0F);
		this.shape3.setTextureSize(64, 64);
		this.shape3.mirror = true;
		this.setRotation(this.shape3, 0F, 0F, 0F);
		this.shape4 = new RendererModel(this, 29, 0);
		this.shape4.addBox(-1F, -5F, -2F, 1, 2, 1);
		this.shape4.setRotationPoint(0F, 23F, 0F);
		this.shape4.setTextureSize(64, 64);
		this.shape4.mirror = true;
		this.setRotation(this.shape4, 0F, 0F, 0F);
		this.shape5 = new RendererModel(this, 34, 3);
		this.shape5.addBox(-1.666667F, -7F, -2.5F, 2, 2, 2);
		this.shape5.setRotationPoint(0F, 23F, 0F);
		this.shape5.setTextureSize(64, 64);
		this.shape5.mirror = true;
		this.setRotation(this.shape5, 0F, 0F, 0F);
		this.shape6 = new RendererModel(this, 29, 0);
		this.shape6.addBox(1F, -5F, -2F, 1, 2, 1);
		this.shape6.setRotationPoint(0F, 23F, 0F);
		this.shape6.setTextureSize(64, 64);
		this.shape6.mirror = true;
		this.setRotation(this.shape6, 0F, 0F, 0F);
		this.shape7 = new RendererModel(this, 34, 3);
		this.shape7.addBox(0.7F, -7F, -2.5F, 2, 2, 2);
		this.shape7.setRotationPoint(0F, 23F, 0F);
		this.shape7.setTextureSize(64, 64);
		this.shape7.mirror = true;
		this.setRotation(this.shape7, 0F, 0F, 0F);
		this.shape8 = new RendererModel(this, 48, 0);
		this.shape8.addBox(-3F, -4F, 1F, 1, 3, 3);
		this.shape8.setRotationPoint(0F, 23F, 0F);
		this.shape8.setTextureSize(64, 64);
		this.shape8.mirror = true;
		this.setRotation(this.shape8, 0F, 0F, 0F);
		this.shape9 = new RendererModel(this, 48, 7);
		this.shape9.addBox(-2F, -6F, 1.5F, 5, 1, 2);
		this.shape9.setRotationPoint(0F, 23F, 0F);
		this.shape9.setTextureSize(64, 64);
		this.shape9.mirror = true;
		this.setRotation(this.shape9, 0F, 0F, 0F);
		this.shape10 = new RendererModel(this, 43, 0);
		this.shape10.addBox(-3F, -6F, 2F, 1, 2, 1);
		this.shape10.setRotationPoint(0F, 23F, 0F);
		this.shape10.setTextureSize(64, 64);
		this.shape10.mirror = true;
		this.setRotation(this.shape10, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		this.shape1.render(scale);
		this.shape2.render(scale);
		this.shape3.render(scale);
		this.shape4.render(scale);
		this.shape5.render(scale);
		this.shape6.render(scale);
		this.shape7.render(scale);
		this.shape8.render(scale);
		this.shape9.render(scale);
		this.shape10.render(scale);
	}

	private void setRotation(RendererModel model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}