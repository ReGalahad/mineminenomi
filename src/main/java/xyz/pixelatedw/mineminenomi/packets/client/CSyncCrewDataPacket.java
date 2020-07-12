package xyz.pixelatedw.mineminenomi.packets.client;

import java.util.function.Supplier;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;

public class CSyncCrewDataPacket
{
	private CompoundNBT data;

	public CSyncCrewDataPacket() {}
	
	public CSyncCrewDataPacket(ExtendedWorldData worldData)
	{
		this.data = new CompoundNBT();
		this.data = worldData.write(this.data);
	}

	public void encode(PacketBuffer buffer)
	{
		buffer.writeCompoundTag(data);
	}
	
	public static CSyncCrewDataPacket decode(PacketBuffer buffer)
	{
		CSyncCrewDataPacket msg = new CSyncCrewDataPacket();
		msg.data = buffer.readCompoundTag();
		return msg;
	}

	public static void handle(CSyncCrewDataPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ctx.get().getSender();
				ExtendedWorldData worldData = ExtendedWorldData.get(player.world);
				worldData.read(message.data);
			});	
		}
		ctx.get().setPacketHandled(true);
	}

}
