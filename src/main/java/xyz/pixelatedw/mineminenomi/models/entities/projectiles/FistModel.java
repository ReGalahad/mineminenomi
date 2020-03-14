package xyz.pixelatedw.mineminenomi.models.entities.projectiles;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;

public class FistModel extends EntityModel
{
    public RendererModel arm;
    public RendererModel righthand1;
    public RendererModel righthand2;
    public RendererModel rightfinger10;
    public RendererModel rightfinger20;
    public RendererModel rightfinger30;
    public RendererModel rightfinger50;
    public RendererModel rightfinger40;
    public RendererModel elbow;
    public RendererModel rightfinger11;
    public RendererModel rightfinger21;
    public RendererModel rightfinger31;
    public RendererModel rightfinger41;

	public FistModel()
	{
		this.textureWidth = 64;
		this.textureHeight = 32;
        this.arm = new RendererModel(this, 0, 0);
        this.arm.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.arm.addBox(-2.0F, -2.0F, -5.0F, 0, 0, 1, 0.0F);
        this.rightfinger50 = new RendererModel(this, 17, 14);
        this.rightfinger50.setRotationPoint(2.3F, 0.5F, -8.1F);
        this.rightfinger50.addBox(-1.0F, 0.0F, 0.0F, 2, 2, 1, 0.0F);
        this.setRotateAngle(rightfinger50, 0.06405358354819189F, 0.9545205679156988F, 1.6947147036864938F);
        this.rightfinger11 = new RendererModel(this, 17, 10);
        this.rightfinger11.setRotationPoint(0.0F, 1.8F, -0.3F);
        this.rightfinger11.addBox(-1.0F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(rightfinger11, 1.8675022996339325F, 0.0F, 0.0F);
        this.rightfinger30 = new RendererModel(this, 17, 6);
        this.rightfinger30.setRotationPoint(0.0F, -0.5F, -9.5F);
        this.rightfinger30.addBox(0.0F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(rightfinger30, 0.0F, -0.05235987755982988F, 0.0F);
        this.rightfinger21 = new RendererModel(this, 17, 10);
        this.rightfinger21.setRotationPoint(0.0F, 1.8F, -0.3F);
        this.rightfinger21.addBox(-1.0F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(rightfinger21, 1.8675022996339325F, 0.0F, 0.0F);
        this.rightfinger41 = new RendererModel(this, 17, 10);
        this.rightfinger41.setRotationPoint(0.0F, 1.8F, -0.3F);
        this.rightfinger41.addBox(0.0F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(rightfinger41, 1.8849555921538759F, 0.0F, 0.0F);
        this.righthand2 = new RendererModel(this, 28, 0);
        this.righthand2.setRotationPoint(0.9F, -0.5F, -5.9F);
        this.righthand2.addBox(0.0F, 0.0F, -0.5F, 1, 2, 2, 0.0F);
        this.setRotateAngle(righthand2, -1.5707963267948966F, -0.4363323129985824F, 0.0F);
        this.rightfinger40 = new RendererModel(this, 17, 6);
        this.rightfinger40.setRotationPoint(1.0F, -0.5F, -9.5F);
        this.rightfinger40.addBox(0.0F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(rightfinger40, 0.0F, -0.08726646259971647F, 0.0F);
        this.rightfinger10 = new RendererModel(this, 17, 6);
        this.rightfinger10.setRotationPoint(-1.0F, -0.5F, -9.5F);
        this.rightfinger10.addBox(-1.0F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(rightfinger10, 0.0F, 0.08726646259971647F, 0.0F);
        this.elbow = new RendererModel(this, 0, 0);
        this.elbow.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.elbow.addBox(-2.0F, -4.0F, -2.0F, 4, 10, 4, 0.0F);
        this.setRotateAngle(elbow, -1.5707963267948966F, 0.0F, 0.0F);
        this.rightfinger20 = new RendererModel(this, 17, 6);
        this.rightfinger20.setRotationPoint(0.0F, -0.5F, -9.5F);
        this.rightfinger20.addBox(-1.0F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(rightfinger20, 0.0F, 0.05235987755982988F, 0.0F);
        this.rightfinger31 = new RendererModel(this, 17, 10);
        this.rightfinger31.setRotationPoint(0.0F, 1.8F, -0.3F);
        this.rightfinger31.addBox(0.0F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(rightfinger31, 1.8849555921538759F, 0.0F, 0.0F);
        this.righthand1 = new RendererModel(this, 17, 0);
        this.righthand1.setRotationPoint(0.0F, -1.0F, -5.0F);
        this.righthand1.addBox(-2.0F, 0.0F, -0.5F, 4, 5, 1, 0.0F);
        this.setRotateAngle(righthand1, -1.5707963267948966F, -0.0F, 0.0F);
        this.arm.addChild(this.rightfinger50);
        this.rightfinger10.addChild(this.rightfinger11);
        this.arm.addChild(this.rightfinger30);
        this.rightfinger20.addChild(this.rightfinger21);
        this.rightfinger40.addChild(this.rightfinger41);
        this.arm.addChild(this.righthand2);
        this.arm.addChild(this.rightfinger40);
        this.arm.addChild(this.rightfinger10);
        this.arm.addChild(this.elbow);
        this.arm.addChild(this.rightfinger20);
        this.rightfinger30.addChild(this.rightfinger31);
        this.arm.addChild(this.righthand1);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		this.arm.render(f5);
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(RendererModel RendererModel, float x, float y, float z)
	{
		RendererModel.rotateAngleX = x;
		RendererModel.rotateAngleY = y;
		RendererModel.rotateAngleZ = z;
	}
}
