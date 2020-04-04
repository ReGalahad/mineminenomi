package xyz.pixelatedw.mineminenomi.entities.projectiles.bari;

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
public class BariProjectiles
{
	public static final EntityType BARRIER_CRASH = WyRegistry.createEntityType(BarrierCrashProjectile::new).size(2.5F, 7.5F).build("barrier_crash");
	public static final EntityType BARRIERBILITY_STAIRS = WyRegistry.createEntityType(BarrierbilityStairsProjectile::new).size(0.5F, 0.5F).build("barrierbility_stairs");
	
	static
	{
		WyRegistry.registerEntityType(BARRIER_CRASH, "Barrier Crash");
		WyRegistry.registerEntityType(BARRIERBILITY_STAIRS, "Barrierbility Stairs");
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void registerEntityRenderers(FMLClientSetupEvent event)
	{
		RenderingRegistry.registerEntityRenderingHandler(BarrierCrashProjectile.class, new AbilityProjectileRenderer.Factory(new CubeModel()).setColor("#87CEFAAA").setScale(5, 7, 0.5));
		RenderingRegistry.registerEntityRenderingHandler(BarrierbilityStairsProjectile.class, new AbilityProjectileRenderer.Factory(new CubeModel()).setScale(0, 0, 0));
	}
}
