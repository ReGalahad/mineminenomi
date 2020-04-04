package xyz.pixelatedw.mineminenomi.abilities.bari;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;

public class BarrierAbility extends ContinuousAbility
{
	public static final BarrierAbility INSTANCE = new BarrierAbility();

	private List<BlockPos> posList = new ArrayList<BlockPos>();
	
	public BarrierAbility()
	{
		super("Barrier", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(2);
		this.setThreshold(30);
		this.setDescription("The user creates an impenetrable wall that shields them from attacks.");
		
		this.onStartContinuityEvent = this::onStartContinuityEvent;
		this.onEndContinuityEvent = this::onEndContinuityEvent;
	}
	
	private boolean onStartContinuityEvent(PlayerEntity player)
	{
		Direction dir = Direction.getFacingDirections(player)[0];

		if (this.posList.isEmpty())
		{
			if (dir == Direction.NORTH)
				this.posList = AbilityHelper.createFilledCube(player.world, player.posX - 1, player.posY, player.posZ - 4, new int[] { 3, 4, 1 }, ModBlocks.BARRIER, "air", "nogrief");
			if (dir == Direction.SOUTH)
				this.posList = AbilityHelper.createFilledCube(player.world, player.posX - 1, player.posY, player.posZ + 2, new int[] { 3, 4, 1 }, ModBlocks.BARRIER, "air", "nogrief");
			if (dir == Direction.EAST)
				this.posList = AbilityHelper.createFilledCube(player.world, player.posX + 2, player.posY, player.posZ - 1, new int[] { 1, 4, 3 }, ModBlocks.BARRIER, "air", "nogrief");
			if (dir == Direction.WEST)
				this.posList = AbilityHelper.createFilledCube(player.world, player.posX - 4, player.posY, player.posZ - 1, new int[] { 1, 4, 3 }, ModBlocks.BARRIER, "air", "nogrief");
		}
		
		return true;	
	}
	
	private boolean onEndContinuityEvent(PlayerEntity player)
	{
		for (BlockPos pos : this.posList)
		{
			if (player.world.getBlockState(pos).getBlock() == ModBlocks.BARRIER)
				player.world.setBlockState(pos, Blocks.AIR.getDefaultState());
		}
		this.posList = new ArrayList<BlockPos>();
		
		return true;
	}
}
