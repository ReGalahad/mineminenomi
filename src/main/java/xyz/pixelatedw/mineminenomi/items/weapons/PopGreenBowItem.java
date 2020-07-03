package xyz.pixelatedw.mineminenomi.items.weapons;

import java.util.function.Predicate;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.PopGreenProjectile;
import xyz.pixelatedw.mineminenomi.init.ModItems;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class PopGreenBowItem extends RangedWeaponItem {

	public PopGreenBowItem(double weightScale) {
		super(weightScale);
	}

	@Override
	public Predicate<ItemStack> getInventoryAmmoPredicate() {
		return itemStack -> itemStack.getItem() == ModItems.POP_GREEN;
	}

	@Override
	public Item getBulletItem() {
		return ModItems.POP_GREEN;
	}

	@Override
	public AbilityProjectileEntity getProjectile(World w, LivingEntity e) {
		return new PopGreenProjectile(w, e, weightScale);
	}

}
