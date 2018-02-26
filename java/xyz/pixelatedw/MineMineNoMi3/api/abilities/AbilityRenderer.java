package xyz.pixelatedw.MineMineNoMi3.api.abilities;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class AbilityRenderer extends Render
{
	private double scaleX, scaleY, scaleZ, rotAngle, rotX, rotY, rotZ, red, blue, green, renderPosX, renderPosY, renderPosZ;
	private float alpha;
	private ModelBase model;
	private AbilityAttribute ablAttr;
	private ResourceLocation texture;
	
	public AbilityRenderer(AbilityAttribute attr) 
	{
		ablAttr = attr;
		this.texture = ablAttr.getProjectileTexture();
	}

	public void doRender(Entity entity, double par2, double par4, double par6, float par8, float par9)
	{
    	this.scaleX = ablAttr.getProjectileSize()[0];
    	this.scaleY = ablAttr.getProjectileSize()[1];
    	this.scaleZ = ablAttr.getProjectileSize()[2];

    	this.red = ablAttr.getProjectileColor().getRed();
    	this.green = ablAttr.getProjectileColor().getGreen();
    	this.blue = ablAttr.getProjectileColor().getBlue();
    	this.alpha = ablAttr.getProjectileAlpha();
    	
    	this.rotX = ablAttr.getProjectileXRotation();
    	this.rotY = ablAttr.getProjectileYRotation();
    	this.rotZ = ablAttr.getProjectileZRotation();
    	
    	this.model = ablAttr.getProjectileModel();
		
    	/*this.scaleX = 1;
    	this.scaleY = 1;
    	this.scaleZ = 1;

    	this.red = 255;
    	this.green = 0;
    	this.blue = 0;
    	this.alpha = 255;
    	
    	this.model = new ModelCube();*/
    	
    	GL11.glPushMatrix();
    	
    	GL11.glTranslatef((float)par2, (float)par4, (float)par6);
    	GL11.glDisable(GL11.GL_TEXTURE_2D);
    	GL11.glEnable(GL11.GL_BLEND);
    	GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA); 
    	
    	GL11.glRotatef(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * par9 - 90.0F, 0.0F, 1.0F, 0.0F);
    	GL11.glRotatef(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * par9, 0.0F, 0.0F, 1.0F);

    	if(rotX != 0)
    		GL11.glRotated(rotX, 1, 0, 0);
    	if(rotY != 0)
    		GL11.glRotated(rotY, 0, 1, 0);
    	if(rotZ != 0)
    		GL11.glRotated(rotZ, 0, 0, 1);

    	GL11.glColor4f((float)this.red/255, (float)this.green/255, (float)this.blue/255, this.alpha/255);
    	GL11.glScaled(this.scaleX, this.scaleY, this.scaleZ);
    	
		if(this.model != null)
			this.model.render(entity, (float)par2, (float)par4, (float)par6, 0.0F, 0.0F, 0.0625F);
		
    	GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) 
	{
		if(this.texture != null)
			return this.texture;
		else
			return null;
	}
}

