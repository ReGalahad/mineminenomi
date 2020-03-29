package xyz.pixelatedw.mineminenomi.events.passives;

import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel.ArmPose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.abilities.nikyu.UrsusShockAbility;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class NikyuPassiveEvents
{
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onEntityRendered(RenderLivingEvent event)
	{
		if (!(event.getEntity() instanceof PlayerEntity))
			return;

		PlayerEntity player = (PlayerEntity) event.getEntity();
		IDevilFruit devilFruitProps = DevilFruitCapability.get(player);

		if (!devilFruitProps.getDevilFruit().equalsIgnoreCase("nikyu_nikyu"))
			return;

		IAbilityData abilityProps = AbilityDataCapability.get(player);
		PlayerRenderer renderer = (PlayerRenderer) event.getRenderer();

		if (abilityProps.getEquippedAbility(UrsusShockAbility.INSTANCE).isCharging())
		{
			renderer.getEntityModel().rightArmPose = ArmPose.THROW_SPEAR;
		}
	}
}
