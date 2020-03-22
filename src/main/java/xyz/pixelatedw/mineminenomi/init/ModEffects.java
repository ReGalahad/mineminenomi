package xyz.pixelatedw.mineminenomi.init;

import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.effects.BubblyCoralEffect;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyRegistry;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEffects
{
	public static final BubblyCoralEffect BUBBLY_CORAL = new BubblyCoralEffect();
	
	static
	{
		WyRegistry.registerEffect(BUBBLY_CORAL, "Bubbly Coral");
	}
}