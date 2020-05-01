package xyz.pixelatedw.mineminenomi.packets.server;

import java.util.function.Supplier;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.entities.mobs.misc.TraderEntity;
import xyz.pixelatedw.mineminenomi.screens.TraderScreen;

public class SOpenTraderScreenPacket
{
	private int traderEntity;

	public SOpenTraderScreenPacket()
	{
	}

	public SOpenTraderScreenPacket(int traderEntity)
	{
		this.traderEntity = traderEntity;
	}
	
	public void encode(PacketBuffer buffer)
	{
		buffer.writeInt(this.traderEntity);
	}

	public static SOpenTraderScreenPacket decode(PacketBuffer buffer)
	{
		SOpenTraderScreenPacket msg = new SOpenTraderScreenPacket();
		msg.traderEntity = buffer.readInt();
		return msg;
	}

	public static void handle(SOpenTraderScreenPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
			ctx.get().enqueueWork(() -> ClientHandler.handle(message));
		ctx.get().setPacketHandled(true);
	}

	public static class ClientHandler
	{
		@OnlyIn(Dist.CLIENT)
		public static void handle(SOpenTraderScreenPacket message)
		{
			Entity trader = Minecraft.getInstance().world.getEntityByID(message.traderEntity);
			if(trader instanceof TraderEntity)
				TraderScreen.open((TraderEntity) trader);
		}
	}
}
