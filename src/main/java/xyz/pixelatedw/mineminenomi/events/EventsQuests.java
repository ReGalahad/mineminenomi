package xyz.pixelatedw.mineminenomi.events;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.Env;
import xyz.pixelatedw.mineminenomi.api.data.quest.IQuestData;
import xyz.pixelatedw.mineminenomi.api.data.quest.QuestDataCapability;
import xyz.pixelatedw.mineminenomi.api.network.packets.server.SQuestDataSyncPacket;
import xyz.pixelatedw.mineminenomi.api.quests.IHitCounterQuest;
import xyz.pixelatedw.mineminenomi.api.quests.IInteractQuest;
import xyz.pixelatedw.mineminenomi.api.quests.IKillQuest;
import xyz.pixelatedw.mineminenomi.api.quests.ITimedQuest;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
import xyz.pixelatedw.mineminenomi.api.quests.extra.Questline;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.entities.mobs.quest.givers.IQuestGiver;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;

@Mod.EventBusSubscriber(modid = Env.PROJECT_ID)
public class EventsQuests
{

	@SubscribeEvent
	public static void onEntityUpdate(LivingUpdateEvent event)
	{
		if (event.getEntityLiving() instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity) event.getEntityLiving();
			IQuestData questProps = QuestDataCapability.get(player);
			ItemStack heldItem = player.getHeldItemMainhand();

			for (Quest quest : questProps.getInProgressQuests())
			{
				if (quest instanceof ITimedQuest)
					((ITimedQuest) quest).onTimePassEvent(player);
			}
		}
	}

	@SubscribeEvent
	public static void onEntityInteract(PlayerInteractEvent.EntityInteract event)
	{
		PlayerEntity player = event.getPlayer();
		IQuestData questProps = QuestDataCapability.get(player);
		LivingEntity target = null;
		if (event.getTarget() instanceof LivingEntity)
			target = (LivingEntity) event.getTarget();

		if (event.getSide() == LogicalSide.CLIENT || event.getHand() == Hand.OFF_HAND)
			return;

		if (target != null && CommonConfig.instance.isQuestsEnabled())
		{
			// General logic for progressing through 'interact' activities
			if (questProps.getInProgressQuests().size() > 0)
			{
				for (Quest quest : questProps.getInProgressQuests())
				{
					if (quest instanceof IInteractQuest && ((IInteractQuest) quest).isTarget(player, target))
					{
						quest.alterProgress(1);
						questProps.completeQuest(player, quest);
						return;
					}
				}
			}

			// A general solution for interacting with a quest giver and receive either the current quest in the questline or opening a UI with available quests
			if (target instanceof IQuestGiver)
			{
				Questline questline = ((IQuestGiver) target).getQuestline();

				// Turning in every quest based on the given questline
				if (questProps.getInProgressQuests().size() > 0)
				{
					int completedQuests = 0;
					for (Quest quest : questProps.getInProgressQuests())
					{
						if (questProps.isPartofQuestline(quest, questline) && questProps.completeQuest(player, quest))
						{
							completedQuests++;
							break;
						}
					}

					// Stop quests from being accepted at the same time as other quests are completed
					if (completedQuests > 0)
					{
						ModNetwork.sendTo(new SQuestDataSyncPacket(player.getEntityId(), questProps), (ServerPlayerEntity) player);
						return;
					}
				}

				// Starting the next quest in the questline
				Quest currentQuest = questProps.getQuestlineCurrentQuest(questline);

				if (currentQuest != null && !questProps.hasCompletedQuest(currentQuest.getQuestId()) && !questProps.hasInProgressQuest(currentQuest))
				{
					//ModMain.proxy.openQuestAcceptScreen(player, currentQuest);
					return;
				}
			}
		}
	}

	@SubscribeEvent
	public static void onEntityDeath(LivingDeathEvent event)
	{
		if (event.getSource().getTrueSource() instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity) event.getSource().getTrueSource();
			IQuestData questProps = QuestDataCapability.get(player);
			LivingEntity target = event.getEntityLiving();

			// General logic for progressing through 'kill' activities
			boolean flag = false;
			for (Quest quest : questProps.getInProgressQuests())
			{
				if (quest instanceof IKillQuest && ((IKillQuest) quest).isTarget(player, target))
				{
					quest.alterProgress(1);
					flag = true;
				}
			}

			if(flag)
				ModNetwork.sendTo(new SQuestDataSyncPacket(player.getEntityId(), questProps), (ServerPlayerEntity) player);
		}
	}

	@SubscribeEvent
	public static void onEntityAttackEvent(LivingHurtEvent event)
	{
		if (event.getSource().getTrueSource() instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity) event.getSource().getTrueSource();
			IQuestData questProps = QuestDataCapability.get(player);
			LivingEntity target = event.getEntityLiving();

			// General logic for progressing through 'hit counter' activities
			boolean flag = false;
			for (Quest quest : questProps.getInProgressQuests())
			{
				if (quest instanceof IHitCounterQuest && ((IHitCounterQuest) quest).checkHit(player, target, event.getSource()))
				{
					quest.alterProgress(1);
					flag = true;
				}
			}

			if(flag)
				ModNetwork.sendTo(new SQuestDataSyncPacket(player.getEntityId(), questProps), (ServerPlayerEntity) player);
		}
	}
}
