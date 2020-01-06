package xyz.pixelatedw.mineminenomi.packets.server;

import java.util.function.Supplier;

import net.minecraft.network.PacketBuffer;
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
			ctx.get().enqueueWork(WantedPosterScreen::open);
		ctx.get().setPacketHandled(true);
	}

}
