package xyz.pixelatedw.mineminenomi.quests.objectives.arrow;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.quests.objectives.IArrowKillObjective;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.quests.objectives.Objective;

public class ArrowKillInSecondsObjective extends Objective implements IArrowKillObjective
{
	private long lastKill = 0;
	private int seconds = 0;
	
	public ArrowKillInSecondsObjective(String title, int count, int seconds)
	{
		super(title);
		this.setMaxProgress(count);
		this.seconds = seconds * 20;
	}

	@Override
	public boolean checkKill(PlayerEntity player, LivingEntity target, DamageSource source)
	{
		long killTime = player.world.getGameTime();

		if(this.lastKill == 0)
			this.lastKill = player.world.getGameTime();

		if(killTime - this.seconds <= this.lastKill)
		{
			this.lastKill = killTime;
			return this.checkArrowKill(player, target, source);			
		}
		else
		{
			this.setProgress(0);
			this.lastKill = 0;
			return false;
		}	
	}

	@Override
	public String getLocalizedTitle() 
	{
		String objectiveKey = new TranslationTextComponent(String.format("quest.objective." + APIConfig.PROJECT_ID + ".%s", this.getId())).getKey();
		return new TranslationTextComponent(objectiveKey, (int)this.getMaxProgress(), (this.seconds / 20)).getFormattedText(); 
	}
}
