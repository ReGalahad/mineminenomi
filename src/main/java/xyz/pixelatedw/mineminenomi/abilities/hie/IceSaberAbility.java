package xyz.pixelatedw.mineminenomi.abilities.hie;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.IParallelContinuousAbility;
import xyz.pixelatedw.wypi.abilities.ItemAbility;

public class IceSaberAbility extends ItemAbility implements IParallelContinuousAbility
{
	public static final Ability INSTANCE = new IceSaberAbility();
	
	public IceSaberAbility()
	{
		super("Ice Saber", AbilityCategory.DEVIL_FRUIT);
		this.setDescription("Creates a sharp blade made of solid ice.");	
	}

	@Override
	public boolean canBeActive(PlayerEntity player)
	{
		IDevilFruit devilFruitProps = DevilFruitCapability.get(player);
		return devilFruitProps.getDevilFruit().equalsIgnoreCase("hie_hie") ;
	}

	@Override
	public ItemStack getItemStack()
	{
		return new ItemStack(ModWeapons.ICE_SABER);
	}
}
