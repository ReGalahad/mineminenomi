package xyz.pixelatedw.mineminenomi.events.passives;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.GlStateManager.DestFactor;
import com.mojang.blaze3d.platform.GlStateManager.SourceFactor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.LeftClickBlock;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.event.world.BlockEvent.EntityPlaceEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.abilities.horo.YutaiRidatsuAbility;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class HoroPassiveEvents
{
	@SubscribeEvent
	public static void onEntityUpdate(LivingUpdateEvent event)
	{
		if (!(event.getEntityLiving() instanceof PlayerEntity))
			return;	
		
		PlayerEntity player = (PlayerEntity) event.getEntityLiving();
		IDevilFruit devilFruitProps = DevilFruitCapability.get(player);
		IAbilityData abilityProps = AbilityDataCapability.get(player);
				
		if (!devilFruitProps.getDevilFruit().equals("horo_horo") || player.isCreative() || player.isSpectator())
			return;
		
		Ability ability = abilityProps.getEquippedAbility(YutaiRidatsuAbility.INSTANCE);
		boolean isActive = ability != null && ability.isContinuous();
		
		if(!isActive)
		{
			player.noClip = false;
			player.abilities.isFlying = false;
			if(player instanceof ServerPlayerEntity)
				((ServerPlayerEntity)player).sendPlayerAbilities();
			return;
		}
		
		player.onGround = false;
		player.noClip = true;
		player.abilities.isFlying = true;
		if(player instanceof ServerPlayerEntity)
			((ServerPlayerEntity)player).sendPlayerAbilities();
	}
	
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onEntityRendered(RenderLivingEvent.Pre event)
	{
		if (!(event.getEntity() instanceof PlayerEntity))
			return;	
		
		PlayerEntity player = (PlayerEntity) event.getEntity();

		if(!isSpirit(player))
			return;

		GlStateManager.color4f(0.3F, 0.7F, 0.9F, 0.6F);
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(SourceFactor.DST_COLOR, DestFactor.ONE_MINUS_CONSTANT_COLOR);
	}
	
	
	@SubscribeEvent
	public static void onEntityHits(AttackEntityEvent event)
	{
		PlayerEntity player = event.getPlayer();

		if(!isSpirit(player))
			return;
		
		event.setCanceled(true);
	}
	
	@SubscribeEvent
	public static void onEntityLeftClickBlocks(LeftClickBlock event)
	{
		PlayerEntity player = event.getPlayer();

		if(!isSpirit(player))
			return;

		event.setCanceled(true);
	}
	
	@SubscribeEvent
	public static void onEntityRightClickBlocks(RightClickBlock event)
	{
		PlayerEntity player = event.getPlayer();

		if(!isSpirit(player))
			return;

		event.setCanceled(true);
	}
	
	@SubscribeEvent
	public static void onEntityBreaksBlocks(BreakEvent event)
	{
		PlayerEntity player = event.getPlayer();

		if(!isSpirit(player))
			return;

		event.setCanceled(true);
	}
	
	@SubscribeEvent
	public static void onEntityPlaceBlocks(EntityPlaceEvent event)
	{
		if (!(event.getEntity() instanceof PlayerEntity))
			return;	
		
		PlayerEntity player = (PlayerEntity) event.getEntity();

		if(!isSpirit(player))
			return;
		
		event.setCanceled(true);
	}
	
	@SubscribeEvent
	public static void onEntityAttackEvent(LivingAttackEvent event)
	{	
		if (!(event.getEntity() instanceof PlayerEntity))
			return;	
		
		PlayerEntity player = (PlayerEntity) event.getEntity();

		if(isSpirit(player) && event.getSource() != DamageSource.MAGIC)
			event.setCanceled(true);
	}
	
	private static boolean isSpirit(PlayerEntity player)
	{
		IDevilFruit devilFruitProps = DevilFruitCapability.get(player);
		IAbilityData abilityProps = AbilityDataCapability.get(player);
		
		if (!devilFruitProps.getDevilFruit().equals("horo_horo") || player.isCreative() || player.isSpectator())
			return false;
		
		Ability ability = abilityProps.getEquippedAbility(YutaiRidatsuAbility.INSTANCE);
		boolean isActive = ability != null && ability.isContinuous();
		
		if(!isActive)
			return false;	
		
		return true;
	}
}
