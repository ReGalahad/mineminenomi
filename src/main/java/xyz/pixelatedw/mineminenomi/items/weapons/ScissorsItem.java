package xyz.pixelatedw.mineminenomi.items.weapons;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModItems;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;

public class ScissorsItem extends CoreSwordItem
{

	public ScissorsItem()
	{
		super(6, 500);
	}
	
	@Override
	public boolean hitEntity(ItemStack itemStack, LivingEntity target, LivingEntity attacker)
	{
		if(!(attacker instanceof PlayerEntity))
			return super.hitEntity(itemStack, target, attacker);
		
		IDevilFruit devilFruitProps = DevilFruitCapability.get(attacker);
		IEntityStats targetProps = EntityStatsCapability.get(target);

		if (!devilFruitProps.getDevilFruit().equalsIgnoreCase("kage_kage") || itemStack == null || itemStack.getItem() != ModWeapons.SCISSORS || !targetProps.hasShadow())
			return super.hitEntity(itemStack, target, attacker);

		targetProps.setShadow(false);
		((PlayerEntity)attacker).inventory.addItemStackToInventory(new ItemStack(ModItems.SHADOW));
		
		return super.hitEntity(itemStack, target, attacker);
	}

}
