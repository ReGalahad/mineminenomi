package xyz.pixelatedw.mineminenomi.api.helpers;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.entity.EntityType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.CustomSpawnerTileEntity;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.wypi.WyHelper;

public class StructuresHelper
{
	private static final List<EntityType> MARINE_GRUNT_TYPES = Lists.newArrayList(ModEntities.MARINE_WITH_SWORD, ModEntities.MARINE_WITH_GUN);
	private static final List<EntityType> PIRATE_GRUNT_TYPES = Lists.newArrayList(ModEntities.PIRATE_WITH_SWORD, ModEntities.PIRATE_WITH_GUN);
	private static final List<EntityType> BANDIT_GRUNT_TYPES = Lists.newArrayList(ModEntities.BANDIT_WITH_SWORD);

	private static EntityType chooseCaptainType(StructureFaction faction)
	{
		EntityType captainType = null;
		
		switch(faction)
		{
			case MARINE:
				captainType = ModEntities.MARINE_CAPTAIN;
				break;
			case PIRATE:
				captainType = ModEntities.PIRATE_CAPTAIN;
				break;
			case BANDIT:
				captainType = ModEntities.BANDIT_WITH_SWORD;
				break;
			default:
				captainType = ModEntities.MARINE_CAPTAIN;
				break;
		}
		
		return captainType;
	}
	
	private static EntityType chooseGruntType(StructureFaction faction)
	{
		List<EntityType> factionList = null;
		
		switch(faction)
		{
			case MARINE:
				factionList = MARINE_GRUNT_TYPES;
				break;
			case PIRATE:
				factionList = PIRATE_GRUNT_TYPES;
				break;
			case BANDIT:
				factionList = BANDIT_GRUNT_TYPES;
				break;
			default:
				factionList = MARINE_GRUNT_TYPES;
				break;
		}
		
		return factionList.get((int) WyHelper.randomWithRange(0, factionList.size() - 1));
	}
	
	public static void setupSpawners(String function, IWorld world, BlockPos pos, StructureFaction faction)
	{
		String[] func = function.split("_");
		
		if(!func[func.length - 1].equalsIgnoreCase("spawn"))
			return;
		
		try
		{
			EntityType type = null;
			int spawnLimit = 1;
			if(func[0].startsWith("grunt"))
			{
				spawnLimit = Integer.parseInt(func[1].replace("x", ""));
				type = chooseGruntType(faction);
			}
			else if(func[0].startsWith("captain"))
			{
				spawnLimit = 1;
				type = chooseCaptainType(faction);
			}
			
			world.setBlockState(pos, ModBlocks.CUSTOM_SPAWNER.getDefaultState(), 3);
			TileEntity spawner = world.getTileEntity(pos);
			if (spawner instanceof CustomSpawnerTileEntity)
			{
				((CustomSpawnerTileEntity) spawner).setSpawnerLimit(spawnLimit);
				((CustomSpawnerTileEntity) spawner).setSpawnerMob(type);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static enum StructureFaction
	{
		MARINE,
		PIRATE,
		BANDIT
	}
}
