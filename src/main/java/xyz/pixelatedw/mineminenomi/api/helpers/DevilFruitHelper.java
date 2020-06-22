package xyz.pixelatedw.mineminenomi.api.helpers;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;

public class DevilFruitHelper
{
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
		
	public static boolean isDevilFruitInWorld(World world, String devilFruitName)
	{
		ExtendedWorldData worldData = ExtendedWorldData.get(world);

		if (worldData.isDevilFruitInWorld(devilFruitName))
			return true;

		return false;
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
