package xyz.pixelatedw.mineminenomi.packets.client;

import java.util.function.Supplier;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.api.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.api.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.helpers.DevilFruitsHelper;

public class CUseAbilityPacket
{
	private String ability;
	
	public CUseAbilityPacket() {}
	
	public CUseAbilityPacket(String ability)
	{
		this.ability = ability;
	}

	public void encode(PacketBuffer buffer)
	{
		buffer.writeString(this.ability);
	}
	
	public static CUseAbilityPacket decode(PacketBuffer buffer)
	{
		CUseAbilityPacket msg = new CUseAbilityPacket();
		msg.ability = buffer.readString();
		return msg;
	}

	public static void handle(CUseAbilityPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{	
				PlayerEntity player = ctx.get().getSender();
				IAbilityData abilityDataProps = AbilityDataCapability.get(player);
				
				if(DevilFruitsHelper.checkForRestriction(player))
					return;
				
				abilityDataProps.getAbility(message.ability).use(player);
								
				/*Ability currentAbility = abilityDataProps.getHotbarAbilityFromSlot(message.abilitySlot);
								
				if (currentAbility != null)
				{
					//ThreadTaskExecutor<?> executor = LogicalSidedProvider.WORKQUEUE.get(LogicalSide.SERVER);
					
					//executor.runAsync(() -> 
					//{
						for (int j = 0; j < 8; j++)
						{
							Ability testAbility = abilityDataProps.getHotbarAbilityFromSlot(j);
							if (testAbility != null)
							{
								if (testAbility.isCharging() && testAbility == currentAbility && currentAbility.getAttribute().canStopChargeEarly())
									currentAbility.endCharging(player);
								
								if (testAbility.isCharging())
									return;
								
								if (currentAbility != testAbility && testAbility.isPassiveActive() && currentAbility.getAttribute().isPassive())
								{
									if(currentAbility.getAttribute().isAbilityFreePassive())
										currentAbility.passive(player);
									else if(!currentAbility.getAttribute().isAbilityFreePassive() && testAbility.getAttribute().isAbilityFreePassive() && testAbility.isPassiveActive())
										currentAbility.passive(player);								
									return;
								}
							}
						}
						
						if (currentAbility.getAttribute().isPassive())
							currentAbility.passive(player);
						else if (currentAbility.getAttribute().getAbilityCharges() > 0)
							currentAbility.startCharging(player);
						else
							currentAbility.use(player);
					//});
				}*/
			});
		}
		
		ctx.get().setPacketHandled(true);
	}
	
}
