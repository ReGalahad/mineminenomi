package xyz.pixelatedw.mineminenomi.items.armors;

import javax.annotation.Nullable;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.DyeableArmorItem;
import net.minecraft.item.IDyeableArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.init.ModArmors;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
import xyz.pixelatedw.mineminenomi.models.armors.CaptainCapeModel;
import xyz.pixelatedw.wypi.APIConfig;

public class CaptainCapeItem extends DyeableArmorItem implements IDyeableArmorItem
{

	public CaptainCapeItem()
	{
		super(ModArmors.CAPTAIN_CAPE_MATERIAL, EquipmentSlotType.CHEST, (new Item.Properties()).group(ModCreativeTabs.WEAPONS));
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
		if(entity instanceof PlayerEntity)
		{
			if(EntityStatsCapability.get((LivingEntity) entity).isPirate())
				return String.format("%s:textures/models/armor/captain_cape_layer_1_overlay_pirate.png", APIConfig.PROJECT_ID);
		}

		return super.getArmorTexture(itemStack, entity, slot, type);
	}
}
