package xyz.pixelatedw.mineminenomi.abilities.moku;

import java.util.List;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.moku.WhiteStrikeParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;

public class WhiteStrikeAbility extends Ability
{
	public static final Ability INSTANCE = new WhiteStrikeAbility();
	
	private static final ParticleEffect PARTICLES = new WhiteStrikeParticleEffect();

	public WhiteStrikeAbility()
	{
		super("White Strike", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(30);
		this.setDescription("Surrounds the nearby area with smoke slowing down nearby entities");
		
		this.onUseEvent = this::onUseEvent;
		this.duringCooldownEvent = this::duringCooldownEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		List<LivingEntity> targets = WyHelper.<LivingEntity>getEntitiesNear(player.getPosition(), player.world, 10, LivingEntity.class);
		targets.remove(player);
		
		targets.stream().forEach(entity -> 
		{
			entity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 350, 2));
			entity.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 350, 2));
			entity.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 350, 2));
		});
		
		return true;
	}
	
	private void duringCooldownEvent(PlayerEntity player, int cooldown)
	{
		if(cooldown > WyHelper.percentage(80, this.maxCooldown))
			PARTICLES.spawn(player.world, player.posX, player.posY, player.posZ, 0, 0, 0);
	}
}
