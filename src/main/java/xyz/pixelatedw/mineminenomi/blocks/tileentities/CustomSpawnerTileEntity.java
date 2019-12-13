package xyz.pixelatedw.mineminenomi.blocks.tileentities;

import java.util.ArrayList;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.text.ITextComponent;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;

public class CustomSpawnerTileEntity extends TileEntity implements ITickableTileEntity
{
	private EntityType entityToSpawn = EntityType.PIG;
	private int spawnLimit = 5;
	private ArrayList<LivingEntity> spawnedEntities = new ArrayList<LivingEntity>();

	public static final TileEntityType TILE_ENTITY = WyRegistry.registerTileEntity("custom_spawner", CustomSpawnerTileEntity::new, ModBlocks.customSpawner);

	public CustomSpawnerTileEntity()
	{
		super(TILE_ENTITY);
	}
	
	public CustomSpawnerTileEntity setSpawnerMob(EntityType toSpawn)
	{
		this.entityToSpawn = toSpawn;
		return this;
	}

	public CustomSpawnerTileEntity setSpawnerLimit(int limit)
	{
		this.spawnLimit = limit;
		return this;
	}

	@Override
	public void tick()
	{
		if (!this.world.isRemote)
		{
			boolean flag = false;

			if (!WyHelper.getEntitiesNear(this, 30, PlayerEntity.class).isEmpty())
			{
				LivingEntity e = WyHelper.getEntitiesNear(this, 30, PlayerEntity.class).get(0);

				if (e != null && e instanceof PlayerEntity)
				{
					PlayerEntity player = (PlayerEntity) e;

					if ((this.spawnedEntities.size() < this.spawnLimit))
					{
						LivingEntity newSpawn = (LivingEntity) entityToSpawn.spawn(world, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, pos.up(), SpawnReason.STRUCTURE, false, false);
						if (newSpawn != null)
						{
							newSpawn.setLocationAndAngles(this.pos.getX(), this.pos.getY(), this.pos.getZ(), 0, 0);
							//newSpawn.onInitialSpawn();
							this.world.addEntity(newSpawn);
							this.spawnedEntities.add(newSpawn);
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
				for (LivingEntity elb : this.spawnedEntities)
				{
					elb.remove();
				}
				this.spawnedEntities.clear();
				flag = false;
			}
		}
	}

	@Override
	public void read(CompoundNBT nbtTag)
	{
		super.read(nbtTag);
		this.entityToSpawn = EntityType.byKey(nbtTag.getString("entityToSpawn")).orElse(EntityType.PIG);
		this.spawnLimit = nbtTag.getInt("spawnLimit");
	}

	@Override
	public CompoundNBT write(CompoundNBT nbtTag)
	{
		super.write(nbtTag);
		nbtTag.putInt("spawnLimit", this.spawnLimit);
		nbtTag.putString("entityToSpawn", EntityType.getKey(this.entityToSpawn).toString());
		return nbtTag;
	}
}
