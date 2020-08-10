package xyz.pixelatedw.mineminenomi.items.armors;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.init.ModArmors;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
import xyz.pixelatedw.mineminenomi.models.armors.SniperGogglesModel;
import xyz.pixelatedw.mineminenomi.models.armors.StrawHatModel;
import xyz.pixelatedw.wypi.APIConfig;

import javax.annotation.Nullable;

public class StrawHatItem extends ArmorItem
{
	public StrawHatItem()
	{
		super(ModArmors.MEDIC_BAG_MATERIAL, EquipmentSlotType.HEAD, (new Properties()).group(ModCreativeTabs.WEAPONS).maxDamage(1000));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	@Nullable
	public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default)
	{
		A armorModel = (A) new StrawHatModel();

		return armorModel;
	}

	@Override
	@Nullable
	public String getArmorTexture(ItemStack itemStack, Entity entity, EquipmentSlotType slot, String type)
	{
		return String.format("%s:textures/models/armor/straw_hat.png", APIConfig.PROJECT_ID);
	}
}
