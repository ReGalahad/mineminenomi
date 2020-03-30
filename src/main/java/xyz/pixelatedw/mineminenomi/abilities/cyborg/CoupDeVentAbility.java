package xyz.pixelatedw.mineminenomi.abilities.cyborg;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.entities.projectiles.cyborg.CoupDeVentProjectile;
import xyz.pixelatedw.mineminenomi.packets.server.SEntityStatsSyncPacket;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.network.WyNetwork;

public class CoupDeVentAbility extends Ability
{
	public static final Ability INSTANCE = new CoupDeVentAbility();

	public CoupDeVentAbility()
	{
		super("Coup de Vent", AbilityCategory.RACIAL);
		this.setMaxCooldown(12);
		this.setDescription("Launches a powerful blast of compressed air that blows the opponent away.");

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		IEntityStats props = EntityStatsCapability.get(player);

		if(props.getCola() - 25 < 0)
		{
			WyHelper.sendMsgToPlayer(player, "Not enough Cola !");
			return false;
		}
		
		CoupDeVentProjectile proj = new CoupDeVentProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 3f, 1);
				
		props.alterCola(-25);
		WyNetwork.sendTo(new SEntityStatsSyncPacket(player.getEntityId(), props), player);
		
		return true;
	}
}