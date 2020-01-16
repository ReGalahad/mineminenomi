package xyz.pixelatedw.mineminenomi.events;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.Env;
import xyz.pixelatedw.mineminenomi.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModEnchantments;
import xyz.pixelatedw.mineminenomi.init.ModItems;

@Mod.EventBusSubscriber(modid = Env.PROJECT_ID)
public class EventsCrafting
{

	@SubscribeEvent
	public static void onAnvilUpdate(AnvilUpdateEvent event)
	{	
		if(ItemsHelper.isSword(event.getLeft()))
		{
			if(event.getRight().getItem() == ModItems.KAIROSEKI && event.getRight().getCount() >= 10)
			{
				event.setCost(1);
				event.setMaterialCost(10);
				event.setOutput(new ItemStack(event.getLeft().getItem()));
				EnchantmentHelper.setEnchantments(EnchantmentHelper.getEnchantments(event.getLeft()), event.getOutput());
				event.getOutput().addEnchantment(ModEnchantments.KAIROSEKI, 1);
			}
			else if(event.getRight().getItem() == ModItems.BLACK_METAL  && event.getRight().getCount() >= 5)
			{
				int level = event.getRight().getCount() / 5;
				
				if(level > 3)
					level = 3;
				
				event.setCost(1);
				event.setMaterialCost(5 * level);
				event.setOutput(new ItemStack(event.getLeft().getItem()));
				EnchantmentHelper.setEnchantments(EnchantmentHelper.getEnchantments(event.getLeft()), event.getOutput());
				event.getOutput().addEnchantment(Enchantments.SHARPNESS, 3 * level);
			}
			else if(event.getRight().getItem() == ModBlocks.FLAME_DIAL.asItem() && event.getRight().getCount() >= 3)
			{
				event.setCost(1);
				event.setMaterialCost(3);
				event.setOutput(new ItemStack(event.getLeft().getItem()));
				EnchantmentHelper.setEnchantments(EnchantmentHelper.getEnchantments(event.getLeft()), event.getOutput());
				event.getOutput().addEnchantment(Enchantments.FIRE_ASPECT, 1);
			}
			else if(event.getRight().getItem() == ModBlocks.EISEN_DIAL.asItem()  && event.getRight().getCount() >= 3)
			{
				int level = event.getRight().getCount() / 3;
				
				if(level > 3)
					level = 3;
				
				event.setCost(1);
				event.setMaterialCost(3 * level);
				event.setOutput(new ItemStack(event.getLeft().getItem()));
				EnchantmentHelper.setEnchantments(EnchantmentHelper.getEnchantments(event.getLeft()), event.getOutput());
				event.getOutput().addEnchantment(Enchantments.SHARPNESS, level);
			}
			else if(event.getRight().getItem() == ModBlocks.FLASH_DIAL.asItem()  && event.getRight().getCount() >= 3)
			{
				event.setCost(1);
				event.setMaterialCost(3);
				event.setOutput(new ItemStack(event.getLeft().getItem()));
				EnchantmentHelper.setEnchantments(EnchantmentHelper.getEnchantments(event.getLeft()), event.getOutput());
				event.getOutput().addEnchantment(ModEnchantments.DIAL_FLASH, 1);
			}
			else if(event.getRight().getItem() == ModBlocks.IMPACT_DIAL.asItem()  && event.getRight().getCount() >= 3)
			{
				int level = event.getRight().getCount() / 3;
				
				if(level > 3)
					level = 3;
				
				event.setCost(1);
				event.setMaterialCost(3 * level);
				event.setOutput(new ItemStack(event.getLeft().getItem()));
				EnchantmentHelper.setEnchantments(EnchantmentHelper.getEnchantments(event.getLeft()), event.getOutput());
				event.getOutput().addEnchantment(ModEnchantments.DIAL_IMPACT, level);
			}
			else if(event.getRight().getItem() == ModBlocks.BREATH_DIAL.asItem()  && event.getRight().getCount() >= 3)
			{
				int level = event.getRight().getCount() / 3;
				
				if(level > 3)
					level = 3;
				
				event.setCost(1);
				event.setMaterialCost(3 * level);
				event.setOutput(new ItemStack(event.getLeft().getItem()));
				EnchantmentHelper.setEnchantments(EnchantmentHelper.getEnchantments(event.getLeft()), event.getOutput());
				event.getOutput().addEnchantment(Enchantments.KNOCKBACK, level);
			}
		}
	}
	
}
