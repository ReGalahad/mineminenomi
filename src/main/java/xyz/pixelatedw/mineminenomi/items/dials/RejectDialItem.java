package xyz.pixelatedw.mineminenomi.items.dials;

import javax.annotation.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.telemetry.WyTelemetry;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;

public class RejectDialItem extends BlockItem
{

	public RejectDialItem()
	{
		super(ModBlocks.rejectDialBlock, new Properties().group(ModCreativeTabs.MISC).maxStackSize(16));
	}

	@Override
	public boolean hitEntity(ItemStack itemStack, LivingEntity target, LivingEntity attacker)
	{
		if(!attacker.world.isRemote && attacker instanceof PlayerEntity)
		{
			attacker.getHeldItem(attacker.getActiveHand()).damageItem(2, attacker, (holder) -> 
			{
				holder.sendBreakAnimation(EquipmentSlotType.MAINHAND);
			});
			
			attacker.attackEntityFrom(DamageSource.GENERIC, Float.MAX_VALUE);
			target.attackEntityFrom(DamageSource.GENERIC, Float.MAX_VALUE);
				
	    	if(!((PlayerEntity) attacker).isCreative())
	    	{
	    		WyTelemetry.addMiscStat("rejectDialsUsed", "Reject Dials Used", 1);
	    		attacker.getHeldItem(attacker.getActiveHand()).shrink(1);
	    		attacker.sendBreakAnimation(EquipmentSlotType.MAINHAND);
			}	
	    	
			return true;   
		}
		
		return false;
	}

	@Override
	protected boolean onBlockPlaced(BlockPos pos, World world, @Nullable PlayerEntity player, ItemStack itemStack, BlockState state)
	{
    	if(!player.isCreative())
    		WyTelemetry.addMiscStat("rejectDialsPlaced", "Reject Dials Placed", 1);
		return super.onBlockPlaced(pos, world, player, itemStack, state);
	}
}
