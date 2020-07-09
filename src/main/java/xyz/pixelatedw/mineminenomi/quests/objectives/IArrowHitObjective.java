package xyz.pixelatedw.mineminenomi.quests.objectives;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.KujaArrowProjectile;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.wypi.quests.objectives.IHitEntityObjective;

public interface IArrowHitObjective extends IHitEntityObjective
{
	public default boolean checkArrowHit(PlayerEntity player, LivingEntity target, DamageSource source)
	{		
		ItemStack heldItem = player.getHeldItemMainhand();

		boolean bowFlag = ItemsHelper.isBow(heldItem);
		boolean arrowFlag = source.getImmediateSource() instanceof ArrowEntity || source.getImmediateSource() instanceof KujaArrowProjectile || source.getImmediateSource() instanceof AbilityProjectileEntity;
		
		return bowFlag && arrowFlag;
	}
}
