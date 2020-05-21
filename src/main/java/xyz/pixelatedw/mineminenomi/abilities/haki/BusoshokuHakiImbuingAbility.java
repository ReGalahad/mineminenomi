package xyz.pixelatedw.mineminenomi.abilities.haki;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.wypi.abilities.IParallelContinuousAbility;

public class BusoshokuHakiImbuingAbility extends ContinuousAbility implements IParallelContinuousAbility
{
	public static final BusoshokuHakiImbuingAbility INSTANCE = new BusoshokuHakiImbuingAbility();

	public BusoshokuHakiImbuingAbility()
	{
		super("Busoshoku Haki: Imbuing", AbilityCategory.HAKI);

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