package xyz.pixelatedw.mineminenomi.proxy;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.ModMain;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;

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
	public void spawnParticleEffect(ParticleEffect effect, World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		ModMain.LOGGER.warn("Can't spawn particles on server side !");
	}
	
	@Override
	public void openCharacterCreatorScreen(PlayerEntity player) {}
	
	@Override
	public void openWantedPosterScreen(PlayerEntity player, CompoundNBT nbt) {}

	@Override
	public void openQuestAcceptScreen(PlayerEntity player, Quest quest) {}
	
	@Override
	public void updateEyeHeight(PlayerEntity player) {}

}
