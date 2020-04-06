package xyz.pixelatedw.mineminenomi.abilities.yuki;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.IParallelContinuousAbility;
import xyz.pixelatedw.wypi.abilities.ItemAbility;

public class TabiraYukiAbility extends ItemAbility implements IParallelContinuousAbility
{
	public static final TabiraYukiAbility INSTANCE = new TabiraYukiAbility();

	public TabiraYukiAbility()
	{
		super("Tabira Yuki", AbilityCategory.DEVIL_FRUIT);
		this.setDescription("The user creates a sword made of solid hardened snow.");
	}

	@Override
	public boolean canBeActive(PlayerEntity player)
	{
		IDevilFruit devilFruitProps = DevilFruitCapability.get(player);
		return devilFruitProps.getDevilFruit().equalsIgnoreCase("yuki_yuki");
	}

	@Override
	public ItemStack getItemStack()
	{
		return new ItemStack(ModWeapons.TABIRA_YUKI);
	}
}
