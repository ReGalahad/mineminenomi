package xyz.pixelatedw.mineminenomi.screens.extra;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.client.gui.ScrollPanel;
import net.minecraftforge.fml.client.config.GuiUtils;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.screens.QuestChooseScreen;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.data.quest.IQuestData;
import xyz.pixelatedw.wypi.network.WyNetwork;
import xyz.pixelatedw.wypi.network.packets.client.CSyncQuestDataPacket;
import xyz.pixelatedw.wypi.quests.Quest;

public class AvailableQuestsListScreenPanel extends ScrollPanel
{
	private QuestChooseScreen parent;
	private IQuestData props;
	private List<Quest> availableQuests = new ArrayList<Quest>();
	private static final int ENTRY_HEIGHT = 20;
	private FontRenderer font;
	
	public AvailableQuestsListScreenPanel(QuestChooseScreen parent, IQuestData abilityProps, Quest[] quests)
	{
		super(parent.getMinecraft(), 200, 180, parent.height / 2 - 90, parent.width / 2 - 190);
		this.parent = parent;
		this.props = abilityProps;
		this.font = parent.getMinecraft().fontRenderer;

		this.updateAvailableQuests(quests);
		
		this.scrollDistance = -10;
	}

	public void updateAvailableQuests(List<Quest> quests)
	{
		Quest[] arr = new Quest[quests.size()];
		Quest[] questsArray = quests.toArray(arr);
		this.updateAvailableQuests(questsArray);
	}
	
	public void updateAvailableQuests(Quest[] quests)
	{
		this.availableQuests.clear();
		for (int i = 0; i <= quests.length - 1; i++)
		{
			Quest quest = quests[i];
			boolean exists = quest != null;
			boolean isNotFinished = exists && !this.props.hasFinishedQuest(quest);
			boolean isNotInProgress = exists && (this.props.getInProgressQuest(quest) == null || (this.props.getInProgressQuest(quest) != null && this.props.getInProgressQuest(quest).isComplete()));
			
			if (isNotFinished && isNotInProgress)
			{
				this.availableQuests.add(quests[i]);
			}
		}
	}
	
	@Override
	public boolean mouseReleased(double p_mouseReleased_1_, double p_mouseReleased_3_, int p_mouseReleased_5_)
	{
		return true;
	}
	
	@Override
	protected int getContentHeight()
	{
		return (this.availableQuests.size()) * ENTRY_HEIGHT * 3 - 30;
	}

	@Override
	protected int getScrollAmount()
	{
		return 12;
	}
	
	@Override
	public void render(int mouseX, int mouseY, float partialTicks)
	{
		Tessellator tess = Tessellator.getInstance();
		BufferBuilder worldr = tess.getBuffer();

		double scale = this.parent.getMinecraft().mainWindow.getGuiScaleFactor();
		GL11.glEnable(GL11.GL_SCISSOR_TEST);
		GL11.glScissor((int) (this.left * scale), (int) (this.parent.getMinecraft().mainWindow.getFramebufferHeight() - (this.bottom * scale)), (int) (this.width * scale), (int) (this.height * scale));

		int baseY = this.top + border - (int) this.scrollDistance;
		this.drawPanel(this.right, baseY, tess, mouseX, mouseY);

		GL11.glDisable(GL11.GL_SCISSOR_TEST);
		
		//super.render(mouseX, mouseY, partialTicks);
	}
	
