package xyz.pixelatedw.mineminenomi.entities;

import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import xyz.pixelatedw.mineminenomi.init.ModEntities;

public class PhysicalBodyEntity extends Entity
{
	private UUID ownerUUID;
	
	public PhysicalBodyEntity(World worldIn)
	{
		super(ModEntities.PHYSICAL_BODY, worldIn);
	}

	public void setOwner(UUID uuid)
	{
		this.ownerUUID = uuid;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount)
	{
		PlayerEntity owner = this.world.getPlayerByUuid(this.ownerUUID);
		
		if(owner == null)
			return true;
		
		return owner.attackEntityFrom(source, amount);
	}

	@Override
	public void tick()
	{
		if(!this.world.isRemote)
		{
			PlayerEntity owner = this.world.getPlayerByUuid(this.ownerUUID);

			if(owner == null || !owner.isAlive())
			{
				this.remove();
				return;
			}			
		}
		super.tick();	
	}
	
	@Override
	protected void registerData() {}

	@Override
	protected void writeAdditional(CompoundNBT compound)
	{
		compound.putString("OwnerUUID", this.ownerUUID.toString());
	}

	@Override
	protected void readAdditional(CompoundNBT compound)
	{
		if (compound.contains("OwnerUUID", 8))
			this.setOwner(UUID.fromString(compound.getString("OwnerUUID")));
	}

	@Override
	public IPacket<?> createSpawnPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}

}
