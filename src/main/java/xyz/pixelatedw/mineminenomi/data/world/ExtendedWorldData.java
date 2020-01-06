package xyz.pixelatedw.mineminenomi.data.world;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.WorldSavedData;
import xyz.pixelatedw.mineminenomi.Env;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;

public class ExtendedWorldData extends WorldSavedData
{
	private static final String IDENTIFIER = "mineminenomi";

	private HashMap<String, Long> issuedBounties = new HashMap<String, Long>();
	private List<String> devilFruitsInWorld = new ArrayList<String>();
	private List<int[][]> protectedAreas = new ArrayList<int[][]>();
	
	public static Map<World, ExtendedWorldData> loadedExtWorlds = new HashMap<>();
	
	public ExtendedWorldData()
	{
		super(IDENTIFIER);
	}

	public ExtendedWorldData(String identifier)
	{
		super(identifier);
	}

	public static ExtendedWorldData get(World world)
	{
		if (world == null)
			return null;
		
		ExtendedWorldData worldExt;

		if (loadedExtWorlds.containsKey(world))
		{
			worldExt = loadedExtWorlds.get(world);
			return worldExt;
		}

		if (world instanceof ServerWorld)
		{
			ServerWorld serverWorld = (ServerWorld) world;
			world.increaseMaxEntityRadius(50);
			ExtendedWorldData worldSavedData = serverWorld.getSavedData().get(ExtendedWorldData::new, IDENTIFIER);
			if (worldSavedData != null)
			{
				worldExt = worldSavedData;
			}
			else
			{
				worldExt = new ExtendedWorldData();
				serverWorld.getSavedData().set(worldExt);
			}
		}
		else
		{
			worldExt = new ExtendedWorldData();
		}

		loadedExtWorlds.put(world, worldExt);
		return worldExt;

	}

	@Override
	public void read(CompoundNBT nbt)
	{
		CompoundNBT bounties = nbt.getCompound("issuedBounties");
		this.issuedBounties.clear();
		bounties.keySet().stream().forEach(x ->
		{
			this.issuedBounties.put(x, bounties.getLong(x));
		});

		CompoundNBT devilFruits = nbt.getCompound("devilFruits");
		this.devilFruitsInWorld.clear();
		devilFruits.keySet().stream().forEach(x ->
		{
			this.devilFruitsInWorld.add(x);
		});

		CompoundNBT protectedAreas = nbt.getCompound("protectedAreas");
		this.protectedAreas.clear();
		for (int i = 0; i <= protectedAreas.keySet().size(); i++)
		{
			int[] minPos = protectedAreas.getIntArray("minPos_" + i);
			int[] maxPos = protectedAreas.getIntArray("maxPos_" + i);
			this.protectedAreas.add(new int[][]
				{
						minPos, maxPos
				});
		}
	}

	@Override
	public CompoundNBT write(CompoundNBT nbt)
	{
		CompoundNBT bounties = new CompoundNBT();
		if (this.issuedBounties.size() > 0)
		{
			this.issuedBounties.entrySet().stream().forEach(x ->
			{
				bounties.putLong(x.getKey(), x.getValue());
			});
		}
		nbt.put("issuedBounties", bounties);

		CompoundNBT devilFruits = new CompoundNBT();
		if (this.devilFruitsInWorld.size() > 0)
		{
			this.devilFruitsInWorld.stream().forEach(x ->
			{
				devilFruits.putBoolean(x, true);
			});
		}
		nbt.put("devilFruits", devilFruits);

		CompoundNBT protectedAreas = new CompoundNBT();
		if (this.protectedAreas.size() > 0)
		{
			int i = 0;
			for (int[][] area : this.protectedAreas)
			{
				protectedAreas.putIntArray("minPos_" + i, area[0]);
				protectedAreas.putIntArray("maxPos_" + i, area[1]);
				i++;
			}
		}
		nbt.put("protectedAreas", protectedAreas);

		return nbt;
	}

	public boolean isInsideRestrictedArea(int posX, int posY, int posZ)
	{
		if (this.protectedAreas.size() <= 0)
			return false;

		for (int[][] area : this.protectedAreas)
		{
			int[] minPos = area[0];
			int[] maxPos = area[1];

			if (minPos.length <= 0 || maxPos.length <= 0)
				continue;

			//System.out.println((posX > minPos[0]) + " " + (posX < maxPos[0]));
			
			if (posX > minPos[0] && posX < maxPos[0] && posY > minPos[1] && posY < maxPos[1] && posZ > minPos[2] && posZ < maxPos[2])
			{
				return true;
			}
		}

		return false;
	}

	public void addRestrictedArea(int[] minPos, int[] maxPos)
	{
		this.protectedAreas.add(new int[][] { minPos, maxPos });
		this.markDirty();
	}

	public void removeRestrictedArea(int midX, int midY, int midZ)
	{
		Iterator iterator = this.protectedAreas.iterator();
		while (iterator.hasNext())
		{
			int[][] area = (int[][]) iterator.next();
			int[] minPos = area[0];
			int[] maxPos = area[1];

			if (minPos.length <= 0 || maxPos.length <= 0)
				continue;

			int possibleMidX = (minPos[0] + maxPos[0]) / 2;
			int possibleMidY = (minPos[1] + maxPos[1]) / 2;
			int possibleMidZ = (minPos[2] + maxPos[2]) / 2;

			if (midX == possibleMidX && midY == possibleMidY && midZ == possibleMidZ)
				iterator.remove();
		}

		this.markDirty();
	}

	public List<int[][]> getAllRestrictions()
	{
		return this.protectedAreas;
	}

	public HashMap<String, Long> getAllBounties()
	{
		return this.issuedBounties;
	}

	public Object[] getRandomBounty()
	{
		int count = this.getAllBounties().size();
	
		if(count <= 0)
			return null;
		
		Object[] keys = this.getAllBounties().keySet().toArray();
		Object key = keys[new Random().nextInt(count)];
		
		long bounty = this.getAllBounties().get(key);
		
		return new Object[] { key, bounty };
	}
	
	public long getBounty(String uuid)
	{
		if (this.issuedBounties.containsKey(uuid.toLowerCase()))
			return this.issuedBounties.get(uuid.toLowerCase());

		return 0;
	}

	public void issueBounty(String uuid, long bounty)
	{
		if (this.issuedBounties.containsKey(uuid.toLowerCase()))
		{
			this.issuedBounties.remove(uuid.toLowerCase());
			this.issuedBounties.put(uuid.toLowerCase(), bounty);
		}
		else
			this.issuedBounties.put(uuid.toLowerCase(), bounty);

		this.markDirty();
	}

	public void addDevilFruitInWorld(AkumaNoMiItem fruit)
	{
		String name = fruit.getTranslationKey().replace("item." + Env.PROJECT_ID + ".", "").replace("nomi", "").replace(":", "").replace(",", "").replace("model", "");

		if (!this.devilFruitsInWorld.contains(name))
		{
			this.devilFruitsInWorld.add(name);
			this.markDirty();
		}
	}

	public void addDevilFruitInWorld(String name)
	{
		if (!this.devilFruitsInWorld.contains(name))
		{
			this.devilFruitsInWorld.add(name);
			this.markDirty();
		}
	}

	public boolean isDevilFruitInWorld(String name)
	{
		return this.devilFruitsInWorld.contains(name);
	}
}
