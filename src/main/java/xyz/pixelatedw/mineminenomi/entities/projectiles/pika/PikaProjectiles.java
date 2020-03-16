package xyz.pixelatedw.mineminenomi.entities.projectiles.pika;

import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import xyz.pixelatedw.wypi.WyRegistry;
import xyz.pixelatedw.wypi.abilities.models.CubeModel;
import xyz.pixelatedw.wypi.abilities.models.SphereModel;
import xyz.pixelatedw.wypi.abilities.renderers.AbilityProjectileRenderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class PikaProjectiles
{
	public static final EntityType AMATERASU = WyRegistry.createEntityType(AmaterasuProjectile::new).size(0.5F, 0.5F).build("amaterasu");
	public static final EntityType YASAKANI_NO_MAGATAMA = WyRegistry.createEntityType(YasakaniNoMagatamaProjectile::new).size(0.2F, 0.2F).build("yasakani_no_magatama");

	static
	{
		WyRegistry.registerEntityType(AMATERASU, "Amaterasu");
		WyRegistry.registerEntityType(YASAKANI_NO_MAGATAMA, "Yasakani no Magatama");
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void registerEntityRenderers(FMLClientSetupEvent event)
	{
		RenderingRegistry.registerEntityRenderingHandler(AmaterasuProjectile.class, new AbilityProjectileRenderer.Factory(new CubeModel()).setColor("#FFFF00").setScale(1, 1, 2));
		RenderingRegistry.registerEntityRenderingHandler(YasakaniNoMagatamaProjectile.class, new AbilityProjectileRenderer.Factory(new SphereModel()).setColor("#FFFF00").setScale(.5, .5, .5));
	}
}
