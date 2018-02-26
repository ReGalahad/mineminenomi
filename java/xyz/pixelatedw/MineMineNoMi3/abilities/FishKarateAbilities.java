package xyz.pixelatedw.MineMineNoMi3.abilities;

import com.mojang.realmsclient.gui.ChatFormatting;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import xyz.pixelatedw.MineMineNoMi3.abilities.RokushikiAbilities.Kamie;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.FishKarateProjectiles;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;

public class FishKarateAbilities 
{
	public static Ability UCHIMIZU = new Uchimizu();
	public static Ability SOSHARK = new Soshark();
	public static Ability KACHIAGEHAISOKU = new KachiageHaisoku();
	public static Ability SAMEHADASHOTEI = new SamehadaShotei();
	public static Ability KARAKUSAGAWARASEIKEN = new KarakusagawaraSeiken();
	
	public static Ability[] abilitiesArray = new Ability[] {UCHIMIZU, SOSHARK, SAMEHADASHOTEI, KARAKUSAGAWARASEIKEN};
	
	public static class Uchimizu extends Ability
	{
		public Uchimizu() 
		{
			super(ListAttributes.UCHIMIZU); 
		}
			
		public void use(EntityPlayer player)
		{
			this.projectile = new FishKarateProjectiles.Uchimizu(player.worldObj, player, ListAttributes.HIGAN);
			super.use(player);
		}
	}
	
	public static class Soshark extends Ability
	{
		public Soshark() 
		{
			super(ListAttributes.SOSHARK); 
		}
			
		public void use(EntityPlayer player)
		{
			this.projectile = new FishKarateProjectiles.Soshark(player.worldObj, player, ListAttributes.HIGAN);
			super.use(player);
		}
	}
	
	public static class KachiageHaisoku extends Ability
	{
		public KachiageHaisoku() 
		{
			super(ListAttributes.KACHIAGEHAISOKU); 
		}
			
		public void use(EntityPlayer player)
		{
			/*MovingObjectPosition mop = Minecraft.getMinecraft().objectMouseOver;
			
			if(mop != null && mop.entityHit != null)
			{			
				if(mop.entityHit instanceof EntityLivingBase)
				{
					EntityLivingBase elb = (EntityLivingBase) mop.entityHit;

					if(player.getDistanceSqToEntity(elb) < 7)
					{
						//elb.attackEntityFrom(DamageSource.causePlayerDamage(player), 8);					
					}
				}
			}*/
			
			WyHelper.sendMsgToPlayer(player, ChatFormatting.RED + "NOT YET IMPLEMENTED");
			//super.use(player);
		}
	}
	
	public static class SamehadaShotei extends Ability
	{
		public SamehadaShotei() 
		{
			super(ListAttributes.SAMEHADASHOTEI); 
		}
	}
	
	public static class KarakusagawaraSeiken extends Ability
	{
		public KarakusagawaraSeiken() 
		{
			super(ListAttributes.KARAKUSAGAWARASEIKEN); 
		}
			
		public void use(EntityPlayer player)
		{	
			if(!this.isOnCooldown)
			{
				for(EntityLivingBase elb : WyHelper.getEntitiesNear(player, 10))
				{
					elb.attackEntityFrom(DamageSource.causePlayerDamage(player), 20);
				}
				super.use(player);
			}			
		}
	}
}
