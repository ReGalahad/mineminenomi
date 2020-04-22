package xyz.pixelatedw.mineminenomi.quests.sniper.objectives;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.entities.projectiles.sniper.RenpatsuNamariBoshiProjectile;
import xyz.pixelatedw.wypi.quests.objectives.IKillEntityObjective;

public class RenpatsuKillInSecondsObjective extends ArrowKillInSecondsObjective implements IKillEntityObjective
{	
	public RenpatsuKillInSecondsObjective(String title, int count, int threshold)
	{
		super(title, count, threshold);
	}

	@Override
	public boolean checkKill(PlayerEntity player, LivingEntity target, DamageSource source)
	{		
		boolean renpatsuFlag = source.getImmediateSource() instanceof RenpatsuNamariBoshiProjectile;
		
		return renpatsuFlag && super.checkKill(player, target, source);
	}

}
