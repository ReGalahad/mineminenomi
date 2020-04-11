package xyz.pixelatedw.mineminenomi.models.abilities;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;

public class PhysicalBodyModel extends EntityModel
{
	public RendererModel field_178721_j;
	public RendererModel field_78115_e;
	public RendererModel field_178722_k;
	public RendererModel field_78116_c;
	public RendererModel field_178723_h;
	public RendererModel field_178724_i;

	public PhysicalBodyModel()
	{
		this.textureWidth = 64;
		this.textureHeight = 64;
		this.field_178723_h = new RendererModel(this, 40, 16);
		this.field_178723_h.setRotationPoint(-5.0F, 2.0F, 0.0F);
		this.field_178723_h.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
		this.field_178724_i = new RendererModel(this, 32, 48);
		this.field_178724_i.setRotationPoint(5.0F, 2.0F, 0.0F);
		this.field_178724_i.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
		this.field_78115_e = new RendererModel(this, 16, 16);
		this.field_78115_e.setRotationPoint(0.0F, 12.0F, 0.0F);
		this.field_78115_e.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
		this.field_78116_c = new RendererModel(this, 0, 0);
		this.field_78116_c.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.field_78116_c.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
		this.setRotateAngle(field_78116_c, 0.7853981633974483F, 0.0F, 0.0F);
		this.field_178721_j = new RendererModel(this, 0, 16);
		this.field_178721_j.setRotationPoint(-1.9F, 22.0F, 0.0F);
		this.field_178721_j.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
		this.setRotateAngle(field_178721_j, -1.5707963267948966F, 0.0F, 0.0F);
		this.field_178722_k = new RendererModel(this, 16, 48);
		this.field_178722_k.setRotationPoint(1.9F, 22.0F, 0.0F);
		this.field_178722_k.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
		this.setRotateAngle(field_178722_k, -1.5707963267948966F, 0.0F, 0.0F);
		this.field_78115_e.addChild(this.field_178723_h);
		this.field_78115_e.addChild(this.field_178724_i);
		this.field_78115_e.addChild(this.field_78116_c);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		this.field_78115_e.render(f5);
		this.field_178721_j.render(f5);
		this.field_178722_k.render(f5);
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
