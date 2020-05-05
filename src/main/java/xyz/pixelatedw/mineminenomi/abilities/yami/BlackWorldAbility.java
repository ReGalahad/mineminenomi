package xyz.pixelatedw.mineminenomi.abilities.yami;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.yami.BlackWorldParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class BlackWorldAbility extends Ability
{
	public static final BlackWorldAbility INSTANCE = new BlackWorldAbility();

	private static final ParticleEffect PARTICLES = new BlackWorldParticleEffect();
	private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(AirBlockProtectionRule.INSTANCE); 

	public BlackWorldAbility()
	{
		super("Black World", AbilityCategory.DEVIL_FRUIT);
		this.setDescription("The user spreads darkness to the surroundings, blinding enemies and creating pillars made of Darkness.");
		this.setMaxCooldown(10);

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		PARTICLES.spawn(player.world, player.posX, player.posY, player.posZ, 0, 0, 0);
		
		for (int i = 0; i < 8; i++)
		{
			int a1 = player.getRNG().nextInt(20) - 10;
			int a2 = player.getRNG().nextInt(20) - 10;

			for(int j = -5; j < 5; j++)
			{
				AbilityHelper.placeBlockIfAllowed(player.world, (int) player.posX + a1, (int) player.posY + j, (int) player.posZ + a2, ModBlocks.DARKNESS, GRIEF_RULE);
				AbilityHelper.placeBlockIfAllowed(player.world, (int) player.posX + a1 + 1, (int) player.posY + j, (int) player.posZ + a2, ModBlocks.DARKNESS, GRIEF_RULE);
				AbilityHelper.placeBlockIfAllowed(player.world, (int) player.posX + a1, (int) player.posY + j, (int) player.posZ + a2 + 1, ModBlocks.DARKNESS, GRIEF_RULE);
				AbilityHelper.placeBlockIfAllowed(player.world, (int) player.posX + a1 + 1, (int) player.posY + j, (int) player.posZ + a2 + 1, ModBlocks.DARKNESS, GRIEF_RULE);
			}
		}

		return true;
	}
}
