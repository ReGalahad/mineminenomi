package xyz.pixelatedw.mineminenomi.proxy;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.IParticleData;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
import xyz.pixelatedw.mineminenomi.particles.CustomParticleData;

public interface IProxy 
{
	
    PlayerEntity getClientPlayer();

    World getClientWorld();
    
	void spawnLogiaParticles(World world, String fx, double posX, double posY, double posZ);

	void spawnVanillaParticle(IParticleData particle, double posX, double posY, double posZ, double motionX, double motionY, double motionZ);
	
	void spawnParticles(World world, CustomParticleData data);
	
	boolean spawnParticleEffects(PlayerEntity player, double posX, double posY, double posZ, String fx);

	void openCharacterCreatorScreen(PlayerEntity player);
	
	void openWantedPosterScreen(PlayerEntity player, CompoundNBT nbt);
	
	void openQuestAcceptScreen(PlayerEntity player, Quest quest);
	
	void updateEyeHeight(PlayerEntity player);
}