package xyz.pixelatedw.mineminenomi.entities.projectiles.mero;

import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.ArrowModel;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.HeartModel;
import xyz.pixelatedw.wypi.WyRegistry;
import xyz.pixelatedw.wypi.abilities.renderers.AbilityProjectileRenderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class MeroProjectiles
{
	public static final EntityType MERO_MERO_MELLOW = WyRegistry.createEntityType(MeroMeroMellowProjectile::new).size(3.5F, 3.5F).build("mero_mero_mellow");
	public static final EntityType PISTOL_KISS = WyRegistry.createEntityType(PistolKissProjectile::new).size(0.5F, 0.5F).build("pistol_kiss");
	public static final EntityType SLAVE_ARROW = WyRegistry.createEntityType(SlaveArrowProjectile::new).size(0.5F, 0.5F).build("slave_arrow");

	static
	{
		WyRegistry.registerEntityType(MERO_MERO_MELLOW, "Mero Mero Mellow");
		WyRegistry.registerEntityType(PISTOL_KISS, "Pistol Kiss");
		WyRegistry.registerEntityType(SLAVE_ARROW, "Slave Arrow");
	}
	
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void registerEntityRenderers(FMLClientSetupEvent event)
	{
		RenderingRegistry.registerEntityRenderingHandler(MeroMeroMellowProjectile.class, new AbilityProjectileRenderer.Factory(new HeartModel()).setTexture("meromeromellow").setScale(3));
		RenderingRegistry.registerEntityRenderingHandler(PistolKissProjectile.class, new AbilityProjectileRenderer.Factory(new HeartModel()).setTexture("meromeromellow").setScale(0.5));
		RenderingRegistry.registerEntityRenderingHandler(SlaveArrowProjectile.class, new AbilityProjectileRenderer.Factory(new ArrowModel()).setColor("#FF69B4"));
	}
}

