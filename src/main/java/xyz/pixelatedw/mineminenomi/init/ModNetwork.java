package xyz.pixelatedw.mineminenomi.init;

import xyz.pixelatedw.mineminenomi.packets.client.CCombatModeTriggerPacket;
import xyz.pixelatedw.mineminenomi.packets.client.CCreateCrewPacket;
import xyz.pixelatedw.mineminenomi.packets.client.CDeleteCCBookPacket;
import xyz.pixelatedw.mineminenomi.packets.client.CGiveItemStackPacket;
import xyz.pixelatedw.mineminenomi.packets.client.CLeaveCrewPacket;
import xyz.pixelatedw.mineminenomi.packets.client.CRequestSyncQuestDataPacket;
import xyz.pixelatedw.mineminenomi.packets.client.CRequestSyncWorldDataPacket;
import xyz.pixelatedw.mineminenomi.packets.client.CStopAbilityPacket;
import xyz.pixelatedw.mineminenomi.packets.client.CSyncDevilFruitPacket;
import xyz.pixelatedw.mineminenomi.packets.client.CSyncEntityStatsPacket;
import xyz.pixelatedw.mineminenomi.packets.client.CSyncZoanPacket;
import xyz.pixelatedw.mineminenomi.packets.client.CUpdateJollyRogerPacket;
import xyz.pixelatedw.mineminenomi.packets.client.CUpdateTraderOffersPacket;
import xyz.pixelatedw.mineminenomi.packets.client.CUseAbilityPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SDespawnQuestObjectivePacket;
import xyz.pixelatedw.mineminenomi.packets.server.SFlySpeedPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SOpenCharacterCreatorScreenPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SOpenJollyRogerCreatorScreenPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SOpenNewCrewScreenPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SOpenQuestChooseScreenPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SOpenTraderScreenPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SOpenWantedPosterScreenPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SRecalculateEyeHeightPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncDevilFruitPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncWorldDataPacket;
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
		WyNetwork.registerPacket(CCombatModeTriggerPacket.class, CCombatModeTriggerPacket::encode, CCombatModeTriggerPacket::decode, CCombatModeTriggerPacket::handle);
		WyNetwork.registerPacket(CUseAbilityPacket.class, CUseAbilityPacket::encode, CUseAbilityPacket::decode, CUseAbilityPacket::handle);
		WyNetwork.registerPacket(CDeleteCCBookPacket.class, CDeleteCCBookPacket::encode, CDeleteCCBookPacket::decode, CDeleteCCBookPacket::handle);
		WyNetwork.registerPacket(CSyncEntityStatsPacket.class, CSyncEntityStatsPacket::encode, CSyncEntityStatsPacket::decode, CSyncEntityStatsPacket::handle);
		WyNetwork.registerPacket(CSyncDevilFruitPacket.class, CSyncDevilFruitPacket::encode, CSyncDevilFruitPacket::decode, CSyncDevilFruitPacket::handle);
		WyNetwork.registerPacket(CSyncAbilityDataPacket.class, CSyncAbilityDataPacket::encode, CSyncAbilityDataPacket::decode, CSyncAbilityDataPacket::handle);
		WyNetwork.registerPacket(CGiveItemStackPacket.class, CGiveItemStackPacket::encode, CGiveItemStackPacket::decode, CGiveItemStackPacket::handle);
		WyNetwork.registerPacket(CUpdateTraderOffersPacket.class, CUpdateTraderOffersPacket::encode, CUpdateTraderOffersPacket::decode, CUpdateTraderOffersPacket::handle);
		WyNetwork.registerPacket(CRequestSyncQuestDataPacket.class, CRequestSyncQuestDataPacket::encode, CRequestSyncQuestDataPacket::decode, CRequestSyncQuestDataPacket::handle);
		WyNetwork.registerPacket(CSyncZoanPacket.class, CSyncZoanPacket::encode, CSyncZoanPacket::decode, CSyncZoanPacket::handle);
		WyNetwork.registerPacket(CStopAbilityPacket.class, CStopAbilityPacket::encode, CStopAbilityPacket::decode, CStopAbilityPacket::handle);
		WyNetwork.registerPacket(CCreateCrewPacket.class, CCreateCrewPacket::encode, CCreateCrewPacket::decode, CCreateCrewPacket::handle);
		WyNetwork.registerPacket(CRequestSyncWorldDataPacket.class, CRequestSyncWorldDataPacket::encode, CRequestSyncWorldDataPacket::decode, CRequestSyncWorldDataPacket::handle);
		WyNetwork.registerPacket(CLeaveCrewPacket.class, CLeaveCrewPacket::encode, CLeaveCrewPacket::decode, CLeaveCrewPacket::handle);
		WyNetwork.registerPacket(CUpdateJollyRogerPacket.class, CUpdateJollyRogerPacket::encode, CUpdateJollyRogerPacket::decode, CUpdateJollyRogerPacket::handle);
		
		// From Server to Client
		WyNetwork.registerPacket(SSyncDevilFruitPacket.class, SSyncDevilFruitPacket::encode, SSyncDevilFruitPacket::decode, SSyncDevilFruitPacket::handle);
		WyNetwork.registerPacket(SSyncAbilityDataPacket.class, SSyncAbilityDataPacket::encode, SSyncAbilityDataPacket::decode, SSyncAbilityDataPacket::handle);
		WyNetwork.registerPacket(SSyncEntityStatsPacket.class, SSyncEntityStatsPacket::encode, SSyncEntityStatsPacket::decode, SSyncEntityStatsPacket::handle);
		WyNetwork.registerPacket(SFlySpeedPacket.class, SFlySpeedPacket::encode, SFlySpeedPacket::decode, SFlySpeedPacket::handle);
		WyNetwork.registerPacket(SRecalculateEyeHeightPacket.class, SRecalculateEyeHeightPacket::encode, SRecalculateEyeHeightPacket::decode, SRecalculateEyeHeightPacket::handle);
		WyNetwork.registerPacket(SViewProtectionPacket.class, SViewProtectionPacket::encode, SViewProtectionPacket::decode, SViewProtectionPacket::handle);
		WyNetwork.registerPacket(SOpenCharacterCreatorScreenPacket.class, SOpenCharacterCreatorScreenPacket::encode, SOpenCharacterCreatorScreenPacket::decode, SOpenCharacterCreatorScreenPacket::handle);
		WyNetwork.registerPacket(SOpenWantedPosterScreenPacket.class, SOpenWantedPosterScreenPacket::encode, SOpenWantedPosterScreenPacket::decode, SOpenWantedPosterScreenPacket::handle);
		WyNetwork.registerPacket(SDespawnQuestObjectivePacket.class, SDespawnQuestObjectivePacket::encode, SDespawnQuestObjectivePacket::decode, SDespawnQuestObjectivePacket::handle);
		WyNetwork.registerPacket(SUpdateTraderOffersPacket.class, SUpdateTraderOffersPacket::encode, SUpdateTraderOffersPacket::decode, SUpdateTraderOffersPacket::handle);
		WyNetwork.registerPacket(SOpenTraderScreenPacket.class, SOpenTraderScreenPacket::encode, SOpenTraderScreenPacket::decode, SOpenTraderScreenPacket::handle);
		WyNetwork.registerPacket(SOpenJollyRogerCreatorScreenPacket.class, SOpenJollyRogerCreatorScreenPacket::encode, SOpenJollyRogerCreatorScreenPacket::decode, SOpenJollyRogerCreatorScreenPacket::handle);
		WyNetwork.registerPacket(SOpenQuestChooseScreenPacket.class, SOpenQuestChooseScreenPacket::encode, SOpenQuestChooseScreenPacket::decode, SOpenQuestChooseScreenPacket::handle);
		WyNetwork.registerPacket(SOpenNewCrewScreenPacket.class, SOpenNewCrewScreenPacket::encode, SOpenNewCrewScreenPacket::decode, SOpenNewCrewScreenPacket::handle);
		WyNetwork.registerPacket(SSyncWorldDataPacket.class, SSyncWorldDataPacket::encode, SSyncWorldDataPacket::decode, SSyncWorldDataPacket::handle);

	}
}
