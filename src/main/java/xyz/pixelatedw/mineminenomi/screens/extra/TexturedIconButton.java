package xyz.pixelatedw.mineminenomi.screens.extra;

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
		
		FontRenderer font = Minecraft.getInstance().fontRenderer;
		WyHelper.drawStringWithBorder(font, this.getMessage(), this.x - font.getStringWidth(this.getMessage()) / 2 + 30, this.y + 6, WyHelper.hexToRGB("#FFFFFF").getRGB());
		
		GlStateManager.color3f(1f, 1f, 1f);

		//this.fillGradient(this.x, this.y, this.width + this.x, this.height + this.y, WyHelper.hexToRGB("#FF0000").getRGB(), WyHelper.hexToRGB("#FF5500").getRGB());
		
		GlStateManager.popMatrix();
	}

}
