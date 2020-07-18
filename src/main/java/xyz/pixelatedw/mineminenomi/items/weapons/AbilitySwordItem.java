package xyz.pixelatedw.mineminenomi.items.weapons;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.ItemAbility;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

public class AbilitySwordItem extends CoreSwordItem
{
	private Ability ability = null;

	public AbilitySwordItem(Ability ability, int damage)
	{
		super(new Properties(), damage);
		this.ability = ability;
	}

	@Override
	public void inventoryTick(ItemStack itemStack, World world, Entity entity, int itemSlot, boolean isSelected)
	{
		super.inventoryTick(itemStack, world, entity, itemSlot, isSelected);
		if (entity instanceof PlayerEntity && this.ability != null)
		{
			PlayerEntity owner = (PlayerEntity) entity;
			IAbilityData abilityDataProps = AbilityDataCapability.get(owner);

			boolean deleteSword = true;

			for(Ability ability : abilityDataProps.getEquippedAbilities(AbilityCategory.ALL))
			{
				if(ability == null || !(ability instanceof ItemAbility) || !this.ability.equals(ability))
					continue;

				if(ability.isContinuous())
					deleteSword = false;
			}

			if(deleteSword)
				owner.inventory.deleteStack(itemStack);
		}
	}

	@Override
	public boolean onEntityItemUpdate(ItemStack itemStack, ItemEntity entityItem)
	{
		if(entityItem.isAlive())
			entityItem.remove();
		return true;

	}

	@Override
	public boolean isEnchantable(ItemStack stack)
	{
		return false;
	}
}
