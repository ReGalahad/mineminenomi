package xyz.pixelatedw.mineminenomi.entities.projectiles.doku;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.doku.ChloroBallParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class DokuFuguProjectile extends AbilityProjectileEntity
{
	private static final ParticleEffect PARTICLES = new ChloroBallParticleEffect();
	private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(AirBlockProtectionRule.INSTANCE, FoliageBlockProtectionRule.INSTANCE); 

	public DokuFuguProjectile(World world)
	{
		super(DokuProjectiles.DOKU_FUGU, world);
	}

	public DokuFuguProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public DokuFuguProjectile(World world, double x, double y, double z)
	{
		super(DokuProjectiles.DOKU_FUGU, world, x, y, z);
	}

	public DokuFuguProjectile(World world, LivingEntity player)
	{
		super(DokuProjectiles.DOKU_FUGU, world, player);

		this.setDamage(7);
		this.setChangeHurtTime(true);
		this.setHurtTime(20);
		
		this.onEntityImpactEvent = this::onEntityImpactEvent;
		this.onBlockImpactEvent = this::onBlockImpactEvent;
		this.onTickEvent = this::onTickEvent;
	}

	private void onEntityImpactEvent(LivingEntity hitEntity)
	{
		hitEntity.addPotionEffect(new EffectInstance(Effects.POISON, 300, 0));
		this.onBlockImpactEvent.onImpact(hitEntity.getPosition());
	}

	private void onBlockImpactEvent(BlockPos pos)
	{
		for (int i = 0; i < 20; i++)
		{
			double offsetX = WyHelper.randomWithRange(-1, 1);
			double offsetZ = WyHelper.randomWithRange(-1, 1);

			BlockPos location = new BlockPos(this.posX + offsetX, this.posY, this.posZ + offsetZ);

			if (this.world.getBlockState(location.down()).isSolid())
				AbilityHelper.placeBlockIfAllowed(this.world, location.getX(), location.getY(), location.getZ(), ModBlocks.POISON, GRIEF_RULE);
		}

		PARTICLES.spawn(this.world, this.posX, this.posY + 1, this.posZ, 0, 0, 0);
	}
	
	private void onTickEvent()
	{
		if (!this.world.isRemote)
		{
			for (int i = 0; i < 2; i++)
			{
				double offsetX = WyHelper.randomDouble() / 2;
				double offsetY = WyHelper.randomDouble() / 2;
				double offsetZ = WyHelper.randomDouble() / 2;

				GenericParticleData data = new GenericParticleData();
				data.setTexture(ModResources.DOKU);
				data.setLife(5);
				data.setSize(1.3F);
				WyHelper.spawnParticles(data, (ServerWorld) this.world, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ);
			}
		}
	}
}
