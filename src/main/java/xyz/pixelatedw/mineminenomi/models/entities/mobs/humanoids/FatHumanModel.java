package xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.entities.mobs.GenericNewEntity;
import xyz.pixelatedw.mineminenomi.init.ModResources;

@OnlyIn(Dist.CLIENT)
public class FatHumanModel extends BipedModel implements IHasArm
{
	public RendererModel head;
	public RendererModel body;
	public RendererModel body2;
	public RendererModel rightarm;
	public RendererModel leftarm;
	public RendererModel rightleg1;
	public RendererModel rightleg2;
	public RendererModel leftleg1;
	public RendererModel leftleg2;

	public FatHumanModel()
	{
		this.textureWidth = 64;
		this.textureHeight = 64;
		this.body = new RendererModel(this, 0, 43);
		this.body.setRotationPoint(0.0F, -1.0F, 0.0F);
		this.body.addBox(-7.0F, 0.0F, -4.0F, 14, 14, 7, 0.0F);
		this.head = new RendererModel(this, 0, 0);
		this.head.setRotationPoint(0.0F, -1.0F, 0.0F);
		this.head.addBox(-4.5F, -8.0F, -5.0F, 9, 8, 9, 0.0F);
		this.leftarm = new RendererModel(this, 40, 18);
		this.leftarm.setRotationPoint(8.0F, 1.5F, 0.0F);
		this.leftarm.addBox(-1.0F, -2.0F, -2.0F, 4, 13, 4, 0.0F);
		this.leftleg2 = new RendererModel(this, 24, 18);
		this.leftleg2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.leftleg2.addBox(-2.0F, 1.0F, -2.0F, 4, 10, 4, 0.0F);
		this.body2 = new RendererModel(this, 37, 36);
		this.body2.setRotationPoint(0.5F, 1.2F, -1.5F);
		this.body2.addBox(-7.0F, 0.0F, -3.0F, 12, 12, 1, 0.0F);
		this.rightleg1 = new RendererModel(this, 0, 18);
		this.rightleg1.setRotationPoint(-3.0F, 13.0F, 0.0F);
		this.rightleg1.addBox(-3.0F, 0.0F, -3.0F, 6, 4, 6, 0.0F);
		this.rightleg2 = new RendererModel(this, 24, 18);
		this.rightleg2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.rightleg2.addBox(-2.0F, 1.0F, -2.0F, 4, 10, 4, 0.0F);
		this.rightarm = new RendererModel(this, 40, 18);
		this.rightarm.setRotationPoint(-8.0F, 1.5F, 0.0F);
		this.rightarm.addBox(-3.0F, -2.0F, -2.0F, 4, 13, 4, 0.0F);
		this.leftleg1 = new RendererModel(this, 0, 18);
		this.leftleg1.setRotationPoint(3.0F, 13.0F, 0.0F);
		this.leftleg1.addBox(-3.0F, 0.0F, -3.0F, 6, 4, 6, 0.0F);
		this.leftleg1.addChild(this.leftleg2);
		this.body.addChild(this.body2);
		this.rightleg1.addChild(this.rightleg2);
	}

	@Override
	public void render(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		this.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

    	boolean hasHaki = ((GenericNewEntity)entity).hasBusoHaki();
    	
		this.rightleg1.render(scale);
		this.leftarm.render(scale);
		this.body.render(scale);
		this.head.render(scale);
		this.leftleg1.render(scale);	
		
		if (hasHaki)
		{
			Minecraft.getInstance().getTextureManager().bindTexture(ModResources.BUSOSHOKU_HAKI_ARM);
			this.rightarm.render(scale);
		}
		else
		{
			this.rightarm.render(scale);
		}
	}

	@Override
	public void setRotationAngles(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		super.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

		// Handles the head movement when following the mouse or when swimming
		this.head.rotateAngleY = netHeadYaw * ((float) Math.PI / 180F);
		this.head.rotateAngleX = headPitch * ((float) Math.PI / 180F);
		
		// Basic movement animations
		float f = 1.0F;
		this.rightarm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.5F * limbSwingAmount * 0.5F / f;
		this.leftarm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.5F * limbSwingAmount * 0.5F / f;
		this.rightleg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.5F * limbSwingAmount / f;
		this.leftleg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.5F * limbSwingAmount / f;
	}

	public void setRotateAngle(RendererModel model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
	@Override
	public void postRenderArm(float scale, HandSide side)
	{
		GlStateManager.rotated(Math.toDegrees(this.rightarm.rotateAngleX), 1, 0, 0);
		GlStateManager.rotated(Math.toDegrees(this.rightarm.rotateAngleY), 0, 1, 0);
		GlStateManager.rotated(Math.toDegrees(this.rightarm.rotateAngleZ), 0, 0, 1);
		GlStateManager.translated(-0.5, 0.3, -0.2);
	}
}
