package xyz.pixelatedw.mineminenomi.entities.projectiles.magu;

import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.FistModel;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.MeigoModel;
import xyz.pixelatedw.wypi.WyRegistry;
import xyz.pixelatedw.wypi.abilities.renderers.AbilityProjectileRenderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class MaguProjectiles
{
	public static final EntityType DAI_FUNKA = WyRegistry.createEntityType(DaiFunkaProjectile::new).size(3F, 3F).build("dai_funka");
	public static final EntityType MEIGO = WyRegistry.createEntityType(MeigoProjectile::new).size(0.5F, 0.5F).build("meigo");

	static
	{
		WyRegistry.registerEntityType(DAI_FUNKA, "Dai Funka");
		WyRegistry.registerEntityType(MEIGO, "Meigo");
	}
	
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void registerEntityRenderers(FMLClientSetupEvent event)
	{
		RenderingRegistry.registerEntityRenderingHandler(DaiFunkaProjectile.class, new AbilityProjectileRenderer.Factory(new FistModel()).setTexture("daifunka").setScale(3));
		RenderingRegistry.registerEntityRenderingHandler(MeigoProjectile.class, new AbilityProjectileRenderer.Factory(new MeigoModel()).setTexture("meigo").setScale(4));
	}
}