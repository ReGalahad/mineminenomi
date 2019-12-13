package xyz.pixelatedw.mineminenomi.blocks;

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
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;

public class SunaSandBlock extends FallingBlock
{
	private static final VoxelShape NON_SUNA_AABB = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D);
	private static final VoxelShape SUNA_AABB = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
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
		return state.get(USER_ON_TOP).booleanValue() ? SUNA_AABB : NON_SUNA_AABB;
	}

	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity)
	{
		if (entity instanceof LivingEntity)
		{
			IDevilFruit dfProps = DevilFruitCapability.get((LivingEntity) entity);

			if (dfProps.getDevilFruit().equalsIgnoreCase("sunasuna"))
				world.setBlockState(pos, state.with(USER_ON_TOP, false));
			else
			{
				world.setBlockState(pos, state.with(USER_ON_TOP, true));
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
