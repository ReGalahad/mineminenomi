package xyz.pixelatedw.mineminenomi;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.api.debug.WyDebug;
import xyz.pixelatedw.mineminenomi.proxy.ClientProxy;
import xyz.pixelatedw.mineminenomi.proxy.IProxy;
import xyz.pixelatedw.mineminenomi.proxy.ServerProxy;

@Mod(Env.PROJECT_ID)
public class ModMain
{
	public static ModMain instance;
	public static IProxy proxy;
	public static final Logger LOGGER = LogManager.getLogger();

	public ModMain()
	{
		if (WyDebug.isDebug())
		{
			String basicPath = System.getProperty("java.class.path");
			Env.projectResourceFolder = basicPath.substring(0, basicPath.indexOf("\\bin")).replace("file:/", "").replace("%20", " ") + "/src/main/resources";
		}

		instance = this;
		proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());
	}
}
