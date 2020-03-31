package xyz.pixelatedw.wypi;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.AbilityDataProvider;
import xyz.pixelatedw.wypi.network.WyNetwork;
import xyz.pixelatedw.wypi.network.packets.client.CSyncAbilityDataPacket;
import xyz.pixelatedw.wypi.network.packets.server.SSyncAbilityDataPacket;

public class APIDefaults
{	
	public static final String ABILITY_MESSAGE_EMPTY_STACK = WyRegistry.registerName("ability.item.message.empty_stack", "Cannot equip because it's an empty stack!");
	public static final String ABILITY_MESSAGE_ANOTHER_ITEM_IN_HAND = WyRegistry.registerName("ability.item.message.another_item_in_hand", "Cannot equip while holding another item in hand!");

	public static void initPackets()
	{
		// Client
		WyNetwork.registerPacket(CSyncAbilityDataPacket.class, CSyncAbilityDataPacket::encode, CSyncAbilityDataPacket::decode, CSyncAbilityDataPacket::handle);

		// Server
		WyNetwork.registerPacket(SSyncAbilityDataPacket.class, SSyncAbilityDataPacket::encode, SSyncAbilityDataPacket::decode, SSyncAbilityDataPacket::handle);
	}

	public static void initCapabilities()
	{
		AbilityDataCapability.register();
	}

	@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
	public static class Registry
	{
		@SubscribeEvent
		public static void attachCapabilities(AttachCapabilitiesEvent<Entity> event)
		{
			if (event.getObject() instanceof PlayerEntity)
			{
				event.addCapability(new ResourceLocation(APIConfig.PROJECT_ID, "ability_data"), new AbilityDataProvider());
			}
		}
	}
}
