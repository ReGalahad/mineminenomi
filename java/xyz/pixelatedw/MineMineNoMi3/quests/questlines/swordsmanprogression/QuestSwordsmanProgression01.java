package xyz.pixelatedw.MineMineNoMi3.quests.questlines.swordsmanprogression;

import net.minecraft.entity.player.EntityPlayer;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.quests.Quest;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;

public class QuestSwordsmanProgression01 extends Quest
{
	
	public String getQuestID()
	{
		return "swordsmanprogression_01";	
	}
	
	public String getQuestName()
	{
		return ID.LANG_QUEST_SWORDSMANPROGRESSION_01;
	}
	
	public String[] getQuestDescription()
	{
		return new String[] 
				{
					ID.LANG_QUEST_SWORDSMANPROGRESSION_01_DESC,
					"",
					"",
					"",
					"",
					"",
					"",
				};
	}
	
	public void startQuest(EntityPlayer player)
	{
		WyHelper.sendMsgToPlayer(player, this.getQuestName() + " has started !");
	}

	public void finishQuest(EntityPlayer player)
	{
		WyHelper.sendMsgToPlayer(player, this.getQuestName() + " has been completed !");	
		
		super.finishQuest(player);
	}

	public boolean canStart(EntityPlayer player)
	{
		ExtendedEntityStats props = ExtendedEntityStats.get(player);
		return props.getFightStyle().equals(ID.FSTYLE_SWORDSMAN);
	}

	public double getMaxProgress()
	{
		return 1;
	}

	public void setProgress(EntityPlayer player, double progress) 
	{
		super.setProgress(player, progress);
	}
	
	public void alterProgress(EntityPlayer player, double progress) 
	{
		super.alterProgress(player, progress);
	}

	public boolean isPrimary()
	{
		return true;
	}

}
