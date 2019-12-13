package xyz.pixelatedw.mineminenomi.init;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.ArmorMaterial;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.items.armors.BasicArmorItem;
import xyz.pixelatedw.mineminenomi.items.armors.CaptainCapeItem;
import xyz.pixelatedw.mineminenomi.items.armors.ColaBackpackItem;
import xyz.pixelatedw.mineminenomi.values.ModValuesEnv;

@Mod.EventBusSubscriber(modid = ModValuesEnv.PROJECT_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModArmors
{
	// Materials
    public static final ArmorMaterial BASIC_ARMOR_MATERIAL = new ArmorMaterial(ModValuesEnv.PROJECT_ID + ":basic_armor", 100, new int[] { 0, 0, 1, 0 }, 12, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 2.0F,() -> Ingredient.fromItems(Items.LEATHER));
    public static final ArmorMaterial CAPTAIN_CAPE_MATERIAL = new ArmorMaterial(ModValuesEnv.PROJECT_ID + ":captain_cape", 100, new int[] { 0, 0, 2, 0 }, 12, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 2.0F,() -> Ingredient.fromItems(Items.LEATHER));
    public static final ArmorMaterial COLA_BACKPACK_MATERIAL = new ArmorMaterial(ModValuesEnv.PROJECT_ID + ":cola_backpack", 100, new int[] { 0, 0, 1, 0 }, 12, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 2.0F,() -> Ingredient.fromItems(ModItems.cola));

    // Armors
	public static final Item PIRATE_HEAD = new BasicArmorItem("pirate", EquipmentSlotType.HEAD);
	public static final Item PIRATE_CHEST = new BasicArmorItem("pirate", EquipmentSlotType.CHEST);
	public static final Item PIRATE_LEGS = new BasicArmorItem("pirate", EquipmentSlotType.LEGS);
	public static final Item PIRATE_FEET = new BasicArmorItem("pirate", EquipmentSlotType.FEET);

	public static final Item MARINE_HEAD = new BasicArmorItem("marine", EquipmentSlotType.HEAD);
	public static final Item MARINE_CHEST = new BasicArmorItem("marine", EquipmentSlotType.CHEST);
	public static final Item MARINE_LEGS = new BasicArmorItem("marine", EquipmentSlotType.LEGS);
	public static final Item MARINE_FEET = new BasicArmorItem("marine", EquipmentSlotType.FEET);
	
	public static final Item CAPTAIN_CAPE = new CaptainCapeItem();
	public static final Item COLA_BACKPACK = new ColaBackpackItem();

	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll
		(
			WyRegistry.registerItem(PIRATE_HEAD, "Pirate Bandana"),
			WyRegistry.registerItem(PIRATE_CHEST, "Pirate Chest"),
			WyRegistry.registerItem(PIRATE_LEGS, "Pirate Pants"),
			WyRegistry.registerItem(PIRATE_FEET, "Pirate Boots"),

			WyRegistry.registerItem(MARINE_HEAD, "Marine Hat"),
			WyRegistry.registerItem(MARINE_CHEST, "Marine Chest"),
			WyRegistry.registerItem(MARINE_LEGS, "Marine Pants"),
			WyRegistry.registerItem(MARINE_FEET, "Marine Boots"),
			
			WyRegistry.registerItem(CAPTAIN_CAPE, "Captain Cape"),
			WyRegistry.registerItem(COLA_BACKPACK, "Cola Backpack")
		);
	}
}
