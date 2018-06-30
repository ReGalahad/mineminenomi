package xyz.pixelatedw.MineMineNoMi3.items;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.EnumFruitType;
import xyz.pixelatedw.MineMineNoMi3.Values;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.math.WyMathHelper;
import xyz.pixelatedw.MineMineNoMi3.lists.ListDevilFruits;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;

public class AkumaNoMiBox extends Item
{
	
	private int tier;
	private AkumaNoMi[] tier1Fruits = new AkumaNoMi[] 
			{ListDevilFruits.BaneBaneNoMi, ListDevilFruits.SukeSukeNoMi, ListDevilFruits.NoroNoroNoMi, ListDevilFruits.DoruDoruNoMi, ListDevilFruits.GoeGoeNoMi, ListDevilFruits.BariBariNoMi};
	private AkumaNoMi[] tier2Fruits = new AkumaNoMi[] 
			{ListDevilFruits.NoroNoroNoMi, ListDevilFruits.OpeOpeNoMi, ListDevilFruits.MokuMokuNoMi, ListDevilFruits.NikyuNikyuNoMi, ListDevilFruits.BomuBomuNoMi, ListDevilFruits.GuraGuraNoMi, 
			ListDevilFruits.KageKageNoMi, ListDevilFruits.DokuDokuNoMi, ListDevilFruits.YukiYukiNoMi, ListDevilFruits.UshiUshiNoMiBison, ListDevilFruits.GoeGoeNoMi, ListDevilFruits.HoroHoroNoMi,
			ListDevilFruits.GomuGomuNoMi};
	private AkumaNoMi[] tier3Fruits = new AkumaNoMi[] 
			{ListDevilFruits.MeraMeraNoMi, ListDevilFruits.MaguMaguNoMi, ListDevilFruits.HieHieNoMi, ListDevilFruits.PikaPikaNoMi, ListDevilFruits.OpeOpeNoMi, ListDevilFruits.GoroGoroNoMi, 
			ListDevilFruits.SunaSunaNoMi, ListDevilFruits.GasuGasuNoMi, ListDevilFruits.UshiUshiNoMiBison, ListDevilFruits.YamiYamiNoMi, ListDevilFruits.ItoItoNoMi, ListDevilFruits.GomuGomuNoMi};
		
	public AkumaNoMiBox(int tier)
	{
		this.tier = tier;
	} 

	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{
		boolean flag = false;	
		for(int i = 0; i < player.inventory.mainInventory.length; i++)
		{
			if(player.inventory.getStackInSlot(i) != null && player.inventory.getStackInSlot(i).getItem() == ListMisc.Key)
			{
				player.inventory.decrStackSize(i, 1);
				flag = true;
				break;
			}				
		}	
			
		if(flag)
		{
			WyHelper.removeStackFromInventory(player, itemStack);				 
			player.inventory.addItemStackToInventory(new ItemStack(roulette(), 1, 0));
		}		
		else
			WyHelper.sendMsgToPlayer(player, "You need a key !");

		return itemStack;
	}
	
	
	private AkumaNoMi roulette()
	{
		Random rand = new Random();
		
		if(rand.nextInt(100) + rand.nextDouble() <= 95)
		{
			if(tier == 1)
			{
				if(rand.nextInt(100) + rand.nextDouble() < 1)
					return tier2Fruits[rand.nextInt(tier2Fruits.length)];				
				else
				{
					System.out.println( tier1Fruits[1] );
					return tier1Fruits[rand.nextInt(tier1Fruits.length)];
				}
			}
			else if(tier == 2)
			{
				if(rand.nextInt(100) + rand.nextDouble() < 1)
					return tier3Fruits[rand.nextInt(tier3Fruits.length)];				
				else
					return tier2Fruits[rand.nextInt(tier2Fruits.length)];
			}
			else if(tier == 3)
			{
				return tier3Fruits[rand.nextInt(tier3Fruits.length)];
			}
		}
		
		return null;
	}

}
