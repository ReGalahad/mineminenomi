package xyz.pixelatedw.mineminenomi.renderers.blocks;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import xyz.pixelatedw.mineminenomi.models.blocks.Dial01Model;
import xyz.pixelatedw.mineminenomi.models.blocks.Dial02Model;
import xyz.pixelatedw.mineminenomi.models.blocks.Dial03Model;
import xyz.pixelatedw.mineminenomi.models.blocks.Dial04Model;
import xyz.pixelatedw.mineminenomi.values.ModValuesEnv;

public class DialTileEntityRenderer  extends TileEntityRenderer
{
	
	private EntityModel model;
	private ResourceLocation texture;

	public DialTileEntityRenderer(EntityModel model, String texture)
	{
		this.model = model;
		this.texture = new ResourceLocation(ModValuesEnv.PROJECT_ID + ":textures/models/blocks/" + texture + ".png");
	}
	
	@Override
	public void render(TileEntity tileEntity, double posX, double posY, double posZ, float partialTicks, int destroyStage)
	{
		this.bindTexture(texture);

		GL11.glPushMatrix();
		{
			if(this.model instanceof Dial01Model)
			{
				GL11.glTranslated(posX + 1.75, posY + 0.1, posZ + 0.5);
				GL11.glRotatef(270, 0F, 0F, 1F);
			}
			else if(this.model instanceof Dial02Model)
			{
				GL11.glTranslated(posX + 0.5, posY + 1.5, posZ + 0.55);
			}
			else if(this.model instanceof Dial03Model)
			{
				GL11.glTranslated(posX + 0.45, posY + 1.5, posZ + 0.5);
			}
			else if(this.model instanceof Dial04Model)
			{
				GL11.glTranslated(posX + 0.45, posY + 1.5, posZ + 0.5);
			}
			
			GL11.glRotatef(180, 1F, 0F, 0F);
			GL11.glScalef(1, 1, 1);
			
			this.model.render(null, 0, 0, 0, 0, 0, 0.0625F);
		}
		GL11.glPopMatrix();
	}
}
