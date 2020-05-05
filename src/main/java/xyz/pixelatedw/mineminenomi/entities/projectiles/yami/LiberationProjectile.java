package xyz.pixelatedw.mineminenomi.entities.projectiles.yami;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class LiberationProjectile extends AbilityProjectileEntity
{
	private final Block[] randomBlocks = new Block[] {Blocks.STONE, Blocks.SAND, Blocks.GRASS_BLOCK, Blocks.DIRT, Blocks.GRAVEL, Blocks.CLAY, Blocks.COBBLESTONE};
	private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(AirBlockProtectionRule.INSTANCE); 

	public LiberationProjectile(World world)
	{
		super(YamiProjectiles.LIBERATION, world);
	}

	public LiberationProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public LiberationProjectile(World world, double x, double y, double z)
	{
		super(YamiProjectiles.LIBERATION, world, x, y, z);
	}

	public LiberationProjectile(World world, LivingEntity player)
	{
		super(YamiProjectiles.LIBERATION, world, player);
		
		this.setDamage(5);
		
		this.onBlockImpactEvent = this::onBlockImpactEvent;
	}
	
	private void onBlockImpactEvent(BlockPos hit)
	{
		Block block = this.randomBlocks[this.rand.nextInt(this.randomBlocks.length)];
		AbilityHelper.placeBlockIfAllowed(this.world, hit.getX(), hit.getY() + 1, hit.getZ(), block, GRIEF_RULE);
	}
}