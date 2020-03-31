package xyz.pixelatedw.mineminenomi.abilities.noro;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import xyz.pixelatedw.mineminenomi.api.abilities.IParallelContinuousAbility;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.ItemAbility;

public class NoroNoroBeamSwordAbility extends ItemAbility implements IParallelContinuousAbility
{
	public static final Ability INSTANCE = new NoroNoroBeamSwordAbility();
	
	public NoroNoroBeamSwordAbility()
	{
		super("Noro Noro Beam Sword", AbilityCategory.DEVIL_FRUIT);
		this.setDescription("Focuses photons inside a hilt to create a sword");	
	}

	@Override
	public boolean canBeActive(PlayerEntity player)
	{
		IDevilFruit devilFruitProps = DevilFruitCapability.get(player);
		return devilFruitProps.getDevilFruit().equalsIgnoreCase("noro_noro") ;
	}

	@Override
	public ItemStack getItemStack()
	{
		return new ItemStack(ModWeapons.NORO_NORO_BEAM_SWORD);
	}
}
