package xyz.pixelatedw.mineminenomi.abilities.suna;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.CoreBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.OreBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.suna.DesertGirasole2ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.suna.DesertGirasoleParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.ChargeableAbility;

public class DesertGirasoleAbility extends ChargeableAbility
{
	public static final Ability INSTANCE = new DesertGirasoleAbility();

	private static final ParticleEffect PARTICLES1 = new DesertGirasoleParticleEffect();
	private static final ParticleEffect PARTICLES2 = new DesertGirasole2ParticleEffect();
	private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(CoreBlockProtectionRule.INSTANCE, OreBlockProtectionRule.INSTANCE, FoliageBlockProtectionRule.INSTANCE); 

	public DesertGirasoleAbility()
	{
		super("Desert Girasole", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(25);
		this.setMaxChargeTime(5);
		this.setDescription("Quickly dries out the surrounding land turning it into quicksand.");

		this.onStartChargingEvent = this::onStartChargingEvent;
		this.duringChargingEvent = this::duringChargingEvent;
		this.onEndChargingEvent = this::onEndChargingEvent;
	}

	private boolean onStartChargingEvent(PlayerEntity player)
	{
		PARTICLES1.spawn(player.world, player.posX, player.posY, player.posZ, 0, 0, 0);
				
		return true;
	}
	
	private void duringChargingEvent(PlayerEntity player, int chargeTime)
	{
		player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 10, 2000));
		player.setMotion(0, 0, 0);
		player.velocityChanged = true;
	}
	
	private boolean onEndChargingEvent(PlayerEntity player)
	{
		PARTICLES2.spawn(player.world, player.posX, player.posY, player.posZ, 0, 0, 0);

		for (int i = -15; i < 15; i++)
		{
			for (int j = -10; j < 10; j++)
			{
				for (int k = -15; k < 15; k++)
				{
					double posX = player.posX + i + (i < -WyHelper.randomWithRange(8, 12) || i > WyHelper.randomWithRange(8, 12) ? WyHelper.randomWithRange(-5, 5) : 0);
					double posY = player.posY + j;
					double posZ = player.posZ + k + (k < -WyHelper.randomWithRange(8, 12) || k > WyHelper.randomWithRange(8, 12) ? WyHelper.randomWithRange(-5, 5) : 0);

					AbilityHelper.placeBlockIfAllowed(player.world, posX, posY - 1, posZ, ModBlocks.SUNA_SAND, GRIEF_RULE);
				}
			}
		}
		
		return true;
	}
}
