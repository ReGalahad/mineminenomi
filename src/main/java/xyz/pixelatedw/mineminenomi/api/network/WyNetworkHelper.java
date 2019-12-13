package xyz.pixelatedw.mineminenomi.api.network;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.PacketDistributor.TargetPoint;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class WyNetworkHelper
{

	public static <MSG> void sendToServer(SimpleChannel channel, MSG msg)
	{
		channel.sendToServer(msg);
	}
	
	public static <MSG> void sendTo(SimpleChannel channel, MSG msg, ServerPlayerEntity player)
	{
		if (!(player instanceof FakePlayer))
		{
			channel.sendTo(msg, player.connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		}
	}
	
	public static <MSG> void sendToAll(SimpleChannel channel, MSG msg)
	{
		channel.send(PacketDistributor.ALL.noArg(), msg);
	}
	
	public static <MSG> void sendToAllAround(SimpleChannel channel, MSG msg, LivingEntity sender)
	{
		try
		{
			channel.send(PacketDistributor.NEAR.with(() -> new TargetPoint(sender.posX, sender.posY, sender.posZ, 50, sender.dimension)), msg);
			if(sender instanceof ServerPlayerEntity)
				sendTo(channel, msg, (ServerPlayerEntity) sender);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return;
		}
	}

}
