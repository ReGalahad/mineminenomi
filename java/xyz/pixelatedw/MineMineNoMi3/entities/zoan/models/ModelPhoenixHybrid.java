package xyz.pixelatedw.MineMineNoMi3.entities.zoan.models;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelPhoenixHybrid extends ModelZoanMorph 
{
	public ModelRenderer head;
	public ModelRenderer body;
	public ModelRenderer Leftarm;
	public ModelRenderer Rightarm;
	public ModelRenderer rightleg;
	public ModelRenderer leftleg;
	public ModelRenderer LeftWing1;
	public ModelRenderer LeftWing2;
	public ModelRenderer RightWing1;
	public ModelRenderer RightWing2;

	public ModelPhoenixHybrid()
	{
		this.textureWidth = 128;
		this.textureHeight = 64;
        this.rightleg = new ModelRenderer(this, 0, 16);
        this.rightleg.setRotationPoint(-2.0F, 12.0F, 0.0F);
        this.rightleg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.LeftWing1 = new ModelRenderer(this, 71, 39);
        this.LeftWing1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.LeftWing1.addBox(0.0F, 1.0F, 1.0F, 13, 10, 0, 0.0F);
        this.body = new ModelRenderer(this, 16, 16);
        this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
        this.Rightarm = new ModelRenderer(this, 100, 36);
        this.Rightarm.setRotationPoint(-3.0F, 0.0F, -0.5F);
        this.Rightarm.addBox(-13.0F, 0.0F, 0.0F, 13, 1, 1, 0.0F);
        this.setRotateAngle(Rightarm, 1.1344640137963142F, -0.2617993877991494F, -0.9948376736367678F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.RightWing1 = new ModelRenderer(this, 71, 54);
        this.RightWing1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.RightWing1.addBox(-13.0F, 1.0F, 1.0F, 13, 10, 0, 0.0F);
        this.RightWing2 = new ModelRenderer(this, 98, 52);
        this.RightWing2.setRotationPoint(-12.1F, 0.2F, 0.0F);
        this.RightWing2.addBox(-15.0F, 0.0F, 1.0F, 15, 12, 0, 0.0F);
        this.setRotateAngle(RightWing2, 0.0F, -0.0F, 0.10471975511965977F);
        this.Leftarm = new ModelRenderer(this, 71, 36);
        this.Leftarm.setRotationPoint(3.0F, 0.0F, -0.5F);
        this.Leftarm.addBox(0.0F, 0.0F, 0.0F, 13, 1, 1, 0.0F);
        this.setRotateAngle(Leftarm, 1.1344640137963142F, 0.2617993877991494F, 0.9948376736367678F);
        this.leftleg = new ModelRenderer(this, 0, 16);
        this.leftleg.setRotationPoint(2.0F, 12.0F, 0.0F);
        this.leftleg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.LeftWing2 = new ModelRenderer(this, 98, 39);
        this.LeftWing2.setRotationPoint(12.1F, 0.0F, 0.0F);
        this.LeftWing2.addBox(0.0F, 0.0F, 1.0F, 15, 12, 0, 0.0F);
        this.setRotateAngle(LeftWing2, 0.0F, -0.0F, -0.10471975511965977F);
        this.Leftarm.addChild(this.LeftWing1);
        this.Rightarm.addChild(this.RightWing1);
        this.Rightarm.addChild(this.RightWing2);
        this.Leftarm.addChild(this.LeftWing2);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.Leftarm.render(f5);
		this.leftleg.render(f5);
		this.body.render(f5);
		this.head.render(f5);
		this.rightleg.render(f5);
		this.Rightarm.render(f5);
	}

    protected double distanceMovedTotal = 0.0D;
	
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch, float scaleFactor, Entity ent)
    {  
	    double[] animationLegMovement = new double[]
				{0, 5, 10, 15, 20, 15, 10, 5, 0, -5, -10, -15, -20, -15, -10, -5, 0};       	    
	
	    double[] animationArmGroudMovement = new double[]
				{-15, -20, -25, -30, -25, -20, -15, -10, -5, 0, 5, 10, 15, 10, 5, 0, -5, -10, -15};       	
	    
	    double[] animationArmFlyMovement = new double[]
				{0, 5, 10, 15, 20, 25, 30, 35, 40, 35, 30, 25, 20, 15, 10, 5, 0, -5, -10, -15, -20, -25, -30, -35, -40, -35, -30, -25, -20, -15, -10, -5, 0};      
	    
    	updateDistanceMovedTotal(ent);
    	
        int cycleIndexLeg = (int) ((getDistanceMovedTotal() * 2) % animationLegMovement.length);
        int cycleIndexArm = (int) ((getDistanceMovedTotal() * 2) % animationArmGroudMovement.length);
        int cycleIndexArmFly = (int) ((ent.ticksExisted * 1.75) % animationArmFlyMovement.length);
        int cycleIndexLegFly = (int) ((ent.ticksExisted * 0.4) % animationLegMovement.length);

        if(!Minecraft.getMinecraft().isGamePaused())
	    {
    		if(ent.onGround)
    		{
    	    	if(ent.getDistance(ent.prevPosX, ent.prevPosY, ent.prevPosZ) > 0)
    	    	{		
    	    		leftleg.rotateAngleX = (float) degToRad(animationLegMovement[cycleIndexLeg]);
    	    		rightleg.rotateAngleX = (float) degToRad(animationLegMovement[cycleIndexLeg]) * -1;
    			    	
		    		Leftarm.rotateAngleX = (float) degToRad(65);
		    		Rightarm.rotateAngleX = (float) degToRad(65);		    		
    	    		
    	    		Leftarm.rotateAngleY = (float) degToRad(animationArmGroudMovement[cycleIndexArm]);
    	    		Rightarm.rotateAngleY = (float) degToRad(animationArmGroudMovement[cycleIndexArm]);
    	    		
		    		Leftarm.rotateAngleZ = (float) degToRad(57);
		    		Rightarm.rotateAngleZ = (float) degToRad(-57); 
    	    	}  
    	    	else
    	    	{
		    		leftleg.rotateAngleX = (float) degToRad(0);
		    		rightleg.rotateAngleX = (float) degToRad(0);
		    		
		    		Leftarm.rotateAngleX = (float) degToRad(65);
		    		Rightarm.rotateAngleX = (float) degToRad(65);
		    		
		    		Leftarm.rotateAngleY = (float) degToRad(15);
		    		Rightarm.rotateAngleY = (float) degToRad(-15);
		    		
		    		Leftarm.rotateAngleZ = (float) degToRad(57);
		    		Rightarm.rotateAngleZ = (float) degToRad(-57);   	    		
    	    	}	    		
    		}
    		else
    		{
	    		leftleg.rotateAngleX = (float) degToRad(animationLegMovement[cycleIndexLegFly]);
	    		rightleg.rotateAngleX = (float) degToRad(animationLegMovement[cycleIndexLegFly]) * -1;
		    		
		    	Leftarm.rotateAngleX = (float) degToRad(0);
		    	Rightarm.rotateAngleX = (float) degToRad(0);	
		    	
		    	Leftarm.rotateAngleY = (float) degToRad(animationArmFlyMovement[cycleIndexArmFly]);
		    	Rightarm.rotateAngleY = (float) degToRad(animationArmFlyMovement[cycleIndexArmFly]) * -1;
		    		
		    	Leftarm.rotateAngleZ = (float) degToRad(-7);
		    	Rightarm.rotateAngleZ = (float) degToRad(7);
    		}		    	
	    }
    }
	
    
    protected float degToRad(double degrees)
    {
        return (float) (degrees * (double)Math.PI / 180) ;
    }
    
    protected void updateDistanceMovedTotal(Entity e)
    {
        distanceMovedTotal += e.getDistance(e.prevPosX, e.prevPosY, e.prevPosZ);
    }
    
    protected double getDistanceMovedTotal() 
    {
        return (distanceMovedTotal);
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

	@Override
	public ModelRenderer getHandRenderer()
	{
		//GL11.glScaled(1.2, 1.2, 1);
		//GL11.glTranslated(-0.1, -0.1, 0.05);
		//GL11.glRotated(-7, 1, 0, 0);
		//GL11.glRotated(7, 0, 0, 1);
		return this.Rightarm;
	}
}
