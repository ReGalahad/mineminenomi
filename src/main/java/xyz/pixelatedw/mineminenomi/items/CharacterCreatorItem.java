package xyz.pixelatedw.mineminenomi.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.packets.server.SOpenCharacterCreatorScreenPacket;

public class CharacterCreatorItem extends Item
{
	
    public CharacterCreatorItem()
	{
		super(new Properties().group(ModCreativeTabs.MISC).maxStackSize(1));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
    {
		if(!world.isRemote)
			ModNetwork.sendTo(new SOpenCharacterCreatorScreenPacket(), (ServerPlayerEntity)player);
		return new ActionResult<>(ActionResultType.SUCCESS, player.getHeldItem(hand));
	}

}
