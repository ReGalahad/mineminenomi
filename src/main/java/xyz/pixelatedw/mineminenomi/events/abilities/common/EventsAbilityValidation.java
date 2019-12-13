package xyz.pixelatedw.mineminenomi.events.abilities.common;

import java.util.Arrays;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import xyz.pixelatedw.mineminenomi.abilities.YomiAbilities;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.IAbilityData;
import xyz.pixelatedw.mineminenomi.api.network.packets.server.SAbilityDataSyncPacket;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.helpers.DevilFruitsHelper;
import xyz.pixelatedw.mineminenomi.init.ModItems;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
import xyz.pixelatedw.mineminenomi.packets.server.SDevilFruitSyncPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SEntityStatsSyncPacket;

public class EventsAbilityValidation
{
	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent event)
	{
		if (event.getEntity() instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity) event.getEntity();
			IEntityStats entityStatsProps = EntityStatsCapability.get(player);
			IDevilFruit devilFruitProps = DevilFruitCapability.get(player);
			//QuestProperties questProps = QuestProperties.get(player);
			IAbilityData abilityProps = AbilityDataCapability.get(player);
			
			if (!player.world.isRemote)
			{			
				if (!entityStatsProps.hasRace() && !entityStatsProps.hasFaction() && !entityStatsProps.hasFightingStyle() && !player.inventory.hasItemStack(new ItemStack(ModItems.characterCreator)))
					player.inventory.addItemStackToInventory(new ItemStack(ModItems.characterCreator, 1));
				
				if(!WyHelper.isNullOrEmpty(devilFruitProps.getDevilFruit()))
				{					
					ItemStack df = DevilFruitsHelper.getDevilFruitItem(devilFruitProps.getDevilFruit());
					
					abilityProps.clearDevilFruitAbilities();
					if(!devilFruitProps.getZoanPoint().equalsIgnoreCase("yomi"))
						devilFruitProps.setZoanPoint("");
					
					if(df != null && df.getItem() != null)
					{
						if(devilFruitProps.hasYamiPower())
						{
							ItemStack yami = DevilFruitsHelper.getDevilFruitItem("yamiyami");
							for(Ability a : ((AkumaNoMiItem)yami.getItem()).abilities)
								if(!DevilFruitsHelper.verifyIfAbilityIsBanned(a))
									abilityProps.addDevilFruitAbility(a);
						}
						for(Ability a : ((AkumaNoMiItem)df.getItem()).abilities)
							if(!DevilFruitsHelper.verifyIfAbilityIsBanned(a))
								abilityProps.addDevilFruitAbility(a);
					}
					
					for(Ability a : abilityProps.getAbilitiesInHotbar())
						if(a != null && a.isOnCooldown())
							a.startUpdate(player);
				}
				
				DevilFruitsHelper.validateRacialMoves(player);
				DevilFruitsHelper.validateStyleMoves(player);
				
				for(Ability a : abilityProps.getDevilFruitAbilities())
				{
					if(Arrays.asList(YomiAbilities.abilitiesArray).contains(a))
					{
						if(!devilFruitProps.getZoanPoint().equalsIgnoreCase("yomi"))
							abilityProps.removeDevilFruitAbility(a);
					}
				}
				
				for(int i = 0; i < abilityProps.countAbilitiesInHotbar(); i++)
				{
					if(abilityProps.getHotbarAbilityFromSlot(i) != null)
					{
						if(DevilFruitsHelper.verifyIfAbilityIsBanned(abilityProps.getHotbarAbilityFromSlot(i)))
							abilityProps.setAbilityInSlot(i, null);
					}
				}			
				
				ModNetwork.sendTo(new SEntityStatsSyncPacket(player.getEntityId(), entityStatsProps), (ServerPlayerEntity) player);
				ModNetwork.sendTo(new SDevilFruitSyncPacket(player.getEntityId(), devilFruitProps), (ServerPlayerEntity) player);
				//ModNetwork.sendTo(new PacketQuestSync(questProps), (ServerPlayerEntity) player);
				ModNetwork.sendTo(new SAbilityDataSyncPacket(player.getEntityId(), abilityProps), (ServerPlayerEntity) player);		
			}		
		}
	}
}
