package xyz.pixelatedw.mineminenomi.abilities.goro;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.abilities.ChargeableAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.extra.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.data.ability.AbilityDataCapability.Category;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.goro.KariParticleEffect;

public class KariAbility extends ChargeableAbility
{
	public static final Ability INSTANCE = new KariAbility();

	private static final ParticleEffect PARTICLES = new KariParticleEffect();

	public KariAbility()
	{
		super("Kari", Category.DEVIL_FRUIT);
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
		ExplosionAbility explosion = WyHelper.newExplosion(player, player.posX, player.posY, player.posZ, 10);
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