package xyz.pixelatedw.mineminenomi.renderers.blocks;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.util.ResourceLocation;
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
		
		GL11.glPushMatrix();
		{
			GL11.glTranslated(posX + 0.5, posY + 1.5, posZ + 0.5);
			GL11.glScalef(1, 1, 1);
			GL11.glRotatef(-state.get(DenDenMushiBlock.FACING).getHorizontalAngle() + 180, 0F, 1F, 0F);
			GL11.glRotatef(180, 0F, 0F, 1F);

			this.model.render(null, 0, 0, 0, 0, 0, 0.0625F);
		}
		GL11.glPopMatrix();
	}
}
