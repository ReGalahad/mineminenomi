package xyz.pixelatedw.mineminenomi.items.weapons;

import java.util.function.Predicate;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.KujaArrowProjectile;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
import xyz.pixelatedw.mineminenomi.init.ModItems;

public class KujaBowItem extends BowItem
{

	public KujaBowItem()
	{
		super(new Properties().group(ModCreativeTabs.WEAPONS).maxStackSize(1));
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack itemStack, World world, LivingEntity entityLiving, int timeLeft)
	{
		if (entityLiving instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity) entityLiving;
			boolean flag = player.abilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, itemStack) > 0;
			ItemStack arrowStack = player.findAmmo(itemStack);

			int i = this.getUseDuration(itemStack) - timeLeft;
			i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(itemStack, world, player, i, !arrowStack.isEmpty() || flag);
			if (i < 0)
				return;

			if (!arrowStack.isEmpty() || flag)
			{
				if (arrowStack.isEmpty())
					arrowStack = new ItemStack(ModItems.KUJA_ARROW);

				float f = getArrowVelocity(i);
				if (f > 0.4F)
				{
					if (!world.isRemote)
					{
						KujaArrowProjectile proj = new KujaArrowProjectile(world, entityLiving);
						proj.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, f * 3.0F, 1.0F);

						if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, itemStack) > 0)
							proj.setFire(100);
						
						world.addEntity(proj);
						arrowStack.shrink(1);
						player.addStat(Stats.ITEM_USED.get(this));
						itemStack.attemptDamageItem(1 + random.nextInt(2), random, (ServerPlayerEntity) player);
					}

					world.playSound((PlayerEntity) null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
				}
			}
		}
	}

	@Override
	public Predicate<ItemStack> getInventoryAmmoPredicate()
	{
		return itemStack -> itemStack.getItem() == ModItems.KUJA_ARROW;
	}

}
