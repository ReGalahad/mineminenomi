package xyz.pixelatedw.mineminenomi.packets.client;

import java.util.function.Supplier;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.IAbilityData;

public class CCombatModeTriggerPacket
{
	private boolean combatMode = false;
	
	public CCombatModeTriggerPacket() {}

	public void encode(PacketBuffer buffer)
	{
		buffer.writeBoolean(this.combatMode);
	}
	
	public static CCombatModeTriggerPacket decode(PacketBuffer buffer)
	{
		CCombatModeTriggerPacket msg = new CCombatModeTriggerPacket();
		msg.combatMode = buffer.readBoolean();
		return msg;
	}

	public static void handle(CCombatModeTriggerPacket message, final Supplier<NetworkEvent.Context> ctx)
	{

		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ctx.get().getSender();
				IAbilityData props = AbilityDataCapability.get(player);
				
				props.setCombatMode(!props.isInCombatMode());							
			});			
		}
		ctx.get().setPacketHandled(true);
	}

}
