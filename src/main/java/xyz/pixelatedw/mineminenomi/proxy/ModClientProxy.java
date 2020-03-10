package xyz.pixelatedw.mineminenomi.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.helpers.MorphsHelper;

public class ModClientProxy implements IModProxy
{

	@Override
	public void updateEyeHeight(PlayerEntity player)
	{
		MorphsHelper.updateEyeView(player);
	}

	@Override
	public PlayerEntity getPlayer()
	{
		return Minecraft.getInstance().player;
	}

	@Override
	public World getWorld()
	{
		return Minecraft.getInstance().world;
	}

}
