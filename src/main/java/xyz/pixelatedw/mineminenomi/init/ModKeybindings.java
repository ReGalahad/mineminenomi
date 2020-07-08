package xyz.pixelatedw.mineminenomi.init;

import org.lwjgl.glfw.GLFW;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent.KeyInputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.packets.client.CCombatModeTriggerPacket;
import xyz.pixelatedw.mineminenomi.packets.client.CUseAbilityPacket;
import xyz.pixelatedw.mineminenomi.screens.CharacterCreatorScreen;
import xyz.pixelatedw.mineminenomi.screens.PlayerStatsScreen;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.wypi.network.WyNetwork;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID, value = Dist.CLIENT)
public class ModKeybindings
{
	public static KeyBinding guiPlayer, enterCombatMode, combatSlot1, combatSlot2, combatSlot3, combatSlot4, combatSlot5, combatSlot6, combatSlot7, combatSlot8;

	private static KeyBinding[] keyBindsCombatbar;

	public static void init()
	{
		guiPlayer = new KeyBinding(ModI18n.KEY_PLAYER, GLFW.GLFW_KEY_R, ModI18n.CATEGORY_GENERAL);
		ClientRegistry.registerKeyBinding(guiPlayer);

		enterCombatMode = new KeyBinding(ModI18n.KEY_COMBATMODE, GLFW.GLFW_KEY_LEFT_ALT, ModI18n.CATEGORY_GENERAL);
		ClientRegistry.registerKeyBinding(enterCombatMode);

		combatSlot1 = new KeyBinding(ModI18n.KEY_COMBATSLOT1, GLFW.GLFW_KEY_1, ModI18n.CATEGORY_GENERAL);
		ClientRegistry.registerKeyBinding(combatSlot1);
		combatSlot2 = new KeyBinding(ModI18n.KEY_COMBATSLOT2, GLFW.GLFW_KEY_2, ModI18n.CATEGORY_GENERAL);
		ClientRegistry.registerKeyBinding(combatSlot2);
		combatSlot3 = new KeyBinding(ModI18n.KEY_COMBATSLOT3, GLFW.GLFW_KEY_3, ModI18n.CATEGORY_GENERAL);
		ClientRegistry.registerKeyBinding(combatSlot3);
		combatSlot4 = new KeyBinding(ModI18n.KEY_COMBATSLOT4, GLFW.GLFW_KEY_4, ModI18n.CATEGORY_GENERAL);
		ClientRegistry.registerKeyBinding(combatSlot4);
		combatSlot5 = new KeyBinding(ModI18n.KEY_COMBATSLOT5, GLFW.GLFW_KEY_5, ModI18n.CATEGORY_GENERAL);
		ClientRegistry.registerKeyBinding(combatSlot5);
		combatSlot6 = new KeyBinding(ModI18n.KEY_COMBATSLOT6, GLFW.GLFW_KEY_6, ModI18n.CATEGORY_GENERAL);
		ClientRegistry.registerKeyBinding(combatSlot6);
		combatSlot7 = new KeyBinding(ModI18n.KEY_COMBATSLOT7, GLFW.GLFW_KEY_7, ModI18n.CATEGORY_GENERAL);
		ClientRegistry.registerKeyBinding(combatSlot7);
		combatSlot8 = new KeyBinding(ModI18n.KEY_COMBATSLOT8, GLFW.GLFW_KEY_8, ModI18n.CATEGORY_GENERAL);
		ClientRegistry.registerKeyBinding(combatSlot8);

		keyBindsCombatbar = new KeyBinding[]
			{
					combatSlot1, combatSlot2, combatSlot3, combatSlot4, combatSlot5, combatSlot6, combatSlot7, combatSlot8
			};
	}

	public static boolean isShiftKeyDown()
	{
		return InputMappings.isKeyDown(Minecraft.getInstance().mainWindow.getHandle(), GLFW.GLFW_KEY_LEFT_SHIFT) || InputMappings.isKeyDown(Minecraft.getInstance().mainWindow.getHandle(), GLFW.GLFW_KEY_RIGHT_SHIFT);
	}

	@SubscribeEvent
	public static void onKeyInput(KeyInputEvent event)
	{
		Minecraft minecraft = Minecraft.getInstance();
		PlayerEntity player = minecraft.player;

		if (player == null)
			return;

		IAbilityData abilityDataProps = AbilityDataCapability.get(player);
		IEntityStats entityStatsProps = EntityStatsCapability.get(player);
		
		if (guiPlayer.isPressed())
		{
			if(!entityStatsProps.hasRace() || !entityStatsProps.hasFaction() || !entityStatsProps.hasFightingStyle())
				Minecraft.getInstance().displayGuiScreen(new CharacterCreatorScreen());
			else
				Minecraft.getInstance().displayGuiScreen(new PlayerStatsScreen(player));
		}

		if (enterCombatMode.isPressed())
		{
			entityStatsProps.setCombatMode(!entityStatsProps.isInCombatMode());
			if (entityStatsProps.isInCombatMode())
			{
				for (KeyBinding kb : Minecraft.getInstance().gameSettings.keyBindsHotbar)
				{
					kb.bind(InputMappings.getInputByCode(GLFW.GLFW_KEY_UNKNOWN, 0));
				}

				int keyId = GLFW.GLFW_KEY_1;
				for (KeyBinding kb : keyBindsCombatbar)
				{
					if (kb.getKey().getKeyCode() < GLFW.GLFW_KEY_8)
						kb.bind(InputMappings.getInputByCode(keyId, 0));
					keyId++;
				}

				KeyBinding.resetKeyBindingArrayAndHash();
			}
			else
			{
				for (KeyBinding kb : keyBindsCombatbar)
				{
					if (kb.getKey().getKeyCode() <= GLFW.GLFW_KEY_8)
						kb.bind(InputMappings.getInputByCode(GLFW.GLFW_KEY_UNKNOWN, 0));
				}

				int keyId = GLFW.GLFW_KEY_1;
				for (KeyBinding kb : Minecraft.getInstance().gameSettings.keyBindsHotbar)
				{
					kb.bind(InputMappings.getInputByCode(keyId, 0));
					keyId++;
				}

				KeyBinding.resetKeyBindingArrayAndHash();
			}
			WyNetwork.sendToServer(new CCombatModeTriggerPacket());
		}

		int j = keyBindsCombatbar.length;

		for (int i = 0; i < j; i++)
		{
			if (keyBindsCombatbar[i].isPressed())
			{
				Ability abl = abilityDataProps.getEquippedAbility(i);
				if (entityStatsProps.isInCombatMode() && abl != null)
					WyNetwork.sendToServer(new CUseAbilityPacket(i));
				else
					player.inventory.currentItem = i;
			}
		}
	}
}
