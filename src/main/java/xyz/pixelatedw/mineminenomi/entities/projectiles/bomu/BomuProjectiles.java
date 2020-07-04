package xyz.pixelatedw.mineminenomi.entities.projectiles.bomu;

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
public class BomuProjectiles
{
	public static final EntityType NOSE_FANCY_CANNON = WyRegistry.createEntityType(NoseFancyCannonProjectile::new).size(1.0F, 1.0F).build("nose_fancy_cannon");
	public static final EntityType BREEZE_BREATH_BOMB = WyRegistry.createEntityType(BreezeBreathBombProjectile::new).size(1.0f, 1.0f).build("breeze_breath_bomb");

	static
	{
		WyRegistry.registerEntityType(NOSE_FANCY_CANNON, "Nose Fancy Cannon");
		WyRegistry.registerEntityType(BREEZE_BREATH_BOMB, "Breeze Breath Bomb");
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void registerEntityRenderers(FMLClientSetupEvent event)
	{
		RenderingRegistry.registerEntityRenderingHandler(NoseFancyCannonProjectile.class, new AbilityProjectileRenderer.Factory(new CubeModel()).setColor("3D2B1F").setScale(.4, .4, .4));
		RenderingRegistry.registerEntityRenderingHandler(BreezeBreathBombProjectile.class, new AbilityProjectileRenderer.Factory(new SphereModel()).setColor("3D2B1F").setScale(1, 1, 1));
	}

}