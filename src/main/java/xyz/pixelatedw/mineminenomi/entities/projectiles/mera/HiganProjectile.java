package xyz.pixelatedw.mineminenomi.entities.projectiles.mera;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class HiganProjectile extends AbilityProjectileEntity
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
		
		this.setDamage(5);
		
		this.onEntityImpactEvent = this::onEntityImpactEvent;
		this.onBlockImpactEvent = this::onBlockImpactEvent;
		this.onTickEvent = this::onTickEvent;
	}
	
	private void onEntityImpactEvent(LivingEntity hitEntity)
	{
		hitEntity.setFire(200);
	}
	
	private void onBlockImpactEvent(BlockPos hit)
	{
		this.world.setBlockState(new BlockPos(hit.getX(), hit.getY(), hit.getZ()), Blocks.FIRE.getDefaultState());
	}
	
	private void onTickEvent()
	{
		if (!this.world.isRemote)
		{
			for (int i = 0; i < 2; i++)
			{
				double offsetX = WyHelper.randomDouble() / 5;
				double offsetY = WyHelper.randomDouble() / 5;
				double offsetZ = WyHelper.randomDouble() / 5;

				GenericParticleData data = new GenericParticleData();
				data.setTexture(ModResources.MERA);
				data.setLife(10);
				data.setSize(1.3F);
				WyHelper.spawnParticles(data, (ServerWorld) this.world, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ);
			}
		}
	}
}