package xyz.pixelatedw.mineminenomi.blocks.tileentities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.util.Constants;
import xyz.pixelatedw.mineminenomi.init.ModTileEntities;
import xyz.pixelatedw.wypi.WyHelper;

public class CustomSpawnerTileEntity extends TileEntity implements ITickableTileEntity
{
	private EntityType entityToSpawn = EntityType.PIG;
	private int spawnLimit = 5;
	private int playerDistance = 30;
	private ArrayList<UUID> spawnedEntities = new ArrayList<UUID>();

	public CustomSpawnerTileEntity()
	{
		super(ModTileEntities.CUSTOM_SPAWNER);
	}
	
	public CustomSpawnerTileEntity setSpawnerMob(EntityType toSpawn)
	{
		this.entityToSpawn = toSpawn;
		this.markDirty();
		return this;
	}

	public CustomSpawnerTileEntity setSpawnerLimit(int limit)
	{
		this.spawnLimit = limit;
		this.markDirty();
		return this;
	}
	
	public CustomSpawnerTileEntity setPlayerDistance(int distance)
	{
		this.playerDistance = distance;
		this.markDirty();
		return this;
	}

	@Override
	public void tick()
	{
		if (!this.world.isRemote)
		{
	    	if(this.world.getBlockState(this.pos.down()).getBlock() == Blocks.AIR)
	    		this.world.setBlockState(this.pos, Blocks.AIR.getDefaultState());
			
			boolean flag = false;

			List<PlayerEntity> nearbyEntities = WyHelper.getEntitiesNear(this.getPos(), this.world, this.playerDistance, PlayerEntity.class);
			
			if (!nearbyEntities.isEmpty())
			{
				LivingEntity target = nearbyEntities.get(0);

				if (target != null && target instanceof PlayerEntity && this.entityToSpawn != null)
				{
					if ((this.spawnedEntities.size() < this.spawnLimit))
					{
						LivingEntity newSpawn = (LivingEntity) this.entityToSpawn.spawn(this.world, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, pos.up(), SpawnReason.STRUCTURE, false, false);
						if (newSpawn != null)
						{
							newSpawn.setLocationAndAngles(this.pos.getX(), this.pos.getY(), this.pos.getZ(), 0, 0);
							//newSpawn.onInitialSpawn();
							this.world.addEntity(newSpawn);
							this.spawnedEntities.add(newSpawn.getUniqueID());
							this.markDirty();
						}
					}
				}
			}
			else
			{
				if (this.spawnedEntities.size() == this.spawnLimit)
				{
					flag = true;
				}
			}

			if (flag)
			{
				for (UUID spawnUUID : this.spawnedEntities)
				{
					for(Entity target : WyHelper.getEntitiesNear(this.getPos(), this.world, 200, this.entityToSpawn.create(this.world).getClass()))
					{
						if(target.getUniqueID().equals(spawnUUID))
							target.remove();
					}
				}
				this.spawnedEntities.clear();
				this.markDirty();
				flag = false;
			}
		}
	}

	@Override
	public void read(CompoundNBT nbt)
	{
		super.read(nbt);
		this.spawnLimit = nbt.getInt("spawnLimit");
		this.playerDistance = nbt.getInt("playerDistance");
		if(this.playerDistance <= 0)
			this.playerDistance = 30;
		this.entityToSpawn = EntityType.byKey(nbt.getString("entityToSpawn")).orElse(EntityType.PIG);

		ListNBT spawnedEntities = nbt.getList("spawns", Constants.NBT.TAG_COMPOUND);
		for (int i = 0; i < spawnedEntities.size(); i++)
		{
			CompoundNBT nbtEntity = spawnedEntities.getCompound(i);
			UUID nbtUUID = nbtEntity.getUniqueId("uuid");
			this.spawnedEntities.add(nbtUUID);
		}	
	}

	@Override
	public CompoundNBT write(CompoundNBT nbt)
	{
		super.write(nbt);
		nbt.putInt("spawnLimit", this.spawnLimit);
		nbt.putInt("playerDistance", this.playerDistance);
		nbt.putString("entityToSpawn", EntityType.getKey(this.entityToSpawn).toString());
		
		ListNBT spawnedEntities = new ListNBT();
		for(UUID uuid : this.spawnedEntities)
		{
			CompoundNBT nbtEntity = new CompoundNBT();
			nbtEntity.putUniqueId("uuid", uuid);
			spawnedEntities.add(nbtEntity);
		}
		nbt.put("spawns", spawnedEntities);

		return nbt;
	}
}
