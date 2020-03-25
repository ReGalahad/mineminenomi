package xyz.pixelatedw.mineminenomi.abilities.ope;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitsHelper;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.ope.CounterShockParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.PunchAbility;

public class CounterShockAbility extends PunchAbility
{
	public static final Ability INSTANCE = new CounterShockAbility();
	
	private static final ParticleEffect PARTICLES = new CounterShockParticleEffect();
	
	public CounterShockAbility()
	{
		super("Counter Shock", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(10);
		this.setDescription("Releases a strong electrical current which shocks the opponent");
		
		this.onHitEntity = this::onHitEntity;
	}
	
	private float onHitEntity(PlayerEntity player, LivingEntity target)
	{		
		ExplosionAbility explosion = DevilFruitsHelper.newExplosion(player, target.posX, target.posY, target.posZ, 1);
		explosion.setExplosionSound(true);
		explosion.setDamageOwner(false);
		explosion.setDestroyBlocks(false);
		explosion.setFireAfterExplosion(false);
		explosion.setSmokeParticles(new CommonExplosionParticleEffect(2));
		explosion.setDamageEntities(true);
		explosion.doExplosion();
		
		PARTICLES.spawn(player.world, target.posX, target.posY, target.posZ, 0, 0, 0);
		
		return 40;
	}
}
