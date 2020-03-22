package xyz.pixelatedw.mineminenomi.abilities.bomu;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitsHelper;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class KickBombAbility extends Ability
{
	public static final Ability INSTANCE = new KickBombAbility();

	public KickBombAbility()
	{
		super("Kick Bomb", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(7);
		this.setDescription("The user kicks the ground, detonating their leg on impact.");

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		ExplosionAbility explosion = DevilFruitsHelper.newExplosion(player, player.posX, player.posY, player.posZ, 7);
		explosion.setExplosionSound(true);
		explosion.setDamageOwner(false);
		explosion.setDestroyBlocks(true);
		explosion.setFireAfterExplosion(false);
		explosion.setSmokeParticles(new CommonExplosionParticleEffect(8));
		explosion.setDamageEntities(true);
		explosion.doExplosion();
		
		return true;
	}
}