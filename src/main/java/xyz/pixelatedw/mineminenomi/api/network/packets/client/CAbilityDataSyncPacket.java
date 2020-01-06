package xyz.pixelatedw.mineminenomi.api.network.packets.client;

import java.util.function.Supplier;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.api.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.api.data.ability.IAbilityData;

public class CAbilityDataSyncPacket
{
	private INBT data;

	public CAbilityDataSyncPacket() {}
	
	public CAbilityDataSyncPacket(IAbilityData props)
	{
		this.data = new CompoundNBT();
		this.data = AbilityDataCapability.INSTANCE.getStorage().writeNBT(AbilityDataCapability.INSTANCE, props, null);
	}

	public void encode(PacketBuffer buffer)
	{
		buffer.writeCompoundTag((CompoundNBT) data);
	}
	
	public static CAbilityDataSyncPacket decode(PacketBuffer buffer)
	{
		CAbilityDataSyncPacket msg = new CAbilityDataSyncPacket();
		msg.data = buffer.readCompoundTag();
		return msg;
	}

	public static void handle(CAbilityDataSyncPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ctx.get().getSender();
				IAbilityData props = AbilityDataCapability.get(player);
				
				AbilityDataCapability.INSTANCE.getStorage().readNBT(AbilityDataCapability.INSTANCE, props, null, message.data);
			});	
		}
		ctx.get().setPacketHandled(true);
	}

}
