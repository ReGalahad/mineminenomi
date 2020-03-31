package xyz.pixelatedw.mineminenomi.entities.projectiles.sniper;

import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import xyz.pixelatedw.wypi.WyRegistry;
import xyz.pixelatedw.wypi.abilities.models.SphereModel;
import xyz.pixelatedw.wypi.abilities.renderers.AbilityProjectileRenderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class SniperProjectiles
{
	public static final EntityType KAEN_BOSHI = WyRegistry.createEntityType(KaenBoshiProjectile::new).size(0.5F, 0.5F).build("kaen_boshi");
	public static final EntityType KEMURI_BOSHI = WyRegistry.createEntityType(KemuriBoshiProjectile::new).size(0.5F, 0.5F).build("kemuri_boshi");
	public static final EntityType RENPATSU_NAMARI_BOSHI = WyRegistry.createEntityType(RenpatsuNamariBoshiProjectile::new).size(0.5F, 0.5F).build("renpatsu_namari_boshi");
	public static final EntityType SAKURETSU_SABOTEN_BOSHI = WyRegistry.createEntityType(SakuretsuSabotenBoshiProjectile::new).size(0.5F, 0.5F).build("sakuretsu_saboten_boshi");

	static
	{
		WyRegistry.registerEntityType(KAEN_BOSHI, "Kaen Boshi");
		WyRegistry.registerEntityType(KEMURI_BOSHI, "Kemuri Boshi");
		WyRegistry.registerEntityType(RENPATSU_NAMARI_BOSHI, "Renpatsu Namari Boshi");
		WyRegistry.registerEntityType(SAKURETSU_SABOTEN_BOSHI, "Sakuretsu Saboten Boshi");
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void registerEntityRenderers(FMLClientSetupEvent event)
	{
		RenderingRegistry.registerEntityRenderingHandler(KaenBoshiProjectile.class, new AbilityProjectileRenderer.Factory(new SphereModel()).setScale(0.5).setColor("#63110E"));
		RenderingRegistry.registerEntityRenderingHandler(KemuriBoshiProjectile.class, new AbilityProjectileRenderer.Factory(new SphereModel()).setScale(0.5).setColor("#C0C0C0"));
		RenderingRegistry.registerEntityRenderingHandler(RenpatsuNamariBoshiProjectile.class, new AbilityProjectileRenderer.Factory(new SphereModel()).setScale(0.5).setColor("#C0C0C0"));
		RenderingRegistry.registerEntityRenderingHandler(SakuretsuSabotenBoshiProjectile.class, new AbilityProjectileRenderer.Factory(new SphereModel()).setScale(0.5).setColor("#437C17"));
	}
}
