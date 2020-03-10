package xyz.pixelatedw.mineminenomi.data.entity.haki;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class HakiDataProvider implements ICapabilitySerializable<CompoundNBT>
{

	private IHakiData instance = HakiDataCapability.INSTANCE.getDefaultInstance();

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side)
	{
		return HakiDataCapability.INSTANCE.orEmpty(cap, LazyOptional.of(() -> instance));
	}

	@Override
	public CompoundNBT serializeNBT()
	{
		return (CompoundNBT) HakiDataCapability.INSTANCE.getStorage().writeNBT(HakiDataCapability.INSTANCE, instance, null);
	}

	@Override
	public void deserializeNBT(CompoundNBT nbt)
	{
		HakiDataCapability.INSTANCE.getStorage().readNBT(HakiDataCapability.INSTANCE, instance, null, nbt);
	}
	
}