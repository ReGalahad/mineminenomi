package xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.mera;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectile;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;

public class HiganProjectile extends AbilityProjectile
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
		
		this.onImpactEvent = this::onImpactEvent;
		this.onTickEvent = this::onTickEvent;
	}
	
	private void onImpactEvent(RayTraceResult hit)
	{
		this.world.setBlockState(new BlockPos(this.posX, this.posY, this.posZ), Blocks.FIRE.getDefaultState());
	}
	
	private void onTickEvent()
	{
		if (!this.world.isRemote)
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
				((ServerWorld) this.world).spawnParticle(data, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ, 1, 0, 0, 0, 0.0D);
			}
		}
	}
}