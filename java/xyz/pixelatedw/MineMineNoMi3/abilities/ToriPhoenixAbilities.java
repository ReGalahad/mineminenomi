package xyz.pixelatedw.MineMineNoMi3.abilities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSync;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSyncInfo;

public class ToriPhoenixAbilities
{

	public static Ability[] abilitiesArray = new Ability[] {new PhoenixPoint()};

	public static class PhoenixPoint extends Ability
	{
		public PhoenixPoint()
		{
			super(ListAttributes.PHOENIXPOINT);
		}

		public void use(EntityPlayer player)
		{
			ExtendedEntityStats props = ExtendedEntityStats.get(player);

			if (!this.isOnCooldown)
			{
				if (props.getZoanPoint().isEmpty())
					props.setZoanPoint("n/a");

				if (props.getZoanPoint().toLowerCase().equals(ID.ZOANMORPH_PHOENIX))
				{
					props.setZoanPoint("n/a");
					WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
					WyNetworkHelper.sendToAll(new PacketSyncInfo(player.getDisplayName(), props));
				} 
				else
				{
					props.setZoanPoint(ID.ZOANMORPH_PHOENIX);
					WyHelper.sendMsgToPlayer(player, "<" + player.getDisplayName() + "> Phoenix Point !");
					WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
					WyNetworkHelper.sendToAll(new PacketSyncInfo(player.getDisplayName(), props));
				}

				super.use(player);
			}
		}
	}
	/*
	public static class FiddleBanff extends Ability
	{
		public FiddleBanff() 
		{
			super(ListAttributes.FIDDLEBANFF); 
		}
		
		public void use(EntityPlayer player)
		{	
			ExtendedEntityStats props = ExtendedEntityStats.get(player);
			
			if((props.getZoanPoint().equals("power") || props.getZoanPoint().equals("speed") ) && !this.isOnCooldown)
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
			else if(!props.getZoanPoint().equals("power") && !props.getZoanPoint().equals("speed"))
			{
				WyHelper.sendMsgToPlayer(player, "" + this.getAttribute().getAttributeName() + " can only be used while Power Point or Speed Point is active !");
			}
		}
		
	    public void duringCooldown(EntityPlayer player, int currentCooldown)
	    {
			if(currentCooldown > 130)
			{
				for(EntityLivingBase e : WyHelper.getEntitiesNear(player, 1.6))
					e.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) player), 6);
			}
	    }
	}*/
	
}
