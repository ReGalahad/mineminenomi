package xyz.pixelatedw.mineminenomi.screens;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NewCrewScreen extends Screen
{
	private PlayerEntity player;

	public NewCrewScreen(PlayerEntity player)
	{
		super(new StringTextComponent(""));
		this.player = player;
	}

	@Override
	public void render(int x, int y, float f)
	{
		this.renderBackground();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	
		int posX = (this.width - 256) / 2;
		int posY = (this.height - 256) / 2;
		
		
		
		super.render(x, y, f);
	}
	
	@Override
	public void init()
	{
		int posX = (this.width - 256) / 2;
		int posY = (this.height - 256) / 2;
		
	}
}
