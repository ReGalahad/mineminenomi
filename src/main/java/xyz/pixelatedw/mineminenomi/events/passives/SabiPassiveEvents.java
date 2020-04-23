package xyz.pixelatedw.mineminenomi.events.passives;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.init.ModEnchantments;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;
import xyz.pixelatedw.wypi.APIConfig;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class SabiPassiveEvents
{
	public static final List<Item> IRON_ITEMS = Lists.newArrayList(ModWeapons.MARINE_SWORD, ModWeapons.PIRATE_CUTLASS, ModWeapons.BANDIT_KNIFE, Items.IRON_AXE, Items.IRON_BARS, Items.IRON_BLOCK, Items.IRON_BOOTS, Items.IRON_CHESTPLATE, Items.IRON_DOOR, Items.IRON_HELMET, Items.IRON_HOE, Items.IRON_HORSE_ARMOR, Items.IRON_INGOT, Items.IRON_LEGGINGS, Items.IRON_NUGGET, Items.IRON_ORE, Items.IRON_PICKAXE, Items.IRON_SHOVEL, Items.IRON_SWORD, Items.IRON_TRAPDOOR);
	
	@SubscribeEvent
	public static void onEntityAttack(LivingAttackEvent event)
	{
		if (!(event.getSource().getTrueSource() instanceof LivingEntity) || !(event.getEntityLiving() instanceof PlayerEntity))
			return;

		LivingEntity attacker = (LivingEntity) event.getSource().getTrueSource();
		PlayerEntity attacked = (PlayerEntity) event.getEntityLiving();
		IDevilFruit devilFruitProps = DevilFruitCapability.get(attacked);

		if (!devilFruitProps.getDevilFruit().equalsIgnoreCase("sabi_sabi"))
			return;

		ItemStack mainhandGear = attacker.getItemStackFromSlot(EquipmentSlotType.MAINHAND);	
		ItemStack offhandGear = attacker.getItemStackFromSlot(EquipmentSlotType.OFFHAND);	
		
		for(Item item : SabiPassiveEvents.IRON_ITEMS)
		{
			if(mainhandGear.getItem() == item && EnchantmentHelper.getEnchantmentLevel(ModEnchantments.KAIROSEKI, mainhandGear) <= 0)
				mainhandGear.damageItem((mainhandGear.getMaxDamage() / 4) + 1, attacked, (entity) -> {});
			else if(offhandGear.getItem() == item && EnchantmentHelper.getEnchantmentLevel(ModEnchantments.KAIROSEKI, offhandGear) <= 0)
				offhandGear.damageItem((offhandGear.getMaxDamage() / 4) + 1, attacked, (entity) -> {});
		}
	}
}
