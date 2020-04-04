package xyz.pixelatedw.mineminenomi.abilities.bomu;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.ChargeableAbility;

public class ZenshinKibakuAbility extends ChargeableAbility{

	public static final ZenshinKibakuAbility INSTANCE = new ZenshinKibakuAbility();
	private int power = 0;
	public ZenshinKibakuAbility() {
		super("Zenshin Kibaku", AbilityCategory.DEVIL_FRUIT);
		// TODO Auto-generated constructor stub
		this.setMaxCooldown(5);
		this.setDescription("The user creates a massive explosion from his body.");
		this.setMaxChargeTime(5);
		this.setCancelable();
		this.duringChargingEvent = this::duringChargingEvent;
		this.onEndChargingEvent = this::onEndChargingEvent;
		
	}

	private void duringChargingEvent(PlayerEntity player, int timer)
	{

		this.power = timer;
	
	}
	
	private boolean onEndChargingEvent(PlayerEntity player)
	{
		ExplosionAbility explosion = AbilityHelper.newExplosion(player, player.posX, player.posY, player.posZ, ((this.getMaxChargeTime() - this.power) / 20) * 4);
		explosion.setExplosionSound(true);
		explosion.setDamageOwner(false);
		explosion.setDestroyBlocks(true);
		explosion.setFireAfterExplosion(false);
		explosion.setSmokeParticles(new CommonExplosionParticleEffect(8));
		explosion.setDamageEntities(true);
		explosion.doExplosion();
		
		return true;
	}

}
