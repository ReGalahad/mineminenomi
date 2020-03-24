package xyz.pixelatedw.mineminenomi.entities.projectiles.magu;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class DaiFunkaProjectile extends AbilityProjectileEntity
{
	public DaiFunkaProjectile(World world)
	{
		super(MaguProjectiles.DAI_FUNKA, world);
	}

	public DaiFunkaProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public DaiFunkaProjectile(World world, double x, double y, double z)
	{
		super(MaguProjectiles.DAI_FUNKA, world, x, y, z);
	}

	public DaiFunkaProjectile(World world, LivingEntity player)
	{
		super(MaguProjectiles.DAI_FUNKA, world, player);

		this.setDamage(20);
		
		this.onEntityImpactEvent = this::onEntityImpactEvent;
		this.onBlockImpactEvent = this::onBlockImpactEvent;
		this.onTickEvent = this::onTickEvent;
	}
	
	private void onEntityImpactEvent(LivingEntity hitEntity)
	{
		hitEntity.setFire(250);
	}
	
	private void onBlockImpactEvent(BlockPos hit)
	{
		this.world.setBlockState(new BlockPos(hit.getX(), hit.getY(), hit.getZ()).up(), Blocks.FIRE.getDefaultState());
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
				data.setTexture(ModResources.MERA);
				data.setLife(3);
				data.setSize(2.3F);
				WyHelper.spawnParticles(data, (ServerWorld) this.world, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ);
			}

			for (int i = 0; i < 2; i++)
			{
				double offsetX = WyHelper.randomDouble() / 2;
				double offsetY = WyHelper.randomDouble() / 2;
				double offsetZ = WyHelper.randomDouble() / 2;

				((ServerWorld)this.world).spawnParticle(ParticleTypes.LAVA, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ, 1, 0, 0, 0, 0.5);
			}
		}
	}
}
