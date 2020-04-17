package xyz.pixelatedw.mineminenomi.abilities.baku;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class BeroCannonAbility extends Ability
{
	public static final BeroCannonAbility INSTANCE = new BeroCannonAbility();
	
	public BeroCannonAbility()
	{
		super("Baro Cannon", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(5);
		this.setDescription("Transforms the user's tongue into a cannon and fires a random block from their inventory.");

		this.onUseEvent = this::onUseEvent;
	}
	
	private boolean onUseEvent(PlayerEntity player)
	{
		/*
		List<ItemStack> projectiles = new ArrayList<ItemStack>();
		for (ItemStack item : player.inventory.mainInventory)
		{
			if (item != null && item.getItem() instanceof BlockItem && Arrays.stream(bakuPermittedBlocks).anyMatch(p -> p == ((BlockItem) item.getItem()).getBlock()))
				projectiles.add(item);
		}
		if (!projectiles.isEmpty())
		{
			//this.projectile = new BeroCannonAbility.BeroCannon(player.world, player, attr);
			ItemStack s = projectiles.stream().findFirst().orElse(null);
			if (s.getCount() > 1)
				s.setCount(s.getCount() - 1);
			else
				player.inventory.deleteStack(s);
			super.use(player);
		}
		else
			WyHelper.sendMsgToPlayer(player, "You don't have any blocks to use");
		 */
		
		return true;
	}
}
