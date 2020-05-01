package xyz.pixelatedw.mineminenomi.screens.extra;

import net.minecraft.client.Minecraft;
import xyz.pixelatedw.wypi.WyHelper;

public class FlickeringString
{
	private String message;
	private int flicker;
	private boolean isVisible = true;
	private Minecraft mc;

	public FlickeringString(String str, int flicker)
	{
		this.mc = Minecraft.getInstance();
		this.message = str;
		this.flicker = flicker;
	}
	
	public void render(int posX, int posY)
	{
		if(this.mc.getFrameTimer().getIndex() % this.flicker == 0)
			this.isVisible = !this.isVisible;
		else
		{
			String msg = this.isVisible ? this.message : "";

			WyHelper.drawStringWithBorder(this.mc.fontRenderer, msg, posX, posY, WyHelper.hexToRGB("#FFFFFF").getRGB());
		}
	}
}
