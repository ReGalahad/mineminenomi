package xyz.pixelatedw.mineminenomi.abilities.baku;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.entities.projectiles.baku.BeroCannonProjectile;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;

public class BeroCannonAbility extends Ability
{
	public static final BeroCannonAbility INSTANCE = new BeroCannonAbility();
	
	public BeroCannonAbility()
	{
		super("Bero Cannon", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(5);
		this.setDescription("Transforms the user's tongue into a cannon and fires a random block from their inventory.");

		this.onUseEvent = this::onUseEvent;
	}
	
	private boolean onUseEvent(PlayerEntity player)
	{
		List<ItemStack> projectiles = new ArrayList<ItemStack>();
		for (ItemStack item : player.inventory.mainInventory)
		{
			if (item != null && item.getItem() instanceof BlockItem && BakuMunchAbility.GRIEF_RULE.getApprovedBlocks().stream().anyMatch(p -> p == ((BlockItem) item.getItem()).getBlock()))
			{
				projectiles.add(item);
				break;
			}
		}
		
		if (!projectiles.isEmpty())
		{
			BeroCannonProjectile proj = new BeroCannonProjectile(player.world, player);
			player.world.addEntity(proj);
			proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 0);
			ItemStack s = projectiles.stream().findFirst().orElse(null);
			if (s.getCount() > 1)
				s.setCount(s.getCount() - 1);
			else
				player.inventory.deleteStack(s);
			
			return true;
		}
		
		WyHelper.sendMsgToPlayer(player, new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NOT_ENOUGH_BLOCKS).getFormattedText());
		
		return false;
	}
}
