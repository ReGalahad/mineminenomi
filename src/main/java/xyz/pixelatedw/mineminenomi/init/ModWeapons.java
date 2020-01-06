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
	public static CoreSwordItem marineSword = new CoreSwordItem(5, 300);
	public static CoreSwordItem pirateCutlass = new CoreSwordItem(5, 300);
	public static CoreSwordItem pipe = new CoreSwordItem(4, 200);
	public static CoreSwordItem scissors = new CoreSwordItem(6, 500);
	public static CoreSwordItem kikoku = new CoreSwordItem(8, 500);
	public static CoreSwordItem kiribachi = new CoreSwordItem(6, 500);
	public static CoreSwordItem yoru = new CoreSwordItem(10, 500);
	public static CoreSwordItem bisento = new CoreSwordItem(8, 500);
	public static CoreSwordItem hook = new CoreSwordItem(6, 500).setIsPoisonous();
	public static CoreSwordItem umbrella = new CoreSwordItem(3, 500);
	public static CoreSwordItem jitte = new CoreSwordItem(7, 500);
	public static CoreSwordItem boStick = new CoreSwordItem(6, 500);
	public static CoreSwordItem hammer5t = new CoreSwordItem(1, 500);
	public static CoreSwordItem hammer10t = new CoreSwordItem(1, 500);
	public static CoreSwordItem hammer100t = new CoreSwordItem(1, 500);
	public static CoreSwordItem tonfa = new CoreSwordItem(4, 500);
	public static CoreSwordItem bandit_knife = new CoreSwordItem(3, 500);
	public static CoreSwordItem knife2 = new CoreSwordItem(3, 500);
	public static CoreSwordItem knife3 = new CoreSwordItem(3, 250);
	public static CoreSwordItem wadoIchimonji = new CoreSwordItem(8, 500);
	public static CoreSwordItem sandaiKitetsu = new CoreSwordItem(8, 500);
	public static CoreSwordItem nidaiKitetsu = new CoreSwordItem(10, 500);
	public static CoreSwordItem shusui = new CoreSwordItem(8, 500);
	public static CoreSwordItem soulSolid = new CoreSwordItem(8, 500);
	public static CoreSwordItem durandal = new CoreSwordItem(7, 500);
	
	// Normal Ranged Weapons
	public static FlintlockItem flintlock = new FlintlockItem();
	public static KujaBowItem greenKujaBow = new KujaBowItem();
	public static KujaBowItem redKujaBow = new KujaBowItem();
	public static KujaBowItem blueKujaBow = new KujaBowItem();
	public static PopGreenBowItem kabuto = new PopGreenBowItem();
	public static PopGreenBowItem blackKabuto = new PopGreenBowItem();
	public static PopGreenBowItem gingaPachinko = new PopGreenBowItem();

	// Devil Fruit Weapons
	public static AbilitySwordItem iceSaber = new AbilitySwordItem(9).setIsSlownessInducing();
	public static AbilitySwordItem amaNoMurakumo = new AbilitySwordItem(9);
	public static AbilitySwordItem noroNoroBeamSword = new AbilitySwordItem(5).setIsSlownessInducing(75, true);
	public static AbilitySwordItem doruDoruArtsKen = new AbilitySwordItem(6);
	public static AbilitySwordItem blueSword = new AbilitySwordItem(8).setIsFireAspect();
	public static AbilitySwordItem tabiraYuki = new AbilitySwordItem(8).setIsSlownessInducing(50);
	
	// JSON Predicates
	public static JSONPredicateObject hakiPredicate = new JSONPredicateObject("haki", new ImmutablePair("haki", 1));
	public static JSONPredicateObject sheathedPredicate = new JSONPredicateObject("sheathed", new ImmutablePair("sheathed", 1));
	public static JSONPredicateObject pulling0Predicate = new JSONPredicateObject("pulling_0", new ImmutablePair("pulling", 1));
	public static JSONPredicateObject pulling1Predicate = new JSONPredicateObject("pulling_1", new ImmutablePair("pulling", 1), new ImmutablePair("pull", 0.65));
	public static JSONPredicateObject pulling2Predicate = new JSONPredicateObject("pulling_2", new ImmutablePair("pulling", 1), new ImmutablePair("pull", 0.9));

	// JSON 3D Custom Models
	public static JSONModelSimple3DItem greenKujaBowModel = new JSONModelSimple3DItem("Green Kuja Bow", pulling0Predicate, pulling1Predicate, pulling2Predicate).isBow();
	public static JSONModelSimple3DItem redKujaBowModel = new JSONModelSimple3DItem("Red Kuja Bow", pulling0Predicate, pulling1Predicate, pulling2Predicate).isBow();
	public static JSONModelSimple3DItem blueKujaBowModel = new JSONModelSimple3DItem("Blue Kuja Bow", pulling0Predicate, pulling1Predicate, pulling2Predicate).isBow();
	public static JSONModelSimple3DItem kabutoModel = new JSONModelSimple3DItem("Kabuto", pulling0Predicate, pulling1Predicate, pulling2Predicate).isBow().setThirdPersonTranslations(-1.0, 1.0, 2.5).setThirdPersonScales( 1.9, 1.9, 1.9);
	public static JSONModelSimple3DItem blackKabutoModel = new JSONModelSimple3DItem("Kuro Kabuto", pulling0Predicate, pulling1Predicate, pulling2Predicate).isBow().setThirdPersonTranslations(-1.0, 1.0, 2.5).setThirdPersonScales( 1.9, 1.9, 1.9);
	public static JSONModelSimple3DItem gingaPachinkoModel = new JSONModelSimple3DItem("Ginga Pachinko", pulling0Predicate, pulling1Predicate, pulling2Predicate).isBow().setThirdPersonTranslations(-1.0, 3.5, 2.5).setThirdPersonScales(1.0, 1.0, 1.0);

	@SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event)
    {
        event.getRegistry().registerAll
        (
        	registerSword(marineSword, "Marine Sword", hakiPredicate),
        	registerSword(pirateCutlass, "Pirate Cutlass", hakiPredicate),
        	registerSword(pipe, "Pipe", hakiPredicate),
        	registerSword(scissors, "Scissors", hakiPredicate),
        	registerSword(kikoku, "Kikoku", hakiPredicate),
        	registerSword(kiribachi, "Kiribachi", hakiPredicate),
        	registerSword(yoru, "Yoru", hakiPredicate),
        	registerRod(bisento, "Bisento", hakiPredicate),     	
        	registerSword(hook, "Hook", hakiPredicate, sheathedPredicate),
        	registerSword(umbrella, "Umbrella"),
        	registerSword(jitte, "Jitte", hakiPredicate),
        	registerRod(boStick, "Bo Staff", hakiPredicate),
        	registerSword(hammer5t, "5t Hammer", hakiPredicate),
        	registerSword(hammer10t, "10t Hammer", hakiPredicate),
        	registerSword(hammer100t, "100t Hammer", hakiPredicate),
        	registerSword(tonfa, "Tonfa", hakiPredicate),      	
        	registerSword(bandit_knife, "Bandit Knife", hakiPredicate),
        	//registerSword(knife2, "Knife"),
        	//registerSword(knife3, "Knife"),
        	registerSword(wadoIchimonji, "Wado Ichimonji", hakiPredicate, sheathedPredicate),
        	registerSword(sandaiKitetsu, "Sandai Kitetsu", hakiPredicate, sheathedPredicate),
        	registerSword(nidaiKitetsu, "Nidai Kitetsu", hakiPredicate, sheathedPredicate),
        	registerSword(shusui, "Shusui", hakiPredicate, sheathedPredicate),
        	registerSword(soulSolid, "Soul Solid", hakiPredicate, sheathedPredicate),
        	registerSword(durandal, "Durandal", hakiPredicate),
        	
        	registerSimple3DItem(flintlock, "Flintlock"),     	
        	registerCustom3DItem(greenKujaBow, "Green Kuja Bow", greenKujaBowModel),
        	registerCustom3DItem(redKujaBow, "Red Kuja Bow", redKujaBowModel),
        	registerCustom3DItem(blueKujaBow, "Blue Kuja Bow", blueKujaBowModel),
        	registerCustom3DItem(kabuto, "Kabuto", kabutoModel),
        	registerCustom3DItem(blackKabuto, "Kuro Kabuto", blackKabutoModel),
        	registerCustom3DItem(gingaPachinko, "Ginga Pachinko", gingaPachinkoModel),

        	registerSword(iceSaber, "Ice Saber"),
        	registerSword(amaNoMurakumo, "Ama no Murakumo"),
        	registerSword(noroNoroBeamSword, "Noro Noro Beam Sword"),
        	registerSword(doruDoruArtsKen, "Doru Doru Arts: Ken"),
        	registerSword(blueSword, "Blue Sword"),
        	registerSword(tabiraYuki, "Tabira Yuki")
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
