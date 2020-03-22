package xyz.pixelatedw.mineminenomi.abilities.gura;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitsHelper;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.PunchAbility;

public class GekishinAbility extends PunchAbility
{
	public static final Ability INSTANCE = new GekishinAbility();

	public GekishinAbility()
	{
		super("Gekishin", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(20);
		this.setDescription("The user cracks the air, which launches a small but powerful shockwave towards the opponent.");

		this.onHitEntity = this::onHitEntity;
	}

	private float onHitEntity(PlayerEntity player, LivingEntity target)
	{
		ExplosionAbility explosion = DevilFruitsHelper.newExplosion(target, target.posX, target.posY, target.posZ, 3);
		explosion.setExplosionSound(true);
		explosion.setDamageOwner(false);
		explosion.setDestroyBlocks(false);
		explosion.setFireAfterExplosion(false);
		explosion.setSmokeParticles(new CommonExplosionParticleEffect(3));
		explosion.setDamageEntities(true);
		explosion.doExplosion();
		
		GenericParticleData data = new GenericParticleData();
		data.setTexture(ModResources.GURA);
		data.setLife(20);
		data.setSize(150F);
		WyHelper.spawnParticles(data, (ServerWorld) player.world, target.posX + 1, target.posY, target.posZ);
		
		return 100;
	}
}
