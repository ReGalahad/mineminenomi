package xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather;

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
public class ArtOfWeatherProjectiles
{
	public static final EntityType HEAT_BALL = WyRegistry.createEntityType(HeatBallProjectile::new).size(0.5F, 0.5F).build("heat_ball");
	public static final EntityType COOL_BALL = WyRegistry.createEntityType(CoolBallProjectile::new).size(0.5F, 0.5F).build("cool_ball");
	public static final EntityType THUNDER_BALL = WyRegistry.createEntityType(ThunderBallProjectile::new).size(0.5F, 0.5F).build("thunder_ball");

	static
	{
		WyRegistry.registerEntityType(HEAT_BALL, "Heat Ball");
		WyRegistry.registerEntityType(COOL_BALL, "Cool Ball");
		WyRegistry.registerEntityType(THUNDER_BALL, "Thunder Ball");
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void registerEntityRenderers(FMLClientSetupEvent event)
	{
		RenderingRegistry.registerEntityRenderingHandler(HeatBallProjectile.class, new AbilityProjectileRenderer.Factory(new SphereModel()).setColor("#FF0000BB").setScale(2, 2, 2));
		RenderingRegistry.registerEntityRenderingHandler(CoolBallProjectile.class, new AbilityProjectileRenderer.Factory(new SphereModel()).setColor("#0000FFBB").setScale(2, 2, 2));
		RenderingRegistry.registerEntityRenderingHandler(ThunderBallProjectile.class, new AbilityProjectileRenderer.Factory(new SphereModel()).setColor("#FFFF00BB").setScale(2, 2, 2));
	}
}

