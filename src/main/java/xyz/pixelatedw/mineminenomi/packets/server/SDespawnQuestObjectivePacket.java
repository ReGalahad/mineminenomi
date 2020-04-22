package xyz.pixelatedw.mineminenomi.packets.server;

import java.util.UUID;
import java.util.function.Supplier;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

public class SDespawnQuestObjectivePacket
{
	private UUID ownerUUID;
	private int targetId;
	
	public SDespawnQuestObjectivePacket()
	{		
	}
	
	public SDespawnQuestObjectivePacket(UUID uuid, int targetId)
	{
		this.ownerUUID = uuid;
		this.targetId = targetId;
	}

	public void encode(PacketBuffer buffer)
	{
		buffer.writeUniqueId(this.ownerUUID);
		buffer.writeInt(this.targetId);
	}

	public static SDespawnQuestObjectivePacket decode(PacketBuffer buffer)
	{
		SDespawnQuestObjectivePacket msg = new SDespawnQuestObjectivePacket();
		msg.ownerUUID = buffer.readUniqueId();
		msg.targetId = buffer.readInt();
		return msg;
	}

	public static void handle(SDespawnQuestObjectivePacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
		{
			ctx.get().enqueueWork(() ->
			{
				ClientHandler.handle(message);
			});
		}
		ctx.get().setPacketHandled(true);
	}
	
	public static class ClientHandler
	{
		@OnlyIn(Dist.CLIENT)
		public static void handle(SDespawnQuestObjectivePacket message)
		{
			PlayerEntity player = Minecraft.getInstance().player;
			World world = Minecraft.getInstance().world;
			Entity target = world.getEntityByID(message.targetId);
			
			if(player.getUniqueID().equals(message.ownerUUID))
				return;
			
			target.remove();
		}
	}

}
