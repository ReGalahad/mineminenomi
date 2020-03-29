package xyz.pixelatedw.mineminenomi.entities.projectiles.fishmankarate;

import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.SharkModel;
import xyz.pixelatedw.wypi.WyRegistry;
import xyz.pixelatedw.wypi.abilities.models.CubeModel;
import xyz.pixelatedw.wypi.abilities.renderers.AbilityProjectileRenderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class FishmanKarateProjectiles
{
	public static final EntityType UCHIMIZU = WyRegistry.createEntityType(UchimizuProjectile::new).size(0.5F, 0.5F).build("uchimizu");
	public static final EntityType MURASAME = WyRegistry.createEntityType(MurasameProjectile::new).size(0.5F, 0.5F).build("murasame");

	static
	{
		WyRegistry.registerEntityType(UCHIMIZU, "Uchimizu");
		WyRegistry.registerEntityType(MURASAME, "Murasame");
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void registerEntityRenderers(FMLClientSetupEvent event)
	{
		RenderingRegistry.registerEntityRenderingHandler(UchimizuProjectile.class, new AbilityProjectileRenderer.Factory(new CubeModel()).setColor("#00CED1").setScale(0.5, 0.5, 1));
		RenderingRegistry.registerEntityRenderingHandler(MurasameProjectile.class, new AbilityProjectileRenderer.Factory(new SharkModel()).setTexture("murasame").setScale(0.8, 0.8, 1.2));
	}
}
