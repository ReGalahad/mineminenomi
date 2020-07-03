package xyz.pixelatedw.mineminenomi.items.weapons;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.abilities.bomu.BreezeBreathBombAbility;
import xyz.pixelatedw.mineminenomi.entities.projectiles.bomu.BreezeBreathBombProjectile;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.KairosekiBulletProjectile;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.NormalBulletProjectile;
import xyz.pixelatedw.mineminenomi.init.ModItems;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

public class RepeaterGunItem extends GunItem{

	private int updateValue;
	public RepeaterGunItem(int maxCooldown, int bulletSpeed, int bulletAccuracy, float weightScale) {
		super(maxCooldown, bulletSpeed, bulletAccuracy, weightScale);
		this.updateValue = (int) ((weightScale + (weightScale / 2)) * 10);
	}

	@Override
	public void onUsingTick(ItemStack stack, LivingEntity player, int count) {
		if(count % this.updateValue == 0 && this.isBeingUsed(stack)) {

			PlayerEntity playerIn = (PlayerEntity) player;
			IAbilityData abilityProps = AbilityDataCapability.get(player);
			BreezeBreathBombAbility ability = abilityProps.getEquippedAbility(BreezeBreathBombAbility.INSTANCE);
			boolean hasAbility = ability != null && ability.isContinuous();
			boolean hasBullets = this.findBulletStack(playerIn) != null ? true : false;
			Item bulletType = this.findBulletStack(playerIn) != null ? this.findBulletStack(playerIn).getItem() : null;
			if(!player.world.isRemote()) {

			if(hasAbility)
			{

					ability.stopContinuity(playerIn);
					BreezeBreathBombProjectile proj = new BreezeBreathBombProjectile(player.world, player);
					player.world.addEntity(proj);
					proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);
					return;
						
				}
			
			if (!hasBullets || bulletType == null) {
				return;
			}
			}

			boolean hasGunPowder = this.getLoadedGunPowder(stack) > 0;
			
			boolean loadedBullets = this.getLoadedBullets(stack) > 0;
			if(!loadedBullets) {
				if(!player.world.isRemote()) {
				playerIn.getCooldownTracker().setCooldown(this, (int) (80 * (2 - this.weightScale)));
				} else {
					playerIn.getCooldownTracker().setCooldown(this, (int) (80 * (2 - this.weightScale) + 17));
				}
				if(!playerIn.world.isRemote()) {
				this.reloadBullets(stack, playerIn);
				
				this.setIsBeingUsed(stack, false);
				
				return;
				}
			} 
			

			if(!playerIn.world.isRemote()) {
			if(!hasGunPowder)
			{
				for (int i = 0; i < playerIn.inventory.getSizeInventory(); ++i)
				{
					ItemStack sstack = playerIn.inventory.getStackInSlot(i);
					if (sstack.getItem() == Items.GUNPOWDER)
					{
						int powderCount = 6;
						if (sstack.getCount() < 6)
							powderCount = sstack.getCount();

						this.setLoadedGunPowder(stack, powderCount);
						playerIn.inventory.decrStackSize(i, powderCount);
						hasGunPowder = true;
						break;
					}
				}
			}
			
			if(!hasGunPowder) {
				return;
			}
			
			
				this.decrementBullets(stack);
				AbilityProjectileEntity proj = null;
				if (bulletType == ModItems.BULLET)
					proj = new NormalBulletProjectile(player.world, player, this.weightScale);
				else if (bulletType == ModItems.KAIROSEKI_BULLET)
					proj = new KairosekiBulletProjectile(player.world, player, this.weightScale);
				proj.setDamage((float) (proj.getDamage() * this.weightScale));
			
				player.world.addEntity(proj);
				proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, this.bulletSpeed, this.bulletAccuracy);
				
				if(!playerIn.abilities.isCreativeMode) {
					stack.attemptDamageItem((int) ((Math.round(this.weightScale * 10) - 10) / 2), playerIn.world.rand, (ServerPlayerEntity) playerIn);

				
			}
			int powder = this.getLoadedGunPowder(stack);
			this.setLoadedGunPowder(stack, --powder);

			}
		
		}
		super.onUsingTick(stack, player, count);
		
	}

	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		
		super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {

		player.setActiveHand(hand);
		ItemStack stack = player.getHeldItem(hand);
		this.setIsBeingUsed(stack, true);
		return new ActionResult<>(ActionResultType.SUCCESS, stack);
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack itemStack, World world, LivingEntity entityLiving, int timeLeft) {
		this.setIsBeingUsed(itemStack, false);
	}

	public boolean isBeingUsed(ItemStack stack) {
		return stack.getOrCreateTag().getBoolean("beingused");
	}
	public void setIsBeingUsed(ItemStack stack, boolean val) {
		stack.getOrCreateTag().putBoolean("beingused", val);
	}
}
