package xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles;

import java.util.ArrayList;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityAttribute;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityProjectile;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.MeraProjectiles.DaiEnkaiEntei;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.MeraProjectiles.Hidaruma;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.MeraProjectiles.Higan;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.MeraProjectiles.Hiken;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.MeraProjectiles.Jujika;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;

public class HieProjectiles 
{

	public static ArrayList<Object[]> abilitiesClassesArray = new ArrayList();
	
	static
	{
		abilitiesClassesArray.add(new Object[] {IceBall.class, ListAttributes.ICEBALL});
		abilitiesClassesArray.add(new Object[] {IceBlockPartisan.class, ListAttributes.ICEBLOCKPARTISAN});
		abilitiesClassesArray.add(new Object[] {IceBlockPheasant.class, ListAttributes.ICEBLOCKPHEASANT});
	}
	
	public static class IceBlockPheasant extends AbilityProjectile
	{
		public IceBlockPheasant(World world)
		{super(world);}
		
		public IceBlockPheasant(World world, double x, double y, double z)
		{super(world, x, y, z);}
		
		public IceBlockPheasant(World world, EntityLivingBase player, AbilityAttribute attr) 
		{		
			super(world, player, attr);		
		}
	}	
	
	public static class IceBlockPartisan extends AbilityProjectile
	{
		public IceBlockPartisan(World world)
		{super(world);}
		
		public IceBlockPartisan(World world, double x, double y, double z)
		{super(world, x, y, z);}
		
		public IceBlockPartisan(World world, EntityLivingBase player, AbilityAttribute attr) 
		{		
			super(world, player, attr);		
		}
		
		public void tasksImapct(MovingObjectPosition hit)
		{
			this.worldObj.setBlock((int)this.posX, (int)this.posY, (int)this.posZ, Blocks.packed_ice);
		}
	}
	
	public static class IceBall extends AbilityProjectile
	{
		public IceBall(World world)
		{super(world);}
		
		public IceBall(World world, double x, double y, double z)
		{super(world, x, y, z);}
		
		public IceBall(World world, EntityLivingBase player, AbilityAttribute attr) 
		{		
			super(world, player, attr);		
		}
		
		public void tasksImapct(MovingObjectPosition hit)
		{
			if(!this.worldObj.isRemote)
			{
				WyHelper.createSphere(this, 6, Blocks.packed_ice);
				WyHelper.createSphere(this, 6, Blocks.packed_ice);
				WyHelper.createSphere(this, 7, Blocks.packed_ice);
				WyHelper.createSphere(this, 7, Blocks.packed_ice);
			}
		}
	}
}
