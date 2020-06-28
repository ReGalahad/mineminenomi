package xyz.pixelatedw.mineminenomi.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModItems;
import xyz.pixelatedw.wypi.WyHelper;

public class AkumaNoMiBoxItem extends Item
{
	private int tier;

	public AkumaNoMiBoxItem(int tier)
	{
		super(new Properties().group(ModCreativeTabs.MISC).maxStackSize(1));
		this.tier = tier;
	}

	public int getKeySlot(PlayerEntity player)
	{
		if (!player.inventory.hasItemStack(new ItemStack(ModItems.KEY)))
		{
			WyHelper.sendMsgToPlayer(player, new TranslationTextComponent(ModI18n.ITEM_MESSAGE_NEED_KEY).getFormattedText());
			return -1;
		}

		for (int i = 0; i < player.inventory.getSizeInventory(); ++i)
		{
			ItemStack stack = player.inventory.getStackInSlot(i);
			if (stack != null && !stack.isEmpty() && stack.getItem() == ModItems.KEY)
				return i;
		}

		return -1;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
	{
		if (!world.isRemote)
		{
			int keySlot = this.getKeySlot(player);

			if (keySlot < 0)
				return new ActionResult<>(ActionResultType.FAIL, player.getHeldItem(hand));

			ItemStack itemStack = player.getHeldItemMainhand();

			player.inventory.decrStackSize(keySlot, 1);
			player.inventory.deleteStack(itemStack);

			AkumaNoMiItem randomFruit = DevilFruitHelper.rouletteDevilFruits(this.tier);
			boolean isAvailable = true && DevilFruitHelper.oneFruitPerWorldCheck(world, randomFruit);

			if (isAvailable)
			{
				player.inventory.addItemStackToInventory(new ItemStack(randomFruit));
				return new ActionResult<>(ActionResultType.SUCCESS, player.getHeldItem(hand));
			}
			else
			{
				player.inventory.deleteStack(itemStack);
				return new ActionResult<>(ActionResultType.SUCCESS, player.getHeldItem(hand));
			}
		}

		return new ActionResult<>(ActionResultType.SUCCESS, player.getHeldItem(hand));
	}


}
