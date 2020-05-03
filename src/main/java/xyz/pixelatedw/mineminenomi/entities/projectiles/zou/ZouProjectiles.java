package xyz.pixelatedw.mineminenomi.entities.projectiles.zou;

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
public class ZouProjectiles
{
	public static final EntityType TRUNK_SHOT = WyRegistry.createEntityType(TrunkShotProjectile::new).size(1F, 1F).build("trunk_shot");

	static
	{
		WyRegistry.registerEntityType(TRUNK_SHOT, "Trunk Shot");
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void registerEntityRenderers(FMLClientSetupEvent event)
	{
		RenderingRegistry.registerEntityRenderingHandler(TrunkShotProjectile.class, new AbilityProjectileRenderer.Factory(new CubeModel()).setTexture("zouskin").setScale(3, 3, 4));
	}
}
