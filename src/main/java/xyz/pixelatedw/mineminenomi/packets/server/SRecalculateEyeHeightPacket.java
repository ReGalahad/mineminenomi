package xyz.pixelatedw.mineminenomi.packets.server;

import java.util.function.Supplier;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

public class SRecalculateEyeHeightPacket
{
	public SRecalculateEyeHeightPacket() {}
	
	public void encode(PacketBuffer buffer) {}

	public static SRecalculateEyeHeightPacket decode(PacketBuffer buffer)
	{
		SRecalculateEyeHeightPacket msg = new SRecalculateEyeHeightPacket();
		return msg;
	}
	
	public static void handle(SRecalculateEyeHeightPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = Minecraft.getInstance().player;
				
				player.recalculateSize();
			});
		}
		ctx.get().setPacketHandled(true);
	}
}
