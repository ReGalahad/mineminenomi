package xyz.pixelatedw.mineminenomi.abilities.pika;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import xyz.pixelatedw.mineminenomi.api.abilities.IParallelContinuousAbility;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.ItemAbility;

public class AmaNoMurakumoAbility extends ItemAbility implements IParallelContinuousAbility
{
	public static final Ability INSTANCE = new AmaNoMurakumoAbility();
	
	public AmaNoMurakumoAbility()
	{
		super("Ama no Murakumo", AbilityCategory.DEVIL_FRUIT);
		this.setDescription("Focuses light in the user's hand to create a sword.");	
	}

	@Override
	public boolean canBeActive(PlayerEntity player)
	{
		IDevilFruit devilFruitProps = DevilFruitCapability.get(player);
		return devilFruitProps.getDevilFruit().equalsIgnoreCase("pika_pika") ;
	}

	@Override
	public ItemStack getItemStack()
	{
		return new ItemStack(ModWeapons.AMA_NO_MURAKUMO);
	}
}
