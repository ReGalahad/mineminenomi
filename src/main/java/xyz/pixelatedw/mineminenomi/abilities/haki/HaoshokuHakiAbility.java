package xyz.pixelatedw.mineminenomi.abilities.haki;

import java.util.List;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.SPlayEntityEffectPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.api.helpers.CrewHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
import xyz.pixelatedw.mineminenomi.entities.mobs.GenericNewEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.ai.abilities.haki.BusoshokuHakiGoal;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.haki.HaoshokuHakiParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.ChargeableAbility;

public class HaoshokuHakiAbility extends ChargeableAbility
{
	public static final HaoshokuHakiAbility INSTANCE = new HaoshokuHakiAbility();
	public static final ParticleEffect PARTICLES_1 = new HaoshokuHakiParticleEffect(1);
	public static final ParticleEffect PARTICLES_2 = new HaoshokuHakiParticleEffect(2);
	public static final ParticleEffect PARTICLES_3 = new HaoshokuHakiParticleEffect(3);
	
	public HaoshokuHakiAbility()
	{
		super("Haoshoku Haki", AbilityCategory.HAKI);
		this.setMaxChargeTime(3);
		
		this.onEndChargingEvent = this::onEndChargingEvent;
	}

	private boolean onEndChargingEvent(PlayerEntity player)
	{
		float haoLevel = HakiHelper.getTotalHakiExp(player) / 100F;
	
		int cooldown = 0;
		double radius = 0;
		int unconsciousTimer = 0;
		
		if(haoLevel < 1)
		{
			radius = 10;
			unconsciousTimer = 0;
			cooldown = 120;
			PARTICLES_1.spawn(player.world, player.posX, player.posY, player.posZ, 0, 0, 0);
		}
		else if(haoLevel >= 1 && haoLevel < 2.5)
		{
			radius = 25;
			unconsciousTimer = 100;
			cooldown = 60;
			PARTICLES_2.spawn(player.world, player.posX, player.posY, player.posZ, 0, 0, 0);
		}
		else if(haoLevel >= 2.5)
		{
			radius = 40;
			unconsciousTimer = 200;
			cooldown = 60;
			PARTICLES_3.spawn(player.world, player.posX, player.posY, player.posZ, 0, 0, 0);
		}

		List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, radius, CrewHelper.NOT_IN_CREW_PREDICATE, LivingEntity.class);
		targets.remove(player);
				
		for (LivingEntity target : targets)
		{
			if(unconsciousTimer > 0)
			{
				float targetHaoLevel = 0;
				if(target instanceof PlayerEntity)
				{
					targetHaoLevel = HakiHelper.getTotalHakiExp((PlayerEntity) target) / 100;
				}
				else if(target instanceof GenericNewEntity)
				{
					float busoHaki = ((GenericNewEntity) target).goalSelector.getRunningGoals().anyMatch(goal -> goal.getGoal() instanceof BusoshokuHakiGoal) ? 1 : 0;
					float dorikiConversion = ((GenericNewEntity) target).getDoriki() / 100;
					
					targetHaoLevel = busoHaki + dorikiConversion;
				}
				
				if(targetHaoLevel + 0.3 >= haoLevel)
					continue;
					
				EffectInstance instance = new EffectInstance(ModEffects.UNCONSCIOUS, unconsciousTimer, 1, false, false);
				target.addPotionEffect(new EffectInstance(ModEffects.ABILITY_OFF, unconsciousTimer - 20, 0, false, false));
				target.addPotionEffect(instance);
				((ServerPlayerEntity) player).connection.sendPacket(new SPlayEntityEffectPacket(target.getEntityId(), instance));
			}
			else
			{
				target.addPotionEffect(new EffectInstance(Effects.NAUSEA, 100, 0));
			}
		}
		
		//this.setMaxCooldown(cooldown);
		this.setMaxCooldown(1);
		return true;
	}
}
