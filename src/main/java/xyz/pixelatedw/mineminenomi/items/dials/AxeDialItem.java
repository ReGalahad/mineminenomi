package xyz.pixelatedw.mineminenomi.items.dials;

import javax.annotation.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.AxeDialProjectile;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;

public class AxeDialItem extends BlockItem
{

	public AxeDialItem()
	{
		super(ModBlocks.AXE_DIAL, new Properties().group(ModCreativeTabs.MISC).maxStackSize(16));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
	{
		if (!world.isRemote)
		{
			AxeDialProjectile proj = new AxeDialProjectile(player.world, player);

			world.addEntity(proj);
    		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);

			if (!player.isCreative())
			{
				player.getHeldItem(hand).shrink(1);
				player.sendBreakAnimation(EquipmentSlotType.MAINHAND);
			}
		}

		return new ActionResult<>(ActionResultType.SUCCESS, player.getHeldItem(hand));
	}

	@Override
	protected boolean onBlockPlaced(BlockPos pos, World world, @Nullable PlayerEntity player, ItemStack itemStack, BlockState state)
	{
		return super.onBlockPlaced(pos, world, player, itemStack, state);
	}
}
