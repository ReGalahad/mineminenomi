package xyz.pixelatedw.mineminenomi.init;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.abilities.LogiaInvulnerabilityAbility;
import xyz.pixelatedw.mineminenomi.abilities.SpecialFlyAbility;
import xyz.pixelatedw.mineminenomi.abilities.ZoomAbility;
import xyz.pixelatedw.mineminenomi.abilities.artofweather.*;
import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.*;
import xyz.pixelatedw.mineminenomi.abilities.baku.BakuMunchAbility;
import xyz.pixelatedw.mineminenomi.abilities.baku.BakuTsuihoAbility;
import xyz.pixelatedw.mineminenomi.abilities.baku.BeroCannonAbility;
import xyz.pixelatedw.mineminenomi.abilities.bane.SpringDeathKnockAbility;
import xyz.pixelatedw.mineminenomi.abilities.bane.SpringHopperAbility;
import xyz.pixelatedw.mineminenomi.abilities.bane.SpringSnipeAbility;
import xyz.pixelatedw.mineminenomi.abilities.bari.*;
import xyz.pixelatedw.mineminenomi.abilities.bomu.*;
import xyz.pixelatedw.mineminenomi.abilities.chiyu.ChiyupopoAbility;
import xyz.pixelatedw.mineminenomi.abilities.chiyu.HealingTouchAbility;
import xyz.pixelatedw.mineminenomi.abilities.cyborg.*;
import xyz.pixelatedw.mineminenomi.abilities.doa.AirDoorAbility;
import xyz.pixelatedw.mineminenomi.abilities.doa.DoorDoorAbility;
import xyz.pixelatedw.mineminenomi.abilities.doa.KaitenDoorAbility;
import xyz.pixelatedw.mineminenomi.abilities.doctor.FailedExperimentAbility;
import xyz.pixelatedw.mineminenomi.abilities.doctor.FirstAidAbility;
import xyz.pixelatedw.mineminenomi.abilities.doctor.MedicBagExplosionAbility;
import xyz.pixelatedw.mineminenomi.abilities.doku.*;
import xyz.pixelatedw.mineminenomi.abilities.doru.*;
import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.*;
import xyz.pixelatedw.mineminenomi.abilities.gasu.*;
import xyz.pixelatedw.mineminenomi.abilities.goe.TodorokiAbility;
import xyz.pixelatedw.mineminenomi.abilities.gomu.*;
import xyz.pixelatedw.mineminenomi.abilities.goro.*;
import xyz.pixelatedw.mineminenomi.abilities.gura.GekishinAbility;
import xyz.pixelatedw.mineminenomi.abilities.gura.KaishinAbility;
import xyz.pixelatedw.mineminenomi.abilities.gura.ShimaYurashiAbility;
import xyz.pixelatedw.mineminenomi.abilities.gura.TenchiMeidoAbility;
import xyz.pixelatedw.mineminenomi.abilities.haki.*;
import xyz.pixelatedw.mineminenomi.abilities.hie.*;
import xyz.pixelatedw.mineminenomi.abilities.horo.MiniHollowAbility;
import xyz.pixelatedw.mineminenomi.abilities.horo.NegativeHollowAbility;
import xyz.pixelatedw.mineminenomi.abilities.horo.TokuHollowAbility;
import xyz.pixelatedw.mineminenomi.abilities.horo.YutaiRidatsuAbility;
import xyz.pixelatedw.mineminenomi.abilities.horu.ChiyuHormoneAbility;
import xyz.pixelatedw.mineminenomi.abilities.horu.GanmenSeichoHormoneAbility;
import xyz.pixelatedw.mineminenomi.abilities.horu.OnnaHormoneAbility;
import xyz.pixelatedw.mineminenomi.abilities.horu.TensionHormoneAbility;
import xyz.pixelatedw.mineminenomi.abilities.ito.*;
import xyz.pixelatedw.mineminenomi.abilities.kachi.EvaporateAbility;
import xyz.pixelatedw.mineminenomi.abilities.kachi.HotBoilingSpecialAbility;
import xyz.pixelatedw.mineminenomi.abilities.kage.*;
import xyz.pixelatedw.mineminenomi.abilities.kilo.KiloPress10000Ability;
import xyz.pixelatedw.mineminenomi.abilities.kilo.KiloPress1Ability;
import xyz.pixelatedw.mineminenomi.abilities.magu.BakuretsuKazanAbility;
import xyz.pixelatedw.mineminenomi.abilities.magu.DaiFunkaAbility;
import xyz.pixelatedw.mineminenomi.abilities.magu.MeigoAbility;
import xyz.pixelatedw.mineminenomi.abilities.magu.RyuseiKazanAbility;
import xyz.pixelatedw.mineminenomi.abilities.mera.*;
import xyz.pixelatedw.mineminenomi.abilities.mero.MeroMeroMellowAbility;
import xyz.pixelatedw.mineminenomi.abilities.mero.PerfumeFemurAbility;
import xyz.pixelatedw.mineminenomi.abilities.mero.PistolKissAbility;
import xyz.pixelatedw.mineminenomi.abilities.mero.SlaveArrowAbility;
import xyz.pixelatedw.mineminenomi.abilities.mogu.MoguHeavyPointAbility;
import xyz.pixelatedw.mineminenomi.abilities.mogu.MoguraBananaAbility;
import xyz.pixelatedw.mineminenomi.abilities.mogu.MoguraTonpoAbility;
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
import xyz.pixelatedw.mineminenomi.abilities.ope.*;
import xyz.pixelatedw.mineminenomi.abilities.pika.*;
import xyz.pixelatedw.mineminenomi.abilities.rokushiki.*;
import xyz.pixelatedw.mineminenomi.abilities.sabi.RustTouchAbility;
import xyz.pixelatedw.mineminenomi.abilities.sniper.KaenBoshiAbility;
import xyz.pixelatedw.mineminenomi.abilities.sniper.KemuriBoshiAbility;
import xyz.pixelatedw.mineminenomi.abilities.sniper.RenpatsuNamariBoshiAbility;
import xyz.pixelatedw.mineminenomi.abilities.sniper.SakuretsuSabotenBoshiAbility;
import xyz.pixelatedw.mineminenomi.abilities.suke.ShishaNoTeAbility;
import xyz.pixelatedw.mineminenomi.abilities.suke.SkattingAbility;
import xyz.pixelatedw.mineminenomi.abilities.suke.SukePunchAbility;
import xyz.pixelatedw.mineminenomi.abilities.suna.*;
import xyz.pixelatedw.mineminenomi.abilities.supa.*;
import xyz.pixelatedw.mineminenomi.abilities.swordsman.OTatsumakiAbility;
import xyz.pixelatedw.mineminenomi.abilities.swordsman.SanbyakurokujuPoundHoAbility;
import xyz.pixelatedw.mineminenomi.abilities.swordsman.ShiShishiSonsonAbility;
import xyz.pixelatedw.mineminenomi.abilities.swordsman.YakkodoriAbility;
import xyz.pixelatedw.mineminenomi.abilities.toriphoenix.*;
import xyz.pixelatedw.mineminenomi.abilities.ushibison.BisonHeavyPointAbility;
import xyz.pixelatedw.mineminenomi.abilities.ushibison.BisonWalkPointAbility;
import xyz.pixelatedw.mineminenomi.abilities.ushibison.FiddleBanffAbility;
import xyz.pixelatedw.mineminenomi.abilities.ushibison.KokuteiCrossAbility;
import xyz.pixelatedw.mineminenomi.abilities.ushigiraffe.BiganAbility;
import xyz.pixelatedw.mineminenomi.abilities.ushigiraffe.GiraffeHeavyPointAbility;
import xyz.pixelatedw.mineminenomi.abilities.ushigiraffe.GiraffeWalkPointAbility;
import xyz.pixelatedw.mineminenomi.abilities.yami.*;
import xyz.pixelatedw.mineminenomi.abilities.yomi.KasuriutaFubukiGiriAbility;
import xyz.pixelatedw.mineminenomi.abilities.yomi.SoulParadeAbility;
import xyz.pixelatedw.mineminenomi.abilities.yomi.YomiNoReikiAbility;
import xyz.pixelatedw.mineminenomi.abilities.yuki.*;
import xyz.pixelatedw.mineminenomi.abilities.zou.*;
import xyz.pixelatedw.mineminenomi.abilities.zushi.AbareHimatsuriAbility;
import xyz.pixelatedw.mineminenomi.abilities.zushi.JigokuTabiAbility;
import xyz.pixelatedw.mineminenomi.abilities.zushi.MokoAbility;
import xyz.pixelatedw.mineminenomi.abilities.zushi.SagariNoRyuseiAbility;
import xyz.pixelatedw.mineminenomi.api.EnumFruitType;
import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
import xyz.pixelatedw.mineminenomi.events.passives.*;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.APIRegistries;
import xyz.pixelatedw.wypi.WyRegistry;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.debug.WyDebug;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModAbilities
{

	public static final AkumaNoMiItem KACHI_KACHI_NO_MI = new AkumaNoMiItem("Kachi Kachi no Mi", 1, EnumFruitType.PARAMECIA, EvaporateAbility.INSTANCE, HotBoilingSpecialAbility.INSTANCE);
	public static final AkumaNoMiItem DOA_DOA_NO_MI = new AkumaNoMiItem("Doa Doa no Mi", 2, EnumFruitType.PARAMECIA, DoorDoorAbility.INSTANCE, KaitenDoorAbility.INSTANCE, AirDoorAbility.INSTANCE);
	public static final AkumaNoMiItem USHI_USHI_NO_MI_GIRAFFE = new AkumaNoMiItem("Ushi Ushi no Mi, Model Giraffe", 2, EnumFruitType.ZOAN, GiraffeHeavyPointAbility.INSTANCE, GiraffeWalkPointAbility.INSTANCE, BiganAbility.INSTANCE);
	public static final AkumaNoMiItem MOGU_MOGU_NO_MI = new AkumaNoMiItem("Mogu Mogu no Mi", 2, EnumFruitType.ZOAN, MoguHeavyPointAbility.INSTANCE, MoguraBananaAbility.INSTANCE, MoguraTonpoAbility.INSTANCE);
	public static final AkumaNoMiItem CHIYU_CHIYU_NO_MI = new AkumaNoMiItem("Chiyu Chiyu no Mi", 1, EnumFruitType.PARAMECIA, HealingTouchAbility.INSTANCE, ChiyupopoAbility.INSTANCE);
	public static final AkumaNoMiItem HITO_HITO_NO_MI = new AkumaNoMiItem("Hito Hito no Mi", 1, EnumFruitType.ZOAN);
	public static final AkumaNoMiItem SABI_SABI_NO_MI = new AkumaNoMiItem("Sabi Sabi no Mi", 1, EnumFruitType.PARAMECIA, RustTouchAbility.INSTANCE);
	public static final AkumaNoMiItem ZOU_ZOU_NO_MI = new AkumaNoMiItem("Zou Zou no Mi", 3, EnumFruitType.ZOAN, ZouGuardPointAbility.INSTANCE, ZouHeavyPointAbility.INSTANCE, TrunkShotAbility.INSTANCE, GreatStompAbility.INSTANCE, IvoryDartAbility.INSTANCE, IvoryStompAbility.INSTANCE);
	public static final AkumaNoMiItem YOMI_YOMI_NO_MI = new AkumaNoMiItem("Yomi Yomi no Mi", 3, EnumFruitType.PARAMECIA, SoulParadeAbility.INSTANCE, KasuriutaFubukiGiriAbility.INSTANCE, YomiNoReikiAbility.INSTANCE);
	public static final AkumaNoMiItem BAKU_BAKU_NO_MI = new AkumaNoMiItem("Baku Baku no Mi", 1, EnumFruitType.PARAMECIA, BakuMunchAbility.INSTANCE, BeroCannonAbility.INSTANCE, BakuTsuihoAbility.INSTANCE);
	public static final AkumaNoMiItem TORI_TORI_NO_MI_PHOENIX = new AkumaNoMiItem("Tori Tori no Mi, Model: Phoenix", 3, EnumFruitType.ZOAN, PhoenixAssaultPointAbility.INSTANCE, PhoenixFlyPointAbility.INSTANCE, PhoenixGoenAbility.INSTANCE, FlameOfRestorationAbility.INSTANCE, BlueFlamesOfResurrectionAbility.INSTANCE, TenseiNoSoenAbility.INSTANCE);
	public static final AkumaNoMiItem USHI_USHI_NO_MI_BISON = new AkumaNoMiItem("Ushi Ushi no Mi, Model: Bison", 2, EnumFruitType.ZOAN, BisonHeavyPointAbility.INSTANCE, BisonWalkPointAbility.INSTANCE, FiddleBanffAbility.INSTANCE, KokuteiCrossAbility.INSTANCE);
	public static final AkumaNoMiItem HORU_HORU_NO_MI = new AkumaNoMiItem("Horu Horu no Mi", 2, EnumFruitType.PARAMECIA, OnnaHormoneAbility.INSTANCE, ChiyuHormoneAbility.INSTANCE, TensionHormoneAbility.INSTANCE, GanmenSeichoHormoneAbility.INSTANCE);
	public static final AkumaNoMiItem KILO_KILO_NO_MI = new AkumaNoMiItem("Kilo Kilo no Mi", 2, EnumFruitType.PARAMECIA, KiloPress1Ability.INSTANCE, KiloPress10000Ability.INSTANCE);
	public static final AkumaNoMiItem GOE_GOE_NO_MI = new AkumaNoMiItem("Goe Goe no Mi", 1, EnumFruitType.PARAMECIA, TodorokiAbility.INSTANCE);
	public static final AkumaNoMiItem MERO_MERO_NO_MI = new AkumaNoMiItem("Mero Mero no Mi", 2, EnumFruitType.PARAMECIA, MeroMeroMellowAbility.INSTANCE, PistolKissAbility.INSTANCE, SlaveArrowAbility.INSTANCE, PerfumeFemurAbility.INSTANCE);
	//public static final AkumaNoMiItem ORI_ORI_NO_MI = new AkumaNoMiItem("Ori Ori no Mi", 1, EnumFruitType.PARAMECIA);
	public static final AkumaNoMiItem SUPA_SUPA_NO_MI = new AkumaNoMiItem("Supa Supa no Mi", 2, EnumFruitType.PARAMECIA, SpiderAbility.INSTANCE, SparClawAbility.INSTANCE, SpiralHollowAbility.INSTANCE, SparklingDaisyAbility.INSTANCE, AtomicSpurtAbility.INSTANCE);
	public static final AkumaNoMiItem HORO_HORO_NO_MI = new AkumaNoMiItem("Horo Horo no Mi", 2, EnumFruitType.PARAMECIA, NegativeHollowAbility.INSTANCE, MiniHollowAbility.INSTANCE, TokuHollowAbility.INSTANCE, YutaiRidatsuAbility.INSTANCE);
	public static final AkumaNoMiItem ITO_ITO_NO_MI = new AkumaNoMiItem("Ito Ito no Mi", 3, EnumFruitType.PARAMECIA, ParasiteAbility.INSTANCE, SoraNoMichiAbility.INSTANCE, TamaitoAbility.INSTANCE, OverheatAbility.INSTANCE, KumoNoSugakiAbility.INSTANCE, TorikagoAbility.INSTANCE, BlackKnightAbility.INSTANCE);
	public static final AkumaNoMiItem ZUSHI_ZUSHI_NO_MI = new AkumaNoMiItem("Zushi Zushi no Mi", 2, EnumFruitType.PARAMECIA, JigokuTabiAbility.INSTANCE, SagariNoRyuseiAbility.INSTANCE, MokoAbility.INSTANCE, AbareHimatsuriAbility.INSTANCE);
	public static final AkumaNoMiItem BARI_BARI_NO_MI = new AkumaNoMiItem("Bari Bari no Mi", 1, EnumFruitType.PARAMECIA, BarrierAbility.INSTANCE, BarrierBallAbility.INSTANCE, BarrierCrashAbility.INSTANCE, BariBariNoPistolAbility.INSTANCE, BarrierbilityStairsAbility.INSTANCE);
	public static final AkumaNoMiItem DOKU_DOKU_NO_MI = new AkumaNoMiItem("Doku Doku no Mi", 2, EnumFruitType.PARAMECIA, HydraAbility.INSTANCE, ChloroBallAbility.INSTANCE, DokuFuguAbility.INSTANCE, VenomRoadAbility.INSTANCE, DokuGumoAbility.INSTANCE, VenomDemonAbility.INSTANCE);
	public static final AkumaNoMiItem DORU_DORU_NO_MI = new AkumaNoMiItem("Doru Doru no Mi", 2, EnumFruitType.PARAMECIA, CandleWallAbility.INSTANCE, CandleHouseAbility.INSTANCE, DoruDoruArtsMoriAbility.INSTANCE, DoruDoruArtsKenAbility.INSTANCE, CandleLockAbility.INSTANCE,DoruDoruArtsPickaxeAbility.INSTANCE, DoruDoruBallAbility.INSTANCE, DoruDoruNoYakataAbility.INSTANCE);
	public static final AkumaNoMiItem KAGE_KAGE_NO_MI = new AkumaNoMiItem("Kage Kage no Mi", 2, EnumFruitType.PARAMECIA, DoppelmanAbility.INSTANCE, KagemushaAbility.INSTANCE, BrickBatAbility.INSTANCE, BlackBoxAbility.INSTANCE, TsunoTokageAbility.INSTANCE);
	public static final AkumaNoMiItem GURA_GURA_NO_MI = new AkumaNoMiItem("Gura Gura no Mi", 3, EnumFruitType.PARAMECIA, KaishinAbility.INSTANCE, GekishinAbility.INSTANCE, TenchiMeidoAbility.INSTANCE, ShimaYurashiAbility.INSTANCE);
	public static final AkumaNoMiItem BOMU_BOMU_NO_MI = new AkumaNoMiItem("Bomu Bomu no Mi", 1, EnumFruitType.PARAMECIA, NoseFancyCannonAbility.INSTANCE, KickBombAbility.INSTANCE, ZenshinKibakuAbility.INSTANCE, ExplosivePunchAbility.INSTANCE, BreezeBreathBombAbility.INSTANCE);
	public static final AkumaNoMiItem NIKYU_NIKYU_NO_MI = new AkumaNoMiItem("Nikyu Nikyu no Mi", 2, EnumFruitType.PARAMECIA, PadHoAbility.INSTANCE, HanpatsuAbility.INSTANCE, TsuppariPadHoAbility.INSTANCE, UrsusShockAbility.INSTANCE);
	public static final AkumaNoMiItem NORO_NORO_NO_MI = new AkumaNoMiItem("Noro Noro no Mi", 1, EnumFruitType.PARAMECIA, NoroNoroBeamAbility.INSTANCE, NoroNoroBeamSwordAbility.INSTANCE, KyubiRushAbility.INSTANCE);
	public static final AkumaNoMiItem OPE_OPE_NO_MI = new AkumaNoMiItem("Ope Ope no Mi", 3, EnumFruitType.PARAMECIA, RoomAbility.INSTANCE, CounterShockAbility.INSTANCE, MesAbility.INSTANCE, ShamblesAbility.INSTANCE, TaktAbility.INSTANCE, InjectionShotAbility.INSTANCE, GammaKnifeAbility.INSTANCE);
	public static final AkumaNoMiItem SUKE_SUKE_NO_MI = new AkumaNoMiItem("Suke Suke no Mi", 1, EnumFruitType.PARAMECIA, SkattingAbility.INSTANCE, ShishaNoTeAbility.INSTANCE, SukePunchAbility.INSTANCE);
	public static final AkumaNoMiItem GOMU_GOMU_NO_MI = new AkumaNoMiItem("Gomu Gomu no Mi", 2, EnumFruitType.PARAMECIA, GomuGomuNoPistolAbility.INSTANCE, GomuGomuNoGatlingAbility.INSTANCE, GomuGomuNoBazookaAbility.INSTANCE, GomuGomuNoRocketAbility.INSTANCE, GearSecondAbility.INSTANCE, GearThirdAbility.INSTANCE, GearFourthAbility.INSTANCE);
	public static final AkumaNoMiItem BANE_BANE_NO_MI = new AkumaNoMiItem("Bane Bane no Mi", 1, EnumFruitType.PARAMECIA, SpringHopperAbility.INSTANCE, SpringSnipeAbility.INSTANCE, SpringDeathKnockAbility.INSTANCE);

	public static final AkumaNoMiItem MOKU_MOKU_NO_MI = new AkumaNoMiItem("Moku Moku no Mi", 2, EnumFruitType.LOGIA, WhiteOutAbility.INSTANCE, WhiteSnakeAbility.INSTANCE, WhiteLauncherAbility.INSTANCE, WhiteStrikeAbility.INSTANCE, SpecialFlyAbility.INSTANCE, MokuPassiveEvents.INVULNERABILITY_INSTANCE);
	public static final AkumaNoMiItem GORO_GORO_NO_MI = new AkumaNoMiItem("Goro Goro no Mi", 3, EnumFruitType.LOGIA, SparkStepAbility.INSTANCE, ElThorAbility.INSTANCE, VoltVariAbility.INSTANCE, KariAbility.INSTANCE, SangoAbility.INSTANCE, RaigoAbility.INSTANCE, ShinzoMassageAbility.INSTANCE, GoroPassiveEvents.INVULNERABILITY_INSTANCE);
	public static final AkumaNoMiItem PIKA_PIKA_NO_MI = new AkumaNoMiItem("Pika Pika no Mi", 3, EnumFruitType.LOGIA, YataNoKagamiAbility.INSTANCE, YasakaniNoMagatamaAbility.INSTANCE, AmaNoMurakumoAbility.INSTANCE, AmaterasuAbility.INSTANCE, FlashAbility.INSTANCE, PikaPassiveEvents.INVULNERABILITY_INSTANCE);
	public static final AkumaNoMiItem HIE_HIE_NO_MI = new AkumaNoMiItem("Hie Hie no Mi", 3, EnumFruitType.LOGIA, IceBlockPartisanAbility.INSTANCE, IceAgeAbility.INSTANCE, IceBallAbility.INSTANCE, IceSaberAbility.INSTANCE, IceTimeCapsuleAbility.INSTANCE, IceBlockPheasantAbility.INSTANCE, IceBlockAvalancheAbility.INSTANCE, HiePassiveEvents.INVULNERABILITY_INSTANCE);
	public static final AkumaNoMiItem YUKI_YUKI_NO_MI = new AkumaNoMiItem("Yuki Yuki no Mi", 3, EnumFruitType.LOGIA, KamakuraAbility.INSTANCE, YukiRabiAbility.INSTANCE, KamakuraJussoshiAbility.INSTANCE, TabiraYukiAbility.INSTANCE, YukiGakiAbility.INSTANCE, FubukiAbility.INSTANCE, YukiPassiveEvents.INVULNERABILITY_INSTANCE);
	public static final AkumaNoMiItem YAMI_YAMI_NO_MI = new AkumaNoMiItem("Yami Yami no Mi", 3, EnumFruitType.LOGIA, BlackHoleAbility.INSTANCE, LiberationAbility.INSTANCE, KurouzuAbility.INSTANCE, DarkMatterAbility.INSTANCE, BlackWorldAbility.INSTANCE);
	public static final AkumaNoMiItem GASU_GASU_NO_MI = new AkumaNoMiItem("Gasu Gasu no Mi", 3, EnumFruitType.LOGIA, GasRobeAbility.INSTANCE, GastanetAbility.INSTANCE, GastilleAbility.INSTANCE, BlueSwordAbility.INSTANCE, KarakuniAbility.INSTANCE, SpecialFlyAbility.INSTANCE, GasuPassiveEvents.INVULNERABILITY_INSTANCE);
	public static final AkumaNoMiItem MAGU_MAGU_NO_MI = new AkumaNoMiItem("Magu Magu no Mi", 3, EnumFruitType.LOGIA, DaiFunkaAbility.INSTANCE, RyuseiKazanAbility.INSTANCE, MeigoAbility.INSTANCE, BakuretsuKazanAbility.INSTANCE, MaguPassiveEvents.INVULNERABILITY_INSTANCE);
	public static final AkumaNoMiItem SUNA_SUNA_NO_MI = new AkumaNoMiItem("Suna Suna no Mi", 3, EnumFruitType.LOGIA, DesertSpadaAbility.INSTANCE, SablesAbility.INSTANCE, BarjanAbility.INSTANCE, DesertEncierroAbility.INSTANCE, GroundDeathAbility.INSTANCE, DesertGirasoleAbility.INSTANCE, SpecialFlyAbility.INSTANCE, SunaPassiveEvents.INVULNERABILITY_INSTANCE);
	public static final AkumaNoMiItem MERA_MERA_NO_MI = new AkumaNoMiItem("Mera Mera no Mi", 3, EnumFruitType.LOGIA, HikenAbility.INSTANCE, HiganAbility.INSTANCE, DaiEnkaiEnteiAbility.INSTANCE, HidarumaAbility.INSTANCE, JujikaAbility.INSTANCE, HeatDashAbility.INSTANCE, KyokaenAbility.INSTANCE, MeraPassiveEvents.INVULNERABILITY_INSTANCE);

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

		// Art of Weather
		WyRegistry.registerAbility(HeatBallAbility.INSTANCE);
		WyRegistry.registerAbility(CoolBallAbility.INSTANCE);
		WyRegistry.registerAbility(ThunderBallAbility.INSTANCE);
		WyRegistry.registerAbility(GustSwordAbility.INSTANCE);
		WyRegistry.registerAbility(WeatherEggAbility.INSTANCE);
		WyRegistry.registerAbility(WeatherCloudTempo.INSTANCE);
		WyRegistry.registerAbility(ThunderLanceTempo.INSTANCE);
		WyRegistry.registerAbility(ThunderboltTempo.INSTANCE);
		WyRegistry.registerAbility(ThunderstormTempo.INSTANCE);
		WyRegistry.registerAbility(RainTempo.INSTANCE);
		WyRegistry.registerAbility(FogTempo.INSTANCE);
		WyRegistry.registerAbility(MirageTempo.INSTANCE);

		// Sniper Googles
		WyRegistry.registerAbility(ZoomAbility.INSTANCE);
		
		// Haki
		WyRegistry.registerAbility(BusoshokuHakiHardeningAbility.INSTANCE);
		WyRegistry.registerAbility(BusoshokuHakiImbuingAbility.INSTANCE);
		WyRegistry.registerAbility(BusoshokuHakiFullBodyHardeningAbility.INSTANCE);
		WyRegistry.registerAbility(KenbunshokuHakiAuraAbility.INSTANCE);
		WyRegistry.registerAbility(KenbunshokuHakiFutureSightAbility.INSTANCE);
		WyRegistry.registerAbility(HaoshokuHakiAbility.INSTANCE);

		WyDebug.debug("A total of " + ModValues.devilfruits.size() + " Devil Fruits have been registered");
		WyDebug.debug("A total of " + APIRegistries.ABILITIES.getEntries().size() + " abilities have been registered");
	}
}
