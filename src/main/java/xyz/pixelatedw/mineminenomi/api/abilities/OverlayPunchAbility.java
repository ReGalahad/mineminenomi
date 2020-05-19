package xyz.pixelatedw.mineminenomi.api.abilities;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncDevilFruitPacket;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.PunchAbility;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.network.WyNetwork;
import xyz.pixelatedw.wypi.network.packets.server.SSyncAbilityDataPacket;

public class OverlayPunchAbility extends PunchAbility
{

	public OverlayPunchAbility(String name, AbilityCategory category)
	{
		super(name, category);
	}

	@Override
	public void startContinuity(PlayerEntity player)
	{
		super.startContinuity(player);
		WyNetwork.sendToAllAround(new SSyncDevilFruitPacket(player.getEntityId(), DevilFruitCapability.get(player)), player);
		WyNetwork.sendToAllAround(new SSyncAbilityDataPacket(player.getEntityId(), AbilityDataCapability.get(player)), player);
	}
	
	@Override
	public void startCooldown(PlayerEntity player)
	{
		super.startCooldown(player);
		WyNetwork.sendToAllAround(new SSyncDevilFruitPacket(player.getEntityId(), DevilFruitCapability.get(player)), player);
		WyNetwork.sendToAllAround(new SSyncAbilityDataPacket(player.getEntityId(), AbilityDataCapability.get(player)), player);
	}
	
	@Override
	public void stopContinuity(PlayerEntity player)
	{
		super.stopContinuity(player);
		WyNetwork.sendToAllAround(new SSyncDevilFruitPacket(player.getEntityId(), DevilFruitCapability.get(player)), player);
		WyNetwork.sendToAllAround(new SSyncAbilityDataPacket(player.getEntityId(), AbilityDataCapability.get(player)), player);
	}
}
