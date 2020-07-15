package xyz.pixelatedw.mineminenomi.entities.projectiles.mera;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.events.SetOnFireEvent;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class HidarumaProjectile extends AbilityProjectileEntity
{
	public HidarumaProjectile(World world)
	{
		super(MeraProjectiles.HIDARUMA, world);
	}

	public HidarumaProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public HidarumaProjectile(World world, double x, double y, double z)
	{
		super(MeraProjectiles.HIDARUMA, world, x, y, z);
	}

	public HidarumaProjectile(World world, LivingEntity player)
	{
		super(MeraProjectiles.HIDARUMA, world, player);

		this.setDamage(2);
		
		this.onEntityImpactEvent = this::onEntityImpactEvent;
		this.onBlockImpactEvent = this::onBlockImpactEvent;
		this.onTickEvent = this::onTickEvent;
	}

	private void onEntityImpactEvent(LivingEntity hitEntity)
	{
		SetOnFireEvent event = new SetOnFireEvent((PlayerEntity) this.getThrower(), hitEntity, 15);
		if (!MinecraftForge.EVENT_BUS.post(event))
			hitEntity.setFire(15);
	}
	
	private void onBlockImpactEvent(BlockPos hit)
	{		
		this.world.setBlockState(new BlockPos(hit.getX(), hit.getY(), hit.getZ()).up(), Blocks.FIRE.getDefaultState());
	}

	private void onTickEvent()
	{
		if (!this.world.isRemote)
		{
			for (int i = 0; i < 5; i++)
			{
				double offsetX = WyHelper.randomDouble() / 2;
				double offsetY = WyHelper.randomDouble() / 2;
				double offsetZ = WyHelper.randomDouble() / 2;
				WyHelper.spawnParticles(ParticleTypes.HAPPY_VILLAGER, (ServerWorld) this.world, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ);
			}
		}
	}
}
