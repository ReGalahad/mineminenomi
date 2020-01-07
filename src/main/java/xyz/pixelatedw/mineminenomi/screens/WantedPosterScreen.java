package xyz.pixelatedw.mineminenomi.screens;

import java.text.DecimalFormat;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.client.config.GuiUtils;
import xyz.pixelatedw.mineminenomi.Env;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.init.ModResources;

public class WantedPosterScreen extends Screen
{
	private CompoundNBT wantedData;

	public WantedPosterScreen()
	{
		super(new StringTextComponent(""));
		this.wantedData = Minecraft.getInstance().player.getHeldItemMainhand().getTag();
	}

	@Override
	public void render(int x, int y, float f)
	{
		this.renderBackground();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		int posX = (this.width - 256) / 2;
		int posY = (this.height - 256) / 2;

		// Scaling the entire "context" x2 and positioning it correctly
		GL11.glTranslated(posX + 60, posY + 10, 0);
		GL11.glTranslated(128, 128, 512);
		GL11.glScaled(1, .9, 0);
		GL11.glTranslated(-128, -128, -512);

		// Bounty Poster Texture
		this.minecraft.getTextureManager().bindTexture(ModResources.BOUNTY_POSTER_LARGE);
		GuiUtils.drawTexturedModalRect(0, 0, 0, 0, 220, 250, 0);

		// Scaling down the entire thing (with wanted poster texture and name) to x0.71
		GL11.glTranslated(67, 150, 0);
		GL11.glTranslated(128, 128, 512);
		GL11.glScaled(1.5, 1.6, 0);
		GL11.glTranslated(-128, -128, -512);

		String name = this.wantedData.getString("Name");
		String background = this.wantedData.getString("Background");

		GL11.glPushMatrix();
		{
			ResourceLocation rs = new ResourceLocation(Env.PROJECT_ID, "textures/gui/wantedposters/backgrounds/" + background + ".png");
			this.minecraft.getTextureManager().bindTexture(rs);

			GL11.glScaled(0.34, 0.245, 0);
			GuiUtils.drawTexturedModalRect(23, -57, 0, 0, 256, 256, 2);
			GL11.glDisable(GL11.GL_BLEND);

			final String finalName = name;
			AbstractClientPlayerEntity player = this.minecraft.world.getPlayers().stream().filter((entity) -> WyHelper.getResourceName(entity.getName().getFormattedText()).equalsIgnoreCase(finalName)).findFirst().orElse(this.minecraft.player);
			rs = player.getLocationSkin();

			this.minecraft.getTextureManager().bindTexture(rs);

			GL11.glScaled(4.25, 5.5, 0);
			GuiUtils.drawTexturedModalRect(21, 0, 32, 32, 32, 32, 3);
		}
		GL11.glPopMatrix();

		this.minecraft.getTextureManager().bindTexture(ModResources.CURRENCIES);
		GuiUtils.drawTexturedModalRect(-2, 63, 0, 0, 32, 32, 1);
		DecimalFormat decimalFormat = new DecimalFormat("#,##0");

		if (name.length() > 13)
			name = name.substring(0, 10) + "...";
		this.minecraft.fontRenderer.drawString(TextFormatting.BOLD + name, 47 - this.minecraft.fontRenderer.getStringWidth(name) / 2, 62, WyHelper.hexToRGB("513413").getRGB());

		String bounty = decimalFormat.format(this.wantedData.getLong("Bounty"));
		boolean flag = bounty.length() > 10;
		if (flag)
		{
			GL11.glPushMatrix();
			GL11.glTranslated(-21, -5, 0);
			GL11.glTranslated(128, 128, 512);
			GL11.glScaled(.82, 0.89, 0);
			GL11.glTranslated(-128, -128, -512);
		}

		this.minecraft.fontRenderer.drawString(TextFormatting.BOLD + bounty, 22, 76, WyHelper.hexToRGB("513413").getRGB());
		if(flag)
			GL11.glPopMatrix();
		
		// Scaling down the entire thing so the date could fit
		GL11.glTranslated(-24, -2, 0);	
		GL11.glTranslated(128, 128, 512);
		GL11.glScaled(.78, .92, 0);	
		GL11.glTranslated(-128, -128, -512);
		
		this.minecraft.fontRenderer.drawString(TextFormatting.BOLD + this.wantedData.getString("Date"), 36 - this.minecraft.fontRenderer.getStringWidth(this.wantedData.getString("Date")) / 2, 90, WyHelper.hexToRGB("513413").getRGB());	
		
		super.render(x, y, f);
	}

	@Override
	public boolean isPauseScreen()
	{
		return false;
	}
	
    public static void open() 
    {
        Minecraft.getInstance().displayGuiScreen(new WantedPosterScreen());
    }
}
