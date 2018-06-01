package xyz.pixelatedw.MineMineNoMi3;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;

public class Values 
{
	public static List<Item> abilities = new ArrayList();
	public static List<Item> devilfruits = new ArrayList();
	public static List<Item> logias = new ArrayList();
	public static List<Item> miscItems = new ArrayList();
	public static List<Block> miscBlocks = new ArrayList();
	public static List<Object[]> customDFs = new ArrayList();
	
	public static final int MAX_DORIKI = 10000;
	public static final int MAX_ULTRACOLA = 10;
	public static final int MAX_GENERAL = 999999999; //Used by Bounty, Reputation, Belly & Extol
	public static final int MAX_CREW = 50;
	
	public static final Item[] KAIROSEKI_ITEMS = new Item[] {ListMisc.Kairoseki, ListMisc.KairosekiBullets, ListMisc.DenseKairoseki};
	 
	//public static Block[] BANNED_BLOCKS = new Block[] {Blocks.bedrock, ListMisc.Ope, ListMisc.OpeMid};	

	//ReflectionHelper
}
