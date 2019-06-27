package xyz.pixelatedw.MineMineNoMi3.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.quest.objectives.IQuestObjective;

public class PacketQuestObjectiveSpawn implements IMessage
{
	
	private int ownerId, objectiveId;

	public PacketQuestObjectiveSpawn() {}
	
	public PacketQuestObjectiveSpawn(int ownerId, int objectiveId) 
	{
		this.ownerId = ownerId;
		this.objectiveId = objectiveId;
	}

	@Override
	public void fromBytes(ByteBuf buffer) 
	{
		this.ownerId = buffer.readInt();
		this.objectiveId = buffer.readInt();
	}
	
	@Override
	public void toBytes(ByteBuf buffer) 
	{
		buffer.writeInt(this.ownerId);
		buffer.writeInt(this.objectiveId);
	}

	public static class ClientHandler implements IMessageHandler<PacketQuestObjectiveSpawn, IMessage>
	{
		@Override
		@SideOnly(Side.CLIENT)
		public IMessage onMessage(PacketQuestObjectiveSpawn message, MessageContext ctx) 
		{
			EntityPlayer player = Minecraft.getMinecraft().thePlayer;

			if(player.getEntityId() == message.ownerId)
				return null;
			
			for(Object entityObject : player.worldObj.loadedEntityList)
			{
				Entity entity = (Entity) entityObject;
				if(entity instanceof IQuestObjective)
				{
					System.out.println(entity);
					//entity.setDead();
				}
			}
			
			//player.worldObj.getEntityByID(message.objectiveId).setDead();
			
			return null;
		}
	}
}
