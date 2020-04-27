package xyz.pixelatedw.mineminenomi.init;

import xyz.pixelatedw.mineminenomi.packets.client.CCombatModeTriggerPacket;
import xyz.pixelatedw.mineminenomi.packets.client.CDeleteCCBookPacket;
import xyz.pixelatedw.mineminenomi.packets.client.CDevilFruitSyncPacket;
import xyz.pixelatedw.mineminenomi.packets.client.CEntityStatsSyncPacket;
import xyz.pixelatedw.mineminenomi.packets.client.CGiveItemStackPacket;
import xyz.pixelatedw.mineminenomi.packets.client.CRequestQuestDataSyncPacket;
import xyz.pixelatedw.mineminenomi.packets.client.CRequestSyncPacket;
import xyz.pixelatedw.mineminenomi.packets.client.CUpdateTraderStacksPacket;
import xyz.pixelatedw.mineminenomi.packets.client.CUseAbilityPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SDespawnQuestObjectivePacket;
import xyz.pixelatedw.mineminenomi.packets.server.SEntityStatsSyncPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SOpenCharacterCreatorScreenPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SOpenWantedPosterScreenPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SRecalculateEyeHeightPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SSpecialFlyingPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncDevilFruitPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SViewProtectionPacket;
import xyz.pixelatedw.wypi.APIDefaults;
import xyz.pixelatedw.wypi.network.WyNetwork;
import xyz.pixelatedw.wypi.network.packets.client.CSyncAbilityDataPacket;
import xyz.pixelatedw.wypi.network.packets.server.SSyncAbilityDataPacket;

public class ModNetwork
{
	public static void init() 
	{
		APIDefaults.initPackets();

		// From Client to Server
		WyNetwork.registerPacket(CRequestSyncPacket.class, CRequestSyncPacket::encode, CRequestSyncPacket::decode, CRequestSyncPacket::handle);
		WyNetwork.registerPacket(CCombatModeTriggerPacket.class, CCombatModeTriggerPacket::encode, CCombatModeTriggerPacket::decode, CCombatModeTriggerPacket::handle);
		WyNetwork.registerPacket(CUseAbilityPacket.class, CUseAbilityPacket::encode, CUseAbilityPacket::decode, CUseAbilityPacket::handle);
		WyNetwork.registerPacket(CDeleteCCBookPacket.class, CDeleteCCBookPacket::encode, CDeleteCCBookPacket::decode, CDeleteCCBookPacket::handle);
		WyNetwork.registerPacket(CEntityStatsSyncPacket.class, CEntityStatsSyncPacket::encode, CEntityStatsSyncPacket::decode, CEntityStatsSyncPacket::handle);
		WyNetwork.registerPacket(CDevilFruitSyncPacket.class, CDevilFruitSyncPacket::encode, CDevilFruitSyncPacket::decode, CDevilFruitSyncPacket::handle);
		WyNetwork.registerPacket(CSyncAbilityDataPacket.class, CSyncAbilityDataPacket::encode, CSyncAbilityDataPacket::decode, CSyncAbilityDataPacket::handle);
		WyNetwork.registerPacket(CGiveItemStackPacket.class, CGiveItemStackPacket::encode, CGiveItemStackPacket::decode, CGiveItemStackPacket::handle);
		WyNetwork.registerPacket(CUpdateTraderStacksPacket.class, CUpdateTraderStacksPacket::encode, CUpdateTraderStacksPacket::decode, CUpdateTraderStacksPacket::handle);
		WyNetwork.registerPacket(CRequestQuestDataSyncPacket.class, CRequestQuestDataSyncPacket::encode, CRequestQuestDataSyncPacket::decode, CRequestQuestDataSyncPacket::handle);
		
		// From Server to Client
		WyNetwork.registerPacket(SSyncDevilFruitPacket.class, SSyncDevilFruitPacket::encode, SSyncDevilFruitPacket::decode, SSyncDevilFruitPacket::handle);
		WyNetwork.registerPacket(SSyncAbilityDataPacket.class, SSyncAbilityDataPacket::encode, SSyncAbilityDataPacket::decode, SSyncAbilityDataPacket::handle);
		WyNetwork.registerPacket(SEntityStatsSyncPacket.class, SEntityStatsSyncPacket::encode, SEntityStatsSyncPacket::decode, SEntityStatsSyncPacket::handle);
		WyNetwork.registerPacket(SSpecialFlyingPacket.class, SSpecialFlyingPacket::encode, SSpecialFlyingPacket::decode, SSpecialFlyingPacket::handle);
		WyNetwork.registerPacket(SRecalculateEyeHeightPacket.class, SRecalculateEyeHeightPacket::encode, SRecalculateEyeHeightPacket::decode, SRecalculateEyeHeightPacket::handle);
		WyNetwork.registerPacket(SViewProtectionPacket.class, SViewProtectionPacket::encode, SViewProtectionPacket::decode, SViewProtectionPacket::handle);
		WyNetwork.registerPacket(SOpenCharacterCreatorScreenPacket.class, SOpenCharacterCreatorScreenPacket::encode, SOpenCharacterCreatorScreenPacket::decode, SOpenCharacterCreatorScreenPacket::handle);
		WyNetwork.registerPacket(SOpenWantedPosterScreenPacket.class, SOpenWantedPosterScreenPacket::encode, SOpenWantedPosterScreenPacket::decode, SOpenWantedPosterScreenPacket::handle);
		WyNetwork.registerPacket(SDespawnQuestObjectivePacket.class, SDespawnQuestObjectivePacket::encode, SDespawnQuestObjectivePacket::decode, SDespawnQuestObjectivePacket::handle);

	}
}
