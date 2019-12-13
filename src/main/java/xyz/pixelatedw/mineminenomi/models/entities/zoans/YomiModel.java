package xyz.pixelatedw.mineminenomi.models.entities.zoans;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;

public class YomiModel extends ZoanMorphModel
{
    public RendererModel bipedHead;
    public RendererModel bipedBody;
    public RendererModel bipedRightArm;
    public RendererModel bipedLeftArm;
    public RendererModel bipedRightLeg;
    public RendererModel bipedLeftLeg;
    public RendererModel afro;
	private int heldItemRight;
    
    public YomiModel()
    {
        this(0.0F);
    }

    public YomiModel(float f)
    {
        super();
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.bipedBody = new RendererModel(this, 16, 16);
        this.bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
        this.bipedHead = new RendererModel(this, 0, 0);
        this.bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.afro = new RendererModel(this, 50, 0);
        this.afro.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.afro.addBox(-5.5F, -17.0F, -5.5F, 11, 11, 11, 0.0F);
        this.bipedLeftArm = new RendererModel(this, 40, 16);
        this.bipedLeftArm.mirror = true;
        this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.bipedLeftArm.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, 0.0F);
        this.setRotateAngle(bipedLeftArm, 0.0F, 0.0F, -0.10000736613927509F);
        this.bipedLeftLeg = new RendererModel(this, 0, 16);
        this.bipedLeftLeg.mirror = true;
        this.bipedLeftLeg.setRotationPoint(2.0F, 12.0F, 0.1F);
        this.bipedLeftLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, 0.0F);
        this.bipedRightLeg = new RendererModel(this, 0, 16);
        this.bipedRightLeg.setRotationPoint(-2.0F, 12.0F, 0.1F);
        this.bipedRightLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, 0.0F);
        this.bipedRightArm = new RendererModel(this, 40, 16);
        this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.bipedRightArm.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, 0.0F);
        this.setRotateAngle(bipedRightArm, 0.0F, 0.0F, 0.10000736613927509F);
        //this.bipedHead.addChild(this.afro);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) 
    { 
    	setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        this.bipedRightArm.render(f5);
        this.bipedLeftArm.render(f5);
        this.bipedRightLeg.render(f5);
        this.bipedLeftLeg.render(f5);
        this.bipedHead.render(f5);
        this.bipedBody.render(f5);
    }

    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch, float scaleFactor, Entity ent)
    {
    	LivingEntity entity = ((LivingEntity)ent);
    	
		this.bipedHead.rotateAngleY = headYaw / (270F / (float) Math.PI);
		this.bipedHead.rotateAngleX = headPitch / (360F / (float) Math.PI);

		this.bipedLeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.8F * limbSwingAmount;
		this.bipedRightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.8F * limbSwingAmount;

		this.bipedRightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.4F * limbSwingAmount;
		this.bipedLeftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.4F * limbSwingAmount;
		
		this.swingProgress = entity.swingProgress;
		
		if(this.swingProgress > 0)
		{
	         float f1 = 1.0F - this.swingProgress;
	         f1 = f1 * f1;
	         f1 = f1 * f1;
	         f1 = 1.0F - f1;
	         float f2 = MathHelper.sin(f1 * (float)Math.PI);
	         float f3 = MathHelper.sin(this.swingProgress * (float)Math.PI) * -(this.bipedHead.rotateAngleX - 0.7F) * 0.75F;
	         this.bipedRightArm.rotateAngleX = (float)(this.bipedRightArm.rotateAngleX - (f2 * 1.2D + f3));
	         this.bipedRightArm.rotateAngleY += this.bipedBody.rotateAngleY * 2.0F;
	         this.bipedRightArm.rotateAngleZ += MathHelper.sin(this.swingProgress * (float)Math.PI) * -0.4F;
		}

		if (MathHelper.sqrt(ent.getDistanceSq(ent.prevPosX, ent.prevPosY, ent.prevPosZ)) <= 0.05F && !entity.isSwingInProgress)
		{
			bipedRightArm.rotateAngleX = 0;
			bipedRightArm.rotateAngleY = 0;
			bipedRightArm.rotateAngleZ = 0.1F;
		}
		else if (!entity.isSwingInProgress && MathHelper.sqrt(ent.getDistanceSq(ent.prevPosX, ent.prevPosY, ent.prevPosZ)) > 0)
		{
			bipedRightArm.rotateAngleY = 0;
			bipedRightArm.rotateAngleZ = 0.1F;
		}
		
		//if (this.heldItemRight != 0)
		//{
		//this.bipedRightArm.rotateAngleX = this.bipedRightArm.rotateAngleX * 0.5F - ((float)Math.PI / 10F) * 1;
		//}

    }
    
    public void setRotateAngle(RendererModel RendererModel, float x, float y, float z)
    {
        RendererModel.rotateAngleX = x;
        RendererModel.rotateAngleY = y;
        RendererModel.rotateAngleZ = z;
    }
    
	@Override
	public RendererModel getHandRenderer()
	{
		GL11.glTranslated(-0.1, 0, 0);
		return this.bipedRightArm;
	}

	@Override
	public RendererModel getArmRenderer()
	{
		return this.getHandRenderer();
	}
}