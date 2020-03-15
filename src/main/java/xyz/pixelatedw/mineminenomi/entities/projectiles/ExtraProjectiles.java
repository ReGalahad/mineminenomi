package xyz.pixelatedw.mineminenomi.entities.projectiles;

import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.AxeDialProjectile;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.EntityCloud;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.KairosekiBulletProjectile;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.KujaArrowProjectile;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.MilkyDialProjectile;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.NormalBulletProjectile;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.PopGreenProjectile;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.ArrowModel;
import xyz.pixelatedw.wypi.WyRegistry;
import xyz.pixelatedw.wypi.abilities.models.CubeModel;
import xyz.pixelatedw.wypi.abilities.models.SphereModel;
import xyz.pixelatedw.wypi.abilities.renderers.AbilityProjectileRenderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ExtraProjectiles
{
	public static final EntityType NORMAL_BULLET = WyRegistry.createEntityType(NormalBulletProjectile::new).size(0.3F, 0.3F).build("normal_bullet");
	public static final EntityType KAIROSEKI_BULLET = WyRegistry.createEntityType(KairosekiBulletProjectile::new).size(0.3F, 0.3F).build("kairoseki_bullet");
	public static final EntityType AXE_DIAL_PROJECTILE = WyRegistry.createEntityType(AxeDialProjectile::new).size(0.3F, 0.3F).build("axe_dial_projectile");
	public static final EntityType MILKY_DIAL_PROJECTILE = WyRegistry.createEntityType(MilkyDialProjectile::new).size(0.3F, 0.3F).build("milky_dial_projectile");
	public static final EntityType POP_GREEN = WyRegistry.createEntityType(PopGreenProjectile::new).size(0.3F, 0.3F).build("pop_green");
	public static final EntityType KUJA_ARROW = WyRegistry.createEntityType(KujaArrowProjectile::new).size(0.5F, 0.5F).build("kuja_arrow");
	public static final EntityType CLOUD = WyRegistry.createEntityType(EntityCloud::new).build("cloud");

	@SubscribeEvent
	public static void registerEntityTypes(RegistryEvent.Register<EntityType<?>> event)
	{
		WyRegistry.setupEntityTypeRegistry(event.getRegistry());

		WyRegistry.registerEntityType(NORMAL_BULLET, "Normal Bullet");
		WyRegistry.registerEntityType(KAIROSEKI_BULLET, "Kairoseki Bullet");
		WyRegistry.registerEntityType(AXE_DIAL_PROJECTILE, "Axe Dial Projectile");
		WyRegistry.registerEntityType(MILKY_DIAL_PROJECTILE, "Milky Dial Projectile");
		WyRegistry.registerEntityType(POP_GREEN, "Pop Green");
		WyRegistry.registerEntityType(KUJA_ARROW, "Kuja Arrow");
		WyRegistry.registerEntityType(CLOUD, "Cloud");
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void registerEntityRenderers(FMLClientSetupEvent event)
	{
		RenderingRegistry.registerEntityRenderingHandler(NormalBulletProjectile.class, new AbilityProjectileRenderer.Factory(new SphereModel()).setScale(0.5).setColor("#878787"));
		RenderingRegistry.registerEntityRenderingHandler(KairosekiBulletProjectile.class, new AbilityProjectileRenderer.Factory(new SphereModel()).setScale(0.5).setColor("#F3F3F3"));
		RenderingRegistry.registerEntityRenderingHandler(AxeDialProjectile.class, new AbilityProjectileRenderer.Factory(new CubeModel()).setScale(6, 0.4, 1.5).setColor("#69E3FF"));
		RenderingRegistry.registerEntityRenderingHandler(MilkyDialProjectile.class, new AbilityProjectileRenderer.Factory(new SphereModel()).setScale(.1).setColor("#69E3FF"));
		RenderingRegistry.registerEntityRenderingHandler(PopGreenProjectile.class, new AbilityProjectileRenderer.Factory(new SphereModel()).setScale(0.5).setColor("#7ccc6a"));
		RenderingRegistry.registerEntityRenderingHandler(KujaArrowProjectile.class, new AbilityProjectileRenderer.Factory(new ArrowModel()).setTexture("kujaarrow").setScale(1.25));
	}
}
