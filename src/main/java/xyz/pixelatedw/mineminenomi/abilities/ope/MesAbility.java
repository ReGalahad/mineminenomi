package xyz.pixelatedw.mineminenomi.abilities.ope;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.StringTextComponent;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModItems;
import xyz.pixelatedw.mineminenomi.items.HeartItem;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.PunchAbility;

public class MesAbility extends PunchAbility
{
	public static final Ability INSTANCE = new MesAbility();

	public MesAbility()
	{
		super("MES", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(10);
		this.setDescription("Removes the heart of the user's target which they can then damage to hurt the opponent");

		this.onHitEntityEvent = this::onHitEntity;
	}

	private float onHitEntity(PlayerEntity player, LivingEntity target)
	{
		IEntityStats targetProps = EntityStatsCapability.get(target);

		if (targetProps.hasHeart())
		{
			ItemStack heart = new ItemStack(ModItems.HEART);
			((HeartItem) heart.getItem()).setHeartOwner(heart, target);
			heart.setDisplayName(new StringTextComponent(target.getCommandSource().getName() + "'s Heart"));
			player.inventory.addItemStackToInventory(heart);
			targetProps.setHeart(false);
		}

		return 1;
	}
}
