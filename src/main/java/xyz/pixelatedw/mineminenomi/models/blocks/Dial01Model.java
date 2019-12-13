package xyz.pixelatedw.mineminenomi.models.blocks;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;

public class Dial01Model extends EntityModel
{
	public RendererModel shell1;
	public RendererModel shell2;
	public RendererModel shell3;
	public RendererModel shell4;
	public RendererModel shell5;
	public RendererModel shell6;

	public Dial01Model()
	{
		this.textureWidth = 50;
		this.textureHeight = 25;
		this.shell1 = new RendererModel(this, 0, 0);
		this.shell1.setRotationPoint(0.0F, 20.5F, 0.0F);
		this.shell1.addBox(-1.0F, -3.5F, -3.5F, 2, 7, 7, 0.0F);
		this.shell3 = new RendererModel(this, 0, 15);
		this.shell3.setRotationPoint(0.0F, 20.5F, 0.0F);
		this.shell3.addBox(-2.0F, -2.5F, -2.5F, 4, 5, 5, 0.0F);
		this.shell5 = new RendererModel(this, 38, 0);
		this.shell5.setRotationPoint(0.0F, 17.399999618530273F, 3.799999952316284F);
		this.shell5.addBox(-1.0F, -2.5F, -2.0F, 2, 2, 2, 0.0F);
		this.setRotateAngle(shell5, 0.6981316804885863F, -0.0F, 0.0F);
		this.shell4 = new RendererModel(this, 19, 15);
		this.shell4.setRotationPoint(0.0F, 20.5F, 0.0F);
		this.shell4.addBox(-2.5F, -2.0F, -2.0F, 5, 4, 4, 0.0F);
		this.shell6 = new RendererModel(this, 38, 5);
		this.shell6.setRotationPoint(0.0F, 17.0F, 2.299999952316284F);
		this.shell6.addBox(-1.0F, -1.5F, -2.0F, 2, 2, 2, 0.0F);
		this.setRotateAngle(shell6, 0.06981316953897475F, -0.0F, 0.0F);
		this.shell2 = new RendererModel(this, 19, 0);
		this.shell2.setRotationPoint(0.0F, 20.5F, 0.0F);
		this.shell2.addBox(-1.5F, -3.0F, -3.0F, 3, 6, 6, 0.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		this.shell1.render(f5);
		this.shell3.render(f5);
		this.shell5.render(f5);
		this.shell4.render(f5);
		this.shell6.render(f5);
		this.shell2.render(f5);
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
