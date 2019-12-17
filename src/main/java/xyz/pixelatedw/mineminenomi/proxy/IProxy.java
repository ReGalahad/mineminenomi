package xyz.pixelatedw.mineminenomi.proxy;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;

public interface IProxy 
{
	
    PlayerEntity getClientPlayer();

    World getClientWorld();
    
	void spawnParticleEffect(ParticleEffect effect, World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ);
	
	void openCharacterCreatorScreen(PlayerEntity player);
	
	void openWantedPosterScreen(PlayerEntity player, CompoundNBT nbt);
	
	void openQuestAcceptScreen(PlayerEntity player, Quest quest);
	
	void updateEyeHeight(PlayerEntity player);
}