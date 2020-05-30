package xyz.pixelatedw.mineminenomi.packets.client;

import java.util.function.Supplier;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.ChargeableAbility;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.wypi.network.WyNetwork;
import xyz.pixelatedw.wypi.network.packets.server.SSyncAbilityDataPacket;

public class CStopAbilityPacket
{
	private int slot;
	
	public CStopAbilityPacket() {}
	
	public CStopAbilityPacket(int id)
	{
		this.slot = id;
	}
	
	public void encode(PacketBuffer buffer)
	{
		buffer.writeInt(this.slot);
	}
	
	public static CStopAbilityPacket decode(PacketBuffer buffer)
	{
		CStopAbilityPacket msg = new CStopAbilityPacket();
		msg.slot = buffer.readInt();
		return msg;
	}

	public static void handle(CStopAbilityPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ctx.get().getSender();
				IAbilityData abilityDataProps = AbilityDataCapability.get(player);

				Ability ability = abilityDataProps.getEquippedAbility(message.slot);

				if(ability instanceof ContinuousAbility && ((ContinuousAbility)ability).isContinuous())
					((ContinuousAbility)ability).stopContinuity(player);
				else if(ability instanceof ChargeableAbility && ((ChargeableAbility)ability).isCharging())
					((ChargeableAbility)ability).stopCharging(player);
				else if(ability.isOnCooldown())
					ability.stopCooldown(player);
				
				abilityDataProps.setEquippedAbility(message.slot, null);
				
				WyNetwork.sendTo(new SSyncAbilityDataPacket(player.getEntityId(), abilityDataProps), player);
			});	
		}
		ctx.get().setPacketHandled(true);
	}
}