package xyz.pixelatedw.mineminenomi.entities.projectiles.hie;

import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.PheasantModel;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.TridentModel;
import xyz.pixelatedw.mineminenomi.renderers.abilities.IceBlockAvalancheRenderer;
import xyz.pixelatedw.wypi.WyRegistry;
import xyz.pixelatedw.wypi.abilities.models.SphereModel;
import xyz.pixelatedw.wypi.abilities.renderers.AbilityProjectileRenderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class HieProjectiles
{
	public static final EntityType ICE_BLOCK_PARTISAN = WyRegistry.createEntityType(IceBlockPartisanProjectile::new).size(0.5F, 0.5F).build("ice_block_partisan");
	public static final EntityType ICE_BALL = WyRegistry.createEntityType(IceBallProjectile::new).size(0.7F, 0.7F).build("ice_ball");
	public static final EntityType ICE_BLOCK_PHEASANT = WyRegistry.createEntityType(IceBlockPheasantProjectile::new).size(1.75F, 1.75F).build("ice_block_pheasant");
    public static final EntityType ICE_BLOCK_AVALANCHE = WyRegistry.createEntityType(IceBlockAvalancheProjectile::new).size(9f, 9f).build("ice_block_avalanche");
	static
	{
		WyRegistry.registerEntityType(ICE_BLOCK_PARTISAN, "Ice Block: Partisan");
		WyRegistry.registerEntityType(ICE_BALL, "Ice Ball");
		WyRegistry.registerEntityType(ICE_BLOCK_PHEASANT, "Ice Block: Pheasant");
		WyRegistry.registerEntityType(ICE_BLOCK_AVALANCHE, "Ice Block: Avalanche");
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void registerEntityRenderers(FMLClientSetupEvent event)
	{
		RenderingRegistry.registerEntityRenderingHandler(IceBlockPartisanProjectile.class, new AbilityProjectileRenderer.Factory(new TridentModel()).setTexture("iceblockpartisan").setScale(1.5));
		RenderingRegistry.registerEntityRenderingHandler(IceBallProjectile.class, new AbilityProjectileRenderer.Factory(new SphereModel()).setColor("#36648B").setScale(5));
		RenderingRegistry.registerEntityRenderingHandler(IceBlockPheasantProjectile.class, new AbilityProjectileRenderer.Factory(new PheasantModel()).setTexture("iceblockpheasant").setScale(5));
        RenderingRegistry.registerEntityRenderingHandler(IceBlockAvalancheProjectile.class, new IceBlockAvalancheRenderer.Factory(new SphereModel()).setColor("#54f7ff").setScale(8));
	}
}
