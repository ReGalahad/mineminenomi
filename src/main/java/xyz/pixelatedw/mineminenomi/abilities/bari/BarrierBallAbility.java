package xyz.pixelatedw.mineminenomi.abilities.bari;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;

public class BarrierBallAbility extends ContinuousAbility
{
	public static final BarrierBallAbility INSTANCE = new BarrierBallAbility();

	private List<BlockPos> posList = new ArrayList<BlockPos>();
	
	public BarrierBallAbility()
	{
		super("Barrier Ball", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(3);
		this.setThreshold(15);
		this.setDescription("The user creates a spherical barrier around them, shielding them from attacks.");
		
		this.onStartContinuityEvent = this::onStartContinuityEvent;
		this.onEndContinuityEvent = this::onEndContinuityEvent;
	}

	private boolean onStartContinuityEvent(PlayerEntity player)
	{		
		if (this.posList.isEmpty())
		{
			RayTraceResult mop = WyHelper.rayTraceBlocks(player);
			World world = player.world;
			System.out.println(player.isSneaking());
			if (player.isSneaking())
				this.posList.addAll(AbilityHelper.createEmptySphere(world, (int) player.posX, (int) player.posY, (int) player.posZ, 5, ModBlocks.BARRIER, "air", "nogrief"));
			else if (mop != null)
				this.posList.addAll(AbilityHelper.createEmptySphere(world, (int) mop.getHitVec().x, (int) mop.getHitVec().y, (int) mop.getHitVec().z, 5, ModBlocks.BARRIER, "air", "nogrief"));
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
