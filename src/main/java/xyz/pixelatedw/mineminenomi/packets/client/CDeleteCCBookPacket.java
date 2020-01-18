package xyz.pixelatedw.mineminenomi.packets.client;

import java.util.function.Supplier;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability.Category;
import xyz.pixelatedw.mineminenomi.api.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.api.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.api.network.packets.server.SAbilityDataSyncPacket;
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
				
				abilityProps.clearHotbar();
				abilityProps.clearAbilities(Category.RACIAL);
				
				/*if(entityProps.isCyborg())
				{										
					abilityProps.addAbility(CyborgAbilities.FRESH_FIRE);
					abilityProps.addAbility(CyborgAbilities.COLA_OVERDRIVE);
					abilityProps.addAbility(CyborgAbilities.RADICAL_BEAM);
					abilityProps.addAbility(CyborgAbilities.STRONG_RIGHT);
					abilityProps.addAbility(CyborgAbilities.COUP_DE_VENT);
					
					entityProps.setMaxCola(100);
					entityProps.setCola(entityProps.getMaxCola());
				}
				
				if(entityProps.isSwordsman())
				{
					abilityProps.addAbility(SwordsmanAbilities.SHI_SHISHI_SONSON);
					if(!CommonConfig.instance.isQuestProgressionEnabled())
					{
						abilityProps.addAbility(SwordsmanAbilities.SANBYAKUROKUJU_POUND_HO);
						abilityProps.addAbility(SwordsmanAbilities.YAKKODORI);
						abilityProps.addAbility(SwordsmanAbilities.O_TATSUMAKI);
					}
				}
	
				if(entityProps.isSniper())		
				{
					abilityProps.addAbility(SniperAbilities.KAENBOSHI);
					if(!CommonConfig.instance.isQuestProgressionEnabled())
					{
						abilityProps.addAbility(SniperAbilities.KEMURIBOSHI);
						abilityProps.addAbility(SniperAbilities.RENPATSUNAMARIBOSHI);
						abilityProps.addAbility(SniperAbilities.SAKURETSUSABOTENBOSHI);
					}
				}*/
				
				for(ItemStack is : player.inventory.mainInventory)
					if(is != null && is.getItem() instanceof CharacterCreatorItem)
						player.inventory.deleteStack(is);
				
				ModNetwork.sendTo(new SEntityStatsSyncPacket(player.getEntityId(), entityProps), (ServerPlayerEntity) player);
				ModNetwork.sendTo(new SAbilityDataSyncPacket(player.getEntityId(), abilityProps), (ServerPlayerEntity) player);				
			});			
		}

		ctx.get().setPacketHandled(true);
	}

}
