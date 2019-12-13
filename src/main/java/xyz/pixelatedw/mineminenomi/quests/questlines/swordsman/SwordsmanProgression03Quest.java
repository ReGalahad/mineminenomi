package xyz.pixelatedw.mineminenomi.quests.questlines.swordsman;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.Biome;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.data.questdata.IQuestData;
import xyz.pixelatedw.mineminenomi.api.data.questdata.QuestDataCapability;
import xyz.pixelatedw.mineminenomi.api.quests.IKillQuest;
import xyz.pixelatedw.mineminenomi.api.quests.IProgressionQuest;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
import xyz.pixelatedw.mineminenomi.api.quests.extra.Questline;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModQuestlines;
import xyz.pixelatedw.mineminenomi.init.ModQuests;

public class SwordsmanProgression03Quest extends Quest implements IProgressionQuest, IKillQuest
{

	@Override
	public String getQuestId()
	{
		return "swordsman_progression_03";
	}

	@Override
	public String getQuestName()
	{
		return "Feel the Wind";
	}

	@Override
	public String[] getQuestDescription()
	{
		return new String[]
			{
					"- Kill 20 creatures or humans in the mountains",
					"using your sword",
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
		String questGiverName = I18n.format("entity.mineminenomi.dojo_sensei");
		WyHelper.sendMsgToPlayer(player, I18n.format(ModI18n.QUEST_SWORDSMAN_PROGRESSION_03_START, questGiverName));
	}
	
	@Override
	public boolean canStart(PlayerEntity player)
	{
		IEntityStats statsProps = EntityStatsCapability.get(player);
		IQuestData questProps = QuestDataCapability.get(player);
		
		boolean styleFlag = statsProps.isSwordsman();
		boolean prevQuestFlag = questProps.hasCompletedQuest(ModQuests.SWORDSMAN_PROGRESSION_02.getQuestId());

		return styleFlag && prevQuestFlag;
	}

	@Override
	public void complete(PlayerEntity player)
	{
		super.complete(player);
		String questGiverName = I18n.format("entity.mineminenomi.dojo_sensei");
		WyHelper.sendMsgToPlayer(player, I18n.format(ModI18n.QUEST_SWORDSMAN_PROGRESSION_03_COMPLETE, questGiverName));
	}
	
	@Override
	public boolean canComplete(PlayerEntity player)
	{
		return true;
	}
	
	@Override
	public double getMaxProgress()
	{
		return 20;
	}

	@Override
	public Questline getQuestLine()
	{
		return ModQuestlines.SWORDSMAN_PROGRESSION;
	}

	@Override
	public boolean isTarget(PlayerEntity player, LivingEntity target)
	{
		Biome biome = player.world.getBiome(player.getPosition());
		ItemStack heldItem = player.getHeldItemMainhand();
		
		boolean biomeFlag = biome.getDepth() > 0.3F;
		boolean enemyFlag = target instanceof MobEntity;
		boolean swordFlag = ItemsHelper.isSword(heldItem);
				
		return biomeFlag && enemyFlag && swordFlag;
	}

}
