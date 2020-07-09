package xyz.pixelatedw.mineminenomi.quests.objectives.arrow;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.KujaArrowProjectile;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.wypi.quests.objectives.IHitEntityObjective;
import xyz.pixelatedw.wypi.quests.objectives.Objective;

public class ArrowHitObjective extends Objective implements IHitEntityObjective
{	
	public ArrowHitObjective(String title, int count)
	{
		super(title);
		this.setMaxProgress(count);
	}

	@Override
	public boolean checkHit(PlayerEntity player, LivingEntity target, DamageSource source)
	{		
		ItemStack heldItem = player.getHeldItemMainhand();

		boolean bowFlag = ItemsHelper.isBow(heldItem);
		boolean arrowFlag = source.getImmediateSource() instanceof ArrowEntity || source.getImmediateSource() instanceof KujaArrowProjectile || source.getImmediateSource() instanceof AbilityProjectileEntity;
		
		return bowFlag && arrowFlag;
	}

}
