package xyz.pixelatedw.mineminenomi.abilities.yami;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.yami.KorouzuParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.ChargeableAbility;

public class KurouzuAbility extends ChargeableAbility
{
	public static final KurouzuAbility INSTANCE = new KurouzuAbility();

	private static final ParticleEffect PARTICLES = new KorouzuParticleEffect();
	private List<LivingEntity> entities = new ArrayList<LivingEntity>();
	
	public KurouzuAbility()
	{
		super("Kurouzu", AbilityCategory.DEVIL_FRUIT);
		this.setDescription("Creates a strong gravitational force, that pulls the opponent towards the user.");
		this.setMaxCooldown(10);
		this.setMaxChargeTime(3);

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
			
			this.entities.clear();
			List<LivingEntity> targets = WyHelper.getEntitiesNear(new BlockPos(i, j, k), player.world, 5, FactionHelper.getOutsideGroupPredicate(player), LivingEntity.class);
			targets.remove(player);
			
			for(LivingEntity target : targets)
			{
				this.entities.add(target);
			}
		}
	}
	
	private boolean onEndChargingEvent(PlayerEntity player)
	{
		for(LivingEntity target : this.entities)
		{
			target.setPositionAndUpdate(player.posX, player.posY, player.posZ);
			target.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 100, 5));
			target.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 100, 5));
			target.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 100, 5));
			target.addPotionEffect(new EffectInstance(ModEffects.ABILITY_OFF, 100, 0, false, false, false));
		}
		
		return true;
	}
}