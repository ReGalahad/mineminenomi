package xyz.pixelatedw.mineminenomi.abilities.zushi;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.ChargeableAbility;

public class GraviPullAbility extends ChargeableAbility{

	
	public static final GraviPullAbility INSTANCE = new GraviPullAbility();
	public GraviPullAbility() {
		super("Gravi Pull", AbilityCategory.DEVIL_FRUIT);
		this.onStartChargingEvent = this::onStartChargingEvent;
		this.setMaxChargeTime(1);
		this.onEndChargingEvent = this::onEndChargingEvent; 

		this.setMaxCooldown(15);
	}

	
	private boolean onStartChargingEvent(PlayerEntity player) {
		for(double i = 0; i < 2 * Math.PI + 1; i += Math.PI / 32) {
			GenericParticleData data = new GenericParticleData();
			data.setTexture(ModResources.GASU);
			data.setLife(100);
			data.setSize(2);
			double offsetX = Math.cos(i);
			double offsetZ = Math.sin(i);
			data.setMotion(offsetX / 5, 0, offsetZ / 5);
			data.setHasMotionDecay(false);
			WyHelper.spawnParticles(data, (ServerWorld) player.world, player.posX + offsetX, player.posY + 1, player.posZ + offsetZ);
		}
		return true;
	}
	
	
	private boolean onEndChargingEvent(PlayerEntity player) {
		for(double i = 0; i < 2 * Math.PI + 1; i += Math.PI / 32) {
			GenericParticleData data = new GenericParticleData();
			data.setTexture(ModResources.GASU);
			data.setLife(5);
			data.setSize(2);
			double offsetX = Math.cos(i) * 20;
			double offsetZ = Math.sin(i) * 20;
			data.setMotion(-offsetX / 10, 0, -offsetZ / 10);
			WyHelper.spawnParticles(data, (ServerWorld) player.world, player.posX + offsetX, player.posY + 1, player.posZ + offsetZ);
		}

		List<Entity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 20, FactionHelper.getOutsideGroupPredicate(player), LivingEntity.class);
		list.forEach(e -> {
			double offsetX = player.posX - e.posX;
			double offsetZ = player.posZ - e.posZ;
			e.setMotion(offsetX / 2, e.getMotion().y, offsetZ / 2);
		});
		return true;
	} 
}
