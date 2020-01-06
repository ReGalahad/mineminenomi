package xyz.pixelatedw.mineminenomi.proxy;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.helpers.MorphsHelper;

public class ClientProxy implements IProxy
{

	@Override
	public void updateEyeHeight(PlayerEntity player)
	{
		MorphsHelper.updateEyeView(player);
	}

}
