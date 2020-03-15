package xyz.pixelatedw.mineminenomi.entities.projectiles.mera;

import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.FistModel;
import xyz.pixelatedw.wypi.WyRegistry;
import xyz.pixelatedw.wypi.abilities.models.CubeModel;
import xyz.pixelatedw.wypi.abilities.models.SphereModel;
import xyz.pixelatedw.wypi.abilities.renderers.AbilityProjectileRenderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class MeraProjectiles
{
	public static final EntityType HIKEN = WyRegistry.createEntityType(HikenProjectile::new).size(0.5F, 0.5F).build("hiken");
	public static final EntityType HIGAN = WyRegistry.createEntityType(HiganProjectile::new).size(0.5F, 0.5F).build("higan");
	public static final EntityType DAI_ENKAI_ENTEI =  WyRegistry.createEntityType(DaiEnkaiEnteiProjectile::new).size(1.75F, 1.75F).build("dai_enkai_entei");
	public static final EntityType HIDARUMA = WyRegistry.createEntityType(HidarumaProjectile::new).size(0.5F, 0.5F).build("hidaruma");
	public static final EntityType JUJIKA = WyRegistry.createEntityType(JujikaProjectile::new).size(0.5F, 0.5F).build("jujika");
	
	@SubscribeEvent
	public static void registerEntityTypes(RegistryEvent.Register<EntityType<?>> event)
	{
		WyRegistry.setupEntityTypeRegistry(event.getRegistry());
		
		WyRegistry.registerEntityType(HIKEN, "Hiken");
		WyRegistry.registerEntityType(HIGAN, "Higan");
		WyRegistry.registerEntityType(DAI_ENKAI_ENTEI, "Dai Enkai: Entei");
		WyRegistry.registerEntityType(HIDARUMA, "Hidaruma");
		WyRegistry.registerEntityType(JUJIKA, "Jujika");
	}
	
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void registerEntityRenderers(FMLClientSetupEvent event)
	{
		RenderingRegistry.registerEntityRenderingHandler(HikenProjectile.class, new AbilityProjectileRenderer.Factory(new FistModel()).setTexture("hiken").setScale(1.5));
		RenderingRegistry.registerEntityRenderingHandler(HiganProjectile.class, new AbilityProjectileRenderer.Factory(new CubeModel()).setColor(255, 0, 0, 100).setScale(0.5));
		RenderingRegistry.registerEntityRenderingHandler(DaiEnkaiEnteiProjectile.class, new AbilityProjectileRenderer.Factory(new SphereModel()).setColor(255, 0, 0, 100).setScale(9));
		RenderingRegistry.registerEntityRenderingHandler(HidarumaProjectile.class, new AbilityProjectileRenderer.Factory(new SphereModel()).setScale(0.01));
		RenderingRegistry.registerEntityRenderingHandler(JujikaProjectile.class, new AbilityProjectileRenderer.Factory(new SphereModel()).setColor(255, 0, 0, 100).setScale(0.5));
	}
}

