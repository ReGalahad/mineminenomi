package xyz.pixelatedw.MineMineNoMi3.abilities;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.SwordsmanProjectiles;
import xyz.pixelatedw.MineMineNoMi3.items.weapons.ItemCoreWeapon;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;

public class SwordsmanAbilities 
{
	public static Ability SANBYAKUROKUJUPOUNDHO = new SanbyakurokujuPoundHo();
	
	public static Ability[] abilitiesArray = new Ability[] {SANBYAKUROKUJUPOUNDHO};
	
	public static class SanbyakurokujuPoundHo extends Ability
	{
		public SanbyakurokujuPoundHo() 
		{
			super(ListAttributes.SANBYAKUROKUJUPOUNDHO); 
		}
			
		public void use(EntityPlayer player)
		{
			if(player.getHeldItem() != null && player.getHeldItem().getItem() instanceof ItemCoreWeapon)
			{
				this.projectile = new SwordsmanProjectiles.SanbyakurokujuPoundHo(player.worldObj, player, ListAttributes.SANBYAKUROKUJUPOUNDHO);
				if(!this.isOnCooldown)
					Minecraft.getMinecraft().thePlayer.swingItem();
				super.use(player);
			}
			else
				WyHelper.sendMsgToPlayer(player, "You need a sword to use this ability !");
		}
	}
	
}
