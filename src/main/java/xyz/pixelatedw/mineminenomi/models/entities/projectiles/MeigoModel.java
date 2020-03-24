package xyz.pixelatedw.mineminenomi.models.entities.projectiles;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MeigoModel extends EntityModel
{
	public RendererModel leftarm;
	public RendererModel leftarm2;
	public RendererModel leftarm3;
	public RendererModel leftarm4;
	public RendererModel leftarm5;
	public RendererModel leftarm6;
	public RendererModel leftarm7;
	public RendererModel lefthand;
	public RendererModel leftfinger10;
	public RendererModel leftfinger20;
	public RendererModel leftfinger30;
	public RendererModel leftfinger40;
	public RendererModel leftfinger50;
	public RendererModel leftfinger11;
	public RendererModel leftfinger21;
	public RendererModel leftfinger31;
	public RendererModel leftfinger41;
	public RendererModel leftfinger51;

	public MeigoModel()
	{
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.leftarm3 = new RendererModel(this, 0, 21);
		this.leftarm3.setRotationPoint(0.4F, -7.3F, 0.5F);
		this.leftarm3.addBox(-0.8F, 1.0F, -0.9F, 3, 5, 3, 0.0F);
		this.leftfinger51 = new RendererModel(this, 17, 10);
		this.leftfinger51.setRotationPoint(-1.0F, 2.7F, -1.7F);
		this.leftfinger51.addBox(0.0F, 0.2F, 0.2F, 1, 2, 1, 0.0F);
		this.setRotateAngle(leftfinger51, 1.0471975511965976F, -0.0F, 0.0F);
		this.leftfinger30 = new RendererModel(this, 17, 6);
		this.leftfinger30.setRotationPoint(0.0F, 0.1F, 0.4F);
		this.leftfinger30.addBox(-0.8F, 1.0F, -2.2F, 1, 2, 1, 0.0F);
		this.setRotateAngle(leftfinger30, -0.4363323129985824F, 0.0F, 0.0F);
		this.leftfinger20 = new RendererModel(this, 17, 6);
		this.leftfinger20.setRotationPoint(0.0F, 0.1F, 0.4F);
		this.leftfinger20.addBox(0.4F, 1.0F, -2.2F, 1, 2, 1, 0.0F);
		this.setRotateAngle(leftfinger20, -0.4363323129985824F, 0.0F, 0.0F);
		this.leftfinger41 = new RendererModel(this, 17, 10);
		this.leftfinger41.setRotationPoint(-1.7F, 3.0F, -2.2F);
		this.leftfinger41.addBox(0.0F, 0.2F, 0.2F, 1, 2, 1, 0.0F);
		this.setRotateAngle(leftfinger41, 1.0471975511965976F, -0.0F, 0.0F);
		this.leftarm6 = new RendererModel(this, 26, 21);
		this.leftarm6.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.leftarm6.addBox(-0.7F, 3.5F, -0.7F, 3, 4, 3, 0.0F);
		this.leftfinger50 = new RendererModel(this, 17, 6);
		this.leftfinger50.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.leftfinger50.addBox(-1.0F, 0.6F, -1.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(leftfinger50, -0.4363323129985824F, 2.0488420089161434F, 0.40980330836826856F);
		this.leftarm2 = new RendererModel(this, 0, 21);
		this.leftarm2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.leftarm2.addBox(-2.4F, -7.5F, -2.4F, 3, 5, 3, 0.0F);
		this.leftarm7 = new RendererModel(this, 26, 21);
		this.leftarm7.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.leftarm7.addBox(-2.2F, 4.2F, -2.3F, 3, 4, 3, 0.0F);
		this.leftfinger10 = new RendererModel(this, 17, 6);
		this.leftfinger10.setRotationPoint(0.0F, 0.1F, 0.4F);
		this.leftfinger10.addBox(1.5F, 1.0F, -2.2F, 1, 2, 1, 0.0F);
		this.setRotateAngle(leftfinger10, -0.4363323129985824F, -0.091106186954104F, 0.0F);
		this.leftfinger21 = new RendererModel(this, 17, 10);
		this.leftfinger21.setRotationPoint(0.45F, 3.0F, -2.2F);
		this.leftfinger21.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
		this.setRotateAngle(leftfinger21, 1.0471975511965976F, -0.0F, 0.0F);
		this.leftarm4 = new RendererModel(this, 13, 21);
		this.leftarm4.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.leftarm4.addBox(-2.4F, -3.2F, -1.4F, 3, 5, 3, 0.0F);
		this.leftarm5 = new RendererModel(this, 13, 21);
		this.leftarm5.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.leftarm5.addBox(-0.9F, -2.1F, -2.4F, 3, 5, 3, 0.0F);
		this.leftarm = new RendererModel(this, 0, 0);
		this.leftarm.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.leftarm.addBox(-2.0F, -7.0F, -2.0F, 4, 16, 4, 0.0F);
		this.setRotateAngle(leftarm, -1.5707963267948966F, -0.0F, 0.0F);
		this.leftfinger40 = new RendererModel(this, 17, 6);
		this.leftfinger40.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.leftfinger40.addBox(-1.7F, 0.9F, -2.0F, 1, 2, 1, 0.0F);
		this.setRotateAngle(leftfinger40, -0.4363323129985824F, 0.18203784098300857F, 0.0F);
		this.lefthand = new RendererModel(this, 17, 0);
		this.lefthand.setRotationPoint(-0.5F, 8.5F, 0.0F);
		this.lefthand.addBox(-1.5F, 0.0F, -2.0F, 4, 1, 4, 0.0F);
		this.setRotateAngle(lefthand, 0.17453292519943295F, -0.0F, 0.0F);
		this.leftfinger31 = new RendererModel(this, 17, 10);
		this.leftfinger31.setRotationPoint(-0.8F, 3.0F, -2.2F);
		this.leftfinger31.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
		this.setRotateAngle(leftfinger31, 1.0471975511965976F, -0.0F, 0.0F);
		this.leftfinger11 = new RendererModel(this, 17, 10);
		this.leftfinger11.setRotationPoint(1.5F, 3.0F, -2.2F);
		this.leftfinger11.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
		this.setRotateAngle(leftfinger11, 1.0471975511965976F, -0.0F, 0.0F);
		this.leftarm.addChild(this.leftarm3);
		this.leftfinger50.addChild(this.leftfinger51);
		this.lefthand.addChild(this.leftfinger30);
		this.lefthand.addChild(this.leftfinger20);
		this.leftfinger40.addChild(this.leftfinger41);
		this.leftarm.addChild(this.leftarm6);
		this.lefthand.addChild(this.leftfinger50);
		this.leftarm.addChild(this.leftarm2);
		this.leftarm.addChild(this.leftarm7);
		this.lefthand.addChild(this.leftfinger10);
		this.leftfinger20.addChild(this.leftfinger21);
		this.leftarm.addChild(this.leftarm4);
		this.leftarm.addChild(this.leftarm5);
		this.lefthand.addChild(this.leftfinger40);
		this.leftarm.addChild(this.lefthand);
		this.leftfinger30.addChild(this.leftfinger31);
		this.leftfinger10.addChild(this.leftfinger11);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		this.leftarm.render(f5);
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
