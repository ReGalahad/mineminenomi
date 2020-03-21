package xyz.pixelatedw.mineminenomi.entities.projectiles.gura;

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
public class GuraProjectiles
{
	public static final EntityType KAISHIN = WyRegistry.createEntityType(KaishinProjectile::new).size(3.0F, 3.0F).build("kaishin");

	static
	{
		WyRegistry.registerEntityType(KAISHIN, "Kaishin");
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void registerEntityRenderers(FMLClientSetupEvent event)
	{
		RenderingRegistry.registerEntityRenderingHandler(KaishinProjectile.class, new AbilityProjectileRenderer.Factory(new CubeModel()).setScale(0, 0, 0));
	}
}