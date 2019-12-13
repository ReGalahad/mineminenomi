package xyz.pixelatedw.mineminenomi.quests.questlines.swordsman;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.abilities.SwordsmanAbilities;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.IAbilityData;
import xyz.pixelatedw.mineminenomi.api.data.questdata.IQuestData;
import xyz.pixelatedw.mineminenomi.api.data.questdata.QuestDataCapability;
import xyz.pixelatedw.mineminenomi.api.quests.IHitCounterQuest;
import xyz.pixelatedw.mineminenomi.api.quests.IProgressionQuest;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
import xyz.pixelatedw.mineminenomi.api.quests.extra.Questline;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModQuestlines;
import xyz.pixelatedw.mineminenomi.init.ModQuests;

public class SwordsmanProgression04Quest extends Quest implements IProgressionQuest, IHitCounterQuest
{

	@Override
	public String getQuestId()
	{
		return "swordsman_progression_04";
	}

	@Override
	public String getQuestName()
	{
		return "The Fruits of Training";
	}

	@Override
	public String[] getQuestDescription()
	{
		return new String[]
			{
					"- Perform 25 critical hits",
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
		String questGiverName = I18n.format("entity.mineminenomi.dojo_sensei");
		WyHelper.sendMsgToPlayer(player, I18n.format(ModI18n.QUEST_SWORDSMAN_PROGRESSION_04_START, questGiverName));
	}
	
	@Override
	public boolean canStart(PlayerEntity player)
	{
		IEntityStats statsProps = EntityStatsCapability.get(player);
		IQuestData questProps = QuestDataCapability.get(player);
		
		boolean styleFlag = statsProps.isSwordsman();
		boolean prevQuestFlag = questProps.hasCompletedQuest(ModQuests.SWORDSMAN_PROGRESSION_03.getQuestId());

		return styleFlag && prevQuestFlag;
	}

	@Override
	public void complete(PlayerEntity player)
	{
		super.complete(player);
		
		IAbilityData abilityData = AbilityDataCapability.get(player);
		
		String questGiverName = I18n.format("entity.mineminenomi.dojo_sensei");
		WyHelper.sendMsgToPlayer(player, I18n.format(ModI18n.QUEST_SWORDSMAN_PROGRESSION_03_COMPLETE, questGiverName));
		
		if(CommonConfig.instance.isQuestProgressionEnabled())
			abilityData.addRacialAbility(SwordsmanAbilities.SANBYAKUROKUJU_POUND_HO);
	}
	
	@Override
	public boolean canComplete(PlayerEntity player)
	{
		return true;
	}
	
	@Override
	public double getMaxProgress()
	{
		return 25;
	}

	@Override
	public Questline getQuestLine()
	{
		return ModQuestlines.SWORDSMAN_PROGRESSION;
	}

	@Override
	public boolean checkHit(PlayerEntity player, LivingEntity target, DamageSource source)
	{
		ItemStack heldItem = player.getHeldItemMainhand();
		
		boolean criticalFlag = player.fallDistance > 0.0F && !player.onGround && !player.isOnLadder() && !player.isInWater() && !player.isPassenger();
		boolean swordFlag = ItemsHelper.isSword(heldItem);
				
		return criticalFlag && swordFlag;
	}

}
