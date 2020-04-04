package xyz.pixelatedw.mineminenomi.entities.projectiles.doku;

import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.HydraModel;
import xyz.pixelatedw.wypi.WyRegistry;
import xyz.pixelatedw.wypi.abilities.models.SphereModel;
import xyz.pixelatedw.wypi.abilities.renderers.AbilityProjectileRenderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DokuProjectiles
{
	public static final EntityType HYDRA = WyRegistry.createEntityType(HydraProjectile::new).size(0.75F, 0.75F).build("hydra");
	public static final EntityType CHLORO_BALL = WyRegistry.createEntityType(ChloroBallProjectile::new).size(0.5F, 0.5F).build("chloro_ball");
	public static final EntityType VENOM_ROAD = WyRegistry.createEntityType(VenomRoadProjectile::new).size(0.75F, 0.75F).build("venom_road");
	public static final EntityType DOKU_FUGU = WyRegistry.createEntityType(DokuFuguProjectile::new).size(0.5F, 0.5F).build("doku_fugu");

	static
	{
		WyRegistry.registerEntityType(HYDRA, "Hydra");
		WyRegistry.registerEntityType(CHLORO_BALL, "Chloro Ball");
		WyRegistry.registerEntityType(VENOM_ROAD, "Venom Road");
		WyRegistry.registerEntityType(DOKU_FUGU, "Doku Fugu");
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void registerEntityRenderers(FMLClientSetupEvent event)
	{
		RenderingRegistry.registerEntityRenderingHandler(HydraProjectile.class, new AbilityProjectileRenderer.Factory(new HydraModel()).setTexture("hydra").setScale(2, 2, 4));
		RenderingRegistry.registerEntityRenderingHandler(ChloroBallProjectile.class, new AbilityProjectileRenderer.Factory(new SphereModel()).setColor("#A020F0").setScale(5));
		RenderingRegistry.registerEntityRenderingHandler(VenomRoadProjectile.class, new AbilityProjectileRenderer.Factory(new HydraModel()).setTexture("hydra").setScale(2, 2, 4));
		RenderingRegistry.registerEntityRenderingHandler(DokuFuguProjectile.class, new AbilityProjectileRenderer.Factory(new SphereModel()).setColor("#A020F0").setScale(3));
	}
}
