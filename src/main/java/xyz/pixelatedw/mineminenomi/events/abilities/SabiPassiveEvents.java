package xyz.pixelatedw.mineminenomi.events.abilities;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.IAbilityData;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.values.ModValuesEnv;

@Mod.EventBusSubscriber(modid = ModValuesEnv.PROJECT_ID)
public class SabiPassiveEvents
{
	@SubscribeEvent
	public static void onEntityAttack(LivingHurtEvent event)
	{
		if (!(event.getSource().getTrueSource() instanceof LivingEntity) || !(event.getEntityLiving() instanceof PlayerEntity))
			return;

		LivingEntity attacker = (LivingEntity) event.getSource().getTrueSource();
		PlayerEntity attacked = (PlayerEntity) event.getEntityLiving();
		IDevilFruit devilFruitProps = DevilFruitCapability.get(attacked);
		IEntityStats statProps = EntityStatsCapability.get(attacked);
		IAbilityData abilityProps = AbilityDataCapability.get(attacked);

		if (!devilFruitProps.getDevilFruit().equalsIgnoreCase("sabisabi"))
			return;

		if (attacker.getHeldItemMainhand() != null && ItemsHelper.isSword(attacker.getHeldItemMainhand()) && !attacker.world.isRemote)
		{
			event.setCanceled(true);
			attacker.getHeldItemMainhand().damageItem(50, attacker, (entity) -> {});
			if (attacker instanceof PlayerEntity && attacker.getHeldItemMainhand().getDamage() <= 0)
				WyHelper.removeStackFromInventory((PlayerEntity) attacker, attacker.getHeldItemMainhand());
			else if(!(attacker instanceof PlayerEntity) && attacker.getHeldItemMainhand().getDamage() <= 0)
				attacker.setItemStackToSlot(EquipmentSlotType.MAINHAND, ItemStack.EMPTY);
		}
	}
}
