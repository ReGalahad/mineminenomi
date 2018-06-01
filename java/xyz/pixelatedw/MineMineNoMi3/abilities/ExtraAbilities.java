package xyz.pixelatedw.MineMineNoMi3.abilities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import scala.Int;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.entities.zoan.EntityMorphVenomDemon;
import xyz.pixelatedw.MineMineNoMi3.entities.zoan.EntityZoanMorph;
import xyz.pixelatedw.MineMineNoMi3.entities.zoan.EntityZoanPowerBison;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSync;

public class ExtraAbilities 
{

	
	public static class PowerPoint extends Ability
	{
		private EntityZoanMorph dummy;
		
		public PowerPoint() 
		{
			super(ListAttributes.POWERPOINT); 
		}
			
		public void use(EntityPlayer player)
		{
			ExtendedEntityStats props = ExtendedEntityStats.get(player);
			
			if(!this.isOnCooldown)
			{
				if(props.getZoanPoint().isEmpty())
					props.setZoanPoint("n/a");
					
				if(props.getZoanPoint().toLowerCase().equals("power"))
				{
					props.setZoanPoint("n/a");
					if(dummy != null)
					{
						if(props.getUsedFruit().equals("ushiushibison"))
							this.dummy.setDead();
						player.removePotionEffect(Potion.invisibility.id);
					}
					WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
				}
				else
				{
					props.setZoanPoint("power");
					WyHelper.sendMsgToPlayer(player, "<" + player.getDisplayName() + "> Power Point !");
					WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
					
					if(props.getUsedFruit().equals("ushiushibison"))
					{
						dummy = new EntityZoanPowerBison(player.worldObj);
						player.addPotionEffect(new PotionEffect(Potion.invisibility.id, Int.MaxValue(), 1, true));
						dummy.setPositionAndRotation(player.posX, player.posY, player.posZ, player.rotationPitch, player.rotationYaw);
						dummy.setOwner(player);
						player.worldObj.spawnEntityInWorld(dummy);		
					}
				}

				super.use(player);
			}
		}
	}
	
	public static class SpeedPoint extends Ability
	{
		public SpeedPoint() 
		{
			super(ListAttributes.SPEEDPOINT); 
		}
			
		public void use(EntityPlayer player)
		{
			ExtendedEntityStats props = ExtendedEntityStats.get(player);
			
			if(!this.isOnCooldown)
			{
				if(props.getZoanPoint().isEmpty())
					props.setZoanPoint("n/a");
					
				if(props.getZoanPoint().toLowerCase().equals("speed"))
					props.setZoanPoint("n/a");
				else
				{
					props.setZoanPoint("speed");
					WyHelper.sendMsgToPlayer(player, "<" + player.getDisplayName() + "> Speed Point !");
				}

				super.use(player);
			}
		}
	}
}
