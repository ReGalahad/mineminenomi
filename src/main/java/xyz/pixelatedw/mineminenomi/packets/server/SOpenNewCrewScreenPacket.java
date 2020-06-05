package xyz.pixelatedw.mineminenomi.packets.server;

import java.util.function.Supplier;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.screens.NewCrewScreen;

public class SOpenNewCrewScreenPacket
{

	public SOpenNewCrewScreenPacket()
	{
	}

	public void encode(PacketBuffer buffer)
	{
	}

	public static SOpenNewCrewScreenPacket decode(PacketBuffer buffer)
	{
		SOpenNewCrewScreenPacket msg = new SOpenNewCrewScreenPacket();
		return msg;
	}

	public static void handle(SOpenNewCrewScreenPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
			ctx.get().enqueueWork(() -> ClientHandler.handle(message));
		ctx.get().setPacketHandled(true);
	}

	public static class ClientHandler
	{
		@OnlyIn(Dist.CLIENT)
		public static void handle(SOpenNewCrewScreenPacket message)
		{
			NewCrewScreen.open();
		}
	}
}
