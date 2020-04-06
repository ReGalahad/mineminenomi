package xyz.pixelatedw.mineminenomi.entities.projectiles.zushi;

import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import xyz.pixelatedw.wypi.WyRegistry;
import xyz.pixelatedw.wypi.abilities.models.SphereModel;
import xyz.pixelatedw.wypi.abilities.renderers.AbilityProjectileRenderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ZushiProjectiles
{
	public static final EntityType SAGARI_NO_RYUSEI = WyRegistry.createEntityType(SagariNoRyuseiProjectile::new).size(10F, 10F).build("sagari_no_ryusei");
	public static final EntityType MOKO = WyRegistry.createEntityType(MokoProjectile::new).size(1F, 1F).build("moko");

	static
	{
		WyRegistry.registerEntityType(SAGARI_NO_RYUSEI, "Sagari no Ryusei");
		WyRegistry.registerEntityType(MOKO, "Moko");
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void registerEntityRenderers(FMLClientSetupEvent event)
	{
		RenderingRegistry.registerEntityRenderingHandler(SagariNoRyuseiProjectile.class, new AbilityProjectileRenderer.Factory(new SphereModel()).setColor("#51585B").setScale(50));
		RenderingRegistry.registerEntityRenderingHandler(MokoProjectile.class, new AbilityProjectileRenderer.Factory(new SphereModel()).setScale(0));
	}
}
