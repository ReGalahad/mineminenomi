package xyz.pixelatedw.mineminenomi.entities.projectiles.kage;

import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.BrickBatModel;
import xyz.pixelatedw.wypi.WyRegistry;
import xyz.pixelatedw.wypi.abilities.models.CubeModel;
import xyz.pixelatedw.wypi.abilities.renderers.AbilityProjectileRenderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class KageProjectiles
{
	public static final EntityType BRICK_BAT = WyRegistry.createEntityType(BrickBatProjectile::new).size(0.5F, 0.5F).build("brick_bat");
	public static final EntityType BLACK_BOX = WyRegistry.createEntityType(BlackBoxProjectile::new).size(0.5F, 0.5F).build("black_box");
	
	// Extras
	public static final EntityType TSUNO_TOKAGE = WyRegistry.createEntityType(TsunoTokagePillarEntity::new).size(1.5F, 2.5F).build("tsuno_tokage");

	static
	{
		WyRegistry.registerEntityType(BRICK_BAT, "Brick Bat");
		WyRegistry.registerEntityType(BLACK_BOX, "Brick Box");
		
		WyRegistry.registerEntityType(TSUNO_TOKAGE, "Tsuno Tokage");
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void registerEntityRenderers(FMLClientSetupEvent event)
	{
		RenderingRegistry.registerEntityRenderingHandler(BrickBatProjectile.class, new AbilityProjectileRenderer.Factory(new BrickBatModel()).setTexture("brickbat"));
		RenderingRegistry.registerEntityRenderingHandler(BlackBoxProjectile.class, new AbilityProjectileRenderer.Factory(new BrickBatModel()).setTexture("brickbat"));
		RenderingRegistry.registerEntityRenderingHandler(TsunoTokagePillarEntity.class, new AbilityProjectileRenderer.Factory(new CubeModel()).setColor("#431C4b").setScale(5, 5, 15));
	}
}
