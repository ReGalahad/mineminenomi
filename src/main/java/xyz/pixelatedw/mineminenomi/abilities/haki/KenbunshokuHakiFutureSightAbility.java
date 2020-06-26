package xyz.pixelatedw.mineminenomi.abilities.haki;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.abilities.IHakiAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper.HakiType;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.wypi.abilities.IParallelContinuousAbility;

public class KenbunshokuHakiFutureSightAbility extends ContinuousAbility implements IHakiAbility, IParallelContinuousAbility
{
	public static final KenbunshokuHakiFutureSightAbility INSTANCE = new KenbunshokuHakiFutureSightAbility();
	
	private static final int MAX_PROTECTION = 300;
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
		if (!HakiHelper.canUseHaki(player, this))
		{
			WyHelper.sendMsgToPlayer(player, new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_ONE_HAKI_TYPE, this.getName()).getFormattedText());
			return false;
		}
		
		IEntityStats sprops = EntityStatsCapability.get(player);
		IHakiData hakiProps = HakiDataCapability.get(player);
		
		float dorikiPower = sprops.getDoriki() / 1000;
		float hakiPower = hakiProps.getKenbunshokuHakiExp() / 2.5F;
		float finalPower = dorikiPower + hakiPower;
		
		this.protection = (int) (MAX_PROTECTION + (finalPower * 2));
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

	@Override
	public HakiType getType()
	{
		return HakiType.KENBUNSHOKU;
	}
}
