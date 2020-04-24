package xyz.pixelatedw.mineminenomi.abilities.ito;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;

public class SoraNoMichiAbility extends Ability
{
	public static final SoraNoMichiAbility INSTANCE = new SoraNoMichiAbility();
	
	private int airJumps = 0;
	
	public SoraNoMichiAbility()
	{
		super("Sora no Michi", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(2);
		this.setDescription("The user attaches the strings to clouds, allowing them to move through the air.");

		this.onUseEvent = this::onUseEvent;
	}
	
	private boolean onUseEvent(PlayerEntity player)
	{		
		Vec3d speed;
		if(player.onGround)
		{
			speed = WyHelper.propulsion(player, 1.0, 1.0);
			player.setMotion(speed.x, 1.9, speed.z);
			this.airJumps = 0;
		}
		else
		{
			speed = WyHelper.propulsion(player, 1.5, 1.5);
			player.setMotion(speed.x, 1.3, speed.z);
			this.airJumps++;
		}

		player.velocityChanged = true;
		((ServerWorld) player.world).getChunkProvider().sendToTrackingAndSelf(player, new SAnimateHandPacket(player, 0));

		if(this.airJumps > 10)
		{
			this.airJumps = 0;
			this.setMaxCooldown(10);
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
