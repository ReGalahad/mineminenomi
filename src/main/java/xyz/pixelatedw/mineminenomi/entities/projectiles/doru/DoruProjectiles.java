package xyz.pixelatedw.mineminenomi.entities.projectiles.doru;

import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.SpearModel;
import xyz.pixelatedw.wypi.WyRegistry;
import xyz.pixelatedw.wypi.abilities.models.CubeModel;
import xyz.pixelatedw.wypi.abilities.renderers.AbilityProjectileRenderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DoruProjectiles
{
	public static final EntityType DORU_DORU_ARTS_MORI = WyRegistry.createEntityType(DoruDoruArtsMoriProjectile::new).size(0.5F, 0.5F).build("doru_doru_arts_mori");
	public static final EntityType CANDLE_LOCK = WyRegistry.createEntityType(CandleLockProjectile::new).size(0.5F, 0.5F).build("candle_lock");

	static
	{
		WyRegistry.registerEntityType(DORU_DORU_ARTS_MORI, "Doru Doru Arts: Mori");
		WyRegistry.registerEntityType(CANDLE_LOCK, "Candle Lock");
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void registerEntityRenderers(FMLClientSetupEvent event)
	{
		RenderingRegistry.registerEntityRenderingHandler(DoruDoruArtsMoriProjectile.class, new AbilityProjectileRenderer.Factory(new SpearModel()).setScale(2).setTexture("dorudoruartsmori"));
		RenderingRegistry.registerEntityRenderingHandler(CandleLockProjectile.class, new AbilityProjectileRenderer.Factory(new CubeModel()).setScale(0));
	}
}
