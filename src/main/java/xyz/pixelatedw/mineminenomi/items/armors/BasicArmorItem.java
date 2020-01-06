package xyz.pixelatedw.mineminenomi.items.armors;

import javax.annotation.Nullable;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.Env;
import xyz.pixelatedw.mineminenomi.init.ModArmors;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;

public class BasicArmorItem extends ArmorItem
{
	private String name;
	
	public BasicArmorItem(String name, EquipmentSlotType type)
	{
		super(ModArmors.BASIC_ARMOR_MATERIAL, type, (new Item.Properties()).group(ModCreativeTabs.WEAPONS));
		this.name = name;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	@Nullable
	public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default)
	{
		A armorModel = (A) new BipedModel(0.1F);

		return armorModel;
	}

	@Override
	@Nullable
	public String getArmorTexture(ItemStack itemStack, Entity entity, EquipmentSlotType slot, String type)
	{
		return String.format("%s:textures/models/armor/%s_%d.png", Env.PROJECT_ID, this.name, slot == EquipmentSlotType.LEGS ? 2 : 1);
	}
}
