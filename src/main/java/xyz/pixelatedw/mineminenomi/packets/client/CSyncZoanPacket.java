package xyz.pixelatedw.mineminenomi.packets.client;

import java.util.function.Supplier;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncDevilFruitPacket;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.wypi.network.WyNetwork;
import xyz.pixelatedw.wypi.network.packets.server.SSyncAbilityDataPacket;

public class CSyncZoanPacket
{
	private int entityId;
	
	public CSyncZoanPacket() {}
	
	public CSyncZoanPacket(int id)
	{
		this.entityId = id;
	}
	
	public void encode(PacketBuffer buffer)
	{
		buffer.writeInt(this.entityId);
	}
	
	public static CSyncZoanPacket decode(PacketBuffer buffer)
	{
		CSyncZoanPacket msg = new CSyncZoanPacket();
		msg.entityId = buffer.readInt();
		return msg;
	}

	public static void handle(CSyncZoanPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ctx.get().getSender();

				Entity targetPlayer = player.world.getEntityByID(message.entityId);			
				if(targetPlayer == null || !(targetPlayer instanceof PlayerEntity))
					return;
				
				IDevilFruit devilFruitProps = DevilFruitCapability.get((PlayerEntity) targetPlayer);
				IAbilityData abilityDataProps = AbilityDataCapability.get((PlayerEntity) targetPlayer);

				WyNetwork.sendToAllAround(new SSyncDevilFruitPacket(targetPlayer.getEntityId(), devilFruitProps), (PlayerEntity) targetPlayer);
				WyNetwork.sendToAllAround(new SSyncAbilityDataPacket(targetPlayer.getEntityId(), abilityDataProps), (PlayerEntity) targetPlayer);
			});	
		}
		ctx.get().setPacketHandled(true);
	}
}
