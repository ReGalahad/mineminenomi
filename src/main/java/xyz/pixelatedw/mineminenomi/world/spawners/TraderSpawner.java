package xyz.pixelatedw.mineminenomi.world.spawners;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.entities.TraderEntity;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.wypi.WyHelper;

public class TraderSpawner
{
	private Random random = new Random();
	private int cooldown;
	private static final Biome.Category[] BIOMES = new Biome.Category[] { Biome.Category.PLAINS, Biome.Category.FOREST, Biome.Category.BEACH, Biome.Category.EXTREME_HILLS, Biome.Category.TAIGA, Biome.Category.SAVANNA, Biome.Category.SWAMP };
	private static final EntityType[] TRADER_TYPES = new EntityType[] { ModEntities.MARINE_TRADER, ModEntities.PIRATE_TRADER };
	
	public void tick(ServerWorld world)
	{
		if (--this.cooldown <= 0)
		{
			this.cooldown = CommonConfig.instance.getTimeBetweenTraderSpawns();
			if (this.random.nextInt(100) <= CommonConfig.instance.getChanceForTraderSpawn())
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
			int r = this.random.nextInt(TRADER_TYPES.length - 1);
			EntityType entityType = TRADER_TYPES[r];
			BlockPos targetPos = player.getPosition();
			BlockPos spawnPos = WyHelper.findOnGroundSpawnLocation(world, entityType, targetPos, 20);
			List<TraderEntity> traders = WyHelper.getEntitiesNear(targetPos, world, 40, TraderEntity.class);
			boolean canSpawnInBiome = Arrays.stream(BIOMES).anyMatch(category -> world.getBiome(spawnPos).getCategory() == category);

			if (spawnPos != null && traders.size() <= 3 && canSpawnInBiome)
			{
				entityType.spawn(world, (CompoundNBT) null, (ITextComponent) null, (PlayerEntity) null, spawnPos, SpawnReason.EVENT, false, false);
			}
		}
	}
}
