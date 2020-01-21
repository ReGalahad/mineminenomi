package xyz.pixelatedw.mineminenomi.items.weapons;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.abilities.ItemAbility;
import xyz.pixelatedw.mineminenomi.api.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.api.data.ability.AbilityDataCapability.Category;
import xyz.pixelatedw.mineminenomi.api.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;

public class AbilitySwordItem extends CoreSwordItem
{

	public AbilitySwordItem(int damage)
	{
		super(new Properties().defaultMaxDamage(10), damage);
	}

	@Override
	public void inventoryTick(ItemStack itemStack, World world, Entity entity, int itemSlot, boolean isSelected)
	{
		super.inventoryTick(itemStack, world, entity, itemSlot, isSelected);
		if (entity instanceof PlayerEntity)
		{
			PlayerEntity owner = (PlayerEntity) entity;
			IDevilFruit devilFruitProps = DevilFruitCapability.get(owner);
			IAbilityData abilityDataProps = AbilityDataCapability.get(owner);

			for(Ability ability : abilityDataProps.getAbilities(Category.ALL))
			{
				if(ability == null || !(ability instanceof ItemAbility))
					continue;

				if(!(ability instanceof ItemAbility) || !ability.isPassiveActive() || !((ItemAbility) ability).canBeActive(owner))
					owner.inventory.deleteStack(itemStack);
			}
		}
	}

	@Override
	public AbilitySwordItem setIsPoisonous()
	{
		this.isPoisonous = true;
		this.poisonTimer = 100;
		return this;
	}

	@Override
	public AbilitySwordItem setIsPoisonous(int timer)
	{
		this.isPoisonous = true;
		this.poisonTimer = timer;
		return this;
	}

	@Override
	public AbilitySwordItem setIsFireAspect()
	{
		this.isFireAspect = true;
		return this;
	}

	@Override
	public AbilitySwordItem setIsFireAspect(int timer)
	{
		this.isFireAspect = true;
		this.fireAspectTimer = timer;
		return this;
	}

	@Override
	public AbilitySwordItem setIsSlownessInducing()
	{
		this.isSlownessInducing = true;
		return this;
	}

	@Override
	public AbilitySwordItem setIsSlownessInducing(int timer)
	{
		this.isSlownessInducing = true;
		this.slownessTimer = timer;
		return this;
	}

	@Override
	public AbilitySwordItem setIsSlownessInducing(int timer, boolean isStackable)
	{
		this.isSlownessInducing = true;
		this.slownessTimer = timer;
		this.isStackable = isStackable;
		return this;
	}

	@Override
	public boolean isEnchantable(ItemStack stack)
	{
		return false;
	}
}
