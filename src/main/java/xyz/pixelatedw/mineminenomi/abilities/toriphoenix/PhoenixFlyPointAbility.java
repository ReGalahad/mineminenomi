package xyz.pixelatedw.mineminenomi.abilities.toriphoenix;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.SPlayerAbilitiesPacket;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
import xyz.pixelatedw.mineminenomi.entities.zoan.PhoenixFlyZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;

public class PhoenixFlyPointAbility extends ZoanAbility
{
	public static final PhoenixFlyPointAbility INSTANCE = new PhoenixFlyPointAbility();

	public PhoenixFlyPointAbility()
	{
		super("Phoenix Fly Point", AbilityCategory.DEVIL_FRUIT, PhoenixFlyZoanInfo.FORM);
		this.setDescription("Allows the user to transforms into a phoenix, which focuses on speed and healing, Allows the user to use 'Phoenix Goen', 'Tensei no Soen' and 'Blue Flames of Resurrection'");
	
		this.onStartContinuityEvent = this::onStartContinuityEvent;
		this.duringContinuityEvent = this::duringContinuityEvent;
		this.onEndContinuityEvent = this::onEndContinuityEvent;
	}
	
	@Override
	protected boolean onStartContinuityEvent(PlayerEntity player)
	{
		if(player.onGround)
		{
			WyHelper.sendMsgToPlayer(player, new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_ONLY_IN_AIR, this.getName()).getFormattedText());
			return false;
		}
		
		if(player.isCreative() || player.isSpectator())
			return super.onStartContinuityEvent(player);
			
		player.abilities.allowFlying = true;
		((ServerPlayerEntity)player).connection.sendPacket(new SPlayerAbilitiesPacket(player.abilities));
		//WyNetwork.sendTo(new SFlySpeedPacket(0.08F), player);

		return super.onStartContinuityEvent(player);
	}
	
	private void duringContinuityEvent(PlayerEntity player, int activeTime)
	{
		player.fallDistance = 0;
		//System.out.println(player.abilities.getFlySpeed());
	}
	
	@Override
	protected boolean onEndContinuityEvent(PlayerEntity player)
	{
		if(player.isCreative() || player.isSpectator())
			return super.onEndContinuityEvent(player);
		
		player.abilities.allowFlying = false;	
		player.abilities.isFlying = false;
		((ServerPlayerEntity)player).connection.sendPacket(new SPlayerAbilitiesPacket(player.abilities));
//		WyNetwork.sendTo(new SFlySpeedPacket(0.05F), player);

		return super.onEndContinuityEvent(player);
	}
}
