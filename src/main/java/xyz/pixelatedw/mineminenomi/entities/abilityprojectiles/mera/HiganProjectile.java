package xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.mera;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.abilities.ProjectileAbility;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;

public class HiganProjectile extends ProjectileAbility
{
	public HiganProjectile(World world)
	{super(MeraProjectiles.HIGAN, world);}
	
	public HiganProjectile(EntityType type, World world)
	{super(type, world);}
	
	public HiganProjectile(World world, double x, double y, double z)
	{super(MeraProjectiles.HIGAN, world, x, y, z);}
	
	public HiganProjectile(World world, LivingEntity player) 
	{		
		super(MeraProjectiles.HIGAN, world, player);
		
		this.onEntityImpactEvent = this::onEntityImpactEvent;
		this.onBlockImpactEvent = this::onBlockImpactEvent;
		this.onTickEvent = this::onTickEvent;
	}
	
	private void onEntityImpactEvent(LivingEntity hitEntity)
	{
		hitEntity.attackEntityFrom(DamageSource.causeMobDamage(this.getThrower()), 5);
		hitEntity.setFire(200);
	}
	
	private void onBlockImpactEvent(BlockRayTraceResult hit)
	{
		this.world.setBlockState(new BlockPos(this.posX, this.posY, this.posZ), Blocks.FIRE.getDefaultState());
	}
	
	private void onTickEvent()
	{
		if (!this.world.isRemote)
		{
			for (int i = 0; i < 2; i++)
			{
				double offsetX = WyMathHelper.randomDouble() / 5;
				double offsetY = WyMathHelper.randomDouble() / 5;
				double offsetZ = WyMathHelper.randomDouble() / 5;

				GenericParticleData data = new GenericParticleData();
				data.setTexture(ModResources.MERA);
				data.setLife(10);
				data.setSize(1.3F);
				((ServerWorld) this.world).spawnParticle(data, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ, 1, 0, 0, 0, 0.0D);
			}
		}
	}
}