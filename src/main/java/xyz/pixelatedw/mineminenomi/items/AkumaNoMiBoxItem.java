package xyz.pixelatedw.mineminenomi.items;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.EnumFruitType;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.debug.WyDebug;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.helpers.DevilFruitsHelper;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
import xyz.pixelatedw.mineminenomi.init.ModItems;
import xyz.pixelatedw.mineminenomi.init.ModValues;

public class AkumaNoMiBoxItem extends Item
{
	private int tier;

	private List<AkumaNoMiItem> tier1Fruits = new ArrayList<AkumaNoMiItem>();
	private List<AkumaNoMiItem> tier2Fruits = new ArrayList<AkumaNoMiItem>();
	private List<AkumaNoMiItem> tier3Fruits = new ArrayList<AkumaNoMiItem>();

	public AkumaNoMiBoxItem(int tier)
	{
		super(new Properties().group(ModCreativeTabs.MISC).maxStackSize(1));
		this.tier = tier;

		for (AkumaNoMiItem df : ModValues.devilfruits)
		{
			double score = 0;

			double typeModifier = 1;
			if (df.getType() == EnumFruitType.PARAMECIA)
				typeModifier = 0.5;
			else if (df.getType() == EnumFruitType.LOGIA)
				typeModifier = 1.4;
			else if (df.getType() == EnumFruitType.ZOAN)
				typeModifier = 1.1;
			else if (df.getType() == EnumFruitType.MYTHICAL_ZOAN || df.getType() == EnumFruitType.ANCIENT_ZOAN)
				typeModifier = 1.5;

			double totalDamage = 0;
			double totalCooldown = 0;
			double totalPower = 0;
			for (Ability a : df.abilities)
			{
				totalCooldown += 1;
				totalDamage += 1;

				totalPower += (totalCooldown + totalDamage) / 2;
			}

			totalPower *= typeModifier;

			if (df.getType() == EnumFruitType.ANCIENT_ZOAN || df.getType() == EnumFruitType.MYTHICAL_ZOAN || WyHelper.getResourceName(new TranslationTextComponent(df.getTranslationKey()).getFormattedText()).equalsIgnoreCase("guraguranomi"))
			{
				this.tier3Fruits.add(df);
			}
			else if (df.getType() == EnumFruitType.PARAMECIA)
			{
				if (totalPower < 500)
					this.tier1Fruits.add(df);
				else
					this.tier2Fruits.add(df);
			}
			else
			{
				if (totalPower < 800)
					this.tier2Fruits.add(df);
				else
					this.tier3Fruits.add(df);
			}
		}

		if (WyDebug.isDebug())
		{
			if (tier == 1)
			{
				for (AkumaNoMiItem df : this.tier1Fruits)
					System.out.print("\"" + new ItemStack(df).getDisplayName() + "\", ");
				System.out.println();
			}
			else if (tier == 2)
			{
				for (AkumaNoMiItem df : this.tier2Fruits)
					System.out.print("\"" + new ItemStack(df).getDisplayName() + "\", ");
				System.out.println();
			}
			else if (tier == 3)
			{
				for (AkumaNoMiItem df : this.tier3Fruits)
					System.out.print("\"" + new ItemStack(df).getDisplayName() + "\", ");
				System.out.println();
			}
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
	{
		if (!world.isRemote)
		{
			ItemStack itemStack = player.getHeldItemMainhand();

			if(!player.inventory.hasItemStack(new ItemStack(ModItems.KEY)))
			{
				WyHelper.sendMsgToPlayer(player, "You need a key !");
				return new ActionResult<>(ActionResultType.FAIL, player.getHeldItem(hand));
			}
			
			int i = player.inventory.getSlotFor(new ItemStack(ModItems.KEY));
			player.inventory.decrStackSize(i, 1);
			
			player.inventory.deleteStack(itemStack);
			AkumaNoMiItem randomFruit = roulette();
			boolean isAvailable = true;

			if (CommonConfig.instance.isOneFruitPerWorldEnabled())
			{
				ExtendedWorldData worldProps = ExtendedWorldData.get(world);
				int chanceForNewFruit = 0;
				while (DevilFruitsHelper.isDevilFruitInWorld(world, randomFruit))
				{
					final AkumaNoMiItem inContextFruit = randomFruit;
					this.tier1Fruits.removeIf(x -> x == inContextFruit);
					this.tier2Fruits.removeIf(x -> x == inContextFruit);
					this.tier3Fruits.removeIf(x -> x == inContextFruit);

					if (chanceForNewFruit >= 10)
					{
						isAvailable = false;
						break;
					}
					randomFruit = roulette();
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

		return new ActionResult<>(ActionResultType.FAIL, player.getHeldItem(hand));
	}

	private AkumaNoMiItem roulette()
	{
		Random rand = new Random();

		if (rand.nextInt(100) + rand.nextDouble() <= 98)
		{
			if (tier == 1)
			{
				if (rand.nextInt(100) + rand.nextDouble() < 10)
				{
					if (tier2Fruits.size() > 0)
						return tier2Fruits.get(rand.nextInt(tier2Fruits.size()));
				}
				else
				{
					if (tier1Fruits.size() > 0)
						return tier1Fruits.get(rand.nextInt(tier1Fruits.size()));
				}
			}
			else if (tier == 2)
			{
				if (rand.nextInt(100) + rand.nextDouble() < 10)
				{
					if (tier3Fruits.size() > 0)
						return tier3Fruits.get(rand.nextInt(tier3Fruits.size()));
				}
				else
				{
					if (tier2Fruits.size() > 0)
						return tier2Fruits.get(rand.nextInt(tier2Fruits.size()));
				}
			}
			else if (tier == 3)
			{
				if (tier3Fruits.size() > 0)
					return tier3Fruits.get(rand.nextInt(tier3Fruits.size()));
			}
		}

		return null;
	}

}
