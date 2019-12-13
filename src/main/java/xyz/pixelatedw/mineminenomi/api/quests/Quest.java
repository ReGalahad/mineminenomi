package xyz.pixelatedw.mineminenomi.api.quests;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.TextFormatting;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.init.ModI18n;

public abstract class Quest
{

	private CompoundNBT extraData = new CompoundNBT();	
	protected double questProgress;
	
	public abstract String getQuestId();
	
	public abstract String getQuestName();
	
	public abstract String[] getQuestDescription();

	public abstract boolean isPrimary();
	
	public abstract boolean isRepeatable();
	
	public double getProgress()
	{
		return this.questProgress;
	}
	
	public void setProgress(double progress)
	{
		if(progress <= this.getMaxProgress())
			this.questProgress = progress;
		else
			this.questProgress = this.getMaxProgress();
	}
	
	public void alterProgress(double progress) 
	{
		if(this.questProgress + progress <= this.getMaxProgress())
			this.questProgress += progress;
		else
			this.questProgress = this.getMaxProgress();
	}
	
	public double getMaxProgress()
	{
		return 1;
	}
	
	public abstract boolean canStart(PlayerEntity player);
	public abstract boolean canComplete(PlayerEntity player);

	public void start(PlayerEntity player)
	{
		String formatedQuestName = I18n.format( String.format("quest.%s.name", this.getQuestId()) );
		WyHelper.sendMsgToPlayer(player, TextFormatting.GREEN + I18n.format(ModI18n.QUEST_STARTED, formatedQuestName));	
	}
	
	public void complete(PlayerEntity player)
	{
		String formatedQuestName = I18n.format( String.format("quest.%s.name", this.getQuestId()) );
		WyHelper.sendMsgToPlayer(player, TextFormatting.GREEN + I18n.format(ModI18n.QUEST_COMPLETED, formatedQuestName));
	}

	public CompoundNBT getExtraData()
	{
		return this.extraData;
	}

	public void setExtraData(CompoundNBT extraData)
	{
		this.extraData = extraData;
	}
	
	@Override
	public Quest clone()
	{
		try
		{
			return this.getClass().newInstance();
		}
		catch (InstantiationException | IllegalAccessException e)
		{
			e.printStackTrace();
		};
		
		return null;	
	}
	
}
