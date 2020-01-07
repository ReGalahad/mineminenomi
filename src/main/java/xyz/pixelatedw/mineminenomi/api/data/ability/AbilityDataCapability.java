package xyz.pixelatedw.mineminenomi.api.data.ability;

import java.util.logging.Level;
import java.util.logging.Logger;

import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.data.CapabilityProviderSerializable;

public class AbilityDataCapability
{
	@CapabilityInject(IAbilityData.class)
	public static final Capability<IAbilityData> INSTANCE = null;

	public static void register()
	{
		CapabilityManager.INSTANCE.register(IAbilityData.class, new Capability.IStorage<IAbilityData>()
		{

			@Override
			public INBT writeNBT(Capability<IAbilityData> capability, IAbilityData instance, Direction side)
			{
				CompoundNBT props = new CompoundNBT();

				props.putBoolean("combatMode", instance.isInCombatMode());

				//if (instance.getPreviouslyUsedAbility() != null)
				//	props.put("previouslyUsedAbility", saveNLOBData(instance.getPreviouslyUsedAbility()));

				for (int i = 0; i < instance.getAbilitiesInHotbar().length; i++)
				{
					Ability ability = instance.getAbilitiesInHotbar()[i];
					if (ability != null)
						props.putByteArray("hotbar_ability_" + i, WyHelper.serialize(ability));
				}

				for (int i = 0; i < instance.getDevilFruitAbilities().length; i++)
				{
					Ability ability = instance.getDevilFruitAbilities()[i];
					if (ability != null)
						props.put("devilfruits_ability_" + i, saveNLOBData(ability));
				}

				return props;
			}

			@Override
			public void readNBT(Capability<IAbilityData> capability, IAbilityData instance, Direction side, INBT nbt)
			{
				CompoundNBT props = (CompoundNBT) nbt;

				try
				{
					instance.setCombatMode(props.getBoolean("combatMode"));

					//instance.setPreviouslyUsedAbility(this.loadAbilityFromNLOB(props.getCompound("previouslyUsedAbility")));

					for (int i = 0; i < instance.getAbilitiesInHotbar().length; i++)
						instance.getAbilitiesInHotbar()[i] = this.loadAbilityFromNLOB(props.getCompound("hotbar_ability_" + i));

					for (int i = 0; i < instance.getDevilFruitAbilities().length; i++)
						instance.getDevilFruitAbilities()[i] = this.loadAbilityFromNLOB(props.getCompound("devilfruits_ability_" + i));
				}
				catch (Exception e)
				{
					Logger.getGlobal().log(Level.SEVERE, "Ability is not registered correctly or could not be found in the master list !");
					e.printStackTrace();
				}
			}

		}, AbilityDataBase::new);
	}

	public static IAbilityData get(final LivingEntity entity)
	{
		return entity.getCapability(INSTANCE, null).orElse(new AbilityDataBase());
	}

	public static ICapabilityProvider createProvider(final IAbilityData data)
	{
		return new CapabilityProviderSerializable<>(INSTANCE, null, data);
	}
}
