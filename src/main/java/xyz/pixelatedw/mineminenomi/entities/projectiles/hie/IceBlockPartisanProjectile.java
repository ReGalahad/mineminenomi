package xyz.pixelatedw.mineminenomi.entities.projectiles.hie;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;

public class IceBlockPartisanProjectile extends AbilityProjectileEntity
{
	public IceBlockPartisanProjectile(World world)
	{
		super(HieProjectiles.ICE_BLOCK_PARTISAN, world);
	}

	public IceBlockPartisanProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public IceBlockPartisanProjectile(World world, double x, double y, double z)
	{
		super(HieProjectiles.ICE_BLOCK_PARTISAN, world, x, y, z);
	}

	public IceBlockPartisanProjectile(World world, LivingEntity player)
	{
		super(HieProjectiles.ICE_BLOCK_PARTISAN, world, player);

		this.setDamage(10);
		
		this.onBlockImpactEvent = this::onBlockImpactEvent;
		this.onTickEvent = this::onTickEvent;
	}
	
	private void onBlockImpactEvent(BlockRayTraceResult hit)
	{		
		WyHelper.placeBlockIfAllowed(this.world, (int)posX, (int)posY, (int)posZ, Blocks.PACKED_ICE, "core", "liquid", "foliage");
	}

	private void onTickEvent()
	{
		if (!this.world.isRemote)
		{
			for (int i = 0; i < 5; i++)
			{
				double offsetX = WyMathHelper.randomDouble() / 2;
				double offsetY = WyMathHelper.randomDouble() / 2;
				double offsetZ = WyMathHelper.randomDouble() / 2;

				GenericParticleData data = new GenericParticleData();
				data.setTexture(ModResources.HIE);
				data.setLife(2);
				data.setSize(1.5F);
				((ServerWorld) this.world).spawnParticle(data, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ, 1, 0, 0, 0, 0.0D);
			}
		}
	}
}
