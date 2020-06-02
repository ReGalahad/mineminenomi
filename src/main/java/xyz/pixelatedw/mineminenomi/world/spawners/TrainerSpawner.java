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
import xyz.pixelatedw.mineminenomi.entities.mobs.quest.givers.IQuestGiver;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.wypi.WyHelper;

public class TrainerSpawner
{
	private Random random = new Random();
	private int cooldown;
	private static final Biome.Category[] BIOMES = new Biome.Category[] { Biome.Category.FOREST, Biome.Category.TAIGA, Biome.Category.JUNGLE };
	private static final EntityType[] TRAINER_TYPES = new EntityType[] { ModEntities.BOW_MASTER };
	
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
			int r = this.random.nextInt(TRAINER_TYPES.length);
			EntityType entityType = TRAINER_TYPES[r];
			BlockPos targetPos = player.getPosition();
			BlockPos spawnPos = WyHelper.findOnGroundSpawnLocation(world, entityType, targetPos, 20);
			List<LivingEntity> trainers = WyHelper.<LivingEntity>getEntitiesNear(targetPos, world, 40).stream().filter(entity -> entity instanceof IQuestGiver).collect(Collectors.toList());
			boolean canSpawnInBiome = Arrays.stream(BIOMES).anyMatch(category -> world.getBiome(spawnPos).getCategory() == category);

			if (spawnPos != null && trainers.size() < 2 && canSpawnInBiome)
			{
				entityType.spawn(world, (CompoundNBT) null, (ITextComponent) null, (PlayerEntity) null, spawnPos, SpawnReason.EVENT, false, false);
			}
		}
	}
}
