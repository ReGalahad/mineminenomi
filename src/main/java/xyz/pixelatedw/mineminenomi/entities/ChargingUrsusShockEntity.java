package xyz.pixelatedw.mineminenomi.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import xyz.pixelatedw.mineminenomi.init.ModEntities;

public class ChargingUrsusShockEntity extends Entity
{
	protected static final DataParameter<Float> CHARGE = EntityDataManager.createKey(ChargingUrsusShockEntity.class, DataSerializers.FLOAT);

	private LivingEntity owner;
	
	public ChargingUrsusShockEntity(World worldIn)
	{
		super(ModEntities.CHARGING_URSUS_SHOCK, worldIn);
	}
	
	public ChargingUrsusShockEntity(EntityType<?> entityTypeIn, World worldIn)
	{
		super(entityTypeIn, worldIn);
	}

	@Override
	public void tick()
	{
		super.tick();
		
		if (!this.world.isRemote)
		{
			if(this.owner == null)
			{
				this.remove();
				return;
			}
			
			this.setPosition(this.owner.posX, this.owner.posY + 1.5, this.owner.posZ);
			this.setRotation(this.owner.rotationYaw, 0);
		}
	}
	
	@Override
	protected void registerData()
	{
		this.dataManager.register(CHARGE, 0F);
	}

	public void setCharge(float value)
	{
		this.dataManager.set(CHARGE, value);
	}
	
	public float getCharge()
	{
		return this.dataManager.get(CHARGE);
	}
	
	public void setOwner(LivingEntity owner)
	{
		this.owner = owner;
	}
	
	@Override
	protected void readAdditional(CompoundNBT compound)
	{
	}

	@Override
	protected void writeAdditional(CompoundNBT compound)
	{
	}

	@Override
	public IPacket<?> createSpawnPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
