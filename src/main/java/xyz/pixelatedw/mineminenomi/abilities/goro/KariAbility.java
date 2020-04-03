package xyz.pixelatedw.mineminenomi.abilities.goro;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.goro.KariParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.ChargeableAbility;

public class KariAbility extends ChargeableAbility
{
	public static final Ability INSTANCE = new KariAbility();

	private static final ParticleEffect PARTICLES = new KariParticleEffect();

	public KariAbility()
	{
		super("Kari", AbilityCategory.DEVIL_FRUIT);
		this.setDescription("Creates an electrical current around the user, which then explodes");
		this.setMaxCooldown(15);
		this.setMaxChargeTime(7);

		this.duringChargingEvent = this::duringChargingEvent;
		this.onEndChargingEvent = this::onEndChargingEvent;
	}
	
	private void duringChargingEvent(PlayerEntity player, int chargeTimer)
	{
		if(chargeTimer % 5 == 0)
			PARTICLES.spawn(player.world, player.posX, player.posY, player.posZ, 0, 0, 0);
	}
	
	private boolean onEndChargingEvent(PlayerEntity player)
	{	
		ExplosionAbility explosion = AbilityHelper.newExplosion(player, player.posX, player.posY, player.posZ, 10);
		explosion.setExplosionSound(false);
		explosion.setDamageOwner(false);
		explosion.setDestroyBlocks(false);
		explosion.setFireAfterExplosion(false);
		explosion.setSmokeParticles(null);
		explosion.setDamageEntities(true);
		explosion.doExplosion();
		
		return true;
	}
}