package xyz.pixelatedw.mineminenomi.abilities.haki;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.abilities.OverlayPunchAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.IParallelContinuousAbility;

public class BusoshokuHakiHardeningAbility extends OverlayPunchAbility implements IParallelContinuousAbility
{
	public static final BusoshokuHakiHardeningAbility INSTANCE = new BusoshokuHakiHardeningAbility();

	public BusoshokuHakiHardeningAbility()
	{
		super("Busoshoku Haki: Hardening", AbilityCategory.HAKI);

		this.onStartContinuityEvent = this::onStartContinuityEvent;
		this.duringContinuityEvent = this::duringContinuity;
		this.onEndContinuityEvent = this::onEndContinuityEvent;
	}

	private boolean onStartContinuityEvent(PlayerEntity player)
	{
		if (!HakiHelper.canUseHaki(player, this))
		{
			WyHelper.sendMsgToPlayer(player, new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_ONE_HAKI_TYPE, this.getName()).getFormattedText());
			return false;
		}
		
		return true;
	}
	
	// Overriding this method for haki punches since we want the ability to remain active after the punch
	@Override
	public float hitEntity(PlayerEntity player, LivingEntity target)
	{
		IEntityStats props = EntityStatsCapability.get(player);
		
		float dorikiPower = props.getDoriki() / 1000;
		float hakiPower = HakiHelper.getBusoHakiExp() / 5;
		float finalPower = dorikiPower + hakiPower;
		
		return finalPower;
	}
	
	private void duringContinuity(PlayerEntity player, int passiveTimer)
	{
		HakiHelper.checkForHakiOveruse(player, passiveTimer);
	}

	private boolean onEndContinuityEvent(PlayerEntity player)
	{
		int cooldown = (int) Math.round(this.continueTime / 25.0);
		this.setMaxCooldown(cooldown);
		return true;
	}
}
