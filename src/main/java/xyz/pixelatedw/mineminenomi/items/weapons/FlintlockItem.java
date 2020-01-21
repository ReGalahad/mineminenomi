package xyz.pixelatedw.mineminenomi.items.weapons;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.api.telemetry.WyTelemetry;
import xyz.pixelatedw.mineminenomi.entities.projectiles.ExtraProjectiles;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
import xyz.pixelatedw.mineminenomi.init.ModExtraAttributes;
import xyz.pixelatedw.mineminenomi.init.ModItems;

public class FlintlockItem extends Item
{

	public FlintlockItem()
	{
		super(new Properties().group(ModCreativeTabs.WEAPONS).maxStackSize(1));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
	{
		ItemStack itemStack = player.getHeldItemMainhand();
		if (player.isSneaking())
		{
			if (itemStack.getTag().getInt("bulletType") == 0)
			{
				itemStack.getTag().putInt("bulletType", 1);
				itemStack.setDisplayName(new StringTextComponent(TextFormatting.RESET + "Flintlock <Kairoseki>"));
			}
			else if (itemStack.getTag().getInt("bulletType") == 1)
			{
				itemStack.getTag().putInt("bulletType", 0);
				itemStack.setDisplayName(new StringTextComponent(TextFormatting.RESET + "Flintlock <Normal>"));
			}
		}
		else
		{
			if (itemStack.getTag().getBoolean("canUse"))
			{
				if(itemStack.getTag().getInt("gunPowder") > 0)
				{
					if ((player.inventory.hasItemStack(new ItemStack(ModItems.BULLET)) && itemStack.getTag().getInt("bulletType") == 0) 
							|| (player.inventory.hasItemStack(new ItemStack(ModItems.KAIROSEKI_BULLET)) && itemStack.getTag().getInt("bulletType") == 1))
					{
						AbilityProjectileEntity proj = null;
						int powder = itemStack.getTag().getInt("gunPowder");
						if (!world.isRemote)
						{
							if (itemStack.getTag().getInt("bulletType") == 0) proj = new ExtraProjectiles.NormalBullet(player.world, player, ModExtraAttributes.NORMAL_BULLET);
							else if (itemStack.getTag().getInt("bulletType") == 1) proj = new ExtraProjectiles.KairosekiBullet(player.world, player, ModExtraAttributes.KAIROSEKI_BULLET);
							player.world.addEntity(proj);
							
							String id = (itemStack.getTag().getInt("bulletType") == 0 ? "normal" : "kairoseki");
					    	WyTelemetry.addMiscStat(id + "BulletsShot", WyHelper.upperCaseFirst(id) + " Bullets Shot", 1);
						}
	
						itemStack.getTag().putBoolean("canUse", false);
						itemStack.getTag().putInt("gunPowder", --powder);
						for(int i = 0; i < player.inventory.getSizeInventory(); ++i)
						{
							ItemStack s = player.inventory.getStackInSlot(i);
							int bulletType = itemStack.getTag().getInt("bulletType");
							if((bulletType == 0 && s.getItem() == ModItems.BULLET) || (bulletType == 1 && s.getItem() == ModItems.KAIROSEKI_BULLET))
							{
								player.inventory.decrStackSize(i, 1);
							}
						}
					}
				}
				else
				{
					if(player.inventory.hasItemStack(new ItemStack(Items.GUNPOWDER)))
					{
						for(int i = 0; i < player.inventory.getSizeInventory(); ++i)
						{
							ItemStack s = player.inventory.getStackInSlot(i);
							if(s.getItem() == Items.GUNPOWDER)
							{
								int count = 10;
								if(s.getCount() < 10)
									count = s.getCount();
								
								itemStack.getTag().putInt("gunPowder", count);
								player.inventory.decrStackSize(i, count);
							}
						}
					}
				}
			}			
		}
		return new ActionResult<>(ActionResultType.SUCCESS, player.getHeldItem(hand));
	}

	@Override
	public void inventoryTick(ItemStack itemStack, World world, Entity entity, int itemSlot, boolean isSelected)
	{
		if (!itemStack.hasTag())
		{
			itemStack.setTag(new CompoundNBT());
			itemStack.getTag().putInt("bulletType", 0);
			itemStack.getTag().putBoolean("canUse", true);
			itemStack.getTag().putInt("gunPowder", 0);
			itemStack.getTag().putInt("cooldown", 15);
			
			itemStack.getTag().putInt("bulletType", 0);
			itemStack.setDisplayName(new StringTextComponent(TextFormatting.RESET + "Flintlock <Normal>"));
		}

		if (!itemStack.getTag().getBoolean("canUse"))
		{
			int cd = itemStack.getTag().getInt("cooldown");
			if (cd > 0)
			{
				cd--;
				itemStack.getTag().putInt("cooldown", cd);
			}
			else
			{
				itemStack.getTag().putInt("cooldown", 15);
				itemStack.getTag().putBoolean("canUse", true);
			}
		}
	}
	
	@Override
	public void addInformation(ItemStack itemStack, @Nullable World world, List<ITextComponent> list, ITooltipFlag par4)
	{
		if (itemStack.hasTag())
		{
	  		list.add(new StringTextComponent("Gun Powder : " + itemStack.getTag().getInt("gunPowder")));
		}
	}

}
