package xyz.pixelatedw.mineminenomi.abilities.ito;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.EntityRayTraceResult;
import xyz.pixelatedw.mineminenomi.particles.effects.ito.KumoNoSugakiParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;

public class KumoNoSugakiAbility extends ContinuousAbility
{
	public static final KumoNoSugakiAbility INSTANCE = new KumoNoSugakiAbility();
	
	private static final KumoNoSugakiParticleEffect PARTICLES = new KumoNoSugakiParticleEffect();

	public KumoNoSugakiAbility()
	{
		super("Kumo no Sugaki", AbilityCategory.DEVIL_FRUIT);
		this.setThreshold(15);
		this.setDescription("Creates a huge web that protects the user from any attack.");

		this.duringContinuityEvent = this::duringContinuity;
		this.onEndContinuityEvent = this::onEndContinuityEvent;
	}

	private void duringContinuity(PlayerEntity player, int passiveTimer)
	{
		EntityRayTraceResult trace = WyHelper.rayTraceEntities(player, 0.8);

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