package xyz.pixelatedw.mineminenomi.abilities.cyborg;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.network.WyNetwork;

public class ColaOverdriveAbility extends Ability
{
	public static final Ability INSTANCE = new ColaOverdriveAbility();

	public ColaOverdriveAbility()
	{
		super("Cola Overdrive", AbilityCategory.RACIAL);
		this.setMaxCooldown(5);
		this.setDescription("The user absorbs half of their cola at once to boost their physical abilities.");

		this.onUseEvent = this::onUseEvent;
	}
	
	private boolean onUseEvent(PlayerEntity player)
	{
		IEntityStats props = EntityStatsCapability.get(player);

		double half = props.getMaxCola() / 2;
		
		if(props.getCola() - half < 0)
		{
			WyHelper.sendMsgToPlayer(player, new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NOT_ENOUGH_COLA).getFormattedText());
			return false;
		}
		
		props.setCola((int) (props.getCola() - half));
		WyNetwork.sendTo(new SSyncEntityStatsPacket(player.getEntityId(), props), player);
		
		player.setHealth((float) (player.getHealth() + ((props.getCola() / 100) * player.getAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue())));
		
		player.addPotionEffect(new EffectInstance(Effects.SPEED, 400, 2));
		player.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 400, 2));
		player.addPotionEffect(new EffectInstance(Effects.STRENGTH, 400, 2));
		
		return true;
	}
}
