package xyz.pixelatedw.mineminenomi.entities.projectiles.supa;

import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.FistModel;
import xyz.pixelatedw.wypi.WyRegistry;
import xyz.pixelatedw.wypi.abilities.renderers.AbilityProjectileRenderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class SupaProjectiles
{
	public static final EntityType SPIRAL_HOLLOW = WyRegistry.createEntityType(SpiralHollowProjectile::new).size(1.0F, 1.0F).build("spiral_hollow");

	static
	{
		WyRegistry.registerEntityType(SPIRAL_HOLLOW, "Spiral Hollow");
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void registerEntityRenderers(FMLClientSetupEvent event)
	{
		RenderingRegistry.registerEntityRenderingHandler(SpiralHollowProjectile.class, new AbilityProjectileRenderer.Factory(new FistModel()).setColor("#F8F8FF").setScale(3, 3, 5));
	}
}