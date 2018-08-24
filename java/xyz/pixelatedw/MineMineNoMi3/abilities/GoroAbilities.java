package xyz.pixelatedw.MineMineNoMi3.abilities;

import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.MovingObjectPosition;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.GoroProjectiles;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.MeraProjectiles;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.GoroProjectiles.ElThorThunder;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;
import xyz.pixelatedw.MineMineNoMi3.lists.ListExtraAttributes;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketParticles;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketPlayer;

public class GoroAbilities
{

	public static Ability[] abilitiesArray = new Ability[] {new ElThor(), new VoltVari(), new Raigo(), new Kari(), new Sango()};
	
	public static class ElThor extends Ability
	{
		public ElThor() 
		{
			super(ListAttributes.ELTHOR); 
		}
		
		public void startCharging(EntityPlayer player)
		{
			super.startCharging(player);				
		}
		
		public void duringCharging(EntityPlayer player, int currentCharge)
		{
			
			MovingObjectPosition mop = WyHelper.rayTraceBlocks(player);	
			
			if(mop != null)
			{
				double i = mop.blockX;
				double j = mop.blockY;
				double k = mop.blockZ;

				WyNetworkHelper.sendTo(new PacketParticles("elthor", i, j, k), (EntityPlayerMP) player);
			}
		}
		
		public void endCharging(EntityPlayer player)
		{						
			MovingObjectPosition mop = WyHelper.rayTraceBlocks(player);	
			
			if(mop != null)
			{
				double i = mop.blockX;
				double j = mop.blockY;
				double k = mop.blockZ;

				WyNetworkHelper.sendTo(new PacketPlayer("ElThorThunder", i, j, k), (EntityPlayerMP) player);
				player.worldObj.newExplosion(new EntityLightningBolt(player.worldObj, i, j, k), i, j, k, 6, true, true);
			}
			
			super.endCharging(player);
		}
	}
	
	public static class VoltVari extends Ability
	{
		public VoltVari() 
		{
			super(ListAttributes.VOLTVARI); 
		}
		
		public void use(EntityPlayer player)
		{
			this.projectile = new GoroProjectiles.VoltVari(player.worldObj, player, attr);
			super.use(player);
		} 
	}
	
	public static class Raigo extends Ability
	{
		public Raigo() 
		{
			super(ListAttributes.RAIGO); 
		}
		
		public void use(EntityPlayer player)
		{
			this.projectile = new GoroProjectiles.Raigo(player.worldObj, player, attr);
			super.use(player);
		} 
	}
	
	public static class Kari extends Ability
	{
		public Kari() 
		{
			super(ListAttributes.KARI); 
		}
	}
	
	public static class Sango extends Ability
	{
		public Sango() 
		{
			super(ListAttributes.SANGO); 
		}
		
		public void use(EntityPlayer player)
		{
			this.projectile = new GoroProjectiles.Sango(player.worldObj, player, attr);
			super.use(player);
		} 
	}

}
