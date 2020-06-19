package xyz.pixelatedw.mineminenomi.renderers.blocks;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.util.ResourceLocation;
import xyz.pixelatedw.mineminenomi.blocks.CannonBlock;
import xyz.pixelatedw.mineminenomi.blocks.DenDenMushiBlock;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.CannonTileEntity;
import xyz.pixelatedw.mineminenomi.models.blocks.CannonModel;
import xyz.pixelatedw.wypi.APIConfig;

public class CannonTileEntityRenderer extends TileEntityRenderer<CannonTileEntity>
{
	private CannonModel model;
	private static final ResourceLocation TEXTURE1 = new ResourceLocation(APIConfig.PROJECT_ID, "textures/models/blocks/cannon.png");

	public CannonTileEntityRenderer()
	{
		this.model = new CannonModel();
	}
	
	@Override
	public void render(CannonTileEntity tileEntity, double posX, double posY, double posZ, float partialTicks, int destroyStage)
	{
		BlockState state = tileEntity.getBlockState();
		
		this.bindTexture(TEXTURE1);
		
		double xRotation = 0.5;
		double zRotation = 0.5;
		
		switch(state.get(CannonBlock.FACING))
		{
			case NORTH:
				zRotation -= 0.25F;
				break;
			case SOUTH:
				zRotation += 0.25F;
				break;
			case WEST:
				xRotation -= 0.25F;
				break;
			case EAST:
				xRotation += 0.25F;
				break;
			default:
				break;
		}
		
		GL11.glPushMatrix();
		{
			GL11.glTranslated(posX + xRotation, posY + 1.5, posZ + zRotation);
			GL11.glScalef(1, 1, 1);
			GL11.glRotatef(-state.get(DenDenMushiBlock.FACING).getHorizontalAngle() + 180, 0F, 1F, 0F);
			GL11.glRotatef(180, 0F, 0F, 1F);

			this.model.render(null, 0, 0, 0, 0, 0, 0.0625F);
		}
		GL11.glPopMatrix();
	}
}
