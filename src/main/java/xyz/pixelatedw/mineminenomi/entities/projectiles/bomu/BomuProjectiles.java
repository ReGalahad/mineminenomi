package xyz.pixelatedw.mineminenomi.entities.projectiles.bomu;

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
public class BomuProjectiles
{
	public static final EntityType NOSE_FANCY_CANNON = WyRegistry.createEntityType(NoseFancyCannonProjectile::new).size(1.0F, 1.0F).build("nose_fancy_cannon");

	static
	{
		WyRegistry.registerEntityType(NOSE_FANCY_CANNON, "Nose Fancy Cannon");
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void registerEntityRenderers(FMLClientSetupEvent event)
	{
		RenderingRegistry.registerEntityRenderingHandler(NoseFancyCannonProjectile.class, new AbilityProjectileRenderer.Factory(new CubeModel()).setColor("3D2B1F").setScale(.4, .4, .4));
	}
}