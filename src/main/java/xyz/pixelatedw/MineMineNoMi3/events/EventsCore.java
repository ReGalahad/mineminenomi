package xyz.pixelatedw.MineMineNoMi3.events;

import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;

import com.google.gson.internal.LinkedTreeMap;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.ClickEvent;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.MainConfig;
import xyz.pixelatedw.MineMineNoMi3.Values;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.extra.AbilityProperties;
import xyz.pixelatedw.MineMineNoMi3.api.debug.WyDebug;
import xyz.pixelatedw.MineMineNoMi3.api.quests.QuestProperties;
import xyz.pixelatedw.MineMineNoMi3.api.telemetry.WyTelemetry;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedEntityData;
import xyz.pixelatedw.MineMineNoMi3.events.customevents.YomiTriggerEvent;

public class EventsCore
{
	
	// Registering the extended properties
	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event) 
	{
		if (event.entity instanceof EntityLivingBase && ExtendedEntityData.get((EntityLivingBase) event.entity) == null)
			ExtendedEntityData.register((EntityLivingBase) event.entity);

		if (event.entity instanceof EntityPlayer)
		{
			if(QuestProperties.get((EntityPlayer) event.entity) == null)
				QuestProperties.register((EntityPlayer) event.entity);
			if(AbilityProperties.get((EntityPlayer) event.entity) == null)
				AbilityProperties.register((EntityPlayer) event.entity);
		}
	}
	
	// Cloning the player data to the new entity based on the config option
	@SubscribeEvent
	public void onClonePlayer(PlayerEvent.Clone e) 
	{
		if(e.wasDeath) 
		{
			ExtendedEntityData oldPlayerProps = ExtendedEntityData.get(e.original);	
			ExtendedEntityData newPlayerProps = ExtendedEntityData.get(e.entityPlayer);
			
			//WyNetworkHelper.sendTo(new PacketNewAABB(0.6F, 1.8F), (EntityPlayerMP) e.entityPlayer);
			
			if(MainConfig.enableKeepIEEPAfterDeath.equals("full"))
			{
				NBTTagCompound compound = new NBTTagCompound();
				
				ExtendedEntityData oldProps = ExtendedEntityData.get(e.original);
				oldProps.saveNBTData(compound);
				oldProps.triggerActiveHaki(false);
				oldProps.triggerBusoHaki(false);
				oldProps.triggerKenHaki(false);
				oldProps.setGear(1);
				oldProps.setZoanPoint("n/a");
				ExtendedEntityData props = ExtendedEntityData.get(e.entityPlayer);
				props.loadNBTData(compound);				
				
				compound = new NBTTagCompound();
				AbilityProperties.get(e.original).saveNBTData(compound);
				AbilityProperties abilityProps = AbilityProperties.get(e.entityPlayer);
				abilityProps.loadNBTData(compound);
				
				if(e.entityPlayer != null && MainConfig.enableExtraHearts)		
				{
					IAttributeInstance maxHp = e.entityPlayer.getEntityAttribute(SharedMonsterAttributes.maxHealth);
								
					if(props.getDoriki() / 100 <= 20)
						e.entityPlayer.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20);
					else
						e.entityPlayer.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(props.getDoriki() / 100);
				}
			}
			else if(MainConfig.enableKeepIEEPAfterDeath.equals("auto"))
			{
				ExtendedEntityData oldProps = ExtendedEntityData.get(e.original);
				
				String faction = oldProps.getFaction();
				String race = oldProps.getRace();
				String fightStyle = oldProps.getFightStyle();
				String crew = oldProps.getCrew();
				int doriki = oldProps.getDoriki() / 3;

				ExtendedEntityData props = ExtendedEntityData.get(e.entityPlayer);
				props.setFaction(faction);
				props.setRace(race);
				props.setFightStyle(fightStyle);
				props.setCrew(crew);			
				props.setMaxCola(100);
				props.setCola(oldProps.getMaxCola());
				props.setDoriki(doriki);
			}
			else if(MainConfig.enableKeepIEEPAfterDeath.equals("custom"))
			{
				ExtendedEntityData oldProps = ExtendedEntityData.get(e.original);
				ExtendedEntityData props = ExtendedEntityData.get(e.entityPlayer);

				for(String stat : MainConfig.statsToKeep)
				{
					switch(WyHelper.getFancyName(stat))
					{
						case "doriki":
							props.setDoriki(oldProps.getDoriki()); break;
						case "bounty":
							props.setBounty(oldProps.getBounty()); break;
						case "belly":
							props.setBelly(oldProps.getBelly()); break;
						case "race":
							props.setRace(oldProps.getRace()); break;
						case "faction":
							props.setFaction(oldProps.getFaction()); break;
						case "fightingstyle":
							props.setFightStyle(oldProps.getFightStyle()); break;
						case "devilfruit":
							props.setUsedFruit(oldProps.getUsedFruit()); break;
					}
				}
			}
					
			NBTTagCompound compound = new NBTTagCompound();
			QuestProperties.get(e.original).saveNBTData(compound);
			QuestProperties questProps = QuestProperties.get(e.entityPlayer);
			questProps.loadNBTData(compound);
			
			YomiTriggerEvent yomiEvent = new YomiTriggerEvent(e.entityPlayer, oldPlayerProps, newPlayerProps);
			if (MinecraftForge.EVENT_BUS.post(yomiEvent))
				return;
		}
	}
	
	// Protection code and the update notification message
	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent event)
	{
		if (event.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.entity;

			if (!player.worldObj.isRemote)
			{
				if(ID.DEV_EARLYACCESS && !WyDebug.isDebug())
					WyHelper.isPatreon(player);
				
				if(MainConfig.enableUpdateMsg)
				{
					try 
					{
						String[] version = ID.PROJECT_VERSION.split("\\.");
	
						int x = Integer.parseInt(version[0]) * 100;
						int y = Integer.parseInt(version[1]) * 10;
						int z = Integer.parseInt(version[2]);
						
						int versionCode = x + y + z;
						
						String url = "/getNewestVersion";
						String json = Values.gson.toJson(ID.PROJECT_MCVERSION);
						
						HttpPost post = new HttpPost(Values.urlConnection + "" + url);	
						StringEntity postingString;
						postingString = new StringEntity(json);
						post.setEntity(postingString);
						post.setHeader("Content-Type", "application/json");
						
						HttpResponse response = Values.httpClient.execute(post);
						ResponseHandler<String> handler = new BasicResponseHandler();
						String body = handler.handleResponse(response);
						
						if(!body.isEmpty())
						{
							LinkedTreeMap result = Values.gson.fromJson(body, LinkedTreeMap.class);
							int highestVersion = ((Double) result.get("highestVersionCode")).intValue();
							String highestName = (String) result.get("highestVersionName");
							
							if(highestVersion > versionCode)
							{
								ChatStyle updateStyle = new ChatStyle().setColor(EnumChatFormatting.GOLD).setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "http://pixelatedw.xyz/versions"));
								
								player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.RED + "" + EnumChatFormatting.BOLD + "[UPDATE]" + EnumChatFormatting.RED + " Mine Mine no Mi " + highestName + " is now available !").setChatStyle(updateStyle) );
								player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.RED + "Download it from the official website : [http://pixelatedw.xyz/versions]").setChatStyle(updateStyle) );
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
	public void onPlayerTick(TickEvent.WorldTickEvent event)
	{		
		if(event.phase == Phase.END && event.side == Side.SERVER)
		{
			if(event.world.getWorldTime() % 12000 == 0)
			{
				WyTelemetry.sendAllData();
			}
		}
	}
}
