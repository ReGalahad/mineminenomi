package xyz.pixelatedw.mineminenomi.proxy;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ModServerProxy implements IModProxy
{

	@Override
	public void updateEyeHeight(PlayerEntity player) {}

	@Override
	public PlayerEntity getPlayer()
	{
		return null;
	}

	@Override
	public World getWorld()
	{
		return null;
	}

}
