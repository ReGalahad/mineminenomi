package xyz.pixelatedw.mineminenomi.entities.projectiles.gomu;

import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.BazookaModel;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.FistModel;
import xyz.pixelatedw.wypi.WyRegistry;
import xyz.pixelatedw.wypi.abilities.renderers.AbilityProjectileRenderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class GomuProjectiles
{
	public static final EntityType GOMU_GOMU_NO_PISTOL = WyRegistry.createEntityType(GomuGomuNoPistolProjectile::new).size(0.5F, 0.5F).build("gomu_gomu_no_pistol");
	public static final EntityType GOMU_GOMU_NO_JET_PISTOL = WyRegistry.createEntityType(GomuGomuNoJetPistolProjectile::new).size(0.5F, 0.5F).build("gomu_gomu_no_jet_pistol");
	public static final EntityType GOMU_GOMU_NO_ELEPHANT_GUN = WyRegistry.createEntityType(GomuGomuNoElephantGunProjectile::new).size(5.0F, 5.0F).build("gomu_gomu_no_elephant_gun");
	public static final EntityType GOMU_GOMU_NO_KONG_GUN = WyRegistry.createEntityType(GomuGomuNoKongGunProjectile::new).size(5.0F, 5.0F).build("gomu_gomu_no_kong_gun");

	public static final EntityType GOMU_GOMU_NO_BAZOOKA = WyRegistry.createEntityType(GomuGomuNoBazookaProjectile::new).size(0.5F, 0.5F).build("gomu_gomu_no_bazooka");
	public static final EntityType GOMU_GOMU_NO_JET_BAZOOKA = WyRegistry.createEntityType(GomuGomuNoJetBazookaProjectile::new).size(0.5F, 0.5F).build("gomu_gomu_no_jet_bazooka");
	public static final EntityType GOMU_GOMU_NO_GRIZZLY_MAGNUM = WyRegistry.createEntityType(GomuGomuNoGrizzlyMagnumProjectile::new).size(5.0F, 5.0F).build("gomu_gomu_no_grizzly_magnum");
	public static final EntityType GOMU_GOMU_NO_LEO_BAZOOKA = WyRegistry.createEntityType(GomuGomuNoLeoBazookaProjectile::new).size(5.0F, 5.0F).build("gomu_gomu_no_leo_bazooka");

	public static final EntityType GOMU_GOMU_NO_ROCKET = WyRegistry.createEntityType(GomuGomuNoRocketProjectile::new).size(0.5F, 0.5F).build("gomu_gomu_no_rocket");

	static
	{
		WyRegistry.registerEntityType(GOMU_GOMU_NO_PISTOL, "Gomu Gomu no Pistol");
		WyRegistry.registerEntityType(GOMU_GOMU_NO_JET_PISTOL, "Gomu Gomu no Jet Pistol");
		WyRegistry.registerEntityType(GOMU_GOMU_NO_ELEPHANT_GUN, "Gomu Gomu no Elephant Gun");
		WyRegistry.registerEntityType(GOMU_GOMU_NO_KONG_GUN, "Gomu Gomu no Kong Gun");

		WyRegistry.registerEntityType(GOMU_GOMU_NO_BAZOOKA, "Gomu Gomu no Bazooka");
		WyRegistry.registerEntityType(GOMU_GOMU_NO_JET_BAZOOKA, "Gomu Gomu no Jet Bazooka");
		WyRegistry.registerEntityType(GOMU_GOMU_NO_GRIZZLY_MAGNUM, "Gomu Gomu no Grizzly Magnum");
		WyRegistry.registerEntityType(GOMU_GOMU_NO_LEO_BAZOOKA, "Gomu Gomu no Leo Bazooka");

		WyRegistry.registerEntityType(GOMU_GOMU_NO_ROCKET, "Gomu Gomu no Rocket");
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void registerEntityRenderers(FMLClientSetupEvent event)
	{
		RenderingRegistry.registerEntityRenderingHandler(GomuGomuNoPistolProjectile.class, new AbilityProjectileRenderer.Factory(new FistModel()).setTexture("gomugomunopistol").setScale(3, 3, 3));
		RenderingRegistry.registerEntityRenderingHandler(GomuGomuNoJetPistolProjectile.class, new AbilityProjectileRenderer.Factory(new FistModel()).setTexture("gomugomunojetpistol").setScale(3, 3, 3));
		RenderingRegistry.registerEntityRenderingHandler(GomuGomuNoElephantGunProjectile.class, new AbilityProjectileRenderer.Factory(new FistModel()).setTexture("gomugomunopistol").setScale(13, 13, 13));
		RenderingRegistry.registerEntityRenderingHandler(GomuGomuNoKongGunProjectile.class, new AbilityProjectileRenderer.Factory(new FistModel()).setTexture("gomugomunopistol").setScale(13, 13, 13));

		RenderingRegistry.registerEntityRenderingHandler(GomuGomuNoBazookaProjectile.class, new AbilityProjectileRenderer.Factory(new BazookaModel()).setTexture("gomugomunobazooka").setScale(3, 3, 3));
		RenderingRegistry.registerEntityRenderingHandler(GomuGomuNoJetBazookaProjectile.class, new AbilityProjectileRenderer.Factory(new BazookaModel()).setTexture("gomugomunojetbazooka").setScale(3, 3, 3));
		RenderingRegistry.registerEntityRenderingHandler(GomuGomuNoGrizzlyMagnumProjectile.class, new AbilityProjectileRenderer.Factory(new BazookaModel()).setTexture("gomugomunobazooka").setScale(13, 13, 13));
		RenderingRegistry.registerEntityRenderingHandler(GomuGomuNoLeoBazookaProjectile.class, new AbilityProjectileRenderer.Factory(new BazookaModel()).setTexture("gomugomunobazooka").setScale(13, 13, 13));

		RenderingRegistry.registerEntityRenderingHandler(GomuGomuNoRocketProjectile.class, new AbilityProjectileRenderer.Factory(new FistModel()).setTexture("gomugomunopistol").setScale(3, 3, 3));
	}
}
