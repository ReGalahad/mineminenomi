package xyz.pixelatedw.mineminenomi.entities.projectiles.magu;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.events.SetOnFireEvent;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class MeigoProjectile extends AbilityProjectileEntity
{
	public MeigoProjectile(World world)
	{
		super(MaguProjectiles.MEIGO, world);
	}

	public MeigoProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public MeigoProjectile(World world, double x, double y, double z)
	{
		super(MaguProjectiles.MEIGO, world, x, y, z);
	}

	public MeigoProjectile(World world, LivingEntity player)
	{
		super(MaguProjectiles.MEIGO, world, player);

		this.setDamage(40);
		this.setMaxLife(3);
		this.setPassThroughEntities();
		
		this.onEntityImpactEvent = this::onEntityImpactEvent;
		this.onBlockImpactEvent = this::onBlockImpactEvent;
		this.onTickEvent = this::onTickEvent;
	}
	
	private void onEntityImpactEvent(LivingEntity hitEntity)
	{
		SetOnFireEvent event = new SetOnFireEvent((PlayerEntity) this.getThrower(), hitEntity, 25);
		if (!MinecraftForge.EVENT_BUS.post(event))
			hitEntity.setFire(25);
	}
	
	private void onBlockImpactEvent(BlockPos hit)
	{
		this.world.setBlockState(new BlockPos(hit.getX(), hit.getY(), hit.getZ()).up(), Blocks.FIRE.getDefaultState());
	}

	private void onTickEvent()
	{
		if (!this.world.isRemote)
		{
			for (int i = 0; i < 3; i++)
			{
				double offsetX = WyHelper.randomDouble() / 2;
				double offsetY = WyHelper.randomDouble() / 2;
				double offsetZ = WyHelper.randomDouble() / 2;

				GenericParticleData data = new GenericParticleData();
				data.setTexture(ModResources.MERA);
				data.setLife(5);
				data.setSize(1.3F);
				WyHelper.spawnParticles(data, (ServerWorld) this.world, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ);
			}

			for (int i = 0; i < 10; i++)
			{
				double offsetX = WyHelper.randomDouble() / 2;
				double offsetY = WyHelper.randomDouble() / 2;
				double offsetZ = WyHelper.randomDouble() / 2;

				((ServerWorld)this.world).spawnParticle(ParticleTypes.LAVA, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ, 1, 0, 0, 0, 0.5);
			}
		}
	}
}
