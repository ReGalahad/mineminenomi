package xyz.pixelatedw.mineminenomi.abilities.haki;

import java.util.List;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.SPlayEntityEffectPacket;
import net.minecraft.potion.EffectInstance;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.haki.HaoshokuHakiParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.ChargeableAbility;

public class HaoshokuHakiAbility extends ChargeableAbility
{
	public static final HaoshokuHakiAbility INSTANCE = new HaoshokuHakiAbility();
	public static final ParticleEffect PARTICLES = new HaoshokuHakiParticleEffect();

	public HaoshokuHakiAbility()
	{
		super("Haoshoku Haki", AbilityCategory.HAKI);
		this.setMaxChargeTime(3);
		//this.setMaxCooldown(60);
		
		this.onEndChargingEvent = this::onEndChargingEvent;
	}

	private boolean onEndChargingEvent(PlayerEntity player)
	{
		PARTICLES.spawn(player.world, player.posX, player.posY, player.posZ, 0, 0, 0);
		
		List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 40);
		targets.remove(player);
				
		for(LivingEntity target : targets)
		{
			EffectInstance instance = new EffectInstance(ModEffects.UNCONSCIOUS, 200, 1);
			target.addPotionEffect(instance);
			((ServerPlayerEntity) player).connection.sendPacket(new SPlayEntityEffectPacket(target.getEntityId(), instance));
		}
		
		return true;
	}
}
