package xyz.pixelatedw.mineminenomi.events;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModItems;
import xyz.pixelatedw.mineminenomi.values.ModValuesEnv;

@Mod.EventBusSubscriber(modid = ModValuesEnv.PROJECT_ID)
public class EventsCrafting
{

	@SubscribeEvent
	public static void onAnvilUpdate(AnvilUpdateEvent event)
	{	
		if(ItemsHelper.isSword(event.getLeft()))
		{
			if(event.getRight().getItem() == ModItems.kairoseki && event.getRight().getCount() >= 10)
			{
				event.setCost(1);
				event.setMaterialCost(10);
				event.setOutput(new ItemStack(event.getLeft().getItem()));
				EnchantmentHelper.setEnchantments(EnchantmentHelper.getEnchantments(event.getLeft()), event.getOutput());
				event.getOutput().addEnchantment(ModEffects.kairoseki, 1);
			}
			else if(event.getRight().getItem() == ModItems.blackMetal  && event.getRight().getCount() >= 5)
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
			else if(event.getRight().getItem() == ModBlocks.flameDialBlock.asItem() && event.getRight().getCount() >= 3)
			{
				event.setCost(1);
				event.setMaterialCost(3);
				event.setOutput(new ItemStack(event.getLeft().getItem()));
				EnchantmentHelper.setEnchantments(EnchantmentHelper.getEnchantments(event.getLeft()), event.getOutput());
				event.getOutput().addEnchantment(Enchantments.FIRE_ASPECT, 1);
			}
			else if(event.getRight().getItem() == ModBlocks.eisenDialBlock.asItem()  && event.getRight().getCount() >= 3)
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
			else if(event.getRight().getItem() == ModBlocks.flashDialBlock.asItem()  && event.getRight().getCount() >= 3)
			{
				event.setCost(1);
				event.setMaterialCost(3);
				event.setOutput(new ItemStack(event.getLeft().getItem()));
				EnchantmentHelper.setEnchantments(EnchantmentHelper.getEnchantments(event.getLeft()), event.getOutput());
				event.getOutput().addEnchantment(ModEffects.dialFlash, 1);
			}
			else if(event.getRight().getItem() == ModBlocks.impactDialBlock.asItem()  && event.getRight().getCount() >= 3)
			{
				int level = event.getRight().getCount() / 3;
				
				if(level > 3)
					level = 3;
				
				event.setCost(1);
				event.setMaterialCost(3 * level);
				event.setOutput(new ItemStack(event.getLeft().getItem()));
				EnchantmentHelper.setEnchantments(EnchantmentHelper.getEnchantments(event.getLeft()), event.getOutput());
				event.getOutput().addEnchantment(ModEffects.dialImpact, level);
			}
			else if(event.getRight().getItem() == ModBlocks.breathDialBlock.asItem()  && event.getRight().getCount() >= 3)
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
