package xyz.pixelatedw.MineMineNoMi3.entities.mobs.pirates.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelFatPirate extends ModelBiped
{
	public ModelRenderer head;
	public ModelRenderer body;
	public ModelRenderer body2;
	public ModelRenderer rightarm;
	public ModelRenderer leftarm;
	public ModelRenderer rightleg1;
	public ModelRenderer rightleg2;
	public ModelRenderer leftleg1;
	public ModelRenderer leftleg2;

	public ModelFatPirate()
	{
		this.textureWidth = 64;
		this.textureHeight = 64;
		this.body = new ModelRenderer(this, 0, 43);
		this.body.setRotationPoint(0.0F, -1.0F, 0.0F);
		this.body.addBox(-7.0F, 0.0F, -4.0F, 14, 14, 7, 0.0F);
		this.head = new ModelRenderer(this, 0, 0);
		this.head.setRotationPoint(0.0F, -1.0F, 0.0F);
		this.head.addBox(-4.5F, -8.0F, -5.0F, 9, 8, 9, 0.0F);
		this.leftarm = new ModelRenderer(this, 40, 18);
		this.leftarm.setRotationPoint(8.0F, 1.5F, 0.0F);
		this.leftarm.addBox(-1.0F, -2.0F, -2.0F, 4, 13, 4, 0.0F);
		this.leftleg2 = new ModelRenderer(this, 24, 18);
		this.leftleg2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.leftleg2.addBox(-2.0F, 1.0F, -2.0F, 4, 10, 4, 0.0F);
		this.body2 = new ModelRenderer(this, 37, 36);
		this.body2.setRotationPoint(0.5F, 1.2F, -1.5F);
		this.body2.addBox(-7.0F, 0.0F, -3.0F, 12, 12, 1, 0.0F);
		this.rightleg1 = new ModelRenderer(this, 0, 18);
		this.rightleg1.setRotationPoint(-3.0F, 13.0F, 0.0F);
		this.rightleg1.addBox(-3.0F, 0.0F, -3.0F, 6, 4, 6, 0.0F);
		this.rightleg2 = new ModelRenderer(this, 24, 18);
		this.rightleg2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.rightleg2.addBox(-2.0F, 1.0F, -2.0F, 4, 10, 4, 0.0F);
		this.rightarm = new ModelRenderer(this, 40, 18);
		this.rightarm.setRotationPoint(-8.0F, 1.5F, 0.0F);
		this.rightarm.addBox(-3.0F, -2.0F, -2.0F, 4, 13, 4, 0.0F);
		this.leftleg1 = new ModelRenderer(this, 0, 18);
		this.leftleg1.setRotationPoint(3.0F, 13.0F, 0.0F);
		this.leftleg1.addBox(-3.0F, 0.0F, -3.0F, 6, 4, 6, 0.0F);
		this.leftleg1.addChild(this.leftleg2);
		this.body.addChild(this.body2);
		this.rightleg1.addChild(this.rightleg2);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		this.rightarm.render(f5);
		this.rightleg1.render(f5);
		this.leftarm.render(f5);
		this.body.render(f5);
		this.head.render(f5);
		this.leftleg1.render(f5);
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		// Basic movement and idle stuff, copies and modified version of ModelBiped's animaations
		this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.rightarm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 2.0F * f1 * 0.5F;
		this.leftarm.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
		this.rightarm.rotateAngleZ = 0.0F;
		this.leftarm.rotateAngleZ = 0.0F;
		this.rightleg1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
		this.leftleg1.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;
		this.rightleg1.rotateAngleY = 0.0F;
		this.leftleg1.rotateAngleY = 0.0F;

		if (this.heldItemLeft != 0)
		{
			this.leftarm.rotateAngleX = this.leftarm.rotateAngleX * 0.5F - ((float) Math.PI / 10F) * (float) this.heldItemLeft;
		}

		if (this.heldItemRight != 0)
		{
			this.rightarm.rotateAngleX = this.rightarm.rotateAngleX * 0.5F - ((float) Math.PI / 10F) * (float) this.heldItemRight;
		}

		this.rightarm.rotateAngleY = 0.0F;
		this.leftarm.rotateAngleY = 0.0F;
		float f6;
		float f7;

		this.body.rotateAngleX = 0.0F;
		this.rightleg1.rotationPointZ = 0.1F;
		this.leftleg1.rotationPointZ = 0.1F;
		this.rightleg1.rotationPointY = 12.0F;
		this.leftleg1.rotationPointY = 12.0F;
		this.head.rotationPointY = 0.0F;

		this.rightarm.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
		this.leftarm.rotateAngleZ -= MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
		this.rightarm.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
		this.leftarm.rotateAngleX -= MathHelper.sin(f2 * 0.067F) * 0.05F;
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
