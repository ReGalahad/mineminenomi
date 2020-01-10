package xyz.pixelatedw.mineminenomi.events;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedOutEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.Env;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.api.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.api.data.quest.IQuestData;
import xyz.pixelatedw.mineminenomi.api.data.quest.QuestDataCapability;
import xyz.pixelatedw.mineminenomi.api.debug.WyDebug;
import xyz.pixelatedw.mineminenomi.api.telemetry.WyTelemetry;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.events.custom.YomiTriggerEvent;

@Mod.EventBusSubscriber(modid = Env.PROJECT_ID)
public class EventsCore
{
	// Cloning the player data to the new entity based on the config option
	@SubscribeEvent
	public static void onClonePlayer(PlayerEvent.Clone event) 
	{
		if(event.isWasDeath()) 
		{
			IDevilFruit oldPlayerProps = DevilFruitCapability.get(event.getOriginal());	
			IDevilFruit newPlayerProps = DevilFruitCapability.get(event.getPlayer());
			
			if(CommonConfig.instance.getAfterDeathLogic() == CommonConfig.KeepStatsLogic.FULL)
			{
				INBT nbt = new CompoundNBT();
				
				// Keep the entity stats
				IEntityStats oldEntityStats = EntityStatsCapability.get(event.getOriginal());
				oldEntityStats.setCola(oldEntityStats.getMaxCola());
				nbt = EntityStatsCapability.INSTANCE.writeNBT(oldEntityStats, null);
				IEntityStats newEntityStats = EntityStatsCapability.get(event.getPlayer());
				EntityStatsCapability.INSTANCE.readNBT(newEntityStats, null, nbt);
				
				// Keep the DF stats
				oldPlayerProps.setZoanPoint("");
				nbt = DevilFruitCapability.INSTANCE.writeNBT(oldPlayerProps, null);
				DevilFruitCapability.INSTANCE.readNBT(newPlayerProps, null, nbt);
				
				// Keep the abilities
				IAbilityData oldAbilityData = AbilityDataCapability.get(event.getOriginal());
				nbt = AbilityDataCapability.INSTANCE.writeNBT(oldAbilityData, null);
				IAbilityData newAbilityData = AbilityDataCapability.get(event.getPlayer());
				AbilityDataCapability.INSTANCE.readNBT(newAbilityData, null, nbt);
			}
			else if(CommonConfig.instance.getAfterDeathLogic() == CommonConfig.KeepStatsLogic.AUTO)
			{
				IEntityStats oldEntityStats = EntityStatsCapability.get(event.getOriginal());

				String faction = oldEntityStats.getFaction();
				String race = oldEntityStats.getRace();
				String fightStyle = oldEntityStats.getFightingStyle();
				//String crew = oldEntityStats.getCrew();
				int doriki = oldEntityStats.getDoriki() / 3;
				
				IEntityStats newEntityStats = EntityStatsCapability.get(event.getPlayer());
				newEntityStats.setFaction(faction);
				newEntityStats.setRace(race);
				newEntityStats.setFightingStyle(fightStyle);
				newEntityStats.setMaxCola(100);
				newEntityStats.setCola(oldEntityStats.getMaxCola());
				newEntityStats.setDoriki(doriki);
			}
			else if(CommonConfig.instance.getAfterDeathLogic() == CommonConfig.KeepStatsLogic.CUSTOM)
			{
				IEntityStats oldEntityStats = EntityStatsCapability.get(event.getOriginal());
				IEntityStats newEntityStats = EntityStatsCapability.get(event.getPlayer());

				IDevilFruit oldDevilFruit = DevilFruitCapability.get(event.getOriginal());
				IDevilFruit newDevilFruit = DevilFruitCapability.get(event.getPlayer());
				
				for(String stat : CommonConfig.instance.getStatsToKeep())
				{
					switch(WyHelper.getResourceName(stat))
					{
						case "doriki":
							newEntityStats.setDoriki(oldEntityStats.getDoriki()); break;
						case "bounty":
							newEntityStats.setBounty(oldEntityStats.getBounty()); break;
						case "belly":
							newEntityStats.setBelly(oldEntityStats.getBelly()); break;
						case "race":
							newEntityStats.setRace(oldEntityStats.getRace()); break;
						case "faction":
							newEntityStats.setFaction(oldEntityStats.getFaction()); break;
						case "fightingstyle":
							newEntityStats.setFightingStyle(oldEntityStats.getFightingStyle()); break;
						case "devilfruit":
							newDevilFruit.setDevilFruit(oldDevilFruit.getDevilFruit()); break;
					}
				}			
			}
		
			// Keep the quests no matter the config
			IQuestData oldQuestData = QuestDataCapability.get(event.getOriginal());
			CompoundNBT nbt = (CompoundNBT) QuestDataCapability.INSTANCE.writeNBT(oldQuestData, null);
			IQuestData newQuestData = QuestDataCapability.get(event.getPlayer());
			QuestDataCapability.INSTANCE.readNBT(newQuestData, null, nbt);
			
			YomiTriggerEvent yomiEvent = new YomiTriggerEvent(event.getPlayer(), oldPlayerProps, newPlayerProps);
			if (MinecraftForge.EVENT_BUS.post(yomiEvent))
				return;
		}
	}
	
