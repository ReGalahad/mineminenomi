package xyz.pixelatedw.mineminenomi.quests.questlines.swordsman;

import java.util.Iterator;
import java.util.Map.Entry;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.quests.IInteractQuest;
import xyz.pixelatedw.mineminenomi.api.quests.IProgressionQuest;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
import xyz.pixelatedw.mineminenomi.api.quests.extra.Questline;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.entities.mobs.quest.givers.DojoSenseiEntity;
import xyz.pixelatedw.mineminenomi.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModQuestlines;

public class SwordsmanProgression01Quest extends Quest implements IProgressionQuest, IInteractQuest
{

	@Override
	public String getQuestId()
	{
		return "swordsman_progression_01";
	}

	@Override
	public String getQuestName()
	{
		return "Road to becoming the Best Swordsman";
	}

	@Override
	public String[] getQuestDescription()
	{
		return new String[]
			{
					"- Show your best sword to the Sensei", 
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
		WyHelper.sendMsgToPlayer(player, I18n.format(ModI18n.QUEST_SWORDSMAN_PROGRESSION_01_START, questGiverName));
	}
	
	@Override
	public boolean canStart(PlayerEntity player)
	{
		IEntityStats statsProps = EntityStatsCapability.get(player);

		boolean styleFlag = statsProps.isSwordsman();

		return styleFlag;
	}

	@Override
	public boolean canComplete(PlayerEntity player)
	{
		return true;
	}

	@Override
	public boolean isTarget(PlayerEntity player, LivingEntity target)
	{
		ItemStack heldItem = player.getHeldItemMainhand();

		if(!(target instanceof DojoSenseiEntity))
			return false;

		if (heldItem == null)
			return false;

		if (!ItemsHelper.isSword(heldItem))
			return false;

		Iterator iterator = heldItem.getAttributeModifiers(EquipmentSlotType.MAINHAND).entries().iterator();

		while (iterator.hasNext())
		{
			Entry entry = (Entry) iterator.next();

			if (entry.getKey().equals(SharedMonsterAttributes.ATTACK_DAMAGE.getName()))
			{
				AttributeModifier attrmodif = (AttributeModifier) entry.getValue();
				double damage = attrmodif.getAmount();

				if (damage >= 7)
				{
					String questGiverName = I18n.format("entity.mineminenomi.dojo_sensei");
					WyHelper.sendMsgToPlayer(player, I18n.format(ModI18n.QUEST_SWORDSMAN_PROGRESSION_01_STRONG_SWORD, questGiverName));
					return true;
				}
				else
				{
					String questGiverName = I18n.format("entity.mineminenomi.dojo_sensei");
					WyHelper.sendMsgToPlayer(player, I18n.format(ModI18n.QUEST_SWORDSMAN_PROGRESSION_01_WEAK_SWORD, questGiverName));
					return false;
				}
			}
		}

		return false;
	}

	@Override
	public Questline getQuestLine()
	{
		return ModQuestlines.SWORDSMAN_PROGRESSION;
	}

}
