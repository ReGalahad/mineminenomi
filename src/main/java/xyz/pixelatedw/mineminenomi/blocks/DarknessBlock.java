package xyz.pixelatedw.mineminenomi.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;

public class DarknessBlock extends Block
{	
	private static final VoxelShape YAMI_AABB = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D);
	private static final VoxelShape NON_YAMI_AABB = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
	private static final BooleanProperty USER_ON_TOP = BooleanProperty.create("user_on_top");

	public DarknessBlock()
	{
		super(Block.Properties.create(Material.WEB).doesNotBlockMovement().hardnessAndResistance(Float.MAX_VALUE).noDrops());
		this.setDefaultState(this.stateContainer.getBaseState().with(USER_ON_TOP, Boolean.valueOf(false)));
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
		return state.get(USER_ON_TOP).booleanValue() ? YAMI_AABB : NON_YAMI_AABB;
	}

	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity)
	{
		if(entity instanceof LivingEntity)
		{
			IDevilFruit dfProps = DevilFruitCapability.get((LivingEntity) entity);
		
			if(dfProps.getDevilFruit().equalsIgnoreCase("yamiyami") || dfProps.getDevilFruit().equalsIgnoreCase("yamidummy") )
				world.setBlockState(pos, state.with(USER_ON_TOP, true));
			else
			{
				world.setBlockState(pos, state.with(USER_ON_TOP, false));	
				entity.setMotionMultiplier(state, new Vec3d(0.25D, 0.05F, 0.25D));
			}
		}
	}

	@Override
	public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean isMoving)
	{
		world.getPendingBlockTicks().scheduleTick(pos, this, this.tickRate(world));
	}
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(USER_ON_TOP);
	}

}
