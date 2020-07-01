package xyz.pixelatedw.mineminenomi.items.weapons;

import java.util.List;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.abilities.bomu.BreezeBreathBombAbility;
import xyz.pixelatedw.mineminenomi.entities.projectiles.bomu.BreezeBreathBombProjectile;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.KairosekiBulletProjectile;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.NormalBulletProjectile;
import xyz.pixelatedw.mineminenomi.init.ModItems;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

public class GunItem extends RangedWeaponItem
{
	private int maxCooldown = 15;
	private int bulletSpeed = 2;
	private int bulletAccuracy = 2;
	private int maxBullets = 10;

	public static final Predicate<ItemStack> BULLETS = (itemStack) ->
	{
		return itemStack.getItem() == ModItems.BULLET || itemStack.getItem() == ModItems.KAIROSEKI_BULLET;
	};

	public GunItem(int maxCooldown, int bulletSpeed, int bulletAccuracy, float weightScale)
	{
		super(weightScale);
		this.maxCooldown = maxCooldown;
		this.bulletSpeed = bulletSpeed;
		this.bulletAccuracy = bulletAccuracy;
		this.maxBullets = (int) Math.ceil(15 - (weightScale * 5));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
	{
		IAbilityData abilityProps = AbilityDataCapability.get(player);
		BreezeBreathBombAbility ability = abilityProps.getEquippedAbility(BreezeBreathBombAbility.INSTANCE);
		boolean hasAbility = ability != null && ability.isContinuous();
		boolean hasBullets = this.findBulletStack(player) != null ? true : false;
		Item bulletType = this.findBulletStack(player) != null ? this.findBulletStack(player).getItem() : null;
		ItemStack heldItemStack = player.getHeldItem(hand);
		
		if(hasAbility)
		{
			player.setActiveHand(hand);
			return new ActionResult<>(ActionResultType.SUCCESS, heldItemStack);
		}
		
		if (!hasBullets || bulletType == null) {
			return new ActionResult<>(ActionResultType.FAIL, heldItemStack);
		}

		boolean hasGunPowder = this.getLoadedGunPowder(heldItemStack) > 0;
		
		boolean loadedBullets = this.getLoadedBullets(heldItemStack) > 0;
		if(!loadedBullets) {
			player.getCooldownTracker().setCooldown(this, 80);
			this.reloadBullets(heldItemStack, player);
			return new ActionResult<>(ActionResultType.FAIL, heldItemStack);
		} 

		if(!hasGunPowder)
		{
			for (int i = 0; i < player.inventory.getSizeInventory(); ++i)
			{
				ItemStack stack = player.inventory.getStackInSlot(i);
				if (stack.getItem() == Items.GUNPOWDER)
				{
					int count = 3;
					if (stack.getCount() < 3)
						count = stack.getCount();

					this.setLoadedGunPowder(heldItemStack, count);
					player.inventory.decrStackSize(i, count);
					hasGunPowder = true;
					break;
				}
			}
		}
		
		if(!hasGunPowder) {
			return new ActionResult<>(ActionResultType.FAIL, heldItemStack);
		}
		
		player.setActiveHand(hand);
		return new ActionResult<>(ActionResultType.SUCCESS, heldItemStack);
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack itemStack, World world, LivingEntity entityLiving, int timeLeft)
	{
		if (!(entityLiving instanceof PlayerEntity))
			return;
		
		PlayerEntity player = (PlayerEntity) entityLiving;
		Item bulletType = this.findBulletStack(player) != null ? this.findBulletStack(player).getItem() : null;		
		IAbilityData abilityProps = AbilityDataCapability.get(player);
		BreezeBreathBombAbility ability = abilityProps.getEquippedAbility(BreezeBreathBombAbility.INSTANCE);
		boolean hasAbility = ability != null && ability.isContinuous();

		
		if (!player.world.isRemote && hasAbility)
		{
			ability.stopContinuity(player);
			BreezeBreathBombProjectile proj = new BreezeBreathBombProjectile(player.world, player);
			player.world.addEntity(proj);
			proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);
			return;
		}
		
		int powder = this.getLoadedGunPowder(itemStack);
		if (!world.isRemote)
		{
			this.decrementBullets(itemStack);
			AbilityProjectileEntity proj = null;
			if (bulletType == ModItems.BULLET)
				proj = new NormalBulletProjectile(player.world, player, this.weightScale);
			else if (bulletType == ModItems.KAIROSEKI_BULLET)
				proj = new KairosekiBulletProjectile(player.world, player, this.weightScale);
			proj.setDamage((float) (proj.getDamage() * this.weightScale));
			player.world.addEntity(proj);
			proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, this.bulletSpeed, this.bulletAccuracy);
		
			player.getCooldownTracker().setCooldown(this, (int) (10 * this.weightScale));
			if(!player.abilities.isCreativeMode) {
				itemStack.attemptDamageItem((int) ((Math.round(this.weightScale * 10) - 10) / 2), world.rand, (ServerPlayerEntity) player);

			}
		}

		this.setLoadedGunPowder(itemStack, --powder);
	}
	public void setLoadedGunPowder(ItemStack itemStack, int powder)
	{
		itemStack.getOrCreateTag().putInt("gunPowder", powder);
	}
	
	public int getLoadedGunPowder(ItemStack itemStack)
	{
		return itemStack.getOrCreateTag().getInt("gunPowder");
	}

	public int getLoadedBullets(ItemStack stack) {
		return stack.getOrCreateTag().getInt("bulletcount");
	}
	public void decrementBullets(ItemStack stack) {
		stack.getOrCreateTag().putInt("bulletcount", stack.getOrCreateTag().getInt("bulletcount") -1);
	}
	public void reloadBullets(ItemStack stack, PlayerEntity player) {
		int count = maxBullets;
		for (int i = 0; i < player.inventory.getSizeInventory(); ++i)
			
		{
			ItemStack itemstack = player.inventory.getStackInSlot(i);
			if (BULLETS.test(itemstack))
			{
				if(itemstack.getCount() >= maxBullets) {
					player.inventory.decrStackSize(i, maxBullets);
				} else {
					count = itemstack.getCount();
					player.inventory.removeStackFromSlot(i);
				}
				break;
			}
		}
		stack.getOrCreateTag().putInt("bulletcount", count);


	}
	public ItemStack findBulletStack(PlayerEntity player)
	{
		for (int i = 0; i < player.inventory.getSizeInventory(); ++i)
		{
			ItemStack stack = player.inventory.getStackInSlot(i);
			if (BULLETS.test(stack))
			{
				return stack;
			}
		}
		
		return null;
	}
	

	@Override
	public UseAction getUseAction(ItemStack stack)
	{
		return UseAction.BOW;
	}

	@Override
	public void addInformation(ItemStack itemStack, @Nullable World world, List<ITextComponent> list, ITooltipFlag par4)
	{
		if (itemStack.hasTag())
		{
			list.add(new StringTextComponent("Gun Powder : " + itemStack.getTag().getInt("gunPowder")));
		}
	}

	@Override
	Item getBulletItem() {
		return null;
	}

	@Override
	AbilityProjectileEntity getProjectile(World w, LivingEntity e) {
		return null;
	}

}
