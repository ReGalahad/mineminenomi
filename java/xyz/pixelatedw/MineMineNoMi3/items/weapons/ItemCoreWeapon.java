package xyz.pixelatedw.MineMineNoMi3.items.weapons;

import com.google.common.collect.Multimap;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;

public class ItemCoreWeapon extends Item
{
	private double damage = 1;
	private double multiplier = 1;
	private boolean canUseSpecial = false;
	private boolean isPoisonous = false;
	
	private IIcon baseIcon, sheathedIcon, hakiImbuedIcon;

	public ItemCoreWeapon(int damage)
	{
		this.damage = damage;
		this.maxStackSize = 1;
		this.setFull3D();
	}

	public ItemCoreWeapon(int damage, boolean canUseSpecial)
	{
		this.damage = damage;
		this.canUseSpecial = canUseSpecial;
		this.maxStackSize = 1;
		this.setFull3D();
	}
	
	public void onUpdate(ItemStack itemStack, World world, Entity entity, int par4, boolean par5) 
	{
		if(!itemStack.hasTagCompound())
		{
			itemStack.setTagCompound(new NBTTagCompound());
			itemStack.getTagCompound().setInteger("metadata", 0); // 0 - normal, 1 - sheathed, 2 - haki imbued
			itemStack.getTagCompound().setDouble("multiplier", 1);
		}
		
		if(!world.isRemote)
		{
			ExtendedEntityStats props = ExtendedEntityStats.get((EntityLivingBase) entity);
			
			double multiplier = 1;
			
			if(props.hasBusoHakiActive())
				multiplier += 0.5;
			
			if(props.getFightStyle().equals(ID.FSTYLE_SWORDSMAN))
				multiplier += 0.25;
			
			itemStack.getTagCompound().setDouble("multiplier", multiplier);
		}
	}

	public ItemCoreWeapon setMaxDamage(int maxDamage)
	{
		super.setMaxDamage(maxDamage);
		return this;
	}

	public ItemCoreWeapon setQuality()
	{
		return this;
	}

	public boolean hitEntity(ItemStack itemStack, EntityLivingBase target, EntityLivingBase attacker)
	{
		ExtendedEntityStats props = ExtendedEntityStats.get(attacker);

		if(!props.hasBusoHakiActive())
			itemStack.damageItem(1, attacker);
		
		if(isPoisonous)
			target.addPotionEffect(new PotionEffect(Potion.poison.id, 100, 0));
		
		return true;
	}

	public ItemCoreWeapon setIsPoisonous()
	{
		this.isPoisonous = true;
		return this;
	}
	
	public Multimap getAttributeModifiers(ItemStack stack)
	{
		Multimap multimap = super.getAttributeModifiers(stack);
		double multiplier;
		if( stack.getTagCompound() != null)
			multiplier = stack.getTagCompound().getDouble("multiplier");
		else
			multiplier = 1;
		multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", this.damage * multiplier, 0));
		return multimap;
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		this.itemIcon = ir.registerIcon(ID.PROJECT_ID + ":" + this.getUnlocalizedName().substring(5));
		this.sheathedIcon = ir.registerIcon(ID.PROJECT_ID + ":" + this.getUnlocalizedName().substring(5) + "_1");
		this.hakiImbuedIcon = ir.registerIcon(ID.PROJECT_ID + ":" + this.getUnlocalizedName().substring(5) + "_haki");
		
		try
		{ Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation(ID.PROJECT_ID + ":textures/items/" + this.getUnlocalizedName().substring(5) + "_1.png")); }
		catch(Exception e)
		{ this.sheathedIcon = this.itemIcon; }
		
		try
		{ Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation(ID.PROJECT_ID + ":textures/items/" + this.getUnlocalizedName().substring(5) + "_haki.png")); }
		catch(Exception e)
		{ this.hakiImbuedIcon = this.itemIcon; }
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining)
	{	
		ExtendedEntityStats props = ExtendedEntityStats.get(player);

		if(stack.getTagCompound() != null)
    	{
			if (player.getHeldItem() != null && player.getHeldItem().equals(stack))
			{								
				if(props.hasBusoHakiActive())
				{
					stack.getTagCompound().setInteger("metadata", 2);
					return this.hakiImbuedIcon;
				}
				
				stack.getTagCompound().setInteger("metadata", 0);
			}
			else
			{
				stack.getTagCompound().setInteger("metadata", 1);
				return this.itemIcon;
			}
    	}
    	
		return this.itemIcon;
	}

    @SideOnly(Side.CLIENT)
    public IIcon getIconIndex(ItemStack stack)
    {
    	if(stack.getTagCompound() != null)
    	{
			if(stack.getTagCompound().getInteger("metadata") == 1)
				return this.sheathedIcon;
			else if(stack.getTagCompound().getInteger("metadata") == 2)
				return this.hakiImbuedIcon;
			else
				return itemIcon;
    	}
    	
    	return itemIcon;
	}
}
