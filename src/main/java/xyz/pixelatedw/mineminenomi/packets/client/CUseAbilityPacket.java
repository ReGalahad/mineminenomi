package xyz.pixelatedw.mineminenomi.packets.client;

import java.io.IOException;
import java.util.function.Supplier;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.api.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.helpers.DevilFruitsHelper;

public class CUseAbilityPacket
{
	
	private Ability ability;
	
	public CUseAbilityPacket() {}
	
	public CUseAbilityPacket(Ability ability)
	{
		this.ability = ability;
	}

	public void encode(PacketBuffer buffer)
	{
		try
		{
			buffer.writeByteArray(WyHelper.serialize(this.ability));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static CUseAbilityPacket decode(PacketBuffer buffer)
	{
		CUseAbilityPacket msg = new CUseAbilityPacket();
		try
		{
			msg.ability = (Ability) WyHelper.deserialize(buffer.readByteArray());
		}
		catch (ClassNotFoundException | IOException e)
		{
			e.printStackTrace();
		}
		return msg;
	}

	public static void handle(CUseAbilityPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		//Minecraft.getInstance().profiler.startSection("ability_use");

		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{	
				PlayerEntity player = ctx.get().getSender();
				IAbilityData abilityDataProps = AbilityDataCapability.get(player);
				
				if(DevilFruitsHelper.checkForRestriction(player))
					return;
				
				message.ability.use(player);
				
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
