package xyz.pixelatedw.mineminenomi.abilities.ope;

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
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.wypi.abilities.IParallelContinuousAbility;

public class RoomAbility extends ContinuousAbility implements IParallelContinuousAbility
{
	public static final Ability INSTANCE = new RoomAbility();
	
	private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(AirBlockProtectionRule.INSTANCE, FoliageBlockProtectionRule.INSTANCE, LiquidBlockProtectionRule.INSTANCE).setBypassGriefRule();
	private List<BlockPos> blockList = new ArrayList<BlockPos>();

	public RoomAbility()
	{
		super("ROOM", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(1);
		this.setDescription("Creates a spherical space around the user in which they can manipulate anything or use other skills");

		this.onStartContinuityEvent = this::onStartContinuityEvent;
		this.onEndContinuityEvent = this::onEndContinuityEvent;
	}
	
	private boolean onStartContinuityEvent(PlayerEntity player)
	{
		if (this.blockList.isEmpty())
		{
			this.blockList.addAll(AbilityHelper.createEmptySphere(player.world, (int) player.posX, (int) player.posY, (int) player.posZ, 20, ModBlocks.OPE, GRIEF_RULE));
			player.world.setBlockState(new BlockPos(player.posX, player.posY, player.posZ), ModBlocks.OPE_MID.getDefaultState());
			this.blockList.add(new BlockPos(MathHelper.floor(player.posX), MathHelper.floor(player.posY), MathHelper.floor(player.posZ)));
		}
		
		return true;
	}
	
	private boolean onEndContinuityEvent(PlayerEntity player)
	{
		for (BlockPos pos : this.blockList)
		{
			Block currentBlock = player.world.getBlockState(pos).getBlock();
			if (currentBlock == ModBlocks.OPE || currentBlock == ModBlocks.OPE_MID)
				player.world.setBlockState(pos, Blocks.AIR.getDefaultState());
		}
		this.blockList = new ArrayList<BlockPos>();
		
		return true;
	}
}