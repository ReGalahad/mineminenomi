package xyz.pixelatedw.mineminenomi.models.blocks;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;

public class WantedPosterModel extends EntityModel
{
	public RendererModel poster;

	public WantedPosterModel()
	{
		this.textureWidth = 64;
		this.textureHeight = 64;
        this.poster = new RendererModel(this, 0, 0);
        this.poster.setRotationPoint(10.0F, 15.0F, 0.0F);
        this.poster.addBox(-10.0F, -15.0F, 0.0F, 20, 30, 0, 1.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		this.poster.render(0.0625F);
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(RendererModel modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}