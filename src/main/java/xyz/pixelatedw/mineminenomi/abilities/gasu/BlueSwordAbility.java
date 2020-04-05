package xyz.pixelatedw.mineminenomi.abilities.gasu;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.IParallelContinuousAbility;
import xyz.pixelatedw.wypi.abilities.ItemAbility;

public class BlueSwordAbility extends ItemAbility implements IParallelContinuousAbility
{
	public static final Ability INSTANCE = new BlueSwordAbility();
	
	public BlueSwordAbility()
	{
		super("Blue Sword", AbilityCategory.DEVIL_FRUIT);
		this.setDescription("The user fills a hilt with lamable gas, them sets it on fire to create a sword.");	
	}

	@Override
	public boolean canBeActive(PlayerEntity player)
	{
		IDevilFruit devilFruitProps = DevilFruitCapability.get(player);
		return devilFruitProps.getDevilFruit().equalsIgnoreCase("gasu_gasu") ;
	}

	@Override
	public ItemStack getItemStack()
	{
		return new ItemStack(ModWeapons.BLUE_SWORD);
	}
}
