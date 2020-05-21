package xyz.pixelatedw.mineminenomi.items;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.packets.server.SEntityStatsSyncPacket;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.network.WyNetwork;

public class HeartItem extends Item
{

	public HeartItem()
	{
		super(new Properties().maxStackSize(1));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
	{
		ItemStack itemStack = player.getHeldItem(hand);
		
		if(!(world.getEntityByID(itemStack.getOrCreateTag().getInt("owner")) instanceof LivingEntity))
			player.inventory.deleteStack(itemStack);
		
		LivingEntity owner = this.getOwner(world, player.getPosition(), itemStack);
		if (itemStack.getTag() != null && owner != null)
		{
			if (owner == player)
			{
				IEntityStats props = EntityStatsCapability.get(player);
				props.setHeart(true);
				WyNetwork.sendToServer(new SEntityStatsSyncPacket(player.getEntityId(), props));
				player.inventory.deleteStack(itemStack);
			}
			else
			{
				owner.attackEntityFrom(DamageSource.MAGIC, 5);
				owner.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 250, 1));
				owner.addPotionEffect(new EffectInstance(Effects.NAUSEA, 250, 1));
				if (owner.getHealth() <= 0)
					player.inventory.deleteStack(itemStack);
			}
		}
		else
		{
			player.inventory.deleteStack(itemStack);
		}

		return new ActionResult<>(ActionResultType.SUCCESS, player.getHeldItem(hand));
	}

	public LivingEntity getOwner(World world, BlockPos pos, ItemStack itemStack)
	{
		LivingEntity entity = (LivingEntity) world.getEntityByID(itemStack.getTag().getInt("owner"));

		if (entity == null)
		{
			List<LivingEntity> list = WyHelper.getEntitiesNear(pos, world, 256);
			for (LivingEntity e : list)
			{
				if (e.getUniqueID().equals(itemStack.getTag().getUniqueId("ownerUUID")))
				{
					itemStack.getTag().putInt("owner", e.getEntityId());
					return e;
				}
			}
		}

		return entity;
	}

	@Override
	public boolean onEntityItemUpdate(ItemStack itemStack, ItemEntity entityItem)
	{
		if (itemStack.getTag() != null)
		{
			LivingEntity target = ((LivingEntity) entityItem.getEntityWorld().getEntityByID(itemStack.getTag().getInt("owner")));

			boolean isBurning = target != null && entityItem.isBurning();

			if (isBurning)
				target.attackEntityFrom(DamageSource.MAGIC, Float.MAX_VALUE);
		}

		return false;
	}

	@Override
	public void addInformation(ItemStack itemStack, @Nullable World world, List<ITextComponent> list, ITooltipFlag flag)
	{
		if (itemStack.getTag() != null)
		{
			if (world.getEntityByID(itemStack.getTag().getInt("owner")) != null)
			{
				LivingEntity entity = (LivingEntity) world.getEntityByID(itemStack.getTag().getInt("owner"));

				list.add(new StringTextComponent(TextFormatting.GOLD + "[Owner] " + TextFormatting.RESET + entity.getName().getFormattedText()));
				list.add(new StringTextComponent(TextFormatting.GOLD + "[HP] " + TextFormatting.RESET + entity.getHealth()));
				list.add(new StringTextComponent(TextFormatting.GOLD + "[Location] " + TextFormatting.RESET + (int) entity.posX + "X " + (int) entity.posY + "Y " + (int) entity.posZ + "Z"));
			}
			else
				list.add(new StringTextComponent(TextFormatting.GOLD + itemStack.getDisplayName().getFormattedText().toString().replace("'s Heart", "") + " is dead !"));
		}
		else
			list.add(new StringTextComponent(TextFormatting.RED + "U JUST GOT BAMBOOZLED!"));
	}

	public void setHeartOwner(ItemStack itemStack, LivingEntity e)
	{
		itemStack.setTag(new CompoundNBT());
		itemStack.getTag().putInt("owner", e.getEntityId());
		itemStack.getTag().putUniqueId("ownerUUID", e.getUniqueID());

	}
}
