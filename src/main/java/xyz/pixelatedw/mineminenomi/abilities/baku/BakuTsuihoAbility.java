package xyz.pixelatedw.mineminenomi.abilities.baku;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.ChargeableAbility;

public class BakuTsuihoAbility extends ChargeableAbility
{
	public static final BakuTsuihoAbility INSTANCE = new BakuTsuihoAbility();
	
	private List<ItemStack> projectiles = new ArrayList<ItemStack>();
	private List<Block> loadedProjectiles = new ArrayList<Block>();

	public BakuTsuihoAbility()
	{
		super("Baku Tsuiho", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(10);
		this.setDescription("Allows the user to charge multiple blocks in their mouth and shoot them all at the same time");

		this.onStartChargingEvent = this::onStartChargingEvent;
		this.duringChargingEvent = this::duringChargingEvent;
		this.onEndChargingEvent = this::onEndChargingEvent;
	}
	
	private boolean onEndChargingEvent(PlayerEntity player)
	{
		/*
		for (int j = 0; j < this.loadedProjectiles.size(); j++)
		{
			AbilityProjectile proj = new BeroCannonAbility.BeroCannon(player.world, player, ModAttributes.BERO_CANNON);
			int distanceBetweenProjectiles = this.loadedProjectiles.size() / 7;
			proj.setLocationAndAngles(player.posX + WyHelper.randomWithRange(-distanceBetweenProjectiles, distanceBetweenProjectiles) + player.world.rand.nextDouble(), (player.posY + 0.3) + WyHelper.randomWithRange(0, distanceBetweenProjectiles) + player.world.rand.nextDouble(), player.posZ + WyHelper.randomWithRange(-distanceBetweenProjectiles, distanceBetweenProjectiles) + player.world.rand.nextDouble(), 0, 0);
			player.world.addEntity(proj);
			proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);
		}
		*/
		return true;
	}
	
	private void duringChargingEvent(PlayerEntity player, int chargeTime)
	{
		if (!this.projectiles.isEmpty())
		{
			if (chargeTime % 20 == 0)
			{
				ItemStack s = this.projectiles.stream().findAny().orElse(null);
				if (s != null)
				{
					if (s.getCount() > 1)
						s.setCount(s.getCount() - 1);
					else
					{
						player.inventory.deleteStack(s);
						this.projectiles.remove(s);
					}
					this.loadedProjectiles.add(((BlockItem) s.getItem()).getBlock());
				}
			}
		}
	}
	
	private boolean onStartChargingEvent(PlayerEntity player)
	{
		/*
		this.loadedProjectiles.clear();
		this.projectiles.clear();
		for (ItemStack item : player.inventory.mainInventory)
		{
			if (item != null && item.getItem() instanceof BlockItem && Arrays.stream(bakuPermittedBlocks).anyMatch(p -> p == ((BlockItem) item.getItem()).getBlock()))
				this.projectiles.add(item);
		}
		
		if (!this.projectiles.isEmpty())
			return true;

		WyHelper.sendMsgToPlayer(player, "You don't have any blocks to use");
		*/
		return false;
	}
}
