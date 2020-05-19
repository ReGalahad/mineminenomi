package xyz.pixelatedw.mineminenomi.events.passives;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.abilities.ito.BlackKnightAbility;
import xyz.pixelatedw.mineminenomi.abilities.ito.KumoNoSugakiAbility;
import xyz.pixelatedw.mineminenomi.abilities.ito.SoraNoMichiAbility;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.entities.mobs.misc.BlackKnightEntity;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class ItoPassiveEvents
{
	@SubscribeEvent
	public static void onEntityUpdate(LivingUpdateEvent event)
	{
		if (!(event.getEntityLiving() instanceof PlayerEntity))
			return;	
		
		PlayerEntity player = (PlayerEntity) event.getEntityLiving();
		IDevilFruit devilProps = DevilFruitCapability.get(player);
		IAbilityData abilityProps = AbilityDataCapability.get(player);
		
		if (!devilProps.getDevilFruit().equalsIgnoreCase("ito_ito"))
			return;

		SoraNoMichiAbility soraNoMichiAbiltiy = abilityProps.getEquippedAbility(SoraNoMichiAbility.INSTANCE);
		boolean blockFallDamage = soraNoMichiAbiltiy != null && !soraNoMichiAbiltiy.hasFallDamage();
				
		if(blockFallDamage)
		{
			player.fallDistance = 0;
			if(!player.onGround && player.world.getBlockState(player.getPosition().down()).isSolid() && soraNoMichiAbiltiy.getCooldown() < soraNoMichiAbiltiy.getMaxCooldown() - 10)
				soraNoMichiAbiltiy.resetFallDamage();
		}
	}
	
	@SubscribeEvent
	public static void onEntityAttackEvent(LivingAttackEvent event)
	{	
		if (!(event.getEntityLiving() instanceof PlayerEntity))
			return;	
				
		PlayerEntity player = (PlayerEntity) event.getEntityLiving();
		IDevilFruit devilProps = DevilFruitCapability.get(player);
		IAbilityData abilityProps = AbilityDataCapability.get(player);
		
		if (!devilProps.getDevilFruit().equalsIgnoreCase("ito_ito"))
			return;
	
		KumoNoSugakiAbility ability = abilityProps.getEquippedAbility(KumoNoSugakiAbility.INSTANCE);
		boolean isActive = ability != null && ability.isContinuous();
		if(isActive)
			event.setCanceled(true);
	}
	

	@SubscribeEvent
	public static void onEntityAttack(LivingHurtEvent event)
	{
		if (!(event.getSource().getTrueSource() instanceof PlayerEntity))
			return;

		PlayerEntity attacker = (PlayerEntity) event.getSource().getTrueSource();
		IDevilFruit devilFruitProps = DevilFruitCapability.get(attacker);
		IAbilityData abilityProps = AbilityDataCapability.get(attacker);
		LivingEntity attacked = event.getEntityLiving();

		if (!devilFruitProps.getDevilFruit().equalsIgnoreCase("ito_ito"))
			return;

		BlackKnightAbility ability = abilityProps.getEquippedAbility(BlackKnightAbility.INSTANCE);
		boolean isActive = ability != null && ability.isContinuous();
		
		if(!isActive)
			return;
		
		BlackKnightEntity knight = WyHelper.getEntitiesNear(attacker.getPosition(), attacker.world, 20, BlackKnightEntity.class).stream().findFirst().orElse(null);

		if (knight != null && knight.getOwner() == attacker)
			knight.forcedTargets.add(attacked);
	}
	
	@SubscribeEvent
	public static void onEntityDamaged(LivingDamageEvent event)
	{
		if(!(event.getEntityLiving() instanceof BlackKnightEntity))
			return;
		
		BlackKnightEntity entity = (BlackKnightEntity) event.getEntityLiving();
		PlayerEntity owner = entity.getOwner();
		
		if(owner == null)
			return;
		
		IDevilFruit props = DevilFruitCapability.get(owner);
		
		if(!props.getDevilFruit().equalsIgnoreCase("ito_ito") || entity.getHealth() - event.getAmount() >= 0)
			return;
		
		IAbilityData abilityProps = AbilityDataCapability.get(owner);
		
		BlackKnightAbility ability = abilityProps.getEquippedAbility(BlackKnightAbility.INSTANCE);
		boolean isActive = ability != null && ability.isContinuous();
		
		if(!isActive)
			return;
		
		ability.setMaxCooldown(60);
		ability.stopContinuity(owner);
	}
}
