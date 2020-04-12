package xyz.pixelatedw.mineminenomi.screens.extra;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
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
import xyz.pixelatedw.wypi.quests.Quest;

public class AvailableQuestsListScreenPanel extends ScrollPanel
{
	private QuestChooseScreen parent;
	private IQuestData props;
	private List<Quest> availableQuests = new ArrayList<Quest>();
	private static final int ENTRY_HEIGHT = 20;

	public AvailableQuestsListScreenPanel(QuestChooseScreen parent, IQuestData abilityProps, Quest[] quests)
	{
		super(parent.getMinecraft(), 200, 180, parent.height / 2 - 90, parent.width / 2 - 190);
		this.parent = parent;
		this.props = abilityProps;

		for (int i = 0; i <= quests.length - 1; i++)
		{
			if (quests[i] != null && !this.props.hasFinishedQuest(quests[i]))
			{
				this.availableQuests.add(quests[i]);
			}
		}
		this.scrollDistance = (float) (-ENTRY_HEIGHT * 2.5);
	}

	@Override
	public boolean mouseReleased(double p_mouseReleased_1_, double p_mouseReleased_3_, int p_mouseReleased_5_)
	{
		return true;
	}
	
	@Override
	protected int getContentHeight()
	{
		return ((this.availableQuests.size()) * ENTRY_HEIGHT) + 50;
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
		GL11.glScissor((int) (left * scale), (int) (this.parent.getMinecraft().mainWindow.getFramebufferHeight() - (bottom * scale)), (int) (width * scale), (int) (height * scale));

		this.drawGradientRect(this.left, this.top, this.right, this.bottom, WyHelper.hexToRGB("#ceae7877").getRGB(), WyHelper.hexToRGB("#ceae7877").getRGB());

		int baseY = this.top + border - (int) this.scrollDistance;
		this.drawPanel(right, baseY, tess, mouseX, mouseY);

		GL11.glDisable(GL11.GL_SCISSOR_TEST);
		
		//super.render(mouseX, mouseY, partialTicks);
	}
	
	@Override
	protected void drawPanel(int entryRight, int relativeY, Tessellator tess, int mouseX, int mouseY)
	{
		for (Quest quest : this.availableQuests)
		{
			float y = relativeY;
			float x = (this.parent.width / 2 - 109) + 40;

			String formattedQuestName = I18n.format("quest." + APIConfig.PROJECT_ID + "." + WyHelper.getResourceName(quest.getId()));
			String questColor = "#FFFFFF";

			if(this.props.getInProgressQuest(quest).isCompleted())
			{
				questColor = "#00FF55";
			}
			
			if(this.isMouseOver(mouseX, mouseY))
			{
				formattedQuestName = "Quest already in progress!";
				if(this.props.getInProgressQuest(quest).isCompleted())
					formattedQuestName = "Can be turned in!";
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

			WyHelper.drawStringWithBorder(formattedQuestName, (int) x - 80, (int) y + 16, WyHelper.hexToRGB(questColor).getRGB(), false);

			relativeY += ENTRY_HEIGHT * 1.25;
		}	
	}
	
	@Override
	public boolean mouseClicked(final double mouseX, final double mouseY, final int button)
	{		
		Quest quest = this.findQuestEntry((int) mouseX, (int) mouseY);

		if(!this.isMouseOver(mouseX, mouseY))
			return false;
		
		return super.mouseClicked(mouseX, mouseY, button);
	}

	@Override
	public boolean isMouseOver(double mouseX, double mouseY)
	{
		Quest quest = this.findQuestEntry((int) mouseX, (int) mouseY);
		
		if(quest != null)
		{
			return true;
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
