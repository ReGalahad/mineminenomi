package xyz.pixelatedw.mineminenomi.init;

import org.apache.commons.lang3.tuple.ImmutablePair;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.Env;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.api.json.models.JSONModelItem;
import xyz.pixelatedw.mineminenomi.api.json.models.JSONPredicateObject;
import xyz.pixelatedw.mineminenomi.api.json.models.item.JSONModelRod;
import xyz.pixelatedw.mineminenomi.api.json.models.item.JSONModelSimple3DItem;
import xyz.pixelatedw.mineminenomi.api.json.models.item.JSONModelSword;
import xyz.pixelatedw.mineminenomi.items.weapons.AbilitySwordItem;
import xyz.pixelatedw.mineminenomi.items.weapons.CoreSwordItem;
import xyz.pixelatedw.mineminenomi.items.weapons.FlintlockItem;
import xyz.pixelatedw.mineminenomi.items.weapons.KujaBowItem;
import xyz.pixelatedw.mineminenomi.items.weapons.PopGreenBowItem;

@Mod.EventBusSubscriber(modid = Env.PROJECT_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModWeapons
{
	// Normal Melee Weapons
	public static final CoreSwordItem MARINE_SWORD = new CoreSwordItem(5, 300);
	public static final CoreSwordItem PIRATE_CUTLASS = new CoreSwordItem(5, 300);
	public static final CoreSwordItem PIPE = new CoreSwordItem(4, 200);
	public static final CoreSwordItem SCISSORS = new CoreSwordItem(6, 500);
	public static final CoreSwordItem KIKOKU = new CoreSwordItem(8, 500);
	public static final CoreSwordItem KIRIBACHI = new CoreSwordItem(6, 500);
	public static final CoreSwordItem YORU = new CoreSwordItem(10, 500);
	public static final CoreSwordItem MURAKUMOGIRI = new CoreSwordItem(8, 500);
	public static final CoreSwordItem HOOK = new CoreSwordItem(6, 500).setIsPoisonous();
	public static final CoreSwordItem UMBRELLA = new CoreSwordItem(3, 500);
	public static final CoreSwordItem JITTE = new CoreSwordItem(7, 500);
	public static final CoreSwordItem BO_STICK = new CoreSwordItem(6, 500);
	public static final CoreSwordItem HAMMER_5T = new CoreSwordItem(1, 500);
	public static final CoreSwordItem HAMMER_10T = new CoreSwordItem(1, 500);
	public static final CoreSwordItem HAMMER_100T = new CoreSwordItem(1, 500);
	public static final CoreSwordItem TONFA = new CoreSwordItem(4, 500);
	public static final CoreSwordItem BANDIT_KNIFE = new CoreSwordItem(3, 500);
	public static final CoreSwordItem KNIFE2 = new CoreSwordItem(3, 500);
	public static final CoreSwordItem KNIFE3 = new CoreSwordItem(3, 250);
	public static final CoreSwordItem WADO_ICHIMONJI = new CoreSwordItem(8, 500);
	public static final CoreSwordItem SANDAI_KITETSU = new CoreSwordItem(8, 500);
	public static final CoreSwordItem NIDAI_KITESTU = new CoreSwordItem(10, 500);
	public static final CoreSwordItem SHUSUI = new CoreSwordItem(8, 500);
	public static final CoreSwordItem SOUL_SOLID = new CoreSwordItem(8, 500);
	public static final CoreSwordItem DURANDAL = new CoreSwordItem(7, 500);
	
	// Normal Ranged Weapons
	public static final FlintlockItem FLINTLOCK = new FlintlockItem();
	public static final KujaBowItem GREEN_KUJA_BOW = new KujaBowItem();
	public static final KujaBowItem RED_KUJA_BOW = new KujaBowItem();
	public static final KujaBowItem BLUE_KUJA_BOW = new KujaBowItem();
	public static final PopGreenBowItem KABUTO = new PopGreenBowItem();
	public static final PopGreenBowItem BLACK_KABUTO = new PopGreenBowItem();
	public static final PopGreenBowItem GINGA_PACHINKO = new PopGreenBowItem();

	// Devil Fruit Weapons
	public static final AbilitySwordItem ICE_SABER = new AbilitySwordItem(9).setIsSlownessInducing();
	public static final AbilitySwordItem AMA_NO_MURAKUMO = new AbilitySwordItem(9);
	public static final AbilitySwordItem NORO_NORO_BEAM_SWORD = new AbilitySwordItem(5).setIsSlownessInducing(75, true);
	public static final AbilitySwordItem DORU_DORU_ARTS_KEN = new AbilitySwordItem(6);
	public static final AbilitySwordItem BLUE_SWORD = new AbilitySwordItem(8).setIsFireAspect();
	public static final AbilitySwordItem TABIRA_YUKI = new AbilitySwordItem(8).setIsSlownessInducing(50);
	
	// JSON Predicates
	public static final JSONPredicateObject HAKI_PREDICATE = new JSONPredicateObject("haki", new ImmutablePair("haki", 1));
	public static final JSONPredicateObject SHEATHED_PREDICATE = new JSONPredicateObject("sheathed", new ImmutablePair("sheathed", 1));
	public static final JSONPredicateObject PULLING_0_PREDICATE = new JSONPredicateObject("pulling_0", new ImmutablePair("pulling", 1));
	public static final JSONPredicateObject PULLING_1_PREDICATE = new JSONPredicateObject("pulling_1", new ImmutablePair("pulling", 1), new ImmutablePair("pull", 0.65));
	public static final JSONPredicateObject PULLING_2_PREDICATE = new JSONPredicateObject("pulling_2", new ImmutablePair("pulling", 1), new ImmutablePair("pull", 0.9));

	// JSON 3D Custom Models
	public static final JSONModelSimple3DItem GREEN_KUJA_BOW_MODEL = new JSONModelSimple3DItem("Green Kuja Bow", PULLING_0_PREDICATE, PULLING_1_PREDICATE, PULLING_2_PREDICATE).isBow();
	public static final JSONModelSimple3DItem RED_KUJA_BOW_MODEL = new JSONModelSimple3DItem("Red Kuja Bow", PULLING_0_PREDICATE, PULLING_1_PREDICATE, PULLING_2_PREDICATE).isBow();
	public static final JSONModelSimple3DItem BLUE_KUJA_BOW_MODEL = new JSONModelSimple3DItem("Blue Kuja Bow", PULLING_0_PREDICATE, PULLING_1_PREDICATE, PULLING_2_PREDICATE).isBow();
	public static final JSONModelSimple3DItem KABUTO_MODEL = new JSONModelSimple3DItem("Kabuto", PULLING_0_PREDICATE, PULLING_1_PREDICATE, PULLING_2_PREDICATE).isBow().setThirdPersonTranslations(-1.0, 1.0, 2.5).setThirdPersonScales( 1.9, 1.9, 1.9);
	public static final JSONModelSimple3DItem BLACK_KABUTO_MODEL = new JSONModelSimple3DItem("Kuro Kabuto", PULLING_0_PREDICATE, PULLING_1_PREDICATE, PULLING_2_PREDICATE).isBow().setThirdPersonTranslations(-1.0, 1.0, 2.5).setThirdPersonScales( 1.9, 1.9, 1.9);
	public static final JSONModelSimple3DItem GINGA_PACHINKO_MODEL = new JSONModelSimple3DItem("Ginga Pachinko", PULLING_0_PREDICATE, PULLING_1_PREDICATE, PULLING_2_PREDICATE).isBow().setThirdPersonTranslations(-1.0, 3.5, 2.5).setThirdPersonScales(1.0, 1.0, 1.0);

	@SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event)
    {
        event.getRegistry().registerAll
        (
        	registerSword(MARINE_SWORD, "Marine Sword", HAKI_PREDICATE),
        	registerSword(PIRATE_CUTLASS, "Pirate Cutlass", HAKI_PREDICATE),
        	registerSword(PIPE, "Pipe", HAKI_PREDICATE),
        	registerSword(SCISSORS, "Scissors", HAKI_PREDICATE),
        	registerSword(KIKOKU, "Kikoku", HAKI_PREDICATE),
        	registerSword(KIRIBACHI, "Kiribachi", HAKI_PREDICATE),
        	registerSword(YORU, "Yoru", HAKI_PREDICATE),
        	registerRod(MURAKUMOGIRI, "Murakumogiri", HAKI_PREDICATE),     	
        	registerSword(HOOK, "Hook", HAKI_PREDICATE, SHEATHED_PREDICATE),
        	registerSword(UMBRELLA, "Umbrella"),
        	registerSword(JITTE, "Jitte", HAKI_PREDICATE),
        	registerRod(BO_STICK, "Bo Staff", HAKI_PREDICATE),
        	registerSword(HAMMER_5T, "5t Hammer", HAKI_PREDICATE),
        	registerSword(HAMMER_10T, "10t Hammer", HAKI_PREDICATE),
        	registerSword(HAMMER_100T, "100t Hammer", HAKI_PREDICATE),
        	registerSword(TONFA, "Tonfa", HAKI_PREDICATE),      	
        	registerSword(BANDIT_KNIFE, "Bandit Knife", HAKI_PREDICATE),
        	//registerSword(knife2, "Knife"),
        	//registerSword(knife3, "Knife"),
        	registerSword(WADO_ICHIMONJI, "Wado Ichimonji", HAKI_PREDICATE, SHEATHED_PREDICATE),
        	registerSword(SANDAI_KITETSU, "Sandai Kitetsu", HAKI_PREDICATE, SHEATHED_PREDICATE),
        	registerSword(NIDAI_KITESTU, "Nidai Kitetsu", HAKI_PREDICATE, SHEATHED_PREDICATE),
        	registerSword(SHUSUI, "Shusui", HAKI_PREDICATE, SHEATHED_PREDICATE),
        	registerSword(SOUL_SOLID, "Soul Solid", HAKI_PREDICATE, SHEATHED_PREDICATE),
        	registerSword(DURANDAL, "Durandal", HAKI_PREDICATE),
        	
        	registerSimple3DItem(FLINTLOCK, "Flintlock"),     	
        	registerCustom3DItem(GREEN_KUJA_BOW, "Green Kuja Bow", GREEN_KUJA_BOW_MODEL),
        	registerCustom3DItem(RED_KUJA_BOW, "Red Kuja Bow", RED_KUJA_BOW_MODEL),
        	registerCustom3DItem(BLUE_KUJA_BOW, "Blue Kuja Bow", BLUE_KUJA_BOW_MODEL),
        	registerCustom3DItem(KABUTO, "Kabuto", KABUTO_MODEL),
        	registerCustom3DItem(BLACK_KABUTO, "Kuro Kabuto", BLACK_KABUTO_MODEL),
        	registerCustom3DItem(GINGA_PACHINKO, "Ginga Pachinko", GINGA_PACHINKO_MODEL),

        	registerSword(ICE_SABER, "Ice Saber"),
        	registerSword(AMA_NO_MURAKUMO, "Ama no Murakumo"),
        	registerSword(NORO_NORO_BEAM_SWORD, "Noro Noro Beam Sword"),
        	registerSword(DORU_DORU_ARTS_KEN, "Doru Doru Arts: Ken"),
        	registerSword(BLUE_SWORD, "Blue Sword"),
        	registerSword(TABIRA_YUKI, "Tabira Yuki")
        );
    }
	
	private static Item registerSword(Item item, String localizedName, JSONPredicateObject... predicate)
	{
		return WyRegistry.registerItem(item, localizedName, new JSONModelSword(localizedName, predicate));
	}
	
	private static Item registerRod(Item item, String localizedName, JSONPredicateObject... predicates)
	{
		return WyRegistry.registerItem(item, localizedName, new JSONModelRod(localizedName, predicates));
	}
	
	private static Item registerSimple3DItem(Item item, String localizedName, JSONPredicateObject... predicates)
	{
		return WyRegistry.registerItem(item, localizedName, new JSONModelSimple3DItem(localizedName, predicates));
	}
	
	private static Item registerCustom3DItem(Item item, String localizedName, JSONModelItem model)
	{
		return WyRegistry.registerItem(item, localizedName, model);
	}
}
