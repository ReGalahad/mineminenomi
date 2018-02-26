package xyz.pixelatedw.MineMineNoMi3.abilities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import xyz.pixelatedw.MineMineNoMi3.api.EnumParticleTypes;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper.Direction;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.ItoProjectiles;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketPlayer;

public class ItoAbilities 
{
	
	public static Ability[] abilitiesArray = new Ability[] {new Parasite(), new SoraNoMichi(), new Overheat()};
	
	public static class Overheat extends Ability
	{
		public Overheat() 
		{
			super(ListAttributes.OVERHEAT); 
		}
		
		public void use(EntityPlayer player)
		{
			this.projectile = new ItoProjectiles.Overheat(player.worldObj, player, attr);
			super.use(player);
		};	
	}
	
	public static class SoraNoMichi extends Ability
	{
		public SoraNoMichi() 
		{
			super(ListAttributes.SORANOMICHI); 
		}
		
		public void use(EntityPlayer player)
		{
			if(!this.isOnCooldown)
			{
				Direction dir = WyHelper.get8Directions(player);
				
				double mX = 0;
				double mY = 0;
				double mZ = 0;
				
				if(player.onGround)
					mY += 1.8;
				else
					mY += 1.96;

				if(dir == WyHelper.Direction.NORTH) mZ -= 1;
				if(dir == WyHelper.Direction.NORTH_WEST) {mZ -= 1; mX -= 1;}
				if(dir == WyHelper.Direction.SOUTH) mZ += 1;
				if(dir == WyHelper.Direction.NORTH_EAST) {mZ -= 1; mX += 1;}
				if(dir == WyHelper.Direction.WEST) mX -= 1;
				if(dir == WyHelper.Direction.SOUTH_WEST) {mZ += 1; mX -= 1;}
				if(dir == WyHelper.Direction.EAST) mX += 1;
				if(dir == WyHelper.Direction.SOUTH_EAST) {mZ += 1; mX += 1;}
				
				motion("=", mX, mY, mZ, player);
				
				super.use(player);
			}
		};	
	}
	
	private static void motion(String c, double x, double y, double z, EntityPlayer p)
	{
		WyNetworkHelper.sendTo(new PacketPlayer("motion" + c, x, y, z), (EntityPlayerMP) p);
	}
	
	public static class Parasite extends Ability
	{
		public Parasite() 
		{
			super(ListAttributes.PARASITE); 
		}
		
		public void use(EntityPlayer player)
		{
			if(!isOnCooldown)
			{			
				for(EntityLivingBase l : WyHelper.getEntitiesNear(player, 20))
				{
					System.out.println(l);

					l.addPotionEffect(new PotionEffect(Potion.blindness.id, 200, 10));
					l.addPotionEffect(new PotionEffect(Potion.confusion.id, 200, 10));
					
					l.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 200, 10));
					l.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 200, 10));
				}
					
				super.use(player);
			}
		}
	}

}
