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
import xyz.pixelatedw.mineminenomi.api.telemetry.WyTelemetry;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.ExtraProjectiles.AxeDialProjectile;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
import xyz.pixelatedw.mineminenomi.init.ModExtraAttributes;

public class AxeDialItem extends BlockItem
{

	public AxeDialItem()
	{
		super(ModBlocks.axeDialBlock, new Properties().group(ModCreativeTabs.MISC).maxStackSize(16));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
	{
		if (!world.isRemote)
		{
			AxeDialProjectile proj = new AxeDialProjectile(player.world, player, ModExtraAttributes.DIAL_AXE);

			world.addEntity(proj);

			if (!player.isCreative())
			{
				WyTelemetry.addMiscStat("axeDialsUsed", "Axe Dials Used", 1);
				player.getHeldItem(hand).shrink(1);
				player.sendBreakAnimation(EquipmentSlotType.MAINHAND);
			}
		}

		return new ActionResult<>(ActionResultType.SUCCESS, player.getHeldItem(hand));
	}

	@Override
	protected boolean onBlockPlaced(BlockPos pos, World world, @Nullable PlayerEntity player, ItemStack itemStack, BlockState state)
	{
		if (!player.isCreative())
			WyTelemetry.addMiscStat("axeDialsPlaced", "Axe Dials Placed", 1);
		return super.onBlockPlaced(pos, world, player, itemStack, state);
	}
}
