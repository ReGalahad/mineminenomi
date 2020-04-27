package xyz.pixelatedw.mineminenomi.screens.extra;

import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import xyz.pixelatedw.wypi.WyHelper;

public class TexturedIconButton extends Button {

	public ResourceLocation texture;
	public TexturedIconButton(ResourceLocation loc,int widthIn, int heightIn, int width, int height, String text, IPressable onPress) {
		super(widthIn, heightIn, width, height, text, onPress);

		this.texture = loc;
	}

	@Override
	public void render(int p_render_1_, int p_render_2_, float p_render_3_) {
	     
		WyHelper.drawIcon(this.texture, this.x, this.y, this.width, this.height);
	   }

}
