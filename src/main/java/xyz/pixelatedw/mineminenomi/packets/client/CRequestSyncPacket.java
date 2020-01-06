package xyz.pixelatedw.mineminenomi.packets.client;

import java.util.function.Supplier;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.api.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.api.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.api.data.quest.IQuestData;
import xyz.pixelatedw.mineminenomi.api.data.quest.QuestDataCapability;
import xyz.pixelatedw.mineminenomi.api.network.packets.server.SAbilityDataSyncPacket;
import xyz.pixelatedw.mineminenomi.api.network.packets.server.SQuestDataSyncPacket;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.data.entity.extraeffects.ExtraEffectCapability;
import xyz.pixelatedw.mineminenomi.data.entity.extraeffects.IExtraEffect;
import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.packets.server.SDevilFruitSyncPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SEntityStatsSyncPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SExtraEffectSyncPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SHakiDataSyncPacket;

public class CRequestSyncPacket
{
	private byte sync;

	public CRequestSyncPacket() {}
	
	/*  
	 	First Byte = Devil Fruits
	 	Second Byte = Entity Stats
	 	Third Byte = Ability Data
	 	Forth Byte = Haki Data
	 	Fifth Byte = Extra Effects
	 	Sixth Byte = Quest Data
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
				IExtraEffect extraEffectProps = ExtraEffectCapability.get(player);
				IQuestData questDataProps = QuestDataCapability.get(player);

				if(((message.sync >> 0) & 1) == 1)
					ModNetwork.sendTo(new SDevilFruitSyncPacket(player.getEntityId(), devilFruitProps), (ServerPlayerEntity) player);
				
				if(((message.sync >> 1) & 1) == 1)
					ModNetwork.sendTo(new SEntityStatsSyncPacket(player.getEntityId(), entityStatsProps), (ServerPlayerEntity) player);
				
				if(((message.sync >> 2) & 1) == 1)
					ModNetwork.sendTo(new SAbilityDataSyncPacket(player.getEntityId(), abilityDataProps), (ServerPlayerEntity) player);
				
				if(((message.sync >> 3) & 1) == 1)
					ModNetwork.sendTo(new SHakiDataSyncPacket(player.getEntityId(), hakiDataProps), (ServerPlayerEntity) player);
				
				if(((message.sync >> 4) & 1) == 1)
					ModNetwork.sendTo(new SExtraEffectSyncPacket(player.getEntityId(), extraEffectProps), (ServerPlayerEntity) player);
				
				if(((message.sync >> 5) & 1) == 1)
					ModNetwork.sendTo(new SQuestDataSyncPacket(player.getEntityId(), questDataProps), (ServerPlayerEntity) player);
			});			
		}
		ctx.get().setPacketHandled(true);
	}

}
