package xyz.pixelatedw.mineminenomi.data.entity.jollyroger;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class JollyRogerProvider implements ICapabilitySerializable<CompoundNBT>
{

	private IJollyRoger instance = JollyRogerCapability.INSTANCE.getDefaultInstance();

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side)
	{
		return JollyRogerCapability.INSTANCE.orEmpty(cap, LazyOptional.of(() -> this.instance));
	}

	@Override
	public CompoundNBT serializeNBT()
	{
		return (CompoundNBT) JollyRogerCapability.INSTANCE.getStorage().writeNBT(JollyRogerCapability.INSTANCE, this.instance, null);
	}

	@Override
	public void deserializeNBT(CompoundNBT nbt)
	{
		JollyRogerCapability.INSTANCE.getStorage().readNBT(JollyRogerCapability.INSTANCE, this.instance, null, nbt);
	}
	
}