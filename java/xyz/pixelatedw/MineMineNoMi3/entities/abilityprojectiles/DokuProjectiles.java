package xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityAttribute;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityProjectile;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;

public class DokuProjectiles 
{

	public static ArrayList<Object[]> abilitiesClassesArray = new ArrayList();
	
	static
	{
		abilitiesClassesArray.add(new Object[] {ChloroBall.class, ListAttributes.CHLOROBALL});
		abilitiesClassesArray.add(new Object[] {Hydra.class, ListAttributes.HYDRA});
	}
	
	public static class ChloroBall extends AbilityProjectile
	{
		public ChloroBall(World world)
		{super(world);}
		
		public ChloroBall(World world, double x, double y, double z)
		{super(world, x, y, z);}
		
		public ChloroBall(World world, EntityLivingBase player, AbilityAttribute attr) 
		{		
			super(world, player, attr);		
		}	
		
		public void tasksImapct(MovingObjectPosition hit)
		{		
			for (int i = 0; i < 20; i++)
			{
				double offsetX = new Random().nextInt(5) - 3;
				double offsetZ = new Random().nextInt(5) - 3;
				
				if(this.worldObj.getBlock((int)(this.posX + offsetX), (int)this.posY, (int)(this.posZ + offsetZ)) == Blocks.air && this.worldObj.getBlock((int)(this.posX + offsetX), (int)this.posY - 1, (int)(this.posZ + offsetZ)) != Blocks.air)
					this.worldObj.setBlock((int)(this.posX + offsetX), (int)this.posY, (int)(this.posZ + offsetZ), ListMisc.Poison);
			}
		};
	}
	
	public static class Hydra extends AbilityProjectile
	{
		public Hydra(World world)
		{super(world);}
		
		public Hydra(World world, double x, double y, double z)
		{super(world, x, y, z);}
		
		public Hydra(World world, EntityLivingBase player, AbilityAttribute attr) 
		{		
			super(world, player, attr);		
		}	
	}
	
}
