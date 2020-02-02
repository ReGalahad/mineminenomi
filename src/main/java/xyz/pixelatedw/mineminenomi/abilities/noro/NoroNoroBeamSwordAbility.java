package xyz.pixelatedw.mineminenomi.abilities.noro;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.abilities.ItemAbility;
import xyz.pixelatedw.mineminenomi.api.data.ability.AbilityDataCapability.Category;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;

public class NoroNoroBeamSwordAbility extends ItemAbility
{
	public static final Ability INSTANCE = new NoroNoroBeamSwordAbility();
	
	public NoroNoroBeamSwordAbility()
	{
		super("Noro Noro Beam Sword", Category.DEVIL_FRUIT);
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
