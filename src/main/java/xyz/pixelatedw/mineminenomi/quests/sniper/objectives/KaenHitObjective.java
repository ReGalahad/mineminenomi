package xyz.pixelatedw.mineminenomi.quests.sniper.objectives;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.entities.projectiles.sniper.KaenBoshiProjectile;
import xyz.pixelatedw.mineminenomi.quests.objectives.ArrowHitObjective;

public class KaenHitObjective extends ArrowHitObjective
{	
	public KaenHitObjective(String title, int count)
	{
		super(title, count);
	}

	@Override
	public boolean checkHit(PlayerEntity player, LivingEntity target, DamageSource source)
	{		
		boolean renpatsuFlag = source.getImmediateSource() instanceof KaenBoshiProjectile;
		boolean hasFireResFlag = target.isImmuneToFire();
		
		return !hasFireResFlag && renpatsuFlag && super.checkHit(player, target, source);
	}
}
