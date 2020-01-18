package xyz.pixelatedw.mineminenomi.helpers;

import java.text.SimpleDateFormat;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.stream.Collectors;

import com.google.common.collect.Multimap;

import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModValues;

public class ItemsHelper
{
	private static String[] wantedPostersBackgrounds = new String[] 
			{
					"forest1", "forest2", 
					"jungle1", "jungle2",
					"hills1", "hills2", "hills3", 
					"plains1", "plains2", "plains3", 
					"taiga1", "taiga2",
			};
	
	public static void dropWantedPosters(World world, int posX, int posY, int posZ)
	{
    	ExtendedWorldData worldData = ExtendedWorldData.get(world);
    	
    	// Populating the list with wanted posters
    	List<Entry<String, Long>> bountiesInPackage = new ArrayList<Entry<String, Long>>();
    	
    	List<PlayerEntity> nearbyPlayers = WyHelper.getEntitiesNear(new BlockPos(posX, posY, posZ), world, 10);
    	
    	if(!nearbyPlayers.isEmpty())
    	{
    		nearbyPlayers.stream().filter(x -> 
    		{
    			return x instanceof PlayerEntity && EntityStatsCapability.get(x).isPirate() && worldData.getBounty(x.getName().getFormattedText()) != 0;
    		}).forEach(x -> 
    		{
    			SimpleEntry<String, Long> se = new SimpleEntry<String, Long>( x.getName().getFormattedText(), worldData.getBounty(x.getName().getFormattedText()) );
    			bountiesInPackage.add( se );
    		});
    	}
    	
    	if((5 + world.rand.nextInt(2)) - bountiesInPackage.size() > 0)
    		bountiesInPackage.addAll( worldData.getAllBounties().entrySet().stream().filter(x -> !bountiesInPackage.contains(x) ).limit((5 + world.rand.nextInt(2)) - bountiesInPackage.size()).collect(Collectors.toList()) );
    	    	
    	// Spawning the wanted posters
    	bountiesInPackage.stream().forEach(x -> 
    	{
    		ItemStack stack = new ItemStack(ModBlocks.WANTED_POSTER.asItem());
	    	stack.setTag(setWantedData(world, x.getKey(), x.getValue()));
	    	world.addEntity(new ItemEntity(world, posX, posY + 1, posZ, stack));
    	});
	}
	
	public static CompoundNBT setWantedData(World world, String id, long bounty)
	{
		CompoundNBT data = null;

		LivingEntity entity = world.getPlayerByUuid(UUID.fromString(id));
		
		data = new CompoundNBT();

		data.putString("UUID", id);
		data.putString("Name", entity.getName().getFormattedText());
		data.putLong("Bounty", bounty);
		int randomBg = (int) WyMathHelper.randomWithRange(0, wantedPostersBackgrounds.length - 1);
		data.putString("Background", wantedPostersBackgrounds[randomBg]);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = format.format(new Date());

		data.putString("Date", dateString);

		return data;
	}
	
	public static boolean isBow(ItemStack itemStack)
	{	
		if (itemStack.getUseAction() == UseAction.BOW)
			return true;
		
		return false;
	}
	
	public static boolean isSword(ItemStack itemStack)
	{
		if(itemStack.isEmpty())
			return false;
		
		if (itemStack.getItem() instanceof SwordItem)
			return true;

		Multimap multimap = itemStack.getAttributeModifiers(EquipmentSlotType.MAINHAND);
		if (multimap.containsKey(SharedMonsterAttributes.ATTACK_DAMAGE.getName()))
			return true;

		return false;
	}
	
	public static boolean hasKairosekiItem(PlayerEntity player)
	{
		for (Object obj : ModValues.KAIROSEKI_ITEMS)
		{
			Item itm = null;
			
			if(obj instanceof Item)
				itm = (Item) obj;
			else if(obj instanceof Block)
				itm = ((Block) obj).asItem();
			
			if (player.inventory.hasItemStack(new ItemStack(itm)))
			{
				return true;
		 	}
		}

		return false;
	}
}
