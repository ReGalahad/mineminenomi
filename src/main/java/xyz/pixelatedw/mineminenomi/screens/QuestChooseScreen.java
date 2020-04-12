package xyz.pixelatedw.mineminenomi.screens;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.config.GuiUtils;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.screens.extra.AvailableQuestsListScreenPanel;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.data.quest.IQuestData;
import xyz.pixelatedw.wypi.data.quest.QuestDataCapability;
import xyz.pixelatedw.wypi.quests.Quest;

@OnlyIn(Dist.CLIENT)
public class QuestChooseScreen extends Screen
{
	private PlayerEntity player;
	private IQuestData qprops;
	private Quest[] availableQuests = new Quest[0];
	private Entity questGiver;
	
	private AvailableQuestsListScreenPanel availableQuestsPanel;
	
	public QuestChooseScreen(PlayerEntity player, Entity questGiver, Quest[] availableQuests)
	{
		super(new StringTextComponent(""));
		this.player = player;
		this.qprops = QuestDataCapability.get(player);
		this.availableQuests = availableQuests;
		this.questGiver = questGiver;
	}

	@Override
	public void render(int mouseX, int mouseY, float partialTicks)
	{
		this.renderBackground();
		GlStateManager.color4f(1F, 1F, 1F, 1F);
		
		int posX = this.width / 2;
		int posY = this.height / 2;
		
		// Background
		Minecraft.getInstance().getTextureManager().bindTexture(ModResources.BLANK_SLIM);
		GlStateManager.pushMatrix();
		{
			double scale = 1.4;
			GlStateManager.translated(posX - 65, posY - 15, 0);
			GlStateManager.translated(256, 256, 0);
			
			GlStateManager.scaled(scale  * 1.1, scale, 0);
			GlStateManager.translated(-256, -256, 0);
			
			// Background
			GuiUtils.drawTexturedModalRect(0, 0, 0, 0, 256, 256, 1);
		}
		GlStateManager.popMatrix();
		
		// Quest Giver model
		GlStateManager.pushMatrix();
		{
			if(this.questGiver instanceof LivingEntity)
				WyHelper.drawEntityOnScreen(posX + 150, posY + 150, 100, 40, 5, (LivingEntity)this.questGiver);
		}
		GlStateManager.popMatrix();
		
		// Quest Giver
		GlStateManager.pushMatrix();
		{
			this.availableQuestsPanel.render(mouseX, mouseY, partialTicks);
			this.availableQuestsPanel.isMouseOver(mouseX, mouseY);
		}
		GlStateManager.popMatrix();
		
		super.render(mouseX, mouseY, partialTicks);
	}
	
	@Override
	public void init()
	{
		this.availableQuestsPanel = new AvailableQuestsListScreenPanel(this, this.qprops, this.availableQuests);
		this.children.add(this.availableQuestsPanel);
		this.setFocused(this.availableQuestsPanel);
	}
}
