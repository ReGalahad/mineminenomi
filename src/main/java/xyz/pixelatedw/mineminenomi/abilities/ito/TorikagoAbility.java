package xyz.pixelatedw.mineminenomi.abilities.ito;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.LiquidBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.wypi.abilities.IParallelContinuousAbility;

public class TorikagoAbility extends ContinuousAbility implements IParallelContinuousAbility
{
	public static final TorikagoAbility INSTANCE = new TorikagoAbility();
	
	private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(AirBlockProtectionRule.INSTANCE, FoliageBlockProtectionRule.INSTANCE, LiquidBlockProtectionRule.INSTANCE).setBypassGriefRule();
	private List<BlockPos> blockList = new ArrayList<BlockPos>();

	public TorikagoAbility()
	{
		super("Torikago", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(1);
		this.setDescription("Creates an indestructible dome made of strings, that damage anyone who toches then.");

		this.onStartContinuityEvent = this::onStartContinuityEvent;
		this.onEndContinuityEvent = this::onEndContinuityEvent;
	}
	
	private boolean onStartContinuityEvent(PlayerEntity player)
	{
		if (this.blockList.isEmpty())
		{
			this.blockList.addAll(AbilityHelper.createEmptySphere(player.world, (int) player.posX, (int) player.posY, (int) player.posZ, 25, ModBlocks.STRING_WALL, GRIEF_RULE));
			player.world.setBlockState(new BlockPos(player.posX, player.posY, player.posZ), ModBlocks.STRING_MID.getDefaultState());
			this.blockList.add(new BlockPos(MathHelper.floor(player.posX), MathHelper.floor(player.posY), MathHelper.floor(player.posZ)));
		}
		
		return true;
	}
	
	private boolean onEndContinuityEvent(PlayerEntity player)
	{
		for (BlockPos pos : this.blockList)
		{
			Block currentBlock = player.world.getBlockState(pos).getBlock();
			if (currentBlock == ModBlocks.STRING_WALL || currentBlock == ModBlocks.STRING_MID)
				player.world.setBlockState(pos, Blocks.AIR.getDefaultState());
		}
		this.blockList = new ArrayList<BlockPos>();
		
		return true;
	}
}