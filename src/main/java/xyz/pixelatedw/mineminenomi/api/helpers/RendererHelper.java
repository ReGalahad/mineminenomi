package xyz.pixelatedw.mineminenomi.api.helpers;

import java.awt.Color;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.GuiUtils;
import xyz.pixelatedw.mineminenomi.api.jollyroger.JollyRogerElement;
import xyz.pixelatedw.mineminenomi.data.entity.jollyroger.IJollyRoger;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;

public class RendererHelper
{
	public static void drawAbilityIcon(String iconName, int x, int y, int u, int v)
	{
		drawAbilityIcon(iconName, x, y, 1, u, v);
	}

	public static void drawAbilityIcon(String iconName, int x, int y, int z, int u, int v)
	{
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation(APIConfig.PROJECT_ID, "textures/abilities/" + WyHelper.getResourceName(iconName) + ".png"));
		BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.pos(x, y + v, z).tex(0.0, 1.0).endVertex();
		bufferbuilder.pos(x + u, y + v, z).tex(1.0, 1.0).endVertex();
		bufferbuilder.pos(x + u, y, z).tex(1.0, 0.0).endVertex();
		bufferbuilder.pos(x, y, z).tex(0.0, 0.0).endVertex();
		Tessellator.getInstance().draw();
	}

	public static void drawDevilFruitIcon(String iconName, int x, int y, int u, int v)
	{
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation(APIConfig.PROJECT_ID, "textures/items/" + WyHelper.getResourceName(iconName) + ".png"));
		BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.pos(x, y + v, 1).tex(0.0, 1.0).endVertex();
		bufferbuilder.pos(x + u, y + v, 1).tex(1.0, 1.0).endVertex();
		bufferbuilder.pos(x + u, y, 1).tex(1.0, 0.0).endVertex();
		bufferbuilder.pos(x, y, 1).tex(0.0, 0.0).endVertex();
		Tessellator.getInstance().draw();
	}
	
	public static void drawPlayerJollyRoger(IJollyRoger props)
	{
		// Jolly Roger draw with all the backgrounds and layers
		// Drawing the base
		if (props.getBase() != null && props.getBase().getTexture() != null)
		{
			if (props.getBase().canBeColored())
			{
				Color color = WyHelper.hexToRGB(props.getBase().getColor());
				GlStateManager.color3f(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F);
			}
			else
			{
				GlStateManager.color3f(1.0F, 1.0F, 1.0F);
			}
			Minecraft.getInstance().getTextureManager().bindTexture(props.getBase().getTexture());
			GuiUtils.drawTexturedModalRect(0, 0, 0, 0, 256, 256, 2);
		}

		// Drawing the backgrounds
		for (JollyRogerElement element : props.getBackgrounds())
		{
			int i = 0;
			if (element != null && element.getTexture() != null)
			{
				if (element.canBeColored())
				{
					Color color = WyHelper.hexToRGB(element.getColor());
					GlStateManager.color3f(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F);
				}
				else
				{
					GlStateManager.color3f(1.0F, 1.0F, 1.0F);
				}
				Minecraft.getInstance().getTextureManager().bindTexture(element.getTexture());
				GuiUtils.drawTexturedModalRect(0, 0, 0, 0, 256, 256, i);
			}
			i++;
		}
		
		// Drawing the details
		for (JollyRogerElement element : props.getDetails())
		{
			int i = 8;
			if (element != null && element.getTexture() != null)
			{
				if (element.canBeColored())
				{
					Color color = WyHelper.hexToRGB(element.getColor());
					GlStateManager.color3f(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F);
				}
				else
				{
					GlStateManager.color3f(1.0F, 1.0F, 1.0F);
				}
				Minecraft.getInstance().getTextureManager().bindTexture(element.getTexture());
				GuiUtils.drawTexturedModalRect(0, 0, 0, 0, 256, 256, i);
			}
			i--;
		}
	}
}
