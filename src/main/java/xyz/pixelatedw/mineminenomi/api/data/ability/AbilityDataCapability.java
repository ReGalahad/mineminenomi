package xyz.pixelatedw.mineminenomi.api.data.ability;

import java.io.IOException;

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
import xyz.pixelatedw.mineminenomi.api.abilities.Ability.Category;
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

				try
				{
					if (instance.getPreviouslyUsedAbility() != null)
						props.putByteArray("previouslyUsedAbility", WyHelper.serialize(instance.getPreviouslyUsedAbility()));

					for (int i = 0; i < instance.getHotbarAbilities().length; i++)
					{
						Ability ability = instance.getHotbarAbilities()[i];
						System.out.println(ability);
						if (ability != null)
							props.putString("hotbar_ability_" + i, ability.getName());
					}

					int i = 0;
					for (Ability abl : instance.getAbilities(Category.ALL))
					{
						props.putByteArray("ability_" + i, WyHelper.serialize(abl));
						i++;
					}
					props.putInt("abilitiesOwned", i);
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}

				return props;
			}

			@Override
			public void readNBT(Capability<IAbilityData> capability, IAbilityData instance, Direction side, INBT nbt)
			{
				CompoundNBT props = (CompoundNBT) nbt;

				instance.setCombatMode(props.getBoolean("combatMode"));

				try
				{
					instance.setPreviouslyUsedAbility((Ability) WyHelper.deserialize(props.getByteArray("previouslyUsedAbility")));

					for (int i = 0; i < instance.getHotbarAbilities().length; i++)
					{
						System.out.println(instance.getAbility(props.getString("hotbar_ability_" + i)));
						instance.setAbilityInHotbar(i, instance.getAbility(props.getString("hotbar_ability_" + i)));
					}

					int total = props.getInt("abilitiesOwned");
					instance.clearAbilities(Category.ALL);
					
					for (int i = 0; i < total; i++)
						instance.addAbility((Ability) WyHelper.deserialize(props.getByteArray("ability_" + i)));					
				}
				catch (ClassNotFoundException | IOException e)
				{
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
