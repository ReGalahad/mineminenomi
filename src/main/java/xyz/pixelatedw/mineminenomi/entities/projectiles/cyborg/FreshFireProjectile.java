package xyz.pixelatedw.mineminenomi.entities.projectiles.cyborg;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class FreshFireProjectile extends AbilityProjectileEntity
{
	private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(AirBlockProtectionRule.INSTANCE); 

	public FreshFireProjectile(World world)
	{
		super(CyborgProjectiles.FRESH_FIRE, world);
	}

	public FreshFireProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public FreshFireProjectile(World world, double x, double y, double z)
	{
		super(CyborgProjectiles.FRESH_FIRE, world, x, y, z);
	}

	public FreshFireProjectile(World world, LivingEntity player)
	{
		super(CyborgProjectiles.FRESH_FIRE, world, player);
		
		this.setDamage(5);
		this.setPassThroughEntities();
		this.setMaxLife(15);
		
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
		AbilityHelper.placeBlockIfAllowed(this.world, hit.getX(), hit.getY() + 1, hit.getZ(), Blocks.FIRE, GRIEF_RULE);
	}
	
	private void onTickEvent()
	{
		for (int i = 0; i < 10; i++)
		{
			double offsetX = WyHelper.randomDouble() / 2;
			double offsetY = WyHelper.randomDouble() / 2;
			double offsetZ = WyHelper.randomDouble() / 2;

			GenericParticleData data = new GenericParticleData();
			data.setTexture(ModResources.MERA);
			data.setLife(5);
			data.setSize(0.7F);
			WyHelper.spawnParticles(data, (ServerWorld) this.world, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ);
		}
	}
}