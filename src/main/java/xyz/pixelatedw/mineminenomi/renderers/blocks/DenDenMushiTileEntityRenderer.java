package xyz.pixelatedw.mineminenomi.renderers.blocks;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.util.ResourceLocation;
import xyz.pixelatedw.mineminenomi.blocks.WantedPosterBlock;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.DenDenMushiTileEntity;
import xyz.pixelatedw.mineminenomi.models.blocks.DenDenMushiBlockModel;
import xyz.pixelatedw.wypi.APIConfig;

public class DenDenMushiTileEntityRenderer extends TileEntityRenderer<DenDenMushiTileEntity>
{
	
	private DenDenMushiBlockModel model;
	private static final ResourceLocation TEXTURE = new ResourceLocation(APIConfig.PROJECT_ID, "textures/models/blocks/dendenmushi.png");

	public DenDenMushiTileEntityRenderer()
	{
		this.model = new DenDenMushiBlockModel();
	}
	
	@Override
	public void render(DenDenMushiTileEntity tileEntity, double posX, double posY, double posZ, float partialTicks, int destroyStage)
	{
		this.bindTexture(TEXTURE);
		BlockState blockstate = tileEntity.getBlockState();

		GL11.glPushMatrix();
		{
			GL11.glTranslated(posX + 0.5, posY + 1.5, posZ + 0.5);
			GL11.glScalef(1, 1, 1);
			GL11.glRotatef(-blockstate.get(WantedPosterBlock.FACING).getHorizontalAngle() + 180, 0F, 1F, 0F);
			GL11.glRotatef(180, 0F, 0F, 1F);

			this.model.render(null, 0, 0, 0, 0, 0, 0.0625F);
		}
		GL11.glPopMatrix();
	}
}
