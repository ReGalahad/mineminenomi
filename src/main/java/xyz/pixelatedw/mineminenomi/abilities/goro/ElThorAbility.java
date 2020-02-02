package xyz.pixelatedw.mineminenomi.abilities.goro;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.RayTraceResult;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.abilities.ChargeableAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.extra.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.data.ability.AbilityDataCapability.Category;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.packets.server.SSpawnLightningPacket;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.goro.ElThorParticleEffect;

public class ElThorAbility extends ChargeableAbility
{
	public static final Ability INSTANCE = new ElThorAbility();

	private static final ParticleEffect PARTICLES = new ElThorParticleEffect();

	public ElThorAbility()
	{
		super("El Thor", Category.DEVIL_FRUIT);
		this.setDescription("Focuses the user's electricity to strike the opponent with lightning from above");
		this.setMaxCooldown(8);
		this.setMaxChargeTime(6);

		this.duringChargingEvent = this::duringChargingEvent;
		this.onEndChargingEvent = this::onEndChargingEvent;
	}
	
	private void duringChargingEvent(PlayerEntity player, int chargeTimer)
	{
		RayTraceResult mop = WyHelper.rayTraceBlocks(player);
		
		if (mop != null)
		{
			double i = mop.getHitVec().x;
			double j = mop.getHitVec().y;
			double k = mop.getHitVec().z;
			
			if(chargeTimer % 5 == 0)
				PARTICLES.spawn(player.world, i, j, k, 0, 0, 0);
		}
	}
	
	private boolean onEndChargingEvent(PlayerEntity player)
	{
		RayTraceResult mop = WyHelper.rayTraceBlocks(player);
		
		if (mop != null)
		{
			double i = mop.getHitVec().x;
			double j = mop.getHitVec().y;
			double k = mop.getHitVec().z;
			ModNetwork.sendToAllAround(new SSpawnLightningPacket(1), player);

			ExplosionAbility explosion = WyHelper.newExplosion(player, i, j, k, 10);
			explosion.setExplosionSound(true);
			explosion.setDamageOwner(false);
			explosion.setDestroyBlocks(true);
			explosion.setFireAfterExplosion(true);
			explosion.setSmokeParticles(new CommonExplosionParticleEffect(10));
			explosion.setDamageEntities(true);
			explosion.doExplosion();
		}
		
		return true;
	}
}