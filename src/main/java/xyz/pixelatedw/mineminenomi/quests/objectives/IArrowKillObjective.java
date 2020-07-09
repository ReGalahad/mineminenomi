package xyz.pixelatedw.mineminenomi.quests.objectives;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.entities.mobs.quest.objectives.SniperTargetEntity;
import xyz.pixelatedw.wypi.quests.objectives.IKillEntityObjective;

public interface IArrowKillObjective extends IKillEntityObjective
{
	public default boolean checkArrowKill(PlayerEntity player, LivingEntity target, DamageSource source)
	{
		ItemStack heldItem = player.getHeldItemMainhand();

		boolean bowFlag = ItemsHelper.isBow(heldItem);
		boolean arrowFlag = true;//source.getImmediateSource() instanceof ArrowEntity || source.getImmediateSource() instanceof KujaArrowProjectile || source.getImmediateSource() instanceof AbilityProjectileEntity;
		boolean isAggressive = true;
		
		if(!(target instanceof SniperTargetEntity))
		{
			IAttributeInstance attackAttribute = target.getAttributes().getAttributeInstance(SharedMonsterAttributes.ATTACK_DAMAGE);
			isAggressive = attackAttribute != null && attackAttribute.getValue() > 0;
		}
		
		return bowFlag && arrowFlag && isAggressive;
	}
}
