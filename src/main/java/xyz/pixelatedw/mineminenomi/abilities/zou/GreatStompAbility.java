package xyz.pixelatedw.mineminenomi.abilities.zou;

import java.util.List;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.entities.zoan.ZouGuardZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.zou.GreatStompParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;

public class GreatStompAbility extends Ability
{
	public static final GreatStompAbility INSTANCE = new GreatStompAbility();

	private static final ParticleEffect PARTICLES = new GreatStompParticleEffect();
	
	public GreatStompAbility()
	{
		super("Great Stomp", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(12);
		
		this.onUseEvent = this::onUseEvent;
	}
	
	private boolean onUseEvent(PlayerEntity player)
	{
		IDevilFruit props = DevilFruitCapability.get(player);
		
		if (!props.getZoanPoint().equalsIgnoreCase(ZouGuardZoanInfo.FORM))
		{
			WyHelper.sendMsgToPlayer(player, new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NOT_ZOAN_FORM_SINGLE, this.getName(), ZouGuardPointAbility.INSTANCE.getName()).getFormattedText());
			return false;
		}
		
		List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 10, FactionHelper.getOutsideGroupPredicate(player), LivingEntity.class);
		targets.remove(player);
		
		for(LivingEntity entity : targets)
		{
			entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 10);
			entity.setPositionAndUpdate(entity.posX, entity.posY + 3, entity.posZ);
		}
		
		PARTICLES.spawn(player.world, player.posX, player.posY, player.posZ, 0, 0, 0);
		
		return true;
	}
}
