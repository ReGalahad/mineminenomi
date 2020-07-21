package xyz.pixelatedw.mineminenomi.packets.client;

import java.util.function.Supplier;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.events.custom.DorikiEvent;
import xyz.pixelatedw.mineminenomi.items.CharacterCreatorItem;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.wypi.network.WyNetwork;
import xyz.pixelatedw.wypi.network.packets.server.SSyncAbilityDataPacket;

public class CDeleteCCBookPacket
{
	public CDeleteCCBookPacket() {}

	public void encode(PacketBuffer buffer)
	{

	}
	
	public static CDeleteCCBookPacket decode(PacketBuffer buffer)
	{
		CDeleteCCBookPacket msg = new CDeleteCCBookPacket();

		return msg;
	}

	public static void handle(CDeleteCCBookPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ctx.get().getSender();
				IEntityStats entityProps = EntityStatsCapability.get(player);
				IAbilityData abilityProps = AbilityDataCapability.get(player);
				
				DorikiEvent e = new DorikiEvent(player);
				MinecraftForge.EVENT_BUS.post(e);
				
				AbilityHelper.validateRacialMoves(player);
				
				if(entityProps.isCyborg())
				{
					entityProps.setMaxCola(100);
					entityProps.setCola(entityProps.getMaxCola());
				}
				
				for(ItemStack is : player.inventory.mainInventory)
					if(is != null && is.getItem() instanceof CharacterCreatorItem)
						player.inventory.deleteStack(is);
				
				WyNetwork.sendTo(new SSyncEntityStatsPacket(player.getEntityId(), entityProps), (ServerPlayerEntity) player);
				WyNetwork.sendTo(new SSyncAbilityDataPacket(player.getEntityId(), abilityProps), (ServerPlayerEntity) player);				
			});			
		}

		ctx.get().setPacketHandled(true);
	}

}
