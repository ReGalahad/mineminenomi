package xyz.pixelatedw.mineminenomi.blocks;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.CannonTileEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.CannonBallProjectile;
import xyz.pixelatedw.mineminenomi.init.ModItems;
import xyz.pixelatedw.wypi.WyHelper;

public class CannonBlock extends Block
{
	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

	public CannonBlock()
	{
		super(Properties.create(Material.IRON).hardnessAndResistance(1.5f));
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
	}

	@Override
	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context)
	{
		BlockState blockstate = this.getDefaultState();
		BlockPos blockpos = context.getPos();
		Direction[] adirection = context.getNearestLookingDirections();

		for (Direction direction : adirection)
		{
			if (direction.getAxis().isHorizontal())
			{
				blockstate = blockstate.with(FACING, direction);
				if (blockstate.isValidPosition(context.getWorld(), blockpos))
				{
					return blockstate;
				}
			}
		}

		return null;
	}

	@Override
	public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
	{
		CannonTileEntity tileEntity = (CannonTileEntity) world.getTileEntity(pos);

		if (!player.getHeldItemMainhand().isEmpty())
		{
			if (tileEntity.getGunpowederLoaded() < 5 && player.getHeldItemMainhand().getItem() == Items.GUNPOWDER)
			{
				player.getHeldItemMainhand().shrink(1);
				tileEntity.addGunpoweder();
				return true;
			}

			if (!tileEntity.hasCannonBall() && player.getHeldItemMainhand().getItem() == ModItems.CANNON_BALL)
			{
				player.getHeldItemMainhand().shrink(1);
				tileEntity.setHasCannonBall(true);
				return true;
			}
		}

		if (!world.isRemote && tileEntity.getGunpowederLoaded() > 0 && tileEntity.hasCannonBall())
		{
			int damage = 30 + (tileEntity.getGunpowederLoaded() * 2);
			int life = 100 + (tileEntity.getGunpowederLoaded() * 20);

			CannonBallProjectile proj = new CannonBallProjectile(player.world, player);
			proj.setPosition(pos.getX(), pos.getY() + 1.25, pos.getZ());
			proj.setDamage(damage);
			proj.setMaxLife(life);
			world.addEntity(proj);
			proj.shoot(player, 0, state.get(FACING).getHorizontalAngle(), 0, 3f, 0);

			for (int i = 0; i < 10; i++)
			{
				double offsetX = WyHelper.randomDouble();
				double offsetY = WyHelper.randomDouble();
				double offsetZ = WyHelper.randomDouble();

				((ServerWorld) world).spawnParticle(ParticleTypes.POOF, pos.getX() + offsetX, (pos.getY() + 1) + offsetY, pos.getZ() + offsetZ, 1, 0, 0, 0, 0);
			}

			tileEntity.emptyGunpoweder();
			tileEntity.setHasCannonBall(false);
			return true;
		}

		return true;
	}

	@Override
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos)
	{
		return facing.getOpposite() == stateIn.get(FACING) && !stateIn.isValidPosition(worldIn, currentPos) ? Blocks.AIR.getDefaultState() : stateIn;
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rot)
	{
		return state.with(FACING, rot.rotate(state.get(FACING)));
	}

	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn)
	{
		return state.rotate(mirrorIn.toRotation(state.get(FACING)));
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(FACING);
	}

	@Override
	public boolean canSpawnInBlock()
	{
		return true;
	}

	@Override
	public BlockRenderType getRenderType(BlockState state)
	{
		return BlockRenderType.INVISIBLE;
	}

	@Override
	public BlockRenderLayer getRenderLayer()
	{
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos)
	{
		return 1.0F;
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos)
	{
		return true;
	}

	@Override
	public boolean causesSuffocation(BlockState state, IBlockReader worldIn, BlockPos pos)
	{
		return false;
	}

	@Override
	public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos)
	{
		return false;
	}

	@Override
	public boolean canEntitySpawn(BlockState state, IBlockReader worldIn, BlockPos pos, EntityType<?> type)
	{
		return false;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public boolean isSideInvisible(BlockState state, BlockState adjacentBlockState, Direction side)
	{
		return true;
	}

	@Override
	public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos)
	{
		return 0;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world)
	{
		return new CannonTileEntity();
	}

	@Override
	public boolean hasTileEntity(BlockState state)
	{
		return true;
	}
}
