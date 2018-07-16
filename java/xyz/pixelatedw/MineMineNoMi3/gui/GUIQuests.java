package xyz.pixelatedw.MineMineNoMi3.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.MainMod;
import xyz.pixelatedw.MineMineNoMi3.api.quests.QuestProperties;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;

public class GUIQuests extends GuiScreen
{
	private EntityPlayer player;
	private RenderItem renderItem;
	private ExtendedEntityStats props;
	private QuestProperties questProps;
	
	public GUIQuests(EntityPlayer player)
	{
		this.player = player;
		this.props = ExtendedEntityStats.get(player);
		this.questProps = QuestProperties.get(player);
	}

	public void drawScreen(int x, int y, float f)
	{
		drawDefaultBackground();
	    
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);	
		
		int posX = (this.width - 256) / 2;
		int posY = (this.height - 256) / 2;		
				
		String currentQuestText = I18n.format(ID.LANG_GUI_CURRENTQUEST);
		String currentQuest = questProps.getPrimaryQuestFromTracker() != null ? questProps.getPrimaryQuestFromTracker().getQuestName() : "None";
		String currentProgress = questProps.getPrimaryQuestFromTracker() != null ? questProps.getPrimaryQuestFromTracker().getProgress() + " / " + questProps.getPrimaryQuestFromTracker().getMaxProgress() : "";
		String[] currentDescription = questProps.getPrimaryQuestFromTracker() != null ? questProps.getPrimaryQuestFromTracker().getQuestDescription() : null;

		mc.fontRenderer.drawStringWithShadow(EnumChatFormatting.BOLD + currentQuestText, posX - 50, posY + 30, -1);
		mc.fontRenderer.drawStringWithShadow(EnumChatFormatting.BOLD + currentQuest, posX - 10, posY + 47, -1);
		mc.fontRenderer.drawStringWithShadow(EnumChatFormatting.BOLD + currentProgress, posX - 10, posY + 65, -1);
		if(currentDescription != null)
		{
			int i = 18;
			for(String line : currentDescription)
			{
				mc.fontRenderer.drawStringWithShadow(EnumChatFormatting.BOLD + line, posX - 40, posY + 65 + i, -1);
				i += 18;
			}
		}

		
		super.drawScreen(x, y, f);
	}

	public void initGui()
	{
		int posX = (this.width - 256) / 2;
		int posY = (this.height - 256) / 2;

		
	}
	
	public void actionPerformed(GuiButton button)
	{
		switch(button.id)
		{
			//case 1: player.openGui(MainMod.getMineMineNoMi(), 4, player.worldObj, (int)player.posX, (int)player.posY, (int)player.posZ);
		}
	}
	
	public boolean doesGuiPauseGame()
	{
		return false;
	}

    private void drawItemStack(ItemStack itemStack, int x, int y, String string)
    {
        GL11.glTranslatef(0.0F, 0.0F, 32.0F);
        this.zLevel = 200.0F;
        itemRender.zLevel = 200.0F;
        FontRenderer font = null;
        if (itemStack != null) font = itemStack.getItem().getFontRenderer(itemStack);
        if (font == null) font = fontRendererObj;
        itemRender.renderItemAndEffectIntoGUI(font, this.mc.getTextureManager(), itemStack, x, y);
        itemRender.renderItemOverlayIntoGUI(font, this.mc.getTextureManager(), itemStack, x, y - 0, string);
        this.zLevel = 0.0F;
        itemRender.zLevel = 0.0F;
    }
}
