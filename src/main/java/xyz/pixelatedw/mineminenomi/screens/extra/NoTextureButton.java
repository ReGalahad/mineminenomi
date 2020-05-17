package xyz.pixelatedw.mineminenomi.screens.extra;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.widget.button.Button;
import xyz.pixelatedw.wypi.WyHelper;

public class NoTextureButton extends Button
{
	private boolean isSelected;
	
	public NoTextureButton(int posX, int posY, int width, int height, String string, IPressable onPress)
	{
		super(posX, posY, width, height, string, onPress);
	}

	@Override
	public void render(int mouseX, int mouseY, float partialTicks)
	{
		GlStateManager.pushMatrix();
		if (this.visible)
		{
			this.isHovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
			int rgb = WyHelper.hexToRGB("#FFFFFF").getRGB();
			int topGrad = WyHelper.hexToRGB("#B3B3B3").getRGB();
			int bottomGrad = WyHelper.hexToRGB("#939393").getRGB();
			
			if (this.isHovered)
			{
				GlStateManager.translated(0, 0.5, 0);
				rgb = WyHelper.hexToRGB("#00FFBB").getRGB();
				topGrad = WyHelper.hexToRGB("#B3B3B3").getRGB();
				bottomGrad = WyHelper.hexToRGB("#505050").getRGB();
			}
			
			if (this.isSelected)
			{
				topGrad = WyHelper.hexToRGB("#00CC00").getRGB();
				bottomGrad = WyHelper.hexToRGB("#005500").getRGB();
			}

			int outlineSize = 1;
			
			this.fillGradient(this.x - outlineSize, this.y - outlineSize, this.width + this.x + outlineSize, this.height + this.y + outlineSize, WyHelper.hexToRGB("#000000").getRGB(), WyHelper.hexToRGB("#000000").getRGB());
			this.fillGradient(this.x, this.y, this.width + this.x, this.height + this.y, topGrad, bottomGrad);
			
			FontRenderer font = Minecraft.getInstance().fontRenderer;
			WyHelper.drawStringWithBorder(font, this.getMessage(), this.x - font.getStringWidth(this.getMessage()) / 2 + this.width / 2, this.y + 4, rgb);
			
			GlStateManager.color3f(1f, 1f, 1f);
		}
		GlStateManager.popMatrix();
	}

	public void select()
	{
		this.isSelected = !this.isSelected;
	}
}
