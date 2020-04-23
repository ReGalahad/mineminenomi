package xyz.pixelatedw.mineminenomi.abilities.cyborg;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.entities.projectiles.cyborg.FreshFireProjectile;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.packets.server.SEntityStatsSyncPacket;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.RepeaterAbility;
import xyz.pixelatedw.wypi.network.WyNetwork;

public class FreshFireAbility extends RepeaterAbility
{
	public static final Ability INSTANCE = new FreshFireAbility();

	public FreshFireAbility()
	{
		super("Fresh Fire", AbilityCategory.RACIAL);
		this.setMaxCooldown(5);
		this.setMaxRepearCount(10, 3);
		this.setDescription("The user heats up and breathes fire like a flamethrower at the opponent.");

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		IEntityStats props = EntityStatsCapability.get(player);

		if(props.getCola() - 1 < 0)
		{
			WyHelper.sendMsgToPlayer(player, new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NOT_ENOUGH_COLA).getFormattedText());
			return false;
		}
		
		FreshFireProjectile proj = new FreshFireProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 10);
			
		props.alterCola(-1);
		WyNetwork.sendTo(new SEntityStatsSyncPacket(player.getEntityId(), props), player);
		
		return true;
	}
}