package xyz.pixelatedw.mineminenomi.screens;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.GlStateManager.DestFactor;
import com.mojang.blaze3d.platform.GlStateManager.SourceFactor;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.config.GuiUtils;
import xyz.pixelatedw.mineminenomi.api.jollyroger.JollyRoger;
import xyz.pixelatedw.mineminenomi.screens.extra.NoTextureButton;
import xyz.pixelatedw.wypi.WyHelper;

@OnlyIn(Dist.CLIENT)
public class JollyRogerCreatorScreen extends Screen
{
	private PlayerEntity player;
	private JollyRoger jollyRoger;
	private Widget selectedButton;
	private String layerName;
	private int layerIndex;
	
	public JollyRogerCreatorScreen()
	{
		super(new StringTextComponent(""));
		this.player = Minecraft.getInstance().player;
		this.jollyRoger = new JollyRoger();
	}
	
	@Override
	public void render(int x, int y, float f)
	{
		this.renderBackground();
		
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		int posX = this.width / 2;
		int posY = this.height / 2;
		
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
		
		GL11.glPushMatrix();
		{
			double scale = 0.5;
			GL11.glTranslated(posX - 130, posY - 120, 1);
			GL11.glTranslated(128, 128, 0);
			GL11.glScaled(scale, scale, scale);
			GL11.glTranslated(-128, -128, 0);

			// Jolly Roger draw with all the backgrounds and layers
			if(this.jollyRoger.getBase().getTexture() != null)
			{
				if(this.jollyRoger.getBase().canBeColored())
				{
					Color color = WyHelper.hexToRGB(this.jollyRoger.getBase().getColor());
					GlStateManager.color3f(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F);				
				}	
				else
				{
					GlStateManager.color3f(1.0F, 1.0F, 1.0F);
				}
				Minecraft.getInstance().getTextureManager().bindTexture(this.jollyRoger.getBase().getTexture());		
				GuiUtils.drawTexturedModalRect(0, 0, 0, 0, 256, 256, 1);
			}
		}
		GL11.glPopMatrix();
		
		
			
		GlStateManager.disableBlend();

		super.render(x, y, f);
	}
	
	@Override
	public void init(Minecraft mc, int width, int height)
	{
		super.init(mc, width, height);
		
		int posX = 0;
		int posY = this.height / 2;
		
		int listPosY = posY - 85;
	
		/*
		NoTextureButton baseButton = new NoTextureButton(posX + 5, listPosY, 128, 16, "Base", this::selectButton);
		this.addButton(baseButton);
		
		for(int i = 0; i < this.jollyRoger.getBackgrounds().length; i++)
		{
			NoTextureButton bgButton = new NoTextureButton(posX + 5, (listPosY + 20 + (i * 20)), 128, 16, "Background " + (i + 1), this::selectButton);
			this.addButton(bgButton);
		}
		
		for(int i = 0; i < this.jollyRoger.getDetails().length; i++)
		{
			NoTextureButton detailButton = new NoTextureButton(posX + 5, (listPosY + 60 + (i * 20)), 128, 16, "Detail " + (i + 1), this::selectButton);
			this.addButton(detailButton);
		}
		
		posX = this.width / 2;
		
		TexturedIconButton nextBaseTexture = new TexturedIconButton(ModResources.BRIGHT_WOOD_ARROW_RIGHT, posX + 40, posY - 105, 16, 25, "", (button) -> {});
		nextBaseTexture = nextBaseTexture.setTextureInfo(posX + 38, posY - 125, 16, 64);
		this.addButton(nextBaseTexture);	
		*/
		
		/*
		TexturedIconButton prevBaseTexture = new TexturedIconButton(ModResources.BRIGHT_WOOD_ARROW_LEFT, posX - 40, posY - 105, 16, 25, "", (button) -> {});
		prevBaseTexture = prevBaseTexture.setTextureInfo(posX - 42, posY - 125, 16, 64);
		this.addButton(prevBaseTexture);
		*/
	}
	
	public void selectButton(Button btn)
	{
		if(!(btn instanceof NoTextureButton))
			return;
		
		if(this.selectedButton != null)
			((NoTextureButton) this.selectedButton).select(); 
		this.selectedButton = btn;
		((NoTextureButton) btn).select();
		
		if(this.buttons.get(0) == btn)
		{
			this.layerName = "base";
			this.layerIndex = 0;
			return;
		}
		
		for(int i = 1; i < this.jollyRoger.getBackgrounds().length + 1; i++)
		{
			if(this.buttons.get(i) == btn)
			{
				this.layerName = "background";
				this.layerIndex = i;
				return;
			}
		}
			
		for(int i = this.jollyRoger.getBackgrounds().length + 1; i < this.jollyRoger.getDetails().length + this.jollyRoger.getBackgrounds().length + 1; i++)
		{
			if(this.buttons.get(i) == btn)
			{
				this.layerName = "detail";
				this.layerIndex = i;
				return;
			}
		}
	}
	
	public boolean doesGuiPauseGame()
	{
		return true;
	}
	
    public static void open() 
    {
        Minecraft.getInstance().displayGuiScreen(new JollyRogerCreatorScreen());
    }
}
