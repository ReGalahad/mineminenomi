package xyz.pixelatedw.mineminenomi.entities.projectiles.goro;

import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import xyz.pixelatedw.wypi.WyRegistry;
import xyz.pixelatedw.wypi.abilities.models.CubeModel;
import xyz.pixelatedw.wypi.abilities.models.SphereModel;
import xyz.pixelatedw.wypi.abilities.renderers.AbilityProjectileRenderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class GoroProjectiles
{
	public static final EntityType SANGO = WyRegistry.createEntityType(SangoProjectile::new).size(0.5F, 0.5F).build("sango");
	public static final EntityType RAIGO = WyRegistry.createEntityType(RaigoProjectile::new).size(5F, 5F).build("raigo");;
	public static final EntityType VOLT_VARI_5_MILLION = WyRegistry.createEntityType(VoltVari5MillionProjectile::new).size(0.5F, 0.5F).build("volt_vari_5_million");
	public static final EntityType VOLT_VARI_20_MILLION = WyRegistry.createEntityType(VoltVari20MillionProjectile::new).size(0.5F, 0.5F).build("volt_vari_20_million");
	public static final EntityType VOLT_VARI_60_MILLION = WyRegistry.createEntityType(VoltVari60MillionProjectile::new).size(0.5F, 0.5F).build("volt_vari_60_million");
	public static final EntityType VOLT_VARI_200_MILLION = WyRegistry.createEntityType(VoltVari200MillionProjectile::new).size(1, 1).build("volt_vari_200_million");

	static
	{
		WyRegistry.registerEntityType(SANGO, "Sango");
		WyRegistry.registerEntityType(RAIGO, "Raigo");
		WyRegistry.registerEntityType(VOLT_VARI_5_MILLION, "Volt Vari 5 Million");
		WyRegistry.registerEntityType(VOLT_VARI_20_MILLION, "Volt Vari 20 Million");
		WyRegistry.registerEntityType(VOLT_VARI_60_MILLION, "Volt Vari 60 Million");
		WyRegistry.registerEntityType(VOLT_VARI_200_MILLION, "Volt Vari 200 Million");
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void registerEntityRenderers(FMLClientSetupEvent event)
	{
		RenderingRegistry.registerEntityRenderingHandler(SangoProjectile.class, new AbilityProjectileRenderer.Factory(new CubeModel()).setScale(0));
		RenderingRegistry.registerEntityRenderingHandler(RaigoProjectile.class, new AbilityProjectileRenderer.Factory(new SphereModel()).setColor("#5D8AA8").setScale(50));
		RenderingRegistry.registerEntityRenderingHandler(VoltVari5MillionProjectile.class, new AbilityProjectileRenderer.Factory(new SphereModel()).setColor("#92C1E5").setScale(1));
		RenderingRegistry.registerEntityRenderingHandler(VoltVari20MillionProjectile.class, new AbilityProjectileRenderer.Factory(new SphereModel()).setColor("#7CB9E8").setScale(3));
		RenderingRegistry.registerEntityRenderingHandler(VoltVari60MillionProjectile.class, new AbilityProjectileRenderer.Factory(new SphereModel()).setColor("#6BB0E5").setScale(5));
		RenderingRegistry.registerEntityRenderingHandler(VoltVari200MillionProjectile.class, new AbilityProjectileRenderer.Factory(new SphereModel()).setColor("#3170A0").setScale(7));
	}
}
