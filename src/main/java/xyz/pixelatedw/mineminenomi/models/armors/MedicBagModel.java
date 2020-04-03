package xyz.pixelatedw.mineminenomi.models.armors;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MedicBagModel extends BipedModel
{
	public RendererModel backpack;
	public RendererModel backpack_2;
	public RendererModel backpack_strings;

	public MedicBagModel()
	{
		this.textureWidth = 64;
		this.textureHeight = 64;
		this.backpack_2 = new RendererModel(this, 0, 9);
		this.backpack_2.setRotationPoint(0.0F, 3.7F, 2.0F);
		this.backpack_2.addBox(-3.5F, -2.5F, -2.0F, 7, 4, 4, 0.0F);
		this.backpack_strings = new RendererModel(this, 20, 0);
		this.backpack_strings.setRotationPoint(0.0F, 3.7F, 2.0F);
		this.backpack_strings.addBox(-5.0F, -7.0F, -1.9F, 10, 9, 0, 0.0F);
		this.backpack = new RendererModel(this, 0, 0);
		this.backpack.setRotationPoint(0.0F, 3.7F, 2.0F);
		this.backpack.addBox(-2.5F, -1.5F, 0.0F, 5, 5, 3, 0.0F);
		this.backpack.addChild(this.backpack_2);
		this.backpack.addChild(this.backpack_strings);
	}

	@Override
	public void render(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		this.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

		this.backpack.render(scale);
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
