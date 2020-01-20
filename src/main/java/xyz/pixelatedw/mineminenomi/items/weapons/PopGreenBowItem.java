package xyz.pixelatedw.mineminenomi.items.weapons;

import java.util.function.Predicate;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.entities.projectiles.ExtraProjectiles;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
import xyz.pixelatedw.mineminenomi.init.ModExtraAttributes;
import xyz.pixelatedw.mineminenomi.init.ModItems;

public class PopGreenBowItem extends BowItem
{

	public PopGreenBowItem()
	{
		super(new Properties().group(ModCreativeTabs.WEAPONS).maxStackSize(1));
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
			i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(itemStack, world, playerentity, i, !arrowStack.isEmpty() || flag);
			if (i < 0)
				return;

			if (!arrowStack.isEmpty() || flag)
			{
				if (arrowStack.isEmpty())
					arrowStack = new ItemStack(ModItems.POP_GREEN);

				float f = getArrowVelocity(i);
				if (!(f < 0.1D))
				{
					if (!world.isRemote)
					{
						ExtraProjectiles.PopGreen proj = new ExtraProjectiles.PopGreen(world, entityLiving, ModExtraAttributes.POP_GREEN);
						proj.shoot(playerentity, playerentity.rotationPitch, playerentity.rotationYaw, 0.0F, f * 3.0F, 1.0F);

						if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, itemStack) > 0)
							proj.setFire(100);
						
						world.addEntity(proj);
						arrowStack.shrink(1);
					}

					world.playSound((PlayerEntity) null, playerentity.posX, playerentity.posY, playerentity.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
					playerentity.addStat(Stats.ITEM_USED.get(this));
				}
			}
		}
	}

	@Override
	public Predicate<ItemStack> getInventoryAmmoPredicate()
	{
		return itemStack -> itemStack.getItem() == ModItems.POP_GREEN;
	}

}
