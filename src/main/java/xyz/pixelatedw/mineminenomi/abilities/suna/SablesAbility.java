package xyz.pixelatedw.mineminenomi.abilities.suna;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.suna.SablesParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.PunchAbility;

public class SablesAbility extends PunchAbility
{
	public static final Ability INSTANCE = new SablesAbility();

	private static final ParticleEffect PARTICLES = new SablesParticleEffect();
	
	public SablesAbility()
	{
		super("Sables", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(3);
		this.setDescription("The user launches a compressed sandstorm at the opponent, which sends them flying.");

		this.onHitEntityEvent = this::onHitEntity;
	}

	private float onHitEntity(PlayerEntity player, LivingEntity target)
	{
		PARTICLES.spawn(player.world, target.posX, target.posY, target.posZ, 0, 0, 0);
		
		target.setPosition(target.posX, target.posY + 10, target.posZ);
		target.setMotion(0, 2, 0);
		target.velocityChanged = true;

		return 0;
	}
}
