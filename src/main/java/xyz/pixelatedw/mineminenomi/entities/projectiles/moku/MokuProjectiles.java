package xyz.pixelatedw.mineminenomi.entities.projectiles.moku;

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
public class MokuProjectiles
{
	public static final EntityType WHITE_OUT = WyRegistry.createEntityType(WhiteOutProjectile::new).size(0.5F, 0.5F).build("white_out");
	public static final EntityType WHITE_SNAKE = WyRegistry.createEntityType(WhiteSnakeProjectile::new).size(0.5F, 0.5F).build("white_snake");

	static
	{
		WyRegistry.registerEntityType(WHITE_OUT, "White Out");
		WyRegistry.registerEntityType(WHITE_SNAKE, "White Snake");
	}
	
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void registerEntityRenderers(FMLClientSetupEvent event)
	{
		RenderingRegistry.registerEntityRenderingHandler(WhiteOutProjectile.class, new AbilityProjectileRenderer.Factory(new CubeModel()).setScale(0));
		RenderingRegistry.registerEntityRenderingHandler(WhiteSnakeProjectile.class, new AbilityProjectileRenderer.Factory(new CubeModel()).setScale(0));
	}
}
