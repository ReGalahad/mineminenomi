package xyz.pixelatedw.mineminenomi.models.entities.projectiles;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TridentModel extends EntityModel
{
	public RendererModel spearCable;
	public RendererModel spear1;
	public RendererModel spear2;
	public RendererModel spear3;
	public RendererModel spear4;
	public RendererModel spear5;
	public RendererModel spear6;
	public RendererModel spear7;
	public RendererModel spear8;
	public RendererModel spear9;

	public TridentModel()
	{
		this.textureWidth = 128;
		this.textureHeight = 32;
		this.spear2 = new RendererModel(this, 0, 5);
		this.spear2.setRotationPoint(0.0F, 9.5F, -7.0F);
		this.spear2.addBox(-3.0F, 0.0F, 0.0F, 6, 1, 2, 0.0F);
		this.spear6 = new RendererModel(this, 13, 19);
		this.spear6.setRotationPoint(-2.0F, 9.5F, -7.0F);
		this.spear6.addBox(0.0F, 0.0F, -1.0F, 1, 1, 1, 0.0F);
		this.setRotateAngle(spear6, 3.141592653589793F, 1.570796282091413F, 3.141592653589793F);
		this.spear4 = new RendererModel(this, 0, 15);
		this.spear4.setRotationPoint(0.8999999761581421F, 9.5F, -6.0F);
		this.spear4.addBox(-0.5F, 0.0F, -5.0F, 1, 1, 5, 0.0F);
		this.setRotateAngle(spear4, 0.0F, 0.17453292012214658F, 0.0F);
		this.spear7 = new RendererModel(this, 18, 19);
		this.spear7.setRotationPoint(-3.0F, 9.5F, -8.0F);
		this.spear7.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1, 0.0F);
		this.setRotateAngle(spear7, 0.0F, 1.2217304706573486F, 0.0F);
		this.spear9 = new RendererModel(this, 18, 22);
		this.spear9.setRotationPoint(3.0F, 9.5F, -8.0F);
		this.spear9.addBox(-2.0F, 0.0F, 0.0F, 2, 1, 1, 0.0F);
		this.setRotateAngle(spear9, 0.0F, -1.2217304706573486F, 0.0F);
		this.spearCable = new RendererModel(this, 0, 0);
		this.spearCable.setRotationPoint(0.0F, 10.0F, -5.0F);
		this.spearCable.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 30, 0.0F);
		this.spear5 = new RendererModel(this, 0, 22);
		this.spear5.setRotationPoint(-0.8999999761581421F, 9.5F, -6.0F);
		this.spear5.addBox(-0.5F, 0.0F, -5.0F, 1, 1, 5, 0.0F);
		this.setRotateAngle(spear5, 0.0F, -0.17453292012214658F, 0.0F);
		this.spear1 = new RendererModel(this, 0, 0);
		this.spear1.setRotationPoint(0.0F, 10.0F, -3.0F);
		this.spear1.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 2, 0.0F);
		this.spear8 = new RendererModel(this, 13, 22);
		this.spear8.setRotationPoint(2.0F, 9.5F, -7.0F);
		this.spear8.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
		this.setRotateAngle(spear8, 3.141592653589793F, 1.570796282091413F, 3.141592653589793F);
		this.spear3 = new RendererModel(this, 0, 9);
		this.spear3.setRotationPoint(0.0F, 9.5F, -7.0F);
		this.spear3.addBox(-0.5F, 0.0F, -4.0F, 1, 1, 4, 0.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		this.spear2.render(f5);
		this.spear6.render(f5);
		this.spear4.render(f5);
		this.spear7.render(f5);
		this.spear9.render(f5);
		this.spearCable.render(f5);
		this.spear5.render(f5);
		this.spear1.render(f5);
		this.spear8.render(f5);
		this.spear3.render(f5);
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
