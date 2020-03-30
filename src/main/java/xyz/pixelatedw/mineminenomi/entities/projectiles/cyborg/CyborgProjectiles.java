package xyz.pixelatedw.mineminenomi.entities.projectiles.cyborg;

import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.FistModel;
import xyz.pixelatedw.wypi.WyRegistry;
import xyz.pixelatedw.wypi.abilities.models.CubeModel;
import xyz.pixelatedw.wypi.abilities.renderers.AbilityProjectileRenderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CyborgProjectiles
{
	public static final EntityType FRESH_FIRE = WyRegistry.createEntityType(FreshFireProjectile::new).size(3F, 3F).build("fresh_fire");
	public static final EntityType RADICAL_BEAM = WyRegistry.createEntityType(RadicalBeamProjectile::new).size(0.5F, 0.5F).build("radical_beam");
	public static final EntityType STRONG_RIGHT = WyRegistry.createEntityType(StrongRightProjectile::new).size(0.5F, 0.5F).build("strong_right");
	public static final EntityType COUP_DE_VENT = WyRegistry.createEntityType(CoupDeVentProjectile::new).size(5.5F, 5.5F).build("coup_de_vent");

	static
	{
		WyRegistry.registerEntityType(FRESH_FIRE, "Fresh Fire");
		WyRegistry.registerEntityType(RADICAL_BEAM, "Radical Beam");
		WyRegistry.registerEntityType(STRONG_RIGHT, "Strong Right");
		WyRegistry.registerEntityType(COUP_DE_VENT, "Coup de Vent");
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void registerEntityRenderers(FMLClientSetupEvent event)
	{
		RenderingRegistry.registerEntityRenderingHandler(FreshFireProjectile.class, new AbilityProjectileRenderer.Factory(new CubeModel()).setScale(0, 0, 0));
		RenderingRegistry.registerEntityRenderingHandler(RadicalBeamProjectile.class, new AbilityProjectileRenderer.Factory(new CubeModel()).setColor("#FFFF00").setScale(.5, .5, 2));
		RenderingRegistry.registerEntityRenderingHandler(StrongRightProjectile.class, new AbilityProjectileRenderer.Factory(new FistModel()).setColor("#F5DEB3").setScale(1, 1, 1.2));
		RenderingRegistry.registerEntityRenderingHandler(CoupDeVentProjectile.class, new AbilityProjectileRenderer.Factory(new CubeModel()).setScale(0, 0, 0));
	}
}
