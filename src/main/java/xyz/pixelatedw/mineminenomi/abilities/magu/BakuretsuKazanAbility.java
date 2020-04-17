package xyz.pixelatedw.mineminenomi.abilities.magu;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.CoreBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.OreBlockProtectionRule;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class BakuretsuKazanAbility extends Ability
{
	public static final Ability INSTANCE = new BakuretsuKazanAbility();

	private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(CoreBlockProtectionRule.INSTANCE, OreBlockProtectionRule.INSTANCE); 

	public BakuretsuKazanAbility()
	{
		super("Bakuretsu Kazan", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(15);
		this.setDescription("By spreading magma to the surroundings, the user turns everything into lava.");

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		AbilityHelper.createFilledSphere(player.world, (int) player.posX, (int) player.posY, (int) player.posZ, 10, Blocks.LAVA, GRIEF_RULE);
		
		return true;
	}
}