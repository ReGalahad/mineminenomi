package xyz.pixelatedw.mineminenomi.abilities.cyborg;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.entities.projectiles.cyborg.RadicalBeamProjectile;
import xyz.pixelatedw.mineminenomi.packets.server.SEntityStatsSyncPacket;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.network.WyNetwork;

public class RadicalBeamAbility extends Ability
{
	public static final Ability INSTANCE = new RadicalBeamAbility();

	public RadicalBeamAbility()
	{
		super("Radical Beam", AbilityCategory.RACIAL);
		this.setMaxCooldown(7);
		this.setDescription("The user launches a powerful beam of energy at the opponent.");

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		IEntityStats props = EntityStatsCapability.get(player);

		if(props.getCola() - 15 < 0)
		{
			WyHelper.sendMsgToPlayer(player, "Not enough Cola !");
			return false;
		}
		
		RadicalBeamProjectile proj = new RadicalBeamProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 3f, 1);
				
		props.alterCola(-15);
		WyNetwork.sendTo(new SEntityStatsSyncPacket(player.getEntityId(), props), player);
		
		return true;
	}
}