package xyz.pixelatedw.mineminenomi.abilities.haki;

import java.util.UUID;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
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
import xyz.pixelatedw.wypi.abilities.IParallelContinuousAbility;
import xyz.pixelatedw.wypi.abilities.PunchAbility;

public class BusoshokuHakiFullBodyHardeningAbility extends PunchAbility implements IHakiAbility, IParallelContinuousAbility
{
	public static final BusoshokuHakiFullBodyHardeningAbility INSTANCE = new BusoshokuHakiFullBodyHardeningAbility();

	private final UUID ArmorUUID = UUID.fromString("0457f786-0a5a-4e83-9ea6-f924c259a798");
	private final UUID ArmorThougnessUUID = UUID.fromString("0457f786-0a5a-4e83-9ea6-f924c259a798");

	public BusoshokuHakiFullBodyHardeningAbility()
	{
		super("Busoshoku Haki: Full Body Hardening", AbilityCategory.HAKI);
		
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
		IHakiData hakiProps = HakiDataCapability.get(player);
		double amount =  5 + hakiProps.getBusoshokuHardeningHakiExp() / 5f;
		System.out.println(amount);

		player.getAttribute(SharedMonsterAttributes.ARMOR).removeModifier(getEntryArmor(amount));
		player.getAttribute(SharedMonsterAttributes.ARMOR).applyModifier(getEntryArmor(amount));

		amount /= 4;
		player.getAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).removeModifier(getEntryArmorThougness(amount));
		player.getAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).applyModifier(getEntryArmorThougness(amount));

		return true;
	}
	
	// Overriding this method for haki punches since we want the ability to remain active after the punch
	@Override
	public float hitEntity(PlayerEntity player, LivingEntity target)
	{
		IEntityStats props = EntityStatsCapability.get(player);
		IHakiData hakiProps = HakiDataCapability.get(player);

		float dorikiPower = props.getDoriki() / 4000f;
		float hakiPower = hakiProps.getBusoshokuHardeningHakiExp() / 8f;
		float finalPower = dorikiPower + hakiPower;
		
		return finalPower;
	}

	private void duringContinuity(PlayerEntity player, int passiveTimer)
	{
		boolean isOnMaxOveruse = HakiHelper.checkForHakiOveruse(player, passiveTimer);
		if(isOnMaxOveruse)
			this.stopContinuity(player);
	}

	private boolean onEndContinuityEvent(PlayerEntity player)
	{
		player.getAttribute(SharedMonsterAttributes.ARMOR).removeModifier(getEntryArmor(0));
		player.getAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).removeModifier(getEntryArmorThougness(0));

		int cooldown = (int) Math.round(this.continueTime / 20.0);
		this.setMaxCooldown(cooldown);
		return true;
	}

	private AttributeModifier getEntryArmor(double amount) {
		return new AttributeModifier(ArmorUUID, "Full body Haki Resitance", amount, AttributeModifier.Operation.ADDITION).setSaved(false);
	}

	private AttributeModifier getEntryArmorThougness(double amount) {
		return new AttributeModifier(ArmorThougnessUUID, "Full body Haki Resitance", amount, AttributeModifier.Operation.ADDITION).setSaved(false);
	}

	@Override
	public HakiType getType()
	{
		return HakiType.HARDENING;
	}
}