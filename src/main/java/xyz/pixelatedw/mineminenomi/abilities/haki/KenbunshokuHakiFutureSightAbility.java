package xyz.pixelatedw.mineminenomi.abilities.haki;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;

public class KenbunshokuHakiFutureSightAbility extends ContinuousAbility
{
	public static final KenbunshokuHakiFutureSightAbility INSTANCE = new KenbunshokuHakiFutureSightAbility();
	
	private static final int MAX_PROTECTION = 1000;
	private int protection;
	
	public KenbunshokuHakiFutureSightAbility()
	{
		super("Kenbunshoku Haki: Future Sight", AbilityCategory.HAKI);
		
		this.onStartContinuityEvent = this::onStartContinuityEvent;
		this.duringContinuityEvent = this::duringContinuity;
		this.onEndContinuityEvent = this::onEndContinuityEvent;
	}
	
	private boolean onStartContinuityEvent(PlayerEntity player)
	{
		this.protection = MAX_PROTECTION;
		return true;
	}
	
	private void duringContinuity(PlayerEntity player, int passiveTimer)
	{
		HakiHelper.checkForHakiOveruse(player, passiveTimer);

		if(this.protection <= 0)
			this.stopContinuity(player);
	}
	
	private boolean onEndContinuityEvent(PlayerEntity player)
	{
		int cooldown = (int) Math.round(this.continueTime / 20.0);
		this.setMaxCooldown(cooldown);
		return true;
	}
	
	public void reduceProtection(float ammount)
	{
		this.protection -= ammount;
	}
}
