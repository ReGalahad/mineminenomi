package xyz.pixelatedw.mineminenomi.api.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;

public class DevilFruitHelper
{
	public static List<AkumaNoMiItem> tier1Fruits = new ArrayList<AkumaNoMiItem>();
	public static List<AkumaNoMiItem> tier2Fruits = new ArrayList<AkumaNoMiItem>();
	public static List<AkumaNoMiItem> tier3Fruits = new ArrayList<AkumaNoMiItem>();
	
	private static String[][] zoanModels = new String[][]
		{
				{
						"ushi_ushi_bison", "bison"
				},
				{
						"tori_tori_phoenix", "phoenix"
				},
				{
						"ushi_ushi_giraffe", "giraffe"
				},
		};
		
	public static boolean oneFruitPerWorldCheck(World world, @Nullable AkumaNoMiItem devilFruit)
	{
		if(devilFruit == null)
			return false;
		
		if (!CommonConfig.instance.hasOneFruitPerWorldSimpleLogic())
			return true;
			
		boolean isAvailable = true;

		ExtendedWorldData worldProps = ExtendedWorldData.get(world);
		int chanceForNewFruit = 0;

		String fruitName = getDevilFruitKey(devilFruit);

		while (DevilFruitHelper.isDevilFruitInWorld(world, fruitName))
		{
			final AkumaNoMiItem inContextFruit = devilFruit;
			DevilFruitHelper.tier1Fruits.removeIf(x -> x == inContextFruit);
			DevilFruitHelper.tier2Fruits.removeIf(x -> x == inContextFruit);
			DevilFruitHelper.tier3Fruits.removeIf(x -> x == inContextFruit);

			if (chanceForNewFruit >= 10)
			{
				isAvailable = false;
				break;
			}
			devilFruit = rouletteDevilFruits(1);
			chanceForNewFruit++;
		}

		if (isAvailable)
		{
			worldProps.addDevilFruitInWorld(devilFruit);
			return true;
		}

		return false;
	}
	
	@Nullable
	public static AkumaNoMiItem rouletteDevilFruits(int tier)
	{
		Random rand = new Random();

		if (rand.nextInt(100) + rand.nextDouble() <= 98)
		{
			if (tier == 1)
			{
				if (rand.nextInt(100) + rand.nextDouble() < 10)
				{
					if (DevilFruitHelper.tier2Fruits.size() > 0)
						return DevilFruitHelper.tier2Fruits.get(rand.nextInt(DevilFruitHelper.tier2Fruits.size()));
				}
				else
				{
					if (DevilFruitHelper.tier1Fruits.size() > 0)
						return DevilFruitHelper.tier1Fruits.get(rand.nextInt(DevilFruitHelper.tier1Fruits.size()));
				}
			}
			else if (tier == 2)
			{
				if (rand.nextInt(100) + rand.nextDouble() < 10)
				{
					if (DevilFruitHelper.tier3Fruits.size() > 0)
						return DevilFruitHelper.tier3Fruits.get(rand.nextInt(DevilFruitHelper.tier3Fruits.size()));
				}
				else
				{
					if (DevilFruitHelper.tier2Fruits.size() > 0)
						return DevilFruitHelper.tier2Fruits.get(rand.nextInt(DevilFruitHelper.tier2Fruits.size()));
				}
			}
			else if (tier == 3)
			{
				if (DevilFruitHelper.tier3Fruits.size() > 0)
					return DevilFruitHelper.tier3Fruits.get(rand.nextInt(DevilFruitHelper.tier3Fruits.size()));
			}
		}

		return null;
	}
	
	public static boolean hasDevilFruit(LivingEntity player, AkumaNoMiItem df)
	{
		IDevilFruit props = DevilFruitCapability.get(player);
		String fruitName = WyHelper.getResourceName(df.getDevilFruitName()).replace("_no_mi", "").replace("_model", "");
		boolean check = props.getDevilFruit().equalsIgnoreCase(fruitName);
		
		if(!check && fruitName.equalsIgnoreCase("yami_yami"))
			check = props.hasYamiPower();
		
		return check;
	}
		
	public static boolean isDevilFruitInWorld(World world, String devilFruitName)
	{
		ExtendedWorldData worldData = ExtendedWorldData.get(world);

		if (worldData.isDevilFruitInWorld(devilFruitName))
			return true;

		return false;
	}

	public static String getDevilFruitKey(AkumaNoMiItem item)
	{
		return item.getTranslationKey().substring(("item." + APIConfig.PROJECT_ID + ".").length()).replace("_no_mi", "").replace(":", "").replace(".", "").replace(",", "").replace("model_", "");
	}
	
	public static ItemStack getDevilFruitItem(String fullName)
	{
		String model = "";
		String fullModel = "";

		for (String[] s : zoanModels)
		{
			if (fullName.equals(s[0]))
			{
				model = s[1];
				fullModel = "_model_" + model;
				break;
			}
		}

		if (fullName.equals("yamidummy"))
			fullName = "yami_yami";

		String finalName = (!WyHelper.isNullOrEmpty(model) ? fullName.replace("_" + model, "") : fullName) + "_no_mi" + fullModel;

		return new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(APIConfig.PROJECT_ID, finalName)));
	}
}
