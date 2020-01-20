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
import xyz.pixelatedw.mineminenomi.abilities.mera.HidarumaAbility;
import xyz.pixelatedw.mineminenomi.abilities.mera.HiganAbility;
import xyz.pixelatedw.mineminenomi.abilities.mera.HikenAbility;
import xyz.pixelatedw.mineminenomi.abilities.mera.JujikaAbility;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.debug.WyDebug;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.mera.MeraProjectiles;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;

@Mod.EventBusSubscriber(modid = Env.PROJECT_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModDevilFruits
{
	
	//public static final AkumaNoMiItem MINI_MINI_NO_MI = new AkumaNoMiItem("Mini Mini no Mi", EnumFruitType.PARAMECIA);
	public static final AkumaNoMiItem KACHI_KACHI_NO_MI = new AkumaNoMiItem("Kachi Kachi no Mi", EnumFruitType.PARAMECIA);
	public static final AkumaNoMiItem DOA_DOA_NO_MI = new AkumaNoMiItem("Doa Doa no Mi", EnumFruitType.PARAMECIA);
	public static final AkumaNoMiItem USHI_USHI_NO_MI_GIRAFFE = new AkumaNoMiItem("Ushi Ushi no Mi, Model Giraffe", EnumFruitType.ZOAN);
	public static final AkumaNoMiItem MOGU_MOGU_NO_MI = new AkumaNoMiItem("Mogu Mogu no Mi", EnumFruitType.ZOAN);
	public static final AkumaNoMiItem CHIYU_CHIYU_NO_MI = new AkumaNoMiItem("Chiyu Chiyu no Mi", EnumFruitType.PARAMECIA);
	public static final AkumaNoMiItem HITO_HITO_NO_MI = new AkumaNoMiItem("Hito Hito no Mi", EnumFruitType.ZOAN);
	public static final AkumaNoMiItem SABI_SABI_NO_MI = new AkumaNoMiItem("Sabi Sabi no Mi", EnumFruitType.PARAMECIA);
	public static final AkumaNoMiItem ZOU_ZOU_NO_MI = new AkumaNoMiItem("Zou Zou no Mi", EnumFruitType.ZOAN);
	public static final AkumaNoMiItem YOMI_YOMI_NO_MI = new AkumaNoMiItem("Yomi Yomi no Mi", EnumFruitType.PARAMECIA);
	public static final AkumaNoMiItem BAKU_BAKU_NO_MI = new AkumaNoMiItem("Baku Baku no Mi", EnumFruitType.PARAMECIA);
	public static final AkumaNoMiItem TORI_TORI_NO_MI_PHOENIX = new AkumaNoMiItem("Tori Tori no Mi, Model: Phoenix", EnumFruitType.ZOAN);
	public static final AkumaNoMiItem USHI_USHI_NO_MI_BISON = new AkumaNoMiItem("Ushi Ushi no Mi, Model: Bison", EnumFruitType.ZOAN);
	//public static final AkumaNoMiItem PAMU_PAMU_NO_MI = new AkumaNoMiItem("Pamu Pamu no Mi", EnumFruitType.PARAMECIA);
	//public static final AkumaNoMiItem ISHI_ISHI_NO_MI = new AkumaNoMiItem("Ishi Ishi no Mi", EnumFruitType.PARAMECIA);
	//public static final AkumaNoMiItem BETA_BETA_NO_MI = new AkumaNoMiItem("Beta Beta no Mi", EnumFruitType.PARAMECIA);
	public static final AkumaNoMiItem HORU_HORU_NO_MI = new AkumaNoMiItem("Horu Horu no Mi", EnumFruitType.PARAMECIA);
	//public static final AkumaNoMiItem HANA_HANA_NO_MI = new AkumaNoMiItem("Hana Hana no Mi", EnumFruitType.PARAMECIA);
	public static final AkumaNoMiItem KILO_KILO_NO_MI = new AkumaNoMiItem("Kilo Kilo no Mi", EnumFruitType.PARAMECIA);
	public static final AkumaNoMiItem GOE_GOE_NO_MI = new AkumaNoMiItem("Goe Goe no Mi", EnumFruitType.PARAMECIA);
	public static final AkumaNoMiItem MERO_MERO_NO_MI = new AkumaNoMiItem("Mero Mero no Mi", EnumFruitType.PARAMECIA);
	public static final AkumaNoMiItem ORI_ORI_NO_MI = new AkumaNoMiItem("Ori Ori no Mi", EnumFruitType.PARAMECIA);
	public static final AkumaNoMiItem SUPA_SUPA_NO_MI = new AkumaNoMiItem("Supa Supa no Mi", EnumFruitType.PARAMECIA);
	public static final AkumaNoMiItem HORO_HORO_NO_MI = new AkumaNoMiItem("Horo Horo no Mi", EnumFruitType.PARAMECIA);
	public static final AkumaNoMiItem ITO_ITO_NO_MI = new AkumaNoMiItem("Ito Ito no Mi", EnumFruitType.PARAMECIA);
	public static final AkumaNoMiItem YAMI_YAMI_NO_MI = new AkumaNoMiItem("Yami Yami no Mi", EnumFruitType.LOGIA);
	public static final AkumaNoMiItem JURYO_JURYO_NO_MI = new AkumaNoMiItem("Juryo Juryo no Mi", EnumFruitType.PARAMECIA);
	public static final AkumaNoMiItem YUKI_YUKI_NO_MI = new AkumaNoMiItem("Yuki Yuki no Mi", EnumFruitType.LOGIA);
	public static final AkumaNoMiItem GASU_GASU_NO_MI = new AkumaNoMiItem("Gasu Gasu no Mi", EnumFruitType.LOGIA);
	public static final AkumaNoMiItem BARI_BARI_NO_MI = new AkumaNoMiItem("Bari Bari no Mi", EnumFruitType.PARAMECIA);
	public static final AkumaNoMiItem DOKU_DOKU_NO_MI = new AkumaNoMiItem("Doku Doku no Mi", EnumFruitType.PARAMECIA);
	public static final AkumaNoMiItem DORU_DORU_NO_MI = new AkumaNoMiItem("Doru Doru no Mi", EnumFruitType.PARAMECIA);
	public static final AkumaNoMiItem MAGU_MAGU_NO_MI = new AkumaNoMiItem("Magu Magu no Mi", EnumFruitType.LOGIA);
	public static final AkumaNoMiItem SUNA_SUNA_NO_MI = new AkumaNoMiItem("Suna Suna no Mi", EnumFruitType.LOGIA);
	public static final AkumaNoMiItem KAGE_KAGE_NO_MI = new AkumaNoMiItem("Kage Kage no Mi", EnumFruitType.PARAMECIA);
	public static final AkumaNoMiItem GURA_GURA_NO_MI = new AkumaNoMiItem("Gura Gura no Mi", EnumFruitType.PARAMECIA);
	public static final AkumaNoMiItem BOMU_BOMU_NO_MI = new AkumaNoMiItem("Bomu Bomu no Mi", EnumFruitType.PARAMECIA);
	public static final AkumaNoMiItem NIKYU_NIKYU_NO_MI = new AkumaNoMiItem("Nikyu Nikyu no Mi", EnumFruitType.PARAMECIA);
	public static final AkumaNoMiItem MOKU_MOKU_NO_MI = new AkumaNoMiItem("Moku Moku no Mi", EnumFruitType.LOGIA);
	public static final AkumaNoMiItem GORO_GORO_NO_MI = new AkumaNoMiItem("Goro Goro no Mi", EnumFruitType.LOGIA);
	public static final AkumaNoMiItem NORO_NORO_NO_MI = new AkumaNoMiItem("Noro Noro no Mi", EnumFruitType.PARAMECIA);
	public static final AkumaNoMiItem OPE_OPE_NO_MI = new AkumaNoMiItem("Ope Ope no Mi", EnumFruitType.PARAMECIA);
	public static final AkumaNoMiItem SUKE_SUKE_NO_MI = new AkumaNoMiItem("Suke Suke no Mi", EnumFruitType.PARAMECIA);
	public static final AkumaNoMiItem GOMU_GOMU_NO_MI = new AkumaNoMiItem("Gomu Gomu no Mi", EnumFruitType.PARAMECIA);
	public static final AkumaNoMiItem PIKA_PIKA_NO_MI = new AkumaNoMiItem("Pika Pika no Mi", EnumFruitType.LOGIA);
	public static final AkumaNoMiItem HIE_HIE_NO_MI = new AkumaNoMiItem("Hie Hie no Mi", EnumFruitType.LOGIA);
	public static final AkumaNoMiItem MERA_MERA_NO_MI = new AkumaNoMiItem("Mera Mera no Mi", EnumFruitType.LOGIA, HikenAbility.INSTANCE, HiganAbility.INSTANCE, DaiEnkaiEnteiAbility.INSTANCE, HidarumaAbility.INSTANCE, JujikaAbility.INSTANCE);

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
}
