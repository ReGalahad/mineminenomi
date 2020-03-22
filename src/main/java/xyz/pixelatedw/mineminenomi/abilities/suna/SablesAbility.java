package xyz.pixelatedw.mineminenomi.abilities.suna;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.suna.SablesParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
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

		this.onHitEntity = this::onHitEntity;
	}

	private float onHitEntity(PlayerEntity player, LivingEntity target)
	{
		double xPower = WyHelper.randomWithRange(-5, 5);
		if(xPower > 0) xPower += 5;
		else xPower -= 5;
		
		double zPower = WyHelper.randomWithRange(-5, 5);
		if(zPower > 0) zPower += 5;
		else zPower -= 5;
		
		PARTICLES.spawn(player.world, target.posX, target.posY, target.posZ, 0, 0, 0);
		
		target.setMotion(xPower, 2, zPower);
		target.velocityChanged = true;

		return 0;
	}
}
