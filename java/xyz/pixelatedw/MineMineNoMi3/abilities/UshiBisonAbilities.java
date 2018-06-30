package xyz.pixelatedw.MineMineNoMi3.abilities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import xyz.pixelatedw.MineMineNoMi3.abilities.ExtraAbilities.PowerPoint;
import xyz.pixelatedw.MineMineNoMi3.abilities.ExtraAbilities.SpeedPoint;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.entities.zoan.EntityZoanMorph;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketParticles;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketPlayer;

public class UshiBisonAbilities 
{

	public static Ability[] abilitiesArray = new Ability[] {new PowerPoint(), new FiddleBanff(), new KokuteiCross()};

	public static class FiddleBanff extends Ability
	{
		public FiddleBanff() 
		{
			super(ListAttributes.FIDDLEBANFF); 
		}
		
		public void use(EntityPlayer player)
		{	
			ExtendedEntityStats props = ExtendedEntityStats.get(player);
			
			if(props.getZoanPoint().equals("power") && !this.isOnCooldown)
			{

				double mX = (double)(-MathHelper.sin(player.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(player.rotationPitch / 180.0F * (float)Math.PI) * 0.4);
				double mZ = (double)(MathHelper.cos(player.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(player.rotationPitch / 180.0F * (float)Math.PI) * 0.4);
					
				double f2 = MathHelper.sqrt_double(mX * mX + player.motionY * player.motionY + mZ * mZ);
				mX /= (double)f2;
				mZ /= (double)f2;
				mX += player.worldObj.rand.nextGaussian() * 0.007499999832361937D * 1.0;
				mZ += player.worldObj.rand.nextGaussian() * 0.007499999832361937D * 1.0;
				mX *= 5;
				mZ *= 5;
			
				motion("=", mX, player.motionY, mZ, player);
				
				super.use(player);
			}
			else if(!props.getZoanPoint().equals("power"))
			{
				WyHelper.sendMsgToPlayer(player, "" + this.getAttribute().getAttributeName() + " can only be used while Power Point is active !");
			}
		}
		
	    public void duringCooldown(EntityPlayer player, int currentCooldown)
	    {
			if(currentCooldown > 130)
			{
				for(EntityLivingBase e : WyHelper.getEntitiesNear(player, 1.6))
					if(!(e instanceof EntityZoanMorph))
						e.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) player), 6);
			}
	    }
	}
	
	public static class KokuteiCross extends Ability
	{
		public KokuteiCross() 
		{
			super(ListAttributes.KOKUTEICROSS); 
		}	
		
		public void startPassive(EntityPlayer player) 
		{
			ExtendedEntityStats props = ExtendedEntityStats.get(player);
			
			if(!props.getZoanPoint().equals("power"))
			{
				this.setPassiveActive(false);
				WyHelper.sendMsgToPlayer(player, "" + this.getAttribute().getAttributeName() + " can only be used while Power Point is active !");
			}
		}
		
		public void hitEntity(EntityPlayer player, EntityLivingBase target) 
		{
			ExtendedEntityStats props = ExtendedEntityStats.get(player);

			if(props.getZoanPoint().equals("power"))
			{
				super.hitEntity(player, target);
				target.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) player), 20);
				WyNetworkHelper.sendTo(new PacketParticles("kokuteiCross", target), (EntityPlayerMP) player);
				
				double mX = (double)(-MathHelper.sin(player.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(player.rotationPitch / 180.0F * (float)Math.PI) * 0.4);
				double mZ = (double)(MathHelper.cos(player.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(player.rotationPitch / 180.0F * (float)Math.PI) * 0.4);
					
				double f2 = MathHelper.sqrt_double(mX * mX + player.motionY * player.motionY + mZ * mZ);
				mX /= (double)f2;
				mZ /= (double)f2;
				mX += player.worldObj.rand.nextGaussian() * 0.007499999832361937D * 1.0;
				mZ += player.worldObj.rand.nextGaussian() * 0.007499999832361937D * 1.0;
				mX *= -0.7;
				mZ *= -0.7;
			
				motion("=", mX, player.motionY, mZ, player);
			}
			else
			{
				this.setPassiveActive(false);
				WyHelper.sendMsgToPlayer(player, "" + this.getAttribute().getAttributeName() + " can only be used while Power Point is active !");
			}
		}
	}
	
	
	private static void motion(String c, double x, double y, double z, EntityPlayer p)
	{
		WyNetworkHelper.sendTo(new PacketPlayer("motion" + c, x, y, z), (EntityPlayerMP) p);
	}
	
}
