package xyz.pixelatedw.mineminenomi.items.dials;

import javax.annotation.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.telemetry.WyTelemetry;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;

public class FlameDialItem extends BlockItem
{

	public FlameDialItem()
	{
		super(ModBlocks.flameDialBlock, new Properties().group(ModCreativeTabs.MISC).maxStackSize(16));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
	{
		if(!world.isRemote)
		{
	    	if(!player.isSneaking())
	    	{
				Vec3d look = player.getLookVec();
				SmallFireballEntity fireball = new SmallFireballEntity(world, player, 1, 1, 1);
				fireball.setPosition(
						player.posX,
						player.posY + 1.5, 
						player.posZ);
				fireball.accelerationX = look.x * 0.2;
				fireball.accelerationY = look.y * 0.2;
				fireball.accelerationZ = look.z * 0.2;
				world.addEntity(fireball);	
	    		
		    	if(!player.isCreative())
		    	{
		    		WyTelemetry.addMiscStat("flameDialsUsed", "Flame Dials Used", 1);
					player.getHeldItem(hand).shrink(1);
					player.sendBreakAnimation(EquipmentSlotType.MAINHAND);
				}
	    	}	    
		}
		
		return new ActionResult<>(ActionResultType.SUCCESS, player.getHeldItem(hand));
	}
	
	@Override
	protected boolean onBlockPlaced(BlockPos pos, World world, @Nullable PlayerEntity player, ItemStack itemStack, BlockState state)
	{
    	if(!player.isCreative())
    		WyTelemetry.addMiscStat("flameDialsPlaced", "Flame Dials Placed", 1);
		return super.onBlockPlaced(pos, world, player, itemStack, state);
	}
}
