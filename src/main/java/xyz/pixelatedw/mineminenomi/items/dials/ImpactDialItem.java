package xyz.pixelatedw.mineminenomi.items.dials;

import javax.annotation.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.telemetry.WyTelemetry;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;

public class ImpactDialItem extends BlockItem
{

	public ImpactDialItem()
	{
		super(ModBlocks.IMPACT_DIAL, new Properties().group(ModCreativeTabs.MISC).maxStackSize(16));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
	{
		if (!world.isRemote)
		{
			player.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 1, 100));
			world.createExplosion(player, player.posX, player.posY, player.posZ, 3, false, CommonConfig.instance.isGriefingEnabled() ? Explosion.Mode.DESTROY : Explosion.Mode.NONE);

			if (!player.isCreative())
			{
				WyTelemetry.addMiscStat("impactDialsUsed", "Impact Dials Used", 1);
				player.getHeldItem(hand).shrink(1);
				player.sendBreakAnimation(EquipmentSlotType.MAINHAND);
			}
		}
		
		return new ActionResult<>(ActionResultType.SUCCESS, player.getHeldItem(hand));
	}
	
	@Override
	protected boolean onBlockPlaced(BlockPos pos, World world, @Nullable PlayerEntity player, ItemStack itemStack, BlockState state)
	{
    	if(!player.isCreative())
    		WyTelemetry.addMiscStat("impactDialsPlaced", "Impact Dials Placed", 1);
		return super.onBlockPlaced(pos, world, player, itemStack, state);
	}
}
