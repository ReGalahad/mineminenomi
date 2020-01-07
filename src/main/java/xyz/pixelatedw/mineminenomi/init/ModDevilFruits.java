package xyz.pixelatedw.mineminenomi.init;

import java.util.HashMap;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.pixelatedw.mineminenomi.EnumFruitType;
import xyz.pixelatedw.mineminenomi.Env;
import xyz.pixelatedw.mineminenomi.abilities.BakuAbilities;
import xyz.pixelatedw.mineminenomi.abilities.BaneAbilities;
import xyz.pixelatedw.mineminenomi.abilities.BariAbilities;
import xyz.pixelatedw.mineminenomi.abilities.BomuAbilities;
import xyz.pixelatedw.mineminenomi.abilities.ChiyuAbilities;
import xyz.pixelatedw.mineminenomi.abilities.CyborgAbilities;
import xyz.pixelatedw.mineminenomi.abilities.DoaAbilities;
import xyz.pixelatedw.mineminenomi.abilities.DokuAbilities;
import xyz.pixelatedw.mineminenomi.abilities.DoruAbilities;
import xyz.pixelatedw.mineminenomi.abilities.FishKarateAbilities;
import xyz.pixelatedw.mineminenomi.abilities.GasuAbilities;
import xyz.pixelatedw.mineminenomi.abilities.GoeAbilities;
import xyz.pixelatedw.mineminenomi.abilities.GomuAbilities;
import xyz.pixelatedw.mineminenomi.abilities.GoroAbilities;
import xyz.pixelatedw.mineminenomi.abilities.GuraAbilities;
import xyz.pixelatedw.mineminenomi.abilities.HakiAbilities;
import xyz.pixelatedw.mineminenomi.abilities.HieAbilities;
import xyz.pixelatedw.mineminenomi.abilities.HoroAbilities;
import xyz.pixelatedw.mineminenomi.abilities.HoruAbilities;
import xyz.pixelatedw.mineminenomi.abilities.ItoAbilities;
import xyz.pixelatedw.mineminenomi.abilities.JuryoAbilities;
import xyz.pixelatedw.mineminenomi.abilities.KachiAbilities;
import xyz.pixelatedw.mineminenomi.abilities.KageAbilities;
import xyz.pixelatedw.mineminenomi.abilities.MaguAbilities;
import xyz.pixelatedw.mineminenomi.abilities.MeraAbilities;
import xyz.pixelatedw.mineminenomi.abilities.MeroAbilities;
import xyz.pixelatedw.mineminenomi.abilities.MoguAbilities;
import xyz.pixelatedw.mineminenomi.abilities.MokuAbilities;
import xyz.pixelatedw.mineminenomi.abilities.NikyuAbilities;
import xyz.pixelatedw.mineminenomi.abilities.NoroAbilities;
import xyz.pixelatedw.mineminenomi.abilities.OpeAbilities;
import xyz.pixelatedw.mineminenomi.abilities.OriAbilities;
import xyz.pixelatedw.mineminenomi.abilities.PikaAbilities;
import xyz.pixelatedw.mineminenomi.abilities.RokushikiAbilities;
import xyz.pixelatedw.mineminenomi.abilities.SabiAbilities;
import xyz.pixelatedw.mineminenomi.abilities.SniperAbilities;
import xyz.pixelatedw.mineminenomi.abilities.SukeAbilities;
import xyz.pixelatedw.mineminenomi.abilities.SunaAbilities;
import xyz.pixelatedw.mineminenomi.abilities.SupaAbilities;
import xyz.pixelatedw.mineminenomi.abilities.SwordsmanAbilities;
import xyz.pixelatedw.mineminenomi.abilities.ToriPhoenixAbilities;
import xyz.pixelatedw.mineminenomi.abilities.UshiBisonAbilities;
import xyz.pixelatedw.mineminenomi.abilities.UshiGiraffeAbilities;
import xyz.pixelatedw.mineminenomi.abilities.YamiAbilities;
import xyz.pixelatedw.mineminenomi.abilities.YomiAbilities;
import xyz.pixelatedw.mineminenomi.abilities.YukiAbilities;
import xyz.pixelatedw.mineminenomi.abilities.ZouAbilities;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.debug.WyDebug;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.BakuProjectiles;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.BaneProjectiles;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.BariProjectiles;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.BomuProjectiles;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.CyborgProjectiles;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.DokuProjectiles;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.DoruProjectiles;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.ExtraProjectiles;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.FishKarateProjectiles;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.GasuProjectiles;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.GoeProjectiles;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.GomuProjectiles;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.GoroProjectiles;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.GuraProjectiles;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.HieProjectiles;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.HoroProjectiles;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.ItoProjectiles;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.JuryoProjectiles;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.KageProjectiles;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.MaguProjectiles;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.MeraProjectiles;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.MeroProjectiles;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.MokuProjectiles;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.NikyuProjectiles;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.NoroProjectiles;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.OpeProjectiles;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.OriProjectiles;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.PikaProjectiles;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.RokushikiProjectiles;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.SniperProjectiles;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.SukeProjectiles;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.SunaProjectiles;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.SupaProjectiles;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.SwordsmanProjectiles;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.ToriPhoenixProjectiles;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.UshiGiraffeProjectiles;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.YamiProjectiles;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.YukiProjectiles;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.ZouProjectiles;
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

	private static final Ability[][] ALL_ABILITIES = 
		{
			// Devil Fruit Abilities lists
			MeraAbilities.abilitiesArray, HieAbilities.abilitiesArray, BaneAbilities.abilitiesArray, PikaAbilities.abilitiesArray, SukeAbilities.abilitiesArray, 
			OpeAbilities.abilitiesArray, GoroAbilities.abilitiesArray, MokuAbilities.abilitiesArray, NikyuAbilities.abilitiesArray, BomuAbilities.abilitiesArray, GuraAbilities.abilitiesArray,
			KageAbilities.abilitiesArray, SunaAbilities.abilitiesArray, MaguAbilities.abilitiesArray, DoruAbilities.abilitiesArray, DokuAbilities.abilitiesArray, GasuAbilities.abilitiesArray,
			YukiAbilities.abilitiesArray, ItoAbilities.abilitiesArray, BariAbilities.abilitiesArray, HoroAbilities.abilitiesArray, YamiAbilities.abilitiesArray, GoeAbilities.abilitiesArray,
			NoroAbilities.abilitiesArray, GomuAbilities.abilitiesArray, JuryoAbilities.abilitiesArray, UshiBisonAbilities.abilitiesArray, ToriPhoenixAbilities.abilitiesArray,
			/*KiloAbilities.abilitiesArray,*/ BakuAbilities.abilitiesArray, OriAbilities.abilitiesArray, YomiAbilities.abilitiesArray, ZouAbilities.abilitiesArray,
			SabiAbilities.abilitiesArray, SupaAbilities.abilitiesArray, MeroAbilities.abilitiesArray, ChiyuAbilities.abilitiesArray, HoruAbilities.abilitiesArray, MoguAbilities.abilitiesArray,
			DoaAbilities.abilitiesArray, KachiAbilities.abilitiesArray, UshiGiraffeAbilities.abilitiesArray,

			// Special Abilities lists
			RokushikiAbilities.abilitiesArray, FishKarateAbilities.abilitiesArray, CyborgAbilities.abilitiesArray, 
			SniperAbilities.abilitiesArray, SwordsmanAbilities.abilitiesArray, 
			HakiAbilities.abilitiesArray
		};
	
	public static final HashMap[] ALL_PROJECTILES = new HashMap[] 
		{
			// Devil Fruit projectiles
			MeraProjectiles.projectiles, HieProjectiles.projectiles, BaneProjectiles.projectiles, PikaProjectiles.projectiles, NoroProjectiles.projectiles, SukeProjectiles.projectiles, OpeProjectiles.projectiles,
			GoroProjectiles.projectiles, MokuProjectiles.projectiles, NikyuProjectiles.projectiles, BomuProjectiles.projectiles, GuraProjectiles.projectiles, KageProjectiles.projectiles, SunaProjectiles.projectiles,
			MaguProjectiles.projectiles, DoruProjectiles.projectiles, DokuProjectiles.projectiles, GasuProjectiles.projectiles, YukiProjectiles.projectiles, ItoProjectiles.projectiles, BariProjectiles.projectiles,
			HoroProjectiles.projectiles, YamiProjectiles.projectiles, GoeProjectiles.projectiles, GomuProjectiles.projectiles, JuryoProjectiles.projectiles, ToriPhoenixProjectiles.projectiles,
			BakuProjectiles.projectiles, SupaProjectiles.projectiles, MeroProjectiles.projectiles, OriProjectiles.projectiles, UshiGiraffeProjectiles.projectiles, ZouProjectiles.projectiles,

			// Special Abilities projectiles
			RokushikiProjectiles.projectiles, FishKarateProjectiles.projectiles, CyborgProjectiles.projectiles, ExtraProjectiles.projectiles, 
			SwordsmanProjectiles.projectiles, SniperProjectiles.projectiles
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
		MeraMeraNoMi = new AkumaNoMiItem(EnumFruitType.LOGIA, MeraAbilities.abilitiesArray);
		registerDevilFruit(MeraMeraNoMi, "Mera Mera no Mi");        
		
		if(WyDebug.isDebug())
		{
			for (int i = 0; i < ALL_ABILITIES.length; i++)
			{		
				totalFruits++;
				for (Ability a : ALL_ABILITIES[i])
					if (a != null)
					{
						totalAbilities++;
					}
			}
			WyDebug.info("A total of " + ModValues.devilfruits.size() + " Devil Fruits have been registered");
			WyDebug.info("A total of " + totalAbilities + " abilities have been registered");
		}
		
		event.getRegistry().registerAll(ModValues.devilfruits.toArray(new AkumaNoMiItem[0]));
    }
	
	public static void registerDevilFruit(AkumaNoMiItem item, String localizedName) 
	{
		if (item.type == EnumFruitType.LOGIA)
			ModValues.logias.add(item);
		ModValues.devilfruits.add(item);
		WyRegistry.registerItem(item, localizedName);
	}
	
}
