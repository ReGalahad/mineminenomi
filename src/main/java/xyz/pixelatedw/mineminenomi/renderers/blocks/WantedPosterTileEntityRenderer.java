package xyz.pixelatedw.mineminenomi.renderers.blocks;

import java.text.DecimalFormat;
import java.util.UUID;

import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.client.config.GuiUtils;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.blocks.WantedPosterBlock;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.WantedPosterTileEntity;
import xyz.pixelatedw.mineminenomi.models.blocks.WantedPosterModel;
import xyz.pixelatedw.mineminenomi.values.ModValuesEnv;

public class WantedPosterTileEntityRenderer extends TileEntityRenderer<WantedPosterTileEntity>
{
	private final ResourceLocation texture = new ResourceLocation(ModValuesEnv.PROJECT_ID + ":textures/models/wantedposter.png");
	private WantedPosterModel posterModel;
	private Minecraft minecraft;

	public WantedPosterTileEntityRenderer()
	{
		this.posterModel = new WantedPosterModel();
		this.minecraft = Minecraft.getInstance();
	}

	@Override
	public void render(WantedPosterTileEntity tileEntity, double x, double y, double z, float partialTicks, int destroyStage)
	{
		this.bindTexture(this.texture);
		BlockState blockstate = tileEntity.getBlockState();

		String name = tileEntity.getEntityName();
		ResourceLocation rs = null;

		GlStateManager.pushMatrix();
		{
			GlStateManager.translatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
			GL11.glRotatef(-blockstate.get(WantedPosterBlock.FACING).getHorizontalAngle() + 180, 0F, 1F, 0F);
			GL11.glRotatef(180, 0, 0, 1);
			GlStateManager.color3f(1, 1, 1);
			GlStateManager.depthMask(true);
			GlStateManager.disableLighting();
			
			// Render the model
			GlStateManager.pushMatrix();
			{
				GL11.glRotatef(180, 0, 0, 1);
				GlStateManager.translatef(-0.4F, 0.6F, 0.5F);

				GlStateManager.enableRescaleNormal();

				GlStateManager.scalef(0.6666667F, -0.6666667F, -0.6666667F);
				this.posterModel.render(null, 0, 0, 0, 0, 0, 0.0625F);
			}
			GlStateManager.popMatrix();

			if (WyHelper.isNullOrEmpty(name))
			{
				GlStateManager.popMatrix();
				return;
			}

			// Render the background and the face
			GlStateManager.pushMatrix();
			{
				GL11.glScalef(.0035F, .0025F, .5F);
				GL11.glTranslated(x - 160, y - 100, 0.9);

				GlStateManager.pushMatrix();
				{
					final PlayerEntity finalEntity = this.minecraft.world.getPlayerByUuid(UUID.fromString(tileEntity.getUUID()));
					rs = ((AbstractClientPlayerEntity) finalEntity).getLocationSkin();
					this.bindTexture(rs);

					GL11.glTranslated(1, 1, 0.014);
					GL11.glScaled(4.25, 5.5, 0);
					GuiUtils.drawTexturedModalRect(21, 0, 32, 32, 32, 32, 0);
				}
				GlStateManager.popMatrix();

				GlStateManager.pushMatrix();
				{
					rs = new ResourceLocation(ModValuesEnv.PROJECT_ID, "textures/gui/wantedposters/backgrounds/" + tileEntity.getBackground() + ".png");
					this.bindTexture(rs);

					GL11.glTranslated(50, -50, 0.015);
					GL11.glScalef(0.87F, 0.94F, 1.0F);
					GuiUtils.drawTexturedModalRect(0, 0, 0, 0, 250, 250, 0);
				}
				GlStateManager.popMatrix();				
			}
			GlStateManager.popMatrix();

			// Render the text
			GlStateManager.pushMatrix();
			{
				GL11.glScalef(.0075F, .008F, .0075F);
				GL11.glTranslated(-12, 39, 59.9);

				if (name.length() > 13)
					name = name.substring(0, 10) + "...";
				this.minecraft.fontRenderer.drawString(TextFormatting.BOLD + name, 3 - this.minecraft.fontRenderer.getStringWidth(name) / 2, 0, WyHelper.hexToRGB("513413").getRGB());

				GL11.glScalef(1.2F, 1.2F, 1.2F);
				DecimalFormat decimalFormat = new DecimalFormat("#,##0");
				if (WyHelper.isNullOrEmpty(tileEntity.getPosterBounty()))
					tileEntity.setPosterBounty("0");
				String bounty = "0";
				try
				{
					bounty = decimalFormat.format(Long.parseLong(tileEntity.getPosterBounty()));
				}
				catch (Exception e)
				{
					bounty = "0";
					e.printStackTrace();
				}

				boolean flag = bounty.length() > 10;

				if (flag)
				{
					GL11.glPushMatrix();
					GL11.glTranslated(-40, -13.5, 0);
					GL11.glTranslated(128, 128, 512);
					GL11.glScaled(.72, 0.89, 1.005);
					GL11.glTranslated(-128, -128, -512);
				}
				this.minecraft.fontRenderer.drawString(TextFormatting.BOLD + bounty, -20, 13, WyHelper.hexToRGB("#513413").getRGB());
				if (flag)
					GL11.glPopMatrix();

				GL11.glScalef(0.7F, 0.9F, 0.8F);
				this.minecraft.fontRenderer.drawString(TextFormatting.BOLD + tileEntity.getIssuedDate(), -40, 30, WyHelper.hexToRGB("#513413").getRGB());

			}
			GlStateManager.popMatrix();

			GlStateManager.enableLighting();
			GlStateManager.depthMask(false);
		}
		GlStateManager.popMatrix();
	}
	
}
