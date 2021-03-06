package xyz.pixelatedw.mineminenomi.screens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.EnchantmentNameParts;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.config.GuiUtils;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.screens.extra.TexturedIconButton;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.data.quest.IQuestData;
import xyz.pixelatedw.wypi.data.quest.QuestDataCapability;
import xyz.pixelatedw.wypi.debug.WyDebug;
import xyz.pixelatedw.wypi.quests.Quest;
import xyz.pixelatedw.wypi.quests.objectives.Objective;

@OnlyIn(Dist.CLIENT)
public class QuestsTrackerScreen extends Screen
{
	private PlayerEntity player;
	private IQuestData qprops;
	private int questIndex = 0;
	private List<String> hiddenTexts = new ArrayList<String>();
	private Quest currentQuest = null;

	public QuestsTrackerScreen(PlayerEntity player)
	{
		super(new StringTextComponent(""));
		this.player = player;
		this.qprops = QuestDataCapability.get(player);
	}
	
	@Override
	public void render(int x, int y, float f)
	{
		this.renderBackground();
		GlStateManager.color4f(1F, 1F, 1F, 1F);
		
		int posX = this.width / 2;
		int posY = this.height / 2;
		
		Minecraft.getInstance().getTextureManager().bindTexture(ModResources.BLANK);
		GlStateManager.pushMatrix();
		{
			double scale = 1.1;
			GlStateManager.translated(posX - 35, posY + 10, 0);
			GlStateManager.translated(256, 256, 0);
			
			GlStateManager.scaled(scale * 1.5, scale * 1.4, 0);
			GlStateManager.translated(-256, -256, 0);
			//GlStateManager.rotated(90, 0, 0, 1);
			
			// Background
			GuiUtils.drawTexturedModalRect(0, 0, 0, 0, 256, 256, 1);
			
			GlStateManager.translated(-30, 50, 0);
			GlStateManager.translated(256, 256, 0);
			
			GlStateManager.scaled(scale * 0.7, scale * 0.9, 0);
			GlStateManager.translated(-256, -256, 0);	
		}
		GlStateManager.popMatrix();
		
		String currentQuestName = this.currentQuest != null ? new TranslationTextComponent(String.format("quest." + APIConfig.PROJECT_ID + ".%s", this.currentQuest.getId())).getFormattedText() : "None";
		double currentQuestProgress = this.currentQuest != null ? this.currentQuest.getProgress() * 100 : -1;
			
		GlStateManager.translated(0, 10, 0);
		
		if(this.currentQuest != null)
		{
			// Quest name
			GlStateManager.pushMatrix();
			{
				double scale = 1.4;
				GlStateManager.translated(posX + 100, posY + 10, 0);
				GlStateManager.translated(256, 256, 0);
				
				GlStateManager.scaled(scale, scale, 0);
				GlStateManager.translated(-256, -256, 0);

				WyHelper.drawStringWithBorder(this.font, currentQuestName, -font.getStringWidth(currentQuestName) / 2, 0, WyHelper.hexToRGB("#FFFFFF").getRGB());
			}
			GlStateManager.popMatrix();
			
			// Quest progress
			if(currentQuestProgress != -1)
			{
				String textColor = "#FFFFFF";
				if(this.currentQuest.isComplete())
					textColor = "#00FF55";
				String progress = TextFormatting.BOLD + new TranslationTextComponent(ModI18n.GUI_QUEST_PROGRESS).getFormattedText() + " : " + String.format("%.1f", currentQuestProgress) + "%";
				WyHelper.drawStringWithBorder(this.font, progress, posX - 120, posY - 65, WyHelper.hexToRGB(textColor).getRGB());
			}
			
			// Quest Objective
			GlStateManager.pushMatrix();
			{
				List<Objective> avilableObjectives = this.currentQuest.getObjectives().stream().limit(5).collect(Collectors.toList());

				int yOffset = -20;
				int i = 0;
				for(Objective obj : avilableObjectives)
				{
					if(obj.isComplete())
						continue;
					
					String objectiveName = obj.getLocalizedTitle();
					String progress = "";
					double objectiveProgress = (obj.getProgress() / obj.getMaxProgress()) * 100;
					List<Objective> hiddenObjs = avilableObjectives.stream().filter(o -> o.isHidden()).collect(Collectors.toList());
					yOffset += 20;

					String textColor = "#FFFFFF";
					if(obj.isLocked())
						textColor = "#505050";
					else
						progress = " - " + String.format("%.1f", objectiveProgress) + "%";
					
					if(obj.isHidden())
					{
			            FontRenderer galacticFont = this.minecraft.getFontResourceManager().getFontRenderer(Minecraft.standardGalacticFontRenderer);
			            WyHelper.drawStringWithBorder(this.font, "• ", posX - 130, posY - 45 + yOffset, WyHelper.hexToRGB(textColor).getRGB());
			            if(hiddenObjs.size() > 0)
			            	WyHelper.drawStringWithBorder(galacticFont, this.hiddenTexts.get(hiddenObjs.indexOf(obj)), posX - 123, posY - 45 + yOffset, WyHelper.hexToRGB(textColor).getRGB());
					}
					else
					{
						objectiveName = "• " + objectiveName + " " + progress;
						List<String> splitText = WyHelper.splitString(this.font, objectiveName, posX, 280);
						for(int j = 0; j < splitText.size(); j++)
						{
							WyHelper.drawStringWithBorder(this.font, (obj.isComplete() ? TextFormatting.STRIKETHROUGH + "" : "") + splitText.get(j), posX - 130, posY - 45 + yOffset + (j * 12), WyHelper.hexToRGB(textColor).getRGB());							
						}
						yOffset += (splitText.size() * 8);
					}
					i++;
				}
				
				if(i == 0)
					WyHelper.drawStringWithBorder(this.font, new TranslationTextComponent(ModI18n.QUEST_NO_OBJECTIVES_LEFT).getFormattedText(), posX - 120, posY - 20 + yOffset, WyHelper.hexToRGB("#FFFFFF").getRGB());
			}
			GlStateManager.popMatrix();
			
			GlStateManager.translated(0, 20, 0);
		}
		
		super.render(x, y, f);
	}
	
