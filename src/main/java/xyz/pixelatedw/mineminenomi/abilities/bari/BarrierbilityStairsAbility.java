package xyz.pixelatedw.mineminenomi.abilities.bari;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import xyz.pixelatedw.mineminenomi.entities.projectiles.bari.BarrierbilityStairsProjectile;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;

public class BarrierbilityStairsAbility extends ContinuousAbility
{
	public static final BarrierbilityStairsAbility INSTANCE = new BarrierbilityStairsAbility();

	private List<BlockPos> posList = new ArrayList<BlockPos>();

	public BarrierbilityStairsAbility()
	{
		super("Barrierbility Stairs", AbilityCategory.DEVIL_FRUIT);
		this.setDescription("By shaping the Barrier, the user can create stairs.");
		
		this.onStartContinuityEvent = this::onStartContinuityEvent;
		this.onEndContinuityEvent = this::onEndContinuityEvent;
	}

	private boolean onStartContinuityEvent(PlayerEntity player)
	{
		if (this.posList.isEmpty())
		{
			BarrierbilityStairsProjectile proj = new BarrierbilityStairsProjectile(player.world, player);
			player.world.addEntity(proj);
			proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);		
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
		this.posList = new ArrayList<>();
		
		return true;
	}
	
	public void fillBlocksList(List<BlockPos> entityList)
	{
		this.posList.addAll(entityList);
	}
}