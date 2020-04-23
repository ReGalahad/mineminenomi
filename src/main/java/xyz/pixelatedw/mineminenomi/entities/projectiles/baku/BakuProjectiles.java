package xyz.pixelatedw.mineminenomi.entities.projectiles.baku;

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
public class BakuProjectiles
{
	public static final EntityType BERO_CANNON = WyRegistry.createEntityType(BeroCannonProjectile::new).size(0.5F, 0.5F).build("bero_cannon");

	static
	{
		WyRegistry.registerEntityType(BERO_CANNON, "Bero Cannon");
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void registerEntityRenderers(FMLClientSetupEvent event)
	{
		RenderingRegistry.registerEntityRenderingHandler(BeroCannonProjectile.class, new AbilityProjectileRenderer.Factory(new CubeModel()).setColor("#989898").setScale(2, 2, 2));
	}
}
