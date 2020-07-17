package xyz.pixelatedw.mineminenomi.abilities.magu;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.CoreBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.OreBlockProtectionRule;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.ChargeableAbility;

public class BakuretsuKazanAbility extends ChargeableAbility
{
	public static final Ability INSTANCE = new BakuretsuKazanAbility();

	private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(CoreBlockProtectionRule.INSTANCE, OreBlockProtectionRule.INSTANCE, FoliageBlockProtectionRule.INSTANCE);

	public BakuretsuKazanAbility()
	{
		super("Bakuretsu Kazan", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(15);
		this.setMaxChargeTime(5);
		this.setDescription("By spreading magma to the surroundings, the user turns everything into lava.");

		this.onEndChargingEvent = this::endChargingEvent;
	}

	private boolean endChargingEvent(PlayerEntity player)
	{
		AbilityHelper.createFilledSphere(player.world, (int) player.posX, (int) player.posY, (int) player.posZ, 10, Blocks.LAVA, GRIEF_RULE);
		
		return true;
	}
}