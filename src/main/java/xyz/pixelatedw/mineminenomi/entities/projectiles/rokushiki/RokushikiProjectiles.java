package xyz.pixelatedw.mineminenomi.entities.projectiles.rokushiki;

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
public class RokushikiProjectiles
{
	public static final EntityType RANKYAKU = WyRegistry.createEntityType(RankyakuProjectile::new).size(5.25F, 0.5F).build("rankyaku");

	static
	{
		WyRegistry.registerEntityType(RANKYAKU, "Rankyaku");
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void registerEntityRenderers(FMLClientSetupEvent event)
	{
		RenderingRegistry.registerEntityRenderingHandler(RankyakuProjectile.class, new AbilityProjectileRenderer.Factory(new CubeModel()).setColor("#B1B1E1").setScale(12, 0.5, 1));
	}
}
