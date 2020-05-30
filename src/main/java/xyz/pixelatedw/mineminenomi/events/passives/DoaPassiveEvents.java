package xyz.pixelatedw.mineminenomi.events.passives;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityViewRenderEvent.CameraSetup;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.LeftClickBlock;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.event.world.BlockEvent.EntityPlaceEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.abilities.doa.AirDoorAbility;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class DoaPassiveEvents
{
	@SubscribeEvent
	public static void onEntityHits(AttackEntityEvent event)
	{
		PlayerEntity player = event.getPlayer();

		if(!isInsideDoor(player))
			return;
		
		event.setCanceled(true);
	}
	
	@SubscribeEvent
	public static void onEntityLeftClickBlocks(LeftClickBlock event)
	{
		PlayerEntity player = event.getPlayer();

		if(!isInsideDoor(player))
			return;

		event.setCanceled(true);
	}
	
	@SubscribeEvent
	public static void onEntityRightClickBlocks(RightClickBlock event)
	{
		PlayerEntity player = event.getPlayer();

		if(!isInsideDoor(player))
			return;

		event.setCanceled(true);
	}
	
	@SubscribeEvent
	public static void onEntityBreaksBlocks(BreakEvent event)
	{
		PlayerEntity player = event.getPlayer();

		if(!isInsideDoor(player))
			return;

		event.setCanceled(true);
	}
	
	@SubscribeEvent
	public static void onEntityPlaceBlocks(EntityPlaceEvent event)
	{
		if (!(event.getEntity() instanceof PlayerEntity))
			return;	
		
		PlayerEntity player = (PlayerEntity) event.getEntity();

		if(!isInsideDoor(player))
			return;
		
		event.setCanceled(true);
	}
	
	@SubscribeEvent
	public static void onEntityAttackEvent(LivingAttackEvent event)
	{	
		if (!(event.getEntity() instanceof PlayerEntity))
			return;	
		
		PlayerEntity player = (PlayerEntity) event.getEntity();

		if(!isInsideDoor(player))
			return;
		
		event.setCanceled(true);
	}
	
	private static boolean isInsideDoor(PlayerEntity player)
	{
		IDevilFruit devilFruitProps = DevilFruitCapability.get(player);
		IAbilityData abilityProps = AbilityDataCapability.get(player);
		
		if (!devilFruitProps.getDevilFruit().equals("doa_doa"))
			return false;
		
		Ability ability = abilityProps.getEquippedAbility(AirDoorAbility.INSTANCE);
		boolean isActive = ability != null && ability.isContinuous();
		
		if(!isActive)
			return false;	
		
		return true;
	}
	
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onPlayerCameraSetup(CameraSetup event)
	{
		PlayerEntity player = Minecraft.getInstance().player;
	
		if (!player.isPotionActive(ModEffects.DOOR_HEAD))
			return;

		if (player.getActivePotionEffect(ModEffects.DOOR_HEAD).getDuration() <= 0)
		{
			player.removePotionEffect(ModEffects.DOOR_HEAD);
			return;
		}
	
		if(Minecraft.getInstance().gameSettings.thirdPersonView == 0)	
			event.setYaw((player.ticksExisted * 10) % 360);
	}
	
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onEntityRendered(RenderLivingEvent.Pre event)
	{
		LivingEntity entity = event.getEntity();

		if (entity.isPotionActive(ModEffects.DOOR_HEAD))
		{
			if (entity.getActivePotionEffect(ModEffects.DOOR_HEAD).getDuration() <= 0)
			{
				entity.removePotionEffect(ModEffects.DOOR_HEAD);
				return;
			}
			
			entity.renderYawOffset = 0;
			entity.prevRenderYawOffset = 0;
		}

		IDevilFruit devilFruitProps = DevilFruitCapability.get(entity);
		IAbilityData abilityProps = AbilityDataCapability.get(entity);
		
		if (devilFruitProps.getDevilFruit().equalsIgnoreCase("doa_doa"))
		{
			AirDoorAbility ability = abilityProps.getEquippedAbility(AirDoorAbility.INSTANCE);
			boolean isActive = ability != null && ability.isContinuous();
			
			if(isActive)
				event.setCanceled(true);
		}
		
	}
	
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onEntityRendered(RenderLivingEvent.Post event)
	{
		LivingEntity entity = event.getEntity();
		
		if (!entity.isPotionActive(ModEffects.DOOR_HEAD))
			return;

		if (entity.getActivePotionEffect(ModEffects.DOOR_HEAD).getDuration() <= 0)
		{
			entity.removePotionEffect(ModEffects.DOOR_HEAD);
			return;
		}
		
		entity.rotationYawHead += 10;
		entity.prevRotationYawHead += 10;
		entity.renderYawOffset = 0;
		entity.prevRenderYawOffset = 0;
	}
	
	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public static void onFirstPersonViewRendered(TickEvent.RenderTickEvent event)
	{
		Minecraft mc = Minecraft.getInstance();
		PlayerEntity player = mc.player;

		if (player == null)
			return;

		if(!isInsideDoor(player))
			return;
		
		WyHelper.drawColourOnScreen(WyHelper.hexToRGB("#2AFFAE").getRGB(), 50, 0, 0, mc.mainWindow.getScaledWidth(), mc.mainWindow.getScaledHeight(), 500);
	}
}
