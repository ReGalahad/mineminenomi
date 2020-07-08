package xyz.pixelatedw.mineminenomi.items.weapons;

import java.util.List;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import xyz.pixelatedw.mineminenomi.abilities.bomu.BreezeBreathBombAbility;
import xyz.pixelatedw.mineminenomi.entities.projectiles.bomu.BreezeBreathBombProjectile;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.KairosekiBulletProjectile;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.NormalBulletProjectile;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
import xyz.pixelatedw.mineminenomi.init.ModItems;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

public class GunItem extends Item
{
	private int maxGunpowder = 3;
	private int bulletSpeed = 2;
	private int bulletAccuracy = 2;
	private int shotCooldown = 10;
	private int reloadCooldown = 20;
	private float damageMultiplier = 1.0F;

	public static final Predicate<ItemStack> BULLETS = (itemStack) ->
	{
		return itemStack.getItem() == ModItems.BULLET || itemStack.getItem() == ModItems.KAIROSEKI_BULLET;
	};

	public GunItem(int maxDamage)
	{
		super(new Properties().group(ModCreativeTabs.WEAPONS).maxStackSize(1).defaultMaxDamage(maxDamage));
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
		
		if (!hasBullets || bulletType == null)
			return new ActionResult<>(ActionResultType.FAIL, heldItemStack);

		boolean hasGunPowder = this.getLoadedGunPowder(heldItemStack) > 0;
		
		if(!hasGunPowder)
		{
			for (int i = 0; i < player.inventory.getSizeInventory(); ++i)
			{
				ItemStack stack = player.inventory.getStackInSlot(i);
				if (stack.getItem() == Items.GUNPOWDER)
				{
					int count = this.maxGunpowder;
					if (stack.getCount() < count)
						count = stack.getCount();

					this.setLoadedGunPowder(heldItemStack, count);
					player.inventory.decrStackSize(i, count);
					hasGunPowder = true;
					player.getCooldownTracker().setCooldown(this, this.reloadCooldown);
					break;
				}
			}		
		}
		
		if(!hasGunPowder)
			return new ActionResult<>(ActionResultType.FAIL, heldItemStack);
		
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
			boolean flag = player.abilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, itemStack) > 0;
			int i = this.getUseDuration(itemStack) - timeLeft;
			i = ForgeEventFactory.onArrowLoose(itemStack, world, player, i, !itemStack.isEmpty() || flag);
			if(i < 0)
				return;
			
			AbilityProjectileEntity proj = null;
			if (bulletType == ModItems.BULLET)
				proj = new NormalBulletProjectile(player.world, player);
			else if (bulletType == ModItems.KAIROSEKI_BULLET)
				proj = new KairosekiBulletProjectile(player.world, player);
			
			int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, itemStack);
			if (j > 0)
				proj.setDamage((float) (proj.getDamage() + j * 0.5D + 0.5D));

			int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, itemStack);
			if (k > 0)
				proj.setKnockbackStrength(k);
			
			if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, itemStack) > 0)
				proj.setFire(100);
			
			proj.setDamage(Math.round(proj.getDamage() * this.damageMultiplier));
			player.world.addEntity(proj);
			proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, this.bulletSpeed, this.bulletAccuracy);
			itemStack.attemptDamageItem(1 + random.nextInt(2), random, (ServerPlayerEntity) player);
			player.addStat(Stats.ITEM_USED.get(this));
		}

		player.getCooldownTracker().setCooldown(this, this.shotCooldown);
		this.setLoadedGunPowder(itemStack, --powder);
		for (int i = 0; i < player.inventory.getSizeInventory(); ++i)
		{
			ItemStack stack = player.inventory.getStackInSlot(i);
			if (BULLETS.test(stack))
			{
				player.inventory.decrStackSize(i, 1);
				break;
			}
		}
	}

	@Override
	public void inventoryTick(ItemStack itemStack, World world, Entity entity, int itemSlot, boolean isSelected)
	{
		if (!itemStack.hasTag())
		{
			itemStack.setTag(new CompoundNBT());
			itemStack.getTag().putInt("gunPowder", 0);
		}
	}

	public void setLoadedGunPowder(ItemStack itemStack, int powder)
	{
		itemStack.getTag().putInt("gunPowder", powder);
	}
	
	public int getLoadedGunPowder(ItemStack itemStack)
	{
		return itemStack.getTag().getInt("gunPowder");
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
	
	public GunItem setGunpowderLimit(int limit)
	{
		this.maxGunpowder = limit;
		return this;
	}
	
	public GunItem setDamageMultiplier(float damage)
	{
		this.damageMultiplier = damage;
		return this;
	}
	
	public GunItem setShotCooldown(int cd)
	{
		this.shotCooldown = cd;
		return this;
	}
	
	public GunItem setReloadCooldown(int cd)
	{
		this.reloadCooldown = cd;
		return this;
	}
	
	public GunItem setBulletAccuracy(int acc)
	{
		this.bulletAccuracy = acc;
		return this;
	}
	
	public GunItem setBulletSpeed(int speed)
	{
		this.bulletSpeed = speed;
		return this;
	}
	
	@Override
	public int getUseDuration(ItemStack stack)
	{
		return 72000;
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

}
