package xyz.pixelatedw.mineminenomi.events;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteractSpecific;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.entities.mobs.quest.givers.IQuestGiver;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.screens.QuestChooseScreen;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.data.quest.IQuestData;
import xyz.pixelatedw.wypi.data.quest.QuestDataCapability;
import xyz.pixelatedw.wypi.network.WyNetwork;
import xyz.pixelatedw.wypi.network.packets.server.SSyncQuestDataPacket;

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
			
		WyNetwork.sendTo(new SSyncQuestDataPacket(questProps), (ServerPlayerEntity) player);		
	}
	
	@SubscribeEvent
	public static void onEntityInteract(EntityInteractSpecific event)
	{
		if(!(event.getTarget() instanceof IQuestGiver) || !event.getPlayer().world.isRemote)
			return;
		
		PlayerEntity player = event.getPlayer();
		IQuestData questProps = QuestDataCapability.get(player);
		IQuestGiver questGiver = (IQuestGiver) event.getTarget();
		boolean hasQuests = false;
		
		for (int i = 0; i <= questGiver.getAvailableQuests(player).length - 1; i++)
		{
			if(questProps.hasFinishedQuest(questGiver.getAvailableQuests(player)[i]))
				continue;
			
			hasQuests = true;
			break;
		}
		
		if(hasQuests)
			Minecraft.getInstance().displayGuiScreen(new QuestChooseScreen(player, event.getTarget(), questGiver.getAvailableQuests(player)));
		else
			WyHelper.sendMsgToPlayer(player, new TranslationTextComponent(ModI18n.QUEST_NO_QUESTS_AVAILABLE, event.getTarget().getDisplayName().getFormattedText()).getFormattedText());
	}
}
