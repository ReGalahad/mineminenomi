package xyz.pixelatedw.mineminenomi.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import xyz.pixelatedw.mineminenomi.api.debug.WyDebug;
import xyz.pixelatedw.mineminenomi.api.json.WyJSONHelper;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
import xyz.pixelatedw.mineminenomi.events.EventsCombatMode;
import xyz.pixelatedw.mineminenomi.helpers.MorphsHelper;
import xyz.pixelatedw.mineminenomi.helpers.WebAppHelper;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModKeybindings;
import xyz.pixelatedw.mineminenomi.init.ModRenderers;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.screens.CharacterCreatorScreen;
import xyz.pixelatedw.mineminenomi.screens.QuestAcceptScreen;
import xyz.pixelatedw.mineminenomi.screens.WantedPosterScreen;

public class ClientProxy implements IProxy
{

	public ClientProxy()
	{
		FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientProxy::clientSetup);

		// Handles Combat Mode GUI (including extra hearts, cola bar and obviously the ability slots) and the FOV remover
		MinecraftForge.EVENT_BUS.register(new EventsCombatMode());

		//  Keybindings
		ModKeybindings.init();	
		
		// Static strings
		ModI18n.init();
	}
		
	public static void clientSetup(FMLClientSetupEvent event)
	{
		ModRenderers.registerRenderers();

		if(WyDebug.isDebug())
		{
			WyJSONHelper.generateJSONLangs();
			WyJSONHelper.generateJSONModels(false);
			WyJSONHelper.generateJSONLootTables(false);
			WebAppHelper.generateWebAppJSONs();
		}
	}
	
	@Override
	public PlayerEntity getClientPlayer()
	{
		return Minecraft.getInstance().player;
	}

	@Override
	public World getClientWorld()
	{
		return Minecraft.getInstance().world;
	}

	@Override
	public void spawnParticleEffect(ParticleEffect effect, World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		effect.spawn(world, posX, posY, posZ, motionX, motionY, motionZ);
	}

	@Override
	public void openCharacterCreatorScreen(PlayerEntity player)
	{
		Minecraft.getInstance().displayGuiScreen(new CharacterCreatorScreen(player));
	}

	@Override
	public void openWantedPosterScreen(PlayerEntity player, CompoundNBT nbt)
	{
		Minecraft.getInstance().displayGuiScreen(new WantedPosterScreen(nbt));
	}
	
	@Override
	public void openQuestAcceptScreen(PlayerEntity player, Quest quest)
	{
		Minecraft.getInstance().displayGuiScreen(new QuestAcceptScreen(player, quest));
	}
	
	@Override
	public void updateEyeHeight(PlayerEntity player)
	{
		MorphsHelper.updateEyeView(player);
	}

}
