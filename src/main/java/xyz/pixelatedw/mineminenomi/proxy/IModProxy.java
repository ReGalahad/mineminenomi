package xyz.pixelatedw.mineminenomi.proxy;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.wypi.proxy.IProxy;

public interface IModProxy extends IProxy
{
	void updateEyeHeight(PlayerEntity player);
}