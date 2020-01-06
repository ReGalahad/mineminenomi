package xyz.pixelatedw.mineminenomi.items.armors;

import javax.annotation.Nullable;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.Env;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.init.ModArmors;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
import xyz.pixelatedw.mineminenomi.models.CaptainCapeModel;

public class CaptainCapeItem extends ArmorItem
{

	public CaptainCapeItem()
	{
		super(ModArmors.CAPTAIN_CAPE_MATERIAL, EquipmentSlotType.CHEST, (new Item.Properties()).group(ModCreativeTabs.WEAPONS));
	}

	public void setCapeType(ItemStack itemStack, Type type)
	{
		itemStack.setTag(new CompoundNBT());
		itemStack.getTag().putString("Type", Type.JUSTICE.getType());
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	@Nullable
	public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default)
	{
		A armorModel = (A) new CaptainCapeModel();

		return armorModel;
	}

	@Override
	@Nullable
	public String getArmorTexture(ItemStack itemStack, Entity entity, EquipmentSlotType slot, String type)
	{
		if(itemStack.getTag() == null)
		{
			itemStack.setTag(new CompoundNBT());
			itemStack.getTag().putString("Type", Type.JUSTICE.getType());
		}
		
		if(WyHelper.isNullOrEmpty(itemStack.getTag().getString("Type")))
			itemStack.getTag().putString("Type", Type.JUSTICE.getType());
			
		return String.format("%s:textures/models/armor/capes/captain_cape_%s.png", Env.PROJECT_ID, itemStack.getTag().getString("Type"));
	}
	
	public static enum Type
	{
		JUSTICE("justice");
		
		private String type;
		
		private Type(String type)
		{
			this.type = type;
		}
		
		public String getType()
		{
			return this.type;
		}
	}
}
