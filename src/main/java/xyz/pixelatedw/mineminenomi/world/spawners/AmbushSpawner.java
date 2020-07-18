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
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.entities.mobs.GenericNewEntity;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.debug.WyDebug;

public class AmbushSpawner
{
	private Random random = new Random();
	private int cooldown;
	private static final Biome.Category[] BIOMES = new Biome.Category[] { Biome.Category.FOREST, Biome.Category.BEACH, Biome.Category.EXTREME_HILLS, Biome.Category.TAIGA, Biome.Category.SAVANNA, Biome.Category.SWAMP, Biome.Category.ICY };
	
	public void tick(ServerWorld world)
	{
		if (--this.cooldown <= 0)
		{
			this.cooldown = CommonConfig.instance.getTimeBetweenAmbushSpawns();
			if (this.random.nextInt(100) <= CommonConfig.instance.getChanceForAmbushSpawn())
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
			
			if(props.isPirate() || props.isBandit() || props.isRevolutionary())
			{
				BlockPos targetPos = player.getPosition();
				long bounty = props.getBounty();
				boolean canSpawnInBiome = Arrays.stream(BIOMES).anyMatch(category -> world.getBiome(targetPos).getCategory() == category);
				
				if(!canSpawnInBiome || bounty < 10000)
					return;
				
				EntityType captainEntity = ModEntities.MARINE_CAPTAIN;
				EntityType grunt1Entity = ModEntities.MARINE_WITH_SWORD;
				EntityType grunt2Entity = ModEntities.MARINE_WITH_GUN;
				
				int r = this.random.nextInt(2);
				if(r == 1)
				{
					// Bounty Hunters here
					//captainEntity = ModEntities.MARINE_CAPTAIN;
					//grunt1Entity = ModEntities.MARINE_WITH_SWORD;
					//grunt2Entity = ModEntities.MARINE_WITH_GUN;
				}
				
				List<GenericNewEntity> dangers = WyHelper.getEntitiesNear(targetPos, world, 80, GenericNewEntity.class);
				if(dangers.size() > 50)
					return;
				
				int nrCaptains = 1 + (int) Math.ceil(bounty / 200000);
				int nrGrunts = 3 + (int) Math.ceil(bounty / 100000);
				
				if(nrCaptains > 3)
					nrCaptains = 3;
				
				if(nrGrunts > 30)
					nrGrunts = 30;
				
				// Spawn Captains
				for(int i = 0; i < nrCaptains; i++)
				{
					BlockPos spawnPos = WyHelper.findOnGroundSpawnLocation(world, captainEntity, targetPos, 10);
					if (spawnPos != null)
						captainEntity.spawn(world, (CompoundNBT) null, (ITextComponent) null, (PlayerEntity) null, spawnPos, SpawnReason.EVENT, false, false);
				}
				
				// Grunts
				for(int i = 0; i < nrGrunts; i++)
				{
					EntityType gruntEntity = (i % 2 == 0 ? grunt1Entity : grunt2Entity);
					
					BlockPos spawnPos = WyHelper.findOnGroundSpawnLocation(world, gruntEntity, targetPos, 20);
					if (spawnPos != null)
						gruntEntity.spawn(world, (CompoundNBT) null, (ITextComponent) null, (PlayerEntity) null, spawnPos, SpawnReason.EVENT, false, false);
				}
				
				WyDebug.debug("Ambush spawned around these coords: " + targetPos);
			}		
		}
	}
}
