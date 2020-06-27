package xyz.pixelatedw.mineminenomi.events;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteractSpecific;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.entities.mobs.quest.givers.IQuestGiver;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.packets.server.SOpenQuestChooseScreenPacket;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.data.quest.IQuestData;
import xyz.pixelatedw.wypi.data.quest.QuestDataCapability;
import xyz.pixelatedw.wypi.network.WyNetwork;
import xyz.pixelatedw.wypi.network.packets.server.SSyncQuestDataPacket;
import xyz.pixelatedw.wypi.quests.Quest;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class QuestEvents
{
	@SubscribeEvent
	public static void onEntityJoinWorld(EntityJoinWorldEvent event)
	{
		if (!(event.getEntity() instanceof PlayerEntity))
			return;
		
		PlayerEntity player = (PlayerEntity) event.getEntity();
		IQuestData questProps = QuestDataCapability.get(player);
		
		if (player.world.isRemote)
			return;
			
		WyNetwork.sendTo(new SSyncQuestDataPacket(player.getEntityId(), questProps), (ServerPlayerEntity) player);		
	}
	
	@SubscribeEvent
	public static void onEntityInteract(EntityInteractSpecific event)
	{
		if(!(event.getTarget() instanceof IQuestGiver))
			return;		
		
		PlayerEntity player = event.getPlayer();
		IQuestData questProps = QuestDataCapability.get(player);
		IQuestGiver questGiver = (IQuestGiver) event.getTarget();
		boolean hasQuests = false;
		boolean hasReset = false;
		
		for(Quest quest : questProps.getInProgressQuests())
		{
			if(quest != null && quest.checkRestart(player))
			{
				hasReset = true;
				break;
			}
		}
		
		for (int i = 0; i <= questGiver.getAvailableQuests(player).length - 1; i++)
		{
			Quest quest = questGiver.getAvailableQuests(player)[i];			
			if(!questProps.hasFinishedQuest(quest))
			{
				hasQuests = true;
				break;
			}
		}

		if(!player.world.isRemote && !hasReset)
		{
			if(hasQuests)
				WyNetwork.sendTo(new SOpenQuestChooseScreenPacket(event.getTarget().getEntityId()), player);
			else
				WyHelper.sendMsgToPlayer(player, new TranslationTextComponent(ModI18n.QUEST_NO_TRIALS_AVAILABLE, event.getTarget().getDisplayName().getFormattedText()).getFormattedText());
		}
	}
}
