package xyz.pixelatedw.mineminenomi.entities.projectiles.kage;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class BlackBoxProjectile extends AbilityProjectileEntity
{
	private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(AirBlockProtectionRule.INSTANCE, FoliageBlockProtectionRule.INSTANCE); 

	public BlackBoxProjectile(World world)
	{
		super(KageProjectiles.BLACK_BOX, world);
	}

	public BlackBoxProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public BlackBoxProjectile(World world, double x, double y, double z)
	{
		super(KageProjectiles.BLACK_BOX, world, x, y, z);
	}

	public BlackBoxProjectile(World world, LivingEntity player)
	{
		super(KageProjectiles.BLACK_BOX, world, player);

		this.setDamage(4);
		
		this.onBlockImpactEvent = this::onBlockImpactEvent;
	}
	
	private void onBlockImpactEvent(BlockPos pos)
	{
		AbilityHelper.createFilledCube(this.world, pos.getX(), pos.getY(), pos.getZ(), 2, 2, 2, ModBlocks.KAGE, GRIEF_RULE);
	}
}