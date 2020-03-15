package xyz.pixelatedw.mineminenomi.entities.projectiles.ope;

import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import xyz.pixelatedw.wypi.WyRegistry;
import xyz.pixelatedw.wypi.abilities.models.CubeModel;
import xyz.pixelatedw.wypi.abilities.renderers.AbilityProjectileRenderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class OpeProjectiles
{
	public static final EntityType GAMMA_KNIFE = WyRegistry.createEntityType(GammaKnifeProjectile::new).size(0.5F, 0.5F).build("gamma_knife");

	@SubscribeEvent
	public static void registerEntityTypes(RegistryEvent.Register<EntityType<?>> event)
	{
		WyRegistry.setupEntityTypeRegistry(event.getRegistry());

		WyRegistry.registerEntityType(GAMMA_KNIFE, "Gamma Knife");
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void registerEntityRenderers(FMLClientSetupEvent event)
	{
		RenderingRegistry.registerEntityRenderingHandler(GammaKnifeProjectile.class, new AbilityProjectileRenderer.Factory(new CubeModel()).setColor("#00AB66").setScale(1, 1, 5));
	}
}
