package xyz.pixelatedw.mineminenomi.events.passives;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.abilities.LogiaInvulnerabilityAbility;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

import java.util.Vector;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class MaguPassiveEvents
{
	public static final LogiaInvulnerabilityAbility INVULNERABILITY_INSTANCE = new LogiaInvulnerabilityAbility(ModResources.MAGU, MaguPassiveEvents::maguDamage, DamageSource.IN_FIRE, DamageSource.ON_FIRE, DamageSource.LAVA, DamageSource.HOT_FLOOR);

	public static boolean maguDamage(LivingEntity target, LivingEntity attacker) {
		attacker.setFire(8);
		attacker.attackEntityFrom(DamageSource.LAVA, 5);
		return true;
	}

	@SubscribeEvent
	public static void onEntityUpdate(LivingUpdateEvent event)
	{
		if (!(event.getEntityLiving() instanceof PlayerEntity))
			return;	
		
		PlayerEntity player = (PlayerEntity) event.getEntityLiving();
		IDevilFruit devilFruitProps = DevilFruitCapability.get(player);

		if (!devilFruitProps.getDevilFruit().equals("magu_magu"))
			return;

		if (player.isInLava() && !player.abilities.isFlying) {
			float a = (player.moveForward > 0) ? 2f : 0.5f;
			float y = player.isSneaking() && !(player.posY - player.prevPosY > 0) ? 2f : 0f;
			Vec3d vec3d = player.getMotion().mul(a, y, a);

			if(player.posY - player.prevPosY > 0)
				player.setMotion(vec3d.add(0, 0.1, 0));
			else
				player.setMotion(vec3d);
		}
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onEntityBurning(RenderBlockOverlayEvent event)
	{
		PlayerEntity player = Minecraft.getInstance().player;
		IDevilFruit devilFruitProps = DevilFruitCapability.get(player);

		if (!devilFruitProps.getDevilFruit().equals("magu_magu"))
			return;

		if(player.isBurning() && player.areEyesInFluid(FluidTags.LAVA))
			event.setCanceled(true);
	}
	
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onEntityInLava(EntityViewRenderEvent.FogDensity event)
	{
		PlayerEntity player = Minecraft.getInstance().player;
		IDevilFruit devilFruitProps = DevilFruitCapability.get(player);

		if (!devilFruitProps.getDevilFruit().equals("magu_magu"))
			return;
		
		if(player.areEyesInFluid(FluidTags.LAVA))
		{
			event.setCanceled(true);
			GlStateManager.fogMode(GlStateManager.FogMode.EXP);
			event.setDensity(0.1F);
		}
	}
}
