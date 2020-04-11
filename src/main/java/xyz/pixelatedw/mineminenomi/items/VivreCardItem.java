package xyz.pixelatedw.mineminenomi.items;

import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.entities.VivreCardEntity;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;

public class VivreCardItem extends Item
{

	public VivreCardItem()
	{
		super(new Properties().group(ModCreativeTabs.MISC).maxStackSize(1));
	}

	@Override
	public boolean onDroppedByPlayer(ItemStack itemStack, PlayerEntity player)
	{
		VivreCardEntity vivreCard = new VivreCardEntity(player.world);

		if (itemStack.getTag() == null)
			return true;

		LivingEntity owner = (LivingEntity) ((ServerWorld) player.world).getEntityByUuid(UUID.fromString(itemStack.getTag().getString("ownerUUID")));

		if (owner == null)
			return true;
		vivreCard.setPositionAndRotation(player.posX, player.posY, player.posZ, player.rotationYaw, player.rotationPitch);

		float f8 = MathHelper.sin(player.rotationPitch * ((float) Math.PI / 180F));
		float f2 = MathHelper.cos(player.rotationPitch * ((float) Math.PI / 180F));
		float f3 = MathHelper.sin(player.rotationYaw * ((float) Math.PI / 180F));
		float f4 = MathHelper.cos(player.rotationYaw * ((float) Math.PI / 180F));
		float f5 = Item.random.nextFloat() * ((float) Math.PI * 2F);
		float f6 = 0.02F * Item.random.nextFloat();
		vivreCard.setMotion(-f3 * f2 * 0.3F + Math.cos(f5) * f6, -f8 * 0.3F + 0.1F + (Item.random.nextFloat() - Item.random.nextFloat()) * 0.1F, f4 * f2 * 0.3F + Math.sin(f5) * f6);
		
		vivreCard.setOwner(owner.getUniqueID());

		player.world.addEntity(vivreCard);

		itemStack.setCount(0);

		return false;
	}

	@Override
	public void inventoryTick(ItemStack itemStack, World world, Entity entity, int itemSlot, boolean isSelected)
	{
		if (world.isRemote)
			return;

		if (itemStack.getTag() != null)
		{
			LivingEntity owner = (LivingEntity) ((ServerWorld) world).getEntityByUuid(UUID.fromString(itemStack.getTag().getString("ownerUUID")));

			if (owner == null)
				itemStack.setCount(0);
		}
	}

	@Override
	public void onCreated(ItemStack itemStack, World world, PlayerEntity player)
	{
		this.setOwner(itemStack, player);
		String itemName = itemStack.getDisplayName().getFormattedText();
		itemStack.setDisplayName(new StringTextComponent(TextFormatting.RESET + player.getDisplayName().getFormattedText() + "'s " + itemName));
	}

	public void setOwner(ItemStack itemStack, LivingEntity e)
	{
		itemStack.setTag(new CompoundNBT());
		itemStack.getTag().putString("ownerUUID", e.getUniqueID().toString());
		itemStack.getTag().putString("ownerUsername", e.getDisplayName().getFormattedText());
		String itemName = itemStack.getDisplayName().getFormattedText();
		itemStack.setDisplayName(new StringTextComponent(TextFormatting.RESET + e.getDisplayName().getFormattedText() + "'s " + itemName));
	}
}
