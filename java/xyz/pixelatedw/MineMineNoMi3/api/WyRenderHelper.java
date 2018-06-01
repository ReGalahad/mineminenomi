package xyz.pixelatedw.MineMineNoMi3.api;

import java.util.Random;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import xyz.pixelatedw.MineMineNoMi3.ID;

public class WyRenderHelper
{	
	public static void drawAbilityIcon(String iconName, int x, int y, int u, int v)
	{
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(ID.PROJECT_ID, "textures/items/" + iconName + ".png"));        
		Tessellator tessellator = Tessellator.instance;
	    tessellator.startDrawingQuads();    
	    tessellator.addVertexWithUV(x			, y + v			, 0, 0.0, 1.0);
	    tessellator.addVertexWithUV(x + u		, y + v			, 0, 1.0, 1.0);
	    tessellator.addVertexWithUV(x + u		, y        		, 0, 1.0, 0.0);
	    tessellator.addVertexWithUV(x			, y         	, 0, 0.0, 0.0);
	    tessellator.draw();	    
	}
	
	/*public static void drawColorOnScreen(int r, int g, int b, int alpha, double posX, double posY, double width, double height)
	{
		GlStateManager.disableTexture2D();
		Tessellator tessellator = Tessellator.getInstance();
		VertexBuffer vexBuffer = tessellator.getBuffer();	
		 
		vexBuffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
		
		vexBuffer.pos(posX, posY + height, 100).color(r, g, b, alpha).endVertex();
		vexBuffer.pos(posX + width, posY + height, 100).color(r, g, b, alpha).endVertex();
		vexBuffer.pos(posX + width, posY, 100).color(r, g, b, alpha).endVertex();
		vexBuffer.pos(posX, posY, 100).color(r, g, b, alpha).endVertex();

		tessellator.draw();
		GlStateManager.enableTexture2D();		
	}

	public static void drawTextureOnScreen()
	{
		
	}*/
	
	public static void drawEntityOnScreen(int posX, int posY, int scale, float mouseX, float mouseY, EntityLivingBase entity)
	{
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)posX, (float)posY, 50.0F);
        GL11.glScalef((float)(-scale), (float)scale, (float)scale);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        float f2 = entity.renderYawOffset;
        float f3 = entity.rotationYaw;
        float f4 = entity.rotationPitch;
        float f5 = entity.prevRotationYawHead;
        float f6 = entity.rotationYawHead;
        GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-((float)Math.atan((double)(mouseY / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
        entity.renderYawOffset = (float)Math.atan((double)(mouseX / 40.0F)) * 20.0F;
        entity.rotationYaw = (float)Math.atan((double)(mouseX / 40.0F)) * 40.0F;
        entity.rotationPitch = -((float)Math.atan((double)(mouseY / 40.0F))) * 20.0F;
        entity.rotationYawHead = entity.rotationYaw;
        entity.prevRotationYawHead = entity.rotationYaw;
        GL11.glTranslatef(0.0F, entity.yOffset, 0.0F);
        RenderManager.instance.playerViewY = 180.0F;
        RenderManager.instance.renderEntityWithPosYaw(entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
        entity.renderYawOffset = f2;
        entity.rotationYaw = f3;
        entity.rotationPitch = f4;
        entity.prevRotationYawHead = f5;
        entity.rotationYawHead = f6;
        GL11.glPopMatrix();
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
	}
	
    public static void startGlScissor(int x, int y, int width, int height)
    {
        Minecraft mc = Minecraft.getMinecraft();

        ScaledResolution scaledRes = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);

        double scaleW = (double)mc.displayWidth / scaledRes.getScaledWidth_double();
        double scaleH = (double)mc.displayHeight / scaledRes.getScaledHeight_double();

        if(width <= 0 || height <= 0)
        {
            return;
        }
        if(x < 0)
        {
            x = 0;
        }
        if(y < 0)
        {
            y = 0;
        }

        GL11.glEnable(GL11.GL_SCISSOR_TEST);

        GL11.glScissor((int)Math.floor((double)x * scaleW), (int)Math.floor((double)mc.displayHeight - ((double)(y + height) * scaleH)), (int)Math.floor((double)(x + width) * scaleW) - (int)Math.floor((double)x * scaleW), (int)Math.floor((double)mc.displayHeight - ((double)y * scaleH)) - (int)Math.floor((double)mc.displayHeight - ((double)(y + height) * scaleH))); //starts from lower left corner (minecraft starts from upper left)
    }
    
    public static void endGlScissor()
    {
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
    }

	/*public static void drawBoundingBox(Entity entity, double x, double y, double z, float entityYaw, float partialTicks)
	{
		GlStateManager.depthMask(false);
		GlStateManager.disableTexture2D();
		GlStateManager.disableLighting();
		GlStateManager.disableCull();
		GlStateManager.disableBlend();
		float f = entity.width / 2.0F;
		AxisAlignedBB axisalignedbb = entity.getEntityBoundingBox();
		AxisAlignedBB axisalignedbb1 = new AxisAlignedBB(axisalignedbb.minX - entity.posX + x,
				axisalignedbb.minY - entity.posY + y, axisalignedbb.minZ - entity.posZ + z,
				axisalignedbb.maxX - entity.posX + x, axisalignedbb.maxY - entity.posY + y,
				axisalignedbb.maxZ - entity.posZ + z);
		RenderGlobal.drawSelectionBoundingBox(axisalignedbb1, 255, 255, 255, 255);

		if (entity instanceof EntityLivingBase)
		{
			float f1 = 0.01F;
			if (entity.getParts() != null)
			{
				for (Entity part : entity.getParts())
					RenderGlobal.drawSelectionBoundingBox(part.getEntityBoundingBox(), 0, 255, 0, 255);
			} else
				RenderGlobal.drawSelectionBoundingBox(
						new AxisAlignedBB(x - (double) f, y + (double) entity.getEyeHeight() - 0.009999999776482582D,
								z - (double) f, x + (double) f,
								y + (double) entity.getEyeHeight() + 0.009999999776482582D, z + (double) f),
						255, 0, 0, 255);
		}

		Tessellator tessellator = Tessellator.getInstance();
		VertexBuffer vertexbuffer = tessellator.getBuffer();
		Vec3d vec3d = entity.getLook(partialTicks);
		vertexbuffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
		vertexbuffer.pos(x, y + (double) entity.getEyeHeight(), z).color(0, 0, 255, 255).endVertex();
		vertexbuffer.pos(x + vec3d.xCoord * 2.0D, y + (double) entity.getEyeHeight() + vec3d.yCoord * 2.0D,
				z + vec3d.zCoord * 2.0D).color(0, 0, 255, 255).endVertex();
		tessellator.draw();
		GlStateManager.enableTexture2D();
		GlStateManager.enableLighting();
		GlStateManager.enableCull();
		GlStateManager.disableBlend();
		GlStateManager.depthMask(true);
	}*/
	
}
