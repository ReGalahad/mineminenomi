package xyz.pixelatedw.mineminenomi.entities;

import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import xyz.pixelatedw.mineminenomi.init.ModEntities;

public class VivreCardEntity extends Entity
{
	
	private UUID ownerUUID;

	public VivreCardEntity(World worldIn)
	{
		super(ModEntities.VIVRE_CARD, worldIn);
	}

	public void setOwner(UUID uuid)
	{
		this.ownerUUID = uuid;
	}

	@Override
	public void tick()
	{
		super.tick();
		
		if(this.ownerUUID == null)
			return;	
		
		
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
