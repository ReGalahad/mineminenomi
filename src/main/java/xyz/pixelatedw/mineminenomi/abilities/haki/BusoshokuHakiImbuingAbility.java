package xyz.pixelatedw.mineminenomi.abilities.haki;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;

public class BusoshokuHakiImbuingAbility extends ContinuousAbility
{
	public static final BusoshokuHakiImbuingAbility INSTANCE = new BusoshokuHakiImbuingAbility();

	public BusoshokuHakiImbuingAbility()
	{
		super("Busoshoku Haki: Imbuing", AbilityCategory.HAKI);

		this.duringContinuityEvent = this::duringContinuity;
		this.onEndContinuityEvent = this::onEndContinuityEvent;
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