	// Protection code and the update notification message
	@SubscribeEvent
	public static void onEntityJoinWorld(EntityJoinWorldEvent event)
	{
		if (event.getEntity() instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity) event.getEntity();

			if (!player.world.isRemote)
			{
				if(!WyHelper.isReleaseBuild())
				{
					if(!WyHelper.hasPatreonAccess(player))
					{ 
						((ServerPlayerEntity)player).connection.disconnect(new StringTextComponent(TextFormatting.BOLD + "" + TextFormatting.RED + "WARNING! \n\n " + TextFormatting.RESET + "You don't have access to this version yet!"));
						if(!WyDebug.isDebug())
						{
							WyTelemetry.addMiscStat("onlinePlayers", "Online Players", -1);
							WyTelemetry.sendAllDataSync();
						}
						event.setCanceled(true);
						return;
					}
				}
				
				if(CommonConfig.instance.isUpdateMessageEnabled())
				{
					try 
					{
						String[] version = Env.PROJECT_VERSION.replaceAll("[^0-9.]", "").split("\\.");
						
						int currentX = Integer.parseInt(version[0]) * 100;
						int currentY = Integer.parseInt(version[1]) * 10;
						int currentZ = Integer.parseInt(version[2]);
						
						int currentVersion = currentX + currentY + currentZ;
						
						String apiURL = "/version?minecraft-version=" + Env.PROJECT_MCVERSION;
						
						String result = WyTelemetry.sendGET(apiURL);
						
						if(!WyHelper.isNullOrEmpty(result))
						{
							String[] resultVersion = result.replaceAll("[^0-9.]", "").split("\\.");
							
							int latestX = Integer.parseInt(resultVersion[0]) * 100;
							int latestY = Integer.parseInt(resultVersion[1]) * 10;
							int latestZ = Integer.parseInt(resultVersion[2]);
							
							int latestVersion = latestX + latestY + latestZ;

							if(latestVersion > currentVersion)
							{
								player.sendMessage(new StringTextComponent(TextFormatting.RED + "" + TextFormatting.BOLD + "[UPDATE]" + TextFormatting.RED + " Mine Mine no Mi " + result + " is now available !").applyTextStyle((style) -> { style.setColor(TextFormatting.GOLD).setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "http://pixelatedw.xyz/versions")); }) );
								player.sendMessage(new StringTextComponent(TextFormatting.RED + "Download it from the official website : [http://pixelatedw.xyz/versions]").applyTextStyle((style) -> { style.setColor(TextFormatting.GOLD).setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "http://pixelatedw.xyz/versions")); })  );
							}
						}
					}
					catch(Exception e)
					{
						System.out.println("Connection failed !");
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void onPlayerLoggedIn(PlayerLoggedInEvent event)
	{
		/*if(!WyDebug.isDebug())
		{
			WyTelemetry.addMiscStat("onlinePlayers", "Online Players", 1);
			WyTelemetry.sendAllDataAsync();
		}*/
	}
	
	@SubscribeEvent
	public static void onPlayerLoggedIn(PlayerLoggedOutEvent event)
	{
	/*	if(!WyDebug.isDebug())
		{
			WyTelemetry.addMiscStat("onlinePlayers", "Online Players", -1);
			WyTelemetry.sendAllDataSync();
		}*/
	}
	
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.WorldTickEvent event)
	{		
		/*if(event.phase == Phase.END && event.side == LogicalSide.SERVER)
		{
			if(event.world.getGameTime() % 1200 == 0)
			{
			//	WyTelemetry.sendAllDataAsync();
			}
		}*/
	}
}
