package xyz.pixelatedw.mineminenomi.init;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.containers.TraderContainer;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyRegistry;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModContainers
{
	public static final ContainerType<TraderContainer> TRADER = IForgeContainerType.create(TraderContainer::new);

	static
	{
		WyRegistry.registerContainerType(TRADER, "Trader");
	}
}
