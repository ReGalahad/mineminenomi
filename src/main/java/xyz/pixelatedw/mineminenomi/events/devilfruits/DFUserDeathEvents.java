package xyz.pixelatedw.mineminenomi.events.devilfruits;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.events.custom.YomiTriggerEvent;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class DFUserDeathEvents {
	// Cloning the player data to the new entity based on the config option
	@SubscribeEvent
	public static void onClonePlayer(PlayerEvent.Clone event) {
		if (event.isWasDeath()) {
			IDevilFruit oldPlayerProps = DevilFruitCapability.get(event.getOriginal());
			IDevilFruit newPlayerProps = DevilFruitCapability.get(event.getPlayer());

			if (CommonConfig.instance.getAfterDeathLogic() == CommonConfig.KeepStatsLogic.FULL) {
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
			} else if (CommonConfig.instance.getAfterDeathLogic() == CommonConfig.KeepStatsLogic.AUTO) {
				IEntityStats oldEntityStats = EntityStatsCapability.get(event.getOriginal());

				String faction = oldEntityStats.getFaction();
				String race = oldEntityStats.getRace();
				String fightStyle = oldEntityStats.getFightingStyle();
				// String crew = oldEntityStats.getCrew();
				int doriki = oldEntityStats.getDoriki() / 3;

				IEntityStats newEntityStats = EntityStatsCapability.get(event.getPlayer());
				newEntityStats.setFaction(faction);
				newEntityStats.setRace(race);
				newEntityStats.setFightingStyle(fightStyle);
				newEntityStats.setMaxCola(100);
				newEntityStats.setCola(oldEntityStats.getMaxCola());
				newEntityStats.setDoriki(doriki);
			} else if (CommonConfig.instance.getAfterDeathLogic() == CommonConfig.KeepStatsLogic.CUSTOM) {
				IEntityStats oldEntityStats = EntityStatsCapability.get(event.getOriginal());
				IEntityStats newEntityStats = EntityStatsCapability.get(event.getPlayer());

				IDevilFruit oldDevilFruit = DevilFruitCapability.get(event.getOriginal());
				IDevilFruit newDevilFruit = DevilFruitCapability.get(event.getPlayer());

				for (String stat : CommonConfig.instance.getStatsToKeep()) {
					switch (WyHelper.getResourceName(stat)) {
					case "doriki":
						newEntityStats.setDoriki(oldEntityStats.getDoriki());
						break;
					case "bounty":
						newEntityStats.setBounty(oldEntityStats.getBounty());
						break;
					case "belly":
						newEntityStats.setBelly(oldEntityStats.getBelly());
						break;
					case "race":
						newEntityStats.setRace(oldEntityStats.getRace());
						break;
					case "faction":
						newEntityStats.setFaction(oldEntityStats.getFaction());
						break;
					case "fightingstyle":
						newEntityStats.setFightingStyle(oldEntityStats.getFightingStyle());
						break;
					case "devilfruit":
						newDevilFruit.setDevilFruit(oldDevilFruit.getDevilFruit());
						break;
					}
				}
			}

			// Keep the quests no matter the config
			// IQuestData oldQuestData = QuestDataCapability.get(event.getOriginal());
			// CompoundNBT nbt = (CompoundNBT)
			// QuestDataCapability.INSTANCE.writeNBT(oldQuestData, null);
			// IQuestData newQuestData = QuestDataCapability.get(event.getPlayer());
			// QuestDataCapability.INSTANCE.readNBT(newQuestData, null, nbt);

			YomiTriggerEvent yomiEvent = new YomiTriggerEvent(event.getPlayer(), oldPlayerProps, newPlayerProps);
			if (MinecraftForge.EVENT_BUS.post(yomiEvent))
				return;
			
				DFUserDeathEvents.changeApple(event);
		}


	}

	//wynd I swear I tried making this readable :(
	@SuppressWarnings("unchecked")
	public static boolean changeApple(PlayerEvent.Clone e) {

		double droppedChance = WyHelper.randomWithRange(1, 100);
		double chance = WyHelper.randomWithRange(1, 10);
		PlayerEntity original = e.getOriginal();
		IDevilFruit oldDevilFruit = DevilFruitCapability.get(e.getOriginal());
		if (oldDevilFruit.hasDevilFruit()) {
			List<ItemEntity> dropList = WyHelper.getEntitiesNear(e.getOriginal().getPosition(), e.getOriginal().world,
					30, ItemEntity.class);
			List<PlayerEntity> players = WyHelper.getEntitiesNear(original.getPosition(), original.world, 30,
					PlayerEntity.class);
			List<VillagerEntity> villagers = WyHelper.getEntitiesNear(original.getPosition(), original.world, 30,
					VillagerEntity.class);
			List<BlockPos> blockPosList = WyHelper.getNearbyBlocks(original, 30);

			dropList.removeIf(entry -> entry.getItem().getItem() != Items.APPLE);
			players.removeIf(entry -> !entry.inventory.hasItemStack(new ItemStack(Items.APPLE)));
			Set<Item> set = ImmutableSet.of(Items.APPLE);
			Iterator<BlockPos> iterator = blockPosList.iterator();
			while (iterator.hasNext()) {
				BlockPos pos = iterator.next();
				IInventory inven = ChestBlock.getInventory(original.world.getBlockState(pos), original.world, pos,
						false);

				if (inven != null) {
					if (!inven.hasAny(set)) {
						iterator.remove();
					}
				} else {
					iterator.remove();
				}
			}
			villagers.removeIf(entry -> !entry.getVillagerInventory().hasAny(set));
			if (!dropList.isEmpty() && droppedChance <= 15) {
				dropList.get(0).setItem(AbilityHelper.getDevilFruitItem(oldDevilFruit.getDevilFruit()));
				return true;
			} else if (!players.isEmpty() && chance == 1) {
				int stackIndex = WyHelper.getIndexOfItemStack(new ItemStack(Items.APPLE), players.get(0).inventory);

				if (stackIndex != -1) {
					players.get(0).inventory.setInventorySlotContents(stackIndex,
							AbilityHelper.getDevilFruitItem(oldDevilFruit.getDevilFruit()));
				}
				return true;
			} else if (!villagers.isEmpty() && chance == 1) {
				int stackIndex = WyHelper.getIndexOfItemStack(new ItemStack(Items.APPLE),
						villagers.get(0).getVillagerInventory());
				if (stackIndex != -1) {
					villagers.get(0).getVillagerInventory().setInventorySlotContents(stackIndex,
							AbilityHelper.getDevilFruitItem(oldDevilFruit.getDevilFruit()));
				}
				return true;
			} else if (!blockPosList.isEmpty() && chance == 1) {
				BlockState state = original.world.getBlockState(blockPosList.get(0));
				IInventory inven = ChestBlock.getInventory(state, original.world, blockPosList.get(0), false);
				int stackIndex = WyHelper.getIndexOfItemStack(new ItemStack(Items.APPLE), inven);

				if (stackIndex != -1) {
					inven.setInventorySlotContents(stackIndex,
							AbilityHelper.getDevilFruitItem(oldDevilFruit.getDevilFruit()));
				}
				return true;
			}
		}
		return false;

	}

}
