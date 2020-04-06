package xyz.pixelatedw.mineminenomi.entities.projectiles.yuki;

import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.YukiRabiModel;
import xyz.pixelatedw.wypi.WyRegistry;
import xyz.pixelatedw.wypi.abilities.renderers.AbilityProjectileRenderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class YukiProjectiles
{
	public static final EntityType YUKI_RABI = WyRegistry.createEntityType(YukiRabiProjectile::new).size(0.5F, 0.5F).build("yuki_rabi");

	static
	{
		WyRegistry.registerEntityType(YUKI_RABI, "Yuki Rabi");
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void registerEntityRenderers(FMLClientSetupEvent event)
	{
		RenderingRegistry.registerEntityRenderingHandler(YukiRabiProjectile.class, new AbilityProjectileRenderer.Factory(new YukiRabiModel()).setTexture("yukirabi").setScale(1));
	}
}
