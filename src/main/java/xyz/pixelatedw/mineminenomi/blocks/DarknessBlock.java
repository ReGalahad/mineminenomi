package xyz.pixelatedw.mineminenomi.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.TickPriority;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.init.ModMaterials;

public class DarknessBlock extends Block
{
	private static final VoxelShape FULL_AABB = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape EMPTY_AABB = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
	private static final BooleanProperty USER_ON_TOP = BooleanProperty.create("user_on_top");
	
	public DarknessBlock()
	{
		super(Block.Properties.create(ModMaterials.DARKNESS_MATERIAL).doesNotBlockMovement().hardnessAndResistance(-1.0F, 3600000.0F).noDrops());
		this.setDefaultState(this.stateContainer.getBaseState().with(USER_ON_TOP, Boolean.valueOf(false)));
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
		return state.get(USER_ON_TOP).booleanValue() ? FULL_AABB : EMPTY_AABB;
	}

	@Override
	public int tickRate(IWorldReader world)
	{
		return 1;
	}

	@Override
	public void tick(BlockState state, World world, BlockPos pos, Random random)
	{
		if (world.getGameTime() % 10 == 0)
			world.setBlockState(pos, state.with(USER_ON_TOP, false));
		world.getPendingBlockTicks().scheduleTick(pos, this, 1, TickPriority.EXTREMELY_HIGH);
	}

	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity)
	{
		if (entity instanceof LivingEntity)
		{
			if (DevilFruitHelper.hasDevilFruit((LivingEntity) entity, ModAbilities.YAMI_YAMI_NO_MI))
				world.setBlockState(pos, state.with(USER_ON_TOP, true));
			else
			{
				world.setBlockState(pos, state.with(USER_ON_TOP, false));
				entity.setMotionMultiplier(state, entity.getMotion().add(0, 0.05, 0));
			}
		}
	}

	@Override
	public boolean causesSuffocation(BlockState state, IBlockReader worldIn, BlockPos pos)
	{
		return true;
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
