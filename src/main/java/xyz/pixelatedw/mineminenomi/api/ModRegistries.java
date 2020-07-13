package xyz.pixelatedw.mineminenomi.api;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryManager;
import xyz.pixelatedw.mineminenomi.api.crew.JollyRogerElement;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.APIRegistries;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class ModRegistries
{
	static
	{
		APIRegistries.make(new ResourceLocation(APIConfig.PROJECT_ID, "jolly_roger_elements"), JollyRogerElement.class);
	}	
	
	public static final IForgeRegistry<JollyRogerElement> JOLLY_ROGER_ELEMENTS = RegistryManager.ACTIVE.getRegistry(JollyRogerElement.class);
}
