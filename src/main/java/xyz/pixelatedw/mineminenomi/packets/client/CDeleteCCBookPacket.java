package xyz.pixelatedw.mineminenomi.packets.client;

import java.util.function.Supplier;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.abilities.CyborgAbilities;
import xyz.pixelatedw.mineminenomi.abilities.SniperAbilities;
import xyz.pixelatedw.mineminenomi.abilities.SwordsmanAbilities;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.api.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.api.network.packets.server.SAbilityDataSyncPacket;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.items.CharacterCreatorItem;
import xyz.pixelatedw.mineminenomi.packets.server.SEntityStatsSyncPacket;

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
				
				abilityProps.clearHotbar(player);
				abilityProps.clearRacialAbilities();
				
				if(entityProps.isCyborg())
				{										
					abilityProps.addRacialAbility(CyborgAbilities.FRESH_FIRE);
					abilityProps.addRacialAbility(CyborgAbilities.COLA_OVERDRIVE);
					abilityProps.addRacialAbility(CyborgAbilities.RADICAL_BEAM);
					abilityProps.addRacialAbility(CyborgAbilities.STRONG_RIGHT);
					abilityProps.addRacialAbility(CyborgAbilities.COUP_DE_VENT);
					
					entityProps.setMaxCola(100);
					entityProps.setCola(entityProps.getMaxCola());
				}
				
				if(entityProps.isSwordsman())
				{
					abilityProps.addRacialAbility(SwordsmanAbilities.SHI_SHISHI_SONSON);
					if(!CommonConfig.instance.isQuestProgressionEnabled())
					{
						abilityProps.addRacialAbility(SwordsmanAbilities.SANBYAKUROKUJU_POUND_HO);
						abilityProps.addRacialAbility(SwordsmanAbilities.YAKKODORI);
						abilityProps.addRacialAbility(SwordsmanAbilities.O_TATSUMAKI);
					}
				}
	
				if(entityProps.isSniper())		
				{
					abilityProps.addRacialAbility(SniperAbilities.KAENBOSHI);
					if(!CommonConfig.instance.isQuestProgressionEnabled())
					{
						abilityProps.addRacialAbility(SniperAbilities.KEMURIBOSHI);
						abilityProps.addRacialAbility(SniperAbilities.RENPATSUNAMARIBOSHI);
						abilityProps.addRacialAbility(SniperAbilities.SAKURETSUSABOTENBOSHI);
					}
				}
				
				for(ItemStack is : player.inventory.mainInventory)
					if(is != null && is.getItem() instanceof CharacterCreatorItem)
						WyHelper.removeStackFromInventory(player, is);
				
				ModNetwork.sendTo(new SEntityStatsSyncPacket(player.getEntityId(), entityProps), (ServerPlayerEntity) player);
				ModNetwork.sendTo(new SAbilityDataSyncPacket(player.getEntityId(), abilityProps), (ServerPlayerEntity) player);				
			});			
		}

		ctx.get().setPacketHandled(true);
	}

}
