package xyz.pixelatedw.mineminenomi.abilities.doru;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import xyz.pixelatedw.mineminenomi.entities.mobs.misc.WaxCloneEntity;
import xyz.pixelatedw.mineminenomi.init.ModItems;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;

public class DoruDoruNoYakataAbility extends Ability
{

	public static final DoruDoruNoYakataAbility INSTANCE = new DoruDoruNoYakataAbility();

	public DoruDoruNoYakataAbility()
	{
		super("Doru Doru no Yakata", AbilityCategory.DEVIL_FRUIT);

		this.setMaxCooldown(40);
		this.setDescription("The user creates a few wax copies of himself.");

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		for (int i = 0; i < 7; i++)
		{
			int offsetX = (int) WyHelper.randomWithRange(-2, 2);
			int offsetZ = (int) WyHelper.randomWithRange(-2, 2);
			WaxCloneEntity clone = new WaxCloneEntity(player.world);
			clone.setPositionAndRotation(player.posX + offsetX, player.posY, player.posZ + offsetZ, 180, 0);
			clone.setOwner(player.getUniqueID());
			if (player.inventory.hasItemStack(new ItemStack(ModItems.COLOR_PALETTE)))
				clone.setTextured();
			player.world.addEntity(clone);
		}

		return true;
	}
}
