package xyz.pixelatedw.mineminenomi.models.armors;// Made with Blockbench 3.6.5
// Exported for Minecraft version 1.14
// Paste this class into your mod and generate all required imports


import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.LivingEntity;

public class StrawHatModel extends BipedModel {
	private final RendererModel bb_main;

	public StrawHatModel() {
		textureWidth = 32;
		textureHeight = 32;

		bb_main = new RendererModel(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 12, -4.0F, -8.0F, -4.0F, 8, 3, 8, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 1, -6.0F, -5.0F, -6.0F, 12, 1, 12, 0.0F, false));
	}

	@Override
	public void render(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		scale = scale * 1.1f;

		this.bipedHeadwear = this.bb_main;

		this.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);


		if(this.isSneak)
			this.bb_main.offsetY = 0.2F;

		this.bb_main.render(scale);
	}

	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}