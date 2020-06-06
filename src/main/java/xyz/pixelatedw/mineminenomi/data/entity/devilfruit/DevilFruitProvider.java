package xyz.pixelatedw.mineminenomi.data.entity.devilfruit;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class DevilFruitProvider implements ICapabilitySerializable<CompoundNBT>
{

	private IDevilFruit instance = DevilFruitCapability.INSTANCE.getDefaultInstance();

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side)
	{
		return DevilFruitCapability.INSTANCE.orEmpty(cap, LazyOptional.of(() -> this.instance));
	}

	@Override
	public CompoundNBT serializeNBT()
	{
		return (CompoundNBT) DevilFruitCapability.INSTANCE.getStorage().writeNBT(DevilFruitCapability.INSTANCE, this.instance, null);
	}

	@Override
	public void deserializeNBT(CompoundNBT nbt)
	{
		DevilFruitCapability.INSTANCE.getStorage().readNBT(DevilFruitCapability.INSTANCE, this.instance, null, nbt);
	}
	
}