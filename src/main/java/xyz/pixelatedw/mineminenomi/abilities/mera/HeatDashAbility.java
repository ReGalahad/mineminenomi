package xyz.pixelatedw.mineminenomi.abilities.mera;

import java.util.List;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.SEntityVelocityPacket;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.common.MinecraftForge;
import xyz.pixelatedw.mineminenomi.api.helpers.CrewHelper;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.mera.HeatDashParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.events.SetOnFireEvent;

public class HeatDashAbility extends Ability
{
	public static final HeatDashAbility INSTANCE = new HeatDashAbility();
	
	private static final ParticleEffect PARTICLES = new HeatDashParticleEffect();
	
	public HeatDashAbility()
	{
		super("Heat Dash", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(7);
		this.setDescription("Transforms the user into fire and launches them forward.");

		this.onUseEvent = this::onUseEvent;
		this.duringCooldownEvent = this::duringCooldown;
	}
	
	private boolean onUseEvent(PlayerEntity player)
	{
		Vec3d speed = WyHelper.propulsion(player, 5, 2, 5);
		player.setMotion(speed.x, 0.5 + speed.y, speed.z);

		((ServerPlayerEntity)player).connection.sendPacket(new SEntityVelocityPacket(player));
			
		return true;
	}
	
	private void duringCooldown(PlayerEntity player, int cooldownTimer)
	{
		if ((cooldownTimer / 20) > (this.maxCooldown / 20) - 3)
		{
			if(cooldownTimer % 2 == 0)
				PARTICLES.spawn(player.world, player.posX, player.posY, player.posZ, 0, 0, 0);
			List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 1.4, CrewHelper.NOT_IN_CREW_PREDICATE, LivingEntity.class);
			list.remove(player);
			for (LivingEntity target : list)
			{
				SetOnFireEvent event = new SetOnFireEvent(player, target, 2);
				if (!MinecraftForge.EVENT_BUS.post(event))
					target.setFire(2);
			}
		}
	}
}
