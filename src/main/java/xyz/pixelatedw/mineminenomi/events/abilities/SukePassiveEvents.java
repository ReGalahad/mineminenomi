package xyz.pixelatedw.mineminenomi.events.abilities;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.Env;
import xyz.pixelatedw.mineminenomi.abilities.suke.SkattingAbility;
import xyz.pixelatedw.mineminenomi.api.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.api.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;

@Mod.EventBusSubscriber(modid = Env.PROJECT_ID)
public class SukePassiveEvents
{
	@SubscribeEvent
	public static void onEntityRendered(RenderLivingEvent.Pre event)
	{
		if (!(event.getEntity() instanceof PlayerEntity))
			return;

		PlayerEntity player = (PlayerEntity) event.getEntity();
		IDevilFruit devilFruitProps = DevilFruitCapability.get(player);
		IAbilityData abilityProps = AbilityDataCapability.get(player);

		if (!devilFruitProps.getDevilFruit().equalsIgnoreCase("suke_suke"))
			return;
		
		if(abilityProps.getAbility(SkattingAbility.INSTANCE).isContinuous())
			event.setCanceled(true);
	}
}
