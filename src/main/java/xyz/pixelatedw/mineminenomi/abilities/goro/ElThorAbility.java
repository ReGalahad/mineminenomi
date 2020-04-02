package xyz.pixelatedw.mineminenomi.abilities.goro;

import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitsHelper;
import xyz.pixelatedw.mineminenomi.init.ModArmors;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.goro.ElThorParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.ChargeableAbility;

public class ElThorAbility extends ChargeableAbility
{
	public static final Ability INSTANCE = new ElThorAbility();

	private static final ParticleEffect PARTICLES = new ElThorParticleEffect();

	public ElThorAbility()
	{
		super("El Thor", AbilityCategory.DEVIL_FRUIT);
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
		ServerWorld world = (ServerWorld) player.world;
		
		boolean hasTomoeDrums = player.inventory.armorInventory.get(2) != null && player.inventory.armorInventory.get(2).getItem() == ModArmors.TOMOE_DRUMS;
		
		if (mop != null)
		{
			double i = mop.getHitVec().x;
			double j = mop.getHitVec().y;
			double k = mop.getHitVec().z;
						
			int f = 0;
			int max = 1;
			
			if(hasTomoeDrums)
				max = 5;
			
			int x = 0;
			int z = 0;
			
			while (f < 5)
			{
				if(hasTomoeDrums)
				{
					x = (int) WyHelper.randomWithRange(-15, 15);
					z = (int) WyHelper.randomWithRange(-15, 15);
				}

				world.addLightningBolt(new LightningBoltEntity(player.world, (i + x) + f, j, (k + z) + f, false));
				world.addLightningBolt(new LightningBoltEntity(player.world, (i + x) + f, j, (k + z) - f, false));
				world.addLightningBolt(new LightningBoltEntity(player.world, (i + x) - f, j, (k + z) - f, false));
				world.addLightningBolt(new LightningBoltEntity(player.world, (i + x) - f, j, (k + z) + f, false));

				ExplosionAbility explosion = DevilFruitsHelper.newExplosion(player, i + x, j, k + z, hasTomoeDrums ? 10 : 7);
				explosion.setExplosionSound(true);
				explosion.setDamageOwner(false);
				explosion.setDestroyBlocks(true);
				explosion.setFireAfterExplosion(true);
				explosion.setSmokeParticles(new CommonExplosionParticleEffect(10));
				explosion.setDamageEntities(true);
				explosion.doExplosion();
				
				f++;
			}
		}
		
		return true;
	}
}