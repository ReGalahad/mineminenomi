package xyz.pixelatedw.mineminenomi.entities.projectiles.bane;

import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.FistModel;
import xyz.pixelatedw.wypi.WyRegistry;
import xyz.pixelatedw.wypi.abilities.renderers.AbilityProjectileRenderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BaneProjectiles
{
	public static final EntityType SPRING_DEATH_KNOCK = WyRegistry.createEntityType(SpringDeathKnockProjectile::new).size(1.5F, 1.5F).build("spring_death_knock");

	static
	{
		WyRegistry.registerEntityType(SPRING_DEATH_KNOCK, "Spring Death Knock");
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void registerEntityRenderers(FMLClientSetupEvent event)
	{
		RenderingRegistry.registerEntityRenderingHandler(SpringDeathKnockProjectile.class, new AbilityProjectileRenderer.Factory(new FistModel()).setTexture("springdeathknock").setScale(5, 5, 7));
	}
}
