package xyz.pixelatedw.mineminenomi.entities.projectiles.yami;

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
public class YamiProjectiles
{
	public static final EntityType LIBERATION = WyRegistry.createEntityType(LiberationProjectile::new).size(1F, 1F).build("liberation");
	public static final EntityType DARK_MATTER = WyRegistry.createEntityType(DarkMatterProjectile::new).size(1F, 1F).build("dark_matter");

	static
	{
		WyRegistry.registerEntityType(LIBERATION, "Liberation");
		WyRegistry.registerEntityType(DARK_MATTER, "Dark Matter");
	}
	
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void registerEntityRenderers(FMLClientSetupEvent event)
	{
		RenderingRegistry.registerEntityRenderingHandler(LiberationProjectile.class, new AbilityProjectileRenderer.Factory(new CubeModel()).setColor("#000000").setScale(1.5));
		RenderingRegistry.registerEntityRenderingHandler(DarkMatterProjectile.class, new AbilityProjectileRenderer.Factory(new SphereModel()).setColor("#000000").setScale(2));
	}
}

