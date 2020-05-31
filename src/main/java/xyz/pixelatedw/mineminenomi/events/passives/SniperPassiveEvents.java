package xyz.pixelatedw.mineminenomi.events.passives;

import java.util.List;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.abilities.ZoomAbility;
import xyz.pixelatedw.mineminenomi.abilities.sniper.KaenBoshiAbility;
import xyz.pixelatedw.mineminenomi.abilities.sniper.KemuriBoshiAbility;
import xyz.pixelatedw.mineminenomi.abilities.sniper.RenpatsuNamariBoshiAbility;
import xyz.pixelatedw.mineminenomi.abilities.sniper.SakuretsuSabotenBoshiAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.ISniperAbility;
import xyz.pixelatedw.mineminenomi.init.ModArmors;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.wypi.network.WyNetwork;
import xyz.pixelatedw.wypi.network.packets.client.CSyncAbilityDataPacket;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class SniperPassiveEvents
{
	private static final List<Ability> SNIPER_ABILITIES = Lists.newArrayList(KaenBoshiAbility.INSTANCE, KemuriBoshiAbility.INSTANCE, RenpatsuNamariBoshiAbility.INSTANCE, SakuretsuSabotenBoshiAbility.INSTANCE);

	@SubscribeEvent
	public static void onEntityShootProjectile(ArrowLooseEvent event)
	{
		if (event.getPlayer() != null)
		{
			IAbilityData props = AbilityDataCapability.get(event.getPlayer());
			
			SNIPER_ABILITIES.forEach(ability -> 
			{
				if(ability instanceof ISniperAbility && props.hasEquippedAbility(ability) && props.getEquippedAbility(ability).isContinuous())
				{			
					((ISniperAbility)props.getEquippedAbility(ability)).shoot(event.getPlayer());
					props.getEquippedAbility(ability).use(event.getPlayer());
					event.setCanceled(true);
				}
			});
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void updateZoom(FOVUpdateEvent event)
	{
		if(!Minecraft.getInstance().player.inventory.hasAny(ImmutableSet.of(ModArmors.SNIPER_GOGGLES)))
			return;
		
		PlayerEntity player = Minecraft.getInstance().player;		
		IAbilityData aprops = AbilityDataCapability.get(player);
		
		if(aprops.getEquippedAbility(ZoomAbility.INSTANCE) != null && player.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() == ModArmors.SNIPER_GOGGLES)
		{
			Ability ability = aprops.getEquippedAbility(ZoomAbility.INSTANCE);
			
			if(ability.isContinuous())
				event.setNewfov(0.01F);
		}
	}
	
	@SubscribeEvent
	public static void onEntityUpdate(LivingUpdateEvent event)
	{
		if (!(event.getEntityLiving() instanceof PlayerEntity))
			return;	
		
		PlayerEntity player = (PlayerEntity) event.getEntityLiving();
		IAbilityData aprops = AbilityDataCapability.get(player);
		
		if(player.inventory.armorInventory.get(3).getItem() != ModArmors.SNIPER_GOGGLES)
		{
			if(aprops.hasUnlockedAbility(ZoomAbility.INSTANCE))
			{
				aprops.getUnlockedAbility(ZoomAbility.INSTANCE).stopContinuity(player);
				aprops.removeUnlockedAbility(ZoomAbility.INSTANCE);
				WyNetwork.sendToServer(new CSyncAbilityDataPacket(aprops));
			}
			return;
		}
		
		if(!aprops.hasUnlockedAbility(ZoomAbility.INSTANCE))
			aprops.addUnlockedAbility(ZoomAbility.INSTANCE);
	}
}
