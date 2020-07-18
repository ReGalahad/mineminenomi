package xyz.pixelatedw.mineminenomi.models.armors;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CaptainCapeModel extends BipedModel
{
	public RendererModel capeback;
	public RendererModel caperight;
	public RendererModel caperightsholder;
	public RendererModel caperightarm;
	public RendererModel caperightsholderpad1;
	public RendererModel caperightsholderpad2;
	public RendererModel capeleft;
	public RendererModel capeleftsholder;
	public RendererModel capeleftarm;
	public RendererModel capeleftsholderpad1;
	public RendererModel capeleftsholderpad2;
	public RendererModel capefrontright;
	public RendererModel capefrontleft;
	public RendererModel capeleftcollar1;
	public RendererModel capeleftcollar2;
	public RendererModel caperightcollar1;
	public RendererModel caperightcollar2;

	public CaptainCapeModel()
	{
		this.textureWidth = 128;
		this.textureHeight = 128;
		this.capeleftsholderpad2 = new RendererModel(this, 5, 106);
		this.capeleftsholderpad2.setRotationPoint(6.4F, 0.0F, 0.0F);
		this.capeleftsholderpad2.addBox(0.0F, 0.0F, -5.5F, 5, 3, 6, 0.0F);
		this.caperightcollar1 = new RendererModel(this, 51, 81);
		this.caperightcollar1.setRotationPoint(-5.2F, -3.0F, -2.3F);
		this.caperightcollar1.addBox(0.0F, 0.0F, 0.0F, 0, 3, 5, 0.0F);
		this.setRotateAngle(caperightcollar1, -0.016929693744344994F, 0.19128808601857852F, -0.08883725892651137F);
		this.capeleft = new RendererModel(this, 40, 70);
		this.capeleft.setRotationPoint(9.0F, 0.0F, 0.0F);
		this.capeleft.addBox(0.0F, 0.0F, -5.0F, 0, 22, 5, 0.0F);
		this.capeleftsholder = new RendererModel(this, 51, 75);
		this.capeleftsholder.setRotationPoint(3.5F, -0.01F, -2.5F);
		this.capeleftsholder.addBox(0.0F, 0.0F, 0.0F, 6, 0, 5, 0.0F);
		this.capeleftcollar2 = new RendererModel(this, 51, 90);
		this.capeleftcollar2.setRotationPoint(5.2F, -3.0F, -2.3F);
		this.capeleftcollar2.addBox(0.0F, 0.0F, 0.0F, 0, 2, 5, 0.0F);
		this.setRotateAngle(capeleftcollar2, 0.02024581932313422F, -0.19093902016817965F, -0.10663961729685353F);
		this.capefrontleft = new RendererModel(this, 28, 98);
		this.capefrontleft.setRotationPoint(0.0F, 0.0F, -5.0F);
		this.capefrontleft.addBox(-3.0F, 0.0F, 0.0F, 3, 22, 0, 0.0F);
		this.caperightarm = new RendererModel(this, 35, 98);
		this.caperightarm.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.caperightarm.addBox(-3.5F, 1.0F, -5.0F, 2, 12, 4, 0.0F);
		this.capeleftsholderpad1 = new RendererModel(this, 5, 98);
		this.capeleftsholderpad1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.capeleftsholderpad1.addBox(-0.0F, -1.0F, -5.5F, 5, 1, 6, 0.0F);
		this.setRotateAngle(capeleftsholderpad1, 0.0F, -0.0F, 0.17453292519943295F);
		this.caperightsholderpad1 = new RendererModel(this, 5, 98);
		this.caperightsholderpad1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.caperightsholderpad1.addBox(-4.0F, -0.8F, -5.5F, 5, 1, 6, 0.0F);
		this.setRotateAngle(caperightsholderpad1, 0.0F, -0.0F, -0.17453292519943295F);
		this.capeleftcollar1 = new RendererModel(this, 51, 81);
		this.capeleftcollar1.setRotationPoint(5.2F, -3.0F, -2.3F);
		this.capeleftcollar1.addBox(0.0F, 0.0F, 0.0F, 0, 3, 5, 0.0F);
		this.setRotateAngle(capeleftcollar1, -0.016929693744344994F, -0.19128808601857852F, 0.08883725892651137F);
		this.caperightcollar2 = new RendererModel(this, 51, 90);
		this.caperightcollar2.setRotationPoint(-5.2F, -3.0F, -2.3F);
		this.caperightcollar2.addBox(0.0F, 0.0F, 0.0F, 0, 2, 5, 0.0F);
		this.setRotateAngle(caperightcollar2, 0.02024581932313422F, 0.19093902016817965F, 0.10663961729685353F);
		this.caperightsholderpad2 = new RendererModel(this, 5, 106);
		this.caperightsholderpad2.setRotationPoint(-6.4F, 0.0F, 0.0F);
		this.caperightsholderpad2.addBox(-4.0F, 0.0F, -5.5F, 5, 3, 6, 0.0F);
		this.caperight = new RendererModel(this, 40, 70);
		this.caperight.setRotationPoint(-8.0F, 0.0F, 0.0F);
		this.caperight.addBox(0.0F, 0.0F, -5.0F, 0, 22, 5, 0.0F);
		this.capeleftarm = new RendererModel(this, 35, 98);
		this.capeleftarm.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.capeleftarm.addBox(2.5F, 1.0F, -5.0F, 2, 12, 4, 0.0F);
		this.capefrontright = new RendererModel(this, 28, 98);
		this.capefrontright.setRotationPoint(0.0F, 0.0F, -5.0F);
		this.capefrontright.addBox(0.0F, 0.0F, 0.0F, 3, 22, 0, 0.0F);
		this.capeback = new RendererModel(this, 5, 75);
		this.capeback.setRotationPoint(-0.5F, 0.0F, 2.5F);
		this.capeback.addBox(-8.0F, 0.0F, 0.0F, 17, 22, 0, 0.0F);
		this.caperightsholder = new RendererModel(this, 51, 75);
		this.caperightsholder.setRotationPoint(-3.5F, -0.01F, -2.5F);
		this.caperightsholder.addBox(-6.0F, 0.0F, 0.0F, 6, 0, 5, 0.0F);
		this.capeback.addChild(this.capeleftsholderpad2);
		this.capeback.addChild(this.capeleft);
		this.capeleft.addChild(this.capefrontleft);
		this.caperightsholderpad2.addChild(this.caperightarm);
		this.capeleftsholderpad2.addChild(this.capeleftsholderpad1);
		this.caperightsholderpad2.addChild(this.caperightsholderpad1);
		this.capeback.addChild(this.caperightsholderpad2);
		this.capeback.addChild(this.caperight);
		this.capeleftsholderpad2.addChild(this.capeleftarm);
		this.caperight.addChild(this.capefrontright);
	}

	@Override
	public void render(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		GlStateManager.pushMatrix();
		this.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		
		GlStateManager.translated(0, -0.05, 0.1);
		
		this.capeback.render(scale);
		this.caperightsholder.render(scale);
		this.capeleftcollar1.render(scale);
		this.capeleftcollar2.render(scale);
		this.caperightcollar2.render(scale);
		this.capeleftsholder.render(scale);
		this.caperightcollar1.render(scale);
		GlStateManager.popMatrix();
	}

	@Override
	public void setRotationAngles(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch, float scaleFactor)
	{
		if (entity.shouldRenderSneaking())
			GlStateManager.rotatef(30.0F, 1.0F, 0.0F, 0.0F);

		double dist = entity.getDistanceSq(entity.prevPosX, entity.prevPosY, entity.prevPosZ);
		if(dist > 0 && dist <= 0.02)
			dist += 0.02;
		
		double angle = MathHelper.clamp((dist * 1000) - 1, 0, 70);

		boolean isMoving = dist > 0.02;
		
		if(isMoving)
			angle = angle + MathHelper.sin(MathHelper.lerp(limbSwing, entity.prevDistanceWalkedModified, entity.distanceWalkedModified)) * 4.0F;
		
		this.capeback.rotateAngleX = (float) Math.toRadians(angle);
		this.caperightsholderpad2.rotateAngleX = (float) Math.toRadians(-angle);
		this.capeleftsholderpad2.rotateAngleX = (float) Math.toRadians(-angle);
		this.caperightarm.rotateAngleX = (float) Math.toRadians(angle - (!isMoving ? 0 : 20));
		this.capeleftarm.rotateAngleX = (float) Math.toRadians(angle - (!isMoving ? 0 : 20));
	}

	public void setRotateAngle(RendererModel model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
