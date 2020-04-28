package xyz.pixelatedw.mineminenomi.packets.client;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.entities.mobs.misc.TraderEntity;

public class CUpdateTraderStacksPacket {



	private List<ItemStack> stacks;
	private int size;
	private TraderEntity trader;
	private int entityId;
	public CUpdateTraderStacksPacket() {}
	
	public CUpdateTraderStacksPacket(TraderEntity e)
	{
		this.stacks = e.STACKS;
		this.size = stacks.size();
		this.trader = e;
		this.entityId = e.getEntityId();
	}

	public void encode(PacketBuffer buffer)
	{
		buffer.writeInt(entityId);
		buffer.writeInt(size);
		for(ItemStack stack : this.stacks) {
		buffer.writeItemStack(stack, false);
		}
	}
	
	public static CUpdateTraderStacksPacket decode(PacketBuffer buffer)
	{
		CUpdateTraderStacksPacket msg = new CUpdateTraderStacksPacket();
		msg.entityId = buffer.readInt();
		msg.stacks = new ArrayList<ItemStack>();
		msg.size = buffer.readInt();
		for(int i = 0; i < msg.size; i++) {
			msg.stacks.add(buffer.readItemStack());
		}
		return msg;
	}

	public static void handle(CUpdateTraderStacksPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ctx.get().getSender();
				message.trader = (TraderEntity) player.world.getEntityByID(message.entityId);
				message.trader.STACKS = message.stacks;
			});	
		}
		ctx.get().setPacketHandled(true);
	}

}
