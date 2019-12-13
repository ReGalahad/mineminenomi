package xyz.pixelatedw.mineminenomi.init;

import org.lwjgl.glfw.GLFW;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.client.event.InputEvent.KeyInputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.IAbilityData;
import xyz.pixelatedw.mineminenomi.packets.client.CCombatModeTriggerPacket;
import xyz.pixelatedw.mineminenomi.packets.client.CRequestSyncPacket;
import xyz.pixelatedw.mineminenomi.packets.client.CUseAbilityPacket;
import xyz.pixelatedw.mineminenomi.screens.PlayerStatsScreen;

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

	@SuppressWarnings("resource")
	@SubscribeEvent
	public void onKeyInput(KeyInputEvent event)
	{
		Minecraft minecraft = Minecraft.getInstance();
		PlayerEntity player = minecraft.player;
		ClientWorld world = minecraft.world;

		if (player == null)
			return;

		IAbilityData abilityDataProps = AbilityDataCapability.get(player);

		if (guiPlayer.isPressed())
		{
			byte sync = 0b000100111;
			ModNetwork.sendToServer(new CRequestSyncPacket(sync));

			Minecraft.getInstance().displayGuiScreen(new PlayerStatsScreen(player));
		}

		if (enterCombatMode.isPressed())
		{
			abilityDataProps.setCombatMode(!abilityDataProps.isInCombatMode());
			if (abilityDataProps.isInCombatMode())
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
					if (kb.getKey().getKeyCode() < GLFW.GLFW_KEY_8)
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
			ModNetwork.sendToServer(new CCombatModeTriggerPacket());
		}

		int j = keyBindsCombatbar.length;

		for (int i = 0; i < j; i++)
		{
			if (keyBindsCombatbar[i].isPressed())
			{
				if (abilityDataProps.isInCombatMode())
					ModNetwork.sendToServer(new CUseAbilityPacket(i));
				else
					player.inventory.currentItem = i;
			}
		}
	}
}
