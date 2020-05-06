package xyz.pixelatedw.mineminenomi.entities.projectiles.ushigiraffe;

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
public class UshiGiraffeProjectiles
{
	public static final EntityType BIGAN = WyRegistry.createEntityType(BiganProjectile::new).size(1.5F, 1.5F).build("bigan");

	static
	{
		WyRegistry.registerEntityType(BIGAN, "Bigan");
	}
	
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void registerEntityRenderers(FMLClientSetupEvent event)
	{
		RenderingRegistry.registerEntityRenderingHandler(BiganProjectile.class, new AbilityProjectileRenderer.Factory(new CubeModel()).setColor("#ffb73a").setScale(.4, .4, 1.5));
	}
}

