package xyz.pixelatedw.wypi.network.packets.client;

import java.util.function.Supplier;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.wypi.data.quest.IQuestData;
import xyz.pixelatedw.wypi.data.quest.QuestDataCapability;
import xyz.pixelatedw.wypi.network.WyNetwork;
import xyz.pixelatedw.wypi.network.packets.server.SSyncQuestDataPacket;
import xyz.pixelatedw.wypi.quests.Quest;
import xyz.pixelatedw.wypi.quests.objectives.IObtainItemObjective;
import xyz.pixelatedw.wypi.quests.objectives.Objective;

public class CSyncQuestDataPacket
{
	private INBT data;

	public CSyncQuestDataPacket() {}

	public CSyncQuestDataPacket(IQuestData props)
	{
		this.data = new CompoundNBT();
		this.data = QuestDataCapability.INSTANCE.getStorage().writeNBT(QuestDataCapability.INSTANCE, props, null);
	}

	public void encode(PacketBuffer buffer)
	{
		buffer.writeCompoundTag((CompoundNBT) this.data);
	}

	public static CSyncQuestDataPacket decode(PacketBuffer buffer)
	{
		CSyncQuestDataPacket msg = new CSyncQuestDataPacket();
		msg.data = buffer.readCompoundTag();
		return msg;
	}

	public static void handle(CSyncQuestDataPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ctx.get().getSender();
				IQuestData props = QuestDataCapability.get(player);

				QuestDataCapability.INSTANCE.getStorage().readNBT(QuestDataCapability.INSTANCE, props, null, message.data);
							
				for(Quest quest : props.getInProgressQuests())
				{
					if(quest != null)
					{
						for(Objective obj : quest.getObjectives())
						{
							if(obj != null && obj instanceof IObtainItemObjective)
							{
								IObtainItemObjective itemQuest = (IObtainItemObjective)obj;
								for(ItemStack stack : player.inventory.mainInventory)
								{
									if(itemQuest.checkItem(stack))
									{
										obj.alterProgress(stack.getCount());
									}
								}								
							}
						}
					}
				}
				
				WyNetwork.sendTo(new SSyncQuestDataPacket(player.getEntityId(), props), player);
			});
		}
		ctx.get().setPacketHandled(true);
	}

}
