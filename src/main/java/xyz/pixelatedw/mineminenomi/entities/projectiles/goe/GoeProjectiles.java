package xyz.pixelatedw.mineminenomi.entities.projectiles.goe;

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
public class GoeProjectiles
{
	public static final EntityType TODOROKI = WyRegistry.createEntityType(TodorokiProjectile::new).size(0.5F, 0.5F).build("todoroki");

	static
	{
		WyRegistry.registerEntityType(TODOROKI, "Todoroki");
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void registerEntityRenderers(FMLClientSetupEvent event)
	{
		RenderingRegistry.registerEntityRenderingHandler(TodorokiProjectile.class, new AbilityProjectileRenderer.Factory(new CubeModel()).setColor("#87CEFA").setScale(2, 2, 4));
	}
}
