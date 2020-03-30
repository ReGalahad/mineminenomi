package xyz.pixelatedw.mineminenomi.abilities.swordsman;

import java.util.List;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitsHelper;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.swordsman.OTatsumakiParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;

public class OTatsumakiAbility extends Ability
{
	public static final Ability INSTANCE = new OTatsumakiAbility();

	private static final ParticleEffect PARTICLES = new OTatsumakiParticleEffect();
	
	public OTatsumakiAbility()
	{
		super("O Tatsumaki", AbilityCategory.RACIAL);
		this.setMaxCooldown(8);
		this.setDescription("By spinning, the user creates a small tornado, which slashes and weakens nearby opponents.");

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		if (!DevilFruitsHelper.canUseSwordsmanAbilities(player))
		{
			WyHelper.sendMsgToPlayer(player, "You need a sword to use this ability !");
			return false;
		}

		List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 2.5);
		list.remove(player);

		list.stream().forEach(entity ->
		{
			entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 15);
		});
		
		PARTICLES.spawn(player.world, player.posX, player.posY, player.posZ, 0, 0, 0);

		return true;
	}
}