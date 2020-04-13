package xyz.pixelatedw.mineminenomi.screens;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
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
	private float animationTime = 0;
	private float animationTranslation = 100;
	
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

		if(this.animationTime < 10)
		{
			this.animationTime += 0.1;
		}
		if(this.animationTranslation > 0)
		{
			this.animationTranslation = 100 - this.animationTime * 40;
		}

		// Quest Giver model
		GlStateManager.pushMatrix();
		{
			GlStateManager.translatef(this.animationTranslation, 0, 0);
			GlStateManager.color4f(1, 1, 1, 0.1F + this.animationTime / 4);
			GlStateManager.enableBlend();
			if (this.questGiver instanceof LivingEntity)
				WyHelper.drawEntityOnScreen(posX + 150, posY + 150, 100, 40, 5, (LivingEntity) this.questGiver);
		}
		GlStateManager.popMatrix();
		
		// Quests
		GlStateManager.pushMatrix();
		{
			GlStateManager.translatef(-this.animationTranslation, 0, 0);
			GlStateManager.color4f(1, 1, 1, 0.1F + this.animationTime / 5);
			GlStateManager.enableBlend();
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
	
	public boolean isAnimationComplete()
	{
		if(this.animationTime >= 5)
			return true;
		return false;
	}
}
