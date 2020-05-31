package xyz.pixelatedw.mineminenomi.models.entities.mobs.animals;

import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.entities.mobs.animals.LapahnEntity;
import xyz.pixelatedw.wypi.APIConfig;

@OnlyIn(Dist.CLIENT)
public class LapahnModel<T extends LapahnEntity> extends BipedModel<T>
{
	public RendererModel leftLeg1;
	public RendererModel leftLeg2;
	public RendererModel leftFoot;
	public RendererModel head;
	public RendererModel body1;
	public RendererModel body2;
	public RendererModel body3;
	public RendererModel body4;
	public RendererModel body5;
	public RendererModel tail;
	public RendererModel rightLeg1;
	public RendererModel rightArm1;
	public RendererModel leftArm1;
	public RendererModel wiskers;
	public RendererModel rightEar;
	public RendererModel leftEar;
	public RendererModel rightLeg2;
	public RendererModel rightFoot;
	public RendererModel rightArm2;
	public RendererModel leftArm2;

	public LapahnModel()
	{
		this.textureWidth = 140;
		this.textureHeight = 70;

		this.bipedBody.isHidden = true;
		this.bipedHead.isHidden = true;
		this.bipedHeadwear.isHidden = true;
		this.bipedLeftArm.isHidden = true;
		this.bipedLeftLeg.isHidden = true;
		this.bipedRightArm.isHidden = true;
		this.bipedRightLeg.isHidden = true;

		this.wiskers = new RendererModel(this, 92, 44);
		this.wiskers.setRotationPoint(0.0F, -8.0F, -0.1F);
		this.wiskers.addBox(-4.5F, -6.0F, -3.0F, 9, 9, 0, 0.0F);
		this.leftEar = new RendererModel(this, 25, 0);
		this.leftEar.setRotationPoint(1.7F, -4.5F, 0.0F);
		this.leftEar.addBox(-1.0F, -6.0F, -0.5F, 2, 6, 1, 0.0F);
		this.setRotateAngle(leftEar, 0.07190756518216637F, -0.17383479349863523F, 0.08866272600131193F);
		this.body4 = new RendererModel(this, 35, 54);
		this.body4.setRotationPoint(0.0F, -5.0F, 0.0F);
		this.body4.addBox(-7.0F, 0.0F, -5.0F, 14, 5, 10, 0.0F);
		this.leftFoot = new RendererModel(this, 0, 37);
		this.leftFoot.setRotationPoint(0.0F, 3.0F, 0.7F);
		this.leftFoot.addBox(-2.5F, 0.0F, -8.0F, 5, 2, 10, 0.0F);
		this.setRotateAngle(leftFoot, -0.03490658503988659F, 0.0F, 0.0F);
		this.leftLeg1 = new RendererModel(this, 0, 13);
		this.leftLeg1.setRotationPoint(4.8F, 14.9F, -2.2F);
		this.leftLeg1.addBox(-4.0F, -1.0F, -4.0F, 8, 6, 9, 0.0F);
		this.setRotateAngle(leftLeg1, -0.24434609527920614F, -0.0F, 0.0F);
		this.body1 = new RendererModel(this, 35, 0);
		this.body1.setRotationPoint(0.0F, 16.0F, 0.0F);
		this.body1.addBox(-8.0F, 0.0F, -5.5F, 16, 1, 11, 0.0F);
		this.rightEar = new RendererModel(this, 25, 0);
		this.rightEar.setRotationPoint(-1.7F, -4.5F, 0.0F);
		this.rightEar.addBox(-1.0F, -6.0F, -0.5F, 2, 6, 1, 0.0F);
		this.setRotateAngle(rightEar, 0.07190756518216637F, 0.17383479349863523F, -0.08866272600131193F);
		this.body3 = new RendererModel(this, 35, 36);
		this.body3.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.body3.addBox(-8.0F, 0.0F, -5.5F, 16, 6, 11, 0.0F);
		this.rightLeg1 = new RendererModel(this, 0, 13);
		this.rightLeg1.setRotationPoint(-4.800000190734863F, 14.899999618530273F, -2.200000047683716F);
		this.rightLeg1.addBox(-4.0F, -1.0F, -4.0F, 8, 6, 9, 0.0F);
		this.setRotateAngle(rightLeg1, -0.24434609711170194F, -0.0F, 0.0F);
		this.body2 = new RendererModel(this, 35, 13);
		this.body2.setRotationPoint(0.0F, 6.0F, 0.0F);
		this.body2.addBox(-8.5F, 0.0F, -6.0F, 17, 10, 12, 0.0F);
		this.leftLeg2 = new RendererModel(this, 0, 29);
		this.leftLeg2.setRotationPoint(0.0F, 3.3F, 3.0F);
		this.leftLeg2.addBox(-2.0F, 0.0F, -2.0F, 4, 3, 4, 0.0F);
		this.setRotateAngle(leftLeg2, 0.33161255787892263F, -0.0F, 0.0F);
		this.rightFoot = new RendererModel(this, 0, 37);
		this.rightFoot.setRotationPoint(0.0F, 3.0F, 0.7F);
		this.rightFoot.addBox(-2.5F, 0.0F, -8.0F, 5, 2, 10, 0.0F);
		this.setRotateAngle(rightFoot, -0.03490658503988659F, 0.0F, 0.0F);
		this.body5 = new RendererModel(this, 90, 0);
		this.body5.setRotationPoint(0.0F, -7.0F, 0.0F);
		this.body5.addBox(-6.5F, 0.0F, -4.5F, 13, 2, 9, 0.0F);
		this.tail = new RendererModel(this, 0, 50);
		this.tail.setRotationPoint(0.0F, 13.0F, 5.800000190734863F);
		this.tail.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 2, 0.0F);
		this.setRotateAngle(tail, -0.1047197580337524F, -0.0F, 0.0F);
		this.rightLeg2 = new RendererModel(this, 0, 29);
		this.rightLeg2.setRotationPoint(0.0F, 3.3F, 3.0F);
		this.rightLeg2.addBox(-2.0F, 0.0F, -2.0F, 4, 3, 4, 0.0F);
		this.setRotateAngle(rightLeg2, 0.33161255787892263F, -0.0F, 0.0F);
		this.head = new RendererModel(this, 0, 0);
		this.head.setRotationPoint(0.0F, -8.0F, 0.0F);
		this.head.addBox(-3.0F, -5.0F, -3.0F, 6, 6, 6, 0.0F);
		this.leftArm2 = new RendererModel(this, 94, 27);
		this.leftArm2.setRotationPoint(2.5F, 5.5F, 0.0F);
		this.leftArm2.addBox(-2.5F, 0.0F, -2.5F, 5, 9, 5, 0.0F);
		this.setRotateAngle(leftArm2, 0.0F, -0.0F, 0.12217304763960307F);
		this.rightArm1 = new RendererModel(this, 94, 13);
		this.rightArm1.setRotationPoint(-6.5F, -3.0F, 0.0F);
		this.rightArm1.addBox(-5.0F, -2.0F, -2.5F, 5, 8, 5, 0.0F);
		this.setRotateAngle(rightArm1, 0.0F, -0.0F, 0.2617993950843811F);
		this.leftArm1 = new RendererModel(this, 94, 13);
		this.leftArm1.setRotationPoint(6.5F, -3.0F, 0.0F);
		this.leftArm1.addBox(0.0F, -2.0F, -2.5F, 5, 8, 5, 0.0F);
		this.setRotateAngle(leftArm1, 0.0F, -0.0F, -0.2617993950843811F);
		this.rightArm2 = new RendererModel(this, 94, 27);
		this.rightArm2.setRotationPoint(-2.5F, 5.5F, 0.0F);
		this.rightArm2.addBox(-2.5F, 0.0F, -2.5F, 5, 9, 5, 0.0F);
		this.setRotateAngle(rightArm2, 0.0F, -0.0F, -0.12217304763960307F);
		this.head.addChild(this.leftEar);
		this.leftLeg2.addChild(this.leftFoot);
		this.head.addChild(this.rightEar);
		this.leftLeg1.addChild(this.leftLeg2);
		this.rightLeg2.addChild(this.rightFoot);
		this.rightLeg1.addChild(this.rightLeg2);
		this.leftArm1.addChild(this.leftArm2);
		this.rightArm1.addChild(this.rightArm2);
	}

	@Override
	public void render(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		this.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		super.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

		if (entity.isEnraged())
		{
			GlStateManager.enableBlend();
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glAlphaFunc(GL11.GL_GREATER, 0.1f);
			Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation(APIConfig.PROJECT_ID, "textures/models/lapahn_angry.png"));
			GL11.glColor3f(0.6F, 0F, 0F);
			GlStateManager.disableBlend();
		}

		this.wiskers.render(scale);
		this.tail.render(scale);
		this.head.render(scale);
		this.body3.render(scale);
		this.body4.render(scale);
		this.body1.render(scale);
		this.leftLeg1.render(scale);
		this.body5.render(scale);
		this.rightArm1.render(scale);
		this.rightLeg1.render(scale);
		this.leftArm1.render(scale);
		this.body2.render(scale);
		
		GL11.glColor3f(1F, 1F, 1F);
	}

	@Override
	public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch, float scaleFactor)
	{
		// Jumping animation...could use some polish later on
		if (Math.sqrt(entity.getDistanceSq(entity.prevPosX, entity.prevPosY, entity.prevPosZ)) > 0)
		{
			this.rightLeg1.rotateAngleX = 0.9F * (-0.2F + MathHelper.cos(ageInTicks * 0.45F));
			this.leftLeg1.rotateAngleX = 0.9F * (-0.2F + MathHelper.cos(ageInTicks * 0.45F));
			this.rightArm1.rotateAngleX = -0.4F * (-0.2F + MathHelper.sin(ageInTicks * 0.45F));
			this.leftArm1.rotateAngleX = -0.4F * (-0.2F + MathHelper.sin(ageInTicks * 0.45F));
			float formula = 0.5F * 0.0F - (0.9F + MathHelper.cos(ageInTicks * 0.45F));
			this.leftArm1.offsetY = formula;
			this.rightArm1.offsetY = formula;
			this.head.offsetY = formula;
			this.wiskers.offsetY = formula;
			this.body1.offsetY = formula;
			this.body2.offsetY = formula;
			this.body3.offsetY = formula;
			this.body4.offsetY = formula;
			this.body5.offsetY = formula;
			this.leftLeg1.offsetY = formula;
			this.rightLeg1.offsetY = formula;
			this.tail.offsetY = formula;
		}
		else
		{
			this.rightLeg1.rotateAngleX = (float) Math.toRadians(-17);
			this.leftLeg1.rotateAngleX = (float) Math.toRadians(-17);
			this.rightArm1.rotateAngleX = (float) Math.toRadians(0);
			this.leftArm1.rotateAngleX = (float) Math.toRadians(0);
			float formula = 0;
			this.leftArm1.offsetY = formula;
			this.rightArm1.offsetY = formula;
			this.head.offsetY = formula;
			this.wiskers.offsetY = formula;
			this.body1.offsetY = formula;
			this.body2.offsetY = formula;
			this.body3.offsetY = formula;
			this.body4.offsetY = formula;
			this.body5.offsetY = formula;
			this.leftLeg1.offsetY = formula;
			this.rightLeg1.offsetY = formula;
			this.tail.offsetY = formula;

			// Ear swing animation
			if (ageInTicks % 300 > 0 && ageInTicks % 300 < 100)
				this.leftEar.rotateAngleX = 0.1F * (0.3F + MathHelper.cos(ageInTicks * 0.55F));
			else
				this.leftEar.rotateAngleX = (float) Math.toRadians(0);
		}
	}

	public void setRotateAngle(RendererModel model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
