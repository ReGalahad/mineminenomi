package xyz.pixelatedw.mineminenomi.entities.projectiles.nikyu;

import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.PawModel;
import xyz.pixelatedw.wypi.WyRegistry;
import xyz.pixelatedw.wypi.abilities.renderers.AbilityProjectileRenderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class NikyuProjectiles
{
	public static final EntityType PAD_HO = WyRegistry.createEntityType(PadHoProjectile::new).size(0.5F, 0.5F).build("pad_ho");
	public static final EntityType URSUS_SHOCK_25 = WyRegistry.createEntityType(UrsusShock25Projectile::new).size(1.0F, 1.0F).build("ursus_shock_25");
	public static final EntityType URSUS_SHOCK_50 = WyRegistry.createEntityType(UrsusShock50Projectile::new).size(3.0F, 3.0F).build("ursus_shock_50");
	public static final EntityType URSUS_SHOCK_75 = WyRegistry.createEntityType(UrsusShock75Projectile::new).size(1.0F, 1.0F).build("ursus_shock_75");

	static
	{
		WyRegistry.registerEntityType(PAD_HO, "Pad Ho");
		WyRegistry.registerEntityType(URSUS_SHOCK_25, "Ursus Shock 25");
		WyRegistry.registerEntityType(URSUS_SHOCK_50, "Ursus Shock 50");
		WyRegistry.registerEntityType(URSUS_SHOCK_75, "Ursus Shock 75");
	}
	
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void registerEntityRenderers(FMLClientSetupEvent event)
	{
		RenderingRegistry.registerEntityRenderingHandler(PadHoProjectile.class, new AbilityProjectileRenderer.Factory(new PawModel()).setColor("#F8F8FFAA").setScale(1.0));
		RenderingRegistry.registerEntityRenderingHandler(UrsusShock25Projectile.class, new AbilityProjectileRenderer.Factory(new PawModel()).setColor("#F8F8FFAA").setScale(1.5));
		RenderingRegistry.registerEntityRenderingHandler(UrsusShock50Projectile.class, new AbilityProjectileRenderer.Factory(new PawModel()).setColor("#F8F8FFAA").setScale(3.5));
		RenderingRegistry.registerEntityRenderingHandler(UrsusShock75Projectile.class, new AbilityProjectileRenderer.Factory(new PawModel()).setColor("#F8F8FFAA").setScale(0.6));
	}
}
