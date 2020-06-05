package xyz.pixelatedw.mineminenomi.init;

import xyz.pixelatedw.mineminenomi.packets.client.CCombatModeTriggerPacket;
import xyz.pixelatedw.mineminenomi.packets.client.CDeleteCCBookPacket;
import xyz.pixelatedw.mineminenomi.packets.client.CDevilFruitSyncPacket;
import xyz.pixelatedw.mineminenomi.packets.client.CEntityStatsSyncPacket;
import xyz.pixelatedw.mineminenomi.packets.client.CGiveItemStackPacket;
import xyz.pixelatedw.mineminenomi.packets.client.CJollyRogerSyncPacket;
import xyz.pixelatedw.mineminenomi.packets.client.CRequestQuestDataSyncPacket;
import xyz.pixelatedw.mineminenomi.packets.client.CRequestSyncPacket;
import xyz.pixelatedw.mineminenomi.packets.client.CStopAbilityPacket;
import xyz.pixelatedw.mineminenomi.packets.client.CSyncZoanPacket;
import xyz.pixelatedw.mineminenomi.packets.client.CUpdateTraderOffersPacket;
import xyz.pixelatedw.mineminenomi.packets.client.CUseAbilityPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SDespawnQuestObjectivePacket;
import xyz.pixelatedw.mineminenomi.packets.server.SEntityStatsSyncPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SFlySpeedPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SJollyRogerSyncPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SOpenCharacterCreatorScreenPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SOpenJollyRogerCreatorScreenPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SOpenQuestChooseScreenPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SOpenTraderScreenPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SOpenWantedPosterScreenPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SRecalculateEyeHeightPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncDevilFruitPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SUpdateTraderOffersPacket;
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
		WyNetwork.registerPacket(CUpdateTraderOffersPacket.class, CUpdateTraderOffersPacket::encode, CUpdateTraderOffersPacket::decode, CUpdateTraderOffersPacket::handle);
		WyNetwork.registerPacket(CRequestQuestDataSyncPacket.class, CRequestQuestDataSyncPacket::encode, CRequestQuestDataSyncPacket::decode, CRequestQuestDataSyncPacket::handle);
		WyNetwork.registerPacket(CJollyRogerSyncPacket.class, CJollyRogerSyncPacket::encode, CJollyRogerSyncPacket::decode, CJollyRogerSyncPacket::handle);
		WyNetwork.registerPacket(CSyncZoanPacket.class, CSyncZoanPacket::encode, CSyncZoanPacket::decode, CSyncZoanPacket::handle);
		WyNetwork.registerPacket(CStopAbilityPacket.class, CStopAbilityPacket::encode, CStopAbilityPacket::decode, CStopAbilityPacket::handle);
		
		// From Server to Client
		WyNetwork.registerPacket(SSyncDevilFruitPacket.class, SSyncDevilFruitPacket::encode, SSyncDevilFruitPacket::decode, SSyncDevilFruitPacket::handle);
		WyNetwork.registerPacket(SSyncAbilityDataPacket.class, SSyncAbilityDataPacket::encode, SSyncAbilityDataPacket::decode, SSyncAbilityDataPacket::handle);
		WyNetwork.registerPacket(SEntityStatsSyncPacket.class, SEntityStatsSyncPacket::encode, SEntityStatsSyncPacket::decode, SEntityStatsSyncPacket::handle);
		WyNetwork.registerPacket(SFlySpeedPacket.class, SFlySpeedPacket::encode, SFlySpeedPacket::decode, SFlySpeedPacket::handle);
		WyNetwork.registerPacket(SRecalculateEyeHeightPacket.class, SRecalculateEyeHeightPacket::encode, SRecalculateEyeHeightPacket::decode, SRecalculateEyeHeightPacket::handle);
		WyNetwork.registerPacket(SViewProtectionPacket.class, SViewProtectionPacket::encode, SViewProtectionPacket::decode, SViewProtectionPacket::handle);
		WyNetwork.registerPacket(SOpenCharacterCreatorScreenPacket.class, SOpenCharacterCreatorScreenPacket::encode, SOpenCharacterCreatorScreenPacket::decode, SOpenCharacterCreatorScreenPacket::handle);
		WyNetwork.registerPacket(SOpenWantedPosterScreenPacket.class, SOpenWantedPosterScreenPacket::encode, SOpenWantedPosterScreenPacket::decode, SOpenWantedPosterScreenPacket::handle);
		WyNetwork.registerPacket(SDespawnQuestObjectivePacket.class, SDespawnQuestObjectivePacket::encode, SDespawnQuestObjectivePacket::decode, SDespawnQuestObjectivePacket::handle);
		WyNetwork.registerPacket(SUpdateTraderOffersPacket.class, SUpdateTraderOffersPacket::encode, SUpdateTraderOffersPacket::decode, SUpdateTraderOffersPacket::handle);
		WyNetwork.registerPacket(SOpenTraderScreenPacket.class, SOpenTraderScreenPacket::encode, SOpenTraderScreenPacket::decode, SOpenTraderScreenPacket::handle);
		WyNetwork.registerPacket(SJollyRogerSyncPacket.class, SJollyRogerSyncPacket::encode, SJollyRogerSyncPacket::decode, SJollyRogerSyncPacket::handle);
		WyNetwork.registerPacket(SOpenJollyRogerCreatorScreenPacket.class, SOpenJollyRogerCreatorScreenPacket::encode, SOpenJollyRogerCreatorScreenPacket::decode, SOpenJollyRogerCreatorScreenPacket::handle);
		WyNetwork.registerPacket(SOpenQuestChooseScreenPacket.class, SOpenQuestChooseScreenPacket::encode, SOpenQuestChooseScreenPacket::decode, SOpenQuestChooseScreenPacket::handle);
	}
}
