package xyz.pixelatedw.mineminenomi.abilities.mera;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectile;
import xyz.pixelatedw.mineminenomi.api.abilities.extra.AbilityExplosion;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.MeraProjectiles;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;

public class HikenAbility extends Ability
{
	public static final Ability INSTANCE = new HikenAbility();
	
	public HikenAbility()
	{
		super("Hiken", Category.DEVIL_FRUIT);
		this.setMaxCooldown(8);
		this.setDescription("Turns the user's fist into flames and launches it towards the target.");

		this.onUseEvent = this::onUseEvent;
	}
	
	// Ability	
	private void onUseEvent(PlayerEntity player, Ability ability)
	{
		MeraProjectiles.Hiken proj = new MeraProjectiles.Hiken(player.world, player);
		proj.setOnImpactEvent(this::onImpactEvent);	
		proj.setOnTickEvent(this::onTickEvent);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);		
	}
	
	// Projectile	
	private void onImpactEvent(AbilityProjectile projectile, RayTraceResult hit)
	{
		AbilityExplosion explosion = WyHelper.newExplosion(projectile.getThrower(), projectile.posX, projectile.posY, projectile.posZ, 2);
		explosion.setExplosionSound(true);
		explosion.setDamageOwner(false);
		explosion.setDestroyBlocks(true);
		explosion.setFireAfterExplosion(true);
		explosion.setSmokeParticles(new CommonExplosionParticleEffect(2));
		explosion.setDamageEntities(true);
		explosion.doExplosion();		}
	
	private void onTickEvent(AbilityProjectile projectile)
	{		
		if(!projectile.world.isRemote)
		{
			for (int i = 0; i < 15; i++)
			{
				double offsetX = WyMathHelper.randomDouble() / 2;
				double offsetY = WyMathHelper.randomDouble() / 2;
				double offsetZ = WyMathHelper.randomDouble() / 2;

				GenericParticleData data = new GenericParticleData();
				data.setTexture(ModResources.MERA);				
				data.setLife(10);
				data.setSize(1.3F);
				((ServerWorld) projectile.world).spawnParticle(data, projectile.posX + offsetX, projectile.posY + offsetY, projectile.posZ + offsetZ, 1, 0, 0, 0, 0.0D);
			}
			
			for (int i = 0; i < 5; i++)
			{
				double offsetX = WyMathHelper.randomDouble() / 2;
				double offsetY = WyMathHelper.randomDouble() / 2;
				double offsetZ = WyMathHelper.randomDouble() / 2;
				
				GenericParticleData data = new GenericParticleData();
				data.setTexture(ModResources.MOKU);
				data.setLife(7);
				data.setSize(1.2F);
				((ServerWorld) projectile.world).spawnParticle(data, projectile.posX + offsetX, projectile.posY + offsetY, projectile.posZ + offsetZ, 1, 0, 0, 0, 0.0D);
			}
		}
	}
}