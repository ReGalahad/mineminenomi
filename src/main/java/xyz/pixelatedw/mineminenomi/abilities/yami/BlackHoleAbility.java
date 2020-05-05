package xyz.pixelatedw.mineminenomi.abilities.yami;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.CoreBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.OreBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.yami.BlackHoleParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class BlackHoleAbility extends Ability
{
	public static final BlackHoleAbility INSTANCE = new BlackHoleAbility();
	
	private static final ParticleEffect PARTICLES = new BlackHoleParticleEffect();
	private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(CoreBlockProtectionRule.INSTANCE, FoliageBlockProtectionRule.INSTANCE, OreBlockProtectionRule.INSTANCE); 

	public BlackHoleAbility()
	{
		super("Black Hole", AbilityCategory.DEVIL_FRUIT);
		this.setDescription("The user spreads darkness over the target area, which engulfs anyone and anything inside of it.");
		this.setMaxCooldown(7);

		this.onUseEvent = this::onUseEvent;
	}
	
	private boolean onUseEvent(PlayerEntity player)
	{
		AbilityHelper.createFilledSphere(player.world, (int)player.posX, (int)player.posY, (int)player.posZ, 10, ModBlocks.DARKNESS, GRIEF_RULE);
		
		PARTICLES.spawn(player.world, player.posX, player.posY, player.posZ, 0, 0, 0);
		
		return true;
	}
}
