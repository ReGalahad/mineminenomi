package xyz.pixelatedw.mineminenomi.init;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.api.GenericArmorMaterial;
import xyz.pixelatedw.mineminenomi.items.armors.BasicArmorItem;
import xyz.pixelatedw.mineminenomi.items.armors.CaptainCapeItem;
import xyz.pixelatedw.mineminenomi.items.armors.ColaBackpackItem;
import xyz.pixelatedw.mineminenomi.items.armors.MedicBagItem;
import xyz.pixelatedw.mineminenomi.items.armors.SniperGogglesItem;
import xyz.pixelatedw.mineminenomi.items.armors.TomoeDrumsItem;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyRegistry;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModArmors
{
	// Materials
    public static final GenericArmorMaterial BASIC_ARMOR_MATERIAL = new GenericArmorMaterial(APIConfig.PROJECT_ID + ":basic_armor", 100, new int[] { 1, 1, 1, 1 }, 12, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 2.0F,() -> Ingredient.fromItems(Items.LEATHER));
    public static final GenericArmorMaterial CAPTAIN_CAPE_MATERIAL = new GenericArmorMaterial(APIConfig.PROJECT_ID + ":captain_cape", 100, new int[] { 2, 2, 2, 2 }, 12, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 2.0F,() -> Ingredient.fromItems(Items.WHITE_WOOL));
    public static final GenericArmorMaterial COLA_BACKPACK_MATERIAL = new GenericArmorMaterial(APIConfig.PROJECT_ID + ":cola_backpack", 100, new int[] { 1, 1, 1, 1 }, 12, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 2.0F,() -> Ingredient.fromItems(ModItems.COLA));
    public static final GenericArmorMaterial MEDIC_BAG_MATERIAL = new GenericArmorMaterial(APIConfig.PROJECT_ID + ":medic_bag", 100, new int[] { 1, 1, 1, 1 }, 12, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 2.0F,() -> Ingredient.fromItems(Items.LEATHER));
    public static final GenericArmorMaterial TOMOE_DRUMS_MATERIAL = new GenericArmorMaterial(APIConfig.PROJECT_ID + ":tomoe_drums", 100, new int[] { 2, 2, 2, 2 }, 12, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.0F,() -> Ingredient.fromItems(Items.IRON_INGOT, Items.GOLD_INGOT));
    public static final GenericArmorMaterial SNIPER_GOGGLES_MATERIAL = new GenericArmorMaterial(APIConfig.PROJECT_ID + ":sniper_goggles", 100, new int[] { 2, 2, 2, 2 }, 12, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.0F,() -> Ingredient.fromItems(Items.IRON_INGOT));

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
	public static final Item MEDIC_BAG = new MedicBagItem();
	public static final Item TOMOE_DRUMS = new TomoeDrumsItem();
	public static final Item SNIPER_GOGGLES = new SniperGogglesItem();

	static
	{
		WyRegistry.registerItem(PIRATE_HEAD, "Pirate Bandana");
		WyRegistry.registerItem(PIRATE_CHEST, "Pirate Chest");
		WyRegistry.registerItem(PIRATE_LEGS, "Pirate Pants");
		WyRegistry.registerItem(PIRATE_FEET, "Pirate Boots");

		WyRegistry.registerItem(MARINE_HEAD, "Marine Hat");
		WyRegistry.registerItem(MARINE_CHEST, "Marine Chest");
		WyRegistry.registerItem(MARINE_LEGS, "Marine Pants");
		WyRegistry.registerItem(MARINE_FEET, "Marine Boots");
			
		WyRegistry.registerItem(CAPTAIN_CAPE, "Captain Cape");
		WyRegistry.registerItem(COLA_BACKPACK, "Cola Backpack");
		WyRegistry.registerItem(MEDIC_BAG, "Medic Bag");
		WyRegistry.registerItem(TOMOE_DRUMS, "Tomoe Drums");
		WyRegistry.registerItem(SNIPER_GOGGLES, "Sniper Goggles");
	}
}
