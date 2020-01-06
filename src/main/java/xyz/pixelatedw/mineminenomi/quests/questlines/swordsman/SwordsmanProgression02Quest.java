package xyz.pixelatedw.mineminenomi.quests.questlines.swordsman;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.data.quest.IQuestData;
import xyz.pixelatedw.mineminenomi.api.data.quest.QuestDataCapability;
import xyz.pixelatedw.mineminenomi.api.network.packets.client.CQuestDataSyncPacket;
import xyz.pixelatedw.mineminenomi.api.quests.IProgressionQuest;
import xyz.pixelatedw.mineminenomi.api.quests.ITimedQuest;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
import xyz.pixelatedw.mineminenomi.api.quests.extra.Questline;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.init.ModQuestlines;
import xyz.pixelatedw.mineminenomi.init.ModQuests;

public class SwordsmanProgression02Quest extends Quest implements IProgressionQuest, ITimedQuest
{

	@Override
	public String getQuestId()
	{
		return "swordsman_progression_02";
	}

	@Override
	public String getQuestName()
	{
		return "Staying Alive";
	}

	@Override
	public String[] getQuestDescription()
	{
		return new String[]
			{
					"- Survive the night",
					"",
					"",
					"",
					"",
					"",
					""
			};
	}

	@Override
	public boolean isPrimary()
	{
		return true;
	}

	@Override
	public boolean isRepeatable()
	{
		return false;
	}

	@Override
	public void start(PlayerEntity player)
	{
		super.start(player);
		IQuestData questProps = QuestDataCapability.get(player);

		String questGiverName = I18n.format("entity.mineminenomi.dojo_sensei");
		WyHelper.sendMsgToPlayer(player, I18n.format(ModI18n.QUEST_SWORDSMAN_PROGRESSION_02_START, questGiverName));
		
		this.getExtraData().putLong("currentDays", player.world.getDayTime());
		ModNetwork.sendToServer(new CQuestDataSyncPacket(questProps));	
	}
	
	@Override
	public boolean canStart(PlayerEntity player)
	{
		IEntityStats statsProps = EntityStatsCapability.get(player);
		IQuestData questProps = QuestDataCapability.get(player);
		
		boolean styleFlag = statsProps.isSwordsman();
		boolean prevQuestFlag = questProps.hasCompletedQuest(ModQuests.SWORDSMAN_PROGRESSION_01.getQuestId());
		boolean sunsetFlag = player.world.isDaytime() && player.world.getSunBrightness(0) < 0.7; // "Bet you don't know why the sun sets red"
		
		if(!sunsetFlag)
		{
			String questGiverName = I18n.format("entity.mineminenomi.dojo_sensei");
			WyHelper.sendMsgToPlayer(player, I18n.format(ModI18n.QUEST_SWORDSMAN_PROGRESSION_02_SUNSET_START, questGiverName));
		}
				
		return styleFlag && prevQuestFlag && sunsetFlag;
	}

	@Override
	public boolean canComplete(PlayerEntity player)
	{
		return true;
	}
	
	@Override
	public double getMaxProgress()
	{
		return 12000;
	}

	@Override
	public Questline getQuestLine()
	{
		return ModQuestlines.SWORDSMAN_PROGRESSION;
	}

	@Override
	public void onTimePassEvent(PlayerEntity player)
	{
		long progress = player.world.getDayTime() - this.getExtraData().getLong("currentDays");
		if(this.getProgress() <= this.getMaxProgress())
			this.setProgress(progress);
	}

}
