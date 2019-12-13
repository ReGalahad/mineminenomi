package xyz.pixelatedw.mineminenomi.init;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import xyz.pixelatedw.mineminenomi.api.network.WyNetworkHelper;
import xyz.pixelatedw.mineminenomi.api.network.packets.client.CAbilityDataSyncPacket;
import xyz.pixelatedw.mineminenomi.api.network.packets.client.CQuestDataSyncPacket;
import xyz.pixelatedw.mineminenomi.api.network.packets.server.SAbilityDataSyncPacket;
import xyz.pixelatedw.mineminenomi.api.network.packets.server.SQuestDataSyncPacket;
import xyz.pixelatedw.mineminenomi.packets.client.CCombatModeTriggerPacket;
import xyz.pixelatedw.mineminenomi.packets.client.CDeleteCCBookPacket;
import xyz.pixelatedw.mineminenomi.packets.client.CDevilFruitSyncPacket;
import xyz.pixelatedw.mineminenomi.packets.client.CEntityStatsSyncPacket;
import xyz.pixelatedw.mineminenomi.packets.client.CRequestSyncPacket;
import xyz.pixelatedw.mineminenomi.packets.client.CUseAbilityPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SDevilFruitSyncPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SEntityStatsSyncPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SExtraEffectSyncPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SParticlesPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SRecalculateEyeHeightPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SSpawnLightningPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SSpecialFlyingPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SUpdateMotionPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SViewProtectionPacket;
import xyz.pixelatedw.mineminenomi.values.ModValuesEnv;

public class ModNetwork
{
	private static final String PROTOCOL_VERSION = Integer.toString(1);
	public static SimpleChannel channel = NetworkRegistry.ChannelBuilder.named(new ResourceLocation(ModValuesEnv.PROJECT_ID, "main_channel")).clientAcceptedVersions(PROTOCOL_VERSION::equals).serverAcceptedVersions(PROTOCOL_VERSION::equals).networkProtocolVersion(() -> PROTOCOL_VERSION).simpleChannel();

	public static void init() 
	{
		int packet = 0;

		// Client
		channel.registerMessage(packet++, CRequestSyncPacket.class, CRequestSyncPacket::encode, CRequestSyncPacket::decode, CRequestSyncPacket::handle);
		channel.registerMessage(packet++, CCombatModeTriggerPacket.class, CCombatModeTriggerPacket::encode, CCombatModeTriggerPacket::decode, CCombatModeTriggerPacket::handle);
		channel.registerMessage(packet++, CUseAbilityPacket.class, CUseAbilityPacket::encode, CUseAbilityPacket::decode, CUseAbilityPacket::handle);
		channel.registerMessage(packet++, CDeleteCCBookPacket.class, CDeleteCCBookPacket::encode, CDeleteCCBookPacket::decode, CDeleteCCBookPacket::handle);
		channel.registerMessage(packet++, CEntityStatsSyncPacket.class, CEntityStatsSyncPacket::encode, CEntityStatsSyncPacket::decode, CEntityStatsSyncPacket::handle);
		channel.registerMessage(packet++, CDevilFruitSyncPacket.class, CDevilFruitSyncPacket::encode, CDevilFruitSyncPacket::decode, CDevilFruitSyncPacket::handle);
		channel.registerMessage(packet++, CAbilityDataSyncPacket.class, CAbilityDataSyncPacket::encode, CAbilityDataSyncPacket::decode, CAbilityDataSyncPacket::handle);
		channel.registerMessage(packet++, CQuestDataSyncPacket.class, CQuestDataSyncPacket::encode, CQuestDataSyncPacket::decode, CQuestDataSyncPacket::handle);

		// Server
		channel.registerMessage(packet++, SDevilFruitSyncPacket.class, SDevilFruitSyncPacket::encode, SDevilFruitSyncPacket::decode, SDevilFruitSyncPacket::handle);
		channel.registerMessage(packet++, SAbilityDataSyncPacket.class, SAbilityDataSyncPacket::encode, SAbilityDataSyncPacket::decode, SAbilityDataSyncPacket::handle);
		channel.registerMessage(packet++, SEntityStatsSyncPacket.class, SEntityStatsSyncPacket::encode, SEntityStatsSyncPacket::decode, SEntityStatsSyncPacket::handle);
		channel.registerMessage(packet++, SExtraEffectSyncPacket.class, SExtraEffectSyncPacket::encode, SExtraEffectSyncPacket::decode, SExtraEffectSyncPacket::handle);
		channel.registerMessage(packet++, SSpawnLightningPacket.class, SSpawnLightningPacket::encode, SSpawnLightningPacket::decode, SSpawnLightningPacket::handle);
		channel.registerMessage(packet++, SSpecialFlyingPacket.class, SSpecialFlyingPacket::encode, SSpecialFlyingPacket::decode, SSpecialFlyingPacket::handle);
		channel.registerMessage(packet++, SRecalculateEyeHeightPacket.class, SRecalculateEyeHeightPacket::encode, SRecalculateEyeHeightPacket::decode, SRecalculateEyeHeightPacket::handle);
		channel.registerMessage(packet++, SParticlesPacket.class, SParticlesPacket::encode, SParticlesPacket::decode, SParticlesPacket::handle);
		channel.registerMessage(packet++, SUpdateMotionPacket.class, SUpdateMotionPacket::encode, SUpdateMotionPacket::decode, SUpdateMotionPacket::handle);
		channel.registerMessage(packet++, SQuestDataSyncPacket.class, SQuestDataSyncPacket::encode, SQuestDataSyncPacket::decode, SQuestDataSyncPacket::handle);
		channel.registerMessage(packet++, SViewProtectionPacket.class, SViewProtectionPacket::encode, SViewProtectionPacket::decode, SViewProtectionPacket::handle);

	}
	
	public static <MSG> void sendToServer(MSG msg)
	{
		WyNetworkHelper.sendToServer(channel, msg);
	}
	
	public static <MSG> void sendTo(MSG msg, ServerPlayerEntity player)
	{
		WyNetworkHelper.sendTo(channel, msg, player);
	}
	
	public static <MSG> void sendToAll(MSG msg)
	{
		WyNetworkHelper.sendToAll(channel, msg);
	}
	
	public static <MSG> void sendToAllAround(MSG msg, LivingEntity sender)
	{
		WyNetworkHelper.sendToAllAround(channel, msg, sender);
	}
}
