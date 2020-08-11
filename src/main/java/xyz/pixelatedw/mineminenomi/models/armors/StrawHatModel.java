package xyz.pixelatedw.mineminenomi.models.armors;// Made with Blockbench 3.6.5
// Exported for Minecraft version 1.14
// Paste this class into your mod and generate all required imports


import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.LivingEntity;

public class StrawHatModel extends BipedModel {
	private final RendererModel Hat;

	public StrawHatModel() {
		textureWidth = 32;
		textureHeight = 32;

		Hat = new RendererModel(this);
		Hat.setRotationPoint(0.0F, 24.0F, 0.0F);
		Hat.cubeList.add(new ModelBox(Hat, 14, 1, -2.0F, -9.0F, -2.0F, 4, 1, 4, 0.0F, false));
		Hat.cubeList.add(new ModelBox(Hat, 0, 6, -3.0F, -8.0F, -3.0F, 6, 1, 6, 0.0F, false));
		Hat.cubeList.add(new ModelBox(Hat, 0, 13, -4.0F, -7.0F, -4.0F, 8, 2, 8, 0.0F, false));
		Hat.cubeList.add(new ModelBox(Hat, 0, 1, -6.0F, -5.0F, -6.0F, 12, 1, 12, 0.0F, false));
	}

	@Override
	public void render(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		scale = scale * 1.25f;

		this.bipedHeadwear = this.Hat;

		this.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);


		if(this.isSneak)
			this.Hat.offsetY = 0.2F;

		this.Hat.render(scale);
	}

	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}