package xyz.pixelatedw.mineminenomi.events;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModEnchantments;
import xyz.pixelatedw.mineminenomi.init.ModItems;
import xyz.pixelatedw.wypi.APIConfig;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class EventsCrafting
{

	@SubscribeEvent
	public static void onAnvilUpdate(AnvilUpdateEvent event)
	{	
		if(ItemsHelper.isBow(event.getLeft()))
		{
			if(event.getRight().getItem() == ModBlocks.FLAME_DIAL.asItem() && event.getRight().getCount() >= 3 && 1 > EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, event.getLeft()))
			{
				event.setCost(1);
				event.setMaterialCost(3);
				event.setOutput(new ItemStack(event.getLeft().getItem()));
				EnchantmentHelper.setEnchantments(EnchantmentHelper.getEnchantments(event.getLeft()), event.getOutput());
				event.getOutput().addEnchantment(Enchantments.FLAME, 1);
			}
			else if(event.getRight().getItem() == ModBlocks.BREATH_DIAL.asItem() && event.getRight().getCount() >= 3 && 1 > EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, event.getLeft()))
			{
				event.setCost(1);
				event.setMaterialCost(3);
				event.setOutput(new ItemStack(event.getLeft().getItem()));
				EnchantmentHelper.setEnchantments(EnchantmentHelper.getEnchantments(event.getLeft()), event.getOutput());
				event.getOutput().addEnchantment(Enchantments.PUNCH, 1);
			}
			else if(event.getRight().getItem() == ModBlocks.EISEN_DIAL.asItem() && event.getRight().getCount() >= 3 && 1 > EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, event.getLeft()))
			{
				int level = event.getRight().getCount() / 3;
				
				if(level > 3)
					level = 3;
				
				event.setCost(1);
				event.setMaterialCost(3 * level);
				event.setOutput(new ItemStack(event.getLeft().getItem()));
				EnchantmentHelper.setEnchantments(EnchantmentHelper.getEnchantments(event.getLeft()), event.getOutput());
				event.getOutput().addEnchantment(Enchantments.POWER, level);
			}
		}
		else if(ItemsHelper.isSword(event.getLeft()))
		{
			if(event.getRight().getItem() == ModItems.KAIROSEKI && event.getRight().getCount() >= 10 && 1 > EnchantmentHelper.getEnchantmentLevel(ModEnchantments.KAIROSEKI, event.getLeft()))
			{
				event.setCost(1);
				event.setMaterialCost(10);
				event.setOutput(new ItemStack(event.getLeft().getItem()));
				EnchantmentHelper.setEnchantments(EnchantmentHelper.getEnchantments(event.getLeft()), event.getOutput());
				event.getOutput().addEnchantment(ModEnchantments.KAIROSEKI, 1);
			}
			else if(event.getRight().getItem() == ModItems.BLACK_METAL  && event.getRight().getCount() >= 5 && 1 > EnchantmentHelper.getEnchantmentLevel(Enchantments.SHARPNESS, event.getLeft()))
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
			else if(event.getRight().getItem() == ModBlocks.FLAME_DIAL.asItem() && event.getRight().getCount() >= 3 && 1 > EnchantmentHelper.getEnchantmentLevel(Enchantments.FIRE_ASPECT, event.getLeft()))
			{
				event.setCost(1);
				event.setMaterialCost(3);
				event.setOutput(new ItemStack(event.getLeft().getItem()));
				EnchantmentHelper.setEnchantments(EnchantmentHelper.getEnchantments(event.getLeft()), event.getOutput());
				event.getOutput().addEnchantment(Enchantments.FIRE_ASPECT, 1);
			}
			else if(event.getRight().getItem() == ModBlocks.EISEN_DIAL.asItem()  && event.getRight().getCount() >= 3 && 1 > EnchantmentHelper.getEnchantmentLevel(Enchantments.SHARPNESS, event.getLeft()))
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
			else if(event.getRight().getItem() == ModBlocks.FLASH_DIAL.asItem()  && event.getRight().getCount() >= 3 && 1 > EnchantmentHelper.getEnchantmentLevel(ModEnchantments.DIAL_FLASH, event.getLeft()))
			{
				event.setCost(1);
				event.setMaterialCost(3);
				event.setOutput(new ItemStack(event.getLeft().getItem()));
				EnchantmentHelper.setEnchantments(EnchantmentHelper.getEnchantments(event.getLeft()), event.getOutput());
				event.getOutput().addEnchantment(ModEnchantments.DIAL_FLASH, 1);
			}
			else if(event.getRight().getItem() == ModBlocks.IMPACT_DIAL.asItem()  && event.getRight().getCount() >= 3 && 1 > EnchantmentHelper.getEnchantmentLevel(ModEnchantments.DIAL_IMPACT, event.getLeft()))
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
			else if(event.getRight().getItem() == ModBlocks.BREATH_DIAL.asItem()  && event.getRight().getCount() >= 3 && 1 > EnchantmentHelper.getEnchantmentLevel(Enchantments.KNOCKBACK, event.getLeft()))
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
