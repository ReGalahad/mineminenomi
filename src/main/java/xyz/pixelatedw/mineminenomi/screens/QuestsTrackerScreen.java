package xyz.pixelatedw.mineminenomi.screens;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.screen.Screen;
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
import xyz.pixelatedw.mineminenomi.api.debug.WyDebug;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.init.ModValues;
import xyz.pixelatedw.mineminenomi.screens.extra.NoTextureButton;

@OnlyIn(Dist.CLIENT)
public class QuestsTrackerScreen extends Screen
{
	private PlayerEntity player;
	private IQuestData questProps;
	private int questIndex = 0;
	
	public QuestsTrackerScreen(PlayerEntity player)
	{
		super(new StringTextComponent(""));
		this.player = player;
		this.questProps = QuestDataCapability.get(player);
	}
	
	@Override
	public void render(int x, int y, float f)
	{
		this.renderBackground();

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		int posX = (this.width - 256) / 2;
		int posY = (this.height - 256) / 2;
			
		// Background rendering
		this.minecraft.getTextureManager().bindTexture(ModResources.BLANK);
		GL11.glPushMatrix();
		{
			double scale = 1.4;
			GL11.glTranslated(posX + 55, posY + 115, 0);
			GL11.glTranslated(256, 256, 0);
			
			GL11.glScaled(scale, scale, 0);
			GL11.glTranslated(-256, -256, 0);
			
			GuiUtils.drawTexturedModalRect(0, 0, 0, 0, 256, 256, 1);
			
			this.minecraft.getTextureManager().bindTexture(ModResources.WIDGETS);
			GuiUtils.drawTexturedModalRect(-20, 30, 0, 92, 25, 100, 1);
			GuiUtils.drawTexturedModalRect(232, 30, 26, 92, 30, 100, 1);		
		}
		GL11.glPopMatrix();
		
		Quest currentQuest = null;
		
		try
		{
			currentQuest = this.questProps.getInProgressQuests().get(this.questIndex);
		}
		catch (Exception e)
		{
			if(this.questProps.getInProgressQuests().size() > 0)
			{
				currentQuest = this.questProps.getInProgressQuests().get(0);
				WyDebug.debug(String.format("\n[ArrayOutOfBounds] \n Max possible index is : %s \n But the index requested is : %s", this.questProps.getInProgressQuests().size() - 1, this.questIndex));
			}
			else
				currentQuest = null;		
			this.questIndex = 0;
		}
		
		String currentQuestName = currentQuest != null ? new TranslationTextComponent(String.format("quest.%s.name", currentQuest.getQuestId())).getFormattedText() : "None";
		double currentQuestProgress = currentQuest != null ? (currentQuest.getProgress() / currentQuest.getMaxProgress()) * 100 : -1;
		List<String> currentQuestDescription = currentQuest != null ? Arrays.stream(currentQuest.getQuestDescription()).filter(line -> !line.isEmpty()).collect(Collectors.toList()) : null;

		if(currentQuest != null)
		{
			// Quest marker rendering
			GL11.glPushMatrix();
			{
				double scale = 1.2156;
	
				GL11.glTranslated(posX + 12, posY + 90, 0);
				GL11.glTranslated(256, 256, 0);
	
				GL11.glScaled(scale, scale, 0);
				GL11.glTranslated(-256, -256, 0);
				GL11.glRotated(-12, 0, 0, 1);
					
				if(currentQuest.isPrimary())
					GL11.glColor3f(1.0F, 1.0F, 0.0F);
				else
					GL11.glColor3f(1.0F, 1.0F, 1.0F);
					
				GuiUtils.drawTexturedModalRect(0, 0, 103, 200, 12, 33, 1);	
			}
			GL11.glPopMatrix();
			
			GL11.glPushMatrix();
			{
				double scale = 1.30056;
				GL11.glTranslated(posX + 55, posY + 120, 0);
				GL11.glTranslated(256, 256, 0);
				
				GL11.glScaled(scale, scale, 0);
				GL11.glTranslated(-256, -256, 0);
				
				this.minecraft.fontRenderer.drawString(currentQuestName, 0, 0, WyHelper.hexToRGB("#161616").getRGB());
			}
			GL11.glPopMatrix();
			
			if(currentQuestProgress != -1)
				this.minecraft.fontRenderer.drawString(TextFormatting.BOLD + new TranslationTextComponent(ModI18n.GUI_QUEST_PROGRESS).getFormattedText() + " : " + String.format("%.1f", currentQuestProgress) + "%", posX + 5, posY + 65, WyHelper.hexToRGB("#161616").getRGB());

			if(currentQuestDescription != null)
			{
				int i = 18;
				for(int l = 0; l < currentQuestDescription.size(); l++)
				{
					this.minecraft.fontRenderer.drawString( new TranslationTextComponent(String.format("quest.%s.desc." + l, currentQuest.getQuestId())).getFormattedText(), posX - 20, posY + 65 + i, WyHelper.hexToRGB("#161616").getRGB());
					i += 16;
				}
			}
		}
		
		super.render(x, y, f);
	}
	
	@Override
	public void init()
	{
		int posX = (this.width - 256) / 2;
		int posY = (this.height - 256) / 2;	

		this.addButton(new NoTextureButton(posX - 65, posY + 60, 24, 125, "", b -> 
		{
			if(this.questIndex > 0)
				questIndex--;
			else
				questIndex = ModValues.MAX_QUESTS - 1;
		}));
		
		this.addButton(new NoTextureButton(posX + 290, posY + 60, 24, 125, "", b -> 
		{
			if(this.questIndex < ModValues.MAX_QUESTS - 1)
				questIndex++;
			else
				questIndex = 0;
		}));
	}
	
	@Override
	public boolean isPauseScreen()
	{
		return false;
	}
}
