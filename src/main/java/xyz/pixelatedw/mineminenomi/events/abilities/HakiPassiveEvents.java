package xyz.pixelatedw.mineminenomi.events.abilities;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.Env;
import xyz.pixelatedw.mineminenomi.api.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.api.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;

@Mod.EventBusSubscriber(modid = Env.PROJECT_ID)
public class HakiPassiveEvents
{
	@SubscribeEvent
	public static void onEntityAttack(LivingHurtEvent event)
	{
		if (!(event.getSource().getTrueSource() instanceof PlayerEntity))
			return;

		PlayerEntity attacker = (PlayerEntity) event.getSource().getTrueSource();
		IDevilFruit devilFruitProps = DevilFruitCapability.get(attacker);
		IEntityStats statProps = EntityStatsCapability.get(attacker);
		IAbilityData abilityProps = AbilityDataCapability.get(attacker);
		LivingEntity attacked = event.getEntityLiving();
		IEntityStats statPropz = EntityStatsCapability.get(attacked);

		/*if (abilityProps.isPassiveActive(ModAttributes.BUSOSHOKU_HAKI))
		{
			int powerDifference = statProps.getDoriki() - statPropz.getDoriki();
			float damageFromDoriki = 2;
			if(powerDifference > 0)
				damageFromDoriki = (float) (Math.sqrt(powerDifference) / 2);
			event.setAmount(damageFromDoriki);
		}*/
	}
}
