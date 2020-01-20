package xyz.pixelatedw.mineminenomi.abilities.hie;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.abilities.PassiveAbility;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;

public class IceSaberAbility extends PassiveAbility
{
	public static final Ability INSTANCE = new IceSaberAbility();
	
	public IceSaberAbility()
	{
		super("Ice Saber", Category.DEVIL_FRUIT);
		this.setDescription("Creates a sharp blade made of solid ice.");
		
		this.onStartPassiveEvent = this::onStartPassiveEvent;
		this.onEndPassiveEvent = this::onEndPassiveEvent;
	}

	private void onStartPassiveEvent(PlayerEntity player)
	{
		if (player.getHeldItemMainhand().isEmpty())
			player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(ModWeapons.ICE_SABER));
		else
		{
			WyHelper.sendMsgToPlayer(player, "Cannot equip " + this.getName() + " while holding another item in hand !");
			this.onEndPassiveEvent.onEndPassive(player);
		}
	}
	
	private void onEndPassiveEvent(PlayerEntity player)
	{
		player.inventory.deleteStack(new ItemStack(ModWeapons.ICE_SABER));
	}
}
