package xyz.pixelatedw.mineminenomi.quests.objectives.sniper;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.entities.projectiles.sniper.KaenBoshiProjectile;
import xyz.pixelatedw.mineminenomi.quests.objectives.IArrowHitObjective;
import xyz.pixelatedw.wypi.quests.objectives.Objective;

public class KaenHitObjective extends Objective implements IArrowHitObjective
{	
	public KaenHitObjective(String title, int count)
	{
		super(title);
		this.setMaxProgress(count);
	}

	@Override
	public boolean checkHit(PlayerEntity player, LivingEntity target, DamageSource source)
	{		
		boolean renpatsuFlag = source.getImmediateSource() instanceof KaenBoshiProjectile;
		boolean hasFireResFlag = target.isImmuneToFire();
		
		return !hasFireResFlag && renpatsuFlag && this.checkArrowHit(player, target, source);
	}
}
