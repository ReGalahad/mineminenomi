package xyz.pixelatedw.mineminenomi.abilities.gura;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.gura.GekishinParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.PunchAbility;

public class GekishinAbility extends PunchAbility
{
	public static final Ability INSTANCE = new GekishinAbility();

	private static final ParticleEffect PARTICLES = new GekishinParticleEffect();
	
	public GekishinAbility()
	{
		super("Gekishin", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(20);
		this.setDescription("The user cracks the air, which launches a small but powerful shockwave towards the opponent.");

		this.onHitEntityEvent = this::onHitEntity;
	}

	private float onHitEntity(PlayerEntity player, LivingEntity target)
	{
		ExplosionAbility explosion = AbilityHelper.newExplosion(target, target.posX, target.posY, target.posZ, 3);
		explosion.setExplosionSound(true);
		explosion.setDamageOwner(false);
		explosion.setDestroyBlocks(false);
		explosion.setFireAfterExplosion(false);
		explosion.setSmokeParticles(new CommonExplosionParticleEffect(3));
		explosion.setDamageEntities(true);
		explosion.doExplosion();
		
		PARTICLES.spawn(player.world, target.posX, target.posY, target.posZ, 0, 0, 0);
		
		return 100;
	}
}
