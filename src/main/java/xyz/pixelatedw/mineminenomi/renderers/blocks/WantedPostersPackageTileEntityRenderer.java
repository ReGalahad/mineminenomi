package xyz.pixelatedw.mineminenomi.renderers.blocks;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.util.ResourceLocation;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.WantedPosterPackageTileEntity;
import xyz.pixelatedw.mineminenomi.models.blocks.WantedPosterPackageModel;
import xyz.pixelatedw.mineminenomi.values.ModValuesEnv;

public class WantedPostersPackageTileEntityRenderer extends TileEntityRenderer<WantedPosterPackageTileEntity>
{
	
	private WantedPosterPackageModel model;
	private static final ResourceLocation texture = new ResourceLocation(ModValuesEnv.PROJECT_ID + ":textures/models/wantedposterspackage.png");

	public WantedPostersPackageTileEntityRenderer()
	{
		this.model = new WantedPosterPackageModel();
	}
	
	@Override
	public void render(WantedPosterPackageTileEntity tileEntity, double posX, double posY, double posZ, float partialTicks, int destroyStage)
	{
		this.bindTexture(texture);

		GL11.glPushMatrix();
		{
			GL11.glTranslated(posX + 0.5, posY + 1.6, posZ + 0.5);
			GL11.glScalef(1, 1, 1);
			GL11.glRotatef(180, 0F, 0F, 1F);

			this.model.parachute.isHidden = true;
			this.model.render(null, 0, 0, 0, 0, 0, 0.0625F);
		}
		GL11.glPopMatrix();
	}
}
