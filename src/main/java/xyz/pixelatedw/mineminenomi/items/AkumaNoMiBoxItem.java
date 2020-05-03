package xyz.pixelatedw.mineminenomi.items;

import java.util.Random;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
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

			AkumaNoMiItem randomFruit = this.roulette();
			boolean isAvailable = true;

			if (CommonConfig.instance.isOneFruitPerWorldEnabled())
			{
				ExtendedWorldData worldProps = ExtendedWorldData.get(world);
				int chanceForNewFruit = 0;

				String fruitName = randomFruit.getTranslationKey().substring("item.mineminenomi.".length()).replace("_no_mi", "").replace(":", "").replace(".", "").replace(",", "").replace("model_", "");

				while (AbilityHelper.isDevilFruitInWorld(world, fruitName))
				{
					final AkumaNoMiItem inContextFruit = randomFruit;
					AbilityHelper.tier1Fruits.removeIf(x -> x == inContextFruit);
					AbilityHelper.tier2Fruits.removeIf(x -> x == inContextFruit);
					AbilityHelper.tier3Fruits.removeIf(x -> x == inContextFruit);

					if (chanceForNewFruit >= 10)
					{
						isAvailable = false;
						break;
					}
					randomFruit = this.roulette();
					chanceForNewFruit++;
				}

				if (isAvailable)
					worldProps.addDevilFruitInWorld(randomFruit);
			}

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

	private AkumaNoMiItem roulette()
	{
		Random rand = new Random();

		if (rand.nextInt(100) + rand.nextDouble() <= 98)
		{
			if (this.tier == 1)
			{
				if (rand.nextInt(100) + rand.nextDouble() < 10)
				{
					if (AbilityHelper.tier2Fruits.size() > 0)
						return AbilityHelper.tier2Fruits.get(rand.nextInt(AbilityHelper.tier2Fruits.size()));
				}
				else
				{
					if (AbilityHelper.tier1Fruits.size() > 0)
						return AbilityHelper.tier1Fruits.get(rand.nextInt(AbilityHelper.tier1Fruits.size()));
				}
			}
			else if (this.tier == 2)
			{
				if (rand.nextInt(100) + rand.nextDouble() < 10)
				{
					if (AbilityHelper.tier3Fruits.size() > 0)
						return AbilityHelper.tier3Fruits.get(rand.nextInt(AbilityHelper.tier3Fruits.size()));
				}
				else
				{
					if (AbilityHelper.tier2Fruits.size() > 0)
						return AbilityHelper.tier2Fruits.get(rand.nextInt(AbilityHelper.tier2Fruits.size()));
				}
			}
			else if (this.tier == 3)
			{
				if (AbilityHelper.tier3Fruits.size() > 0)
					return AbilityHelper.tier3Fruits.get(rand.nextInt(AbilityHelper.tier3Fruits.size()));
			}
		}

		return null;
	}
}
