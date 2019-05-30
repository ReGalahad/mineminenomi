package xyz.pixelatedw.MineMineNoMi3.packets;

import java.util.UUID;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedEntityData;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedNPCData;

public class PacketSyncNPCData implements IMessage
{
	public NBTTagCompound data;
	private int entityId;
	
	public PacketSyncNPCData() {}
	
	public PacketSyncNPCData(int id, ExtendedNPCData props) 
	{
		data = new NBTTagCompound();
		this.entityId = id;
		props.saveNBTData(data);
		//System.out.println(" " + this.entityUUID);
	}

	public void fromBytes(ByteBuf buffer)
	{
		data = ByteBufUtils.readTag(buffer);
		this.entityId = buffer.readInt();
	}

	public void toBytes(ByteBuf buffer)
	{
		ByteBufUtils.writeTag(buffer, data);
		buffer.writeInt(this.entityId);
	}

	public static class ClientHandler implements IMessageHandler<PacketSyncNPCData, IMessage>
	{
		@SideOnly(Side.CLIENT)
		public IMessage onMessage(PacketSyncNPCData message, MessageContext ctx) 
		{
			System.out.println("2. " + message.entityId);

			for (Object entity : Minecraft.getMinecraft().theWorld.loadedEntityList)
			{
				if(entity instanceof EntityLivingBase)
				{
					if(((EntityLivingBase) entity).getEntityId() > 200)
						System.out.println(((EntityLivingBase) entity).getEntityId());
						
					if(((EntityLivingBase) entity).getEntityId() == message.entityId)
					{
						System.out.println("Found entity");
					}
					//System.out.println("" + ((EntityLivingBase) entity).getEntityId());
					//ExtendedEntityData propz = ExtendedEntityData.get((EntityLivingBase) target);
				}
			}
			return null;
		}
	}
	
}
