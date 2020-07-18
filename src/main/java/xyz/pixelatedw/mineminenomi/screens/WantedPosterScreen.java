package xyz.pixelatedw.mineminenomi.screens;

import java.text.DecimalFormat;

import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.GlStateManager.DestFactor;
import com.mojang.blaze3d.platform.GlStateManager.SourceFactor;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.client.config.GuiUtils;
import xyz.pixelatedw.mineminenomi.api.crew.Crew;
import xyz.pixelatedw.mineminenomi.api.crew.JollyRoger;
import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;

public class WantedPosterScreen extends Screen
{
	private CompoundNBT wantedData;
	private ExtendedWorldData worldData;
	private JollyRoger jollyRoger;
	private PlayerEntity player;

	public WantedPosterScreen()
	{
		super(new StringTextComponent(""));
		this.wantedData = Minecraft.getInstance().player.getHeldItemMainhand().getTag();
		
		String name = this.wantedData.getString("Name");
		final String finalName = name;
		this.minecraft = Minecraft.getInstance();
		this.player = this.minecraft.world.getPlayers().stream().filter((entity) -> WyHelper.getResourceName(entity.getName().getFormattedText()).equalsIgnoreCase(finalName)).findFirst().orElse(this.minecraft.player);
		this.worldData = ExtendedWorldData.get(this.player.world);
		
		Crew crew = this.worldData.getCrewWithMember(this.player.getUniqueID());
		if(crew != null)
			this.jollyRoger = crew.getJollyRoger();
		
		//WyNetwork.sendToServer(new CRequestSyncWorldDataPacket());
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
		DecimalFormat decimalFormat = new DecimalFormat("#,##0");
		String bounty = decimalFormat.format(this.wantedData.getLong("Bounty"));
		
		// Drawing the background, the player's skin face segment and their crew's jolly roger (if they are in a crew) and expried mark if its expired
		GL11.glPushMatrix();
		{
			ResourceLocation rs;

			rs = new ResourceLocation(APIConfig.PROJECT_ID, "textures/gui/wantedposters/backgrounds/" + background + ".jpg");
			this.minecraft.getTextureManager().bindTexture(rs);

			GL11.glScaled(0.34, 0.245, 0);
			GuiUtils.drawTexturedModalRect(23, -57, 0, 0, 256, 256, 2);
			GL11.glDisable(GL11.GL_BLEND);

			rs = ((AbstractClientPlayerEntity) player).getLocationSkin();

			this.minecraft.getTextureManager().bindTexture(rs);

			GL11.glScaled(4.25, 5.5, 0);
			GuiUtils.drawTexturedModalRect(21, 0, 32, 32, 32, 32, 3);
			
			GlStateManager.pushMatrix();
			{
				GlStateManager.enableBlend();
				GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
		
				double scale = 0.08;
				GlStateManager.scaled(scale, scale, scale);
				GlStateManager.translated(550, 190, 0);
				
				if(this.jollyRoger != null)
					RendererHelper.drawPlayerJollyRoger(this.jollyRoger);
				
				GlStateManager.disableBlend();
			}
			GlStateManager.popMatrix();	
			
			if (this.worldData.getBounty(this.player.getUniqueID().toString()) != Long.parseLong(bounty.replace(",", "")))
			{
				GlStateManager.pushMatrix();
				{
					this.minecraft.getTextureManager().bindTexture(ModResources.EXPIRED);

					double scale = 0.25;
					GlStateManager.scaled(scale, scale, scale);
					GlStateManager.translated(30, -50, 0);
					GuiUtils.drawTexturedModalRect(0, 0, 16, 16, 256, 256, 0);
				}
				GlStateManager.popMatrix();
			}		
		}
		GL11.glPopMatrix();

		this.minecraft.getTextureManager().bindTexture(ModResources.CURRENCIES);
		GuiUtils.drawTexturedModalRect(-2, 63, 0, 0, 32, 32, 1);

		if (name.length() > 13)
			name = name.substring(0, 10) + "...";
		this.minecraft.fontRenderer.drawString(TextFormatting.BOLD + name, 47 - this.minecraft.fontRenderer.getStringWidth(name) / 2, 62, WyHelper.hexToRGB("513413").getRGB());
	
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
