package xyz.pixelatedw.mineminenomi.init;

import java.util.List;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.pixelatedw.mineminenomi.EnumFruitType;
import xyz.pixelatedw.mineminenomi.Env;
import xyz.pixelatedw.mineminenomi.abilities.mera.DaiEnkaiEnteiAbility;
import xyz.pixelatedw.mineminenomi.abilities.mera.HiganAbility;
import xyz.pixelatedw.mineminenomi.abilities.mera.HikenAbility;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.debug.WyDebug;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.mera.MeraProjectiles;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;

@Mod.EventBusSubscriber(modid = Env.PROJECT_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModDevilFruits
{

	public static AkumaNoMiItem  MeraMeraNoMi, HieHieNoMi, BaneBaneNoMi, PikaPikaNoMi, GomuGomuNoMi, SukeSukeNoMi,
			OpeOpeNoMi, NoroNoroNoMi, GoroGoroNoMi, MokuMokuNoMi, NikyuNikyuNoMi, BomuBomuNoMi, GuraGuraNoMi,
			KageKageNoMi, SunaSunaNoMi, MaguMaguNoMi, DoruDoruNoMi, DokuDokuNoMi, BariBariNoMi, GasuGasuNoMi,
			YukiYukiNoMi, JuryoJuryoNoMi, YamiYamiNoMi, ItoItoNoMi, HoroHoroNoMi, SupaSupaNoMi, OriOriNoMi, 
			MeroMeroNoMi, GoeGoeNoMi, KiloKiloNoMi, HanaHanaNoMi, HoruHoruNoMi, BetaBetaNoMi, IshiIshiNoMi, 
			PamuPamuNoMi, UshiUshiNoMiBison, ToriToriNoMiPhoenix, BakuBakuNoMi, YomiYomiNoMi, ZouZouNoMi,
			SabiSabiNoMi, HitoHitoNoMi, ChiyuChiyuNoMi, MoguMoguNoMi, UshiUshiNoMiGiraffe, DoaDoaNoMi,
			KachiKachiNoMi, MiniMiniNoMi;

	public static final List[] ALL_PROJECTILES = new List[] 
		{
			// Devil Fruit projectiles
			MeraProjectiles.projectiles/*, HieProjectiles.projectiles, BaneProjectiles.projectiles, PikaProjectiles.projectiles, NoroProjectiles.projectiles, SukeProjectiles.projectiles, OpeProjectiles.projectiles,
			GoroProjectiles.projectiles, MokuProjectiles.projectiles, NikyuProjectiles.projectiles, BomuProjectiles.projectiles, GuraProjectiles.projectiles, KageProjectiles.projectiles, SunaProjectiles.projectiles,
			MaguProjectiles.projectiles, DoruProjectiles.projectiles, DokuProjectiles.projectiles, GasuProjectiles.projectiles, YukiProjectiles.projectiles, ItoProjectiles.projectiles, BariProjectiles.projectiles,
			HoroProjectiles.projectiles, YamiProjectiles.projectiles, GoeProjectiles.projectiles, GomuProjectiles.projectiles, JuryoProjectiles.projectiles, ToriPhoenixProjectiles.projectiles,
			BakuProjectiles.projectiles, SupaProjectiles.projectiles, MeroProjectiles.projectiles, OriProjectiles.projectiles, UshiGiraffeProjectiles.projectiles, ZouProjectiles.projectiles,

			// Special Abilities projectiles
			RokushikiProjectiles.projectiles, FishKarateProjectiles.projectiles, CyborgProjectiles.projectiles, ExtraProjectiles.projectiles, 
			SwordsmanProjectiles.projectiles, SniperProjectiles.projectiles*/
		};

	@SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event)
    {
        if (!event.getName().equals(ForgeRegistries.ITEMS.getRegistryName())) return;
    	
		int totalFruits = 0, totalAbilities = 0;	

		//MiniMiniNoMi = new ItemAkumaNoMi(EnumFruitType.PARAMECIA, MiniAbilities.abilitiesArray);
		//registerDevilFruit(MiniMiniNoMi, "Mini Mini no Mi");
		/*KachiKachiNoMi = new AkumaNoMiItem(EnumFruitType.PARAMECIA, KachiAbilities.abilitiesArray);
		registerDevilFruit(KachiKachiNoMi, "Kachi Kachi no Mi");
		//DoaDoaNoMi = new AkumaNoMiItem(EnumFruitType.PARAMECIA, DoaAbilities.abilitiesArray);
		//registerDevilFruit(DoaDoaNoMi, "Doa Doa no Mi");
		UshiUshiNoMiGiraffe = new AkumaNoMiItem(EnumFruitType.ZOAN, UshiGiraffeAbilities.abilitiesArray);
		registerDevilFruit(UshiUshiNoMiGiraffe, "Ushi Ushi no Mi, Model Giraffe");
		MoguMoguNoMi = new AkumaNoMiItem(EnumFruitType.ZOAN, MoguAbilities.abilitiesArray);
		registerDevilFruit(MoguMoguNoMi, "Mogu Mogu no Mi");
		HoruHoruNoMi = new AkumaNoMiItem(EnumFruitType.PARAMECIA, HoruAbilities.abilitiesArray);
		registerDevilFruit(HoruHoruNoMi, "Horu Horu no Mi");
		ChiyuChiyuNoMi = new AkumaNoMiItem(EnumFruitType.PARAMECIA, ChiyuAbilities.abilitiesArray);
		registerDevilFruit(ChiyuChiyuNoMi, "Chiyu Chiyu no Mi");
		MeroMeroNoMi = new AkumaNoMiItem(EnumFruitType.PARAMECIA, MeroAbilities.abilitiesArray);
		registerDevilFruit(MeroMeroNoMi, "Mero Mero no Mi");
		SupaSupaNoMi = new AkumaNoMiItem(EnumFruitType.PARAMECIA, SupaAbilities.abilitiesArray);
		registerDevilFruit(SupaSupaNoMi, "Supa Supa no Mi");
		HitoHitoNoMi = new AkumaNoMiItem(EnumFruitType.ZOAN, new Ability[] {});
		registerDevilFruit(HitoHitoNoMi, "Hito Hito no Mi");
		SabiSabiNoMi = new AkumaNoMiItem(EnumFruitType.PARAMECIA, SabiAbilities.abilitiesArray);
		registerDevilFruit(SabiSabiNoMi, "Sabi Sabi no Mi");
		ZouZouNoMi = new AkumaNoMiItem(EnumFruitType.ZOAN, ZouAbilities.abilitiesArray);
		registerDevilFruit(ZouZouNoMi, "Zou Zou no Mi");
		YomiYomiNoMi = new AkumaNoMiItem(EnumFruitType.PARAMECIA, YomiAbilities.abilitiesArray);
		registerDevilFruit(YomiYomiNoMi, "Yomi Yomi no Mi");
		BakuBakuNoMi = new AkumaNoMiItem(EnumFruitType.PARAMECIA, BakuAbilities.abilitiesArray);
		registerDevilFruit(BakuBakuNoMi, "Baku Baku no Mi");
		//KiloKiloNoMi = new AkumaNoMiItem(EnumFruitType.PARAMECIA, KiloAbilities.abilitiesArray);
		//registerDevilFruit(KiloKiloNoMi, "Kilo Kilo no Mi");
		OriOriNoMi = new AkumaNoMiItem(EnumFruitType.PARAMECIA, OriAbilities.abilitiesArray);
		registerDevilFruit(OriOriNoMi, "Ori Ori no Mi");
		ToriToriNoMiPhoenix = new AkumaNoMiItem(EnumFruitType.MYTHICALZOAN, ToriPhoenixAbilities.abilitiesArray);
		registerDevilFruit(ToriToriNoMiPhoenix, "Tori Tori no Mi, Model Phoenix");
		UshiUshiNoMiBison = new AkumaNoMiItem(EnumFruitType.ZOAN, UshiBisonAbilities.abilitiesArray);
		registerDevilFruit(UshiUshiNoMiBison, "Ushi Ushi no Mi, Model Bison");
		JuryoJuryoNoMi = new AkumaNoMiItem(EnumFruitType.PARAMECIA, JuryoAbilities.abilitiesArray);
		registerDevilFruit(JuryoJuryoNoMi, "Zushi Zushi no Mi");
		YamiYamiNoMi = new AkumaNoMiItem(EnumFruitType.LOGIA, YamiAbilities.abilitiesArray);
		registerDevilFruit(YamiYamiNoMi, "Yami Yami no Mi");
		GoeGoeNoMi = new AkumaNoMiItem(EnumFruitType.PARAMECIA, GoeAbilities.abilitiesArray);
		registerDevilFruit(GoeGoeNoMi, "Goe Goe no Mi");
		HoroHoroNoMi = new AkumaNoMiItem(EnumFruitType.PARAMECIA, HoroAbilities.abilitiesArray);
		registerDevilFruit(HoroHoroNoMi, "Horo Horo no Mi");
		BariBariNoMi = new AkumaNoMiItem(EnumFruitType.PARAMECIA, BariAbilities.abilitiesArray);
		registerDevilFruit(BariBariNoMi, "Bari Bari no Mi");
		ItoItoNoMi = new AkumaNoMiItem(EnumFruitType.PARAMECIA, ItoAbilities.abilitiesArray);
		registerDevilFruit(ItoItoNoMi, "Ito Ito no Mi");
		YukiYukiNoMi = new AkumaNoMiItem(EnumFruitType.LOGIA, YukiAbilities.abilitiesArray);
		registerDevilFruit(YukiYukiNoMi, "Yuki Yuki no Mi");
		GasuGasuNoMi = new AkumaNoMiItem(EnumFruitType.LOGIA, GasuAbilities.abilitiesArray);
		registerDevilFruit(GasuGasuNoMi, "Gasu Gasu no Mi");
		DokuDokuNoMi = new AkumaNoMiItem(EnumFruitType.PARAMECIA, DokuAbilities.abilitiesArray);
		registerDevilFruit(DokuDokuNoMi, "Doku Doku no Mi");
		DoruDoruNoMi = new AkumaNoMiItem(EnumFruitType.PARAMECIA, DoruAbilities.abilitiesArray);
		registerDevilFruit(DoruDoruNoMi, "Doru Doru no Mi");
		MaguMaguNoMi = new AkumaNoMiItem(EnumFruitType.LOGIA, MaguAbilities.abilitiesArray);
		registerDevilFruit(MaguMaguNoMi, "Magu Magu no Mi");
		SunaSunaNoMi = new AkumaNoMiItem(EnumFruitType.LOGIA, SunaAbilities.abilitiesArray);
		registerDevilFruit(SunaSunaNoMi, "Suna Suna no Mi");
		KageKageNoMi = new AkumaNoMiItem(EnumFruitType.PARAMECIA, KageAbilities.abilitiesArray);
		registerDevilFruit(KageKageNoMi, "Kage Kage no Mi");
		GuraGuraNoMi = new AkumaNoMiItem(EnumFruitType.PARAMECIA, GuraAbilities.abilitiesArray);
		registerDevilFruit(GuraGuraNoMi, "Gura Gura no Mi");
		BomuBomuNoMi = new AkumaNoMiItem(EnumFruitType.PARAMECIA, BomuAbilities.abilitiesArray);
		registerDevilFruit(BomuBomuNoMi, "Bomu Bomu no Mi");
		NikyuNikyuNoMi = new AkumaNoMiItem(EnumFruitType.PARAMECIA, NikyuAbilities.abilitiesArray);
		registerDevilFruit(NikyuNikyuNoMi, "Nikyu Nikyu no Mi");
		MokuMokuNoMi = new AkumaNoMiItem(EnumFruitType.LOGIA, MokuAbilities.abilitiesArray);
		registerDevilFruit(MokuMokuNoMi, "Moku Moku no Mi");
		GoroGoroNoMi = new AkumaNoMiItem(EnumFruitType.LOGIA, GoroAbilities.abilitiesArray);
		registerDevilFruit(GoroGoroNoMi, "Goro Goro no Mi");
		OpeOpeNoMi = new AkumaNoMiItem(EnumFruitType.PARAMECIA, OpeAbilities.abilitiesArray);
		registerDevilFruit(OpeOpeNoMi, "Ope Ope no Mi");
		NoroNoroNoMi = new AkumaNoMiItem(EnumFruitType.PARAMECIA, NoroAbilities.abilitiesArray);
		registerDevilFruit(NoroNoroNoMi, "Noro Noro no Mi");
		SukeSukeNoMi = new AkumaNoMiItem(EnumFruitType.PARAMECIA, SukeAbilities.abilitiesArray);
		registerDevilFruit(SukeSukeNoMi, "Suke Suke no Mi");
		PikaPikaNoMi = new AkumaNoMiItem(EnumFruitType.LOGIA, PikaAbilities.abilitiesArray);
		registerDevilFruit(PikaPikaNoMi, "Pika Pika no Mi");
		GomuGomuNoMi = new AkumaNoMiItem(EnumFruitType.PARAMECIA, GomuAbilities.abilitiesArray);
		registerDevilFruit(GomuGomuNoMi, "Gomu Gomu no Mi");
		BaneBaneNoMi = new AkumaNoMiItem(EnumFruitType.PARAMECIA, BaneAbilities.abilitiesArray);
		registerDevilFruit(BaneBaneNoMi, "Bane Bane no Mi");
		HieHieNoMi = new AkumaNoMiItem(EnumFruitType.LOGIA, HieAbilities.abilitiesArray);
		registerDevilFruit(HieHieNoMi, "Hie Hie no Mi");*/
		MeraMeraNoMi = new AkumaNoMiItem(EnumFruitType.LOGIA, HikenAbility.INSTANCE, HiganAbility.INSTANCE, DaiEnkaiEnteiAbility.INSTANCE);
		registerDevilFruit(MeraMeraNoMi, "Mera Mera no Mi");        
		
		for(AkumaNoMiItem df : ModValues.devilfruits)
		{
			totalFruits++;
			for (Ability abl : df.abilities)
			{
				if (abl != null)
				{
					totalAbilities++;
					WyRegistry.registerName("ability." + WyHelper.getResourceName(abl.getName()) + ".name", abl.getName());
				}
			}
		}
		WyDebug.info("A total of " + ModValues.devilfruits.size() + " Devil Fruits have been registered");
		WyDebug.info("A total of " + totalAbilities + " abilities have been registered");
		
		event.getRegistry().registerAll(ModValues.devilfruits.toArray(new AkumaNoMiItem[0]));
    }
	
	private static void registerDevilFruit(AkumaNoMiItem item, String localizedName) 
	{
		if (item.type == EnumFruitType.LOGIA)
			ModValues.logias.add(item);
		ModValues.devilfruits.add(item);
		WyRegistry.registerItem(item, localizedName);
	}

}
