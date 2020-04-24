package xyz.pixelatedw.mineminenomi.abilities.rokushiki;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.rokushiki.GeppoParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;

public class GeppoAbility extends Ability
{
	public static final Ability INSTANCE = new GeppoAbility();
	
	private static final ParticleEffect PARTICLES = new GeppoParticleEffect();
	private int airJumps = 0;
	
	public GeppoAbility()
	{
		super("Geppo", AbilityCategory.RACIAL);
		this.setMaxCooldown(2);
		this.setDescription("The user kicks the air beneath them to launch themselves into the air.");

		this.onUseEvent = this::onUseEvent;
	}
	
	private boolean onUseEvent(PlayerEntity player)
	{		
		Vec3d speed;
		if(player.onGround)
		{
			speed = WyHelper.propulsion(player, 1.0, 1.0);
			player.setMotion(speed.x, 1.86, speed.z);
			this.airJumps = 0;
		}
		else
		{
			speed = WyHelper.propulsion(player, 1.5, 1.5);
			player.setMotion(speed.x, 1.25, speed.z);
			this.airJumps++;
		}

		player.velocityChanged = true;

		PARTICLES.spawn(player.world, player.posX, player.posY, player.posZ, 0, 0, 0);
		
		if(this.airJumps > 5)
		{
			this.airJumps = 0;
			this.setMaxCooldown(20);
			this.startCooldown();
			return true;
		}
		else
		{
			this.setMaxCooldown(2);
		}
		
		return true;
	}
}