package xyz.pixelatedw.mineminenomi.models.entities.mobs;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.LivingEntity;

public class SniperTargetModel extends BipedModel<LivingEntity>
{
	public RendererModel parachute;
	public RendererModel target;
	public RendererModel parachuteChild;
	public RendererModel parachuteChild_1;
	public RendererModel parachuteChild_2;
	public RendererModel parachuteChild_3;
	public RendererModel parachuteChild_4;
	public RendererModel parachuteChild_5;
	public RendererModel parachuteChild_6;
	public RendererModel parachuteChild_7;
	public RendererModel parachuteChild_8;
	public RendererModel parachuteChild_9;
	public RendererModel parachuteChild_10;
	public RendererModel parachuteChild_11;

	public SniperTargetModel()
	{
		this.textureWidth = 64;
		this.textureHeight = 64;
		this.parachuteChild_4 = new RendererModel(this, 5, 30);
		this.parachuteChild_4.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.parachuteChild_4.addBox(-4.0F, -11.5F, -9.0F, 8, 1, 11, 0.0F);
		this.setRotateAngle(parachuteChild_4, 0.2617993950843811F, 1.5707963705062866F, 0.0F);
		this.parachuteChild_11 = new RendererModel(this, 5, 30);
		this.parachuteChild_11.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.parachuteChild_11.addBox(-4.0F, -11.5F, -9.0F, 8, 1, 11, 0.0F);
		this.setRotateAngle(parachuteChild_11, 0.2617993950843811F, 3.1415927410125732F, 0.0F);
		this.parachuteChild_3 = new RendererModel(this, 0, 30);
		this.parachuteChild_3.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.parachuteChild_3.addBox(0.0F, -11.0F, -1.0F, 1, 12, 1, 0.0F);
		this.setRotateAngle(parachuteChild_3, 0.43633231520652765F, 0.0F, 0.0F);
		this.parachuteChild_9 = new RendererModel(this, 5, 30);
		this.parachuteChild_9.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.parachuteChild_9.addBox(-4.0F, -11.5F, -8.0F, 8, 1, 10, 0.0F);
		this.setRotateAngle(parachuteChild_9, 0.2617993950843811F, 2.356194496154785F, 0.0F);
		this.parachuteChild_7 = new RendererModel(this, 0, 30);
		this.parachuteChild_7.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.parachuteChild_7.addBox(0.0F, -11.0F, -1.0F, 1, 12, 1, 0.0F);
		this.setRotateAngle(parachuteChild_7, 0.43633231520652765F, 1.5707963705062866F, 0.0F);
		this.parachuteChild_5 = new RendererModel(this, 5, 30);
		this.parachuteChild_5.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.parachuteChild_5.addBox(-4.0F, -11.5F, -8.0F, 8, 1, 10, 0.0F);
		this.setRotateAngle(parachuteChild_5, 0.2617993950843811F, 3.9269907474517822F, 0.0F);
		this.parachute = new RendererModel(this, 0, 30);
		this.parachute.setRotationPoint(0.0F, 19.0F, 0.0F);
		this.parachute.addBox(-0.5F, 0.0F, -0.5F, 1, 1, 1, 0.0F);
		this.target = new RendererModel(this, 0, 0);
		this.target.setRotationPoint(0.0F, 20.0F, 0.0F);
		this.target.addBox(-4.0F, 0.0F, -0.5F, 8, 8, 1, 0.0F);
		this.parachuteChild_1 = new RendererModel(this, 5, 30);
		this.parachuteChild_1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.parachuteChild_1.addBox(-4.0F, -11.5F, -8.0F, 8, 1, 10, 0.0F);
		this.setRotateAngle(parachuteChild_1, 0.2617993950843811F, 0.7853981852531433F, 0.0F);
		this.parachuteChild_10 = new RendererModel(this, 0, 30);
		this.parachuteChild_10.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.parachuteChild_10.addBox(0.0F, -11.0F, -1.0F, 1, 12, 1, 0.0F);
		this.setRotateAngle(parachuteChild_10, 0.43633231520652765F, -1.5707963705062866F, 0.0F);
		this.parachuteChild_8 = new RendererModel(this, 5, 30);
		this.parachuteChild_8.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.parachuteChild_8.addBox(-4.0F, -11.5F, -9.0F, 8, 1, 11, 0.0F);
		this.setRotateAngle(parachuteChild_8, 0.2617993950843811F, 0.0F, 0.0F);
		this.parachuteChild = new RendererModel(this, 5, 30);
		this.parachuteChild.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.parachuteChild.addBox(-4.0F, -11.5F, -8.0F, 8, 1, 10, 0.0F);
		this.setRotateAngle(parachuteChild, 0.2617993950843811F, 5.497786998748779F, 0.0F);
		this.parachuteChild_2 = new RendererModel(this, 5, 30);
		this.parachuteChild_2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.parachuteChild_2.addBox(-4.0F, -11.5F, -9.0F, 8, 1, 11, 0.0F);
		this.setRotateAngle(parachuteChild_2, 0.2617993950843811F, 4.71238899230957F, 0.0F);
		this.parachuteChild_6 = new RendererModel(this, 0, 30);
		this.parachuteChild_6.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.parachuteChild_6.addBox(0.0F, -11.0F, -1.0F, 1, 12, 1, 0.0F);
		this.setRotateAngle(parachuteChild_6, 0.43633231520652765F, 3.1415927410125732F, 0.0F);
		this.parachute.addChild(this.parachuteChild_4);
		this.parachute.addChild(this.parachuteChild_11);
		this.parachute.addChild(this.parachuteChild_3);
		this.parachute.addChild(this.parachuteChild_9);
		this.parachute.addChild(this.parachuteChild_7);
		this.parachute.addChild(this.parachuteChild_5);
		this.parachute.addChild(this.parachuteChild_1);
		this.parachute.addChild(this.parachuteChild_10);
		this.parachute.addChild(this.parachuteChild_8);
		this.parachute.addChild(this.parachuteChild);
		this.parachute.addChild(this.parachuteChild_2);
		this.parachute.addChild(this.parachuteChild_6);
	}

	@Override
	public void render(LivingEntity entity, float f, float f1, float f2, float f3, float f4, float scale)
	{
		this.parachute.render(scale);
		this.target.render(scale);
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