	@Override
	public void init()
	{	
		int posX = (this.width - 256) / 2;
		int posY = (this.height - 256) / 2;
			
		try
		{
			this.currentQuest = this.qprops.getInProgressQuests()[this.questIndex];
		}
		catch (Exception e)
		{
			if(this.qprops.getInProgressQuests().length > 0)
			{
				this.currentQuest = this.qprops.getInProgressQuests()[0];
				WyDebug.debug(String.format("\n[ArrayOutOfBounds] \n Max possible index is : %s \n But the index requested is : %s", this.qprops.getInProgressQuests().length - 1, this.questIndex));
			}
			else
				this.currentQuest = null;		
			this.questIndex = 0;
			e.printStackTrace();
		}
		
		if(this.currentQuest == null)
			return;
		
		this.hiddenTexts.clear();
		for(Objective obj : this.currentQuest.getObjectives())
		{
			if(obj.isHidden())
			{
				this.hiddenTexts.add(EnchantmentNameParts.getInstance().generateNewRandomName(Minecraft.getInstance().fontRenderer, obj.getTitle().length() * 2));
			}
		}
		
		List<Quest> availableQuests = Arrays.asList(this.qprops.getInProgressQuests()).stream().filter(quest -> quest != null).collect(Collectors.toList());
		
		TexturedIconButton nextButton = new TexturedIconButton(ModResources.BIG_WOOD_BUTTON_RIGHT, posX + 285, posY + 80, 24, 100, "", (btn) -> 
		{
			if(this.questIndex + 1 < availableQuests.size())
				this.questIndex++;
			else
				this.questIndex = 0;
			this.init();
		});
		nextButton = nextButton.setTextureInfo(posX + 280, posY + 35, 32, 128);
		this.addButton(nextButton);
		
		TexturedIconButton prevButton = new TexturedIconButton(ModResources.BIG_WOOD_BUTTON_LEFT, posX - 55, posY + 80, 24, 100, "", (btn) -> 
		{
			if(this.questIndex > 0)
				this.questIndex--;
			else
				this.questIndex = availableQuests.size() - 1;
			this.init();
		});
		prevButton = prevButton.setTextureInfo(posX - 58, posY + 35, 32, 128);
		this.addButton(prevButton);
	}
	
	@Override
	public boolean isPauseScreen()
	{
		return false;
	}
}
