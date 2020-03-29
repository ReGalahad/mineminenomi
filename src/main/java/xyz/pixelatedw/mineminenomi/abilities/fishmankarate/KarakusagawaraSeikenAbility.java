package xyz.pixelatedw.mineminenomi.abilities.fishmankarate;

import java.util.List;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;

public class KarakusagawaraSeikenAbility extends Ability
{
	public static final Ability INSTANCE = new KarakusagawaraSeikenAbility();
	
	public KarakusagawaraSeikenAbility()
	{
		super("Karakusagawara Seiken", AbilityCategory.RACIAL);
		this.setMaxCooldown(25);
		this.setDescription("The user punches the air, which sends a shockwave through water vapor in the air.");

		this.onUseEvent = this::onUseEvent;
	}
	
	private boolean onUseEvent(PlayerEntity player)
	{
		List<LivingEntity> targets = WyHelper.<LivingEntity>getEntitiesNear(player.getPosition(), player.world, 10);
		targets.remove(player);

		targets.stream().forEach(entity -> 
		{
			entity.attackEntityFrom(DamageSource.causePlayerDamage(player), entity.isInWater() ? 40 : 20);
			entity.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 200, 1));
		});

		return true;
	}
}