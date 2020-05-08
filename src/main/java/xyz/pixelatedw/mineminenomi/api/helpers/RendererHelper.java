package xyz.pixelatedw.mineminenomi.api.helpers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
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
}
