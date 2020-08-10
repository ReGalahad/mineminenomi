package xyz.pixelatedw.mineminenomi.world.spawners;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.entities.mobs.quest.givers.IQuestGiver;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.init.ModQuests;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.data.quest.QuestDataCapability;
import xyz.pixelatedw.wypi.debug.WyDebug;
import xyz.pixelatedw.wypi.quests.Quest;

public class TrainerSpawner
{
	private Random random = new Random();
	private int cooldown;
	private static final Biome.Category[] SWORDSMAN_BIOMES = new Biome.Category[] { Biome.Category.FOREST, Biome.Category.TAIGA, Biome.Category.PLAINS, Biome.Category.EXTREME_HILLS };
	private static final Biome.Category[] SNIPER_BIOMES = new Biome.Category[] { Biome.Category.FOREST, Biome.Category.TAIGA, Biome.Category.JUNGLE };
	private static final Biome.Category[] WEATHER_WIZARD_BIOMES = new Biome.Category[] { Biome.Category.MESA, Biome.Category.ICY, Biome.Category.MUSHROOM, Biome.Category.BEACH };
	private static final Biome.Category[] DOCTOR_BIOMES = new Biome.Category[] { Biome.Category.BEACH, Biome.Category.PLAINS, Biome.Category.TAIGA, Biome.Category.FOREST };

	public void tick(ServerWorld world)
	{
		if (--this.cooldown <= 0)
		{
			this.cooldown = CommonConfig.instance.getTimeBetweenTrainerSpawns();
			if (this.random.nextInt(100) <= CommonConfig.instance.getChanceForTrainerSpawn())
			{
				this.spawn(world);
			}
		}
	}

	private void spawn(ServerWorld world)
	{
		PlayerEntity player = world.getRandomPlayer();
		if (player == null)
			return;
		else
		{
			IEntityStats props = EntityStatsCapability.get(player);
			EntityType entityType = null;
			Biome.Category[] biomes = null;
			Quest[] quests = null;

			if(props.isSwordsman())
			{
				entityType = ModEntities.DOJO_SENSEI;
				biomes = SWORDSMAN_BIOMES;
				quests = ModQuests.SWORDSMAN_TRIALS;
			}
			else if(props.isSniper())
			{
				entityType = ModEntities.BOW_MASTER;
				biomes = SNIPER_BIOMES;
				quests = ModQuests.SNIPER_TRIALS;
			}
			else if(props.isWeatherWizard())
			{
				entityType = ModEntities.WEATHER_WIZARD;
				biomes = WEATHER_WIZARD_BIOMES;
				quests = ModQuests.ART_OF_WEATHER_TRIALS;
			}
			else if(props.isDoctor())
			{
				entityType = ModEntities.DOCTOR;
				biomes = DOCTOR_BIOMES;
				quests = ModQuests.DOCTOR_TRIALS;
			}
			
			if(entityType == null)
				return;
			
			BlockPos targetPos = player.getPosition();
			BlockPos spawnPos = WyHelper.findOnGroundSpawnLocation(world, entityType, targetPos, 20);
			if(spawnPos == null)
				return;
			List<LivingEntity> trainers = WyHelper.<LivingEntity>getEntitiesNear(spawnPos, world, 100).stream().filter(entity -> entity instanceof IQuestGiver).collect(Collectors.toList());
			boolean hasAvailableQuests = true;
			
			if(quests != null)
			{
				for(Quest trail : quests)
				{
					if(!QuestDataCapability.get(player).hasFinishedQuest(trail))
						break;
					
					hasAvailableQuests = false;
				}
			}
			
			if (spawnPos != null && hasAvailableQuests)
			{
				boolean canSpawnInBiome = Arrays.stream(biomes).anyMatch(category -> world.getBiome(spawnPos).getCategory() == category);

				if(trainers.size() < 2 && canSpawnInBiome)
				{
					entityType.spawn(world, (CompoundNBT) null, (ITextComponent) null, (PlayerEntity) null, spawnPos, SpawnReason.EVENT, false, false);
					WyDebug.debug("Trainer spawned at: " + spawnPos);
				}
			}
		}
	}
}
