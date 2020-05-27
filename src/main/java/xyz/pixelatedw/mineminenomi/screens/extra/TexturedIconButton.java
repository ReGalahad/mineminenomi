package xyz.pixelatedw.mineminenomi.screens.extra;

import java.util.List;

import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import xyz.pixelatedw.wypi.WyHelper;

public class TexturedIconButton extends Button
{
	private ResourceLocation texture;
	private int textureWidth;
	private int textureHeight;
	private int texturePosX;
	private int texturePosY;
	private int textPosX;
	private int textPosY;
	private double textScale = 1;
	private ResourceLocation iconTexture;
	private double iconScale = 1;
	private int iconPosX;
	private int iconPosY;
	
	public TexturedIconButton(ResourceLocation loc, int posX, int posY, int width, int height, String text, IPressable onPress)
	{
		super(posX, posY, width, height, text, onPress);

		this.texture = loc;
		this.texturePosX = posX;
		this.texturePosY = posY;
		this.textureWidth = width;
		this.textureHeight = height;
	}
	
	public TexturedIconButton setTextureInfo(int texturePosX, int texturePosY, int textureWidth, int textureHeight)
	{
		this.texturePosX = texturePosX;
		this.texturePosY = texturePosY;
		this.textureWidth = textureWidth;
		this.textureHeight = textureHeight;
		return this;
	}
	
	public TexturedIconButton setTextInfo(int textPosX, int textPosY, double scale)
	{
		this.textPosX = textPosX;
		this.textPosY = textPosY;
		this.textScale = scale;
		return this;
	}
	
	public TexturedIconButton setIconInfo(ResourceLocation loc, int iconPosX, int iconPosY, double scale)
	{
		this.iconTexture = loc;
		this.iconPosX = iconPosX;
		this.iconPosY = iconPosY;
		this.iconScale = scale;
		return this;
	}

	@Override
	public void render(int mouseX, int mouseY, float partialTicks)
	{
		GlStateManager.pushMatrix();
		this.isHovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
		
		if(this.isHovered)
		{
			GlStateManager.translated(0, 0.5, 0);
			GlStateManager.color3f(0.7f, 0.7f, 0.7f);
		}
		
		WyHelper.drawIcon(this.texture, this.texturePosX, this.texturePosY, this.textureWidth, this.textureHeight);
		
		// Icons handling, scaling, position, rendering etc
		if(this.iconTexture != null)
		{
			GlStateManager.pushMatrix();
			{
				GlStateManager.translated(this.iconPosX, this.iconPosY, 2);
				GL11.glTranslated(16, 16, 0);
				GL11.glScaled(this.iconScale, this.iconScale, this.iconScale);
				GL11.glTranslated(-16, -16, 1);
				
				WyHelper.drawIcon(this.iconTexture, 0, 0, 16, 16);
			}
			GlStateManager.popMatrix();
		}
		
		// Text handling, scaling, split lines etc
		GlStateManager.pushMatrix();
		{
			FontRenderer font = Minecraft.getInstance().fontRenderer;
			List<String> strings = WyHelper.splitString(font, this.getMessage(), this.textPosX - font.getStringWidth(this.getMessage()) / 2 + 26, (this.width / 3) + 10);
	
			GlStateManager.translated(this.textPosX, this.textPosY - (strings.size() > 1 ? strings.size() * 3 : 0), 2);
			GL11.glTranslated(128, 128, 0);
			GL11.glScaled(this.textScale, this.textScale, this.textScale);
			GL11.glTranslated(-128, -128, 1);
			for (int b = 0; b < strings.size(); b++)
			{		
				WyHelper.drawStringWithBorder(font, strings.get(b), 0, 7 * b, WyHelper.hexToRGB("#FFFFFF").getRGB());
			}
		}
		GlStateManager.popMatrix();
			
		GlStateManager.color3f(1f, 1f, 1f);

		//if(WyDebug.isDebug())
		//	this.fillGradient(this.x, this.y, this.width + this.x, this.height + this.y, WyHelper.hexToRGB("#FF0000").getRGB(), WyHelper.hexToRGB("#FF5500").getRGB());
		
		GlStateManager.popMatrix();
	}

}
