package xyz.pixelatedw.mineminenomi.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
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
import net.minecraft.world.IWorldReader;
import net.minecraft.world.TickPriority;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.wypi.WyHelper;

public class SunaSandBlock extends FallingBlock
{
	private static final VoxelShape FULL_AABB = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape EMPTY_AABB = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
	private static final BooleanProperty USER_ON_TOP = BooleanProperty.create("user_on_top");

	private final int dustColor;

	public SunaSandBlock()
	{
		super(Properties.create(Material.SAND).hardnessAndResistance(0.5F).doesNotBlockMovement().noDrops());
		this.dustColor = WyHelper.hexToRGB("#765038").getRGB();
		this.setDefaultState(this.stateContainer.getBaseState().with(USER_ON_TOP, Boolean.valueOf(false)));
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
		return state.get(USER_ON_TOP).booleanValue() ? FULL_AABB : EMPTY_AABB;
	}
	
	@Override
	public boolean causesSuffocation(BlockState state, IBlockReader worldIn, BlockPos pos)
	{
		return true;
	}

	@Override
	public int tickRate(IWorldReader world)
	{
		return 1;
	}
	
	@Override
	public void tick(BlockState state, World world, BlockPos pos, Random random)
	{
		if(world.getGameTime() % 10 == 0)
			world.setBlockState(pos, state.with(USER_ON_TOP, false));
		world.getPendingBlockTicks().scheduleTick(pos, this, 1, TickPriority.EXTREMELY_HIGH);
	}

	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity)
	{
		if (entity instanceof LivingEntity)
		{
			IDevilFruit dfProps = DevilFruitCapability.get((LivingEntity) entity);

			if (dfProps.getDevilFruit().equalsIgnoreCase("suna_suna"))
				world.setBlockState(pos, state.with(USER_ON_TOP, true));
			else
			{
				world.setBlockState(pos, state.with(USER_ON_TOP, false));
				entity.setMotionMultiplier(state, new Vec3d(0.25D, 0.05F, 0.25D));
			}

		}
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public int getDustColor(BlockState state)
	{
		return this.dustColor;
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(USER_ON_TOP);
	}
}
