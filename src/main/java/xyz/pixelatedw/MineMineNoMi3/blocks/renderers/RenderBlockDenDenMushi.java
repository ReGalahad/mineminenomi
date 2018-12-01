package xyz.pixelatedw.MineMineNoMi3.blocks.renderers;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.blocks.models.ModelBlockDenDenMushi;
import xyz.pixelatedw.MineMineNoMi3.blocks.tileentities.TileEntityDenDenMushi;

@SideOnly(Side.CLIENT)
public class RenderBlockDenDenMushi extends TileEntitySpecialRenderer
{
	private ModelBlockDenDenMushi model;
	private static final ResourceLocation texture = new ResourceLocation(ID.PROJECT_ID + ":textures/models/denden2.png");

	public RenderBlockDenDenMushi()
	{
		this.model = new ModelBlockDenDenMushi();
	}

	public void renderTileEntityAt(TileEntityDenDenMushi tile, double x, double y, double z, float f1)
	{
		int rotation = 0;
		if (tile.getWorldObj() != null)
			rotation = tile.getBlockMetadata();
		
		GL11.glPushMatrix();		
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
			GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
			GL11.glRotatef(180, 0F, 0F, 1F);
			
			this.bindTexture(texture);
			
			Minecraft.getMinecraft().renderEngine.bindTexture(texture);
			
			GL11.glPushMatrix();
				GL11.glRotatef(rotation * 90, 0.0F, 1.0F, 0.0F);
				this.model.render();
			GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
	
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f1)
	{
		this.renderTileEntityAt((TileEntityDenDenMushi)tile, x, y, z, f1);
	}
}