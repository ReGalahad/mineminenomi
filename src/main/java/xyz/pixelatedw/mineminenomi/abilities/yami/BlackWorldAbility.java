package xyz.pixelatedw.mineminenomi.abilities.yami;

import java.util.List;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.yami.BlackWorldParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
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
			int a1 = (int) WyHelper.randomWithRange(-10, 10);
			int a2 = (int) WyHelper.randomWithRange(-10, 10);

			for(int j = -5; j < 5; j++)
			{
				AbilityHelper.placeBlockIfAllowed(player.world, (int) player.posX + a1, (int) player.posY + j, (int) player.posZ + a2, ModBlocks.DARKNESS, GRIEF_RULE);
				AbilityHelper.placeBlockIfAllowed(player.world, (int) player.posX + a1 + 1, (int) player.posY + j, (int) player.posZ + a2, ModBlocks.DARKNESS, GRIEF_RULE);
				AbilityHelper.placeBlockIfAllowed(player.world, (int) player.posX + a1, (int) player.posY + j, (int) player.posZ + a2 + 1, ModBlocks.DARKNESS, GRIEF_RULE);
				AbilityHelper.placeBlockIfAllowed(player.world, (int) player.posX + a1 + 1, (int) player.posY + j, (int) player.posZ + a2 + 1, ModBlocks.DARKNESS, GRIEF_RULE);
			}
		}
		
		List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 20, FactionHelper.getOutsideGroupPredicate(player), LivingEntity.class);
		targets.remove(player);
		
		for(LivingEntity target : targets)
		{
			target.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 200, 1, false, false));
			target.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 200, 1, false, false));
		}
		
		return true;
	}
}
