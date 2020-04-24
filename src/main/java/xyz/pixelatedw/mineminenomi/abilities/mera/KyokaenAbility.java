package xyz.pixelatedw.mineminenomi.abilities.mera;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.EntityRayTraceResult;
import xyz.pixelatedw.mineminenomi.particles.effects.mera.KyokaenParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;

public class KyokaenAbility extends ContinuousAbility
{
	public static final KyokaenAbility INSTANCE = new KyokaenAbility();
	
	private static final KyokaenParticleEffect PARTICLES = new KyokaenParticleEffect();

	public KyokaenAbility()
	{
		super("Kyokaen", AbilityCategory.DEVIL_FRUIT);
		this.setThreshold(12);
		this.setDescription("Creates a wall of fire protecting its user.");

		this.duringContinuityEvent = this::duringContinuity;
		this.onEndContinuityEvent = this::onEndContinuityEvent;
	}

	private void duringContinuity(PlayerEntity player, int passiveTimer)
	{
		EntityRayTraceResult trace = WyHelper.rayTraceEntities(player, 1.2);

		if (passiveTimer % 2 == 0)
			PARTICLES.spawn(player.world, trace.getHitVec().getX(), player.posY, trace.getHitVec().getZ(), 0, 0, 0);
	}

	private boolean onEndContinuityEvent(PlayerEntity player)
	{
		int cooldown = (int) Math.round(this.continueTime / 14.0);
		this.setMaxCooldown(cooldown);
		return true;
	}
}