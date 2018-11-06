package xyz.pixelatedw.MineMineNoMi3.entities.zoan;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.entities.zoan.models.ModelZoanMorph;

public class RenderZoanMorph extends Render
{
	private ResourceLocation texture = new ResourceLocation(ID.PROJECT_ID, "textures/models/null.png");
	private ModelBase model;
	private double scale;
	private float offset[] = new float[3];
	
	public RenderZoanMorph(ModelBase model, String texture)
	{
		this.shadowSize = 0;
		this.model = model;
		this.scale = 1;
		this.texture = new ResourceLocation(ID.PROJECT_ID, "textures/models/" + texture + ".png");
		this.offset = new float[] {0, 0, 0};
	}
	
	public RenderZoanMorph(ModelBase model, String texture, double scale)
	{
		this.shadowSize = 0;
		this.model = model;
		this.scale = scale;
		this.texture = new ResourceLocation(ID.PROJECT_ID, "textures/models/" + texture + ".png");
		this.offset = new float[] {0, 0, 0};
	}
	
	public RenderZoanMorph(ModelBase model, String texture, double scale, float offset[])
	{
		this.shadowSize = 0;
		this.model = model;
		this.scale = scale;
		this.texture = new ResourceLocation(ID.PROJECT_ID, "textures/models/" + texture + ".png");
		this.offset = offset;
	}

    public void renderFirstPersonArm(EntityPlayer player)
    {
        float f = 1.0F;
        GL11.glColor3f(f, f, f);
        if(this.model instanceof ModelZoanMorph && ((ModelZoanMorph) this.model).getHandRenderer() != null)
        	((ModelZoanMorph) this.model).getHandRenderer().render(0.0625F);
    }
	
	public void doRender(Entity entity, double x, double y, double z, float u, float v) 
	{
		GL11.glPushMatrix();
		
		GL11.glTranslatef((float) x + this.offset[0], (float)y + 1.3F + this.offset[1], (float) z + this.offset[2]);

		GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
    	GL11.glRotatef(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * v - 180.0F, 0.0F, 1.0F, 0.0F);
		
    	GL11.glScaled(this.scale, this.scale, this.scale);
		GL11.glScalef(1.0F, 1.0F, 1.0F);
		
		Minecraft.getMinecraft().renderEngine.bindTexture(this.getEntityTexture(entity));
		this.model.render(entity, 0.0F, 0.0F, 0F, 0F, 0F, 0.0625F);
		
		GL11.glPopMatrix();
	}

	public ResourceLocation getEntityTexture(Entity p_110775_1_) 
	{
		return texture;
	}

}
