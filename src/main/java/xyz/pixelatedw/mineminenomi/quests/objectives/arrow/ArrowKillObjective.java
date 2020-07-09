package xyz.pixelatedw.mineminenomi.quests.objectives.arrow;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.KujaArrowProjectile;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.wypi.quests.objectives.IKillEntityObjective;
import xyz.pixelatedw.wypi.quests.objectives.Objective;

public class ArrowKillObjective extends Objective implements IKillEntityObjective
{	
	public ArrowKillObjective(String title, int count)
	{
		super(title);
		this.setMaxProgress(count);
	}

	@Override
	public boolean checkKill(PlayerEntity player, LivingEntity target, DamageSource source)
	{
		ItemStack heldItem = player.getHeldItemMainhand();

		boolean bowFlag = ItemsHelper.isBow(heldItem);
		boolean arrowFlag = source.getImmediateSource() instanceof ArrowEntity || source.getImmediateSource() instanceof KujaArrowProjectile || source.getImmediateSource() instanceof AbilityProjectileEntity;
		
		IAttributeInstance attackAttribute = target.getAttributes().getAttributeInstance(SharedMonsterAttributes.ATTACK_DAMAGE);
		boolean isAggressive = attackAttribute != null && attackAttribute.getValue() > 0;
		
		return bowFlag && arrowFlag && isAggressive;
	}

}
