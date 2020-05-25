package xyz.pixelatedw.mineminenomi.events.passives;

import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.entities.zoan.BisonHeavyZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.BisonWalkZoanInfo;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class UshiBisonPassiveEvents
{
	@SubscribeEvent
	public static void onEntityUpdate(LivingUpdateEvent event)
	{
		if (!(event.getEntityLiving() instanceof PlayerEntity))
			return;

		PlayerEntity player = (PlayerEntity) event.getEntityLiving();
		IDevilFruit props = DevilFruitCapability.get(player);

		if (!props.getDevilFruit().equalsIgnoreCase("ushi_ushi_bison"))
			return;

		if (props.getZoanPoint().equalsIgnoreCase(BisonWalkZoanInfo.FORM))
		{

			Entity rayTracedEntity = WyHelper.rayTraceEntities(player, 1).getEntity();
			LivingEntity target = (rayTracedEntity != null && rayTracedEntity instanceof LivingEntity) ? (LivingEntity) rayTracedEntity : null;
			
			if(target != null)
			{
				Vec3d speed = WyHelper.propulsion(player, 2, 2);
	
				target.attackEntityFrom(DamageSource.causePlayerDamage(player), 2);
				target.setMotion(speed.x, 0.2, speed.z);
			}
		}
	}

	@SubscribeEvent
	public static void onEntityAttack(LivingHurtEvent event)
	{
		if (!(event.getSource().getTrueSource() instanceof PlayerEntity))
			return;

		PlayerEntity attacker = (PlayerEntity) event.getSource().getTrueSource();
		IDevilFruit props = DevilFruitCapability.get(attacker);

		if (!props.getDevilFruit().equalsIgnoreCase("ushi_ushi_bison"))
			return;

		if (props.getZoanPoint().equalsIgnoreCase(BisonHeavyZoanInfo.FORM))
		{
			event.setAmount(event.getAmount() + 3);
		}
	}

}
