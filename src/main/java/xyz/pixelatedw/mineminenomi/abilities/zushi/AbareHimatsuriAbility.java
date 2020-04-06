package xyz.pixelatedw.mineminenomi.abilities.zushi;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.packets.server.SSpecialFlyingPacket;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.wypi.network.WyNetwork;

public class AbareHimatsuriAbility extends ContinuousAbility
{
	public static final AbareHimatsuriAbility INSTANCE = new AbareHimatsuriAbility();

	public AbareHimatsuriAbility()
	{
		super("Abare Himatsuri", AbilityCategory.DEVIL_FRUIT);
		this.setDescription("The user stands on a piece of rock and levitates it into the air and flies across the battlefield");
		this.setMaxCooldown(10);
		this.setThreshold(60);
		
		this.onStartContinuityEvent = this::onStartContinuityEvent;
		this.duringContinuityEvent = this::duringContinuityEvent;
		this.onEndContinuityEvent = this::onEndContinuityEvent;
	}
	
	private boolean onStartContinuityEvent(PlayerEntity player)
	{
		if(player.isCreative() || player.isSpectator())
			return true;

		WyNetwork.sendTo(new SSpecialFlyingPacket(true), player);
		
		return true;
	}
	
	private void duringContinuityEvent(PlayerEntity player, int activeTime)
	{
		player.fallDistance = 0;
	}
	
	private boolean onEndContinuityEvent(PlayerEntity player)
	{
		if(player.isCreative() || player.isSpectator())
			return true;
		
		WyNetwork.sendTo(new SSpecialFlyingPacket(false), player);
		
		return true;
	}
}
