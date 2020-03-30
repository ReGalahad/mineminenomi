package xyz.pixelatedw.mineminenomi.abilities.cyborg;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.entities.projectiles.cyborg.StrongRightProjectile;
import xyz.pixelatedw.mineminenomi.packets.server.SEntityStatsSyncPacket;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.network.WyNetwork;

public class StrongRightAbility extends Ability
{
	public static final Ability INSTANCE = new StrongRightAbility();

	public StrongRightAbility()
	{
		super("Strong Right", AbilityCategory.RACIAL);
		this.setMaxCooldown(3);
		this.setDescription("The user punches the opponent with a metal fist.");

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		IEntityStats props = EntityStatsCapability.get(player);

		if(props.getCola() - 10 < 0)
		{
			WyHelper.sendMsgToPlayer(player, "Not enough Cola !");
			return false;
		}
		
		StrongRightProjectile proj = new StrongRightProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);
				
		props.alterCola(-10);
		WyNetwork.sendTo(new SEntityStatsSyncPacket(player.getEntityId(), props), player);
		
		return true;
	}
}