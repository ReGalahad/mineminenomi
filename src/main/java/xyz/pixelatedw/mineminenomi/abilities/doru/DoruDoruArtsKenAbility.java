package xyz.pixelatedw.mineminenomi.abilities.doru;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import xyz.pixelatedw.mineminenomi.api.abilities.IParallelContinuousAbility;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.ItemAbility;

public class DoruDoruArtsKenAbility extends ItemAbility implements IParallelContinuousAbility
{
	public static final Ability INSTANCE = new DoruDoruArtsKenAbility();
	
	public DoruDoruArtsKenAbility()
	{
		super("Doru Doru Arts: Ken", AbilityCategory.DEVIL_FRUIT);
		this.setDescription("The user uses hardened wax to create a sword.");	
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
		return new ItemStack(ModWeapons.DORU_DORU_ARTS_KEN);
	}
}
