package xyz.pixelatedw.mineminenomi.abilities.gura;

import java.util.List;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.api.helpers.CrewHelper;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.gura.TenchiMeidoParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.ChargeableAbility;

public class TenchiMeidoAbility extends ChargeableAbility
{
	public static final Ability INSTANCE = new TenchiMeidoAbility();

	private static final ParticleEffect PARTICLES = new TenchiMeidoParticleEffect();
	
	public TenchiMeidoAbility()
	{
		super("Tenchi Meido", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(15);
		this.setMaxChargeTime(5);
		this.setDescription("The user grabs the air and pulls it downwards after which all of the opponents are tossed into the air and fall back down.");

		this.duringChargingEvent = this::duringChargingEvent;
		this.onEndChargingEvent = this::onEndChargingEvent;
	}
	
	private void duringChargingEvent(PlayerEntity player, int chargeTime)
	{
		if(chargeTime % 2 == 0)
			PARTICLES.spawn(player.world, player.posX, player.posY, player.posZ, 0, 0, 0);
		player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 10, 2000));
		player.setMotion(0, -0.2, 0);
		player.velocityChanged = true;
	}
	
	private boolean onEndChargingEvent(PlayerEntity player)
	{
		List<LivingEntity> targets = WyHelper.<LivingEntity>getEntitiesNear(player.getPosition(), player.world, 20, CrewHelper.NOT_IN_CREW_PREDICATE, LivingEntity.class);
		targets.removeIf(entity -> !entity.onGround);
		targets.remove(player);
		
		targets.stream().filter(target -> target != null && target.isAlive()).forEach(target -> 
		{
			target.setMotion(0, 3, 0);
			target.velocityChanged = true;
		});
		
		return true;
	}
}
