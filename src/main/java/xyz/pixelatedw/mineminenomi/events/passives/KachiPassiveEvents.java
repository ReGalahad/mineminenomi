package xyz.pixelatedw.mineminenomi.events.passives;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderSpecificHandEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.abilities.kachi.HotBoilingSpecialAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.MorphHelper;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID, value = Dist.CLIENT)
public class KachiPassiveEvents
{
	@SubscribeEvent
	public static void onHandRendering(RenderSpecificHandEvent event)
	{
		if(event.getHand() != Hand.MAIN_HAND)
			return;
		
		if(!event.getItemStack().isEmpty())
			return;	
		
		PlayerEntity player = Minecraft.getInstance().player;

		IDevilFruit devilFruitProps = DevilFruitCapability.get(player);
		IAbilityData abilityProps = AbilityDataCapability.get(player);

		if (!devilFruitProps.getDevilFruit().equalsIgnoreCase("kachi_kachi"))
			return;
		
		HotBoilingSpecialAbility ability = abilityProps.getEquippedAbility(HotBoilingSpecialAbility.INSTANCE);

		if (ability == null || !ability.isContinuous())
			return;

		event.setCanceled(true);
		MorphHelper.renderArmFirstPerson(event.getEquipProgress(), event.getSwingProgress(), HandSide.RIGHT, ModResources.HOT_BOILING_SPECIAL_ARM);
	}
}
