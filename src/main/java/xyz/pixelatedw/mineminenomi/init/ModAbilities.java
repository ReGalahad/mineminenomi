package xyz.pixelatedw.mineminenomi.init;

import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.EnumFruitType;
import xyz.pixelatedw.mineminenomi.abilities.bane.SpringDeathKnockAbility;
import xyz.pixelatedw.mineminenomi.abilities.bane.SpringHopperAbility;
import xyz.pixelatedw.mineminenomi.abilities.bane.SpringSnipeAbility;
import xyz.pixelatedw.mineminenomi.abilities.bari.BariBariNoPistolAbility;
import xyz.pixelatedw.mineminenomi.abilities.bari.BarrierAbility;
import xyz.pixelatedw.mineminenomi.abilities.bari.BarrierBallAbility;
import xyz.pixelatedw.mineminenomi.abilities.bari.BarrierCrashAbility;
import xyz.pixelatedw.mineminenomi.abilities.bari.BarrierbilityStairsAbility;
import xyz.pixelatedw.mineminenomi.abilities.bomu.BreezeBreathBombAbility;
import xyz.pixelatedw.mineminenomi.abilities.bomu.ExplosivePunchAbility;
import xyz.pixelatedw.mineminenomi.abilities.bomu.KickBombAbility;
import xyz.pixelatedw.mineminenomi.abilities.bomu.NoseFancyCannonAbility;
import xyz.pixelatedw.mineminenomi.abilities.bomu.ZenshinKibakuAbility;
import xyz.pixelatedw.mineminenomi.abilities.cyborg.ColaOverdriveAbility;
import xyz.pixelatedw.mineminenomi.abilities.cyborg.CoupDeVentAbility;
import xyz.pixelatedw.mineminenomi.abilities.cyborg.FreshFireAbility;
import xyz.pixelatedw.mineminenomi.abilities.cyborg.RadicalBeamAbility;
import xyz.pixelatedw.mineminenomi.abilities.cyborg.StrongRightAbility;
import xyz.pixelatedw.mineminenomi.abilities.doctor.FailedExperimentAbility;
import xyz.pixelatedw.mineminenomi.abilities.doctor.FirstAidAbility;
import xyz.pixelatedw.mineminenomi.abilities.doctor.MedicBagExplosionAbility;
import xyz.pixelatedw.mineminenomi.abilities.doku.ChloroBallAbility;
import xyz.pixelatedw.mineminenomi.abilities.doku.DokuFuguAbility;
import xyz.pixelatedw.mineminenomi.abilities.doku.DokuGumoAbility;
import xyz.pixelatedw.mineminenomi.abilities.doku.HydraAbility;
import xyz.pixelatedw.mineminenomi.abilities.doku.VenomDemonAbility;
import xyz.pixelatedw.mineminenomi.abilities.doku.VenomRoadAbility;
import xyz.pixelatedw.mineminenomi.abilities.doru.CandleHouseAbility;
import xyz.pixelatedw.mineminenomi.abilities.doru.CandleLockAbility;
import xyz.pixelatedw.mineminenomi.abilities.doru.CandleWallAbility;
import xyz.pixelatedw.mineminenomi.abilities.doru.DoruDoruArtsKenAbility;
import xyz.pixelatedw.mineminenomi.abilities.doru.DoruDoruArtsMoriAbility;
import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.KachiageHaisokuAbility;
import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.KarakusagawaraSeikenAbility;
import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.MurasameAbility;
import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.SamehadaShoteiAbility;
import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.UchimizuAbility;
import xyz.pixelatedw.mineminenomi.abilities.gasu.BlueSwordAbility;
import xyz.pixelatedw.mineminenomi.abilities.gasu.GasRobeAbility;
import xyz.pixelatedw.mineminenomi.abilities.gasu.GastanetAbility;
import xyz.pixelatedw.mineminenomi.abilities.gasu.GastilleAbility;
import xyz.pixelatedw.mineminenomi.abilities.gasu.KarakuniAbility;
import xyz.pixelatedw.mineminenomi.abilities.goe.TodorokiAbility;
import xyz.pixelatedw.mineminenomi.abilities.goro.ElThorAbility;
import xyz.pixelatedw.mineminenomi.abilities.goro.KariAbility;
import xyz.pixelatedw.mineminenomi.abilities.goro.RaigoAbility;
import xyz.pixelatedw.mineminenomi.abilities.goro.SangoAbility;
import xyz.pixelatedw.mineminenomi.abilities.goro.SparkStepAbility;
import xyz.pixelatedw.mineminenomi.abilities.goro.VoltVariAbility;
import xyz.pixelatedw.mineminenomi.abilities.gura.GekishinAbility;
import xyz.pixelatedw.mineminenomi.abilities.gura.KaishinAbility;
import xyz.pixelatedw.mineminenomi.abilities.gura.ShimaYurashiAbility;
import xyz.pixelatedw.mineminenomi.abilities.gura.TenchiMeidoAbility;
import xyz.pixelatedw.mineminenomi.abilities.hie.IceAgeAbility;
import xyz.pixelatedw.mineminenomi.abilities.hie.IceBallAbility;
import xyz.pixelatedw.mineminenomi.abilities.hie.IceBlockPartisanAbility;
import xyz.pixelatedw.mineminenomi.abilities.hie.IceBlockPheasantAbility;
import xyz.pixelatedw.mineminenomi.abilities.hie.IceSaberAbility;
import xyz.pixelatedw.mineminenomi.abilities.hie.IceTimeCapsuleAbility;
import xyz.pixelatedw.mineminenomi.abilities.horo.MiniHollowAbility;
import xyz.pixelatedw.mineminenomi.abilities.horo.NegativeHollowAbility;
import xyz.pixelatedw.mineminenomi.abilities.horo.TokuHollowAbility;
import xyz.pixelatedw.mineminenomi.abilities.horo.YutaiRidatsuAbility;
import xyz.pixelatedw.mineminenomi.abilities.magu.BakuretsuKazanAbility;
import xyz.pixelatedw.mineminenomi.abilities.magu.DaiFunkaAbility;
import xyz.pixelatedw.mineminenomi.abilities.magu.MeigoAbility;
import xyz.pixelatedw.mineminenomi.abilities.magu.RyuseiKazanAbility;
import xyz.pixelatedw.mineminenomi.abilities.mera.DaiEnkaiEnteiAbility;
import xyz.pixelatedw.mineminenomi.abilities.mera.HidarumaAbility;
import xyz.pixelatedw.mineminenomi.abilities.mera.HiganAbility;
import xyz.pixelatedw.mineminenomi.abilities.mera.HikenAbility;
import xyz.pixelatedw.mineminenomi.abilities.mera.JujikaAbility;
import xyz.pixelatedw.mineminenomi.abilities.mero.MeroMeroMellowAbility;
import xyz.pixelatedw.mineminenomi.abilities.mero.PerfumeFemurAbility;
import xyz.pixelatedw.mineminenomi.abilities.mero.PistolKissAbility;
import xyz.pixelatedw.mineminenomi.abilities.mero.SlaveArrowAbility;
import xyz.pixelatedw.mineminenomi.abilities.moku.WhiteLauncherAbility;
import xyz.pixelatedw.mineminenomi.abilities.moku.WhiteOutAbility;
import xyz.pixelatedw.mineminenomi.abilities.moku.WhiteSnakeAbility;
import xyz.pixelatedw.mineminenomi.abilities.moku.WhiteStrikeAbility;
import xyz.pixelatedw.mineminenomi.abilities.nikyu.HanpatsuAbility;
import xyz.pixelatedw.mineminenomi.abilities.nikyu.PadHoAbility;
import xyz.pixelatedw.mineminenomi.abilities.nikyu.TsuppariPadHoAbility;
import xyz.pixelatedw.mineminenomi.abilities.nikyu.UrsusShockAbility;
import xyz.pixelatedw.mineminenomi.abilities.noro.KyubiRushAbility;
import xyz.pixelatedw.mineminenomi.abilities.noro.NoroNoroBeamAbility;
import xyz.pixelatedw.mineminenomi.abilities.noro.NoroNoroBeamSwordAbility;
import xyz.pixelatedw.mineminenomi.abilities.ope.CounterShockAbility;
import xyz.pixelatedw.mineminenomi.abilities.ope.GammaKnifeAbility;
import xyz.pixelatedw.mineminenomi.abilities.ope.InjectionShotAbility;
import xyz.pixelatedw.mineminenomi.abilities.ope.MesAbility;
import xyz.pixelatedw.mineminenomi.abilities.ope.RoomAbility;
import xyz.pixelatedw.mineminenomi.abilities.ope.ShamblesAbility;
import xyz.pixelatedw.mineminenomi.abilities.ope.TaktAbility;
import xyz.pixelatedw.mineminenomi.abilities.pika.AmaNoMurakumoAbility;
import xyz.pixelatedw.mineminenomi.abilities.pika.AmaterasuAbility;
import xyz.pixelatedw.mineminenomi.abilities.pika.FlashAbility;
import xyz.pixelatedw.mineminenomi.abilities.pika.YasakaniNoMagatamaAbility;
import xyz.pixelatedw.mineminenomi.abilities.pika.YataNoKagamiAbility;
import xyz.pixelatedw.mineminenomi.abilities.rokushiki.GeppoAbility;
import xyz.pixelatedw.mineminenomi.abilities.rokushiki.KamieAbility;
import xyz.pixelatedw.mineminenomi.abilities.rokushiki.RankyakuAbility;
import xyz.pixelatedw.mineminenomi.abilities.rokushiki.ShiganAbility;
import xyz.pixelatedw.mineminenomi.abilities.rokushiki.SoruAbility;
import xyz.pixelatedw.mineminenomi.abilities.rokushiki.TekkaiAbility;
import xyz.pixelatedw.mineminenomi.abilities.sabi.RustTouchAbility;
import xyz.pixelatedw.mineminenomi.abilities.sniper.KaenBoshiAbility;
import xyz.pixelatedw.mineminenomi.abilities.sniper.KemuriBoshiAbility;
import xyz.pixelatedw.mineminenomi.abilities.sniper.RenpatsuNamariBoshiAbility;
import xyz.pixelatedw.mineminenomi.abilities.sniper.SakuretsuSabotenBoshiAbility;
import xyz.pixelatedw.mineminenomi.abilities.suke.ShishaNoTeAbility;
import xyz.pixelatedw.mineminenomi.abilities.suke.SkattingAbility;
import xyz.pixelatedw.mineminenomi.abilities.suke.SukePunchAbility;
import xyz.pixelatedw.mineminenomi.abilities.suna.BarjanAbility;
import xyz.pixelatedw.mineminenomi.abilities.suna.DesertEncierroAbility;
import xyz.pixelatedw.mineminenomi.abilities.suna.DesertGirasoleAbility;
import xyz.pixelatedw.mineminenomi.abilities.suna.DesertSpadaAbility;
import xyz.pixelatedw.mineminenomi.abilities.suna.GroundDeathAbility;
import xyz.pixelatedw.mineminenomi.abilities.suna.SablesAbility;
import xyz.pixelatedw.mineminenomi.abilities.supa.AtomicSpurtAbility;
import xyz.pixelatedw.mineminenomi.abilities.supa.SparClawAbility;
import xyz.pixelatedw.mineminenomi.abilities.supa.SparklingDaisyAbility;
import xyz.pixelatedw.mineminenomi.abilities.supa.SpiderAbility;
import xyz.pixelatedw.mineminenomi.abilities.supa.SpiralHollowAbility;
import xyz.pixelatedw.mineminenomi.abilities.swordsman.OTatsumakiAbility;
import xyz.pixelatedw.mineminenomi.abilities.swordsman.SanbyakurokujuPoundHoAbility;
import xyz.pixelatedw.mineminenomi.abilities.swordsman.ShiShishiSonsonAbility;
import xyz.pixelatedw.mineminenomi.abilities.swordsman.YakkodoriAbility;
import xyz.pixelatedw.mineminenomi.abilities.yuki.FubukiAbility;
import xyz.pixelatedw.mineminenomi.abilities.yuki.KamakuraAbility;
import xyz.pixelatedw.mineminenomi.abilities.yuki.KamakuraJussoshiAbility;
import xyz.pixelatedw.mineminenomi.abilities.yuki.TabiraYukiAbility;
import xyz.pixelatedw.mineminenomi.abilities.yuki.YukiGakiAbility;
import xyz.pixelatedw.mineminenomi.abilities.yuki.YukiRabiAbility;
import xyz.pixelatedw.mineminenomi.abilities.zushi.AbareHimatsuriAbility;
import xyz.pixelatedw.mineminenomi.abilities.zushi.JigokuTabiAbility;
import xyz.pixelatedw.mineminenomi.abilities.zushi.MokoAbility;
import xyz.pixelatedw.mineminenomi.abilities.zushi.SagariNoRyuseiAbility;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.APIRegistries;
import xyz.pixelatedw.wypi.WyRegistry;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.debug.WyDebug;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModAbilities
{

	public static final AkumaNoMiItem KACHI_KACHI_NO_MI = new AkumaNoMiItem("Kachi Kachi no Mi", EnumFruitType.PARAMECIA);
	public static final AkumaNoMiItem DOA_DOA_NO_MI = new AkumaNoMiItem("Doa Doa no Mi", EnumFruitType.PARAMECIA);
	public static final AkumaNoMiItem USHI_USHI_NO_MI_GIRAFFE = new AkumaNoMiItem("Ushi Ushi no Mi, Model Giraffe", EnumFruitType.ZOAN);
	public static final AkumaNoMiItem MOGU_MOGU_NO_MI = new AkumaNoMiItem("Mogu Mogu no Mi", EnumFruitType.ZOAN);
	public static final AkumaNoMiItem CHIYU_CHIYU_NO_MI = new AkumaNoMiItem("Chiyu Chiyu no Mi", EnumFruitType.PARAMECIA);
	public static final AkumaNoMiItem HITO_HITO_NO_MI = new AkumaNoMiItem("Hito Hito no Mi", EnumFruitType.ZOAN);
	public static final AkumaNoMiItem SABI_SABI_NO_MI = new AkumaNoMiItem("Sabi Sabi no Mi", EnumFruitType.PARAMECIA, RustTouchAbility.INSTANCE);
	public static final AkumaNoMiItem ZOU_ZOU_NO_MI = new AkumaNoMiItem("Zou Zou no Mi", EnumFruitType.ZOAN);
	public static final AkumaNoMiItem YOMI_YOMI_NO_MI = new AkumaNoMiItem("Yomi Yomi no Mi", EnumFruitType.PARAMECIA);
	public static final AkumaNoMiItem BAKU_BAKU_NO_MI = new AkumaNoMiItem("Baku Baku no Mi", EnumFruitType.PARAMECIA);
	public static final AkumaNoMiItem TORI_TORI_NO_MI_PHOENIX = new AkumaNoMiItem("Tori Tori no Mi, Model: Phoenix", EnumFruitType.ZOAN);
	public static final AkumaNoMiItem USHI_USHI_NO_MI_BISON = new AkumaNoMiItem("Ushi Ushi no Mi, Model: Bison", EnumFruitType.ZOAN);
	public static final AkumaNoMiItem HORU_HORU_NO_MI = new AkumaNoMiItem("Horu Horu no Mi", EnumFruitType.PARAMECIA);
	public static final AkumaNoMiItem KILO_KILO_NO_MI = new AkumaNoMiItem("Kilo Kilo no Mi", EnumFruitType.PARAMECIA);
	public static final AkumaNoMiItem GOE_GOE_NO_MI = new AkumaNoMiItem("Goe Goe no Mi", EnumFruitType.PARAMECIA, TodorokiAbility.INSTANCE);
	public static final AkumaNoMiItem MERO_MERO_NO_MI = new AkumaNoMiItem("Mero Mero no Mi", EnumFruitType.PARAMECIA, MeroMeroMellowAbility.INSTANCE, PistolKissAbility.INSTANCE, SlaveArrowAbility.INSTANCE, PerfumeFemurAbility.INSTANCE);
	public static final AkumaNoMiItem ORI_ORI_NO_MI = new AkumaNoMiItem("Ori Ori no Mi", EnumFruitType.PARAMECIA);
	public static final AkumaNoMiItem SUPA_SUPA_NO_MI = new AkumaNoMiItem("Supa Supa no Mi", EnumFruitType.PARAMECIA, SpiderAbility.INSTANCE, SparClawAbility.INSTANCE, SpiralHollowAbility.INSTANCE, SparklingDaisyAbility.INSTANCE, AtomicSpurtAbility.INSTANCE);
	public static final AkumaNoMiItem HORO_HORO_NO_MI = new AkumaNoMiItem("Horo Horo no Mi", EnumFruitType.PARAMECIA, NegativeHollowAbility.INSTANCE, MiniHollowAbility.INSTANCE, TokuHollowAbility.INSTANCE, YutaiRidatsuAbility.INSTANCE);
	public static final AkumaNoMiItem ITO_ITO_NO_MI = new AkumaNoMiItem("Ito Ito no Mi", EnumFruitType.PARAMECIA);
	public static final AkumaNoMiItem YAMI_YAMI_NO_MI = new AkumaNoMiItem("Yami Yami no Mi", EnumFruitType.LOGIA);
	public static final AkumaNoMiItem ZUSHI_ZUSHI_NO_MI = new AkumaNoMiItem("Zushi Zushi no Mi", EnumFruitType.PARAMECIA, JigokuTabiAbility.INSTANCE, SagariNoRyuseiAbility.INSTANCE, MokoAbility.INSTANCE, AbareHimatsuriAbility.INSTANCE);
	public static final AkumaNoMiItem YUKI_YUKI_NO_MI = new AkumaNoMiItem("Yuki Yuki no Mi", EnumFruitType.LOGIA, KamakuraAbility.INSTANCE, YukiRabiAbility.INSTANCE, KamakuraJussoshiAbility.INSTANCE, TabiraYukiAbility.INSTANCE, YukiGakiAbility.INSTANCE, FubukiAbility.INSTANCE);
	public static final AkumaNoMiItem GASU_GASU_NO_MI = new AkumaNoMiItem("Gasu Gasu no Mi", EnumFruitType.LOGIA, GasRobeAbility.INSTANCE, GastanetAbility.INSTANCE, GastilleAbility.INSTANCE, BlueSwordAbility.INSTANCE, KarakuniAbility.INSTANCE);
	public static final AkumaNoMiItem BARI_BARI_NO_MI = new AkumaNoMiItem("Bari Bari no Mi", EnumFruitType.PARAMECIA, BarrierAbility.INSTANCE, BarrierBallAbility.INSTANCE, BarrierCrashAbility.INSTANCE, BariBariNoPistolAbility.INSTANCE, BarrierbilityStairsAbility.INSTANCE);
	public static final AkumaNoMiItem DOKU_DOKU_NO_MI = new AkumaNoMiItem("Doku Doku no Mi", EnumFruitType.PARAMECIA, HydraAbility.INSTANCE, ChloroBallAbility.INSTANCE, DokuFuguAbility.INSTANCE, VenomRoadAbility.INSTANCE, DokuGumoAbility.INSTANCE, VenomDemonAbility.INSTANCE);
	public static final AkumaNoMiItem DORU_DORU_NO_MI = new AkumaNoMiItem("Doru Doru no Mi", EnumFruitType.PARAMECIA, CandleWallAbility.INSTANCE, CandleHouseAbility.INSTANCE, DoruDoruArtsMoriAbility.INSTANCE, DoruDoruArtsKenAbility.INSTANCE, CandleLockAbility.INSTANCE);
	public static final AkumaNoMiItem MAGU_MAGU_NO_MI = new AkumaNoMiItem("Magu Magu no Mi", EnumFruitType.LOGIA, DaiFunkaAbility.INSTANCE, RyuseiKazanAbility.INSTANCE, MeigoAbility.INSTANCE, BakuretsuKazanAbility.INSTANCE);
	public static final AkumaNoMiItem SUNA_SUNA_NO_MI = new AkumaNoMiItem("Suna Suna no Mi", EnumFruitType.LOGIA, DesertSpadaAbility.INSTANCE, SablesAbility.INSTANCE, BarjanAbility.INSTANCE, DesertEncierroAbility.INSTANCE, GroundDeathAbility.INSTANCE, DesertGirasoleAbility.INSTANCE);
	public static final AkumaNoMiItem KAGE_KAGE_NO_MI = new AkumaNoMiItem("Kage Kage no Mi", EnumFruitType.PARAMECIA);
	public static final AkumaNoMiItem GURA_GURA_NO_MI = new AkumaNoMiItem("Gura Gura no Mi", EnumFruitType.PARAMECIA, KaishinAbility.INSTANCE, GekishinAbility.INSTANCE, TenchiMeidoAbility.INSTANCE, ShimaYurashiAbility.INSTANCE);
	public static final AkumaNoMiItem BOMU_BOMU_NO_MI = new AkumaNoMiItem("Bomu Bomu no Mi", EnumFruitType.PARAMECIA, NoseFancyCannonAbility.INSTANCE, KickBombAbility.INSTANCE, ZenshinKibakuAbility.INSTANCE, ExplosivePunchAbility.INSTANCE, BreezeBreathBombAbility.INSTANCE);
	public static final AkumaNoMiItem NIKYU_NIKYU_NO_MI = new AkumaNoMiItem("Nikyu Nikyu no Mi", EnumFruitType.PARAMECIA, PadHoAbility.INSTANCE, HanpatsuAbility.INSTANCE, TsuppariPadHoAbility.INSTANCE, UrsusShockAbility.INSTANCE);
	public static final AkumaNoMiItem MOKU_MOKU_NO_MI = new AkumaNoMiItem("Moku Moku no Mi", EnumFruitType.LOGIA, WhiteOutAbility.INSTANCE, WhiteSnakeAbility.INSTANCE, WhiteLauncherAbility.INSTANCE, WhiteStrikeAbility.INSTANCE);
	public static final AkumaNoMiItem GORO_GORO_NO_MI = new AkumaNoMiItem("Goro Goro no Mi", EnumFruitType.LOGIA, ElThorAbility.INSTANCE, VoltVariAbility.INSTANCE, KariAbility.INSTANCE, SangoAbility.INSTANCE, RaigoAbility.INSTANCE, SparkStepAbility.INSTANCE);
	public static final AkumaNoMiItem NORO_NORO_NO_MI = new AkumaNoMiItem("Noro Noro no Mi", EnumFruitType.PARAMECIA, NoroNoroBeamAbility.INSTANCE, NoroNoroBeamSwordAbility.INSTANCE, KyubiRushAbility.INSTANCE);
	public static final AkumaNoMiItem OPE_OPE_NO_MI = new AkumaNoMiItem("Ope Ope no Mi", EnumFruitType.PARAMECIA, RoomAbility.INSTANCE, CounterShockAbility.INSTANCE, MesAbility.INSTANCE, ShamblesAbility.INSTANCE, TaktAbility.INSTANCE, InjectionShotAbility.INSTANCE, GammaKnifeAbility.INSTANCE);
	public static final AkumaNoMiItem SUKE_SUKE_NO_MI = new AkumaNoMiItem("Suke Suke no Mi", EnumFruitType.PARAMECIA, SkattingAbility.INSTANCE, ShishaNoTeAbility.INSTANCE, SukePunchAbility.INSTANCE);
	public static final AkumaNoMiItem GOMU_GOMU_NO_MI = new AkumaNoMiItem("Gomu Gomu no Mi", EnumFruitType.PARAMECIA);
	public static final AkumaNoMiItem PIKA_PIKA_NO_MI = new AkumaNoMiItem("Pika Pika no Mi", EnumFruitType.LOGIA, YataNoKagamiAbility.INSTANCE, YasakaniNoMagatamaAbility.INSTANCE, AmaNoMurakumoAbility.INSTANCE, AmaterasuAbility.INSTANCE, FlashAbility.INSTANCE);
	public static final AkumaNoMiItem BANE_BANE_NO_MI = new AkumaNoMiItem("Bane Bane no Mi", EnumFruitType.PARAMECIA, SpringHopperAbility.INSTANCE, SpringSnipeAbility.INSTANCE, SpringDeathKnockAbility.INSTANCE);
	public static final AkumaNoMiItem HIE_HIE_NO_MI = new AkumaNoMiItem("Hie Hie no Mi", EnumFruitType.LOGIA, IceBlockPartisanAbility.INSTANCE, IceAgeAbility.INSTANCE, IceBallAbility.INSTANCE, IceSaberAbility.INSTANCE, IceTimeCapsuleAbility.INSTANCE, IceBlockPheasantAbility.INSTANCE);
	public static final AkumaNoMiItem MERA_MERA_NO_MI = new AkumaNoMiItem("Mera Mera no Mi", EnumFruitType.LOGIA, HikenAbility.INSTANCE, HiganAbility.INSTANCE, DaiEnkaiEnteiAbility.INSTANCE, HidarumaAbility.INSTANCE, JujikaAbility.INSTANCE);

	static
	{
		for (AkumaNoMiItem df : ModValues.devilfruits)
		{
			WyRegistry.registerItem(df, df.getDevilFruitName());
			for (Ability abl : df.abilities)
			{
				if (abl != null)
					WyRegistry.registerAbility(abl);
			}
		}

		// Human
		WyRegistry.registerAbility(SoruAbility.INSTANCE);
		WyRegistry.registerAbility(TekkaiAbility.INSTANCE);
		WyRegistry.registerAbility(ShiganAbility.INSTANCE);
		WyRegistry.registerAbility(GeppoAbility.INSTANCE);
		WyRegistry.registerAbility(RankyakuAbility.INSTANCE);
		WyRegistry.registerAbility(KamieAbility.INSTANCE);
		
		// Fishman
		WyRegistry.registerAbility(UchimizuAbility.INSTANCE);
		WyRegistry.registerAbility(MurasameAbility.INSTANCE);
		WyRegistry.registerAbility(KachiageHaisokuAbility.INSTANCE);
		WyRegistry.registerAbility(SamehadaShoteiAbility.INSTANCE);
		WyRegistry.registerAbility(KarakusagawaraSeikenAbility.INSTANCE);
		
		// Cyborg
		WyRegistry.registerAbility(FreshFireAbility.INSTANCE);
		WyRegistry.registerAbility(ColaOverdriveAbility.INSTANCE);
		WyRegistry.registerAbility(StrongRightAbility.INSTANCE);
		WyRegistry.registerAbility(RadicalBeamAbility.INSTANCE);
		WyRegistry.registerAbility(CoupDeVentAbility.INSTANCE);
		
		// Swordsman
		WyRegistry.registerAbility(ShiShishiSonsonAbility.INSTANCE);
		WyRegistry.registerAbility(YakkodoriAbility.INSTANCE);
		WyRegistry.registerAbility(SanbyakurokujuPoundHoAbility.INSTANCE);
		WyRegistry.registerAbility(OTatsumakiAbility.INSTANCE);

		// Sniper
		WyRegistry.registerAbility(KaenBoshiAbility.INSTANCE);
		WyRegistry.registerAbility(KemuriBoshiAbility.INSTANCE);
		WyRegistry.registerAbility(RenpatsuNamariBoshiAbility.INSTANCE);
		WyRegistry.registerAbility(SakuretsuSabotenBoshiAbility.INSTANCE);
		
		// Doctor
		WyRegistry.registerAbility(FirstAidAbility.INSTANCE);
		WyRegistry.registerAbility(MedicBagExplosionAbility.INSTANCE);
		WyRegistry.registerAbility(FailedExperimentAbility.INSTANCE);

		WyDebug.debug("A total of " + ModValues.devilfruits.size() + " Devil Fruits have been registered");
		WyDebug.debug("A total of " + APIRegistries.ABILITIES.getEntries().size() + " abilities have been registered");
	}
}
