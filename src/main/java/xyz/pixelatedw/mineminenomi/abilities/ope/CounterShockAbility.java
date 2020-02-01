package xyz.pixelatedw.mineminenomi.abilities.ope;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.abilities.PunchAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.extra.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.data.ability.AbilityDataCapability.Category;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;

public class CounterShockAbility extends PunchAbility
{
	public static final Ability INSTANCE = new CounterShockAbility();
	
	public CounterShockAbility()
	{
		super("Counter Shock", Category.DEVIL_FRUIT);
		this.setMaxCooldown(10);
		this.setDescription("Releases a strong electrical current which shocks the opponent");
		
		this.onHitEntity = this::onHitEntity;
	}
	
	private float onHitEntity(PlayerEntity player, LivingEntity target)
	{		
		ExplosionAbility explosion = WyHelper.newExplosion(player, target.posX, target.posY, target.posZ, 1);
		explosion.setExplosionSound(true);
		explosion.setDamageOwner(false);
		explosion.setDestroyBlocks(false);
		explosion.setFireAfterExplosion(false);
		explosion.setSmokeParticles(new CommonExplosionParticleEffect(2));
		explosion.setDamageEntities(true);
		explosion.doExplosion();
		
		return 40;
	}
}
