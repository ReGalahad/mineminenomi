package xyz.pixelatedw.mineminenomi.entities.projectiles.swordsman;

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
public class SwordsmanProjectiles
{
	public static final EntityType YAKKODORI = WyRegistry.createEntityType(YakkodoriProjectile::new).size(0.5F, 1.5F).build("yakkodori");
	public static final EntityType SANBYAKUROKUJU_POUND_HO = WyRegistry.createEntityType(SanbyakurokujuPoundHoProjectile::new).size(2.5F, 0.5F).build("sanbyakurokuju_pound_ho");

	static
	{
		WyRegistry.registerEntityType(YAKKODORI, "Yakkodori");
		WyRegistry.registerEntityType(SANBYAKUROKUJU_POUND_HO, "Sanbyakurokuju Pound Ho");
	}
	
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void registerEntityRenderers(FMLClientSetupEvent event)
	{
		RenderingRegistry.registerEntityRenderingHandler(YakkodoriProjectile.class, new AbilityProjectileRenderer.Factory(new CubeModel()).setColor("#bbf7b4").setScale(.4, 6, .4));
		RenderingRegistry.registerEntityRenderingHandler(SanbyakurokujuPoundHoProjectile.class, new AbilityProjectileRenderer.Factory(new CubeModel()).setColor("#bbf7b4").setScale(6, .4, 1.5));
	}
}

