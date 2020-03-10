package xyz.pixelatedw.mineminenomi;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.proxy.ClientProxy;
import xyz.pixelatedw.mineminenomi.proxy.IProxy;
import xyz.pixelatedw.mineminenomi.proxy.ServerProxy;
import xyz.pixelatedw.wypi.APIConfig;

@Mod(APIConfig.PROJECT_ID)
public class ModMain
{
	public static ModMain instance;
	public static IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());;
	public static final Logger LOGGER = LogManager.getLogger(APIConfig.PROJECT_ID);

	public ModMain()
	{
		APIConfig.setupResourceFolderPath();

		instance = this;

		CommonConfig.init();
		ModNetwork.init();
		//ModQuests.init();
	}
}
