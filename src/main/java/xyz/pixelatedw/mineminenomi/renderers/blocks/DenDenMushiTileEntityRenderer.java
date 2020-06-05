package xyz.pixelatedw.mineminenomi.renderers.blocks;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.util.ResourceLocation;
import xyz.pixelatedw.mineminenomi.blocks.DenDenMushiBlock;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.DenDenMushiTileEntity;
import xyz.pixelatedw.mineminenomi.models.blocks.DenDenMushiBlockModel;
import xyz.pixelatedw.wypi.APIConfig;

public class DenDenMushiTileEntityRenderer extends TileEntityRenderer<DenDenMushiTileEntity>
{
	
	private DenDenMushiBlockModel model;
	private static final ResourceLocation TEXTURE1 = new ResourceLocation(APIConfig.PROJECT_ID, "textures/models/blocks/den_den_mushi1.png");
	private static final ResourceLocation TEXTURE2 = new ResourceLocation(APIConfig.PROJECT_ID, "textures/models/blocks/den_den_mushi2.png");
	private static final ResourceLocation TEXTURE3 = new ResourceLocation(APIConfig.PROJECT_ID, "textures/models/blocks/den_den_mushi3.png");

	public DenDenMushiTileEntityRenderer()
	{
		this.model = new DenDenMushiBlockModel();
	}
	
	@Override
	public void render(DenDenMushiTileEntity tileEntity, double posX, double posY, double posZ, float partialTicks, int destroyStage)
	{
		BlockState state = tileEntity.getBlockState();
		
		int type = state.get(DenDenMushiBlock.TYPE);
		if(type == 1)
			this.bindTexture(TEXTURE2);
		else if(type == 2)
			this.bindTexture(TEXTURE3);
		else
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
