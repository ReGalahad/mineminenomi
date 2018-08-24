package xyz.pixelatedw.MineMineNoMi3;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;

public class DevilFruitsHelper
{ 
	public static String[][] zoanModels = new String[][]
	{
		{"ushiushibison", "bison"},
		{"toritoriphoenix", "phoenix"}
	};
	
	public static Block[] replaceableBlocks = new Block[] { Blocks.air, Blocks.tallgrass, Blocks.snow_layer, Blocks.red_flower, Blocks.yellow_flower, Blocks.water, Blocks.flowing_water, Blocks.lava, 
			Blocks.flowing_lava, Blocks.waterlily, Blocks.redstone_wire, Blocks.double_plant, Blocks.wheat, Blocks.carrots, Blocks.carpet, Blocks.cake, Blocks.sapling, Blocks.deadbush, Blocks.web,
			Blocks.wooden_pressure_plate, Blocks.stone_pressure_plate, Blocks.light_weighted_pressure_plate, Blocks.heavy_weighted_pressure_plate, Blocks.carrots, Blocks.carpet, Blocks.vine,
			ListMisc.Poison, ListMisc.DemonPoison, Blocks.torch, Blocks.redstone_torch};
	
	public static boolean canReplaceBlock(Block b)
	{
		for(Block blk : replaceableBlocks)
		{
			if(b == blk)
				return true;
		}
		
		return false;
	}
	
	public static void placeIfCanReplaceBlock(World world, int posX, int posY, int posZ, Block toPlace)
	{
		Block b = world.getBlock((int)posX, (int)posY, (int)posZ);
		
		for(Block blk : replaceableBlocks)
		{
			if(b == blk)
			{
				world.setBlock(posX, posY, posZ, toPlace);
				break;
			}
		}
	}
	
	public static ItemStack getDevilFruitItem(String fullName)
	{
		String model = "";
		String fullModel = "";
		
		for(String[] s : zoanModels)
		{
			if(fullName.equals(s[0]))
			{
				model = s[1];
				fullModel = "model" + model;
				break;
			}
		}

		if(fullName.equals("yamidummy"))
			fullName = "yamiyami";
		
		return new ItemStack(GameRegistry.findItem(ID.PROJECT_ID, fullName.replace(model, "") + "nomi" + fullModel));
	}
	
	public static boolean isEntityInRoom(EntityLivingBase entity)
	{	
		for(int i = -20; i < 20; i++)
		for(int j = -20; j < 20; j++)
		for(int k = -20; k < 20; k++)
		{
			if(entity.worldObj.getBlock((int)entity.posX + i, (int)entity.posY + j, (int)entity.posZ + k) == ListMisc.OpeMid)
				return true;
		}
		
		return false;
	}
	
	public static int getRegenFromPoision(EntityLivingBase entity)
	{
		return entity.getActivePotionEffect(Potion.poison).getAmplifier() / 5;
	}
	
	public static boolean hasKairosekiItem(EntityPlayer player)
	{
		for(Item itm : Values.KAIROSEKI_ITEMS)
		{
			if(player.inventory.hasItem(itm))
			{
				return true;
			}
		}
		
		return false;
	}
}
