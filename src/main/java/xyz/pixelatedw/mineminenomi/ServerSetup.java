package xyz.pixelatedw.mineminenomi;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Env.PROJECT_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = { Dist.CLIENT, Dist.DEDICATED_SERVER })
public class ServerSetup
{

}
