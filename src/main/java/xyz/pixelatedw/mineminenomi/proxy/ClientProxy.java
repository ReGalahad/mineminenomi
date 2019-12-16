package xyz.pixelatedw.mineminenomi.proxy;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
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
import xyz.pixelatedw.mineminenomi.particles.CustomParticle;
import xyz.pixelatedw.mineminenomi.particles.CustomParticleData;
import xyz.pixelatedw.mineminenomi.particles.CustomParticleManager;
import xyz.pixelatedw.mineminenomi.screens.CharacterCreatorScreen;
import xyz.pixelatedw.mineminenomi.screens.QuestAcceptScreen;
import xyz.pixelatedw.mineminenomi.screens.WantedPosterScreen;
import xyz.pixelatedw.mineminenomi.values.ModValuesParticles;

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
	public void spawnLogiaParticles(World world, String fx, double posX, double posY, double posZ)
	{
		String devilFruit = fx.substring("logiaEffect_".length(), fx.length());

		for(int i = 0; i < 3; i++)
		{
			double offsetX = (new Random().nextInt(20) + 1.0D - 10.0D) / 15.0D;
			double offsetY = (new Random().nextInt(20) + 1.0D - 10.0D) / 15.0D;
			double offsetZ = (new Random().nextInt(20) + 1.0D - 10.0D) / 15.0D;
			
			CustomParticleData data = new CustomParticleData();
			data.setPosX(posX + offsetX);
			data.setPosY(posY + 1 + offsetY);
			data.setPosZ(posZ + offsetZ);
			data.setMotionY(0.01);
			
			data.setMaxAge(10);
			data.setScale(1.5F);
			
			if(devilFruit.equals("meramera"))
				data.setTexture(ModValuesParticles.PARTICLE_ICON_MERA);
			else if(devilFruit.equals("hiehie"))
				data.setTexture(ModValuesParticles.PARTICLE_ICON_HIE);
			else if(devilFruit.equals("pikapika"))
				data.setTexture(ModValuesParticles.PARTICLE_ICON_PIKA);
			else if(devilFruit.equals("gorogoro"))
				data.setTexture(ModValuesParticles.PARTICLE_ICON_GORO);
			else if(devilFruit.equals("mokumoku"))
				data.setTexture(ModValuesParticles.PARTICLE_ICON_MOKU);
			else if(devilFruit.equals("sunasuna"))
				data.setTexture(ModValuesParticles.PARTICLE_ICON_SUNA2);
			else if(devilFruit.equals("magumagu"))
				world.addParticle(ParticleTypes.LAVA, data.getPosX(), data.getPosY(), data.getPosZ(), 0D, 0D, 0D);
			else if(devilFruit.equals("gasugasu"))
				data.setTexture(ModValuesParticles.PARTICLE_ICON_GASU);
			else if(devilFruit.equals("yukiyuki"))
				data.setTexture(ModValuesParticles.PARTICLE_ICON_YUKI);
			
			this.spawnParticles(world, data);
		}
	}

	@Override
	public void spawnVanillaParticle(IParticleData particle, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		Minecraft.getInstance().world.addParticle(particle, posX, posY, posZ, motionX, motionY, motionZ);			
	}
	
	@Override
	public void spawnParticles(World world, CustomParticleData data)
	{
		CustomParticle cp = new CustomParticle(world, data.getTexture(),
				data.getPosX(), 
				data.getPosY(),
				data.getPosZ(), 
				data.getMotionX(),
				data.getMotionY(),
				data.getMotionZ());
		
		if(data.hasCustomGravity())
			cp.setParticleGravity(data.getGravity());
		
		if(data.hasCustomScale())
			cp.setParticleScale(data.getScale());
		
		if(data.hasCustomMaxAge())
			cp.setParticleAge(data.getMaxAge());
		
		cp.setColor(data.getRedColor(), data.getGreenColor(), data.getBlueColor());

		Minecraft.getInstance().particles.addEffect(cp);
	}
	
	@Override
	public boolean spawnParticleEffects(PlayerEntity player, double posX, double posY, double posZ, String fx)
	{
		return CustomParticleManager.getInstance().spawnFX(player, posX, posY, posZ, fx);
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
