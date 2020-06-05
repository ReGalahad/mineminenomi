package xyz.pixelatedw.mineminenomi.abilities.toriphoenix;

import java.util.UUID;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.SPlayerAbilitiesPacket;
import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
import xyz.pixelatedw.mineminenomi.entities.zoan.PhoenixAssaultZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;

public class PhoenixAssaultPointAbility extends ZoanAbility
{
	public static final PhoenixAssaultPointAbility INSTANCE = new PhoenixAssaultPointAbility();
	private static final AttributeModifier REGEN_RATE_MODIFIER = new AttributeModifier(UUID.fromString("eb43ce22-3c5a-45a0-810c-03c0f552efe7"), "Health Regeneration Speed Multiplier", 1f, AttributeModifier.Operation.ADDITION).setSaved(false);

	public PhoenixAssaultPointAbility()
	{
		super("Phoenix Assault Point", AbilityCategory.DEVIL_FRUIT, PhoenixAssaultZoanInfo.FORM);
		this.setDescription("Allows the user to transforms into a half-phoenix hybrid, which focuses on speed and healing, Allows the user to use 'Phoenix Goen' and 'Flame of Restoration'");
	
		this.onStartContinuityEvent = this::onStartContinuityEvent;
		this.duringContinuityEvent = this::duringContinuityEvent;
		this.onEndContinuityEvent = this::onEndContinuityEvent;
		
		this.addZoanModifier(ModAttributes.REGEN_RATE, REGEN_RATE_MODIFIER);
	}

	@Override
	protected boolean onStartContinuityEvent(PlayerEntity player)
	{
		if(player.isCreative() || player.isSpectator())
			return super.onStartContinuityEvent(player);

		player.abilities.allowFlying = true;	
		((ServerPlayerEntity)player).connection.sendPacket(new SPlayerAbilitiesPacket(player.abilities));

		return super.onStartContinuityEvent(player);
	}
	
	private void duringContinuityEvent(PlayerEntity player, int activeTime)
	{
		player.fallDistance = 0;
	}
	
	@Override
	protected boolean onEndContinuityEvent(PlayerEntity player)
	{
		if(player.isCreative() || player.isSpectator())
			return super.onEndContinuityEvent(player);

		player.abilities.allowFlying = false;	
		player.abilities.isFlying = false;
		((ServerPlayerEntity)player).connection.sendPacket(new SPlayerAbilitiesPacket(player.abilities));

		return super.onEndContinuityEvent(player);
	}
}
