package xyz.pixelatedw.MineMineNoMi3.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.MainConfig;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedEntityData;

public class GUIHistory extends GuiScreen
{
	private EntityPlayer player;
	private ExtendedEntityData props;
	
	public GUIHistory(EntityPlayer player)
	{
		this.player = player;
		this.props = ExtendedEntityData.get(player);
	}
	
	@Override
	public void drawScreen(int x, int y, float f)
	{
		drawDefaultBackground();
		GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.0F);

		int posX = (this.width - 256) / 2;
		int posY = (this.height - 256) / 2;
		
		super.drawScreen(x, y, f);
	}
	
	@Override
	public void initGui()
	{
		int posX = (this.width - 256) / 2;
		int posY = (this.height - 256) / 2;

		if(MainConfig.enableQuests)
			this.buttonList.add(new GuiButton(1, posX - 20, posY + 210, 70, 20, I18n.format(ID.LANG_GUI_CHALLENGES)));
		
		//this.buttonList.add(new GuiButton(2, posX + 63, posY + 210, 80, 20, I18n.format("gui.epithet.name")));
	}
	
	@Override
	public void actionPerformed(GuiButton button)
	{
		switch(button.id)
		{

		}
	}
	
	@Override
	public boolean doesGuiPauseGame()
	{
		return false;
	}
}
