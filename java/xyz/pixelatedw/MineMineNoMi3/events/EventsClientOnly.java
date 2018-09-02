package xyz.pixelatedw.MineMineNoMi3.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import xyz.pixelatedw.MineMineNoMi3.api.network.PacketQuestSync;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.api.quests.QuestProperties;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;
import xyz.pixelatedw.MineMineNoMi3.quests.ITimedQuest;

public class EventsClientOnly
{
	@SubscribeEvent
	public void onWorldTick(TickEvent.WorldTickEvent event)
	{
		if(event.phase == Phase.START)
		{
			EntityPlayer player = (EntityPlayer) Minecraft.getMinecraft().thePlayer;
			if(player != null)
			{
				ExtendedEntityStats props = ExtendedEntityStats.get(player);
				QuestProperties questProps = QuestProperties.get(player);
				
				if(questProps.questsInProgress() > 0)
				{
					for(int i = 0; i < questProps.questsInProgress(); i++)
					{
						if(questProps.getQuestIndexFromTracker(i) != null && questProps.getQuestIndexFromTracker(i) instanceof ITimedQuest)
						{
							((ITimedQuest)questProps.getQuestIndexFromTracker(i)).onTimePassEvent(player);
						}
					}
				}

				WyNetworkHelper.sendToServer(new PacketQuestSync(questProps));
			}
		}
	}
}
