package xyz.pixelatedw.mineminenomi.packets.client;

import java.util.function.Supplier;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
import xyz.pixelatedw.mineminenomi.packets.server.SDevilFruitSyncPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SEntityStatsSyncPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SHakiDataSyncPacket;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.wypi.network.WyNetwork;
import xyz.pixelatedw.wypi.network.packets.server.SSyncAbilityDataPacket;

public class CRequestSyncPacket
{
	private byte sync;

	public CRequestSyncPacket() {}
	
	/*  
	 	First Byte = Devil Fruits
	 	Second Byte = Entity Stats
	 	Third Byte = Ability Data
	 	Forth Byte = Haki Data
	 	Fifth Byte = 
	 	Sixth Byte = 
	 	Seventh Byte = 
	 	Eight Byte = 
	 */
	public CRequestSyncPacket(byte sync)
	{
		this.sync = sync;
	}

	public void encode(PacketBuffer buffer)
	{
		buffer.writeByte(this.sync);
	}
	
	public static CRequestSyncPacket decode(PacketBuffer buffer)
	{
		CRequestSyncPacket msg = new CRequestSyncPacket();
		msg.sync = buffer.readByte();
		return msg;
	}

	public static void handle(CRequestSyncPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ctx.get().getSender();
				IDevilFruit devilFruitProps = DevilFruitCapability.get(player);
				IEntityStats entityStatsProps = EntityStatsCapability.get(player);
				IAbilityData abilityDataProps = AbilityDataCapability.get(player);
				IHakiData hakiDataProps = HakiDataCapability.get(player);
				
				if(((message.sync >> 0) & 1) == 1)
					WyNetwork.sendTo(new SDevilFruitSyncPacket(player.getEntityId(), devilFruitProps), (ServerPlayerEntity) player);
				
				if(((message.sync >> 1) & 1) == 1)
					WyNetwork.sendTo(new SEntityStatsSyncPacket(player.getEntityId(), entityStatsProps), (ServerPlayerEntity) player);
				
				if(((message.sync >> 2) & 1) == 1)
					WyNetwork.sendTo(new SSyncAbilityDataPacket(abilityDataProps), (ServerPlayerEntity) player);
				
				if(((message.sync >> 3) & 1) == 1)
					WyNetwork.sendTo(new SHakiDataSyncPacket(player.getEntityId(), hakiDataProps), (ServerPlayerEntity) player);
			});			
		}
		ctx.get().setPacketHandled(true);
	}

}
