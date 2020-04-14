package xyz.pixelatedw.mineminenomi.entities.projectiles.ito;

import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import xyz.pixelatedw.wypi.WyRegistry;
import xyz.pixelatedw.wypi.abilities.models.CubeModel;
import xyz.pixelatedw.wypi.abilities.models.SphereModel;
import xyz.pixelatedw.wypi.abilities.renderers.AbilityProjectileRenderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItoProjectiles
{
	public static final EntityType OVERHEAT = WyRegistry.createEntityType(OverheatProjectile::new).size(0.5F, 0.5F).build("overheat");
	public static final EntityType TAMAITO = WyRegistry.createEntityType(TamaitoProjectile::new).size(0.5F, 0.5F).build("tamaito");

	static
	{
		WyRegistry.registerEntityType(OVERHEAT, "Overheat");
		WyRegistry.registerEntityType(TAMAITO, "Tamaito");
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void registerEntityRenderers(FMLClientSetupEvent event)
	{
		RenderingRegistry.registerEntityRenderingHandler(OverheatProjectile.class, new AbilityProjectileRenderer.Factory(new CubeModel()).setColor("#f77c25").setScale(1, 1, 5));
		RenderingRegistry.registerEntityRenderingHandler(TamaitoProjectile.class, new AbilityProjectileRenderer.Factory(new SphereModel()).setColor("#dee1e5").setScale(.5));
	}
}
