package xyz.pixelatedw.mineminenomi.screens;

import java.util.ArrayList;
import java.util.List;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnchantmentNameParts;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.config.GuiUtils;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.data.quest.IQuestData;
import xyz.pixelatedw.wypi.data.quest.QuestDataCapability;
import xyz.pixelatedw.wypi.debug.WyDebug;
import xyz.pixelatedw.wypi.network.WyNetwork;
import xyz.pixelatedw.wypi.network.packets.client.CSyncQuestDataPacket;
import xyz.pixelatedw.wypi.quests.Quest;
import xyz.pixelatedw.wypi.quests.objectives.IObtainItemObjective;
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
		
		// Background
		Minecraft.getInstance().getTextureManager().bindTexture(ModResources.BLANK);
		GlStateManager.pushMatrix();
		{
			double scale = 1.4;
			GlStateManager.translated(posX - 72, posY - 15, 0);
			GlStateManager.translated(256, 256, 0);
			
			GlStateManager.scaled(scale, scale, 0);
			GlStateManager.translated(-256, -256, 0);
			
			// Background
			GuiUtils.drawTexturedModalRect(0, 0, 0, 0, 256, 256, 1);
			
			// Next / Previous buttons
			this.minecraft.getTextureManager().bindTexture(ModResources.WIDGETS);
			GuiUtils.drawTexturedModalRect(-20, 30, 0, 92, 25, 100, 1);
			GuiUtils.drawTexturedModalRect(232, 30, 26, 92, 30, 100, 1);		
		}
		GlStateManager.popMatrix();

		try
		{
			this.currentQuest = this.qprops.getInProgressQuests().get(this.questIndex);
		}
		catch (Exception e)
		{
			if(this.qprops.getInProgressQuests().size() > 0)
			{
				this.currentQuest = this.qprops.getInProgressQuests().get(0);
				WyDebug.debug(String.format("\n[ArrayOutOfBounds] \n Max possible index is : %s \n But the index requested is : %s", this.qprops.getInProgressQuests().size() - 1, this.questIndex));
			}
			else
				this.currentQuest = null;		
			this.questIndex = 0;
		}
		
		String currentQuestName = this.currentQuest != null ? new TranslationTextComponent(String.format("quest." + APIConfig.PROJECT_ID + ".%s", this.currentQuest.getId())).getFormattedText() : "None";
		double currentQuestProgress = this.currentQuest != null ? this.currentQuest.getProgress() * 100 : -1;
		
		if(this.currentQuest != null)
		{
			// Quest name
			GlStateManager.pushMatrix();
			{
				double scale = 1.4;
				GlStateManager.translated(posX + 100, posY + 15, 0);
				GlStateManager.translated(256, 256, 0);
				
				GlStateManager.scaled(scale, scale, 0);
				GlStateManager.translated(-256, -256, 0);

				WyHelper.drawStringWithBorder(currentQuestName, 0, 0, WyHelper.hexToRGB("#FFFFFF").getRGB(), true);
			}
			GlStateManager.popMatrix();
			
			// Quest progress
			if(currentQuestProgress != -1)
			{
				String progress = TextFormatting.BOLD + new TranslationTextComponent(ModI18n.GUI_QUEST_PROGRESS).getFormattedText() + " : " + String.format("%.1f", currentQuestProgress) + "%";
				WyHelper.drawStringWithBorder(progress, posX - 90, posY - 65, WyHelper.hexToRGB("#FFFFFF").getRGB(), false);
			}
			
			// Quest Objective
			GlStateManager.pushMatrix();
			{
				int yOffset = -20;
				for(Objective obj : this.currentQuest.getObjectives())
				{
					String objectiveName = new TranslationTextComponent(String.format("quest.objective." + APIConfig.PROJECT_ID + ".%s", obj.getId())).getFormattedText();
					String progress = "";
					double objectiveProgress = (obj.getProgress() / obj.getMaxProgress()) * 100;
					yOffset += 20;

					String textColor = "#FFFFFF";
					if(obj.isLocked())
						textColor = "#505050";
					else
						progress = " - " + String.format("%.1f", objectiveProgress) + "%";
					
					if(obj.isComplete())
						textColor = "#00FF55";
					
					if(obj.isHidden())
					{
			            FontRenderer galacticFont = this.minecraft.getFontResourceManager().getFontRenderer(Minecraft.standardGalacticFontRenderer);
			            WyHelper.drawStringWithBorder("• ", posX - 90, posY - 45 + yOffset, WyHelper.hexToRGB(textColor).getRGB(), false);
						WyHelper.drawStringWithBorder(galacticFont, this.hiddenTexts.get((int) WyHelper.randomWithRange(0, this.hiddenTexts.size() - 1)), posX - 82, posY - 45 + yOffset, WyHelper.hexToRGB(textColor).getRGB(), false);
					}
					else
						WyHelper.drawStringWithBorder((obj.isComplete() ? TextFormatting.STRIKETHROUGH + "" : "") + "• " + objectiveName + progress, posX - 90, posY - 45 + yOffset, WyHelper.hexToRGB(textColor).getRGB(), false);
				}
			}
			GlStateManager.popMatrix();
		}
		
		super.render(x, y, f);
	}
	
	@Override
	public void init()
	{
		int posX = this.width / 2;
		int posY = this.height / 2;
				
		try
		{
			this.currentQuest = this.qprops.getInProgressQuests().get(this.questIndex);
		}
		catch (Exception e)
		{
			if(this.qprops.getInProgressQuests().size() > 0)
			{
				this.currentQuest = this.qprops.getInProgressQuests().get(0);
				WyDebug.debug(String.format("\n[ArrayOutOfBounds] \n Max possible index is : %s \n But the index requested is : %s", this.qprops.getInProgressQuests().size() - 1, this.questIndex));
			}
			else
				this.currentQuest = null;		
			this.questIndex = 0;
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
			
			if (!obj.isHidden() && !obj.isLocked() && !obj.isComplete() && obj instanceof IObtainItemObjective)
			{
				for(ItemStack stack : this.player.inventory.mainInventory)
				{
					if (((IObtainItemObjective) obj).checkItem(stack))
					{
						obj.alterProgress(1);
						WyNetwork.sendToServer(new CSyncQuestDataPacket(QuestDataCapability.get(this.player)));
					}
				}
			}
		}
	}
	
	@Override
	public boolean isPauseScreen()
	{
		return false;
	}

	/*
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
	*/
}
