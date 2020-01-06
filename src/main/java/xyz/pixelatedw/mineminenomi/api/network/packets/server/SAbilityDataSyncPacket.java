package xyz.pixelatedw.mineminenomi.api.network.packets.server;

import java.util.function.Supplier;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.IAbilityData;

public class SAbilityDataSyncPacket
{
	private int entityId;
	private INBT data;

	public SAbilityDataSyncPacket() {}
	
	public SAbilityDataSyncPacket(int entityId, IAbilityData abiltiyDataProps)
	{
		this.data = new CompoundNBT();
		this.data = AbilityDataCapability.INSTANCE.getStorage().writeNBT(AbilityDataCapability.INSTANCE, abiltiyDataProps, null);
		this.entityId = entityId;
	}

	public void encode(PacketBuffer buffer)
	{
		buffer.writeInt(this.entityId);
		buffer.writeCompoundTag((CompoundNBT) this.data);
	}
	
	public static SAbilityDataSyncPacket decode(PacketBuffer buffer)
	{
		SAbilityDataSyncPacket msg = new SAbilityDataSyncPacket();
		msg.entityId = buffer.readInt();
		msg.data = buffer.readCompoundTag();
		return msg;
	}

	public static void handle(SAbilityDataSyncPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = Minecraft.getInstance().player;

				Entity target = player.world.getEntityByID(message.entityId);			
				if(target == null || !(target instanceof LivingEntity))
					return;
				
				IAbilityData abilityDataProps = AbilityDataCapability.get((LivingEntity) target);
				AbilityDataCapability.INSTANCE.getStorage().readNBT(AbilityDataCapability.INSTANCE, abilityDataProps, null, message.data);
			});	
		}
		ctx.get().setPacketHandled(true);
	}

}
