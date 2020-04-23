package xyz.pixelatedw.mineminenomi.abilities.doru;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.init.ModItems;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.IParallelContinuousAbility;
import xyz.pixelatedw.wypi.abilities.ItemAbility;

public class DoruDoruArtsPickaxeAbility extends ItemAbility implements IParallelContinuousAbility{

	public static final Ability INSTANCE = new DoruDoruArtsPickaxeAbility();
	
	public DoruDoruArtsPickaxeAbility()
	{
		super("Doru Doru Arts: Pickaxe", AbilityCategory.DEVIL_FRUIT);
		this.setDescription("The user uses hardened wax to create a pickaxe.");	
	}

	@Override
	public boolean canBeActive(PlayerEntity player)
	{
		IDevilFruit devilFruitProps = DevilFruitCapability.get(player);
		return devilFruitProps.getDevilFruit().equalsIgnoreCase("doru_doru") ;
	}

	@Override
	public ItemStack getItemStack()
	{
		return new ItemStack(ModItems.DORU_PICKAXE);
	}
}
