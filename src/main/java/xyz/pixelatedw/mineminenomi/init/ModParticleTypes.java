package xyz.pixelatedw.mineminenomi.init;

import net.minecraft.particles.ParticleType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.wypi.APIConfig;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModParticleTypes
{

	public static final ParticleType<GenericParticleData> GENERIC_PARTICLE = (ParticleType<GenericParticleData>) new ParticleType<>(true, GenericParticleData.DESERIALIZER).setRegistryName(APIConfig.PROJECT_ID, "generic_particle");

	@SubscribeEvent
	public static void registerParticles(RegistryEvent.Register<ParticleType<?>> event)
	{
		event.getRegistry().registerAll(GENERIC_PARTICLE);
	}
}
