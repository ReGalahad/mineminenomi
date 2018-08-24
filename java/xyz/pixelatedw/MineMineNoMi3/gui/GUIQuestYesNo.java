package xyz.pixelatedw.MineMineNoMi3.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.quests.Quest;
import xyz.pixelatedw.MineMineNoMi3.api.quests.QuestProperties;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;

public class GUIQuestYesNo extends GuiScreen
{
	private EntityPlayer player;
	private RenderItem renderItem;
	private ExtendedEntityStats props;
	private QuestProperties questProps;
	private Quest quest;
	
	public GUIQuestYesNo(EntityPlayer player)
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
			
		Minecraft.getMinecraft().getTextureManager().bindTexture(ID.TEXTURE_BLANK);
		
		GL11.glPushMatrix();
		{
			double scale = 1.4;
			GL11.glTranslated(posX + 55, posY + 115, 0);
			GL11.glTranslated(256, 256, 0);
			
			GL11.glScaled(scale, scale, 0);
			GL11.glTranslated(-256, -256, 0);
			
			drawTexturedModalRect(0, 0, 0, 0, 256, 256);
		}
		GL11.glPopMatrix();
		
		String currentQuest = questProps.getPrimaryQuestFromTracker() != null ? questProps.getPrimaryQuestFromTracker().getQuestName() : "None";
		String[] currentDescription = questProps.getPrimaryQuestFromTracker() != null ? questProps.getPrimaryQuestFromTracker().getQuestDescription() : null;

		GL11.glPushMatrix();
		{
			double scale = 1.30056;
			GL11.glTranslated(posX + 55, posY + 120, 0);
			GL11.glTranslated(256, 256, 0);
			
			GL11.glScaled(scale, scale, 0);
			GL11.glTranslated(-256, -256, 0);
			
			mc.fontRenderer.drawString(EnumChatFormatting.BOLD + "" + EnumChatFormatting.UNDERLINE + currentQuest, 0, 0, WyHelper.hexToRGB("#161616").getRGB());
		}
		GL11.glPopMatrix();

		if(currentDescription != null)
		{
			int i = 18;
			for(String line : currentDescription)
			{
				mc.fontRenderer.drawString(line, posX - 30, posY + 65 + i, WyHelper.hexToRGB("#161616").getRGB());
				i += 16;
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
