package xyz.pixelatedw.MineMineNoMi3.api.quests;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import xyz.pixelatedw.MineMineNoMi3.api.network.PacketQuestSync;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.lists.ListQuests;

public abstract class Quest
{

	protected double questProgress;
	
	public abstract String getQuestID();
	
	public abstract String getQuestName();
	
	public abstract String[] getQuestDescription();
	
	public abstract void startQuest(EntityPlayer player);
	
	public abstract boolean isPrimary();
	
	public void finishQuest(EntityPlayer player)
	{
		QuestProperties questProps = QuestProperties.get(player);

		questProps.removeQuestFromTracker(ListQuests.swordsmanProgression01);
		WyNetworkHelper.sendTo(new PacketQuestSync(questProps), (EntityPlayerMP) player);
	}
	
	public abstract boolean canStart(EntityPlayer player);
	
	public boolean isFinished(EntityPlayer player)
	{
		if(this.questProgress >= this.getMaxProgress())
		{
			return true;
		}
		
		return false;
	}
	
	public double getProgress()
	{
		return questProgress;
	}
	
	public double getMaxProgress()
	{
		return 1;
	}
	
	public void setProgress(EntityPlayer player, double progress)
	{
		if(progress <= this.getMaxProgress())
			this.questProgress = progress;
		else
			this.questProgress = this.getMaxProgress();
	
		if(this.isFinished(player))
			this.finishQuest(player);		
	}

	public void alterProgress(EntityPlayer player, double progress) 
	{
		if(this.questProgress + progress <= this.getMaxProgress())
			this.questProgress += progress;
		else
			this.questProgress = this.getMaxProgress();
	
		if(this.isFinished(player))
			this.finishQuest(player);		
	}

}
