package xyz.pixelatedw.mineminenomi.init;

import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.pixelatedw.mineminenomi.Env;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiBoxItem;
import xyz.pixelatedw.mineminenomi.items.BellyPouchItem;
import xyz.pixelatedw.mineminenomi.items.CharacterCreatorItem;
import xyz.pixelatedw.mineminenomi.items.ColaItem;
import xyz.pixelatedw.mineminenomi.items.HeartItem;
import xyz.pixelatedw.mineminenomi.items.SeaKingMeatItem;
import xyz.pixelatedw.mineminenomi.items.UltraColaItem;

@Mod.EventBusSubscriber(modid = Env.PROJECT_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItems
{

	// Random stuff
	public static Item characterCreator = new CharacterCreatorItem();
	public static Item kairoseki = new Item(new Properties().group(ModCreativeTabs.MISC));
	public static Item denseKairoseki = new Item(new Properties().group(ModCreativeTabs.MISC));
	public static Item blackMetal = new Item(new Properties().group(ModCreativeTabs.MISC));
	public static Item shadow = new Item(new Properties().group(ModCreativeTabs.MISC));
	public static Item heart = new HeartItem();
	public static Item bellyPouch = new BellyPouchItem();
	public static Item key = new Item(new Properties().group(ModCreativeTabs.MISC));
	
	// Devil Fruit Boxes
	public static Item woodBox;
	public static Item ironBox;
	public static Item goldBox;
	
	// Food
	public static Item seaKingMeat = new SeaKingMeatItem();
	public static Item cola = new ColaItem();
	public static Item ultraCola = new UltraColaItem();
		
	// Ammo
	public static Item bullets = new Item(new Properties().group(ModCreativeTabs.MISC));
	public static Item kairosekiBullets = new Item(new Properties().group(ModCreativeTabs.MISC));
	public static Item kujaArrow = new Item(new Properties().group(ModCreativeTabs.MISC));
	public static Item popGreen = new Item(new Properties().group(ModCreativeTabs.MISC));

	@SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event)
    {
		if (!event.getName().equals(ForgeRegistries.ITEMS.getRegistryName())) return;
		
		woodBox = new AkumaNoMiBoxItem(1);
		ironBox = new AkumaNoMiBoxItem(2);
		goldBox = new AkumaNoMiBoxItem(3);

        event.getRegistry().registerAll
        (
        	WyRegistry.registerItem(kairoseki, "Kairoseki"),
        	WyRegistry.registerItem(denseKairoseki, "Dense Kairoseki"),
        	WyRegistry.registerItem(blackMetal, "Black Metal"),
        	WyRegistry.registerItem(shadow, "Shadow"),
        	WyRegistry.registerItem(key, "Key"),
        	WyRegistry.registerItem(characterCreator, "Character Creator"),
        	WyRegistry.registerItem(bellyPouch, "Belly Pouch"),
        	WyRegistry.registerItem(seaKingMeat, "Sea King Meat"),
        	WyRegistry.registerItem(cola, "Cola"),
        	WyRegistry.registerItem(ultraCola, "Ultra Cola"),
        	WyRegistry.registerItem(heart, "Heart"),
        	WyRegistry.registerItem(bullets, "Bullet"),
        	WyRegistry.registerItem(kairosekiBullets, "Kairoseki Bullet"),
        	WyRegistry.registerItem(kujaArrow, "Kuja Arrow"),
        	WyRegistry.registerItem(popGreen, "Pop Green"),
        	WyRegistry.registerItem(woodBox, "Wooden Box"),
        	WyRegistry.registerItem(ironBox, "Iron Box"),
        	WyRegistry.registerItem(goldBox, "Golden Box")
        );
    }
}
