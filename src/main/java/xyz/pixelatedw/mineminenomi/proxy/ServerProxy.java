package xyz.pixelatedw.mineminenomi.proxy;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.IParticleData;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
import xyz.pixelatedw.mineminenomi.particles.CustomParticleData;

public class ServerProxy implements IProxy
{

	@Override
	public PlayerEntity getClientPlayer()
	{
		return null;
	}

	@Override
	public World getClientWorld()
	{
		return null;
	}

	@Override
	public void spawnLogiaParticles(World world, String fx, double posX, double posY, double posZ) {}

	@Override
	public void spawnParticles(World world, CustomParticleData data) {}

	@Override
	public boolean spawnParticleEffects(PlayerEntity player, double posX, double posY, double posZ, String fx)
	{
		return false;
	}

	@Override
	public void openCharacterCreatorScreen(PlayerEntity player) {}
	
	@Override
	public void openWantedPosterScreen(PlayerEntity player, CompoundNBT nbt) {}

	@Override
	public void openQuestAcceptScreen(PlayerEntity player, Quest quest) {}
	
	@Override
	public void updateEyeHeight(PlayerEntity player) {}

	@Override
	public void spawnVanillaParticle(IParticleData particle, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {}

}
