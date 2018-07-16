package xyz.pixelatedw.MineMineNoMi3.abilities;

import com.mojang.realmsclient.gui.ChatFormatting;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.DokuProjectiles;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSync;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSyncInfo;

public class DokuAbilities 
{

	public static Ability[] abilitiesArray = new Ability[] {new Hydra(), new ChloroBall(), new DokuFugu(), new VenomDemon()};	
		
	
	public static class VenomDemon extends Ability
	{
		public VenomDemon() 
		{
			super(ListAttributes.VENOMDEMON); 
		}
		
		public void startPassive(EntityPlayer player) 
		{
			ExtendedEntityStats props = ExtendedEntityStats.get(player);
			
			if (props.getZoanPoint().isEmpty())
				props.setZoanPoint("n/a");

			props.setZoanPoint(ID.ZOANMORPH_DOKU);
			WyHelper.sendMsgToPlayer(player, "<" + player.getDisplayName() + "> Venom Demon !");
			WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
			WyNetworkHelper.sendToAll(new PacketSyncInfo(player.getDisplayName(), props));
		}
		
		public void duringPassive(EntityPlayer player, int passiveTimer) 
		{
			if(!WyHelper.isBlockNearby(player, 2, Blocks.water, Blocks.flowing_water, ListMisc.KairosekiOre, ListMisc.KairosekiBlock))
			{
				if (player.worldObj.getBlock((int)player.posX, (int)player.posY - 1, (int)player.posZ - 1) != Blocks.air
				&& player.worldObj.getBlock((int)player.posX, (int)player.posY - 1, (int)player.posZ - 1) != ListMisc.Poison
				&& player.worldObj.getBlock((int)player.posX, (int)player.posY - 1, (int)player.posZ - 1) != ListMisc.Ope
				&& player.worldObj.getBlock((int)player.posX, (int)player.posY - 1, (int)player.posZ - 1) != ListMisc.OpeMid
				&& player.worldObj.getBlock((int)player.posX, (int)player.posY - 1, (int)player.posZ - 1) != Blocks.bedrock)
					player.worldObj.setBlock((int)player.posX, (int)player.posY, (int)player.posZ - 1, ListMisc.DemonPoison);
			}
		}		
		
		public void hitEntity(EntityPlayer player, EntityLivingBase target) 
		{
			if(!WyHelper.isBlockNearby(player, 2, Blocks.water, Blocks.flowing_water, ListMisc.KairosekiOre, ListMisc.KairosekiBlock))
			{
				target.addPotionEffect(new PotionEffect(Potion.poison.id, 400, 2));
			}
		}
		
		public void endPassive(EntityPlayer player) 
		{
			ExtendedEntityStats props = ExtendedEntityStats.get(player);
			
			if(props.getZoanPoint().toLowerCase().equals(ID.ZOANMORPH_DOKU))
			{
				props.setZoanPoint("n/a");	
				WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
				WyNetworkHelper.sendToAll(new PacketSyncInfo(player.getDisplayName(), props));
			}
		}
	}
	
	public static class DokuFugu extends Ability
	{
		public DokuFugu() 
		{
			super(ListAttributes.DOKUFUGU); 
		}
		
		public void use(EntityPlayer player)
		{	
			this.projectile = new DokuProjectiles.ChloroBall(player.worldObj, player, attr);
			super.use(player);
		} 
	}
	
	public static class VenomRoad extends Ability
	{
		public VenomRoad() 
		{
			super(ListAttributes.VENOMROAD); 
		}
		
		public void use(EntityPlayer player)
		{	
			WyHelper.sendMsgToPlayer(player, ChatFormatting.RED + "NOT YET IMPLEMENTED");
			/*if(!player.worldObj.isRemote)
			{
				if(!isOnCooldown)
				{
					if(WyHelper.rayTrace(player) != null)
					{
						MovingObjectPosition mop = WyHelper.rayTrace(player);
						 
						int x = mop.blockX;
						int y = mop.blockY;
						int z = mop.blockZ;
		
						EnderTeleportEvent event = new EnderTeleportEvent(player, x, y, z, 0.0F);
						if(!MinecraftForge.EVENT_BUS.post(event))
						{
							if (player.isRiding())
								player.dismountEntity((Entity)null);
							player.setPositionAndRotation(x, y, z, player.rotationYaw, player.rotationPitch);
						}			
					}	
					
					isOnCooldown = true;
					startCooldown();
				}
			}*/
		} 
	}
	
	public static class ChloroBall extends Ability
	{
		public ChloroBall() 
		{
			super(ListAttributes.CHLOROBALL); 
		}
		
		public void use(EntityPlayer player)
		{	
			this.projectile = new DokuProjectiles.ChloroBall(player.worldObj, player, attr);
			super.use(player);
		} 
	}
	
	public static class Hydra extends Ability
	{
		public Hydra() 
		{
			super(ListAttributes.HYDRA); 
		}
		
		public void use(EntityPlayer player)
		{	
			this.projectile = new DokuProjectiles.Hydra(player.worldObj, player, attr);
			super.use(player);
		} 
	}
	
}
