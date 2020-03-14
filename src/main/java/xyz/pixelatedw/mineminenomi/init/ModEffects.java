package xyz.pixelatedw.mineminenomi.init;

import net.minecraft.potion.Effect;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;
import xyz.pixelatedw.mineminenomi.effects.BubblyCoralEffect;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyRegistry;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(APIConfig.PROJECT_ID)
public class ModEffects
{
	public static final BubblyCoralEffect BUBBLY_CORAL = null;
	
	@SubscribeEvent
	public static void registerEffects(RegistryEvent.Register<Effect> event)
	{
		event.getRegistry().registerAll
		(
			WyRegistry.registerEffect("Bubbly Coral", new BubblyCoralEffect())
		);
	}
}