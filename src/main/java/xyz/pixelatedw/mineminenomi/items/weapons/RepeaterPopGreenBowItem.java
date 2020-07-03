package xyz.pixelatedw.mineminenomi.items.weapons;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class RepeaterPopGreenBowItem extends PopGreenBowItem {

	public RepeaterPopGreenBowItem(double weightScale) {
		super(weightScale);
		requiredTime = (float) (30 * weightScale);
		this.properties.remove(new ResourceLocation("pull"));
		this.addPropertyOverride(new ResourceLocation("pull"), (p_210310_0_, p_210310_1_, p_210310_2_) -> {
	         if (p_210310_2_ == null) {
	             return 0.0F;
	          } else {
	        	  float val1 = p_210310_0_.getUseDuration() - p_210310_2_.getItemInUseCount();
	        	  float val2 = (val1 % requiredTime) + 1;
	        	  float val = val2 / requiredTime;
	             return !(p_210310_2_.getActiveItemStack().getItem() instanceof BowItem) ? 0.0F : val;
	          }
	       });

	}

	@Override
	public void onUsingTick(ItemStack stack, LivingEntity player, int count) {
		if(player instanceof PlayerEntity) {
			PlayerEntity p = (PlayerEntity) player;
      	  float val = count  % requiredTime;
				if(val >= requiredTime - 1 && this.isBeingUsed(stack)) {
					boolean flag = p.abilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;

					ItemStack arrowStack = p.findAmmo(stack);
					if (!arrowStack.isEmpty() || flag)
					{
					 if(!p.world.isRemote()) {
						if (arrowStack.isEmpty())
							arrowStack = new ItemStack(this.getBulletItem());

					AbilityProjectileEntity proj = getProjectile(player.world,player);
					
					proj.shoot(p, p.rotationPitch, p.rotationYaw, 0.0F,  3.0F, 1.0F);

					if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0)
						proj.setFire(100);
					p.world.addEntity(proj);
					arrowStack.shrink(1);
					p.world.playSound((PlayerEntity) null, p.posX, p.posY, p.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + 0.5F);
					}
					p.addStat(Stats.ITEM_USED.get(this));

					

					}
				} 
			}
		
		super.onUsingTick(stack, player, count);
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack itemStack, World world, LivingEntity entityLiving, int timeLeft) {
	this.setIsBeingUsed(itemStack, false);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		playerIn.setActiveHand(handIn);
		ItemStack stack = playerIn.getHeldItem(handIn);
		ItemStack arrowStack = playerIn.findAmmo(stack);
		if(!arrowStack.isEmpty()) {
			this.setIsBeingUsed(stack, true);
			return new ActionResult<>(ActionResultType.FAIL, stack); 
		} else {
			this.setIsBeingUsed(stack, false);

			return new ActionResult<>(ActionResultType.SUCCESS, stack);
		}


	}

	public float getCycleUseCount(ItemStack stack) {
		return stack.getOrCreateTag().getFloat("cyclecount");
	}
	public void setCycleUseCount(ItemStack stack, float val) {
		stack.getOrCreateTag().putFloat("cyclecount", val);
	}
	public boolean isBeingUsed(ItemStack stack) {
		return stack.getOrCreateTag().getBoolean("beingused");
	}
	public void setIsBeingUsed(ItemStack stack, boolean val) {
		stack.getOrCreateTag().putBoolean("beingused", val);
	}

}
