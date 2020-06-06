package xyz.pixelatedw.mineminenomi.data.entity.haki;

import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class HakiDataCapability
{
	@CapabilityInject(IHakiData.class)
	public static final Capability<IHakiData> INSTANCE = null;

	public static void register()
	{
		CapabilityManager.INSTANCE.register(IHakiData.class, new Capability.IStorage<IHakiData>()
		{
			@Override
			public INBT writeNBT(Capability<IHakiData> capability, IHakiData instance, Direction side)
			{
				CompoundNBT props = new CompoundNBT();

				props.putInt("kenHakiExp", instance.getKenbunshokuHakiExp());
				props.putInt("busoHardeningHakiExp", instance.getBusoshokuHardeningHakiExp());
				props.putInt("busoImbuingHakiExp", instance.getBusoshokuImbuingHakiExp());

				return props;
			}

			@Override
			public void readNBT(Capability<IHakiData> capability, IHakiData instance, Direction side, INBT nbt)
			{
				CompoundNBT props = (CompoundNBT) nbt;

				instance.setKenbunshokuHakiExp(props.getInt("kenHakiExp"));
				instance.setBusoshokuHardeningHakiExp(props.getInt("busoHardeningHakiExp"));
				instance.setBusoshokuImbuingHakiExp(props.getInt("busoImbuingHakiExp"));

			}

		}, () -> new HakiDataBase());
	}
	
	public static IHakiData get(final LivingEntity entity)
	{
		return entity.getCapability(INSTANCE, null).orElse(new HakiDataBase());
	}
}
