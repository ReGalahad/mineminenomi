package xyz.pixelatedw.MineMineNoMi3.entities.zoan;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.worldGovernment.models.ModelLucci;
import xyz.pixelatedw.MineMineNoMi3.entities.zoan.models.ModelZoanMorph;
import xyz.pixelatedw.MineMineNoMi3.events.customevents.DorikiEvent;
import xyz.pixelatedw.MineMineNoMi3.events.customevents.MorphRenderEvent;

public class RenderZoanMorph extends Render 
{
	private ResourceLocation texture = new ResourceLocation(ID.PROJECT_ID, "textures/models/null.png");
	private ModelZoanMorph model;
	private double scale;
	private float offset[] = new float[3];
	
	public RenderZoanMorph(ModelBase model, String texture)
	{
		this.shadowSize = 0;
		this.model = (ModelZoanMorph) model;
		this.scale = 1;
		this.texture = new ResourceLocation(ID.PROJECT_ID, "textures/models/" + texture + ".png");
		this.offset = new float[] {0, 0, 0};
	}
	
	public RenderZoanMorph(ModelBase model, String texture, double scale)
	{
		this.shadowSize = 0;
		this.model = (ModelZoanMorph) model;
		this.scale = scale;
		this.texture = new ResourceLocation(ID.PROJECT_ID, "textures/models/" + texture + ".png");
		this.offset = new float[] {0, 0, 0};
	}
	
	public RenderZoanMorph(ModelBase model, String texture, double scale, float offset[])
	{
		this.shadowSize = 0;
		this.model = (ModelZoanMorph) model;
		this.scale = scale;
		this.texture = new ResourceLocation(ID.PROJECT_ID, "textures/models/" + texture + ".png");
		this.offset = offset;
	}
	
	public void render(EntityZoanMorph entity, double x, double y, double z, float u, float v) 
	{
		MorphRenderEvent e = new MorphRenderEvent(entity);
		if (MinecraftForge.EVENT_BUS.post(e)) return;
		
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + this.offset[0], (float)y + 1.3F + this.offset[1], (float) z + this.offset[2]);

		GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
		
    	GL11.glRotatef(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * v - 180.0F, 0.0F, 1.0F, 0.0F);
    	//GL11.glRotatef(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * v, 0.0F, 0.0F, 1.0F);
		
    	GL11.glScaled(this.scale, this.scale, this.scale);
    	
		float f4 = 1.0F;
		GL11.glScalef(f4, f4, f4);
		this.bindEntityTexture(entity);
		GL11.glScalef(1.0F, 1.0F, 1.0F);

		this.model.render(entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
	}
	
    public void renderFirstPersonArm(EntityPlayer player)
    {
        float f = 1.0F;
        GL11.glColor3f(f, f, f);
        this.model.getHandRenderer().render(0.0625F);
    }
	
	public void doRender(Entity entity, double x, double y, double z, float u, float v) 
	{
		this.render((EntityZoanMorph) entity, x, y, z, u, v);
	}

	protected ResourceLocation getEntityTexture(Entity p_110775_1_) 
	{
		return texture;
	}

}
