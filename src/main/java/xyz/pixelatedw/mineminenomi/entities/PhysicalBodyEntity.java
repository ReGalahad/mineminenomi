package xyz.pixelatedw.mineminenomi.entities;

import java.util.Optional;
import java.util.UUID;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import xyz.pixelatedw.mineminenomi.init.ModEntities;

public class PhysicalBodyEntity extends CreatureEntity
{
	private static final DataParameter<Optional<UUID>> OWNER = EntityDataManager.createKey(PhysicalBodyEntity.class, DataSerializers.OPTIONAL_UNIQUE_ID);

	public PhysicalBodyEntity(World worldIn)
	{
		super(ModEntities.PHYSICAL_BODY, worldIn);
	}

	public void setOwner(UUID uuid)
	{
		this.dataManager.set(OWNER, Optional.of(uuid));
	}

	public PlayerEntity getOwner()
	{
		return this.getDataManager().get(OWNER).isPresent() ? this.world.getPlayerByUuid(this.getDataManager().get(OWNER).get()) : null;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount)
	{
		PlayerEntity owner = this.getOwner();
		if(owner == null)
			return false;
		
		owner.attackEntityFrom(DamageSource.MAGIC, amount);
		this.setHealth(owner.getHealth());
		
		return super.attackEntityFrom(source, amount);
	}

	@Override
	public void tick()
	{
		if (!this.world.isRemote)
		{
			PlayerEntity owner = this.getOwner();

			if (owner == null || !owner.isAlive())
			{
				this.remove();
				return;
			}
		}

		super.tick();
	}

	@Override
	protected void registerData()
	{
		super.registerData();
		this.dataManager.register(OWNER, null);
	}

	@Override
	protected void registerAttributes()
	{
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
		this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
	}

	@Override
	public void writeAdditional(CompoundNBT compound)
	{
		super.writeAdditional(compound);
		if (this.dataManager.get(OWNER) != null)
		{
			compound.putString("OwnerUUID", this.dataManager.get(OWNER).get().toString());
		}
	}

	@Override
	public void readAdditional(CompoundNBT compound)
	{
		super.readAdditional(compound);
		this.dataManager.set(OWNER, Optional.of(UUID.fromString(compound.getString("OwnerUUID"))));
	}

	@Override
	public IPacket<?> createSpawnPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
