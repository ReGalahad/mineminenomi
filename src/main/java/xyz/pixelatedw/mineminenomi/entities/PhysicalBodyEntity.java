package xyz.pixelatedw.mineminenomi.entities;

import java.util.Optional;
import java.util.UUID;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.HandSide;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import xyz.pixelatedw.mineminenomi.init.ModEntities;

public class PhysicalBodyEntity extends LivingEntity
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

		if (owner == null)
			return true;

		return owner.attackEntityFrom(source, amount);
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

	@Override
	public Iterable<ItemStack> getArmorInventoryList()
	{
		return null;
	}

	@Override
	public ItemStack getItemStackFromSlot(EquipmentSlotType slotIn)
	{
		return ItemStack.EMPTY;
	}

	@Override
	public void setItemStackToSlot(EquipmentSlotType slotIn, ItemStack stack)
	{
	}

	@Override
	public HandSide getPrimaryHand()
	{
		return HandSide.RIGHT;
	}

}
