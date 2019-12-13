package xyz.pixelatedw.mineminenomi.packets.server;

import java.util.function.Supplier;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.ModMain;
import xyz.pixelatedw.mineminenomi.api.WyHelper;

public class SViewProtectionPacket
{

	private boolean state;
	private int[] midPoint;
	private int size;
	
	public SViewProtectionPacket() {}
	
	public SViewProtectionPacket(boolean state, int[] midPoint, int size) 
	{
		this.state = state;
		this.midPoint = midPoint;
		this.size = size;
	}
	
	public void encode(PacketBuffer buffer)
	{
		buffer.writeBoolean(this.state);
		buffer.writeInt(this.midPoint[0]);
		buffer.writeInt(this.midPoint[1]);
		buffer.writeInt(this.midPoint[2]);
		buffer.writeInt(this.size);
	}
	
	public static SViewProtectionPacket decode(PacketBuffer buffer)
	{
		SViewProtectionPacket msg = new SViewProtectionPacket();
		msg.state = buffer.readBoolean();
		msg.midPoint = new int[] {buffer.readInt(), buffer.readInt(), buffer.readInt()};
		msg.size = buffer.readInt();
		return msg;
	}
	
	public static void handle(SViewProtectionPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		ctx.get().enqueueWork(() ->
		{
			PlayerEntity player = ModMain.proxy.getClientPlayer();

			if(message.state)
			{
				WyHelper.createEmptyCube(player.world, message.midPoint[0], message.midPoint[1], message.midPoint[2], new int[] { message.size, message.size, message.size }, Blocks.BLUE_STAINED_GLASS, "air", "liquids", "foliage");
				WyHelper.createEmptySphere(player.world, message.midPoint[0], message.midPoint[1], message.midPoint[2], 1, Blocks.RED_STAINED_GLASS, "air", "liquids", "foliage");
			}
			else
			{
				WyHelper.createEmptyCube(player.world, message.midPoint[0], message.midPoint[1], message.midPoint[2], new int[] { message.size, message.size, message.size }, Blocks.AIR, "protection");
				WyHelper.createEmptySphere(player.world, message.midPoint[0], message.midPoint[1], message.midPoint[2], 1, Blocks.AIR, "protection");
			}
		});

		ctx.get().setPacketHandled(true);
	}
}