	@Override
	protected void drawPanel(int entryRight, int relativeY, Tessellator tess, int mouseX, int mouseY)
	{
		for (Quest quest : this.availableQuests)
		{
			if(quest == null)
				continue;
			
			float y = relativeY;
			float x = (this.parent.width / 2 - 109) + 40;

			String formattedQuestName = I18n.format("quest." + APIConfig.PROJECT_ID + "." + WyHelper.getResourceName(quest.getId()));
			String questColor = "#FFFFFF";

			Quest inProgressQuest = this.props.getInProgressQuest(quest);
			if(inProgressQuest != null)
			{				
				if(this.isMouseOverQuest(mouseX, mouseY, inProgressQuest))
				{
					formattedQuestName = "Already in progress!";
				}
				
				if(inProgressQuest.isComplete())
				{
					questColor = "#00FF55";
				}
			}
			
			if(quest.isLocked(this.props))
			{
				questColor = "#505050";
			}
			
			if(this.parent.isAnimationComplete() && this.isMouseOverQuest(mouseX, mouseY, quest))
			{
				GlStateManager.color3f(0.8f, 0.8f, 0.8f);
			}
			
			GlStateManager.pushMatrix();
			{
				Minecraft.getInstance().getTextureManager().bindTexture(ModResources.SCROLL);
				double scale = 0.5;
				GlStateManager.translated(x - 180, y - 196, 0);
				GlStateManager.translated(256, 256, 0);
				
				GlStateManager.scaled(scale * 1.5, scale * 0.6, 0);
				GlStateManager.translated(-256, -256, 0);
				
				// Background
				GuiUtils.drawTexturedModalRect(0, 0, 0, 0, 256, 256, 1);
			}
			GlStateManager.popMatrix();

			if(this.parent.isAnimationComplete())
				GlStateManager.color3f(1f, 1f, 1f);
			
			if(this.font.getStringWidth(formattedQuestName) > 140)
			{
				GlStateManager.pushMatrix();
				{
					List<String> splittedText = WyHelper.splitString(this.font, formattedQuestName, (int) x - 80, (int) y + 16, 140);
					GlStateManager.translated(0, -((splittedText.size() - 1) * 5), 0);
					for(String string : splittedText)
					{
						WyHelper.drawStringWithBorder(this.font, string, (int) x - 80, (int) y + 16, WyHelper.hexToRGB(questColor).getRGB());
						y += 10;
					}
				}
				GlStateManager.popMatrix();
			}
			else
			{
				WyHelper.drawStringWithBorder(this.font, formattedQuestName, (int) x - 80, (int) y + 16, WyHelper.hexToRGB(questColor).getRGB());
			}
			
			relativeY += ENTRY_HEIGHT * 2.75;
		}	
	}
	
	@Override
	public boolean mouseClicked(final double mouseX, final double mouseY, final int button)
	{		
		Quest quest = this.findQuestEntry((int) mouseX, (int) mouseY);
		Quest inProgressQuest = this.props.getInProgressQuest(quest);

		if(button != 0)
			return false;
		
		if(inProgressQuest != null && inProgressQuest.isComplete())
		{
			this.props.addFinishedQuest(inProgressQuest);
			this.props.removeInProgressQuest(inProgressQuest);
			inProgressQuest.giveReward(Minecraft.getInstance().player);
			WyNetwork.sendToServer(new CSyncQuestDataPacket(this.props));
			this.updateAvailableQuests(this.availableQuests);
		}
		else if(quest != null && inProgressQuest == null)
		{
			if(!quest.isLocked(props))
			{
				this.props.addInProgressQuest(quest);
				WyNetwork.sendToServer(new CSyncQuestDataPacket(this.props));
				this.updateAvailableQuests(this.availableQuests);
			}
		}
		
		return super.mouseClicked(mouseX, mouseY, button);
	}

	public boolean isMouseOverQuest(double mouseX, double mouseY, Quest overQuest)
	{
		Quest quest = this.findQuestEntry((int) mouseX, (int) mouseY);

		if(quest != null && quest.equals(overQuest))
		{
			return super.isMouseOver(mouseX, mouseY);
		}
		
		return false;
	}
	
	private Quest findQuestEntry(final int mouseX, final int mouseY)
	{
		double offset = (mouseY - this.top) + this.scrollDistance;

		if (offset <= 0)
			return null;

		int lineIdx = (int) (offset / (ENTRY_HEIGHT * 2.75));
		if (lineIdx >= this.availableQuests.size())
			return null;

		Quest quest = this.availableQuests.get(lineIdx);
		if (quest != null)
		{
			return quest;
		}

		return null;
	}
}
