package xyz.pixelatedw.MineMineNoMi3.quests.questlines.swordsmanprogression;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.math.WyMathHelper;
import xyz.pixelatedw.MineMineNoMi3.api.quests.Quest;
import xyz.pixelatedw.MineMineNoMi3.api.quests.QuestProperties;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;
import xyz.pixelatedw.MineMineNoMi3.lists.ListQuests;
import xyz.pixelatedw.MineMineNoMi3.quests.EnumQuestlines;
import xyz.pixelatedw.MineMineNoMi3.quests.IProgressionQuest;
import xyz.pixelatedw.MineMineNoMi3.quests.ITimedQuest;

public class QuestSwordsmanProgression02 extends Quest implements ITimedQuest, IProgressionQuest
{

	public String getQuestID()
	{
		return "swordsmanprogression02";	
	}
	
	public String getQuestName()
	{
		return "Staying Alive";
	}
	
	public String[] getQuestDescription()
	{
		return new String[] 
				{
					" He agreed to train me, the following days will ",
					"be spent training my strength, stamina and how to ",
					"focus my power in my blade.",
					"But first...I need to survive this night.",
					"",
					"",
					""
				};
	}
	
	public void startQuest(EntityPlayer player)
	{
		WyHelper.sendMsgToPlayer(player, "<Swordsman Master> First we'll test your determination, you must survive the night in the wilderness without dying !");
		
		this.extraData = new NBTTagCompound();	
		this.extraData.setLong("currentDays", (int) (player.worldObj.getWorldTime()));
		
		super.startQuest(player);
	}

	public void finishQuest(EntityPlayer player)
	{
		boolean extraDays = (int) (player.worldObj.getWorldTime() / 24000) >= (this.extraData.getLong("currentDays") / 24000) + 3 ;
		
		if(extraDays)
			WyHelper.sendMsgToPlayer(player, "<Swordsman Master> Almost thought you died there kid, but there's no time to rest, hope you're ready for your next trial !");
		else
			WyHelper.sendMsgToPlayer(player, "<Swordsman Master> Seems like it was too easy for you ?");
		
		super.finishQuest(player);
	}

	public boolean canStart(EntityPlayer player)
	{
		ExtendedEntityStats props = ExtendedEntityStats.get(player);
		QuestProperties questProps = QuestProperties.get(player);
		
		boolean flag1 = !props.getFightStyle().equals(ID.FSTYLE_SWORDSMAN) || !questProps.hasQuestCompleted(ListQuests.swordsmanProgression01);
		
		if(flag1)
			return false;
		
		if(!player.worldObj.isDaytime())
		{
			WyHelper.sendMsgToPlayer(player, "<Swordsman Master> There is no point in starting this trial now it's too late, come back in the morning and we'll talk then.");
			return false;
		}
		
		return true;
	}

	public double getMaxProgress()
	{
		return 1;
	}

	public void setProgress(EntityPlayer player, double progress) 
	{
		super.setProgress(player, progress);
	}
	
	public boolean isFinished(EntityPlayer player)
	{	
		if((int) (player.worldObj.getWorldTime() / 24000) >= (this.extraData.getLong("currentDays") / 24000) + 1) 
			return true;
		
		return false;
	}
	
	public void alterProgress(EntityPlayer player, double progress) 
	{
		super.alterProgress(player, progress);
		
		if(this.isFinished(player))
			this.finishQuest(player);	
	}

	public boolean isPrimary()
	{
		return true;
	}

	public EnumQuestlines getQuestLine()
	{
		return EnumQuestlines.SWORDSMANPROGRESSION;
	}

	public boolean isRepeatable()
	{
		return false;
	}

	@Override
	public void onTimePassEvent(EntityPlayer player)
	{
		if(!this.isFinished(player))
			this.setProgress(player, (player.worldObj.getWorldTime() - this.extraData.getLong("currentDays")) / (double)(24000) );
	}

}
