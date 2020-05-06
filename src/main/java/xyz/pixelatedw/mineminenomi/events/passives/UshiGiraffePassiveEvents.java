package xyz.pixelatedw.mineminenomi.events.passives;

import java.util.UUID;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.entities.zoan.GiraffeHeavyZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.GiraffeWalkZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.wypi.APIConfig;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class UshiGiraffePassiveEvents
{
	private static final AttributeModifier SPEED_MODIFIER = new AttributeModifier(UUID.fromString("d171ef28-e77a-418d-927b-ca9a09417189"), "Walk Point Speed Multiplier", 1.05, AttributeModifier.Operation.MULTIPLY_BASE);
	private static final AttributeModifier JUMP_MODIFIER = new AttributeModifier(UUID.fromString("13b3d607-ed90-4459-832c-01e616a5ef16"), "Walk Point Jump Multiplier", 3, AttributeModifier.Operation.ADDITION);

	@SubscribeEvent
	public static void onEntityUpdate(LivingUpdateEvent event)
	{
		if (!(event.getEntityLiving() instanceof PlayerEntity))
			return;

		PlayerEntity player = (PlayerEntity) event.getEntityLiving();
		IDevilFruit props = DevilFruitCapability.get(player);

		if (!props.getDevilFruit().equalsIgnoreCase("ushi_ushi_giraffe"))
			return;

		IAttributeInstance speedAttr = player.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
		IAttributeInstance jumpAttr = player.getAttribute(ModAttributes.JUMP_HEIGHT);
		if (props.getZoanPoint().equalsIgnoreCase(GiraffeWalkZoanInfo.FORM))
		{
			if (!speedAttr.hasModifier(SPEED_MODIFIER))
				speedAttr.applyModifier(SPEED_MODIFIER);

			if (!jumpAttr.hasModifier(JUMP_MODIFIER))
				jumpAttr.applyModifier(JUMP_MODIFIER);
		}
		else
		{
			speedAttr.removeModifier(SPEED_MODIFIER);
			jumpAttr.removeModifier(JUMP_MODIFIER);
		}
	}

	@SubscribeEvent
	public static void onEntityAttack(LivingHurtEvent event)
	{
		if (!(event.getSource().getTrueSource() instanceof PlayerEntity))
			return;

		PlayerEntity attacker = (PlayerEntity) event.getSource().getTrueSource();
		IDevilFruit props = DevilFruitCapability.get(attacker);

		if (!props.getDevilFruit().equalsIgnoreCase("ushi_ushi_giraffe"))
			return;

		if (props.getZoanPoint().equalsIgnoreCase(GiraffeHeavyZoanInfo.FORM))
		{
			event.setAmount(3);
		}
	}
}
