package xyz.pixelatedw.mineminenomi.items.weapons;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public abstract class RangedWeaponItem extends BowItem{

	double weightScale = 1d;
	float requiredTime;
	public RangedWeaponItem(double weightScale) {
		super(new Properties().group(ModCreativeTabs.WEAPONS).maxStackSize(1));
		this.weightScale = weightScale;
		requiredTime = (float) (23 * weightScale);
		this.properties.remove(new ResourceLocation("pull"));
		this.addPropertyOverride(new ResourceLocation("pull"), (p_210310_0_, p_210310_1_, p_210310_2_) -> {
	         if (p_210310_2_ == null) {
	             return 0.0F;
	          } else {
	             return !(p_210310_2_.getActiveItemStack().getItem() instanceof BowItem) ? 0.0F : (float)(p_210310_0_.getUseDuration() - p_210310_2_.getItemInUseCount()) / requiredTime;
	          }
	       });
	}
	@Override
	public void onPlayerStoppedUsing(ItemStack itemStack, World world, LivingEntity entityLiving, int timeLeft)
	{
		
		if (entityLiving instanceof PlayerEntity)
		{
			PlayerEntity playerentity = (PlayerEntity) entityLiving;
			boolean flag = playerentity.abilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, itemStack) > 0;
			ItemStack arrowStack = playerentity.findAmmo(itemStack);

	         int i = this.getUseDuration(itemStack) - timeLeft;
	         int v = (int) (this.getUseDuration(itemStack) - requiredTime);
	         
	         i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(itemStack, world, playerentity, i, !itemStack.isEmpty() || flag);
	         if (timeLeft > v) return;

			if (!arrowStack.isEmpty() || flag)
			{
				if (arrowStack.isEmpty())
					arrowStack = new ItemStack(this.getBulletItem());
					
					

				float f = 1f;

					if (!world.isRemote)
					{
						AbilityProjectileEntity proj = getProjectile(world,entityLiving);
						proj.shoot(playerentity, playerentity.rotationPitch, playerentity.rotationYaw, 0.0F, f * 3.0F, 1.0F);

						if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, itemStack) > 0)
							proj.setFire(100);
						
						world.addEntity(proj);
						arrowStack.shrink(1);
					}

					world.playSound((PlayerEntity) null, playerentity.posX, playerentity.posY, playerentity.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
					playerentity.addStat(Stats.ITEM_USED.get(this));
					if(!world.isRemote) {
					itemStack.attemptDamageItem((int) ((Math.round(weightScale * 10) - 10) / 2), world.rand, (ServerPlayerEntity) playerentity);
					}
					}
			}
		
	}

	abstract Item getBulletItem();
    abstract AbilityProjectileEntity getProjectile(World w, LivingEntity e);

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {

    	System.out.println("right clicked");
    	return super.onItemRightClick(worldIn, playerIn, handIn);
    }

}
