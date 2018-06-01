package xyz.pixelatedw.MineMineNoMi3.items;

import com.google.common.collect.Multimap;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;

public class ItemAbilityWeapon extends ItemCoreWeapon
{
	private double damage = 1;
	
	public ItemAbilityWeapon(int damage)
	{
		super(damage);
		this.damage = damage;
		this.maxStackSize = 1;
		this.setFull3D();
	}

	public void onUpdate(ItemStack itemStack, World world, Entity entity, int par4, boolean par5) 
	{
		if(entity instanceof EntityPlayer)
		{
			EntityPlayer owner = (EntityPlayer) entity;		
			ExtendedEntityStats props = ExtendedEntityStats.get(owner);

			if(props.getUsedFruit().equals("hiehie") || props.getUsedFruit().equals("pikapika") || props.getUsedFruit().equals("noronoro") || props.getUsedFruit().equals("dorudoru")
					|| props.getUsedFruit().equals("gasugasu") || props.getUsedFruit().equals("yukiyuki"))
			{
				for(int i = 0; i < props.countAbilitiesInHotbar(); i++)
				{
					if(props.getAbilityFromSlot(i) != null && !props.getAbilityFromSlot(i).equals("n/a") && props.getAbilityFromSlot(i).getAttribute().isPassive())
					{
						if(!props.getAbilityFromSlot(i).isPassiveActive())
						{
							String ablName = WyHelper.getFancyName(props.getAbilityFromSlot(i).getAttribute().getAttributeName());
							if(ablName.equals(WyHelper.getFancyName(ListAttributes.ICESABER.getAttributeName())))
								owner.inventory.clearInventory(this, -1);
							else if(ablName.equals(WyHelper.getFancyName(ListAttributes.AMANOMURAKUMO.getAttributeName())))
								owner.inventory.clearInventory(this, -1);
							else if(ablName.equals(WyHelper.getFancyName(ListAttributes.NORONOROBEAMSWORD.getAttributeName())))
								owner.inventory.clearInventory(this, -1);
							else if(ablName.equals(WyHelper.getFancyName(ListAttributes.DORUDORUARTSKEN.getAttributeName())))
								owner.inventory.clearInventory(this, -1);
							else if(ablName.equals(WyHelper.getFancyName(ListAttributes.BLUESWORD.getAttributeName())))
								owner.inventory.clearInventory(this, -1);
							else if(ablName.equals(WyHelper.getFancyName(ListAttributes.TABIRAYUKI.getAttributeName())))
								owner.inventory.clearInventory(this, -1);
						}
					}
				}
			}
			else
				owner.inventory.clearInventory(this, -1);
			
		}	
	}
	
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{
		return itemStack;		
	}

}
