package xyz.pixelatedw.MineMineNoMi3.quests.bounties;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.quests.Quest;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.pirates.PirateData;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;

public class BountyLowLevel01 extends Bounty
{
	public String getQuestID()
	{
		return "bountylowlevel_01";	
	}
	
	public String getQuestName()
	{
		return ID.LANG_QUEST_BOUNTYLOWLEVEL_01;
	}
	
	public String[] getQuestDescription()
	{
		return new String[] 
				{
					"",
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
		
		ExtendedEntityStats props = ExtendedEntityStats.get(player);
		
		props.alterBelly(100);
		
		super.finishQuest(player);
	}

	public boolean canStart(EntityPlayer player)
	{
		ExtendedEntityStats props = ExtendedEntityStats.get(player);
		return props.getFaction().equals(ID.FACTION_BOUNTYHUNTER);
	}

	public double getMaxProgress()
	{
		return 10;
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
		return false;
	}

	public boolean isTarget(EntityLivingBase target)
	{
		return target instanceof PirateData;
	}
}
