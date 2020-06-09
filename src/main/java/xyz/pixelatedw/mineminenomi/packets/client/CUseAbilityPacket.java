package xyz.pixelatedw.mineminenomi.packets.client;

import java.util.function.Supplier;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncDevilFruitPacket;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.ChargeableAbility;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.wypi.abilities.IParallelContinuousAbility;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.wypi.network.WyNetwork;
import xyz.pixelatedw.wypi.network.packets.server.SSyncAbilityDataPacket;

public class CUseAbilityPacket
{
	private int slot;

	public CUseAbilityPacket()
	{
	}

	public CUseAbilityPacket(int slot)
	{
		this.slot = slot;
	}

	public void encode(PacketBuffer buffer)
	{
		buffer.writeInt(this.slot);
	}

	public static CUseAbilityPacket decode(PacketBuffer buffer)
	{
		CUseAbilityPacket msg = new CUseAbilityPacket();
		msg.slot = buffer.readInt();
		return msg;
	}

	public static void handle(CUseAbilityPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ctx.get().getSender();
				IAbilityData abilityDataProps = AbilityDataCapability.get(player);
				IDevilFruit devilFruitProps = DevilFruitCapability.get(player);

				if (player.isSpectator())
					return;

				if (AbilityHelper.checkForRestriction(player))
					return;

				Ability abl = abilityDataProps.getEquippedAbility(message.slot);

				// Only allows cancelable chargeables to be canceled early
				if (abl instanceof ChargeableAbility && abl.isCharging() && !((ChargeableAbility) abl).isCancelable())
					return;

				// Stops multiple continuous abilities from being activated if they're not parallel continuous
				if (abl instanceof ContinuousAbility && !abl.isContinuous() && !(abl instanceof IParallelContinuousAbility))
				{
					for (Ability ability : abilityDataProps.getEquippedAbilities())
					{
						if (ability instanceof ContinuousAbility && ability.isContinuous() && !(ability instanceof IParallelContinuousAbility))
						{
							return;
						}
					}
				}

				// Stops multiple zoan abilities from getting triggered at the same time
				if (abl instanceof ZoanAbility && !abl.isContinuous())
				{
					for (Ability ability : abilityDataProps.getEquippedAbilities())
					{
						if (ability instanceof ZoanAbility && ability.isContinuous())
						{
							return;
						}
					}
				}

				if (abl != null)
				{
					abl.use(player);

					if(abl.isOnStandby())
					{
						WyNetwork.sendToAllTracking(new SSyncDevilFruitPacket(player.getEntityId(), devilFruitProps), player);
						WyNetwork.sendToAllTracking(new SSyncAbilityDataPacket(player.getEntityId(), abilityDataProps), player);
					}
				}
			});
		}

		ctx.get().setPacketHandled(true);
	}

}
