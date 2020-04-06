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
import xyz.pixelatedw.mineminenomi.init.ModArmors;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
import xyz.pixelatedw.mineminenomi.models.armors.SniperGogglesModel;
import xyz.pixelatedw.wypi.APIConfig;

public class SniperGogglesItem extends ArmorItem
{
	public SniperGogglesItem()
	{
		super(ModArmors.MEDIC_BAG_MATERIAL, EquipmentSlotType.HEAD, (new Item.Properties()).group(ModCreativeTabs.WEAPONS).maxDamage(1000));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	@Nullable
	public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default)
	{
		A armorModel = (A) new SniperGogglesModel();

		return armorModel;
	}

	@Override
	@Nullable
	public String getArmorTexture(ItemStack itemStack, Entity entity, EquipmentSlotType slot, String type)
	{
		return String.format("%s:textures/models/armor/sniper_goggles.png", APIConfig.PROJECT_ID);
	}
}
