package xyz.pixelatedw.MineMineNoMi3.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.Values;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.math.WyMathHelper;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.api.telemetry.WyTelemetry;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSync;

public class BellyPouch extends Item
{
	public BellyPouch()
	{
		
	} 

	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{
		ExtendedEntityStats props = ExtendedEntityStats.get(player);

		//player.addStat(stat);
		
		if(!world.isRemote)
		{
			//player.addStat(ListStats.SHIPS_RAIDED);
			//System.out.println( ((EntityPlayerMP)player).getStatFile().readStat(ListStats.SHIPS_RAIDED) );
			
			int amount = (int) WyMathHelper.randomWithRange(5, 100);		
			
			if(props.getBelly() <= Values.MAX_GENERAL - amount)
			{
				props.alterBelly(amount);
				WyHelper.sendMsgToPlayer(player, "You've obtained " + amount + " belly !");
				WyHelper.removeStackFromInventory(player, itemStack);
			}
			else
				props.setBelly(Values.MAX_GENERAL);	
			
	    	if(!ID.DEV_EARLYACCESS && !player.capabilities.isCreativeMode)
	    		WyTelemetry.addGeneralStat("bellyEarnedFromPouches", amount);
		}
		
		WyNetworkHelper.sendToServer(new PacketSync(props));
		
		return itemStack;
	}

}
