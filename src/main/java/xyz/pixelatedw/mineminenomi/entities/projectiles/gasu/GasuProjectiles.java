package xyz.pixelatedw.mineminenomi.entities.projectiles.gasu;

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
public class GasuProjectiles
{
	public static final EntityType GAS_ROBE = WyRegistry.createEntityType(GasRobeProjectile::new).size(0.5F, 0.5F).build("gas_robe");
	public static final EntityType GASTILLE = WyRegistry.createEntityType(GastilleProjectile::new).size(0.5F, 0.5F).build("gas_robe");

	static
	{
		WyRegistry.registerEntityType(GAS_ROBE, "Gas Robe");
		WyRegistry.registerEntityType(GASTILLE, "Gastille");
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void registerEntityRenderers(FMLClientSetupEvent event)
	{
		RenderingRegistry.registerEntityRenderingHandler(GasRobeProjectile.class, new AbilityProjectileRenderer.Factory(new CubeModel()).setScale(0, 0, 0));	
		RenderingRegistry.registerEntityRenderingHandler(GastilleProjectile.class, new AbilityProjectileRenderer.Factory(new CubeModel()).setColor("#9999FF").setScale(1, 1, 3));
	}
}
