package xyz.pixelatedw.mineminenomi.abilities.supa;

import java.util.List;

import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.baku.BakuMunchParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;

public class SparklingDaisyAbility extends Ability
{
	public static final SparklingDaisyAbility INSTANCE = new SparklingDaisyAbility();
	
	private static final ParticleEffect PARTICLES = new BakuMunchParticleEffect();
	
	private int initialY;

	public SparklingDaisyAbility()
	{
		super("Sparkling Daisy", AbilityCategory.DEVIL_FRUIT);
		this.setDescription("Launches the user forward slicing anything in his path");
		this.setMaxCooldown(15);
		
		this.onUseEvent = this::onUseEvent;
		this.duringCooldownEvent = this::duringCooldownEvent;
	}
	
	private boolean onUseEvent(PlayerEntity player)
	{
		this.initialY = (int) player.posY;
		double[] motion = AbilityHelper.propulsion(player, 3, 3);
		player.setMotion(motion[0], player.getMotion().y, motion[1]);
		player.velocityChanged = true;
		
		return true;
	}
	
	private void duringCooldownEvent(PlayerEntity player, int cooldownTimer)
	{
		if ((cooldownTimer / 20) > (this.maxCooldown / 20) - 2 && player.posY >= this.initialY)
		{
			List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 1.6);
			list.remove(player);
			for (LivingEntity target : list)
				target.attackEntityFrom(DamageSource.causePlayerDamage(player), 20);
			for (BlockPos location : WyHelper.getNearbyBlocks(player, 3)) 
			{
				if (location.getY() >= player.posY) 
				{
					if (AbilityHelper.placeBlockIfAllowed(player.world, location.getX(), location.getY(), location.getZ(), Blocks.AIR, "core", "foliage", "ores")) 
					{
						PARTICLES.spawn(player.world, location.getX(), location.getY(), location.getZ(), 0, 0, 0);
					}
				}
			}
		}
	}
}
