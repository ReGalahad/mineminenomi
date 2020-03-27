package xyz.pixelatedw.mineminenomi.packets.server;

import java.util.function.Supplier;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.screens.WantedPosterScreen;

public class SOpenWantedPosterScreenPacket
{

	public SOpenWantedPosterScreenPacket()
	{
	}

	public void encode(PacketBuffer buffer)
	{
	}

	public static SOpenWantedPosterScreenPacket decode(PacketBuffer buffer)
	{
		SOpenWantedPosterScreenPacket msg = new SOpenWantedPosterScreenPacket();
		return msg;
	}

	public static void handle(SOpenWantedPosterScreenPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
			ctx.get().enqueueWork(() -> ClientHandler.handle(message));
		ctx.get().setPacketHandled(true);
	}

	public static class ClientHandler
	{
		@OnlyIn(Dist.CLIENT)
		public static void handle(SOpenWantedPosterScreenPacket message)
		{
			WantedPosterScreen.open();
		}
	}
}
