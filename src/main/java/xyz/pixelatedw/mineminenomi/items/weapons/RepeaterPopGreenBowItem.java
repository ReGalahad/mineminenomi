package xyz.pixelatedw.mineminenomi.items.weapons;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class RepeaterPopGreenBowItem extends PopGreenBowItem {

	public RepeaterPopGreenBowItem(double weightScale) {
		super(weightScale);
		this.properties.remove(new ResourceLocation("pull"));
		this.addPropertyOverride(new ResourceLocation("pull"), (p_210310_0_, p_210310_1_, p_210310_2_) -> {
	         if (p_210310_2_ == null) {
	             return 0.0F;
	          } else {
	             return !(p_210310_2_.getActiveItemStack().getItem() instanceof BowItem) ? 0.0F : this.getCycleUseCount(p_210310_0_) / requiredTime;
	          }
	       });

	}

	@Override
	public void onUsingTick(ItemStack stack, LivingEntity player, int count) {
		if(player instanceof PlayerEntity) {
			PlayerEntity p = (PlayerEntity) player;
			if(!p.world.isRemote()) {
				if(this.getCycleUseCount(stack) >= requiredTime) {
					
				}
			}
		}
		super.onUsingTick(stack, player, count);
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack itemStack, World world, LivingEntity entityLiving, int timeLeft) {
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		playerIn.setActiveHand(handIn);
		ItemStack stack = playerIn.getHeldItem(handIn);
		return new ActionResult<>(ActionResultType.SUCCESS, stack);

	}

	public float getCycleUseCount(ItemStack stack) {
		return stack.getOrCreateTag().getFloat("cyclecount");
	}
	public void setCycleUseCount(ItemStack stack, float val) {
		stack.getOrCreateTag().putFloat("cyclecount", val);
	}
}
