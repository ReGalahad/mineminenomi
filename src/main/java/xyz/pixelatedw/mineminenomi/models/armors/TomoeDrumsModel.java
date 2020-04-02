package xyz.pixelatedw.mineminenomi.models.armors;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.LivingEntity;

public class TomoeDrumsModel extends BipedModel
{
	public RendererModel Bar1;
	public RendererModel Bar2;
	public RendererModel Bar3;
	public RendererModel Bar4;
	public RendererModel Bar5;
	public RendererModel Bar6;
	public RendererModel Bar7;
	public RendererModel Bar8;
	public RendererModel Bar9;
	public RendererModel Bar10;
	public RendererModel Bar11;
	public RendererModel Drum1;
	public RendererModel Drum2;
	public RendererModel Drum3;
	public RendererModel Drum4;

	public TomoeDrumsModel()
	{
		this.textureWidth = 32;
		this.textureHeight = 32;
		this.Bar4 = new RendererModel(this, 0, 0);
		this.Bar4.setRotationPoint(5.0F, 1.0F, 2.0F);
		this.Bar4.addBox(0.0F, 0.0F, 0.0F, 5, 1, 1, 0.0F);
		this.setRotateAngle(Bar4, 0.0F, -0.0F, -0.8726646259971648F);
		this.Bar5 = new RendererModel(this, 0, 0);
		this.Bar5.setRotationPoint(8.0F, -2.0F, 2.0F);
		this.Bar5.addBox(0.0F, 0.0F, 0.0F, 5, 1, 1, 0.0F);
		this.setRotateAngle(Bar5, 0.0F, -0.0F, -1.6580627893946132F);
		this.Bar9 = new RendererModel(this, 0, 0);
		this.Bar9.setRotationPoint(5.7F, -10.4F, 2.0F);
		this.Bar9.addBox(0.0F, 0.0F, 0.0F, 5, 1, 1, 0.0F);
		this.setRotateAngle(Bar9, 0.0F, -0.0F, -2.792526803190927F);
		this.Bar2 = new RendererModel(this, 0, 0);
		this.Bar2.setRotationPoint(-1.0F, 3.0F, 2.0F);
		this.Bar2.addBox(-5.0F, 0.0F, 0.0F, 5, 1, 1, 0.0F);
		this.setRotateAngle(Bar2, 0.0F, -0.0F, 0.4886921905584123F);
		this.Drum2 = new RendererModel(this, 0, 5);
		this.Drum2.setRotationPoint(7.0F, -4.0F, 1.0F);
		this.Drum2.addBox(0.0F, 0.0F, 0.0F, 3, 3, 3, 0.0F);
		this.Drum1 = new RendererModel(this, 0, 5);
		this.Drum1.setRotationPoint(-10.0F, -4.0F, 1.0F);
		this.Drum1.addBox(0.0F, 0.0F, 0.0F, 3, 3, 3, 0.0F);
		this.Bar7 = new RendererModel(this, 0, 0);
		this.Bar7.setRotationPoint(7.7F, -6.5F, 2.0F);
		this.Bar7.addBox(0.0F, 0.0F, 0.0F, 5, 1, 1, 0.0F);
		this.setRotateAngle(Bar7, 0.0F, -0.0F, -2.0943951023931953F);
		this.Drum4 = new RendererModel(this, 0, 5);
		this.Drum4.setRotationPoint(4.0F, -12.0F, 1.0F);
		this.Drum4.addBox(0.0F, 0.0F, 0.0F, 3, 3, 3, 0.0F);
		this.Bar6 = new RendererModel(this, 0, 0);
		this.Bar6.setRotationPoint(-8.0F, -2.0F, 2.0F);
		this.Bar6.addBox(-5.0F, 0.0F, 0.0F, 5, 1, 1, 0.0F);
		this.setRotateAngle(Bar6, 0.0F, -0.0F, 1.6580627893946132F);
		this.Drum3 = new RendererModel(this, 0, 5);
		this.Drum3.setRotationPoint(-8.0F, -12.0F, 1.0F);
		this.Drum3.addBox(0.0F, 0.0F, 0.0F, 3, 3, 3, 0.0F);
		this.Bar10 = new RendererModel(this, 0, 0);
		this.Bar10.setRotationPoint(-5.7F, -10.4F, 2.0F);
		this.Bar10.addBox(-5.0F, 0.0F, 0.0F, 5, 1, 1, 0.0F);
		this.setRotateAngle(Bar10, 0.0F, -0.0F, 2.792526803190927F);
		this.Bar1 = new RendererModel(this, 0, 0);
		this.Bar1.setRotationPoint(1.0F, 3.0F, 2.0F);
		this.Bar1.addBox(0.0F, 0.0F, 0.0F, 5, 1, 1, 0.0F);
		this.setRotateAngle(Bar1, 0.0F, -0.0F, -0.4886921905584123F);
		this.Bar8 = new RendererModel(this, 0, 0);
		this.Bar8.setRotationPoint(-7.7F, -6.5F, 2.0F);
		this.Bar8.addBox(-5.0F, 0.0F, 0.0F, 5, 1, 1, 0.0F);
		this.setRotateAngle(Bar8, 0.0F, -0.0F, 2.0943951023931953F);
		this.Bar11 = new RendererModel(this, 0, 0);
		this.Bar11.setRotationPoint(-1.5F, -13.1F, 2.0F);
		this.Bar11.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1, 0.0F);
		this.Bar3 = new RendererModel(this, 0, 0);
		this.Bar3.setRotationPoint(-5.0F, 1.0F, 2.0F);
		this.Bar3.addBox(-5.0F, 0.0F, 0.0F, 5, 1, 1, 0.0F);
		this.setRotateAngle(Bar3, 0.0F, -0.0F, 0.8726646259971648F);
	}

	@Override
	public void render(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		this.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

		this.Bar4.render(scale);
		this.Drum1.render(scale);
		this.Bar11.render(scale);
		this.Bar2.render(scale);
		this.Bar6.render(scale);
		this.Bar10.render(scale);
		this.Bar8.render(scale);
		this.Bar9.render(scale);
		this.Bar7.render(scale);
		this.Bar5.render(scale);
		this.Bar1.render(scale);
		this.Bar3.render(scale);
		this.Drum2.render(scale);
		this.Drum4.render(scale);
		this.Drum3.render(scale);
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(RendererModel model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
