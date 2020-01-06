package xyz.pixelatedw.mineminenomi.screens;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.config.GuiUtils;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.data.quest.IQuestData;
import xyz.pixelatedw.mineminenomi.api.data.quest.QuestDataCapability;
import xyz.pixelatedw.mineminenomi.api.network.packets.client.CQuestDataSyncPacket;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.screens.extra.NoTextureButton;

@OnlyIn(Dist.CLIENT)
public class QuestAcceptScreen extends Screen
{
	private PlayerEntity player;
	private IQuestData questProps;
	private Quest currentQuestToDisplay;
	
	public QuestAcceptScreen(PlayerEntity player, Quest quest)
	{
		super(new StringTextComponent(""));
		this.player = player;
		this.questProps = QuestDataCapability.get(player);
		this.currentQuestToDisplay = quest;
	}
	
	@Override
	public void render(int x, int y, float f)
	{
		this.renderBackground();

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		int posX = (this.width - 256) / 2;
		int posY = (this.height - 256) / 2;
	
		this.minecraft.getTextureManager().bindTexture(ModResources.BLANK);
		
		if(this.currentQuestToDisplay == null)
			return;
		
		GL11.glPushMatrix();
		{
			double scale = 1.4;
			GL11.glTranslated(posX + 55, posY + 115, 0);
			GL11.glTranslated(256, 256, 0);
			
			GL11.glScaled(scale, scale, 0);
			GL11.glTranslated(-256, -256, 0);
			
			GuiUtils.drawTexturedModalRect(0, 0, 0, 0, 256, 256, 1);
		}
		GL11.glPopMatrix();
		
		String currentQuestName = new TranslationTextComponent(String.format("quest.%s.name", this.currentQuestToDisplay.getQuestId())).getFormattedText();

		this.minecraft.fontRenderer.drawString(new TranslationTextComponent(ModI18n.GUI_QUEST_ACCEPT).getFormattedText(), posX - 20, posY + 75, WyHelper.hexToRGB("#161616").getRGB());

		GL11.glPushMatrix();
		{
			double scale = 1.30056;
			GL11.glTranslated(posX + 55, posY + 180, 0);
			GL11.glTranslated(256, 256, 0);
			
			GL11.glScaled(scale, scale, 0);
			GL11.glTranslated(-256, -256, 0);
			
			this.minecraft.fontRenderer.drawString(TextFormatting.BOLD + "" + currentQuestName, 0, 0, WyHelper.hexToRGB("#161616").getRGB());		
		}
		GL11.glPopMatrix();

		this.minecraft.getTextureManager().bindTexture(ModResources.WIDGETS);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);	
		
		GuiUtils.drawTexturedModalRect(posX + 10, posY + 140, 0, 196, 96, 49, 0);
		GuiUtils.drawTexturedModalRect(posX + 150, posY + 140, 0, 196, 96, 49, 0);
		
		this.minecraft.fontRenderer.drawString(I18n.format(ModI18n.GUI_ACCEPT), posX + 21, posY + 159, WyHelper.hexToRGB("#12e557").getRGB());
		this.minecraft.fontRenderer.drawString(I18n.format(ModI18n.GUI_ACCEPT), posX + 20, posY + 158, WyHelper.hexToRGB("#161616").getRGB());

		this.minecraft.fontRenderer.drawString(I18n.format(ModI18n.GUI_DECLINE), posX + 161, posY + 159, WyHelper.hexToRGB("#cc1010").getRGB());
		this.minecraft.fontRenderer.drawString(I18n.format(ModI18n.GUI_DECLINE), posX + 160, posY + 158, WyHelper.hexToRGB("#161616").getRGB());
		
		super.render(x, y, f);		
	}
	
	@Override
	public void init()
	{
		int posX = (this.width - 256) / 2;
		int posY = (this.height - 256) / 2;
		
		this.addButton(new NoTextureButton(posX + 12, posY + 143, 90, 37, "", b -> 
		{
			this.minecraft.displayGuiScreen((Screen)null);
			this.questProps.startQuest(player, this.currentQuestToDisplay);
			ModNetwork.sendToServer(new CQuestDataSyncPacket(questProps));
		}));
		
		this.addButton(new NoTextureButton(posX + 152, posY + 143, 90, 37, "", b -> 
		{
			this.minecraft.displayGuiScreen((Screen)null);
		}));
	}
	
	@Override
	public boolean isPauseScreen()
	{
		return false;
	}
}
