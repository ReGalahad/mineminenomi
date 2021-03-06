package xyz.pixelatedw.mineminenomi.models.blocks;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class Dial03Model extends EntityModel
{
	public RendererModel shell1;
	public RendererModel shell2;
	public RendererModel shell3;
	public RendererModel shell4;

	public Dial03Model()
	{
		this.textureWidth = 50;
		this.textureHeight = 25;
		this.shell4 = new RendererModel(this, 19, 0);
		this.shell4.setRotationPoint(-1.2000000476837158F, 21.299999237060547F, -0.800000011920929F);
		this.shell4.addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3, 0.0F);
		this.shell3 = new RendererModel(this, 0, 16);
		this.shell3.setRotationPoint(4.0F, 22.0F, 0.0F);
		this.shell3.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F);
		this.shell1 = new RendererModel(this, 0, 0);
		this.shell1.setRotationPoint(0.0F, 22.0F, 0.0F);
		this.shell1.addBox(-2.5F, -2.0F, -2.0F, 5, 4, 4, 0.0F);
		this.shell2 = new RendererModel(this, 0, 9);
		this.shell2.setRotationPoint(2.5F, 22.0F, 0.0F);
		this.shell2.addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3, 0.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		this.shell4.render(f5);
		this.shell3.render(f5);
		this.shell1.render(f5);
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
