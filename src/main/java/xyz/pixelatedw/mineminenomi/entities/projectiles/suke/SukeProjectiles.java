package xyz.pixelatedw.mineminenomi.entities.projectiles.suke;

import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import xyz.pixelatedw.wypi.WyRegistry;
import xyz.pixelatedw.wypi.abilities.models.SphereModel;
import xyz.pixelatedw.wypi.abilities.renderers.AbilityProjectileRenderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class SukeProjectiles
{
	public static final EntityType SHISHA_NO_TE = WyRegistry.createEntityType(ShishaNoTeProjectile::new).size(0.5F, 0.5F).build("shisha_no_te");

	@SubscribeEvent
	public static void registerEntityTypes(RegistryEvent.Register<EntityType<?>> event)
	{
		WyRegistry.setupEntityTypeRegistry(event.getRegistry());
		
		WyRegistry.registerEntityType(SHISHA_NO_TE, "Shisha no Te");
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void registerEntityRenderers(FMLClientSetupEvent event)
	{
		RenderingRegistry.registerEntityRenderingHandler(ShishaNoTeProjectile.class, new AbilityProjectileRenderer.Factory(new SphereModel()).setScale(0));
	}
}
