package xyz.pixelatedw.MineMineNoMi3.abilities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.GomuProjectiles;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;
import xyz.pixelatedw.MineMineNoMi3.lists.ListExtraAttributes;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSync;

public class GomuAbilities 
{
	public static Ability[] abilitiesArray = new Ability[] {new GomuGomuNoPistol(), new GomuGomuNoBazooka(), new GearSecond(), new GearThird(), new GearForth()};

	public static class GearForth extends Ability
	{
		public GearForth() 
		{
			super(ListAttributes.GEARFOURTH); 
		}
		
		public void startPassive(EntityPlayer player)
		{
			ExtendedEntityStats props = ExtendedEntityStats.get(player);
			
			props.setGear(4);
			WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
		} 
			
		public void duringPassive(EntityPlayer player, int passiveTimer)
		{
			ExtendedEntityStats props = ExtendedEntityStats.get(player);

			if(passiveTimer >= 30)
			{
				props.setGear(1);
				WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
				super.setPassiveActive(false);
				super.use(player);
			}			
		} 
		
		public void endPassive(EntityPlayer player)
		{
			ExtendedEntityStats props = ExtendedEntityStats.get(player);

			props.setGear(1);
			WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
			super.use(player);
		} 
	}	
	
	public static class GearThird extends Ability
	{
		public GearThird() 
		{
			super(ListAttributes.GEARTHIRD); 
		}
		
		public void startPassive(EntityPlayer player)
		{
			ExtendedEntityStats props = ExtendedEntityStats.get(player);
			
			props.setGear(3);
			WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
		} 
			
		public void duringPassive(EntityPlayer player, int passiveTimer)
		{
			ExtendedEntityStats props = ExtendedEntityStats.get(player);

			if(passiveTimer >= 60)
			{
				props.setGear(1);
				WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
				super.setPassiveActive(false);
				super.use(player);
			}			
		} 
		
		public void endPassive(EntityPlayer player)
		{
			ExtendedEntityStats props = ExtendedEntityStats.get(player);

			props.setGear(1);
			WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
			super.use(player);
		} 
	}		
	
	public static class GearSecond extends Ability
	{
		public GearSecond() 
		{
			super(ListAttributes.GEARSECOND); 
		}
		
		public void startPassive(EntityPlayer player)
		{
			ExtendedEntityStats props = ExtendedEntityStats.get(player);
			
			props.setGear(2);
			WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
		} 
			
		public void duringPassive(EntityPlayer player, int passiveTimer)
		{
			ExtendedEntityStats props = ExtendedEntityStats.get(player);

			if(passiveTimer >= 60)
			{
				props.setGear(1);
				WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
				super.setPassiveActive(false);
				super.use(player);
			}			
		} 
		
		public void endPassive(EntityPlayer player)
		{
			ExtendedEntityStats props = ExtendedEntityStats.get(player);

			props.setGear(1);
			WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
			super.use(player);
		} 
	}
	
	public static class GomuGomuNoBazooka extends Ability
	{
		public GomuGomuNoBazooka() 
		{
			super(ListAttributes.GOMUGOMUNOBAZOOKA); 
		}

		public void endCharging(EntityPlayer player)
		{
			ExtendedEntityStats props = ExtendedEntityStats.get(player);
			
			switch(props.getGear())
			{
				case 1:
					this.projectile = new GomuProjectiles.GomuGomuNoBazooka(player.worldObj, player, ListExtraAttributes.GOMUGOMUNOBAZOOKA);
					this.attr.setAbilityCooldown(12);
					this.attr.setAbilityCharges(20);
					break;
				case 2:
					this.projectile = new GomuProjectiles.GomuGomuNoJetBazooka(player.worldObj, player, ListExtraAttributes.GOMUGOMUNOJETBAZOOKA);
					this.attr.setAbilityCooldown(6);
					this.attr.setAbilityCharges(10);
					break;
				case 3:
					this.projectile = new GomuProjectiles.GomuGomuNoGrizzlyMagnum(player.worldObj, player, ListExtraAttributes.GOMUGOMUNOGRIZZLYMAGNUM);
					this.attr.setAbilityCooldown(20);
					this.attr.setAbilityCharges(40);
					break;
				case 4:
					this.projectile = new GomuProjectiles.GomuGomuNoLeoBazooka(player.worldObj, player, ListExtraAttributes.GOMUGOMUNOLEOBAZOOKA);
					this.attr.setAbilityCooldown(30);
					this.attr.setAbilityCharges(40);
					break;
			}
			
			super.endCharging(player);
		} 
	}
	
	public static class GomuGomuNoPistol extends Ability
	{
		public GomuGomuNoPistol() 
		{
			super(ListAttributes.GOMUGOMUNOPISTOL); 
		}
		
		public void use(EntityPlayer player)
		{
			ExtendedEntityStats props = ExtendedEntityStats.get(player);
				
			switch(props.getGear())
			{
				case 1:
					this.projectile = new GomuProjectiles.GomuGomuNoPistol(player.worldObj, player, ListExtraAttributes.GOMUGOMUNOPISTOL);
					this.attr.setAbilityCooldown(10);
					break;
				case 2:
					this.projectile = new GomuProjectiles.GomuGomuNoJetPistol(player.worldObj, player, ListExtraAttributes.GOMUGOMUNOJETPISTOL);
					this.attr.setAbilityCooldown(5);
					break;
				case 3:
					this.projectile = new GomuProjectiles.GomuGomuNoElephantGun(player.worldObj, player, ListExtraAttributes.GOMUGOMUNOELEPHANTGUN);
					this.attr.setAbilityCooldown(15);
					break;
				case 4:
					this.projectile = new GomuProjectiles.GomuGomuNoKongGun(player.worldObj, player, ListExtraAttributes.GOMUGOMUNOKONGGUN);
					this.attr.setAbilityCooldown(30);
					break;
			}

			super.use(player);
		} 
	}
}
