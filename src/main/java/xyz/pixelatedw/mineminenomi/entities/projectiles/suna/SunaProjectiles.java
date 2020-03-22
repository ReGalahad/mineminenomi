package xyz.pixelatedw.mineminenomi.entities.projectiles.suna;

import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import xyz.pixelatedw.wypi.WyRegistry;
import xyz.pixelatedw.wypi.abilities.models.CubeModel;
import xyz.pixelatedw.wypi.abilities.renderers.AbilityProjectileRenderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class SunaProjectiles
{
	public static final EntityType BARJAN = WyRegistry.createEntityType(BarjanProjectile::new).size(2.0F, 2.0F).build("barjan");
	public static final EntityType DESERT_SPADA = WyRegistry.createEntityType(DesertSpadaProjectile::new).size(3.0F, 3.0F).build("desert_spada");

	static
	{
		WyRegistry.registerEntityType(BARJAN, "Barjan");
		WyRegistry.registerEntityType(DESERT_SPADA, "Desert Spada");
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void registerEntityRenderers(FMLClientSetupEvent event)
	{
		RenderingRegistry.registerEntityRenderingHandler(BarjanProjectile.class, new AbilityProjectileRenderer.Factory(new CubeModel()).setColor("967117").setScale(6, 0.4, 1.5));
		RenderingRegistry.registerEntityRenderingHandler(DesertSpadaProjectile.class, new AbilityProjectileRenderer.Factory(new CubeModel()).setScale(0, 0, 0));
	}
}