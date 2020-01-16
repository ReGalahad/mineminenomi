package xyz.pixelatedw.mineminenomi.items.dials;

import javax.annotation.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.telemetry.WyTelemetry;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;

public class EisenDialItem extends BlockItem
{

	public EisenDialItem()
	{
		super(ModBlocks.EISEN_DIAL, new Properties().group(ModCreativeTabs.MISC).maxStackSize(16));
	}
	
	@Override
	protected boolean onBlockPlaced(BlockPos pos, World world, @Nullable PlayerEntity player, ItemStack itemStack, BlockState state)
	{
    	if(!player.isCreative())
    		WyTelemetry.addMiscStat("eisenDialsPlaced", "Eisen Dials Placed", 1);
		return super.onBlockPlaced(pos, world, player, itemStack, state);
	}
}
